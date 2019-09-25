package com.javarush.task.task31.task3111;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {
    // 1. В классе SearchFileVisitor нужно создать поля partOfName, partOfContent, minSize, maxSize и сеттеры для них.
    private String partOfName = null; // выражение, встречающееся в названии файла (String);
    private String partOfContent = null ; // выражение, встречающееся в содержимом файла (String);
    private int minSize =-1; // максимальный и минимальный размер файла (int).
    private int maxSize = -1 ;
    private List<Path> foundFiles= new ArrayList<>();

    public void setPartOfName(String partOfName) {
        this.partOfName = partOfName;
    }

    public void setPartOfContent(String partOfContent) {
        this.partOfContent = partOfContent;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public List<Path> getFoundFiles() {
        return foundFiles;
    }

    // Основная логика класса по поиску выполняется в методе visitFile(Path file, BasicFileAttributes attrs).
    // переопределяем метод из SimpleFileVisitor<Path>
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        boolean isFitWithFilter = true;

        if(partOfName!=null&&isFitWithFilter){
            isFitWithFilter = file.getFileName().toString().contains(partOfName);
        }
        if(partOfContent!=null&&isFitWithFilter){
            isFitWithFilter = Files.readAllLines(file).toString().contains(partOfContent);
        }
        if(minSize>=0&&isFitWithFilter){
            isFitWithFilter = attrs.size()>minSize;
        }
        if(maxSize>=0&&isFitWithFilter){
            isFitWithFilter = attrs.size()<maxSize;
        }

        if(isFitWithFilter) foundFiles.add(file);

        return FileVisitResult.CONTINUE;
    }
}
