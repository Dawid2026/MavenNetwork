/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic;
import com.mycompany.mavennetwork.optimizers.Optimizer;
import com.mycompany.mavennetwork.layers.Layer;
import com.mycompany.mavennetwork.lossfunctions.LossFunctions;
import java.util.List;
import java.util.Random;
/**
 *
 * @author Dawid
 */
public class Trainer {
    private Model model;
    private final Optimizer optimizer;
    private final List<Layer> layers;
    private final LossFunctions lossfunction;
    
    public Trainer(Model model, Optimizer optimizer){
        this.model = model;
        this.optimizer = optimizer;
        this.layers = model.getLayers();      
        lossfunction = model.getLossFunction();
    }
    
    public void fit(double[][] X, double[][] Y, int epochs) {
        for (int e = 0; e < epochs; e++) {
            double totalLoss = 0;
            
            model.resetGradients();
            for (int i = 0; i < X.length; i++) {
                double[] yPred = model.forward(X[i]);
                totalLoss += model.loss(yPred, Y[i]);
                double[] grad = lossfunction.gradient(yPred, Y[i]); 
                model.backward(grad);
            }
            model.scaleGradients(1.0 / X.length); 
            optimizer.update(layers);
            
            System.out.println("Epoch " + e + " loss: " + totalLoss / X.length);
        }
    }
    
    public void fit_partial(double[][] X, double[][] Y, int epochs, int batchSize) {
        Random rand = new Random();

        for (int e = 0; e < epochs; e++) {
            double totalLoss = 0;

            int[] indices = new int[X.length];
            for (int i = 0; i < X.length; i++) indices[i] = i;

            for (int i = X.length - 1; i > 0; i--) {
                int j = rand.nextInt(i + 1);
                int tmp = indices[i];
                indices[i] = indices[j];
                indices[j] = tmp;
            }

            for (int start = 0; start < X.length; start += batchSize) {
                int end = Math.min(start + batchSize, X.length);

                model.resetGradients();

                for (int i = start; i < end; i++) {
                    int id = indices[i];
                    double[] yPred = model.forward(X[id]);
                    totalLoss += model.loss(yPred, Y[id]);
                    double[] grad = lossfunction.gradient(yPred, Y[id]);
                    model.backward(grad);
                }
                
                model.scaleGradients(1.0 / (end - start));
                optimizer.update(model.getLayers());
            }

            System.out.println("Epoch " + e + " loss: " + totalLoss / X.length);
        }
    }
}
