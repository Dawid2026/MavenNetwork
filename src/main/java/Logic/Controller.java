/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic;
import com.mycompany.mavennetwork.UI.LogPanel;
import com.mycompany.mavennetwork.activationfunction.*;
import com.mycompany.mavennetwork.layers.*;
import com.mycompany.mavennetwork.lossfunctions.*;
import com.mycompany.mavennetwork.optimizers.*;
import com.mycompany.mavennetwork.data.DataBase;

/**
 *
 * @author Dawid
 */
public class Controller {
    private final Model model;
    private final DataBase data;
    private final LogPanel log;

    public Controller(LogPanel logPanel) {
        this.log = logPanel;
        this.model = new Model();
        this.data = new DataBase();
    }
    public void loadTrainingData() {
        data.loadTrainingData("mnist_train.csv");
        model.clearLayers();
        model.add(new InputLayer(data.getX()[0].length, null));

        log.log("Training data loaded");
        log.log("Input layer added: " + data.getX()[0].length);
    }

    public void loadTestingData() {
        data.loadTestingData("mnist_test.csv");
        log.log("Testing data loaded");
    }

    public void addDenseLayer(int in, int out, String activation) {
        if ("Relu".equalsIgnoreCase(activation)) {
            model.add(new DenseLayer(in, out, new Relu()));
        } else if ("Sigmoid".equalsIgnoreCase(activation)) {
            model.add(new DenseLayer(in, out, new Sigmoid()));
        } else if ("LeakyRelu".equalsIgnoreCase(activation)) {
            model.add(new DenseLayer(in, out, new LeakyRelu()));
        } else {
            model.add(new DenseLayer(in, out, null));
        }

        log.log("Layer added: " + in + " → " + out + " (" + activation + ")");
    }

    public void addOutputLayer(String activation) {
        Layer last = model.getLayers().get(model.getLayers().size() - 1);
        int inputSize = ((TrainableLayers) last).getOutputSize();

        model.add(new OutputLayer(inputSize, 10, null));
        log.log("Output layer added (10 classes)");
    }

    public void compile() {
        model.compile(new SoftmaxCrossEntropy());
        log.log("Model compiled");
    }

    public void train(int epochs, int batchSize, double learningRate) {
        Trainer trainer = new Trainer(model, new SGD(learningRate));
        trainer.fit_partial(data.getX(), data.getY(), epochs, batchSize);
        log.log("Training finished");
    }

    public void test() {
        double acc = model.test(data.getXTest(), data.getLabelsTest());
        log.log("Accuracy: " + acc);
    }
    
    public void testmnist(double[] X) {
        int prediction = helperMathFunctions.argMax(helperMathFunctions.Softmax(model.forward(X)));
        log.log("Predicted Number: " + prediction);
    }

    public void reset() {
        model.clearLayers();
        model.add(new InputLayer(data.getX()[0].length, null));
        log.log("Model reset");
        log.log("Input layer added: " + data.getX()[0].length);
    }
}
