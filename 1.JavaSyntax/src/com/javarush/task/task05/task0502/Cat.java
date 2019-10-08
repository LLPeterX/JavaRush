package com.javarush.task.task05.task0502;

/* 
Реализовать метод fight
(НЕ ДОДЕЛАНО!)
*/

public class Cat {
    public int age;
    public int weight;
    public int strength;

    public Cat() {
    }

    public boolean fight(Cat anotherCat) {
        //напишите тут ваш код

//        int wAge = this.age > anotherCat.age ? 1 : 0;
//        int wWei = this.weight > anotherCat.weight ? 1 : 0;
//        int wStr = this.strength > anotherCat.strength ? 1 : 0;
        int cat1Force =0;
        int cat2Force = 0;
        /*
        if(this.age> anotherCat.age)
            if(this.weight>anotherCat.weight || this.strength>anotherCat.strength)
                return true;
        if(this.weight>anotherCat.weight)
            if(this.age> anotherCat.age || this.strength>anotherCat.strength)
                return true;
        if(this.strength>anotherCat.strength)
            if(this.age> anotherCat.age || this.weight>anotherCat.weight)
                return true;

        return false;
         */
        if(this.age> anotherCat.age) cat1Force++; else cat2Force++;
        if(this.weight>anotherCat.weight) cat1Force++; else cat2Force++;
        if(this.strength>anotherCat.strength) cat1Force++; else cat2Force++;
        return cat1Force>cat2Force;
    }

    public static void main(String[] args) {

    }
}
