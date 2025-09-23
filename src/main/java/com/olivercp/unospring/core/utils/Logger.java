package com.olivercp.unospring.core.utils;

public enum Logger {
    INSTANCE;

    private Logger() {}

    public enum COLOR {
        ANSI_RESET("\u001B[0m"),
        ANSI_BLACK("\u001B[30m"),
        ANSI_RED("\u001B[31m"),
        ANSI_GREEN("\u001B[32m"),
        ANSI_YELLOW("\u001B[33m"),
        ANSI_BLUE("\u001B[34m"),
        ANSI_PURPLE("\u001B[35m"),
        ANSI_CYAN("\u001B[36m"),
        ANSI_WHITE("\u001B[37m");

        private final String escapeCode;

        COLOR(String escapeCode) {
            this.escapeCode = escapeCode;
        }

        public String getEscapeCode() {
            return escapeCode;
        }
    }

    public void printError(String message) {
        String result = COLOR.ANSI_RED.escapeCode + "[ERROR]: " + message + COLOR.ANSI_RESET.escapeCode;
        System.err.println(result);
    }

    public void printInfo(String message) {
        String result = COLOR.ANSI_GREEN.escapeCode + "[INFO]: " + message + COLOR.ANSI_RESET.escapeCode;
        System.out.println(result);
    }

    public void printWarning(String message) {
        String result = COLOR.ANSI_YELLOW.escapeCode + "[WARNING]: " + message + COLOR.ANSI_RESET.escapeCode;
        System.out.println(result);
    }

    public void printDebug(String message) {
        String stringBuilder = COLOR.ANSI_CYAN.escapeCode + "[DEBUG]: " + message + COLOR.ANSI_RESET.escapeCode;
        System.out.println(stringBuilder);
    }


    public void printMessage(String message) {
        System.out.println(COLOR.ANSI_WHITE.escapeCode + "[MESSAGE]: " + message +  COLOR.ANSI_RESET.escapeCode);
    }

    public void printCustom(String type, String message, COLOR color) {
        System.out.println(color.escapeCode + "[" + type + "]: " + message + COLOR.ANSI_RESET.escapeCode);
    }

}
