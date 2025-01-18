package com.sviryd.assignment_system_technology;

import java.util.HashMap;
import java.util.Map;

public class CipherCVCInMsgConverter {
    private static final String MESSAGE_CVV_PREFIX = "Code";
    private static final String MESSAGE_CVV_POSTFIX = " ";
    private static final String MESSAGE_START = "<Message>";
    private static final String MESSAGE_END = "</Message>";

    /**
     * Маскирует шифр CVV в тексте всех сообщений пачки (для записи такого варианта в лог).
     */
    public String maskCipherCVCInPack(String xml) {
        Map<String, String> replaces = new HashMap<>(); // набор сообщения для замены
        String buf = xml;
        int startPos = buf.indexOf(MESSAGE_START);

        while (startPos > 0) {
            buf = buf.substring(startPos);
            int endPos = buf.indexOf(MESSAGE_END);
            int tailLength = MESSAGE_END.length();
            // если по какой-то причине нет завершающего тега, то считаем всю оставшуюся строку сообщением с CVV
            if (endPos <= 0) {
                endPos = buf.length() - 1;
                tailLength = 0;
            }
            String message = buf.substring(0, endPos + tailLength);
            String masked = maskCipherCVCInMsg(message);

            if (!message.equals(masked)) {
                replaces.put(message, masked);
            }

            startPos = buf.indexOf(MESSAGE_START, endPos + MESSAGE_END.length());
        }
        // в оригинальной строке выполняем замены
        for (Map.Entry<String, String> rec : replaces.entrySet()) {
            xml = xml.replace(rec.getKey(), rec.getValue());
        }
        return xml;
    }

    /**
     * Маскирует шифр CVV в тексте одного сообщения смс (для записи такого варианта в лог). Ожидается, что текст
     * сообщения в тегах &lt;Message&gt;&lt;/Message&gt; находится (если это не так и после шифра нет пробелов, то
     * маскирование всё равно будет выполнено, замаскировав чуть больше, для надежности)
     *
     * @param message смс с зашифрованным CVV
     * @return
     */
    private String maskCipherCVCInMsg(String message) {
        int startFrom = 0;
        String mask = "---";
        int cipherLength;

        int startPos = message.indexOf(MESSAGE_CVV_PREFIX, startFrom);
        if (startPos == -1) {
            return message;
        }
        startPos += MESSAGE_CVV_PREFIX.length() + 2; // ": " - это 2 символа
        int endPos = message.indexOf(MESSAGE_CVV_POSTFIX, startPos);
        if (endPos > 0) {
            cipherLength = endPos - startPos;
        } else {
            cipherLength = message.length() - startPos - MESSAGE_END.length();
        }
        message = message.substring(0, startPos) + mask + message.substring(startPos + cipherLength);
        return message;
    }
}

