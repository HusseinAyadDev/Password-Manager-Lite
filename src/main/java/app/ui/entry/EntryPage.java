package app.ui.entry;

import app.ui.front.FrontPage;
import app.user.Users;

import java.util.Scanner;

public class EntryPage {

    private final Scanner scanner;
    private final Users users;

    public EntryPage(Scanner scanner, Users users) {
        this.scanner = scanner;
        this.users = users;
    }

    public void start() {

        while (true) {
            EntryPageCommands.showCommands();
            System.out.print("Choose Action: ");
            String userInput = scanner.next();

            if (!EntryPageCommands.isCommand(userInput)) {
                System.out.println("Invalid command!");
                continue;
            }
            EntryPageCommands command = EntryPageCommands.getCommand(userInput);

            while (true) {
                switch (command) {
                    case LOGIN:
                        command = startLogin();
                        break;
                    case SIGNUP:
                        command = startSignUp();
                        break;
                    case QUIT:
                        System.out.println("Closing ...");
                        users.serialize();
                        return;
                    case null:
                        break;
                }
            }
        }
    }

    private EntryPageCommands startLogin() {
        System.out.println();
        System.out.println("Login");
        EntryPageCommands.showCommands(EntryPageCommands.QUIT, EntryPageCommands.SIGNUP);

        while (true) {
            System.out.print("Enter Username: ");
            String username = scanner.next();

            if (EntryPageCommands.isCommand(username)) {
                return EntryPageCommands.getCommand(username);
            }

            System.out.print("Enter Password: ");
            String password = scanner.next();

            if (users.userValidator(username, password)) {
                System.out.println();
                System.out.println("access granted");
                FrontPage userPage = new FrontPage(scanner, users.getUser(username));
                return userPage.start();
            }
            System.out.println();
            System.out.println("Wrong Username or Password.");
            System.out.println("Try Again, or try different command!");
        }
    }

    private EntryPageCommands startSignUp() {
        System.out.println();
        System.out.println("Sign Up");
        EntryPageCommands.showCommands(EntryPageCommands.QUIT, EntryPageCommands.LOGIN);

        while (true) {
            System.out.print("Enter Username: ");
            String username = scanner.next();

            if (EntryPageCommands.isCommand(username)) {
                return EntryPageCommands.getCommand(username);
            }
            if (users.hasUser(username) || username.isBlank()) {
                System.out.println("Username taken. Try another!");
                continue;
            }
            while (true) {
                System.out.print("Enter Password: ");
                String password = scanner.next();

                if (EntryPageCommands.isCommand(password)) {
                    return EntryPageCommands.getCommand(password);
                }
                if (!password.isBlank()) {
                    users.addUser(username, password);
                    return EntryPageCommands.LOGIN;
                }
                System.out.println("Please enter a valid password!");
            }
        }
    }
}
