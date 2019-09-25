package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// у каждого планшета будет свой объект менеджера, который будет подбирать оптимальный набор роликов
// и их последовательность для каждого заказа.
// Он также будет взаимодействовать с плеером и отображать ролики.
// AdvertisementManager выполняет только одно единственное действие - обрабатывает рекламное видео.
public class AdvertisementManager {
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;

    // конструктор
    // Т.к. продолжительность видео у нас хранится в секундах, то и и время выполнения заказа тоже будем принимать в секундах.
    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }
/*
    public void processVideos() throws NoVideoAvailableException {
        //2. Подобрать список видео из доступных, просмотр которых обеспечивает максимальную выгоду.
        //2.4. Отобразить все рекламные ролики, отобранные для показа, в порядке уменьшения стоимости показа
        // одного рекламного ролика в копейках. Вторичная сортировка - по увеличению стоимости показа одной секунды
        // рекламного ролика в тысячных частях копейки.
        //Используйте метод Collections.sort

        if(storage==null || storage.list().size()==0) {
            throw new NoVideoAvailableException();
        }
        Collections.sort(storage.list(), new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                // сначала сортировка по стоимости показа (уменьш.)
                int res = Long.compare(o1.getAmountPerOneDisplaying(), o2.getAmountPerOneDisplaying());
                if (res != 0)
                    return -res;
                // если равны - сорт по стоимости показа 1 секунды (увел.)
                // тысячная копейки = 1000
                long oneSecondCost1 = o1.getAmountPerOneDisplaying() * 1000 / o1.getDuration();
                long oneSecondCost2 = o2.getAmountPerOneDisplaying() * 1000 / o2.getDuration();

                return Long.compare(oneSecondCost1, oneSecondCost2); // return компаратора
            }
        });
//        // отсортировали. Выводим список
//        for(Advertisement a : storage.list()) {
//            //getAmountPerOneDisplaying - стоим.показа 1 ролика (коп)
//            // стоимости показа одной секунды рекламного ролика в тысячных частях копейки.
//            long stoimSecs = a.getAmountPerOneDisplaying()*1000/a.getDuration();
//            //ConsoleHelper.writeMessage(String.format("First Video is displaying... %d,%d", a.getAmountPerOneDisplaying(), stoimSecs));
//        }
        // Рекурсивный метод должен выбрать набор рекламных роликов, которые будут показаны посетителю.
        //
        //Этот набор должен удовлетворять следующим требованиям:
        //1. сумма денег, полученная от показов, должна быть максимальной из всех возможных вариантов
        //2. общее время показа рекламных роликов НЕ должно превышать время приготовления блюд для текущего заказа;
        //3. для одного заказа любой видео-ролик показывается не более одного раза;
        //4. если существует несколько вариантов набора видео-роликов с одинаковой суммой денег, полученной от показов, то:
        //4.1. выбрать тот вариант, у которого суммарное время максимальное;
        //4.2. если суммарное время у этих вариантов одинаковое, то выбрать вариант с минимальным количеством роликов;
        //5. количество показов у любого рекламного ролика из набора - положительное число.
        //
        //Также не забудь реализовать п.2.4 из предыдущего задания (вывести на экран все подходящие ролики в порядке уменьшения стоимости показа одного рекламного ролика в копейках. Вторичная сортировка - по увеличению стоимости показа одной секунды рекламного ролика в тысячных частях копейки).
        //
        //Для каждого показанного видео-ролика должен быть вызван метод revalidate().
        int timeLeft = timeSeconds;
        // собрать статистику ДО показа
        long amount = 0;
        int totalDuration = 0;
        for (Advertisement ad : storage.list()) {
            totalDuration += ad.getDuration();
            amount += ad.getAmountPerOneDisplaying();
        }
        StatisticManager.getInstance().register(new VideoSelectedEventDataRow(storage.list(), amount, totalDuration));
        // собственно показ
        for (Advertisement advertisement : storage.list()) {
            if (timeLeft < advertisement.getDuration() || advertisement.getHits()<=0) {
                continue;
            }
            ConsoleHelper.writeMessage(advertisement.getName() + " is displaying... "
                    + advertisement.getAmountPerOneDisplaying() + ", "
                    + advertisement.getAmountPerOneDisplaying() * 1000 / advertisement.getDuration());

            timeLeft -= advertisement.getDuration();
            advertisement.revalidate();
        }
//        // вызывать register перед отображением рекламы пользователю
//        StatisticManager statisticManager = StatisticManager.getInstance();
//        // VideoSelectedEventDataRow(List<Advertisement> optimalVideoSet, long amount, int totalDuration)
//        VideoSelectedEventDataRow data = new  VideoSelectedEventDataRow(storage.list(),
//                advertisement.getAmountPerOneDisplaying(),
//                advertisement.getDuration());

        if (timeLeft == timeSeconds) {
            throw new NoVideoAvailableException();
        }

    }

 */
public void processVideos() throws NoVideoAvailableException {

    List<Advertisement> videos = storage.list();
    if (storage.list().isEmpty())
        throw new NoVideoAvailableException();

    // ищем список видео для показа согласно критериям
    //List<Advertisement> bestAds = new VideoHelper().findAllYouNeed();
    List<Advertisement> bestAds = storage.list();

    // сортируем полученный список
    Collections.sort(bestAds, new Comparator<Advertisement>() {
        @Override
        public int compare(Advertisement video1, Advertisement video2) {
            long dif = video2.getAmountPerOneDisplaying() - video1.getAmountPerOneDisplaying();
            if (dif == 0) dif = video2.getDuration() - video1.getDuration();
            return (int) dif;
        }
    });
    long amount = 0;
    int totalDuration = 0;
    for (Advertisement ad : bestAds) {
        totalDuration += ad.getDuration();
        amount += ad.getAmountPerOneDisplaying();
    }
    StatisticManager.getInstance().register(new VideoSelectedEventDataRow(bestAds, amount, totalDuration));


    // выводим список: имя видео, стоимость одного показа, стоимость показа 1 секунды
    for (Advertisement ad : bestAds) {
        ConsoleHelper.writeMessage(ad.getName() + " is displaying... " +
                ad.getAmountPerOneDisplaying() + ", " +
                1000 * ad.getAmountPerOneDisplaying() / ad.getDuration());
        ad.revalidate();
    }
} // end processVideos()


}
