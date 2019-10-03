package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Этот класс будет реализовывать конкретную стратегию работы с сайтом ХэдХантер (http://hh.ua/ и http://hh.ru/).
public class HHStrategy implements Strategy {

    private static final String URL_FORMAT = "http://hh.ua/search/vacancy?text=java+%s&page=%d";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        // для задачи Aggregator (8) код ниже не нужен
/*
        String url = String.format(URL_FORMAT,"Киев",1);
        String userAgent = "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:69.0) Gecko/20100101 Firefox/69.0";
        String referer = "https://hh.ua/search/vacancy?text=java+%D0%BA%D0%B8%D0%B5%D0%B2";
        Document doc = null;
        try {
            doc = Jsoup.connect(url).userAgent(userAgent).referrer(referer).get();
            System.out.println(doc.html());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_LIST;

 */
        int page = 0;
        List<Vacancy> vacancies = new ArrayList<>();
        Document doc = null;
        // цикл НАДО заключить в try/catch. Просто while() не принимает вадилдатор (хотя всё работает)
        try {
            doc = getDocument(searchString, page);
            while (true) {
                Elements elVacancies = doc.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy");
                if (elVacancies.size() == 0) break;
                // проходимся по всем <div> вакансий
                for (Element elVac : elVacancies) {
                    if (elVac != null) {
                        Vacancy vacancy = new Vacancy();
                        // заголовок
                        vacancy.setTitle(elVac.getElementsByAttributeValueContaining("data-qa", "title").text());
                        // компания  (data-qa="vacancy-serp__vacancy-employer")
                        vacancy.setCompanyName(elVac.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-employer").text());
                        // адрес (data-qa="vacancy-serp__vacancy-address") - или просто "address"
                        vacancy.setCity(elVac.getElementsByAttributeValueContaining("data-qa", "address").text());
                        // зарплата (data-qa="vacancy-serp__vacancy-compensation") - или просто "compensation"
                        String zarplata = elVac.getElementsByAttributeValueContaining("data-qa", "compensation").text();
                        vacancy.setSalary(zarplata.length() == 0 ? "" : zarplata);
                        // URL берем из A.. HREF
                        String url = elVac.getElementsByAttributeValueContaining("data-qa", "title").attr("href");
                        vacancy.setUrl(url); // херня, ссылка всё равно на hh.ua
                        // siteName
                        vacancy.setSiteName(URL_FORMAT); // х.з. зачем siteName
                        // добавляем вакансию в список
                        vacancies.add(vacancy);
                    }
                }
                page++;
                doc = getDocument(searchString, page);
            } // while
        } catch (IOException e) {
            e.printStackTrace();
        }
        // тестовый вывод
//        for(Vacancy v: vacancies) {
//            System.out.println(v);
//        }
        return vacancies;
    }

    protected Document getDocument(String searchString, int page)  throws IOException {
        //String url = "http://javarush.ru/testdata/big28data.html"; // временный URL - не надо юзать
        String url = String.format(URL_FORMAT,searchString,page);
        String userAgent = "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:69.0) Gecko/20100101 Firefox/69.0";
        String referer = "https://hh.ua";
        Document doc = null;
        try {
            doc = Jsoup.connect(url).userAgent(userAgent).referrer("").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
// для тестирования с файлом - тоже на самом деле не нуджно
//        Document doc = null;
//        try {
//            doc = Jsoup.parse(new File("C:\\work\\Java\\JavaRushTasks\\vacancies.html"),"UTF-8");
//        } catch (IOException e) {
//            return null;
//        }
        return doc;
    }
}
