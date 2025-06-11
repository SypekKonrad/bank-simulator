 package utils;

 import exceptions.DataSaveException;
 import java.io.*;

 public class DataManager {

     public static void saveUserData(Object user, Object account, String filename) throws DataSaveException {
         try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
             oos.writeObject(user);
             oos.writeObject(account);
         } catch (IOException e) {
             throw new DataSaveException("Error saving data to file: " + filename, e);
         }
     }
 }