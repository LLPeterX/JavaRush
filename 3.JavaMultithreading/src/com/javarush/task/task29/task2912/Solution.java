package com.javarush.task.task29.task2912;

/* 
Рефакторинг паттерна Chain of Responsibility
Есть программа, в которой реализована система логирования по принципу:
- если событие уровня FATAL - происходит звонок директору, отправляется СМС-сообщение CEO, выводится сообщение в консоль, происходит логирование в файл;
- если событие уровня ERROR - отправляется СМС-сообщение CEO, выводится сообщение в консоль, происходит логирование в файл;
- если событие уровня WARN - выводится сообщение в консоль, происходит логирование в файл;
- если событие уровня INFO - происходит логирование в файл.

В программе реализован паттерн "цепочка ответственности". Изучи его внимательно. В классах FileLogger, ConsoleLogger, SmsLogger, PhoneLogger
есть много повторяющегося кода. Подними весь повторяющийся код в абстрактный класс AbstractLogger.
Подъемом в рефакторинге называется перенос полей, методов, конструкторов из всех наследников в одного общего предка. Из наследников, при этом, удаляется код, который перенесен в класс предка.

Логика работы программы не должна измениться.


Требования:
1. Класс AbstractLogger должен быть публичным и абстрактным.
2. Класс AbstractLogger должен поддерживать интерфейс Logger.
3. Необходимо поднять поля level и next в класс AbstractLogger.
4. Необходимо поднять методы setNext(Logger) и inform(String, int) в класс AbstractLogger.
5. Вывод программы должен остаться неизменным.
*/

public class Solution {
    public static void main(String[] args) {
        // создаем логгеры
        Logger logger3 = new PhoneLogger(Level.FATAL);
        Logger logger2 = new SmsLogger(Level.ERROR);
        Logger logger1 = new ConsoleLogger(Level.WARN);
        Logger logger0 = new FileLogger(Level.INFO);
        // созлдаем цепочку логгеров. Если уровень >= заданного, пишет в разные потоки (console,sms,file. phone)
        logger3.setNext(logger2); // phone; FATAL - phone, sms(error); от error - см.ниже
        logger2.setNext(logger1); // sms; ERROR: sms и console(warn); warn - ниже
        logger1.setNext(logger0); // файл; WARN: console, file; для INFO только file

        // далее лог идет через начало ццкепочки = т.к. макс.уровня опасности (FATAL)
        logger3.inform("Everything is OK", Level.INFO); // только файл
        logger3.inform("We found a bug", Level.WARN); // warn - log1(console) и log0(file)
        logger3.inform("Database connection error", Level.ERROR); // error - log2(sms), log1(cons), log0(file)
        logger3.inform("System shut down", Level.FATAL); // fatal - через всю цепочку log3(phone), log2(sms), log1(cons), log0(file)
    }
}