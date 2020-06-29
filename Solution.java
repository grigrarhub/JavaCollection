package com.javarush.task.task31.task3101;

import com.javarush.task.task31.task3101.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.*;
import java.util.*;
import java.util.PriorityQueue;
/*
Проход по дереву файлов
*/
public class Solution {



    public static void main(String[] args) {

         File file = new File(args[0]);
     File resultFileAbsolutePath = new File(args[1]);
     File newFile = new File(resultFileAbsolutePath.getParent() + "/" + "allFilesContent.txt");
     FileUtils.renameFile(resultFileAbsolutePath, newFile);


     ArrayList<File> result = new ArrayList<>();
        PriorityQueue<File> files = new PriorityQueue<>();

        files.add(file);
        while (files.size()!=0) {
            File current = files.poll() ;

            if (current.isDirectory()) {
                //File[] tmp = current.listFiles();
                for (File f : current.listFiles())
                    files.add(f);
            }
            if (current.isFile()&& current.length()<=50)
                result.add(current);
        }



   result.sort(new Comparator<File>() {
                   @Override
                   public int compare(File first, File second) {
                       return first.getName().compareTo(second.getName());
                   }
               });

        try (FileWriter bufferedWriter = new FileWriter(resultFileAbsolutePath)) {
    for (File paths : result) {
        BufferedReader br = new BufferedReader(new FileReader(paths));
        String line = "";
        while (br.ready()) {
            bufferedWriter.write(br.readLine());
        }
        bufferedWriter.write("\n");
        br.close();
    }

} catch (IOException e) {
            e.printStackTrace();
        }


    }
}