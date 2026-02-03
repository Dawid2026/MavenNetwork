
package com.mycompany.mavennetwork.UI;
import javafx.scene.control.*;
import javafx.scene.layout.*;


/**
 * Class that defiends all of the LogPanel.
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
