package com.mycompany.mavennetwork;

import javafx.application.Application;
import com.mycompany.mavennetwork.UI.MainUI;
import javafx.stage.Stage;



public class App extends Application {
    
    private MainUI ui;
    
    @Override
    public void start(Stage stage) {
        ui = new MainUI(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}

