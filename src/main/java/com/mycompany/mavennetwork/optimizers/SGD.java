/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavennetwork.optimizers;
import com.mycompany.mavennetwork.layers.*;
import java.util.List;
/**
 *
 * @author Dawid
 */
public class SGD implements Optimizer {
    private double learningRate;

    public SGD(double learningRate) {
        this.learningRate = learningRate;
    }

    @Override
    public void update(List<Layer> layers) {
        for (Layer l : layers) {
            if (l instanceof TrainableLayers layer) {
                double[][] weights = layer.getWeights();
                double[][] gradWeights = layer.getGradWeights();
                double[] bias = layer.getBias();
                double[] gradBias = layer.getGradBias();

                // Update weights
                for (int i = 0; i < weights.length; i++) {
                    for (int j = 0; j < weights[0].length; j++) {
                        weights[i][j] -= learningRate * gradWeights[i][j];
                    }
                }

                // Update biases
                for (int j = 0; j < bias.length; j++) {
                    bias[j] -= learningRate * gradBias[j];
                }
            }
        }
    }
}
