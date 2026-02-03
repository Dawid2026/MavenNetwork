
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
     * @param yTrue Output from training data
     * @param yPredicted Data predicted by mode
     * @return 
     */
    double loss(double[] yTrue, double[] yPredicted);
    /**
     * Derivative of this loss function 
     * 
     * @param yTrue Output from training data
     * @param yPredicted Data predicted by mode
     * @return 
     */
    double[] gradient(double[] yTrue, double[] yPredicted);
}
