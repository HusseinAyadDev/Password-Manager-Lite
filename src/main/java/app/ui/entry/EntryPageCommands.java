package app.ui.entry;

public enum EntryPageCommands {
    LOGIN("L", "Login."),
    SIGNUP("S", "Signup."),
    QUIT("X", "Quit program.");

    private final String command;
    private final String text;

    EntryPageCommands(String command, String text) {
        this.command = command;
        this.text = String.format("[%s] - ", command) + text;
    }

    public static EntryPageCommands getCommand(String input) {
        for (EntryPageCommands c : EntryPageCommands.values()) {
            if (isCommand(input, c)) {
                return c;
            }
        }
        return null;
    }

    public static boolean isCommand(String input) {
        return isCommand(input, EntryPageCommands.values());
    }

    public static boolean isCommand(String input, EntryPageCommands... commands) {
        for (EntryPageCommands c : commands) {
            if (input.equalsIgnoreCase(c.command)) {
                return true;
            }
        }
        return false;
    }

    public static void showCommands() {
        showCommands(EntryPageCommands.values());
    }

    public static void showCommands(EntryPageCommands... commands) {
        for (EntryPageCommands c : commands) {
            System.out.println(c.text);
        }
    }
}
