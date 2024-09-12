package app;

import app.ui.entry.EntryPage;
import app.user.Users;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Users users = new Users();
        EntryPage application = new EntryPage(scanner, users);

        application.start();
    }
}
