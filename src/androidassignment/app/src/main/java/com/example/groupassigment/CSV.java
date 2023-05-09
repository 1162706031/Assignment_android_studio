package com.example.groupassigment;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.CSVWriterBuilder;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSV {
    public static class UserData {
        String userId;
        String name;
        List<String> courses;

        public UserData(String userId, String name, List<String> courses) {
            this.userId = userId;
            this.name = name;
            this.courses = courses;
        }
    }

    public static void addUser(UserData userData, String filePath) {
        UserData existingUserData = loadUserDataFromCSV(userData.userId, filePath);
        if (existingUserData == null) {
            try (FileWriter fileWriter = new FileWriter(filePath, true);
                 CSVWriter csvWriter = (CSVWriter) new CSVWriterBuilder(fileWriter).build()) {

                String[] record = {userData.userId, userData.name, String.join(";", userData.courses)};
                csvWriter.writeNext(record);
                csvWriter.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("User with ID: " + userData.userId + " already exists.");
        }
    }

    public static void addCourse(String userId, String newCourse) throws IOException {
        String filePath = "user_data.csv";
        List<String[]> records;
        boolean updated = false;

        try (FileReader fileReader = new FileReader(filePath);
             CSVReader csvReader = new CSVReaderBuilder(fileReader).build()) {
            records = csvReader.readAll();
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }

        for (String[] record : records) {
            if (record[0].equals(userId)) {
                List<String> courses = new ArrayList<>(Arrays.asList(record[2].split(";")));
                courses.add(newCourse);
                record[2] = String.join(";", courses);
                updated = true;
                break;
            }
        }

        if (updated) {
            try (FileWriter fileWriter = new FileWriter(filePath);
                 CSVWriter csvWriter = (CSVWriter) new CSVWriterBuilder(fileWriter).build()) {
                csvWriter.writeAll(records);
                csvWriter.flush();
            }
        }
    }

    public static UserData loadUserDataFromCSV(String userId, String filePath) {
        UserData userData = null;

        try (FileReader fileReader = new FileReader(filePath);
             CSVReader csvReader = new CSVReaderBuilder(fileReader).build()) {

            String[] record;
            while ((record = csvReader.readNext()) != null) {
                if (record[0].equals(userId)) {
                    String name = record[1];
                    List<String> courses = new ArrayList<>(Arrays.asList(record[2].split(";")));
                    userData = new UserData(userId, name, courses);
                    break;
                }
            }

        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        return userData;
    }

    public static void main(String[] args) throws IOException {
        String filePath = "user_data.csv";


        UserData loadedUserData = loadUserDataFromCSV("u7416617", filePath);

        if (loadedUserData != null) {
            System.out.println("User ID: " + loadedUserData.userId);
            System.out.println("Name: " + loadedUserData.name);
            System.out.println("Courses: " + loadedUserData.courses);

            addCourse(loadedUserData.userId, "MATH1077");
            UserData updatedUserData = loadUserDataFromCSV("u7416617", filePath);
        }
    }
}
