package com.javarush.task.task32.task3209;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class HTMLFileFilter extends FileFilter {

    // Метод accept(File f) класса HTMLFileFilter должен возвращать true, если переданный файл - директория
    // или содержит в конце имени ".html" или ".htm" без учета регистра.
    @Override
    public boolean accept(File f) {
        return f.isDirectory() || f.getName().toLowerCase().endsWith(".htm") || f.getName().toLowerCase().endsWith(".html");
    }

    @Override
    public String getDescription() {
        return "HTML и HTM файлы";
    }
}
