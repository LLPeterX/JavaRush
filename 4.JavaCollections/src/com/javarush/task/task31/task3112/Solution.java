package com.javarush.task.task31.task3112;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/* 
Загрузчик файлов

Метод должен создать объект URL и загрузить содержимое файла на локальный диск.
Выкачивай сначала во временную директорию, чтобы в случае неуспешной загрузки в твоей директории
не оставались недокачанные файлы.
Затем перемести файл в пользовательскую директорию. Имя для файла возьми из ссылки.
Используй только классы и методы из пакета java.nio.


*/
public class Solution {

    public static void main(String[] args) throws IOException {
        Path passwords = downloadFile("https://javarush.ru/testdata/secretPasswords.txt", Paths.get("D:/MyDownloads"));
        //Path passwords = downloadFile("https://javarush.ru/testdata/secretPasswords.txt", Paths.get("C:/temp"));

        for (String line : Files.readAllLines(passwords)) {
            System.out.println(line);
        }
    }

    // второй параметр - куда сохранить
    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException {
        // implement this method
        URL url = new URL(urlString);
        Path tempFile = Files.createTempFile("temp-",".tmp");
        InputStream in = url.openStream();
        //System.out.println("Try download into "+tempFile.getFileName().toString());
        Files.copy(in, tempFile, StandardCopyOption.REPLACE_EXISTING); // скачали во временный файл - FileAlreadyExistsException (?!)
        //String fileName = url.getFile();
        String fileName = new File(url.getFile()).getName();
        //System.out.println("TEMP: "+tempFile.getFileName().toString()+" FILENAME:"+fileName);
        // переместить tempFile в новый каталог с именем как в URL
        Path newPath = downloadDirectory.resolve(fileName);
        //System.out.println("New path: "+newPath.toString());
        Files.move(tempFile,newPath); // тут error т.к. newPath содержит путь
        return newPath;
    }
}
