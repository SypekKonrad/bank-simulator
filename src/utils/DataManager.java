package utils;

import exceptions.DataSaveException;
import exceptions.DataLoadException;
import models.BankData;

import java.io.*;

public class DataManager {
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
            return new BankData();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (BankData) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DataLoadException("Error loading data from file: " + FILENAME, e);
        }
    }
}
