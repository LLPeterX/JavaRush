package com.javarush.task.task31.task3113;

import java.io.BufferedReader; //x
import java.io.IOException; //x
import java.io.InputStreamReader; //x
import java.nio.file.*; //x
import java.nio.file.attribute.BasicFileAttributes; //x

/* 
Что внутри папки?

Напиши программу, которая будет считать подробную информацию о папке и выводить ее на консоль.

Первым делом считай путь к папке с консоли.
Если введенный путь не является директорией - выведи "[полный путь] - не папка" и заверши работу.
Затем посчитай и выведи следующую информацию:

Всего папок - [количество папок в директории и поддиректориях]
Всего файлов - [количество файлов в директории и поддиректориях]
Общий размер - [общее количество байт, которое хранится в директории]

Используй только классы и методы из пакета java.nio.

Квадратные скобки [ ] выводить на экран не нужно.


Требования:
1. Метод main должен считывать путь к папке с консоли.
2. Если введенный путь не является директорией - нужно вывести "[полный путь] - не папка" и завершить работу.
3. Используй только классы и методы из пакета java.nio.
4. На консоль должна быть выведена следующая информация: "Всего папок - [количество папок в директории и поддиректориях]".
5. На консоль должна быть выведена следующая информация: "Всего файлов - [количество файлов в директории и поддиректориях]".
6. На консоль должна быть выведена следующая информация: "Общий размер - [общее количество байт, которое хранится в директории]"
*/
public class Solution {
    private static int folders = 0; // кол-во папок
    private static int files = 0; // кол-во файлов
    private static long totalSize = 0; // общий размер байт, хранящихся в директории


    public static void main(String[] args) throws IOException {
        // читаем путь к папке
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        Path path = Paths.get(console.readLine());
        console.close();
        // если НЕ каталог - выбросить исключение
        if (!Files.isDirectory(path)) {
            System.out.println(path.toAbsolutePath() + " - не папка");
            return;
        }
        //System.out.println("Processing " + path.toAbsolutePath().toString());
        Files.walkFileTree(path,new MyVisitor()); // не считает каталоги! сука!
        // вывод результатов
        // Всего папок - [количество папок в директории и поддиректориях]
        //Всего файлов - [количество файлов в директории и поддиректориях]
        //Общий размер - [общее количество байт, которое хранится в директории]
        System.out.println("Всего папок - "+(folders-1)); // минус начальный каталог
        System.out.println("Всего файлов - "+files);
        System.out.println("Общий размер - "+totalSize);
    } // main

    // внутр. класс в Solution -
    private static class MyVisitor extends SimpleFileVisitor<Path> {
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                files++;
                // плюс размер файлов
                //totalSize +=file.toFile().length(); // не работает!!!!
            totalSize += attrs.size();
            return FileVisitResult.CONTINUE;
        }

        // какой-то косяк
        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            //return super.visitFileFailed(file, exc);
            return FileVisitResult.CONTINUE;
        }

        // для подсчета каталогов - postVisitDirectory() не работает, надо preVisitDirectory() ! - херня!!!
//        @Override
//        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
//            //return super.postVisitDirectory(dir, exc);
//            folders++;
//            return  FileVisitResult.CONTINUE;
//
//        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            folders++;
            return  FileVisitResult.CONTINUE;
//            return super.preVisitDirectory(dir, attrs);
        }
    }

}
