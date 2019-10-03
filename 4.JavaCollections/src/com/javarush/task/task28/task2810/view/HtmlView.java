package com.javarush.task.task28.task2810.view;

import com.javarush.task.task28.task2810.Controller;
import com.javarush.task.task28.task2810.vo.Vacancy;

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
        return null;
    }

    // надо записать содержимое fileBody в файл filePath
    private void updateFile(String fileBody) {
        try (FileOutputStream outf = new FileOutputStream(filePath)) {
            outf.write(fileBody.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
