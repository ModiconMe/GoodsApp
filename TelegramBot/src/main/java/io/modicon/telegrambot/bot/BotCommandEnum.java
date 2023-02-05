package io.modicon.telegrambot.bot;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum BotCommandEnum {
    START("/start"),
    HELP("/help"),
    FIND_ITEM("/find"),
    ADD_ITEM("/additem"),
    DELETE_ITEM("/deleteitem");

    private final String commandId;

    public static BotCommandEnum getCommand(String cmd) {
        BotCommandEnum botCommand = null;
        BotCommandEnum[] availableCommands = BotCommandEnum.values();
        for (BotCommandEnum command : availableCommands) {
            if (command.getCommandId().equals(cmd)) {
                botCommand = command;
                break;
            }
        }
        return botCommand;
    }

}
