package app;

import app.user.Users;
import app.ui.TextUI;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Users users = new Users();
        TextUI application = new TextUI(scanner, users);

        application.start();
    }
}
