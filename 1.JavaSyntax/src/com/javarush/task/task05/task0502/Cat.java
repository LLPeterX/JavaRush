package com.javarush.task.task05.task0502;

/* 
Реализовать метод fight
*/

public class Cat {
    public int age;
    public int weight;
    public int strength;

    public Cat() {
    }

    public boolean fight(Cat anotherCat) {
        //напишите тут ваш код
//        int ageAdvantage = this.age > anotherCat.age ? 1 : 0;
//        int weightAdvantage = this.weight > anotherCat.weight ? 1 : 0;
//        int strengthAdvantage = this.strength > anotherCat.strength ? 1 : 0;
//
//        int score = ageAdvantage + weightAdvantage + strengthAdvantage;
//        return score > 2;
        int wAge = this.age > anotherCat.age ? 1 : 0;
        int wWei = this.weight > anotherCat.weight ? 1 : 0;
        int wStr = this.strength > anotherCat.strength ? 1 : 0;
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
        //return (wAge+wWei+wStr)>=2;

    }

    public static void main(String[] args) {

    }
}