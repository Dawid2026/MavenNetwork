
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
