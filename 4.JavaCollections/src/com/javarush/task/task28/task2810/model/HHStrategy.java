package com.javarush.task.task28.task2810.model;

// Этот класс будет реализовывать конкретную стратегию работы с сайтом ХэдХантер (http://hh.ua/ и http://hh.ru/).
public class HHStrategy implements Strategy {
    // URL: https://krasnodar.hh.ru/search/vacancy?area=115&clusters=true&enable_snippets=true&text=java&page=4
    // или
    // URL: https://krasnodar.hh.ru/search/vacancy?area=115&st=searchVacancy&text=java+%D0%9A%D0%B8%D0%B5%D0%B2

    // URL_FORMAT должна быть static!!
    final static private String URL_FORMAT = "http://hh.ua/search/vacancy?text=java+%s&page=%d";

}
