package com.javarush.task.task21.task2109;

import java.util.Objects;

/*
Запретить клонирование
*/
public class Solution {
    public static class A implements Cloneable {
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            A a = (A) o;
            return i == a.i &&
                    j == a.j;
        }

        @Override
        public int hashCode() {
            return Objects.hash(i, j);
        }

        private int i;
        private int j;

        public A(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }
    }

    public static class B extends A {
        private String name;

        public B(int i, int j, String name) {
            super(i, j);
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public Object clone() throws CloneNotSupportedException {
            throw new CloneNotSupportedException();
        }
    }

    public static class C extends B implements Cloneable { // нечего клонировать. Но вызывеет super()
        public C(int i, int j, String name) {
            super(i, j, name);
        }

        public C clone() {
            C c = new C(this.getI(), this.getJ(), this.getName());
            return c;
        }
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        C c1 = new C(1,2,"Peter");
        C c2 = c1.clone();
        System.out.println(c1.equals(c2));
    }
}
