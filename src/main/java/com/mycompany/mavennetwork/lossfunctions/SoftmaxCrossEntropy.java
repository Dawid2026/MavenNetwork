/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavennetwork.lossfunctions;

import com.mycompany.mavennetwork.lossfunctions.LossFunctions;

/**
 * Loss Function Softmaxcrossentropy
 * 
 * @author Dawid
 */
public class SoftmaxCrossEntropy implements LossFunctions {

    /** {@inheritDoc} */
    @Override
    public double loss(double[] logits, double[] yTrue) {
        double max = logits[0];
            for (double z : logits) {
                if (z > max) max = z;
            }
        double sumExp = 0;
        for (double z : logits) sumExp += Math.exp(z - max);

        double loss = 0;
        for (int i = 0; i < logits.length; i++) {
            double softmax = Math.exp(logits[i] - max) / sumExp;
            loss -= yTrue[i] * Math.log(softmax + 1e-9);
        }
        return loss;
    }
    
    /** {@inheritDoc} */
    @Override
    public double[] gradient(double[] logits, double[] yTrue) {
        double max = logits[0];
            for (double z : logits) {
                if (z > max) max = z;
            }
        double sumExp = 0;
        for (double z : logits) sumExp += Math.exp(z - max);

        double[] grad = new double[logits.length];
        for (int i = 0; i < logits.length; i++) {
            double softmax = Math.exp(logits[i] - max) / sumExp;
            grad[i] = softmax - yTrue[i];
        }
        return grad;
    }
}