package io.modicon.telegrambot.bot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vdurmont.emoji.EmojiParser;
import feign.FeignException;
import io.modicon.ekatalogservice.api.dto.GetItemAndShopsResponse;
import io.modicon.ekatalogservice.api.dto.ItemDto;
import io.modicon.telegrambot.config.ApplicationConfig;
import io.modicon.telegrambot.exception.TelegramBotException;
import io.modicon.telegrambot.service.*;
import io.modicon.userservice.api.dto.ItemWithPricesDto;
import io.modicon.userservice.api.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.math.BigDecimal;
import java.util.List;

@Component
@Slf4j
public class Bot extends TelegramLongPollingBot {

    private final ApplicationConfig applicationConfig;
    private final UserService userService;
    private final ItemService itemService;
    private final ObjectMapper objectMapper;

    private static final String HELP_MESSAGE = """
            Я - бот, созданный для поиска товаров, по EKatalog. 
            """;

    public Bot(ApplicationConfig applicationConfig, UserService userService, ItemService itemService, ObjectMapper objectMapper) {
        this.applicationConfig = applicationConfig;
        this.userService = userService;
        this.itemService = itemService;
        this.objectMapper = objectMapper;
    }

    @Override
    public String getBotToken() {
        return applicationConfig.getApiKey();
    }

    @Override
    public String getBotUsername() {
        return applicationConfig.getBotName();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText();
            String chatId = String.valueOf(update.getMessage().getChatId());

            String command = message;
            String param = "";
            int paramBegin = message.indexOf(" ");
            if (paramBegin != -1) {
                command = message.substring(0, paramBegin);
                param = message.substring(paramBegin + 1);
            }

            BotCommandEnum botCommand = BotCommandEnum.getCommand(command);

            if (botCommand != null) {
                switch (botCommand) {
                    case START -> {
                        startCommandReceiver(chatId, update.getMessage());
                        executeMessage(chatId, HELP_MESSAGE);
                    }
                    case HELP -> executeMessage(chatId, HELP_MESSAGE);
                    case FIND_ITEM -> findItemCommandReceiver(chatId, param);
                    case FIND_SHOP -> findShopsCommandReceiver(chatId, param);
                    case ADD_ITEM -> addItemCommandReceiver(chatId, param);
                    case DELETE_ITEM -> deleteItemCommandReceiver(chatId, param);
                    case USER_ITEMS -> userItemsCommandReceiver(chatId);
                }
            } else {
                executeMessage(chatId, "Извините, я вас не понимаю. Попробуйте /help");
            }
        }
    }

    private void startCommandReceiver(String chatId, Message msg) {
        var username = msg.getChat().getUserName() == null ? msg.getChat().getFirstName() + msg.getChat().getLastName() : msg.getChat().getUserName();
        userService.registerUser(new UserDto(chatId, username));
        String answer = EmojiParser.parseToUnicode(String.format("Привет, %s, приятно познакомится! :blush:", username));
        executeMessage(chatId, answer);
        log.info("Replied to user {}", username);
    }

    private void findItemCommandReceiver(String chatId, String itemName) {
        List<ItemDto> items = itemService.findItems(itemName);
        executeMessage(chatId, ItemListDrawer.draw(items));
    }

    private void findShopsCommandReceiver(String chatId, String param) {
        GetItemAndShopsResponse prices = itemService.getPrices(param);
        executeMessage(chatId, ItemDrawer.draw(prices.getItem(), prices.getShops()));
    }

    private void addItemCommandReceiver(String chatId, String param) {
        try {
            String[] params = ParamHandler.toParamArray(param);
            userService.addItem(params[0], BigDecimal.valueOf(Double.parseDouble(params[1])), chatId);
            executeMessage(chatId, "Товар успешно добавлен в отслеживание");
        } catch (Exception e) {
            executeMessage(chatId, "Ошибка при обработке параметров: {" + param + "}");
        }
    }

    private void deleteItemCommandReceiver(String chatId, String param) {
        try {
            userService.deleteItem(param, chatId);
            executeMessage(chatId, "Товар больше не ослеживается");
        } catch (Exception e) {
            executeMessage(chatId, "Вы не отслеживали товар: {" + param + "}");
        }
    }

    private void userItemsCommandReceiver(String chatId) {
        List<ItemWithPricesDto> items = userService.getItems(chatId);
        executeMessage(chatId, ItemWithPricesDrawer.draw(items));
    }

    public void executeMessage(String chatId, String msg) {
        try {
            SendMessage sendMessage = new SendMessage(chatId, msg);
            sendMessage.enableMarkdown(true);
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
            executeMessage(chatId, "Что то пошло не так...");
        }
    }

    private String feignExceptionMapper(FeignException e) {
        try {
            return objectMapper.readValue(e.contentUTF8(), TelegramBotException.class).getMessage();
        } catch (JsonProcessingException ex) {
            log.error("Json exception parse error: " + ex.getMessage());
        }
        return "Что то пошло не так...";
    }

}
