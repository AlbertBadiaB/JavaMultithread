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
public class PrincipalCsv {
    
    private static String BTC  = "https://query1.finance.yahoo.com/v7/finance/download/BTC-USD?period1=1604315741&period2=1635851741&interval=1d&events=history&includeAdjustedClose=true";
    private static String ETH  = "https://query1.finance.yahoo.com/v7/finance/download/ETH-USD?period1=1604316376&period2=1635852376&interval=1d&events=history&includeAdjustedClose=true";
    private static String AMZ  = "https://query1.finance.yahoo.com/v7/finance/download/AMZN?period1=1604316468&period2=1635852468&interval=1d&events=history&includeAdjustedClose=true";
    private static String MSFT  = "https://query1.finance.yahoo.com/v7/finance/download/MSFT?period1=1604316394&period2=1635852394&interval=1d&events=history&includeAdjustedClose=true";
    
     public static void main(String[] args) {
        try{
            System.out.println(Thread.currentThread().getName());
            ExecutorService service = Executors.newFixedThreadPool(4);
            
            Future<String> resultadoBTC =  service.submit(new CsvGetter(BTC));
            Future<String> resultadoETH =  service.submit(new CsvGetter(ETH));
            Future<String> resultadoMSFT =  service.submit(new CsvGetter(MSFT));
            Future<String> resultadoAMZ =  service.submit(new CsvGetter(AMZ));
            
            
            while(!resultadoBTC.isDone() && !resultadoETH.isDone() && !resultadoAMZ.isDone() && !resultadoMSFT.isDone()){
               // System.out.println("Calculating...");
            }
            //.isDone() desactivats ja que per algun motiu no els mostrava tots
            //if(resultadoBTC.isDone()){
                System.out.println("The BTC result is: " + resultadoBTC.get());
            //}
            //if(resultadoETH.isDone()){
                System.out.println("The ETH result is: " + resultadoETH.get());
            //}
            //if(resultadoMSFT.isDone()){
                System.out.println("The MSFT result is: " + resultadoMSFT.get());
            //}
            //if(resultadoAMZ.isDone()){
                System.out.println("The AMZ result is: " + resultadoAMZ.get());
            //}
            service.shutdown();
        }catch(InterruptedException ex){
            ex.printStackTrace();
        }catch(ExecutionException ex){
            ex.printStackTrace();
        }
    }
}
