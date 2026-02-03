package com.mycompany.mavennetwork;

import javafx.application.Application;
import com.mycompany.mavennetwork.UI.MainUI;
import javafx.stage.Stage;


/**
 * Main class of this aplication 
 * 
 * @author Dawid
 */
public class App extends Application {
    
    private MainUI ui;
    
    @Override
    public void start(Stage stage) {
        ui = new MainUI(stage);
    }
    
    /**
     * Main function
     * @param args 
     */
    public static void main(String[] args) {
        launch();
    }
}

