
package com.mycompany.mavennetwork.layers;
/**
 *  Abstract class that separete Layers that need to be trainind.
 * 
 * @author Dawid
 */
public abstract class TrainableLayers extends Layer {
    protected double[][] weights;
    protected double[][] gradWeights;
    protected double[] bias;
    protected double[] gradBias;
    protected double[] preActivation;
    protected int outputSize;
    protected double[] inputs;
    
    public double[][] getWeights() { return weights; }
    public double[][] getGradWeights() { return gradWeights; }
    public int getOutputSize() {return outputSize;}
    public double[] getBias() { return bias; }
    public double[] getGradBias() { return gradBias; }
    public abstract void resetGradient();
   
}
