package com.javarush.task.task29.task2904;

/* 
Особенности автобоксинга
Исправь ошибку в методе getValueByIndex().
Читай доп. статью про особенности автобоксинга.


*/
public class Solution {
    private Integer[] array = new Integer[]{1, 2, 3, 4};

    public static void main(String[] args) {
        Number value1 = new Solution().getValueByIndex(5); //-1.0, class java.lang.Double expected
        Number value2 = new Solution().getValueByIndex(2); //3, class java.lang.Integer expected

        System.out.println(value1 + ", " + value1.getClass().toString());
        System.out.println(value2 + ", " + value2.getClass().toString());
    }

    Number getValueByIndex(int index) {
        // тут был тернарный оператор - а он принимает значеия только одного типа (т.е. double)
        if(index >= 0 && index < array.length)
            return new Integer(array[index]);
        else return new Double(-1);
    }
}
