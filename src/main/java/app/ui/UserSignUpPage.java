package app.ui;

import app.user.Users;

import java.util.Scanner;

public class UserSignUpPage {

    private final Scanner scanner;
    private final Users users;

    public UserSignUpPage(Scanner scanner, Users users) {
        this.scanner = scanner;
        this.users = users;
    }

    public String start() {
        System.out.println();
        System.out.println("Sign Up");
        System.out.println("To login instead enter [" + Commands.LOGIN.getCommand() + "]");
        System.out.println("To quit instead enter [" + Commands.QUIT.getCommand() + "]");

        String username = askUsername();

        if (Commands.SIGNUP.isCommand(username)) {
            return username;
        }
        String password = askPassword();

        if (Commands.SIGNUP.isCommand(username)) {
            return username;
        }
        users.addUser(username, password);
        return Commands.LOGIN.getCommand();
    }

    private String askUsername() {
        while (true) {
            System.out.print("Enter Username: ");
            String username = scanner.next();

            if (!(users.hasUser(username) || username.isBlank())) {
                return username;
            }
            System.out.println("Username taken. Try another!");
        }
    }

    private String askPassword() {
        while (true) {
            System.out.print("Enter Password: ");
            String password = scanner.next();

            if (!password.isBlank()) {
                return password;
            }
            System.out.println("Please enter a valid password!");
        }
    }
}
