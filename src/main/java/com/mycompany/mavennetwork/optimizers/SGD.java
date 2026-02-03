
package com.mycompany.mavennetwork.optimizers;
import com.mycompany.mavennetwork.layers.*;
import java.util.List;
/**
 *  Class SGD updates weigths in layers and aplies learning rate.
 * 
 * @author Dawid
 */
public class SGD implements Optimizer {
    private double learningRate;

    public SGD(double learningRate) {
        this.learningRate = learningRate;
    }
    
    /** {@inheritDoc} */
    @Override
    public void update(List<Layer> layers) {
        for (Layer l : layers) {
            if (l instanceof TrainableLayers layer) {
                double[][] weights = layer.getWeights();
                double[][] gradWeights = layer.getGradWeights();
                double[] bias = layer.getBias();
                double[] gradBias = layer.getGradBias();

                for (int i = 0; i < weights.length; i++) {
                    for (int j = 0; j < weights[0].length; j++) {
                        weights[i][j] -= learningRate * gradWeights[i][j];
                    }
                }

                for (int j = 0; j < bias.length; j++) {
                    bias[j] -= learningRate * gradBias[j];
                }
            }
        }
    }
}
