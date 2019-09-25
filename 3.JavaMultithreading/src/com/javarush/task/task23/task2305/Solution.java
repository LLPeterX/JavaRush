package com.javarush.task.task23.task2305;

/* 
Inner
*/
public class Solution {
    public InnerClass[] innerClasses = new InnerClass[2];

    public class InnerClass {
    }

    public static Solution[] getTwoSolutions() {
        Solution sol1 = new Solution();
        sol1.innerClasses[0] = sol1.new InnerClass();
        sol1.innerClasses[1] = sol1.new InnerClass();

        Solution sol2 = new Solution();
        sol2.innerClasses[0] = sol2.new InnerClass();
        sol2.innerClasses[1] = sol2.new InnerClass();

        Solution[] result = {sol1, sol2};
        return result;



//        result[0].innerClasses[0] = result[0].new InnerClass();
//        result[0].innerClasses[1] = result[0].new InnerClass();
//        result[1].innerClasses[0] =  result[1].new InnerClass();
//        result[1].innerClasses[1] =  result[1].new InnerClass();
        //return result;
    }

    public static void main(String[] args) {

    }
}
