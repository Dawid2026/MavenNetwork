/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavennetwork.UI;
import javafx.scene.control.*;
import javafx.scene.layout.*;


/**
 *
 * @author Dawid
 */
public class LogPanel extends VBox{
    public TextArea logArea;
     
    public LogPanel(){
        logArea = new TextArea();
        logArea.setEditable(false);
        logArea.setWrapText(true);
        getChildren().add(logArea);
    }  
    
    public void log(String message) {
        logArea.appendText(message + "\n");
    }
}
