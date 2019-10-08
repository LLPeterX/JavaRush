package com.javarush.task.task34.task3410.view;

import javax.swing.*;
import java.awt.*;

public class Field extends JPanel {
    private View view;

    public Field(View view) {
        this.view = view;
        //this.view.init(); // не сюда!
    }

    // пока заглушка
    public void paint(Graphics g) {

    }
}
