package com.javarush.task.task32.task3211;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;

/* 
Целостность информации

Представь ситуацию, ты выкачал архив с интернета, и в момент скачивания у тебя на несколько секунд пропало соединение.
Нужно убедиться, валидный ли архив ты скачал. Для этого сверим его MD5 хеш.
Прочитать о MD5 на wiki.
Прочитать о MessageDigest.

Реализуй логику метода compareMD5, который должен получать MD5 хеш из переданного ByteArrayOutputStream и сравнивать с эталонным MD5 переданным вторым параметром.
Метод main не участвует в тестировании.


Требования:
1. Класс Solution должен содержать метод compareMD5(ByteArrayOutputStream byteArrayOutputStream, String md5).
2. Метод compareMD5(ByteArrayOutputStream byteArrayOutputStream, String md5) должен использовать MessageDigest.
3. Метод compareMD5(ByteArrayOutputStream byteArrayOutputStream, String md5) должен возвращать
   результат сравнения вычисленного MD5 хеша для byteArrayOutputStream с переданным параметром md5.
4. Класс Solution должен содержать метод main.
*/

public class Solution {
    public static void main(String... args) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(new String("test string")); // пишем обьект строка в ByteArrayOutputStream
        oos.flush();
        System.out.println(compareMD5(bos, "5a47d12a2e3f9fecf2d9ba1fd98152eb")); //true

    }

    public static boolean compareMD5(ByteArrayOutputStream byteArrayOutputStream, String md5) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        digest.reset();
        // читаем из байтового потока в массив
        byte[] bytes = byteArrayOutputStream.toByteArray();
        byte[] md5Bytes = digest.digest(bytes); // получили MD5 в виде массива байтов
        // мы не можем декодировать md5; поэтому кодируем байтовый массив в MD5 и сравниваем хеши
        // формируем строку md5 из hex-значений байтов
        StringBuilder sb = new StringBuilder();
        for(byte b : md5Bytes) {
            sb.append(String.format("%02x",b));
        }
//        System.out.println("SRC: "+md5);
//        System.out.println("DST: "+sb.toString());
// можно короче: DatatypeConverter.printHexBinary(messageDigest.digest()); - сразу засчитало.
        return md5.equals(sb.toString());
    }
}
