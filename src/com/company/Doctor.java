package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Doctor {
    private final String name;
    private final int armySize;
    private final Factory factory;
    private final AtomicBoolean isOver;
    private final List<Robot> currentInProgress = new ArrayList<>();
    private int readyCount = 0;

    public Doctor(Factory factory, AtomicBoolean isOver, Integer armySize, String name) {
        this.name = name;
        this.isOver = isOver;
        this.factory = factory;
        this.armySize = armySize;
        Collections.addAll(currentInProgress, Robot.values());
    }

    public void createArmy() {
        while (!isOver.get()) {
            List<Robot> details = factory.getDetailsFromStorage(currentInProgress);
            if (!details.isEmpty()) {
                System.out.println(name + " Получены детали: " + details);
                currentInProgress.removeAll(details);
                System.out.println(name + " нужны детали: " + currentInProgress);
            }
            if (currentInProgress.isEmpty()) {
                ++readyCount;
                System.out.println(name + " количество роботов " + readyCount);
                if (armySize == readyCount && !isOver.get()) {
                    isOver.set(true);
                    System.out.println("доктор " + name + " победил!");
                    return;
                }
                Collections.addAll(currentInProgress, Robot.values());
            }
        }
    }
}
