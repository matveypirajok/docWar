package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class Factory {
    private final AtomicBoolean win;
    private static final Random RANDOM = new Random();
    private final List<Robot> storage = new ArrayList<>();

    public Factory(AtomicBoolean win) {
        this.win = win;
    }


    public void createDetail() {
        synchronized (storage) {
            while (!win.get()) {
                Robot detail = Robot.values()[RANDOM.nextInt(Robot.values().length)];

                storage.add(detail);
                System.out.println("Добавлена деталь в склад: " + detail);

                try {
                    storage.wait(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Robot> getDetailsFromStorage(List<Robot> details) {
        List<Robot> returnedDetails = new ArrayList<>();
        synchronized (storage) {
            for(Robot d : storage) {
                if(storage.remove(d)){
                    returnedDetails.add(d);
                }
            }
        }
        return returnedDetails;
    }
}
