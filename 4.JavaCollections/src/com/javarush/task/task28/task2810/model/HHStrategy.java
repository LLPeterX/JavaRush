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
        String userAgent = "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:69.0) Gecko/20100101 Firefox/69.0";
        String referer = "https://hh.ua/search/vacancy?text=java+%D0%BA%D0%B8%D0%B5%D0%B2";
        Document doc = null;
        try {
            //doc = Jsoup.connect(url).get();
            doc = Jsoup.connect(url).userAgent(userAgent).referrer(referer).get();
            //System.out.println(doc.html());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_LIST;

    }
}
