/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavennetwork.data;

import com.mycompany.mavennetwork.App;
import Logic.helperMathFunctions;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dawid
 */
public class DataBase {
    private double[][] X;
    private double[][] Y;
    private int[] labels;
    private double[][] XTest;
    private double[][] YTest;
    private int[] labelsTest;

    public double[][] getX() {
        return X;
    }

    public double[][] getY() {
        return Y;
    }

    public int[] getLabels() {
        return labels;
    }

    public double[][] getXTest() {
        return XTest;
    }

    public double[][] getYTest() {
        return YTest;
    }

    public int[] getLabelsTest() {
        return labelsTest;
    }

    public double[][] readCSV(String filePath) {
        InputStream is = App.class.getResourceAsStream(filePath);
        List<double[]> dataList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                double[] rowData = new double[tokens.length];

                for (int i = 0; i < tokens.length; i++) {
                    rowData[i] = Double.parseDouble(tokens[i]);
                }

                dataList.add(rowData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        double[][] dataArray = new double[dataList.size()][];
        for (int i = 0; i < dataList.size(); i++) {
            dataArray[i] = dataList.get(i);
        }

        return dataArray;
    }

    public void loadTrainingData(String filePath) {
        double[][] input = this.readCSV(filePath);
        int samples = input.length;
        int features = input[0].length - 1;

        labels = new int[samples];
        X = new double[samples][features];

        for (int i = 0; i < samples; i++) {
            labels[i] = (int) input[i][0];
            for (int j = 1; j < input[i].length; j++) {
                X[i][j - 1] = input[i][j] / 255.0;
            }
        }

        Y = helperMathFunctions.oneHotEncode(labels, 10);
    }

    public void loadTestingData(String filePath) {
        double[][] input = this.readCSV(filePath);
        int samples = input.length;
        int features = input[0].length - 1;

        labelsTest = new int[samples];
        XTest = new double[samples][features];

        for (int i = 0; i < samples; i++) {
            labelsTest[i] = (int) input[i][0];
            for (int j = 1; j < input[i].length; j++) {
                XTest[i][j - 1] = input[i][j] / 255.0;
            }
        }

        YTest = helperMathFunctions.oneHotEncode(labelsTest, 10);
    }
}

