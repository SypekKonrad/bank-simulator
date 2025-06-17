package utils;

import exceptions.DataSaveException;
import exceptions.DataLoadException;
import models.*;

import java.io.*;

public class DataManager {

//    private static final String filename = "user_account.txt";
//
//    public static void saveDataAsText(BankData data) throws IOException {
//        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
//            writer.println("=== USERS ===");
//            for (User user : data.getUsers()) {
//                writer.println("User: " + user.getLogin());
//                writer.println("Email: " + user.getEmail());
//                writer.println("Password: " + user.getPassword());
//                writer.println("---");
//            }
//
//            writer.println("\n=== ACCOUNTS ===");
//            for (Account account : data.getAccounts()) {
//                writer.println("Account: " + account.getAccountNumber());
//                writer.println("Balance: " + account.getBalance());
//                writer.println("Owner: " + account.getOwner().getLogin());
//                writer.println("---");
//            }
//        }
//    }
//
//    public static void readDataFromText() throws IOException {
//        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
//            }
//        }
//    }


    private static final String FILENAME = "user_account.dat";

    public static void saveData(BankData data) throws DataSaveException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            oos.writeObject(data);
        } catch (IOException e) {
            throw new DataSaveException("Error saving data to file: " + FILENAME, e);
        }
    }

    public static BankData loadData() throws DataLoadException {
        File file = new File(FILENAME);
        if (!file.exists()) {
            System.out.println("1");
            return new BankData();
        } else {
            System.out.println("nie ma");

        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (BankData) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DataLoadException("Error loading data from file: " + FILENAME, e);
        }
    }
}
