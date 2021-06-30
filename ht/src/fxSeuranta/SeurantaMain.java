package fxSeuranta;


import Seuranta.Seuranta;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/**
 * @author Konsta
 * @version 15.2.2021
 * Ohj2 harjoitustyö "Seuranta", maini
 */
public class SeurantaMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("PalautuminenGUIView.fxml"));
            final Pane root = ldr.load();
            final SeurantaGUIController seurantaCtrl = (SeurantaGUIController) ldr.getController();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("seuranta.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Seuranta");
            
            Seuranta seuranta = new Seuranta();
            
            seurantaCtrl.asetaSeuranta(seuranta);
            
            primaryStage.setResizable(false);
            primaryStage.show();
            seurantaCtrl.lueTiedosto("seuranta");
                

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args Ei k�yt�ss�
     */
    public static void main(String[] args) {
        launch(args);
    }
}