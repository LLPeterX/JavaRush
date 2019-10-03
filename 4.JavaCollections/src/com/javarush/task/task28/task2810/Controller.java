package com.javarush.task.task28.task2810;

import com.javarush.task.task28.task2810.model.Provider;
import com.javarush.task.task28.task2810.vo.Vacancy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// класс бизнес-логики
public class Controller {

    private Provider[] providers;
    private List<Vacancy> vacancies = new ArrayList<>();

    // конструктор с переменным значением аргументов - провадйреров
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

    public void scan() {
        // пройтись по всем провайдерам и собрать вакансии
        for(Provider p : providers) {
            vacancies.addAll(p.getJavaVacancies("Киев"));
        }
        System.out.println(vacancies.size());
    }
}
