package edu.project1;

public final class Main {

    private Main() {
    }

    @SuppressWarnings("checkstyle:UncommentedMain")
    public static void main(String[] args) {
        ConsoleHangman consoleHangman = new ConsoleHangman();
        consoleHangman.run();
    }

}
