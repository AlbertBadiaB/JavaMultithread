/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ba.rrhh.multithread;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 *
 * @author albert.badia
 */
public class CsvGetter implements Callable<String> {

    private String uri;
    private int CLOSE_POSITION = 5;

    public CsvGetter(String uri) {
        this.uri = uri;
    }

    public String getLastClosevalue() throws MalformedURLException, IOException {
        BufferedInputStream in = new BufferedInputStream(new URL(this.uri).openStream());
        List<String> csvValues = readFile(in);
        return csvValues.get(csvValues.size() - 1);
    }

    private List<String> readFile(BufferedInputStream in) {
        ArrayList<String> closeValues = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in,
                StandardCharsets.UTF_8))) {

            String line = br.readLine();

            while (line != null) {
                String[] attributes = line.split(",");
                closeValues.add(attributes[CLOSE_POSITION]);
                line = br.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return closeValues;
    }

    @Override
    public String call() throws Exception {
        return getLastClosevalue();
    }
}
