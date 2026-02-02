/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic;
import com.mycompany.mavennetwork.layers.TrainableLayers;
import com.mycompany.mavennetwork.layers.Layer;
import com.mycompany.mavennetwork.lossfunctions.LossFunctions;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Dawid
 */
public class Model {
    private ArrayList<Layer> layers = new ArrayList<>();
    private LossFunctions lossFunction;
    
    public void compile(LossFunctions lossFunction) {
        this.lossFunction = lossFunction;
    }

    public void add(Layer layer){
        layers.add(layer);
    }
    
    public List<Layer> getLayers() {
        return layers;
    }
    
    public LossFunctions getLossFunction() {
        return lossFunction;
    }

    public double loss(double[] yPred, double[] yTrue) {
        return lossFunction.loss(yPred, yTrue);
    }
    
    public void resetGradients(){
        for(Layer l : layers){
            if(l instanceof TrainableLayers d){
                d.resetGradient();
            }
        }
    }
    
    public void clearLayers(){
        layers.clear();
    }
    
    public void scaleGradients(double scale) {
        for (Layer l : layers) {
            if (l instanceof TrainableLayers layer) {
                double[][] gradWeights = layer.getGradWeights();
                double[] gradBias = layer.getGradBias();
                
                for (int i = 0; i < gradWeights.length; i++) {
                    for (int j = 0; j < gradWeights[0].length; j++) {
                        gradWeights[i][j] *= scale;
                    }
                }

                for (int j = 0; j < gradBias.length; j++) {
                    gradBias[j] *= scale;
                }
            }
        }
    }
    
    public double[] forward(double[] input){
        double[] output = input;
        for (Layer layer : layers) {
            output = layer.forward(output);
        }
        return output;
    }
    
    public void backward(double[] gradOutput) {
        double[] grad = gradOutput;

        for (int i = layers.size() - 1; i >= 0; i--) {
            grad = layers.get(i).backward(grad);
        }

    }
    
    public double test(double[][] XTest, int[] labelsTest) {
        int correct = 0;

        for (int i = 0; i < XTest.length; i++) {
            double[] logits = forward(XTest[i]);
            int predicted = helperMathFunctions.argMax(logits);
            int actual = labelsTest[i];

            if (predicted == actual) {
                correct++;
            }
        }

        return (double) correct / XTest.length;
    }
}
