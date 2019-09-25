package com.javarush.task.task32.task3201;

import java.io.IOException;
import java.io.RandomAccessFile;

/*
Запись в существующий файл
В метод main приходят три параметра:
1) fileName - путь к файлу;
2) number - число, позиция в файле;
3) text - текст.
Записать text в файл fileName начиная с позиции number.
Запись должна производиться поверх старых данных, содержащихся в файле.
Если файл слишком короткий, то записать в конец файла.
Используй RandomAccessFile и его методы seek и write.


Требования:
1. В методе main класса Solution необходимо использовать RandomAccessFile.
2. В методе main класса Solution программа должна записывать данные в файл при помощи метода write класса RandomAccessFile.
3. Запись в файл должна происходить с указанной позиции с заменой содержимого.
4. Если файл слишком короткий, то запись text должна происходить в конец файла.
*/
public class Solution {
    public static void main(String... args) throws IOException {
        // параметры:
        // 0) fileName - путь к файлу;
        // 1) number - число, позиция в файле;
        // 2) text - текст.
        String filename = args[0];
        long position = Long.parseLong(args[1]);
        String text = args[2];

        RandomAccessFile file = new RandomAccessFile(filename,"rw");
        if(file.length()<position) {
            file.seek(file.length());
        } else {
            file.seek(position);
        }
        file.write(text.getBytes());
        file.close();
    }
}
