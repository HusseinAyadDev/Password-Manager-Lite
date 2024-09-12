package app.ui.front;

public enum FrontPageCommands {
    ADD("A", "Add Account."),
    SHOWPASSWORDS("P", "Show passwords."),
    CHANGEACCOUNT("C", "Change account details."),
    REMOVEACCOUNT("R", "Remove Account."),
    SIGNOUT("S", "Sign out."),
    QUIT("X", "Quit program.");

    private final String command;
    private final String text;

    FrontPageCommands(String command, String text) {
        this.command = command;
        this.text = String.format("[%s] - ", command) + text;
    }

    public static FrontPageCommands getCommand(String input) {
        for (FrontPageCommands c : FrontPageCommands.values()) {
            if (isCommand(input, c)) {
                return c;
            }
        }
        return null;
    }

    public static boolean isCommand(String input) {
        return isCommand(input, FrontPageCommands.values());
    }

    public static boolean isCommand(String input, FrontPageCommands... commands) {
        for (FrontPageCommands c : commands) {
            if (input.equalsIgnoreCase(c.command)) {
                return true;
            }
        }
        return false;
    }

    public static void showCommands() {
        showCommands(FrontPageCommands.values());
    }

    public static void showCommands(FrontPageCommands... commands) {
        for (FrontPageCommands c : commands) {
            System.out.println(c.text);
        }
    }
}
