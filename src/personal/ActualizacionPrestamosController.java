/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personal;

import DAO.Persona;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import modelo.Prestamo;
import static personal.Personal.escenario;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ActualizacionPrestamosController implements Initializable {

    @FXML
    private TextField idCedula;
    @FXML
    private TextField idValor;
    @FXML
    private TextField idCuota;
    String a, b;
    int c1;
    int c2;
    LocalDate xx;
    @FXML
    private Button idCancelar;
    @FXML
    private DatePicker fechaPrestamos;
    @FXML
    private Button idCalcular1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void onCancelar(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("personal/Inicio.fxml"));
            Scene scene = new Scene(root);
            escenario.setScene(scene);
        } catch (IOException ex) {
            System.out.println("ERROR (IO): " + ex);
        }
    }

    @FXML
    private void onCalcul(ActionEvent event) {
        if (idCedula.getText().isEmpty() || idValor.getText().isEmpty() || idCuota.getText().isEmpty() || idCedula.getText().length() > 10) {
            if (idCedula.getText().length() > 11) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("ERROR");
                alert.setHeaderText("Atención");
                alert.setContentText("La cedula no puede tener mas de 11 diguitos");
                alert.showAndWait();
                idCedula.setFocusTraversable(true);
            } else if (idCedula.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("ERROR");
                alert.setHeaderText("Atención");
                alert.setContentText("El campo cedula no puede estar vacio");
                alert.showAndWait();
                idCedula.setFocusTraversable(true);
            } else if (idValor.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("ERROR");
                alert.setHeaderText("Atención");
                alert.setContentText("El campo valor no puede estar vacio");
                alert.showAndWait();
                idValor.setFocusTraversable(true);
            } else if (idCuota.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("ERROR");
                alert.setHeaderText("Atención");
                alert.setContentText("El campo cuota no puede estar vacio");
                alert.showAndWait();
                idCuota.setFocusTraversable(true);
            }
        } else {
            Persona dao = new Persona();
            Prestamo min = new Prestamo();
            a = idValor.getText();
            b = idCuota.getText();
            c1 = Integer.parseInt(a);
            c2 = Integer.parseInt(b);
            String fechaInicio = "" + fechaPrestamos.getValue();
            min.setCedula1(idCedula.getText());
            min.setFecha_(fechaInicio);
            min.setValor(c1);
            min.setSaldo(c2);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar Prestamo");
            alert.setContentText("Estas seguro de realizar este prestamo?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                int res = dao.busquedaCedula(idCedula.getText());
                if (res > 0) {
                    int inse = dao.generarPrestamo(min);
                    if (inse == 0) {
                        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                        alerta.setTitle("Realizado");
                        alerta.setContentText("Se Actualizo el prestamo");
                        alerta.showAndWait();
                        idCedula.setText("");
                        fechaPrestamos.setValue(xx);
                        idValor.setText("");
                        idCuota.setText("");
                    }
                } else {
                    Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                    alerta.setTitle("Error");
                    alerta.setHeaderText("Trabajador no registado");
                    alerta.setContentText("O prestamo activo");
                    alerta.showAndWait();
                }
            } else {

                idCedula.setText("");
                fechaPrestamos.setValue(xx);
                idValor.setText("");
                idCuota.setText("");
            }

        }

    }
}
