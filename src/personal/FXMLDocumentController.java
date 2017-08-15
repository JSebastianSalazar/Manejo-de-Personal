/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personal;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import static personal.Personal.escenario;

/**
 *
 * @author Martha
 */
public class FXMLDocumentController implements Initializable {

    private Label label;
    @FXML
    private Button btnIngresar;
    @FXML
    private PasswordField txtClave;
    private int intentos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        intentos = 0;
    }

    @FXML
    private void onIngresar(ActionEvent event) {

        if (!txtClave.getText().isEmpty()) {

            if (txtClave.getText().equals("marEX")) {

                try {
                    Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("personal/Inicio.fxml"));
                    Scene scene = new Scene(root);
                    escenario.setScene(scene);
                } catch (IOException ex) {
                    System.out.println("ERRORa (IO): " + ex);
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Error al validar la clave");
                alert.setContentText("Debes ingresar la clave adecuada");
                alert.showAndWait();
                intentos++;
                txtClave.setText("");
            }
            if (intentos > 2) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informacion");
                alert.setHeaderText("");
                alert.setContentText("Excediste el numero de intentos");
                alert.showAndWait();
                System.exit(1);
                txtClave.setText("");
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Campo vacio");
            alert.setContentText("Ingresa la clave adecuada");
            alert.showAndWait();
            txtClave.setText("");
        }

    }

}
