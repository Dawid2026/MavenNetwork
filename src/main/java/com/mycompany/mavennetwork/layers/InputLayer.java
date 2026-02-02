/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavennetwork.layers;

import com.mycompany.mavennetwork.activationfunction.ActivationFunction;

/**
 *
 * @author Dawid
 */
public class InputLayer extends Layer{
    
    private int inputSize;
    /**
     * Costructor for Input Layer
     * 
     * @param inputSize Number of inputs
     * @param activation Activation Function null 
     */
    public InputLayer(int inputSize, ActivationFunction activation){
        this.inputSize = inputSize;
        this.activation = activation;
    }
    
    /**
     * Forward fo Input Layer
     * 
     * @param inputs Inputs 
     * @return Inputs
     */
    @Override
    public double [] forward(double inputs[]){
        return inputs;
    }
    /**
     * Backward fo OutputLayer
     * 
     * @param gradOutput Gradient (from next layer in Network)
     * @return Final Gradiends
     */
    @Override
    public double[] backward(double[] gradOutput) {
        return gradOutput;
    }
}
