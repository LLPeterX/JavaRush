package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/* 
Находим все файлы
Реализовать логику метода getFileTree, который должен в директории root найти список всех файлов включая вложенные.
Используй очередь, рекурсию не используй.
Верни список всех путей к найденным файлам, путь к директориям возвращать не надо.
Путь должен быть абсолютный.

Требования:
1. Метод getFileTree должен принимать аргументом String root, по которому нужно найти все вложенные файлы.
2. Метод getFileTree должен возвращать список строк.
3. Нужно реализовать метод getFileTree: найти все файлы по указанному пути и добавить их в список.
4. Метод getFileTree должен быть вызван только 1 раз (рекурсию не использовать).


*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        // очередь: помещаем каталог в очередь, а потом проходимся по нему
        Queue<File> queue = new LinkedList<>(); // очередь для каталогов
        List<String> allFilesPath = new ArrayList<>(); // сам список полных путей к файлам
        Queue<File> dirs = new LinkedList<File>(); // тут только каталоги
        dirs.add(new File(root)); // добвляем сначала корневой каталог в виде File
        while (!dirs.isEmpty()) { // если каталог непустой, то читаем файлы
            for (File f : dirs.poll().listFiles()) { // вытаскиваем из очереди каталог и получаем список его файлов (add был выше)
                if (f.isDirectory()) { // еще квлож.каталог - добавляем его в очередь (в писок файлов добавлять не надо)
                    dirs.add(f);
                } else if (f.isFile()) { // ели файл - добавляем в список файлов
                    //allFiles.add(f);
                    allFilesPath.add(f.getAbsolutePath());
                }
            }
        }
        return allFilesPath;
    }

    public static void main(String[] args) throws IOException {
        String root = "c:/temp";
        List<String> files = getFileTree(root);
        for(String s: files)
            System.out.println(s);
    }
}
