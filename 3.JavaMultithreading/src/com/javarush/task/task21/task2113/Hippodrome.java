package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

public class Hippodrome {
    private List<Horse> horses;
    public  static Hippodrome game;

    public List<Horse> getHorses() {
        return this.horses;
    }

    // конструкторы
    public Hippodrome() {}
    
    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    public void run() throws InterruptedException{
        for(int i=0; i<100; i++) {
            move();
            print();
            Thread.sleep(200);
        }
    }

    public void move() {
        for(Horse h : this.horses) {
            h.move();
        }
    }

    public void print() {
        for(Horse h : this.horses) {
            h.print();
        }
        for(int i=0; i<10; i++)
            System.out.println();
    }

    public Horse getWinner() {
        double dist = 0;
        Horse winner = null;
        for(Horse h : this.horses) {
            if(h.getDistance() > dist) {
                dist = h.getDistance();
                winner = h;
            }
        }
        return winner;
    }

    public void printWinner() {
        System.out.println("Winner is "+getWinner().getName()+"!");
    }

    // -------------------- main ---------------------
    public static void main(String args[]) {
        List<Horse> list = new ArrayList<>();
        list.add(new Horse("Silva",3,0));
        list.add(new Horse("Mashka",3,0));
        list.add(new Horse("Skakoon",3,0));
        game = new Hippodrome(list);
        try {
            game.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        game.printWinner();
    }

}
