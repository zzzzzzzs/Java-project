package com.me.print;

/**
 * @author zs
 * @date 2021/12/14
 */
public class PrintUtil {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void black(String ss) {
        System.out.println(ANSI_BLACK + ss + ANSI_RESET);
    }

    public static void red(String ss) {
        System.out.println(ANSI_RED + ss + ANSI_RESET);
    }

    public static void green(String ss) {
        System.out.println(ANSI_GREEN + ss + ANSI_RESET);
    }

    public static void yellow(String ss) {
        System.out.println(ANSI_YELLOW + ss + ANSI_RESET);
    }

    public static void blue(String ss) {
        System.out.println(ANSI_BLUE + ss + ANSI_RESET);
    }

    public static void purple(String ss) {
        System.out.println(ANSI_PURPLE + ss + ANSI_RESET);
    }

    public static void cyan(String ss) {
        System.out.println(ANSI_CYAN + ss + ANSI_RESET);
    }

    public static void white(String ss) {
        System.out.println(ANSI_WHITE + ss + ANSI_RESET);
    }


    public static void main(String[] args) {
        black("asd");
        red("sad");
        green("asd");
        yellow("sad");
        blue("asd");
        purple("asd");
        cyan("sad");
        white("asd");
    }

}
