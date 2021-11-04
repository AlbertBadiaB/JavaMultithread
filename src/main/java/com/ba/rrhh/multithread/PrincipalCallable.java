/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ba.rrhh.multithread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author albert.badia
 */
public class PrincipalCallable {

    public static void main(String[] args) {
        try{
            System.out.println(Thread.currentThread().getName());
            ExecutorService service = Executors.newFixedThreadPool(1);
            
            Future<Integer> resultado =  service.submit(new MyCallable());
            while(!resultado.isDone()){
                System.out.println("Calculating...");
            }
            if(resultado.isDone()){
                System.out.println("The result is: " + resultado.get());
            }
            service.shutdown();
        }catch(InterruptedException ex){
            ex.printStackTrace();
        }catch(ExecutionException ex){
            ex.printStackTrace();
        }
    }
}
