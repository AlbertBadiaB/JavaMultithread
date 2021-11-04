/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ba.rrhh.multithread;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author albert.badia
 */
public class MyRunnable implements Runnable {

    private final long waitTime;

    public MyRunnable(long waitTime) {
        this.waitTime = waitTime;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(waitTime);
            System.out.println(Thread
                    .currentThread()
                    .getName());
        } catch (InterruptedException ex) {
            Logger
                    .getLogger(MyRunnable.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

}
