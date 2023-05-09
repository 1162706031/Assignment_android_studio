package com.example.groupassigment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AVLTreeOperations {

    public static String absolutePath = new File("").getAbsolutePath();
    public static String ANUpath = absolutePath + "/app/src/main/java/ANU.json";
    public static String Fieldpath = absolutePath + "/app/src/main/java/Field_code.json";
    public static String collagepath = absolutePath + "/app/src/main/java/Fied_collage.json";
    public static AVLTree.Node search(String jsonFile, int searchKey) throws IOException {
        // Read JSON from file
        String json = new String(Files.readAllBytes(Paths.get(jsonFile)));

        // Deserialize JSON into AVLTree object
        Gson gson = new Gson();
        AVLTree avlTree = gson.fromJson(json, AVLTree.class);

        // Search the tree using the key
        return avlTree.search(avlTree.root, searchKey);
    }

    public static boolean updatStu(AVLTree tree, int searchKey, String student) throws IOException {

        boolean updated = tree.update_student(tree.root, searchKey, student);

        if (updated) {
            // Serialize the updated AVL tree to JSON
            Gson gson2 = new GsonBuilder().setPrettyPrinting().create();
            String updatedJson = gson2.toJson(tree);

            // Write the updated JSON to file
            Files.write(Paths.get(ANUpath), updatedJson.getBytes());
        }

        return updated;
    }

    public static boolean updateFil(AVLTree tree, int searchKey, String file) throws IOException {



        // Update the tree using the key
        boolean updated = tree.update_file(tree.getRoot(), searchKey, file);

        if (updated) {
            // Serialize the updated AVL tree to JSON
            Gson gson2 = new GsonBuilder().setPrettyPrinting().create();
            String updatedJson = gson2.toJson(tree);

            // Write the updated JSON to file
            Files.write(Paths.get(ANUpath), updatedJson.getBytes());
        }


        return updated;
    }


    public static AVLTree CreatTree(String jsonFile) throws IOException {
        // Read JSON from file
        String json = new String(Files.readAllBytes(Paths.get(jsonFile)));

        // Deserialize JSON into AVLTree object
        Gson gson = new Gson();
        AVLTree avlTree = gson.fromJson(json, AVLTree.class);

        // Return the root of the tree
        return avlTree;
    }


}