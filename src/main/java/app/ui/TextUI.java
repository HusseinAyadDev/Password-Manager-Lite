package app.ui;

import app.data.Users;

import java.util.Scanner;

public class TextUI {

    private final Scanner scanner;
    private final Users users;
    private final UserLoginPage loginPage;
    private final UserSignUpPage signUpPage;

    public TextUI(Scanner scanner, Users users) {
        this.scanner = scanner;
        this.users = users;
        this.loginPage = new UserLoginPage(scanner, users);
        this.signUpPage = new UserSignUpPage(scanner, users);
    }

    public void start() {
        showCommands();
        String userInput = scanner.next();

        while (!userInput.equalsIgnoreCase(Commands.QUIT.getCommand())) {

            if (!Commands.SIGNUP.isCommand(userInput)) {
                System.out.println();
                System.out.println("Invalid command!");
                showCommands();
                userInput = scanner.next();
            } else if (userInput.equalsIgnoreCase(Commands.SIGNUP.getCommand())) {
                userInput = signUpPage.start();
            } else if (userInput.equalsIgnoreCase(Commands.LOGIN.getCommand())) {
                userInput = loginPage.start();
            }
        }
    }

    private void showCommands() {
        System.out.printf("[%s] - Quit program.%n", Commands.QUIT.getCommand());
        System.out.printf("[%s] - Signup.%n", Commands.SIGNUP.getCommand());
        System.out.printf("[%s] - Login.%n", Commands.LOGIN.getCommand());
        System.out.print("Choose Action: ");
    }
}
