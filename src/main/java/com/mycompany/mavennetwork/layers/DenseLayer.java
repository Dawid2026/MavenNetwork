
package com.mycompany.mavennetwork.layers;
import com.mycompany.mavennetwork.activationfunction.*;
import java.util.Random;


/**
 * Class that defiens denselayer
 * 
 * @author Dawid
 */
public class DenseLayer extends TrainableLayers{
    private int inputSize;
    private Random random;
    
    /**
     * Constructor fo DenseLayer
     * 
     * @param inputSize Number of Neurons of previouse layer
     * @param outputSize Number of Neurons in current layer
     * @param activation Activation function used in this layer
     */
    public DenseLayer(int inputSize,int outputSize, ActivationFunction activation){
        this.inputSize = inputSize;
        this.outputSize = outputSize;
        this.activation = activation;
        weights = new double[inputSize][outputSize];
        bias = new double[outputSize];
        gradWeights = new double[inputSize][outputSize];
        gradBias = new double[outputSize];
        preActivation = new double[outputSize];
        
        initializeWeights();
    }
    
    private void initializeWeights() {
        double std;

        if (activation instanceof Relu || activation instanceof LeakyRelu) {
            std = Math.sqrt(2.0 / inputSize);
        } else if (activation instanceof Sigmoid ) {
            std = Math.sqrt(1.0 / inputSize);
        } else {
            std = 0.01; 
        }

        weights = new double[inputSize][outputSize];
        bias = new double[outputSize];

        random = new Random();
        for(int j = 0;j < bias.length;j++){
            bias[j] = 0;
            for(int i = 0;i < weights.length;i++){
                weights[i][j] = random.nextGaussian() * std;
            }
        }
    }

    /**
     * Method that resets gradients
     */
    @Override
    public void resetGradient(){
        for(int j = 0;j < outputSize;j++){
            gradBias[j] = 0;
            for(int i = 0;i < inputSize;i++){
                gradWeights[i][j] = 0;
            }
        }
    }
    
    /**
     * Calculating forwardpass for this layer.
     * 
     * @param input Inputs 
     * @return 
     */
    @Override
    public double [] forward(double[] input){
        double[] output = new double[outputSize];
        inputs = input.clone();
        
        for(int j = 0;j < outputSize;j++){
            for(int i = 0;i < inputSize;i++){
                output[j] += weights[i][j] * input[i];
            }
            output[j] += bias[j];
            preActivation[j] = output[j];
        }
        
        if(activation != null){
            return activation.apply(output);  
        }else{
            return output;
        } 
    }
    /**
     * 
     * @param oldOutput Gradient (from next layer in Network)
     * @return Gradient to next layer
     */
    @Override
    public double [] backward(double[] oldOutput){
        double[] gradOutput = oldOutput.clone();
        if (activation != null) {
            double[] actGrad = activation.gradient(preActivation);
            for (int j = 0; j < actGrad.length; j++) {
                gradOutput[j] *= actGrad[j];
            }
        }
        
        double[] gradInput = new double[inputs.length];
        
        for(int j = 0;j < gradOutput.length;j++){
            for(int i = 0;i < inputs.length;i++){
                gradInput[i] += weights[i][j] * gradOutput[j];
            }
        }
        
         for (int i = 0; i < inputs.length; i++) {
            for (int j = 0; j < gradOutput.length; j++) {
                gradWeights[i][j] += inputs[i] * gradOutput[j];
            }
        }

        for (int j = 0; j < gradOutput.length; j++) {
            gradBias[j] += gradOutput[j];
       }
       
        return gradInput;  
    }
    
}
