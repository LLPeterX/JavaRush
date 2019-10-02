package com.javarush.task.task28.task2810.vo;

// Этот класс будет хранить данные о вакансии.
public class Vacancy {
    // 3. Что есть у вакансии?
    //Название, зарплата, город, название компании, название сайта, на котором вакансия найдена,
    // ссылка на вакансию. В классе Vacancy создай соответствующие строковые поля:
    // title, salary, city, companyName, siteName, url.
    private String title;
    private String salary;
    private String city;
    private String companyName;
    private String siteName;
    private String url;

    // геттеры и сеттеры
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
