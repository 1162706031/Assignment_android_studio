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


}