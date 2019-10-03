package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MoikrugStrategy implements Strategy {
    private static final String URL_FORMAT = "https://moikrug.ru/vacancies?q=java+%s&page=%d";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        int page = 0;
        List<Vacancy> vacancies = new ArrayList<>();
        Document doc = null;
        try {
            doc = getDocument(searchString, page);
            while (true) {
                //doc = getDocument(searchString, page);
                Elements elVacancies = doc.getElementsByClass("job");
                if (elVacancies.size() == 0) break;
                // проходимся по всем <div> вакансий
                for (Element elVac : elVacancies) {
                    if (elVac != null) {
                        Vacancy vacancy = new Vacancy();
                        // заголовок
                        Element eTitle = elVac.getElementsByClass("title").first();
                        vacancy.setTitle(eTitle.text());
                        // компания
                        vacancy.setCompanyName(elVac.getElementsByClass("company_name").text());
                        // адрес
                        vacancy.setCity(elVac.getElementsByClass("location").text());
                        // зарплата
                        String zarplata = elVac.getElementsByClass("salary").text();
                        vacancy.setSalary(zarplata.length() == 0 ? "" : zarplata);
                        // откуда взять URL? из a href...
                        String url = eTitle.getElementsByTag("a").first().attr("href");
                        url = "https://moikrug.ru"+url;
                        vacancy.setUrl(url);
                        // siteName
                        vacancy.setSiteName(URL_FORMAT); // вообще, неправильно! Да х.з. зачем siteName
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
        return vacancies;
    }

    protected Document getDocument(String searchString, int page) throws IOException {
        String url = String.format(URL_FORMAT, searchString, page);
        String userAgent = "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:69.0) Gecko/20100101 Firefox/69.0";
        Document doc = null;
        try {
            doc = Jsoup.connect(url).userAgent(userAgent).referrer("").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }

    }

