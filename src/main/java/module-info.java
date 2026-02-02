module com.mycompany.mavennetwork {
    requires javafx.controls;
    
    opens com.mycompany.mavennetwork.UI;
    exports com.mycompany.mavennetwork;
}
