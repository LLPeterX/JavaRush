package com.javarush.task.task34.task3411;

/* 
Ханойские башни
*/

public class Solution {
    public static void main(String[] args) {
        int numRings = 3;
        moveRing('A', 'B', 'C', numRings);
    }

    public static void moveRing(char a, char b, char c, int numRings) {
        //напишите тут ваш код
        // переносим с A на B; C - промежуточный стержень. Диски начально на А, по возрастанию
        // алгоритм:
        // - перенесем n – 1 дисков со стержня А на стержень C, используя B;
        // - перенесем диск со стержня А на стержень B; (последний)
        // - перенесем n – 1 дисков со стержня C на стержень B, используя А;
        if(numRings==0) return;
        // перемещаем диски с А в С, исапользуя посдедний как промежуточный
        moveRing(a,c,b,numRings-1);
        // печватаем "from A to B"
        System.out.printf("from %c to %c%n",a,b);
        moveRing(c,b,a,numRings-1);
    }
}