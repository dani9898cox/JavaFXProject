package com.example.terogmergifx.utils;

import java.io.File;
import java.io.IOException;

/**
 * Functie care este utilizata pentru a gasi fiserul in care este conversatia dintre doi utilizatori,
 * returneaza in orice caz un fisier in care va fi sau va incepe sa fie conversatia doamne ajuta
 */
public class FindFile {
    public String findFile(String id1, String id2) throws IOException {
        String testFilePath1 = "mesaje_" + id1 + "_" + id2 + ".txt";
        String testFilePath2 = "mesaje_" + id2 + "_" + id1 + ".txt";

        File fisier1 = new File(testFilePath1);

        if (fisier1.exists())
            return testFilePath1;

        File fisier2 = new File(testFilePath2);

        if(fisier2.exists())
            return testFilePath2;

        fisier1.createNewFile();
        return testFilePath1;
    }
}
