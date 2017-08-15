/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personal;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Martha
 */
public class Personal extends Application {
    
    public static Stage escenario;
    
    @Override
    public void start(Stage stage) throws Exception {
        escenario = stage;
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
       // stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("PERONAL EXCARBON");
        stage.centerOnScreen();
        Scene scene = new Scene(root);
        //  stage.setTitle("EXCARBON");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("LogoExcarbon2.png")));
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
       
    }
        
    }
    
