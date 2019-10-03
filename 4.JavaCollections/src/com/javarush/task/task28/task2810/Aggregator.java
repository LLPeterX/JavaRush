package com.javarush.task.task28.task2810;

import com.javarush.task.task28.task2810.model.HHStrategy;
import com.javarush.task.task28.task2810.model.Model;
import com.javarush.task.task28.task2810.model.Provider;
import com.javarush.task.task28.task2810.view.HtmlView;
import com.javarush.task.task28.task2810.view.View;

public class Aggregator {
    public static void main(String[] args) {
        Provider provider = new Provider(new HHStrategy());
        View vv = new HtmlView();
        Model model = new Model(vv,provider);
        Controller controller = new Controller(model);
        // Agggator(11): Удали код из метода main. Этот код уже не валидный.
        //controller.scan();
    }
}
