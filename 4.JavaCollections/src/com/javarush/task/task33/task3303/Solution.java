package com.javarush.task.task33.task3303;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/* 
Десериализация JSON объекта
В метод convertFromJsonToNormal первым параметром приходит имя файла, который содержит один ДЖЕЙСОН объект.
Вторым параметром приходит имя класса, объект которого находится в файле.
Метод convertFromJsonToNormal должен вычитать объект из файла, преобразовать его из JSON и вернуть.


Требования:
1. В методе convertFromJsonToNormal должен быть создан объект типа ObjectMapper с помощью конструктора без параметров.
2. Объект возвращаемый методом convertFromJsonToNormal должен быть получен с помощью метода readValue класса ObjectMapper.
3. Метод convertFromJsonToNormal должен быть статическим.
4. Метод convertFromJsonToNormal должен быть публичным.
*/
public class Solution {
    public static <T> T convertFromJsonToNormal(String fileName, Class<T> clazz) throws IOException {
        T result = null;
        ObjectMapper mapper = new ObjectMapper();
        result = mapper.readValue(new FileReader(new File(fileName)), clazz);
        return result;
    }

    public static void main(String[] args) {
        String filename = args[0]; // имя файла с json
        String className = args[1]; // имя объекта
        //Object o =convertFromJsonToNormal(args[0]);



    }
}
