package com.example.groupassigment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AVLTreeOperations {

    private static String absolutePath = new File("").getAbsolutePath();
    private static String ANUpath = absolutePath + "/app/src/main/java/ANU.json";
    private static String Fieldpath = absolutePath + "/app/src/main/java/Field_code.json";
    private static String collagepath = absolutePath + "/app/src/main/java/Fied_collage.json";
    public static AVLTree.Node search(String jsonFile, int searchKey) throws IOException {
        // Read JSON from file
        String json = new String(Files.readAllBytes(Paths.get(jsonFile)));

        // Deserialize JSON into AVLTree object
        Gson gson = new Gson();
        AVLTree avlTree = gson.fromJson(json, AVLTree.class);

        // Search the tree using the key
        return avlTree.search(avlTree.root, searchKey);
    }

    public static boolean updatStu(String jsonFile, int searchKey, String student) throws IOException {
        String json = new String(Files.readAllBytes(Paths.get(jsonFile)));

        // Deserialize JSON into AVLTree object
        Gson gson = new Gson();
        AVLTree avlTree = gson.fromJson(json, AVLTree.class);

        // Update the tree using the key
        boolean updated = avlTree.update_student(avlTree.root, searchKey, student);

        if (updated) {
            // Serialize the updated AVL tree to JSON
            Gson gson2 = new GsonBuilder().setPrettyPrinting().create();
            String updatedJson = gson2.toJson(avlTree);

            // Write the updated JSON to file
            Files.write(Paths.get(jsonFile), updatedJson.getBytes());
        }


        return updated;
    }

    public static boolean updateFil(String jsonFile, int searchKey, String file) throws IOException {
        String json = new String(Files.readAllBytes(Paths.get(jsonFile)));

        // Deserialize JSON into AVLTree object
        Gson gson = new Gson();
        AVLTree avlTree = gson.fromJson(json, AVLTree.class);

        // Update the tree using the key
        boolean updated = avlTree.update_file(avlTree.root, searchKey, file);

        if (updated) {
            // Serialize the updated AVL tree to JSON
            Gson gson2 = new GsonBuilder().setPrettyPrinting().create();
            String updatedJson = gson2.toJson(avlTree);

            // Write the updated JSON to file
            Files.write(Paths.get(jsonFile), updatedJson.getBytes());
        }


        return updated;
    }

    public static void main(String[] args) throws IOException {

        String jsonFile = ANUpath;
        System.out.println(ANUpath);
        int searchKey = -1891117552;
        AVLTree.Node node = search(jsonFile, searchKey);

        // Print the result
        if (node != null) {
            System.out.println("Key: " + node.value);
            System.out.println("Data: " + node.A);
            System.out.println("Number: " + node.NUMBER);
        } else {
            System.out.println("Key not found.");
        }

        String student = "u7416619";
        boolean updated = updatStu(jsonFile, searchKey, student);

        if (updated) {
            System.out.println("Node updated successfully.");
        } else {
            System.out.println("Node with the given key not found.");
        }


        String file = "https://firebasestorage.googleapis.com/v0/b/silken-sled-356305.appspot.com/o/%E5%B7%A5%E4%BD%9C%E5%AE%A4MOD%E7%BB%84%EF%BC%88AI-HS2%EF%BC%89.txt?alt=media&token=2bf4ea93-6c0c-4ddb-90d6-60d022153d55";
        boolean updated2 = updateFil(jsonFile, searchKey, file);

        if (updated2) {
            System.out.println("Node updated successfully.");
        } else {
            System.out.println("Node with the given key not found.");
        }
    }
}