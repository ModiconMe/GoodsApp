package io.modicon.telegrambot.service;

public class ParamHandler {
    private static final String SEPARATORS = ",";

    public static String[] toParamArray(String param) {
        String[] splited = param.split(SEPARATORS);
        for (int i = 0; i < splited.length - 1; i++) {
            splited[i] = splited[i].trim();
        }
        return splited;
    }
}
