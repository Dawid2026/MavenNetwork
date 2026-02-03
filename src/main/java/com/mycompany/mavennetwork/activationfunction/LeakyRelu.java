
package com.mycompany.mavennetwork.activationfunction;

/**
 * Calculated Laeky Relu function and its derivative
 * 
 * @author Dawid
 */
public class LeakyRelu implements ActivationFunction{
    
    /** {@inheritDoc} */
    @Override
    public double[] apply(double[] input){
        double output[] = new double[input.length];
        for(int i = 0;i < input.length;i++){
            output[i] = Math.max(0.1 * input[i], input[i]);
        }
        return output;
    }
    
    /** {@inheritDoc} */
    @Override
    public double[] gradient(double[] oldOutput){
        double grad[] = new double[oldOutput.length];
        for(int i = 0;i < oldOutput.length;i++){
            if(oldOutput[i] > 0){
                grad[i] = 1;
            }else{
                grad[i] = 0.1;
            }
        }
        return grad;
    }
}
