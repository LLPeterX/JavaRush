package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.view.View;
import com.javarush.task.task28.task2810.vo.Vacancy;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private View view;
    private Provider[] providers;

    // Создай конструктор с двумя параметрами - 1) вью, 2) массив провайдеров
    // На неправильные данные кинь IllegalArgumentException.
    public Model(View view, Provider ...providers) {
        if(view == null || (providers == null || providers.length ==0))
            throw  new IllegalArgumentException();
        this.view = view;
        this.providers = providers;
    }

    // 5. Реализуй логику метода selectCity:
    //5.1. получить вакансии с каждого провайдера,
    //5.2. обновить вью списком вакансий из п.5.1.
    public void selectCity(String city) {
        List<Vacancy> vacancies = new ArrayList<>();
        for(Provider p : providers) {
            vacancies.addAll(p.getJavaVacancies(city));
        }
        view.update(vacancies);
    }

    public void scan() {
        // пройтись по всем провайдерам и собрать вакансии
        List<Vacancy> vacancies = new ArrayList<>();
        for(Provider p : providers) {
            vacancies.addAll(p.getJavaVacancies("Киев"));
        }
        System.out.println(vacancies.size());
    }

}
