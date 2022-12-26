package com.company;

import java.util.concurrent.atomic.AtomicBoolean;

public class Main {

    public static void main(String[] args) {
            int armySize = 5;
            AtomicBoolean isOver = new AtomicBoolean();

            Factory factory = new Factory(isOver);
            Doctor firstDoc = new Doctor(factory, isOver, armySize, "Mon");
            Doctor SecondDoc = new Doctor(factory, isOver, armySize, "Delor");

            Thread factoryThread = new Thread(factory::createDetail);
            Thread FirstDocArmy = new Thread(firstDoc::createArmy);
            Thread SecondDocArmy = new Thread(SecondDoc::createArmy);

            factoryThread.start();
            FirstDocArmy.start();
            SecondDocArmy.start();

            try {
                FirstDocArmy.join();
                SecondDocArmy.join();
                factoryThread.join();
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
