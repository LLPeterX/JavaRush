package com.javarush.task.task06.task0621;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Родственные связи кошек
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Дедушка(1): ");
        Cat ded = new Cat(reader.readLine(),null,null,'m');

        System.out.print("Бабушка(2): ");
        Cat bab = new Cat(reader.readLine(),null,null,'f');

        System.out.print("Папа(3): ");
        Cat dad = new Cat(reader.readLine(),ded,null,'m');

        System.out.print("Мама(4): ");
        Cat mom = new Cat(reader.readLine(),null,bab,'f');

        System.out.print("Доча(5): ");
        //Cat dau = new Cat(reader.readLine(),dad,mom, ded, bab,'f');
        Cat dau = new Cat(reader.readLine(),dad,mom,'f');


        System.out.print("Сын(6): ");
        //Cat son = new Cat(reader.readLine(),dad,mom,ded,bab,'m');
        Cat son = new Cat(reader.readLine(),dad,mom,'m');

        System.out.println(ded);
        System.out.println(bab);
        System.out.println(dad);
        System.out.println(mom);
        System.out.println(son);
        System.out.println(dau);


//        String motherName = reader.readLine();
//        Cat catMother = new Cat(motherName);
//
//        String daughterName = reader.readLine();
//        Cat catDaughter = new Cat(daughterName, catMother);

        //System.out.println(catMother);
        //System.out.println(catDaughter);
    }

    public static class Cat {
        private String name;
        private Cat parentMom, parentDad;
        //private Cat parentDedusjka, parentBabushka;
        private char sex; // m/f

        Cat(String name) {
            this.name = name;
        }

        Cat(String name, Cat parentDad, Cat parentMom, char sex) {
            this.name = name;
            this.parentMom = parentMom;
            this.parentDad = parentDad;
            this.sex = sex;
        }
//        Cat(String name, Cat dad, Cat mom, Cat ded, Cat bab, char sex) {
//            this(name, dad, mom, sex);
//            this.parentDedusjka=ded;
//            this.parentBabushka=bab;
//        }
        //Cat(String name, Cat parentDad, Cat parentMom, Cat parentDedusjka, Cat parentBabushka, char sex) {
//            this(name, parentDad, parentMom,sex);
//            this.parentBabushka=parentBabushka;
//            this.parentBabushka = parentBabushka;
//        }

        @Override
        public String toString() {
//            if (parent == null)
//                return "The cat's name is " + name + ", no mother ";
//            else
//                return "The cat's name is " + name + ", mother is " + parent.name;
//        }

            String s = "The cat's name is " + name;
            if(parentDad != null)  s=s+", father is "+parentDad.name; else s+=", no father";
            if(parentMom != null)  s=s+", mother is "+parentMom.name; else s+=", no mother";
            return s;
        }
    }

}
