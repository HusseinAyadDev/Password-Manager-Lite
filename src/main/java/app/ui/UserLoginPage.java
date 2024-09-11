package app.ui;

import app.user.Users;

import java.util.Scanner;

public class UserLoginPage {

    private final Scanner scanner;
    private final Users users;

    public UserLoginPage(Scanner scanner, Users users) {
        this.scanner = scanner;
        this.users = users;
    }

    public String start() {
        System.out.println();
        System.out.println("Login");
        System.out.println("To signup instead enter [" + Commands.SIGNUP.getCommand() + "]");
        System.out.println("To quit instead enter [" + Commands.QUIT.getCommand() + "]");

        while (true) {
            System.out.print("Enter Username: ");
            String username = scanner.next();

            if (Commands.SIGNUP.isCommand(username)) {
                return username;
            }

            System.out.print("Enter Password: ");
            String password = scanner.next();

            if (!users.userValidator(username, password)) {
                System.out.println();
                System.out.println("Wrong Username or Password.");
                System.out.println("Try Again, or try different command!");

                continue;
            }

            System.out.println("access granted");
            return Commands.QUIT.getCommand();
        }
    }
}
