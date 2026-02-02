/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavennetwork.lossfunctions;

/**
 * Loss functions interface
 * 
 * @author Dawid
 */
public interface LossFunctions {
    /**
     * Calculates loss of predicted outputs
     * 
     * @param yTrue
     * @param yPredicted
     * @return 
     */
    double loss(double[] yTrue, double[] yPredicted);
    /**
     * Derivative of this loss function 
     * 
     * @param yTrue
     * @param yPredicted
     * @return 
     */
    double[] gradient(double[] yTrue, double[] yPredicted);
}
