package com.javarush.task.task38.task3803;

/* 
Runtime исключения (unchecked exception)
*/

public class VeryComplexClass {
    public void methodThrowsClassCastException() {
        // должен выбрать ClassCastException
        Object intValue = Integer.valueOf(43);
        String s = (String)intValue;
    }

    public void methodThrowsNullPointerException() {
        // должно выбросить NullPointerException
        String dummy = null;
        dummy.charAt(0);
    }

    public static void main(String[] args) {
        //VeryComplexClass c = new VeryComplexClass();
        //c.methodThrowsNullPointerException();
        //c.methodThrowsClassCastException();
    }
}
