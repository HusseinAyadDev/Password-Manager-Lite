package app.user;

import app.utils.MyCipher;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

public class User {

    private final String username;
    private final String password;
    private byte[] ivByte;
    private List<Account> accounts;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.ivByte = MyCipher.generateIvByte();
        this.accounts = new ArrayList<>();
    }

    @SuppressWarnings("unused")
    private User() {
        this(null, null);
        this.ivByte = null;
        this.accounts = null;
    }

    private static class Account {
        private final int accountID;
        private String accountName;
        private String accountUsername;
        private String accountPassword;

        private Account(int id, String name, String username, String password) {
            accountID = id;
            accountName = name;
            accountUsername = username;
            accountPassword = password;
        }

        @SuppressWarnings("unused")
        private Account() {
            this(0, null, null, null);
        }

        @SuppressWarnings("StringBufferReplaceableByString")
        @Override
        public String toString() {
            StringBuilder text = new StringBuilder();
            text.append("ID: ").append(accountID).append("\n");
            text.append("Account Name: ").append(accountName).append("\n");
            text.append("Username: ").append(accountUsername).append("\n");
            text.append("password: ").append(accountPassword).append("\n");

            return text.toString();
        }
    }

    public boolean hasAccounts() {
        return accounts.isEmpty();
    }

    public void addAccount(String name, String username, String password) {
        int id = accounts.size() + 1;
        accounts.add(new Account(id, name, username, password));
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean hasID(int id) {
        return id < accounts.size();
    }

    public void removeAccount(int id) {
        accounts.remove(id);
    }

    public void setAccountName(int id, String input) {
        accounts.get(id).accountName = input;
    }

    public void setAccountUsername(int id, String input) {
        accounts.get(id).accountUsername = input;
    }

    public void setAccountPassword(int id, String input) {
        accounts.get(id).accountPassword = input;
    }

    public void encrypt() {
        IvParameterSpec iv = MyCipher.generateIv(ivByte);

        try {
            SecretKey key = MyCipher.getKeyFromPassword(password, username);

            for (int id = 0; id < accounts.size(); id++) {
                Account acc = accounts.get(id);

                String encrypted = MyCipher.encrypt(acc.accountName, key, iv);
                setAccountName(id, encrypted);

                encrypted = MyCipher.encrypt(acc.accountUsername, key, iv);
                setAccountUsername(id, encrypted);

                encrypted = MyCipher.encrypt(acc.accountPassword, key, iv);
                setAccountPassword(id, encrypted);
            }
        } catch (InvalidKeySpecException | NoSuchAlgorithmException | NoSuchPaddingException |
                 IllegalBlockSizeException | BadPaddingException | InvalidKeyException |
                 InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        }
    }

    public void decrypt() {
        IvParameterSpec iv = MyCipher.generateIv(ivByte);

        try {
            SecretKey key = MyCipher.getKeyFromPassword(password, username);

            for (int id = 0; id < accounts.size(); id++) {
                Account acc = accounts.get(id);

                String encrypted = MyCipher.decrypt(acc.accountName, key, iv);
                setAccountName(id, encrypted);

                encrypted = MyCipher.decrypt(acc.accountUsername, key, iv);
                setAccountUsername(id, encrypted);

                encrypted = MyCipher.decrypt(acc.accountPassword, key, iv);
                setAccountPassword(id, encrypted);
            }
        } catch (InvalidKeySpecException | NoSuchAlgorithmException | NoSuchPaddingException |
                 IllegalBlockSizeException | BadPaddingException | InvalidKeyException |
                 InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        }
    }

    public void printAccounts() {
        if (accounts.isEmpty()) {
            System.out.println();
            System.out.println("No accounts exits!");
            return;
        }
        for (Account a : accounts) {
            System.out.println();
            System.out.println(a);
        }
    }
}
