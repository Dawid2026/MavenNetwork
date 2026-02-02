package com.mycompany.mavennetwork.UI;

/**
 *
 * @author Dawid
 */

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;

public class ControlPanel extends VBox {
    
    public Label titleLabel;

    public Button loadTrainBtn;
    public Button loadTestBtn;
    public Button addLayerBtn;
    public Button addOutputLayer;
    public Button compileBtn;
    public Button trainBtn;
    public Button testBtn;
    public Button resetBtn;

    public TextField inputNeurons;
    public TextField outputNeurons;
    public TextField learningRate;
    public TextField epochsField;
    public TextField batchField;
    public ComboBox<String> activationBox;
    
    public ControlPanel() {
        setSpacing(10);
        setPadding(new Insets(10));
        setPrefWidth(300);
        
        titleLabel = new Label("Neural Network Trainer");
        titleLabel.getStyleClass().add("title-label");

        loadTrainBtn = new Button("Load Training Data");
        loadTestBtn = new Button("Load Testing Data");

        inputNeurons = new TextField();
        inputNeurons.setPromptText("Input neurons");

        outputNeurons = new TextField();
        outputNeurons.setPromptText("Number of Neurons");

        activationBox = new ComboBox<>();
        activationBox.getItems().addAll("Relu","LeakyRelu", "Sigmoid", "null");
        activationBox.setPromptText("Activation");
        
        addLayerBtn = new Button("Add Layer");
        addOutputLayer = new Button("Add Output Layer");
        
        learningRate = new TextField();
        learningRate.setPromptText("Learning Rate");
        
        compileBtn = new Button("Compile Model");

        epochsField = new TextField();
        epochsField.setPromptText("Epochs");

        batchField = new TextField();
        batchField.setPromptText("Batch Size");

        trainBtn = new Button("Train Model");
        testBtn = new Button("Test Model");
        resetBtn = new Button("Reset Model");
        
        loadTestBtn.setDisable(true);
        addLayerBtn.setDisable(true);
        addOutputLayer.setDisable(true);
        compileBtn.setDisable(true);
        trainBtn.setDisable(true);
        testBtn.setDisable(true);
        resetBtn.setDisable(true);

        getChildren().addAll(
                titleLabel,
                loadTrainBtn, loadTestBtn,
                new Label("Add Layer"),
                outputNeurons, activationBox,
                addLayerBtn, addOutputLayer,
                compileBtn,
                new Label("Train"),
                learningRate,epochsField, batchField,
                trainBtn,
                testBtn,
                resetBtn
        );
        
        
    }
}

