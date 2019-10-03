package com.javarush.task.task28.task2810.view;

import com.javarush.task.task28.task2810.Controller;
import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class HtmlView implements View {
    private Controller controller;
    // В классе HtmlView создай приватное строковое final поле filePath и присвой ему относительный путь к vacancies.html.
    // ниже filePath правильно (?)
    final private String filePath = "./4.JavaCollections/src/"+this.getClass().getPackage().getName().replace('.', '/')+"/vacancies.html";

    // список вакансий для отображения
    @Override
    public void update(List<Vacancy> vacancies) {
        //System.out.println(vacancies.size());
        // 1. В методе update класса HtmlView реализуй следующую логику:
        //1.1. сформируй новое тело файла vacancies.html, которое будет содержать вакансии,
        //1.2. запиши в файл vacancies.html его обновленное тело,
        //1.3. Все исключения должны обрабатываться в этом методе - выведи стек-трейс, если возникнет исключение.
        try {
            String fileBody = getUpdatedFileContent(vacancies);
            updateFile(fileBody);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    // симуляция события - действие юзера
    public void userCitySelectEmulationMethod() {
        controller.onCitySelect("Odessa");
    }

    // пустые методы для обновления содержимого файов
    private String getUpdatedFileContent(List<Vacancy> vacList) {
        // Получи элемент, у которого есть класс template
        try {
            Document doc = getDocument();
            Element template = doc.getElementsByClass("template").first();
            // Сделай копию этого объекта, удали из нее атрибут "style" и класс "template".
            //Используй этот элемент в качестве шаблона для добавления новой строки в таблицу вакансий.
            Element copyTemplate = template.clone();
            copyTemplate.removeAttr("style");
            copyTemplate.removeClass("template");
            // удалить из документа все элементы с class="vacancy"
            // аккуратно - через select() - только "vacancy", но не " vacancy template" (2 класса)
            doc.select(".vacancy").not(".template").remove();
            // новый элемент будем вставлять перед "template"
            for(Vacancy v : vacList) {
                Element e = copyTemplate.clone();
                // ttile - содержит href
                Element eTitle = e.getElementsByClass("title").first();
                  Element eURL = eTitle.getElementsByTag("a").first();
                  eURL.attr("href", v.getUrl());
                  eURL.text(v.getTitle());
                //eTitle.text(v.getTitle());
                // пишем город
                Element eCity = e.getElementsByClass("city").first();
                eCity.text(v.getCity());
                // companyName
                Element eComp = e.getElementsByClass("companyName").first();
                eComp.text(v.getCompanyName());
                // salary
                Element eSalary = e.getElementsByClass("salary").first();
                eSalary.text(v.getSalary());
                // вставоляем созданный элемент в документ перед шаблоном
                template.before(e);
            }
            return doc.html();
        } catch (Exception e) {
            e.printStackTrace();
            return "Some exception occurred";
        }

    }

    // надо записать содержимое fileBody в файл filePath
    private void updateFile(String fileBody) {
        try (FileOutputStream outf = new FileOutputStream(filePath)) {
            outf.write(fileBody.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected Document getDocument() throws IOException {
        // распарсить файл filePath (vacancies.html)
        Document doc = Jsoup.parse(new File(filePath),"UTF-8");
        //System.out.println(doc.html());
        return doc;
    }
}
