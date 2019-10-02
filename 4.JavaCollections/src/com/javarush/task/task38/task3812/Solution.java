package com.javarush.task.task38.task3812;

/* 
Обработка аннотаций
*/

public class Solution {
    public static void main(String[] args) {
        printFullyQualifiedNames(Solution.class);
        printFullyQualifiedNames(SomeTest.class);

        printValues(Solution.class);
        printValues(SomeTest.class);
    }
// В методы printFullyQualifiedNames() и printValues() приходит класс.
// Если этот класс отмечен аннотацией PrepareMyTest, необходимо вывести на экран fullyQualifiedNames()
// и values() (определены в PrepareMyTest) -  в соответствующих методах и вернуть true.
// Если же аннотация PrepareMyTest отсутствует - вернуть false.
// Для вывода на экран классов из массива value используй сокращенное имя (getSimpleName).
    public static boolean printFullyQualifiedNames(Class c) {
        if(c.isAnnotationPresent(PrepareMyTest.class)) {
            // вывести FQDN
            PrepareMyTest testFQN = (PrepareMyTest)c.getAnnotation(PrepareMyTest.class);
            for(String str : testFQN.fullyQualifiedNames()) {
                System.out.println(str);
            }
            return true;
        }
        return false;
    }

    public static boolean printValues(Class c) {
        if(c.isAnnotationPresent(PrepareMyTest.class)) {
            PrepareMyTest testValues = (PrepareMyTest)c.getAnnotation(PrepareMyTest.class);
            for(Class clazz : testValues.value()) {
                System.out.println(clazz.getSimpleName());
            }
            return true;
        }
        return false;
    }
}
