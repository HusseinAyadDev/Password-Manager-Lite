package app.ui;

public enum Commands {
    LOGIN("L"),
    SIGNUP("S"),
    QUIT("X");

    private final String command;

    Commands(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public boolean isCommand(String input) {
        return isCommand(input, Commands.values());
    }

    public boolean isCommand(String input, Commands... commands) {
        for (Commands c : commands) {
            if (input.equalsIgnoreCase(c.getCommand())) {
                return true;
            }
        }
        return false;
    }

}
