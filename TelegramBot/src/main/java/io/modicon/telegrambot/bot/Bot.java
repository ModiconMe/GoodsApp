package io.modicon.telegrambot.bot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vdurmont.emoji.EmojiParser;
import feign.FeignException;
import io.modicon.telegrambot.config.ApplicationConfig;
import io.modicon.telegrambot.exception.TelegramBotException;
import io.modicon.telegrambot.service.UserService;
import io.modicon.userservice.api.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@Slf4j
public class Bot extends TelegramLongPollingBot {

    private final ApplicationConfig applicationConfig;
    private final UserService userService;
    private final ObjectMapper objectMapper;

    private static final String HELP_MESSAGE = """
            Я - бот-агрегатор акций, облигаций и курсов валют.
            Я помогу тебе найти нужные активы по идентефикаторам (Ticker или FIGI).
            Вот что я умею:
            /start - начало работы с ботом, регистрация пользователя по его chatId и username;
            /addstock - добавление актива по его идентификаторам (Ticker или FIGI). Данные вводятся в формате:
            TICKER1 QUANTITY1 FIGI2 QUANTITY2. Пример: SBER 20 GAZP 31 RU000A0JS6M0 16.
            /portfolioinfo - просмотр информации о портфеле пользователя.
            /updatestock - обновление количества актива в портфеле пользователя по FIGI.
            /deletestock - удаление актива из портфеля пользователя по FIGI.
            /currency - выводит курс валют на текущую дату.
            /currency 30.01.2022 - выводит курс валют на указанную дату.
            /findstock - поиск актива по его Ticker.
            """;

    public Bot(ApplicationConfig applicationConfig, UserService userService, ObjectMapper objectMapper) {
        this.applicationConfig = applicationConfig;
        this.userService = userService;
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

    private void executeMessage(String chatId, String msg) {
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
