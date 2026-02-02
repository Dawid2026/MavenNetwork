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
public abstract class Layer {
    protected ActivationFunction activation;
    public abstract double [] forward(double[] input);
    public abstract double [] backward(double[] oldOutput);
}
