package com.javarush.task.task28.task2810;

import com.javarush.task.task28.task2810.model.Model;
import com.javarush.task.task28.task2810.vo.Vacancy;

import java.util.ArrayList;
import java.util.List;

// класс бизнес-логики
public class Controller {

    //private Provider[] providers;
    private List<Vacancy> vacancies = new ArrayList<>();
    // Aggregator (11) : добавить поле Добавь в Controller новое поле Model model.
    private Model model;

    // конструктор с переменным значением аргументов - провадйреров
    // Aggregator (11) - конструктор и toString() уже не нужны
/*
    public Controller(Provider... providers) {
        if(providers == null || providers.length==0) {
            throw new IllegalArgumentException();
        }
        this.providers = providers;
    }

    @Override
    public String toString() {
        return "Controller{" +
                "providers=" + Arrays.toString(providers) +
                '}';
    }
 */
// Aggregator (11) - новый конструктор

    public Controller(Model model) {
        if(model == null) {
            throw new IllegalArgumentException();
        }
        this.model = model;
    }

// Aggregator (11) - переносим scan() в Model
/*
    public void scan() {
        // пройтись по всем провайдерам и собрать вакансии
        for(Provider p : providers) {
            vacancies.addAll(p.getJavaVacancies("Киев"));
        }
        System.out.println(vacancies.size());
    }

 */

    public void onCitySelect(String cityName) {
        model.selectCity(cityName);
    }
}
