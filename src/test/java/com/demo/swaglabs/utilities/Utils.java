package com.demo.swaglabs.utilities;

public abstract class Utils {

    public static void hardWait(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
