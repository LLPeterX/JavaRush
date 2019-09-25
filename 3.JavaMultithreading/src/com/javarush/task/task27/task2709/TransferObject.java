package com.javarush.task.task27.task2709;

public class TransferObject {
    private int value;
    protected volatile boolean isValuePresent = false; //use this variable

// 5. Метод get класса TransferObject должен устанавливать флаг isValuePresent в false
// и уведомлять другие нити ожидающие освобождения монитора перед возвратом значение поля value.
    public synchronized int get() throws InterruptedException {
        while(!isValuePresent)
            wait();
        System.out.println("Got: " + value);
        isValuePresent = false;
        notifyAll();
        return value;
    }
//4. Метод put класса TransferObject должен ждать пока value заберут и обновлять его значение после того, как оно пропадет.
//6. Метод put класса TransferObject должен устанавливать флаг isValuePresent в true
//   и уведомлять другие нити ожидающие освобождения монитора после обновления значение поля value.
    public synchronized void put(int value) throws InterruptedException {
        while (isValuePresent) {
                wait(); // ждать, пока не заберут значение
        }
        // значение забрали, суем новое
        this.value = value;
        System.out.println("Put: " + value);
        isValuePresent = true;
        notifyAll();
        //notifyAll();
    }
}
