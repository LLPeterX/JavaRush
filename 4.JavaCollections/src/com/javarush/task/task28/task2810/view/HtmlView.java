package com.javarush.task.task28.task2810.view;

import com.javarush.task.task28.task2810.Controller;
import com.javarush.task.task28.task2810.vo.Vacancy;

import java.util.List;

public class HtmlView implements View {
    private Controller controller;

    // список вакансий для отображения
    @Override
    public void update(List<Vacancy> vacancies) {
        // Важно! тут надо вывести только число без взяских доп. элементов типа "Вакансий: NNN"
        System.out.println(vacancies.size());
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    // симуляция события - действие юзера
    public void userCitySelectEmulationMethod() {
        controller.onCitySelect("Odessa");
    }
}
