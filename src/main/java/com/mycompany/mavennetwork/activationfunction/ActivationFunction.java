/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavennetwork.activationfunction;

/**
 *
 * @author Dawid
 */
public interface ActivationFunction {
    /**
     * Calculate Activation function
     * 
     * @param input Inputs
     * @return output of activation function
     */
    public double[] apply(double[] input);
    /**
     * Calculate derivative of function
     * 
     * @param oldOutput Gradient from prev layer
     * @return output of derivative of activation function
     */
    public double[] gradient(double[] oldOutput);
            
}
