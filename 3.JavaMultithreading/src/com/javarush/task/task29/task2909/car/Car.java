package com.javarush.task.task29.task2909.car;

import java.util.Date;

abstract public class Car {
    static public final int TRUCK = 0;
    static public final int SEDAN = 1;
    static public final int CABRIOLET = 2;

    double fuel;

    public double summerFuelConsumption;
    public double winterFuelConsumption;
    public double winterWarmingUp;

    private int type;

    private boolean driverAvailable;
    private int numberOfPassengers;

    // конструктор
    protected Car(int type, int numberOfPassengers) {
        this.type = type;
        this.numberOfPassengers = numberOfPassengers;
    }

    public void fill(double numberOfLiters) throws Exception { // void а не int
        if (numberOfLiters < 0)
            throw new Exception();
        fuel += numberOfLiters;

    }


    public double getTripConsumption(Date date, int length, Date SummerStart, Date SummerEnd) {
        if (isSummer(date, SummerStart, SummerEnd)) {
            return getSummerConsumption(length);
        } else {
            return getWinterConsumption(length);
        }
    }


    public int getNumberOfPassengersCanBeTransferred() {
////        if (!isDriverAvailable())
////            return 0;
////        if (fuel <= 0)
////            return 0;
//
//        return numberOfPassengers;
        if(canPassengersBeTransferred())
            return numberOfPassengers;
        else
            return 0;
    }

    public boolean isDriverAvailable() {
        return driverAvailable;
    }

    public void setDriverAvailable(boolean driverAvailable) {
        this.driverAvailable = driverAvailable;
    }

    public void startMoving() {
//        if (numberOfPassengers > 0) {
//            fastenPassengersBelts();
//            fastenDriverBelt();
//        } else {
//            fastenDriverBelt();
//        }
        fastenDriverBelt();
        if(numberOfPassengers>0) {
            fastenPassengersBelts();
        }
    }

    public void fastenPassengersBelts() {
    }

    public void fastenDriverBelt() {
    }

    //    public int getMaxSpeed() {
//        if (type == TRUCK)
//            return 80;
//        if (type == SEDAN)
//            return 120;
//        return 90;
//    }
    abstract public int getMaxSpeed();

    public static Car create(int type, int numberOfPassengers) {
        Car c = null;
        switch (type) {
            case SEDAN:
                c= new Sedan(numberOfPassengers);
                break;
            case TRUCK:
                c= new Truck(numberOfPassengers);
                break;
            case CABRIOLET:
                c= new Cabriolet(numberOfPassengers);
                break;
        }
        return c;
    }

    public boolean isSummer(Date date, Date summerStart, Date summerEnd) {
         //return (date.getMonth()>=summerStart.getMonth() && date.getMonth()<=summerEnd.getMonth());
        return !(date.before(summerStart) || date.after(summerEnd));
    }

    public double getWinterConsumption(int length) {
        return length * winterFuelConsumption + winterWarmingUp;
    }

    public double getSummerConsumption(int length) {
        return length * summerFuelConsumption;
    }

    private boolean canPassengersBeTransferred() {
        return isDriverAvailable() && fuel>0;
    }
}