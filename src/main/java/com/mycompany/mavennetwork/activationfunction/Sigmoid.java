/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavennetwork.activationfunction;

/**
 * Calculate Sigmoid function and its derivative
 * 
 * @author Dawid
 */
public class Sigmoid implements ActivationFunction{
    /** {@inheritDoc} */
    @Override
    public double[] apply(double[] input){
       double[] output = new double[input.length];
       for(int i = 0;i < input.length;i++){
           output[i] = 1.0 / (1.0 + Math.exp(-input[i]));
       }
       return output;
   }
    /** {@inheritDoc} */
    @Override
    public double[] gradient(double[] input){
       double[] grad = new double[input.length];
       for(int i = 0;i < input.length;i++){
           grad[i] = 1.0 / (1.0 + Math.exp(-input[i]));
       }
       return grad;
   }
}
