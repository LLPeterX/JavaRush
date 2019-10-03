package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

// Этот класс будет реализовывать конкретную стратегию работы с сайтом ХэдХантер (http://hh.ua/ и http://hh.ru/).
public class HHStrategy implements Strategy {

    final static private String URL_FORMAT = "http://hh.ua/search/vacancy?text=java+%s&page=%d";
    //final static private String URL_FORMAT = "http://hh.ua/search/vacancy?text=java&page=1";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        String url = String.format(URL_FORMAT,"Киев",1);
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
            //System.out.println(doc.html());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_LIST;

    }
}
