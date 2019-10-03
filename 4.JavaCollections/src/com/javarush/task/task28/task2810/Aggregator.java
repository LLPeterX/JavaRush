package com.javarush.task.task28.task2810;

import com.javarush.task.task28.task2810.model.HHStrategy;
import com.javarush.task.task28.task2810.model.Model;
import com.javarush.task.task28.task2810.model.MoikrugStrategy;
import com.javarush.task.task28.task2810.model.Provider;
import com.javarush.task.task28.task2810.view.HtmlView;
import com.javarush.task.task28.task2810.view.View;

public class Aggregator {
    public static void main(String[] args) {

        Provider providerHH = new Provider(new HHStrategy());
        Provider prviderMK = new Provider(new MoikrugStrategy());
        View vv = new HtmlView();
        Model model = new Model(vv,providerHH, prviderMK);
        Controller controller = new Controller(model);
        vv.setController(controller);
        // Agggator(11): Удали код из метода main. Этот код уже не валидный.
        ((HtmlView) vv).userCitySelectEmulationMethod();
    }
}
