package com.javarush.task.task39.task3906;

public class ElectricPowerSwitch {
    //private SecuritySystem securitySystem;
    Switchable sw;

    //public ElectricPowerSwitch(SecuritySystem securitySystem) {
//        this.securitySystem = securitySystem;
  //  }


    public ElectricPowerSwitch(Switchable sw) {
        this.sw = sw;
    }

    public void press() {
//        System.out.println("Power switch flipped.");
//        if (securitySystem.isOn()) {
//            securitySystem.turnOff();
//        } else {
//            securitySystem.turnOn();
//        }
        sw.press();
    }
}
