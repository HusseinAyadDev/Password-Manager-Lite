package app.ui.front;

import app.ui.entry.EntryPageCommands;
import app.user.User;

import java.util.Scanner;

public class FrontPage {
    private final Scanner scanner;
    private final User user;

    public FrontPage(Scanner scanner, User user) {
        this.scanner = scanner;
        this.user = user;
    }

    public EntryPageCommands start() {

        while (true) {
            System.out.println();
            FrontPageCommands.showCommands();
            System.out.println();
            System.out.print("Choose Action: ");
            String userInput = scanner.next();

            if (!FrontPageCommands.isCommand(userInput)) {
                System.out.println();
                System.out.println("Invalid command!");
                continue;
            }
            FrontPageCommands command = FrontPageCommands.getCommand(userInput);

            switch (command) {
                case ADD:
                    addAccount();
                    break;
                case SHOWPASSWORDS:
                    user.printAccounts();
                    break;
                case CHANGEACCOUNT:
                    if (user.hasAccounts()) {
                        System.out.println();
                        System.out.println("No accounts exist.");
                        break;
                    }
                    changeAccountDetails();
                    break;
                case REMOVEACCOUNT:
                    if (user.hasAccounts()) {
                        System.out.println();
                        System.out.println("No accounts exist.");
                        break;
                    }
                    removeAccount();
                    break;
                case SIGNOUT:
                    return EntryPageCommands.LOGIN;
                case QUIT:
                    return EntryPageCommands.QUIT;
                case null:
                    break;
            }
        }
    }

    private void addAccount() {
        System.out.println();
        System.out.print("Enter Account name: ");
        String accountName = scanner.next();

        System.out.println();
        System.out.print("Enter Account username: ");
        String userName = scanner.next();

        System.out.println();
        System.out.print("Enter Account password: ");
        String password = scanner.next();

        user.addAccount(accountName, userName, password);
    }

    private void removeAccount() {
        int id;

        while (true) {
            System.out.println();
            System.out.println("Enter ID for account to remove or blank to cancel: ");
            scanner.nextLine();
            String userInput = scanner.nextLine();

            if (userInput.isBlank()) {
                return;
            }
            try {
                id = Integer.parseInt(userInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid ID");
                continue;
            }
            id -= 1;
            if (user.hasID(id)) {
                break;
            }
            System.out.println("Invalid ID");
        }
        user.removeAccount(id);
    }

    private void changeAccountDetails() {
        System.out.println();
        System.out.println("Leave blank if change for field is not required");
        int id;

        while (true) {
            System.out.println("Choose ID of account or leave blank to cancel edit: ");
            scanner.nextLine();
            String userInput = scanner.nextLine();

            if (userInput.isBlank()) {
                return;
            }
            try {
                id = Integer.parseInt(userInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid ID");
                continue;
            }
            id -= 1;
            if (user.hasID(id)) {
                break;
            }
            System.out.println("Invalid ID");
        }
        System.out.println();
        System.out.print("New Account Name: ");
        String userInput = scanner.nextLine();

        if (!userInput.isBlank()) {
            user.setAccountName(id, userInput);
        }
        System.out.println();
        System.out.print("New Username: ");
        userInput = scanner.nextLine();

        if (!userInput.isBlank()) {
            user.setAccountUsername(id, userInput);
        }
        System.out.println();
        System.out.print("New Password: ");
        userInput = scanner.nextLine();

        if (!userInput.isBlank()) {
            user.setAccountPassword(id, userInput);
        }
    }
}
