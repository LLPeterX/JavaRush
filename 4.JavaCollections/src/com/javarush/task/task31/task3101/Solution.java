package com.javarush.task.task31.task3101;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
Проход по дереву файлов

1. На вход метода main() подаются два параметра.
 Первый - path - путь к директории,
 второй - resultFileAbsolutePath - имя (полный путь) существующего файла, который будет содержать результат.
2. Переименовать resultFileAbsolutePath в allFilesContent.txt (используй метод FileUtils.renameFile(), и,
   если понадобится, FileUtils.isExist()).
3. Для каждого файла в директории path и в ее всех вложенных поддиректориях выполнить следующее:
Если у файла длина в байтах НЕ больше 50, то для всех таких файлов:
3.1. Отсортировать их по имени файла в возрастающем порядке, путь не учитывать при сортировке.
3.2. В allFilesContent.txt последовательно записать содержимое всех файлов из п. 3.1. После каждого тела файла записать "\n".
Все файлы имеют расширение txt.
В качестве разделителя пути используй "/".
Для создания файлов используй конструктор File(String pathname).


Требования:
1. Файл, который приходит вторым параметром в main, должен быть переименован в allFilesContent.txt.
2. Нужно создать поток для записи в переименованный файл.
3. Содержимое всех файлов, размер которых не превышает 50 байт, должно быть записано в файл allFilesContent.txt в отсортированном по имени файла порядке.
4. Поток для записи в файл нужно закрыть.
5. Не используй статические переменные.
*/
public class Solution {

    public static void main(String[] args) {
        File path = new File(args[0]); // путь к директории
        File resultFileAbsolutePath= new File(args[1]); // полный путь к имени файла, куда сохранить результат
          //File path = new File("c:/temp");
          //File resultFileAbsolutePath = new File("c:/temp/foo.txt");
        File newFile = new File(resultFileAbsolutePath.getParent()+"/allFilesContent.txt");
        //FileUtils.renameFile(resultFileAbsolutePath, new File(resultFileAbsolutePath.getParent() + "/allFilesContent.txt"));
        //System.out.println("Try to rename "+resultFileAbsolutePath.getAbsolutePath()+" to "+newFile.getAbsolutePath());
        if(FileUtils.isExist(resultFileAbsolutePath)) {
            //System.out.println("Delete "+resultFileAbsolutePath.getAbsolutePath());
            FileUtils.deleteFile(resultFileAbsolutePath);
        }
        FileUtils.renameFile(resultFileAbsolutePath, newFile);
        resultFileAbsolutePath = newFile;
        //3. Для каждого файла в директории path и в ее всех вложенных поддиректориях выполнить следующее
        List<File> list = new ArrayList<>();
        listFiles(path,list);
        // сортируем
        Collections.sort(list, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        // выводим в файл
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(resultFileAbsolutePath))) {
            for(File f : list) {
                //writer.write(f.getAbsolutePath().replaceAll("\\\\","/")+"\n");
                // надо писать содерджимое!!!!
                System.out.println("write "+f.getAbsolutePath());
                try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
                    String str;
                    while((str = reader.readLine())!=null)
                        writer.write(str);
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // получить список файлов в каталоге path и всех подкаталогах
    public static void listFiles(File path, List<File> list) {

        // Перебираем все папки и файлы начиная со стартового пути
        for (File file : path.listFiles()) {
            //если это каталог
            if (file.isDirectory())  {
                //если не пуст, то вызываем этот метод повторно
                if (file.listFiles().length != 0)
                    listFiles(file,list);

                //если пуст - удаляем

            } else { // обычный файл. Проверяем длину
                if (file.length() <= 50 && file.getName().toLowerCase().endsWith(".txt"))
                    list.add(file);
                }
            }
        }



}
