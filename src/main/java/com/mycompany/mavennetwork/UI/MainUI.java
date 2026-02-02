/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavennetwork.UI;
import Logic.Controller;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Dawid
 */
public class MainUI {

    public ControlPanel controls;
    public LogPanel logPanel;
    public MNISTCanvas mnist;

    private Controller controller;

    public MainUI(Stage stage) {

        controls = new ControlPanel();
        logPanel = new LogPanel();
        controller = new Controller(logPanel);
        mnist = new MNISTCanvas();

        BorderPane root = new BorderPane();
        root.setLeft(controls);
        root.setRight(logPanel);
        root.setCenter(mnist);

        hookEvents();

        Scene scene = new Scene(root, 1000, 700);
        
        applyStyles(controls, logPanel, mnist);
        

        stage.setScene(scene);
        stage.show();
    }

    private void hookEvents() {

        controls.loadTrainBtn.setOnAction(e -> {
            controller.loadTrainingData();

            controls.loadTestBtn.setDisable(false);
            controls.addLayerBtn.setDisable(false);
            controls.addOutputLayer.setDisable(false);
            controls.resetBtn.setDisable(false);
            controls.loadTrainBtn.setDisable(true);
            controls.inputNeurons.setText("784");
            
        });

        controls.loadTestBtn.setOnAction(e -> {
                controller.loadTestingData();
                controls.testBtn.setDisable(false);
                controls.loadTestBtn.setDisable(true);
        });

        controls.addLayerBtn.setOnAction(e -> {
            controller.addDenseLayer(
                    Integer.parseInt(controls.inputNeurons.getText()),
                    Integer.parseInt(controls.outputNeurons.getText()),
                    controls.activationBox.getValue()
            );
            controls.inputNeurons.setText(controls.outputNeurons.getText());
        });

        controls.addOutputLayer.setOnAction(e -> {
                controller.addOutputLayer(controls.activationBox.getValue());
                controls.compileBtn.setDisable(false);
                controls.addLayerBtn.setDisable(true);
                controls.addOutputLayer.setDisable(true);
        });

        controls.compileBtn.setOnAction(e -> {
            controller.compile();
            controls.trainBtn.setDisable(false);
            controls.addLayerBtn.setDisable(true);
            controls.addOutputLayer.setDisable(true);
            controls.compileBtn.setDisable(true);
        });

        controls.trainBtn.setOnAction(e ->
            controller.train(
                Integer.parseInt(controls.epochsField.getText()),
                Integer.parseInt(controls.batchField.getText()),
                Double.parseDouble(controls.learningRate.getText())
             )
        );

        controls.testBtn.setOnAction(e ->
                controller.test()
        );

        controls.resetBtn.setOnAction(e -> {
            controller.reset();
            controls.inputNeurons.setText("784");
            controls.addLayerBtn.setDisable(false);
            controls.addOutputLayer.setDisable(false);
        });
        
        mnist.predict.setOnAction(e ->{
            controller.testmnist(mnist.getMNISTImageFlattened());
            mnist.clearCanvas();
        });
    }
    
    private void applyStyles(ControlPanel controlPanel, LogPanel logPanel, MNISTCanvas mnistCanvas) {
        controlPanel.setStyle("-fx-background-color: #f4f4f4;");
        logPanel.setStyle("-fx-background-color: #eaeaea; -fx-padding: 10;");

        controlPanel.titleLabel.setStyle(
                "-fx-font-size: 20px; " +
                "-fx-font-weight: bold; " +
                "-fx-text-fill: #333;"
        );

        controlPanel.loadTrainBtn.setStyle(buttonStyle());
        controlPanel.loadTestBtn.setStyle(buttonStyle());
        controlPanel.addLayerBtn.setStyle(buttonStyle());
        controlPanel.addOutputLayer.setStyle(buttonStyle());
        controlPanel.compileBtn.setStyle(buttonStyle());
        controlPanel.trainBtn.setStyle(buttonStyle());
        controlPanel.testBtn.setStyle(buttonStyle());
        controlPanel.resetBtn.setStyle(buttonStyle());

        mnistCanvas.predict.setStyle(buttonStyle());
        mnistCanvas.getChildren().filtered(node -> node instanceof javafx.scene.control.Button)
                .forEach(node -> node.setStyle(buttonStyle()));

        controlPanel.inputNeurons.setStyle(inputStyle());
        controlPanel.outputNeurons.setStyle(inputStyle());
        controlPanel.learningRate.setStyle(inputStyle());
        controlPanel.epochsField.setStyle(inputStyle());
        controlPanel.batchField.setStyle(inputStyle());

        controlPanel.activationBox.setStyle(
                "-fx-background-color: white; " +
                "-fx-border-color: #cccccc; " +
                "-fx-border-radius: 3;"
        );
        
        logPanel.logArea.setStyle(
                "-fx-background-color: white; " +
                "-fx-border-color: #cccccc; " +
                "-fx-border-radius: 3; " +
                "-fx-padding: 5;" +
                "-fx-font-size: 18px;" +    
                "-fx-font-family: 'Consolas';"
        );
        
        
    
        logPanel.logArea.setPrefHeight(200);

    }

    private String buttonStyle() {
        return "-fx-background-color: #4CAF50; " +
               "-fx-text-fill: white; " +
               "-fx-padding: 8 16 8 16; " +
               "-fx-background-radius: 5;" +
               "-fx-font-weight: bold;";
    }

    private String inputStyle() {
        return "-fx-background-color: white; " +
               "-fx-border-color: #cccccc; " +
               "-fx-border-radius: 3; " +
               "-fx-padding: 5;";
    }
}

