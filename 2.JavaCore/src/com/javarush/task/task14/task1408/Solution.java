package com.javarush.task.task14.task1408;

/* 
Куриная фабрика
*/

public class Solution {
    public static void main(String[] args) {
        Hen hen = HenFactory.getHen(Country.BELARUS);
        //System.out.println(Country.BELARUS);
        hen.getCountOfEggsPerMonth();
        System.out.println(hen.getDescription());
    }

    static class HenFactory {

        static Hen getHen(String country) {
            Hen hen = null;
            //напишите тут ваш код
            if(country.equals("Belarus"))
                hen = new BelarusianHen();
            else if(country.equals("Moldova"))    
                hen = new MoldovanHen();
            else if(country.equals("Ukraine"))   
                hen = new UkrainianHen();
            else if(country.equals("Russia"))   
                hen = new RussianHen();
            return hen;
        }
    }
    



}
