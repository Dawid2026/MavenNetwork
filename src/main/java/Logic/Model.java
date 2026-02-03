package Logic;
import com.mycompany.mavennetwork.layers.TrainableLayers;
import com.mycompany.mavennetwork.layers.Layer;
import com.mycompany.mavennetwork.lossfunctions.LossFunctions;
import java.util.ArrayList;
import java.util.List;
/**
 * Class Model defiends network model
 * 
 * @author Dawid
 */
public class Model {
    private ArrayList<Layer> layers = new ArrayList<>();
    private LossFunctions lossFunction;
    /**
     * Initilize lossfunction
     * @param lossFunction 
     */
    public void compile(LossFunctions lossFunction) {
        this.lossFunction = lossFunction;
    }
    
    /**
     * Add layer into model
     * @param layer 
     */
    public void add(Layer layer){
        layers.add(layer);
    }
    
    public List<Layer> getLayers() {
        return layers;
    }
    
    public LossFunctions getLossFunction() {
        return lossFunction;
    }
    
    /**
     * Calculate loss of the model
     * @param yPred Model predictions
     * @param yTrue Actual data
     * @return loss
     */
    public double loss(double[] yPred, double[] yTrue) {
        return lossFunction.loss(yPred, yTrue);
    }
    /**
     * Resets gradients in this model
     */
    public void resetGradients(){
        for(Layer l : layers){
            if(l instanceof TrainableLayers d){
                d.resetGradient();
            }
        }
    }
    
    /**
     * Resets array of layers in the network
     */
    public void clearLayers(){
        layers.clear();
    }
    /**
     * SCales gradients for minibach
     * @param scale 
     */
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
    /**
     * Calculate forward pass of each layer
     * @param input
     * @return model prediction
     */
    public double[] forward(double[] input){
        double[] output = input;
        for (Layer layer : layers) {
            output = layer.forward(output);
        }
        return output;
    }
    /**
     * Pass back gradient into nexr layers
     * @param gradOutput 
     */
    public void backward(double[] gradOutput) {
        double[] grad = gradOutput;

        for (int i = layers.size() - 1; i >= 0; i--) {
            grad = layers.get(i).backward(grad);
        }

    }
    /**
     * Calculates model accuracy based on Testing data
     * @param XTest Testing pixels
     * @param labelsTest Testing labales
     * @return accuracy
     */
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
