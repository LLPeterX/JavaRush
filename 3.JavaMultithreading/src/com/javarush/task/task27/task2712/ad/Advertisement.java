package com.javarush.task.task27.task2712.ad;

// пока заказ готовится, показываем рекламу
public class Advertisement {
    private Object content; // видео
    private String name; // имя/название
    private long initialAmount; // начальная сумма, стоимость рекламы в копейках. Используем long, чтобы избежать проблем с округлением
    private int hits; // количество оплаченных показов
    private int duration; // продолжительность в секундах этого рекламного ролика

    private long amountPerOneDisplaying; // стоимость одного показа рекламного объявления в копейках (initialAmount/hits).

    public Advertisement(Object content, String name, long initialAmount, int hits, int duration) {
        this.content = content; // видео
        this.name = name; // наименование
        this.initialAmount = initialAmount;
        this.hits = hits;
        this.duration = duration;
        //this.amountPerOneDisplaying = initialAmount/hits;
        this.amountPerOneDisplaying =  (hits > 0) ? initialAmount / hits : 0;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    // стоимость показа одного рекламного ролика
    public long getAmountPerOneDisplaying() {
        return amountPerOneDisplaying;
    }


    public void revalidate() {
        // 2.5.1. Бросать UnsupportedOperationException, если количество показов не положительное число.
        //2.5.2. Уменьшать количество показов.
        if(hits<=0) {
            throw new UnsupportedOperationException();
        }
        if (hits == 1)
            amountPerOneDisplaying += initialAmount % amountPerOneDisplaying;
        hits--;
    }

    public int getHits() {
        return hits;
    }
}
