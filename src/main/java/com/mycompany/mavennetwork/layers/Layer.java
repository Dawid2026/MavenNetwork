
package com.mycompany.mavennetwork.layers;
import com.mycompany.mavennetwork.activationfunction.ActivationFunction;


/**
 *
 * Layer abstract class
 * 
 * @author Dawid
 */
public abstract class Layer {
    protected ActivationFunction activation;
    public abstract double [] forward(double[] input);
    public abstract double [] backward(double[] oldOutput);
}
