package personal;

import DAO.Persona;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.geometry.Insets;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;
import javax.swing.JOptionPane;
import modelo.InactivoPersonal;
import modelo.Prestamo;
import modelo.minero;
import static personal.Personal.escenario;

public class InicioController implements Initializable {

    @FXML
    private Label idFecha;
    int day, year, month;
    @FXML
    private TextField txtCedulaR;
    @FXML
    private TextField txtNombreR;
    @FXML
    private TextField txtSaludR;
    @FXML
    private TextField txtPensionR;
    @FXML
    private TextField txtArlR;
    @FXML
    private TextField txtCompensacionR;
    @FXML
    private TextField txtCamisaR;
    @FXML
    private TextField txtPantalonR;
    @FXML
    private TextField txtBotasR;
    @FXML
    private ComboBox<String> comboEmpresaR;
    @FXML
    private ComboBox<String> comboOcupacionR;
    @FXML
    private DatePicker dateFechaR;
    @FXML
    private Button btnRegistarPersonal;
    @FXML
    private TextField txtCedulaCotizanteR;
    @FXML
    private TextField txtCedulaBeneficiario;
    @FXML
    private ComboBox<String> comboTipoBeneficiario;
    @FXML
    private TextField txtNombreBeneficiario;
    @FXML
    private TextField txtParentescoBeneficiario;
    @FXML
    private Button btnRegistrarBeneficiario;
    @FXML
    private TextField txtSalarioR;
    LocalDate xx;

    @FXML
    private TableColumn cedulaTable;
    @FXML
    private TableColumn nombreTable;
    @FXML
    private TableColumn SalarioTable;
    @FXML
    private TableColumn empresaTable;

    @FXML
    private TableColumn saludTable;
    @FXML
    private TableColumn fechaInicioTable;
    @FXML
    private TableColumn arlTable;
    @FXML
    private TableColumn pensionTable;
    @FXML
    private TableColumn cargoTable;
    ObservableList<minero> tbl;
    ObservableList<InactivoPersonal> tbl2;
    ObservableList<Prestamo> tbl3;
    @FXML
    private TableView<minero> TablaPersonalActivo;
    public Stage escenario3;
    @FXML
    private TextField txtCedulaInactividad;
    @FXML
    private DatePicker txtFechaInactividad;
    @FXML
    private TableView<InactivoPersonal> TablaInactividad;
    @FXML
    private TableColumn tblCedulaINactivo;
    @FXML
    private TableColumn tblDiasInactivo;
    @FXML
    private TextField txtCedulaActivar;
    @FXML
    private DatePicker dateFechaActivar;
    @FXML
    private TableColumn tblCamisaActivoo;
    @FXML
    private TableColumn tblPantalonActivo;
    @FXML
    private TableColumn tblCalzadoActivo;
    @FXML
    private Button btnActivos;
    @FXML
    private TableColumn tblNombreInactivo;
    @FXML
    private TableColumn tblSalarioInactivo;
    @FXML
    private TableColumn tblCentroInactivo;
    @FXML
    private TableColumn tblSaludInactivo;
    @FXML
    private TableColumn tblFechaIngresoInactivo;
    @FXML
    private TableColumn tblFechaSalidaInactivo;
    @FXML
    private TableColumn tblCargoInactivo;
    @FXML
    private TableColumn tblCamisaInactivo;
    @FXML
    private TableColumn tblPantalonInactivo;
    @FXML
    private TableColumn tblCalzadoInactivo;
    @FXML
    private TableView<Prestamo> tblPrestamos;
    @FXML
    private TableColumn tblCedulaPrestamo;
    @FXML
    private TableColumn tblNombreReporte;
    @FXML
    private TableColumn tblValorReporte;
    @FXML
    private TableColumn tblCuotasPrestamo;
    @FXML
    private TableColumn tblSaldoCuotasPrestamo;
    @FXML
    private TableColumn tblAbonoPrestamos;
    @FXML
    private TableColumn tblPagosPrestamos;
    @FXML
    private TableColumn tblFechaPrestamos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        datosTablaPrestamos();
        fecha();
        comboEmpresaR.getItems().addAll("Mina", "Oficina", "Primavera");
        comboOcupacionR.getItems().addAll("Administrador", "Aux Contable", "Conductores", "Gerente", "Mensajero", "Minero", "Practicante", "Recursos Humanos", "Repre Legal");
        comboTipoBeneficiario.getItems().addAll("Cedula", "Registro Civil", "Tarjeta Id");
        datosTablaActivosPersonal();
        datosTablaInactivosPersonal();
        tablaInactivo();

    }

    public void tablaInactivo() {
        cedulaTable.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        nombreTable.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        SalarioTable.setCellValueFactory(new PropertyValueFactory<>("salario"));
        empresaTable.setCellValueFactory(new PropertyValueFactory<>("empresa"));
        tblPantalonActivo.setCellValueFactory(new PropertyValueFactory<>("panta"));
        saludTable.setCellValueFactory(new PropertyValueFactory<>("salud"));
        fechaInicioTable.setCellValueFactory(new PropertyValueFactory<>("fecha_Na"));
        arlTable.setCellValueFactory(new PropertyValueFactory<>("arl"));
        pensionTable.setCellValueFactory(new PropertyValueFactory<>("pension"));
        cargoTable.setCellValueFactory(new PropertyValueFactory<>("cargo"));
        tblCalzadoActivo.setCellValueFactory(new PropertyValueFactory<>("botas"));
        tblCamisaActivoo.setCellValueFactory(new PropertyValueFactory<>("camisa"));
        TablaPersonalActivo.setItems(tbl);
        tblCedulaINactivo.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        tblNombreInactivo.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tblSalarioInactivo.setCellValueFactory(new PropertyValueFactory<>("salario"));
        tblCentroInactivo.setCellValueFactory(new PropertyValueFactory<>("empresa"));
        tblSaludInactivo.setCellValueFactory(new PropertyValueFactory<>("salud"));
        tblFechaIngresoInactivo.setCellValueFactory(new PropertyValueFactory<>("fecha_ingreso"));
        tblFechaSalidaInactivo.setCellValueFactory(new PropertyValueFactory<>("fecha_salida"));
        tblDiasInactivo.setCellValueFactory(new PropertyValueFactory<>("dias"));
        tblCargoInactivo.setCellValueFactory(new PropertyValueFactory<>("cargo"));
        tblCamisaInactivo.setCellValueFactory(new PropertyValueFactory<>("camisa"));
        tblPantalonInactivo.setCellValueFactory(new PropertyValueFactory<>("pantalon"));
        tblCalzadoInactivo.setCellValueFactory(new PropertyValueFactory<>("botas"));
        TablaInactividad.setItems(tbl2);
        tblCedulaPrestamo.setCellValueFactory(new PropertyValueFactory<>("cedula1"));
        tblNombreReporte.setCellValueFactory(new PropertyValueFactory<>("nombre1"));
        tblValorReporte.setCellValueFactory(new PropertyValueFactory<>("valor"));
        tblCuotasPrestamo.setCellValueFactory(new PropertyValueFactory<>("cuotas"));
        tblSaldoCuotasPrestamo.setCellValueFactory(new PropertyValueFactory<>("saldo"));
        tblAbonoPrestamos.setCellValueFactory(new PropertyValueFactory<>("abono"));
        tblPagosPrestamos.setCellValueFactory(new PropertyValueFactory<>("pago"));
        tblFechaPrestamos.setCellValueFactory(new PropertyValueFactory<>("fecha_"));
        tblPrestamos.setItems(tbl3);
    }

    private void datosTablaPrestamos() {
        tbl3 = FXCollections.observableArrayList();
        Persona dao = new Persona();
        List<Prestamo> list = dao.listarPrestamos();
        if (list != null || !list.isEmpty()) {
            tbl3.addAll(list);
        } else {
            JOptionPane.showMessageDialog(null, "No hay datos");
        }

    }

    private void datosTablaActivosPersonal() {
        tbl = FXCollections.observableArrayList();
        Persona dao = new Persona();
        minero min;
        List<minero> list = dao.minerosActivos();
        if (list != null || !list.isEmpty()) {
            tbl.addAll(list);
        } else {
            JOptionPane.showMessageDialog(null, "No hay datos");
        }

    }

    private void datosTablaInactivosPersonal() {
        tbl2 = FXCollections.observableArrayList();
        Persona dao = new Persona();
        InactivoPersonal min;
        List<InactivoPersonal> list = dao.personalInactivo();
        if (list != null || !list.isEmpty()) {
            tbl2.addAll(list);
        } else {
            JOptionPane.showMessageDialog(null, "No hay datos");
        }

    }

    public void fecha() {
        Calendar calendar = Calendar.getInstance();
        day = calendar.get(calendar.DAY_OF_MONTH);
        year = calendar.get(calendar.YEAR);
        month = (calendar.get(Calendar.MONTH) + 1);
        idFecha.setText(day + "/" + month + "/" + year);
    }

    @FXML
    private void onRegistrarPersonal(ActionEvent event) {

        if (txtCedulaR.getText().isEmpty() || txtArlR.getText().isEmpty() || txtNombreR.getText().isEmpty() || txtSalarioR.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Campos Vacios");
            alert.setContentText("Ingresa los campos requeridos del formulario del trabajador");
            alert.showAndWait();

        } else {
            if (txtCedulaR.getText().length() > 11) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("ERROR");
                alert.setHeaderText("Atención");
                alert.setContentText("El campo cedula no puede tener mas de 11 diguitos");
                alert.showAndWait();

            } else if (txtNombreR.getText().length() > 200) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("ERROR");
                alert.setHeaderText("Atención");
                alert.setContentText("El campo  nombre no puede tener mas de 200 diguitos");
                alert.showAndWait();

            } else if (txtCamisaR.getText().length() > 2) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error");
                alert.setHeaderText("Atención");
                alert.setContentText("El campo talla  camisa no puede tener mas de 3 diguitos");
                alert.showAndWait();
            } else if (txtPantalonR.getText().length() > 2) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error");
                alert.setHeaderText("Atención");
                alert.setContentText("El campo talla  pantalon no puede tener mas de 3 diguitos");
                alert.showAndWait();
            } else if (txtBotasR.getText().length() > 2) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error");
                alert.setHeaderText("Atención");
                alert.setContentText("El campo  talla  botas no puede tener mas de 3 diguitos");
                alert.showAndWait();
            } else if (txtSaludR.getText().length() > 19) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error");
                alert.setHeaderText("Atención");
                alert.setContentText("El campo salud  no puede tener mas de 19 diguitos");
                alert.showAndWait();
            } else if (txtPensionR.getText().length() > 19) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error");
                alert.setHeaderText("Atención");
                alert.setContentText("El campo pension no puede tener mas de 19 diguitos");
                alert.showAndWait();
            } else if (txtArlR.getText().length() > 30) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error");
                alert.setHeaderText("Atención");
                alert.setContentText("El campo ARL no puede tener mas de 30 diguitos");
                alert.showAndWait();

            } else if (txtCompensacionR.getText().length() > 30) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("ERROR");
                alert.setHeaderText("Atención");
                alert.setContentText("El campo caja compensacion  no puede tener mas de 30 diguitos");
                alert.showAndWait();

            } else {
                String empresacombo = comboEmpresaR.getValue().toString();
                String ocupacioncombo = comboOcupacionR.getValue().toString();
                String fechaInicio = "" + dateFechaR.getValue();
                int salario = Integer.parseInt(txtSalarioR.getText());
                minero min = new minero();
                min.setCedula(txtCedulaR.getText());
                min.setNombre(txtNombreR.getText());
                min.setSalud(txtSaludR.getText());
                min.setArl(txtArlR.getText());
                min.setCompensa(txtCompensacionR.getText());
                min.setCamisa(txtCamisaR.getText());
                min.setPanta(txtPantalonR.getText());
                min.setBotas(txtBotasR.getText());
                min.setEmpresa(empresacombo);
                min.setCargo(ocupacioncombo);
                min.setFecha_Na(fechaInicio);
                min.setPension(txtPensionR.getText());
                min.setSalario(salario);
                Persona dao = new Persona();
                int a = dao.insertarMinero(min);
                if (a == 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Nuevo Usuario");
                    alert.setHeaderText("Se registro un nuevo trabajador");
                    alert.setContentText("Nombre:   " + txtNombreR.getText() + "    Cedula:   " + txtCedulaR.getText());
                    alert.showAndWait();

                    txtCedulaR.setText("");
                    txtNombreR.setText("");
                    txtCamisaR.setText("");
                    txtPantalonR.setText("");
                    txtBotasR.setText("");
                    txtSaludR.setText("");
                    txtPensionR.setText("");
                    txtArlR.setText("");
                    txtCompensacionR.setText("");
                    dateFechaR.setValue(xx);
                    txtSalarioR.setText("");

                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Error");
                    alert.setHeaderText("Se presento un error al registrar el trabajador");
                    alert.setContentText("Usuario con la cedula:" + txtCedulaR.getText() + " ya se encuentra registrado");
                    alert.showAndWait();

                }
            }
        }
    }

    @FXML
    private void onRegistrarBeneficiario(ActionEvent event) {

        if (txtCedulaCotizanteR.getText().isEmpty() || txtCedulaBeneficiario.getText().isEmpty() || txtNombreBeneficiario.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Campos Vacios");
            alert.setContentText("Debes ingresar todos los campos del beneficiario");
            alert.showAndWait();

        } else {
            if (txtCedulaCotizanteR.getText().length() > 11) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("ERROR");
                alert.setHeaderText("Atencion");
                alert.setContentText("El campo cedula cotizante no puede tener mas de 11 diguitos");
                alert.showAndWait();

            } else if (txtCedulaBeneficiario.getText().length() > 18) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error");
                alert.setHeaderText("Atencion");
                alert.setContentText("El campo documento beneficiario no puede  tener mas de 18 diguitos");
                alert.showAndWait();

            } else if (txtParentescoBeneficiario.getText().length() > 24) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error");
                alert.setHeaderText("Atencion");
                alert.setContentText("El parentesco  no puede tener mas de 24 diguitos");
                alert.showAndWait();

            } else if (txtNombreBeneficiario.getText().length() > 200) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error");
                alert.setHeaderText("Atencion");
                alert.setContentText("El nombre del beneficiario no puede tener mas de 200 diguitos");
                alert.showAndWait();

            } else {
                String tipo = comboTipoBeneficiario.getValue().toString();
                minero mina = new minero();
                mina.setCedula(txtCedulaCotizanteR.getText());
                mina.setCedulaBene(txtCedulaBeneficiario.getText());
                mina.setParente(txtParentescoBeneficiario.getText());
                mina.setTipo(tipo);
                mina.setNombreBene(txtNombreBeneficiario.getText());
                Persona per = new Persona();
                int A = per.insertarBene(mina);
                if (A > 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Nuevo Beneficiario");
                    alert.setHeaderText("Se registro un nuevo beneficiario");
                    alert.setContentText("Nombre:   " + txtNombreBeneficiario.getText() + "    Documento:   " + txtCedulaBeneficiario.getText());
                    alert.showAndWait();
                    txtNombreBeneficiario.setText("");
                    txtParentescoBeneficiario.setText("");
                    txtCedulaBeneficiario.setText("");
                    txtCedulaCotizanteR.setText("");

                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Error");
                    alert.setHeaderText("Se presento un error al registrar el beneficiario");
                    alert.setContentText("Benefciario con el documento : " + txtCedulaBeneficiario.getText() + "  ya se encuentra registrado");
                    alert.showAndWait();
                }
            }
        }

    }

    @FXML
    private void onReporteTBL(ActionEvent event) throws Exception {
        Persona dao = new Persona();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Reporte ");
        alert.setHeaderText("Presiona click izquierdo en aceptar para generar el reporte");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Calendar calendar = Calendar.getInstance();
            day = calendar.get(calendar.DAY_OF_MONTH);
            year = calendar.get(calendar.YEAR);
            month = (calendar.get(Calendar.MONTH) + 1);
            Document documento = new Document();
            FileOutputStream ficheroPdf = new FileOutputStream("C:\\reportes\\ReportePersonal(" + year + month + day + ").pdf");
            PdfWriter.getInstance(documento, ficheroPdf).setInitialLeading(20);
            Image imagenes = Image.getInstance("C:\\Users\\User\\Documents\\Excarbon\\Personal\\src\\img\\LogoExcarbon.png");
            documento.open();
            imagenes.setAlignment(Element.ALIGN_TOP);
            imagenes.setAlignment(Element.ALIGN_RIGHT);
            imagenes.scaleToFit(150, 150);//agg imagen al documento
            documento.add(imagenes);
            documento.add(new Paragraph("INFORME",
                    FontFactory.getFont("arial", // fuente
                            25, // tamaño
                            Font.ITALIC, // estilo
                            BaseColor.GRAY)));
            documento.add(new Phrase(Chunk.NEWLINE));
            List<minero> list = dao.minerosActivos();
            PdfPTable tabla = new PdfPTable(6);
            PdfPCell celda1 = new PdfPCell(new Paragraph("Cedula", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
            PdfPCell celda2 = new PdfPCell(new Paragraph("Nombre ", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
            PdfPCell celda3 = new PdfPCell(new Paragraph("Cargo ", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
            PdfPCell celda4 = new PdfPCell(new Paragraph("Centro de costos", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
            PdfPCell celda5 = new PdfPCell(new Paragraph("Fecha Ingreso", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
            PdfPCell celda6 = new PdfPCell(new Paragraph("Salario", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
            tabla.addCell(celda1);
            tabla.addCell(celda2);
            tabla.addCell(celda3);
            tabla.addCell(celda4);
            tabla.addCell(celda5);
            tabla.addCell(celda6);
            for (minero min : list) {
                tabla.addCell(min.getCedula());
                tabla.addCell(min.getNombre());
                tabla.addCell(min.getCargo());
                tabla.addCell(min.getEmpresa());
                tabla.addCell(min.getFecha_Na());
                tabla.addCell("" + min.getSalario());

            }
            Chunk link = new Chunk(year + "/" + month + "/" + day);
            link.setCharacterSpacing(5);
            link.setHorizontalScaling(Element.ALIGN_RIGHT);
            documento.add(tabla);

            documento.add(new Phrase(Chunk.NEWLINE));
            documento.add(new Phrase(Chunk.NEWLINE));
            documento.add(link);
            documento.close();
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Reporte");
            alerta.setHeaderText("Se realizo reporte correctamente en la siguiente ruta.");
            alerta.setContentText("C:\\reportes\\ReportePersonal.pdf(" + year + month + day + ").pdf");
            alerta.showAndWait();
        } else {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Reporte");
            alerta.setHeaderText("No se genero ningun reporte.");
            alerta.showAndWait();
        }

    }

    @FXML
    private void onActivar(ActionEvent event
    ) {

        if (txtCedulaActivar.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Campo cedula activar trabajador");
            alert.setContentText("No puede estar en limpio");
            alert.showAndWait();
        } else {
            if (txtCedulaActivar.getText().length() > 12) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error");
                alert.setHeaderText("Campo cedula activar trabajador");
                alert.setContentText(" No puede tener mas de 12 diguitos");
                alert.showAndWait();
            } else {
                String fechaInicio = "" + dateFechaActivar.getValue();
                minero min = new minero();
                Persona per = new Persona();
                min.setCedula(txtCedulaActivar.getText());
                min.setFecha_Na(fechaInicio);
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmar");
                alert.setHeaderText("De verdad deseas realizar esta accion ?");
                alert.setContentText("Dale click en  aceptar para confirmar");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {

                    int traba = per.activarTrabajador(min);
                    if (traba > 0) {
                        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                        alerta.setTitle("Activar");
                        alerta.setHeaderText("Se activo  el trabajador ");
                        alerta.setContentText("cedula " + txtCedulaActivar.getText());
                        alerta.showAndWait();
                        dateFechaActivar.setValue(xx);
                        txtCedulaActivar.setText("");
                    } else {
                        Alert alerta = new Alert(Alert.AlertType.WARNING);
                        alerta.setTitle("Error");
                        alerta.setHeaderText("El trabajador con cedula  " + txtCedulaActivar.getText());
                        alerta.setContentText("Se encuentra activo o  no esta registrado");
                        alerta.showAndWait();
                        dateFechaActivar.setValue(xx);
                        txtCedulaActivar.setText("");
                    }

                } else {
                    Alert alerta = new Alert(Alert.AlertType.WARNING);
                    alerta.setTitle("Error");
                    alerta.setHeaderText("El trabajador con la cedula  " + txtCedulaActivar.getText());
                    alerta.setContentText("No se activo, cancelado por el usuario. ");
                    alerta.showAndWait();
                    dateFechaActivar.setValue(xx);
                    txtCedulaActivar.setText("");
                }

            }
        }

    }

    @FXML
    private void onAseptarInactividad(ActionEvent event) {
        if (txtCedulaInactividad.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Campo cedula eliminar trabajador");
            alert.setContentText("No puede estar en limpio");
            alert.showAndWait();
        } else {
            if (txtCedulaInactividad.getText().length() > 12) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error");
                alert.setHeaderText("Campo cedula eliminar trabajador ");
                alert.setContentText(" No tener mas de 12 diguitos");
                alert.showAndWait();
            } else {
                String fechaInicio = "" + txtFechaInactividad.getValue();
                minero min = new minero();
                Persona per = new Persona();
                min.setCedula(txtCedulaInactividad.getText());
                min.setFecha_Na(fechaInicio);

                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmar");
                alert.setHeaderText("De verdad deseas realizar esta accion ?");
                alert.setContentText("Dale click en  aceptar para confirmar");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    int traba = per.EliminarMinero(min);
                    if (traba > 0) {
                        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                        alerta.setTitle("Eliminar");
                        alerta.setHeaderText("Se Elimino  el trabajador");
                        alerta.setContentText("cedula  " + txtCedulaInactividad.getText());
                        alerta.showAndWait();
                        txtFechaInactividad.setValue(xx);
                        txtCedulaInactividad.setText("");

                    } else {
                        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                        alerta.setTitle("Error");
                        alerta.setHeaderText("El trabajador con cedula   " + txtCedulaInactividad.getText());
                        alerta.setContentText("Se encuentra inactivo o no esta registrado");
                        alerta.showAndWait();
                        txtFechaInactividad.setValue(xx);
                        txtCedulaInactividad.setText("");
                    }

                } else {
                    Alert alerta = new Alert(Alert.AlertType.WARNING);
                    alerta.setTitle("Error");
                    alerta.setHeaderText("El trabajador con cedula " + txtCedulaInactividad.getText());
                    alerta.setContentText("No se elimino, cancelado por el usuario. ");
                    alerta.showAndWait();
                    txtFechaInactividad.setValue(xx);
                    txtCedulaInactividad.setText("");
                }

            }
        }

    }

    @FXML
    private void onBuscarTBLActivo(ActionEvent event) throws Exception {
        Persona dao = new Persona();
        TextInputDialog dialog = new TextInputDialog("CEDULA");
        dialog.setTitle("Reporte");
        dialog.setHeaderText("INGRESA LA CEDULA DEL EMPLEADO");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            Persona per = new Persona();
            String pd = result.get();
            int a = 0;
            a = per.busquedaCedula(pd);
            if (a > 0) {
                Calendar calendar = Calendar.getInstance();
                day = calendar.get(calendar.DAY_OF_MONTH);
                year = calendar.get(calendar.YEAR);
                month = (calendar.get(Calendar.MONTH) + 1);
                Document documento = new Document();
                FileOutputStream ficheroPdf = new FileOutputStream("C:\\reportes\\" + pd + "(individual)(" + year + month + day + ").pdf");
                PdfWriter.getInstance(documento, ficheroPdf).setInitialLeading(20);
                Image imagenes = Image.getInstance("C:\\Users\\User\\Documents\\Excarbon\\Personal\\src\\img\\LogoExcarbon.png");
                documento.open();
                imagenes.setAlignment(Element.ALIGN_TOP);
                imagenes.setAlignment(Element.ALIGN_RIGHT);
                imagenes.scaleToFit(150, 150);//agg imagen al documento
                documento.add(imagenes);
                documento.add(new Paragraph("INFORME INDIVIDUAL",
                        FontFactory.getFont("arial", // fuente
                                25, // tamaño
                                Font.ITALIC, // estilo
                                BaseColor.GRAY)));
                documento.add(new Phrase(Chunk.NEWLINE));
                List<minero> list = dao.busquedaActivoXcedula(pd);
                for (minero min : list) {
                    documento.add(new Paragraph("CEDULA :" + min.getCedula()));
                    documento.add(new Phrase(Chunk.NEWLINE));
                    documento.add(new Paragraph("NOMBRE :" + min.getNombre()));
                    documento.add(new Phrase(Chunk.NEWLINE));
                    documento.add(new Paragraph("CARGO :" + min.getCargo()));
                    documento.add(new Phrase(Chunk.NEWLINE));
                    documento.add(new Paragraph("Centro de costos:" + min.getEmpresa()));
                    documento.add(new Phrase(Chunk.NEWLINE));
                    documento.add(new Paragraph("FECHA DE INGRESO :" + min.getFecha_Na()));
                    documento.add(new Phrase(Chunk.NEWLINE));
                    documento.add(new Paragraph("SALARIO :" + min.getSalario()));
                    documento.add(new Phrase(Chunk.NEWLINE));
                    documento.add(new Paragraph("SALUD :" + min.getSalud()));
                    documento.add(new Phrase(Chunk.NEWLINE));
                    documento.add(new Paragraph("PENSIÓN :" + min.getPension()));
                    documento.add(new Phrase(Chunk.NEWLINE));
                    documento.add(new Paragraph("ARL :" + min.getArl()));
                    documento.add(new Phrase(Chunk.NEWLINE));
                    documento.add(new Paragraph("CAJA COMPENSACIÓN :" + min.getCompensa()));
                    documento.add(new Phrase(Chunk.NEWLINE));
                    documento.add(new Paragraph("TALLA DE CAMISA :" + min.getCamisa()));
                    documento.add(new Phrase(Chunk.NEWLINE));
                    documento.add(new Paragraph("TALLA DE PANTALON :" + min.getPanta()));
                    documento.add(new Phrase(Chunk.NEWLINE));
                    documento.add(new Paragraph("TALLA DE BOTAS :" + min.getBotas()));
                    documento.add(new Phrase(Chunk.NEWLINE));

                }

                Chunk link = new Chunk(year + "/" + month + "/" + day);
                link.setCharacterSpacing(5);
                link.setHorizontalScaling(Element.ALIGN_RIGHT);
                link.setCharacterSpacing(5);
                link.setHorizontalScaling(Element.ALIGN_RIGHT);
                documento.add(new Phrase(Chunk.NEWLINE));
                documento.add(new Phrase(Chunk.NEWLINE));
                documento.add(link);
                documento.close();

                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Reporte");
                alerta.setHeaderText("Se realizo reporte correctamente en la siguiente ruta.");
                alerta.setContentText("C:\\reportes\\" + pd + "(individual)(" + year + month + day + ").pdf");
                alerta.showAndWait();
            } else {
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Error");
                alerta.setContentText("Empleado no encontrado.");
                alerta.showAndWait();
            }

        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Error");
            alerta.setContentText("El campo no puede quedar en limpio.");
            alerta.showAndWait();
        }

    }

    @FXML
    private void onReporteXCentro(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Reporte personal");
        alert.setHeaderText("Presiona click izquierdo al centro para generar el reporte");
        ButtonType buttonTypeOne = new ButtonType("Oficina");
        ButtonType buttonTypeTwo = new ButtonType("Primavera");
        ButtonType buttonTypeThree = new ButtonType("Mina");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree, buttonTypeCancel);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne) {
            FileOutputStream ficheroPdf = null;
            try {
                Persona dao = new Persona();
                Calendar calendar = Calendar.getInstance();
                day = calendar.get(calendar.DAY_OF_MONTH);
                year = calendar.get(calendar.YEAR);
                month = (calendar.get(Calendar.MONTH) + 1);
                Document documento = new Document();
                ficheroPdf = new FileOutputStream("C:\\reportes\\ReporteOficina(" + year + month + day + ").pdf");
                PdfWriter.getInstance(documento, ficheroPdf).setInitialLeading(20);
                Image imagenes = Image.getInstance("C:\\Users\\User\\Documents\\Excarbon\\Personal\\src\\img\\LogoExcarbon.png");
                documento.open();
                imagenes.setAlignment(Element.ALIGN_TOP);
                imagenes.setAlignment(Element.ALIGN_RIGHT);
                imagenes.scaleToFit(200, 200);//agg imagen al documento
                documento.add(imagenes);
                documento.add(new Paragraph("INFORME OFICINA",
                        FontFactory.getFont("arial", // fuente
                                25, // tamaño
                                Font.ITALIC, // estilo
                                BaseColor.GRAY)));
                documento.add(new Phrase(Chunk.NEWLINE));
                String centro = "Oficina";
                List<minero> list = dao.reporteXcentro(centro);
                PdfPTable tabla = new PdfPTable(5);
                PdfPCell celda1 = new PdfPCell(new Paragraph("Cedula", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
                PdfPCell celda2 = new PdfPCell(new Paragraph("Nombre ", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
                PdfPCell celda3 = new PdfPCell(new Paragraph("Cargo ", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
                PdfPCell celda4 = new PdfPCell(new Paragraph("Fecha Ingreso", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
                PdfPCell celda6 = new PdfPCell(new Paragraph("Salario", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
                tabla.addCell(celda1);
                tabla.addCell(celda2);
                tabla.addCell(celda3);
                tabla.addCell(celda4);
                tabla.addCell(celda6);

                for (minero min : list) {
                    tabla.addCell(min.getCedula());
                    tabla.addCell(min.getNombre());
                    tabla.addCell(min.getCargo());
                    tabla.addCell(min.getFecha_Na());
                    tabla.addCell("" + min.getSalario());
                }
                Chunk link = new Chunk(year + "/" + month + "/" + day);
                link.setCharacterSpacing(5);
                link.setHorizontalScaling(Element.ALIGN_RIGHT);
                documento.add(tabla);

                documento.add(new Phrase(Chunk.NEWLINE));
                documento.add(new Phrase(Chunk.NEWLINE));
                documento.add(link);
                documento.close();
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Reporte");
                alerta.setHeaderText("Se realizo reporte correctamente en la siguiente ruta.");
                alerta.setContentText("C:\\reportes\\ReporteOficina(" + year + month + day + ").pdf");
                alerta.showAndWait();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(InicioController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DocumentException ex) {
                Logger.getLogger(InicioController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(InicioController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    ficheroPdf.close();
                } catch (IOException ex) {
                    Logger.getLogger(InicioController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else if (result.get() == buttonTypeTwo) {

            FileOutputStream ficheroPdf = null;
            try {
                Persona dao = new Persona();
                Calendar calendar = Calendar.getInstance();
                day = calendar.get(calendar.DAY_OF_MONTH);
                year = calendar.get(calendar.YEAR);
                month = (calendar.get(Calendar.MONTH) + 1);
                Document documento = new Document();
                ficheroPdf = new FileOutputStream("C:\\reportes\\ReportePrimavera.pdf" + year + month + day + ".pdf");
                PdfWriter.getInstance(documento, ficheroPdf).setInitialLeading(20);
                Image imagenes = Image.getInstance("C:\\Users\\User\\Documents\\Excarbon\\Personal\\src\\img\\LogoExcarbon.png");
                documento.open();
                imagenes.setAlignment(Element.ALIGN_TOP);
                imagenes.setAlignment(Element.ALIGN_RIGHT);
                imagenes.scaleToFit(200, 200);//agg imagen al documento
                documento.add(imagenes);
                documento.add(new Paragraph("INFORME PRIMAVERA",
                        FontFactory.getFont("arial", // fuente
                                25, // tamaño
                                Font.ITALIC, // estilo
                                BaseColor.GRAY)));
                documento.add(new Phrase(Chunk.NEWLINE));
                String centro = "Primavera";
                List<minero> list = dao.reporteXcentro(centro);
                PdfPTable tabla = new PdfPTable(5);
                PdfPCell celda1 = new PdfPCell(new Paragraph("Cedula", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
                PdfPCell celda2 = new PdfPCell(new Paragraph("Nombre ", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
                PdfPCell celda3 = new PdfPCell(new Paragraph("Cargo ", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
                PdfPCell celda4 = new PdfPCell(new Paragraph("Fecha Ingreso", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
                PdfPCell celda6 = new PdfPCell(new Paragraph("Salario", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
                tabla.addCell(celda1);
                tabla.addCell(celda2);
                tabla.addCell(celda3);
                tabla.addCell(celda4);
                tabla.addCell(celda6);

                for (minero min : list) {
                    tabla.addCell(min.getCedula());
                    tabla.addCell(min.getNombre());
                    tabla.addCell(min.getCargo());
                    tabla.addCell(min.getFecha_Na());
                    tabla.addCell("" + min.getSalario());
                }

                Chunk link = new Chunk(year + "/" + month + "/" + day);
                link.setCharacterSpacing(5);
                link.setHorizontalScaling(Element.ALIGN_RIGHT);
                documento.add(tabla);
                documento.add(new Phrase(Chunk.NEWLINE));
                documento.add(new Phrase(Chunk.NEWLINE));
                documento.add(link);
                documento.close();
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Reporte");
                alerta.setHeaderText("Se realizo reporte correctamente en la siguiente ruta.");
                alerta.setContentText("C:\\reportes\\ReportePrimavera.pdf(" + year + month + day + ").pdf");
                alerta.showAndWait();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(InicioController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DocumentException ex) {
                Logger.getLogger(InicioController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(InicioController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    ficheroPdf.close();
                } catch (IOException ex) {
                    Logger.getLogger(InicioController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else if (result.get() == buttonTypeThree) {
            FileOutputStream ficheroPdf = null;
            try {
                Persona dao = new Persona();
                Calendar calendar = Calendar.getInstance();
                day = calendar.get(calendar.DAY_OF_MONTH);
                year = calendar.get(calendar.YEAR);
                month = (calendar.get(Calendar.MONTH) + 1);
                Document documento = new Document();
                ficheroPdf = new FileOutputStream("C:\\reportes\\ReporteMina(" + year + month + day + ").pdf");
                PdfWriter.getInstance(documento, ficheroPdf).setInitialLeading(20);
                Image imagenes = Image.getInstance("C:\\Users\\User\\Documents\\Excarbon\\Personal\\src\\img\\LogoExcarbon.png");
                documento.open();
                imagenes.setAlignment(Element.ALIGN_TOP);
                imagenes.setAlignment(Element.ALIGN_RIGHT);
                imagenes.scaleToFit(200, 200);//agg imagen al documento
                documento.add(imagenes);
                documento.add(new Paragraph("INFORME MINA",
                        FontFactory.getFont("arial", // fuente
                                25, // tamaño
                                Font.ITALIC, // estilo
                                BaseColor.GRAY)));
                documento.add(new Phrase(Chunk.NEWLINE));
                String centro = "Mina";
                List<minero> list = dao.reporteXcentro(centro);
                PdfPTable tabla = new PdfPTable(7);
                PdfPCell celda1 = new PdfPCell(new Paragraph("Cedula", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
                PdfPCell celda2 = new PdfPCell(new Paragraph("Nombre ", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
                PdfPCell celda3 = new PdfPCell(new Paragraph("Cargo ", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
                PdfPCell celda4 = new PdfPCell(new Paragraph("Fecha Ingreso", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
                PdfPCell celda6 = new PdfPCell(new Paragraph("Camisa ", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
                PdfPCell celda7 = new PdfPCell(new Paragraph("Pantalon", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
                PdfPCell celda8 = new PdfPCell(new Paragraph("Calzado", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
                tabla.addCell(celda1);
                tabla.addCell(celda2);
                tabla.addCell(celda3);
                tabla.addCell(celda4);
                tabla.addCell(celda6);
                tabla.addCell(celda7);
                tabla.addCell(celda8);
                for (minero min : list) {
                    tabla.addCell(min.getCedula());
                    tabla.addCell(min.getNombre());
                    tabla.addCell(min.getCargo());
                    tabla.addCell(min.getFecha_Na());
                    tabla.addCell(min.getCamisa());
                    tabla.addCell(min.getPanta());
                    tabla.addCell(min.getBotas());
                }
                Chunk link = new Chunk(year + "/" + month + "/" + day);
                link.setCharacterSpacing(5);
                link.setHorizontalScaling(Element.ALIGN_RIGHT);
                documento.add(tabla);

                documento.add(new Phrase(Chunk.NEWLINE));
                documento.add(new Phrase(Chunk.NEWLINE));
                documento.add(link);
                documento.close();
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Reporte");
                alerta.setHeaderText("Se realizo reporte correctamente en la siguiente ruta.");
                alerta.setContentText("C:\\reportes\\ReporteMina(" + year + month + day + ").pdf");
                alerta.showAndWait();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(InicioController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DocumentException ex) {
                Logger.getLogger(InicioController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(InicioController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    ficheroPdf.close();
                } catch (IOException ex) {
                    Logger.getLogger(InicioController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Reporte general");
            alerta.setHeaderText("Reporte cancelado");
            alerta.showAndWait();
        }

    }

    @FXML
    private void onReporteBeneficiarioCedula(ActionEvent event) throws Exception {
        Persona per = new Persona();
        TextInputDialog dialog = new TextInputDialog("CEDULA");
        dialog.setTitle("Reporte");
        dialog.setHeaderText("INGRESA LA CEDULA DEL EMPLEADO");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String pd = result.get();
            int a = 0;
            a = per.busquedaCedula(pd);
            if (a > 0) {
                FileOutputStream ficheroPdf = null;
                try {
                    Calendar calendar = Calendar.getInstance();
                    day = calendar.get(calendar.DAY_OF_MONTH);
                    year = calendar.get(calendar.YEAR);
                    month = (calendar.get(Calendar.MONTH) + 1);
                    Document documento = new Document();
                    ficheroPdf = new FileOutputStream("C:\\reportes\\ReporteBeneficiario" + "(cedula " + pd + ")(" + year + month + day + ").pdf");
                    PdfWriter.getInstance(documento, ficheroPdf).setInitialLeading(20);
                    Image imagenes = Image.getInstance("C:\\Users\\User\\Documents\\Excarbon\\Personal\\src\\img\\LogoExcarbon.png");
                    documento.open();
                    imagenes.setAlignment(Element.ALIGN_TOP);
                    imagenes.setAlignment(Element.ALIGN_RIGHT);
                    imagenes.scaleToFit(200, 200);//agg imagen al documento
                    documento.add(imagenes);
                    documento.add(new Paragraph("INFORME BENEFICIARIOS",
                            FontFactory.getFont("arial", // fuente
                                    25, // tamaño
                                    Font.ITALIC, // estilo
                                    BaseColor.GRAY)));
                    documento.add(new Phrase(Chunk.NEWLINE));
                    List<minero> list = per.busquedaBeneficiarioXcedulaActivo(pd);
                    documento.add(new Paragraph("Cedula cotizante",
                            FontFactory.getFont("arial",
                                    10, // tamaño
                                    Font.ITALIC,
                                    BaseColor.BLACK)));
                    Chunk link1 = new Chunk(pd);
                    link1.setCharacterSpacing(2);
                    documento.add(link1);
                    PdfPTable tabla = new PdfPTable(4);
                    PdfPCell celda1 = new PdfPCell(new Paragraph("Tipo Documento", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
                    PdfPCell celda2 = new PdfPCell(new Paragraph("Numero Documento ", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
                    PdfPCell celda3 = new PdfPCell(new Paragraph("Nombre ", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
                    PdfPCell celda4 = new PdfPCell(new Paragraph("Parentezco", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
                    tabla.addCell(celda1);
                    tabla.addCell(celda2);
                    tabla.addCell(celda3);
                    tabla.addCell(celda4);
                    for (minero min : list) {
                        tabla.addCell(min.getTipo());
                        tabla.addCell(min.getCedulaBene());
                        tabla.addCell(min.getNombreBene());
                        tabla.addCell(min.getParente());
                    }
                    Chunk link = new Chunk(year + "/" + month + "/" + day);
                    link.setCharacterSpacing(5);
                    link.setHorizontalScaling(Element.ALIGN_RIGHT);
                    documento.add(tabla);
                    documento.add(new Phrase(Chunk.NEWLINE));
                    documento.add(new Phrase(Chunk.NEWLINE));
                    documento.add(link);
                    documento.close();
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("Reporte");
                    alerta.setHeaderText("Se realizo reporte correctamente en la siguiente ruta.");
                    alerta.setContentText("C:\\reportes\\ReporteBeneficiario" + "(cedula " + pd + ")(" + year + month + day + ").pdf");
                    alerta.showAndWait();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(InicioController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (DocumentException ex) {
                    Logger.getLogger(InicioController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(InicioController.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        ficheroPdf.close();
                    } catch (IOException ex) {
                        Logger.getLogger(InicioController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            } else {
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Error");
                alerta.setContentText("Trabajador no registrado");
                alerta.showAndWait();
            }

        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Error");
            alerta.setContentText("El campo no puede quedar en limpio.");
            alerta.showAndWait();
        }

    }

    @FXML
    private void onReporteINactivoTotal(ActionEvent event) throws Exception {

        Persona dao = new Persona();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Reporte ");
        alert.setHeaderText("Presiona click izquierdo en aceptar para generar el reporte");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Calendar calendar = Calendar.getInstance();
            day = calendar.get(calendar.DAY_OF_MONTH);
            year = calendar.get(calendar.YEAR);
            month = (calendar.get(Calendar.MONTH) + 1);
            Document documento = new Document();
            FileOutputStream ficheroPdf = new FileOutputStream("C:\\reportes\\ReportePersonalInactivo(" + year + month + day + ").pdf");
            PdfWriter.getInstance(documento, ficheroPdf).setInitialLeading(20);
            Image imagenes = Image.getInstance("C:\\Users\\User\\Documents\\Excarbon\\Personal\\src\\img\\LogoExcarbon.png");
            documento.open();
            imagenes.setAlignment(Element.ALIGN_TOP);
            imagenes.setAlignment(Element.ALIGN_RIGHT);
            imagenes.scaleToFit(150, 150);//agg imagen al documento
            documento.add(imagenes);
            documento.add(new Paragraph("INFORME",
                    FontFactory.getFont("arial", // fuente
                            25, // tamaño
                            Font.ITALIC, // estilo
                            BaseColor.GRAY)));
            documento.add(new Phrase(Chunk.NEWLINE));
            List<InactivoPersonal> list = dao.personalInactivo();
            PdfPTable tabla = new PdfPTable(7);
            PdfPCell celda1 = new PdfPCell(new Paragraph("Cedula", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
            PdfPCell celda2 = new PdfPCell(new Paragraph("Nombre ", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
            PdfPCell celda3 = new PdfPCell(new Paragraph("Cargo ", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
            PdfPCell celda4 = new PdfPCell(new Paragraph("Fecha ingreso", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
            PdfPCell celda5 = new PdfPCell(new Paragraph("Fecha salida", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
            PdfPCell celda6 = new PdfPCell(new Paragraph("Dias laborados", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
            PdfPCell celda7 = new PdfPCell(new Paragraph("Centro", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
            tabla.addCell(celda1);
            tabla.addCell(celda2);
            tabla.addCell(celda3);
            tabla.addCell(celda4);
            tabla.addCell(celda5);
            tabla.addCell(celda6);
            tabla.addCell(celda7);
            for (InactivoPersonal u : list) {
                tabla.addCell(u.getCedula());
                tabla.addCell(u.getNombre());
                tabla.addCell(u.getCargo());
                tabla.addCell(u.getFecha_ingreso());
                tabla.addCell(u.getFecha_salida());
                tabla.addCell("" + u.getDias());
                tabla.addCell(u.getEmpresa());

            }
            Chunk link = new Chunk(year + "/" + month + "/" + day);
            link.setCharacterSpacing(5);
            link.setHorizontalScaling(Element.ALIGN_RIGHT);
            documento.add(tabla);
            documento.add(new Phrase(Chunk.NEWLINE));
            documento.add(new Phrase(Chunk.NEWLINE));
            documento.add(new Phrase(Chunk.NEWLINE));
            documento.add(link);
            documento.close();
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Reporte");
            alerta.setHeaderText("Se realizo reporte correctamente en la siguiente ruta.");
            alerta.setContentText("C:\\reportes\\ReportePersonalInactivo(" + year + month + day + ").pdf");
            alerta.showAndWait();
        } else {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Reporte");
            alerta.setHeaderText("No se genero ningun reporte.");
            alerta.showAndWait();
        }

    }

    @FXML
    private void onReporteCentroInactivo(ActionEvent event) throws Exception {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Reporte personal");
        alert.setHeaderText("Presiona click izquierdo al centro para generar el reporte");
        ButtonType buttonTypeOne = new ButtonType("Oficina");
        ButtonType buttonTypeTwo = new ButtonType("Primavera");
        ButtonType buttonTypeThree = new ButtonType("Mina");
        ButtonType buttonTypeCancel = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree, buttonTypeCancel);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne) {
            FileOutputStream ficheroPdf = null;
            try {
                Persona dao = new Persona();
                Calendar calendar = Calendar.getInstance();
                day = calendar.get(calendar.DAY_OF_MONTH);
                year = calendar.get(calendar.YEAR);
                month = (calendar.get(Calendar.MONTH) + 1);
                Document documento = new Document();
                ficheroPdf = new FileOutputStream("C:\\reportes\\ReporteOficinaInactivo(" + year + month + day + ").pdf");
                PdfWriter.getInstance(documento, ficheroPdf).setInitialLeading(20);
                Image imagenes = Image.getInstance("C:\\Users\\User\\Documents\\Excarbon\\Personal\\src\\img\\LogoExcarbon.png");
                documento.open();
                imagenes.setAlignment(Element.ALIGN_TOP);
                imagenes.setAlignment(Element.ALIGN_RIGHT);
                imagenes.scaleToFit(200, 200);//agg imagen al documento
                documento.add(imagenes);
                documento.add(new Paragraph("INFORME OFICINA",
                        FontFactory.getFont("arial", // fuente
                                25, // tamaño
                                Font.ITALIC, // estilo
                                BaseColor.GRAY)));
                documento.add(new Phrase(Chunk.NEWLINE));
                String centro = "Oficina";
                List<InactivoPersonal> list = dao.personalInactivoXcentro(centro);
                PdfPTable tabla = new PdfPTable(7);
                PdfPCell celda1 = new PdfPCell(new Paragraph("Cedula", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
                PdfPCell celda2 = new PdfPCell(new Paragraph("Nombre ", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
                PdfPCell celda3 = new PdfPCell(new Paragraph("Cargo ", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
                PdfPCell celda4 = new PdfPCell(new Paragraph("Fecha ingreso", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
                PdfPCell celda6 = new PdfPCell(new Paragraph("Fecha salida", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
                PdfPCell celda7 = new PdfPCell(new Paragraph("Dias laborados", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
                PdfPCell celda8 = new PdfPCell(new Paragraph("Centro", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
                tabla.addCell(celda1);
                tabla.addCell(celda2);
                tabla.addCell(celda3);
                tabla.addCell(celda4);
                tabla.addCell(celda6);
                tabla.addCell(celda7);
                tabla.addCell(celda8);
                for (InactivoPersonal u : list) {
                    tabla.addCell(u.getCedula());
                    tabla.addCell(u.getNombre());
                    tabla.addCell(u.getCargo());
                    tabla.addCell(u.getFecha_ingreso());
                    tabla.addCell(u.getFecha_salida());
                    tabla.addCell("" + u.getDias());
                    tabla.addCell(u.getEmpresa());
                }
                Chunk link = new Chunk(year + "/" + month + "/" + day);
                link.setCharacterSpacing(5);
                link.setHorizontalScaling(Element.ALIGN_RIGHT);
                documento.add(tabla);
                documento.add(new Phrase(Chunk.NEWLINE));
                documento.add(new Phrase(Chunk.NEWLINE));
                documento.add(new Phrase(Chunk.NEWLINE));
                documento.add(new Phrase(Chunk.NEWLINE));
                documento.add(new Phrase(Chunk.NEWLINE));
                documento.add(new Phrase(Chunk.NEWLINE));
                documento.add(link);
                documento.close();
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Reporte");
                alerta.setHeaderText("Se realizo reporte correctamente en la siguiente ruta.");
                alerta.setContentText("C:\\reportes\\ReporteOficinaInactivo(" + year + month + day + ").pdf");
                alerta.showAndWait();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(InicioController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DocumentException ex) {
                Logger.getLogger(InicioController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(InicioController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    ficheroPdf.close();
                } catch (IOException ex) {
                    Logger.getLogger(InicioController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else if (result.get() == buttonTypeTwo) {

            FileOutputStream ficheroPdf = null;
            try {
                Persona dao = new Persona();
                Calendar calendar = Calendar.getInstance();
                day = calendar.get(calendar.DAY_OF_MONTH);
                year = calendar.get(calendar.YEAR);
                month = (calendar.get(Calendar.MONTH) + 1);
                Document documento = new Document();
                ficheroPdf = new FileOutputStream("C:\\reportes\\ReportePrimaveraInactivo.pdf(" + year + month + day + ").pdf");
                PdfWriter.getInstance(documento, ficheroPdf).setInitialLeading(20);
                Image imagenes = Image.getInstance("C:\\Users\\User\\Documents\\Excarbon\\Personal\\src\\img\\LogoExcarbon.png");
                documento.open();
                imagenes.setAlignment(Element.ALIGN_TOP);
                imagenes.setAlignment(Element.ALIGN_RIGHT);
                imagenes.scaleToFit(200, 200);//agg imagen al documento
                documento.add(imagenes);
                documento.add(new Paragraph("INFORME PRIMAVERA",
                        FontFactory.getFont("arial", // fuente
                                25, // tamaño
                                Font.ITALIC, // estilo
                                BaseColor.GRAY)));
                documento.add(new Phrase(Chunk.NEWLINE));
                String centro = "Primavera";
                List<InactivoPersonal> list = dao.personalInactivoXcentro(centro);
                PdfPTable tabla = new PdfPTable(6);
                PdfPCell celda1 = new PdfPCell(new Paragraph("Cedula", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
                PdfPCell celda2 = new PdfPCell(new Paragraph("Nombre ", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
                PdfPCell celda3 = new PdfPCell(new Paragraph("Cargo ", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
                PdfPCell celda4 = new PdfPCell(new Paragraph("Fecha ingreso", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
                PdfPCell celda6 = new PdfPCell(new Paragraph("Fecha salida", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
                PdfPCell celda7 = new PdfPCell(new Paragraph("Dias laborados", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
                tabla.addCell(celda1);
                tabla.addCell(celda2);
                tabla.addCell(celda3);
                tabla.addCell(celda4);
                tabla.addCell(celda6);
                tabla.addCell(celda7);
                for (InactivoPersonal u : list) {
                    tabla.addCell(u.getCedula());
                    tabla.addCell(u.getNombre());
                    tabla.addCell(u.getCargo());
                    tabla.addCell(u.getFecha_ingreso());
                    tabla.addCell(u.getFecha_salida());
                    tabla.addCell("" + u.getDias());
                }
                Chunk link = new Chunk(year + "/" + month + "/" + day);
                link.setCharacterSpacing(5);
                link.setHorizontalScaling(Element.ALIGN_RIGHT);
                documento.add(tabla);
                documento.add(new Phrase(Chunk.NEWLINE));
                documento.add(new Phrase(Chunk.NEWLINE));
                documento.add(link);
                documento.close();
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Reporte");
                alerta.setHeaderText("Se realizo reporte correctamente en la siguiente ruta.");
                alerta.setContentText("C:\\reportes\\ReportePrimaveraInactivo.pdf(" + year + month + day + ").pdf");
                alerta.showAndWait();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(InicioController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DocumentException ex) {
                Logger.getLogger(InicioController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(InicioController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    ficheroPdf.close();
                } catch (IOException ex) {
                    Logger.getLogger(InicioController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else if (result.get() == buttonTypeThree) {
            FileOutputStream ficheroPdf = null;
            try {
                Persona dao = new Persona();
                Calendar calendar = Calendar.getInstance();
                day = calendar.get(calendar.DAY_OF_MONTH);
                year = calendar.get(calendar.YEAR);
                month = (calendar.get(Calendar.MONTH) + 1);
                Document documento = new Document();
                ficheroPdf = new FileOutputStream("C:\\reportes\\ReporteMinaInactivo (" + year + month + day + ").pdf");
                PdfWriter.getInstance(documento, ficheroPdf).setInitialLeading(20);
                Image imagenes = Image.getInstance("C:\\Users\\User\\Documents\\Excarbon\\Personal\\src\\img\\LogoExcarbon.png");
                documento.open();
                imagenes.setAlignment(Element.ALIGN_TOP);
                imagenes.setAlignment(Element.ALIGN_RIGHT);
                imagenes.scaleToFit(200, 200);//agg imagen al documento
                documento.add(imagenes);
                documento.add(new Paragraph("INFORME MINA",
                        FontFactory.getFont("arial", // fuente
                                25, // tamaño
                                Font.ITALIC, // estilo
                                BaseColor.GRAY)));
                documento.add(new Phrase(Chunk.NEWLINE));
                String centro = "Mina";
                List<InactivoPersonal> list = dao.personalInactivoXcentro(centro);
                PdfPTable tabla = new PdfPTable(6);
                PdfPCell celda1 = new PdfPCell(new Paragraph("Cedula", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
                PdfPCell celda2 = new PdfPCell(new Paragraph("Nombre ", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
                PdfPCell celda3 = new PdfPCell(new Paragraph("Cargo ", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
                PdfPCell celda4 = new PdfPCell(new Paragraph("Fecha ingreso", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
                PdfPCell celda5 = new PdfPCell(new Paragraph("Fecha salida", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
                PdfPCell celda9 = new PdfPCell(new Paragraph("Dias laborados", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
                tabla.addCell(celda1);
                tabla.addCell(celda2);
                tabla.addCell(celda3);
                tabla.addCell(celda4);
                tabla.addCell(celda5);
                tabla.addCell(celda9);
                for (InactivoPersonal min : list) {
                    tabla.addCell(min.getCedula());
                    tabla.addCell(min.getNombre());
                    tabla.addCell(min.getCargo());
                    tabla.addCell(min.getFecha_ingreso());
                    tabla.addCell(min.getFecha_salida());
                    tabla.addCell("" + min.getDias());
                }
                Chunk link = new Chunk(year + "/" + month + "/" + day);
                link.setCharacterSpacing(5);
                link.setHorizontalScaling(Element.ALIGN_RIGHT);
                documento.add(tabla);
                documento.add(new Phrase(Chunk.NEWLINE));
                documento.add(new Phrase(Chunk.NEWLINE));
                documento.add(link);
                documento.close();
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Reporte");
                alerta.setHeaderText("Se realizo reporte correctamente en la siguiente ruta.");
                alerta.setContentText("C:\\reportes\\ReporteMinaInactivo (" + year + month + day + ").pdf");
                alerta.showAndWait();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(InicioController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DocumentException ex) {
                Logger.getLogger(InicioController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(InicioController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    ficheroPdf.close();
                } catch (IOException ex) {
                    Logger.getLogger(InicioController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Reporte general");
            alerta.setHeaderText("Reporte cancelado");
            alerta.showAndWait();
        }

    }

    @FXML
    private void onReporteBeneficiariosInactivo(ActionEvent event) throws Exception {
        Persona per = new Persona();
        TextInputDialog dialog = new TextInputDialog("CEDULA");
        dialog.setTitle("Reporte");
        dialog.setHeaderText("INGRESA LA CEDULA DEL EMPLEADO");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String pd = result.get();
            int a = 0;
            a = per.busquedaCedulaInactivo(pd);
            if (a > 0) {
                FileOutputStream ficheroPdf = null;
                try {
                    Calendar calendar = Calendar.getInstance();
                    day = calendar.get(calendar.DAY_OF_MONTH);
                    year = calendar.get(calendar.YEAR);
                    month = (calendar.get(Calendar.MONTH) + 1);
                    Document documento = new Document();
                    ficheroPdf = new FileOutputStream("C:\\reportes\\ReporteBeneficiario" + "(cedula " + pd + ")" + year + month + day + ".pdf");
                    PdfWriter.getInstance(documento, ficheroPdf).setInitialLeading(20);
                    Image imagenes = Image.getInstance("C:\\Users\\User\\Documents\\Excarbon\\Personal\\src\\img\\LogoExcarbon.png");
                    documento.open();
                    imagenes.setAlignment(Element.ALIGN_TOP);
                    imagenes.setAlignment(Element.ALIGN_RIGHT);
                    imagenes.scaleToFit(200, 200);//agg imagen al documento
                    documento.add(imagenes);
                    documento.add(new Paragraph("INFORME BENEFICIARIOS",
                            FontFactory.getFont("arial", // fuente
                                    25, // tamaño
                                    Font.ITALIC, // estilo
                                    BaseColor.GRAY)));
                    documento.add(new Phrase(Chunk.NEWLINE));
                    List<minero> list = per.busquedaBeneficiarioXcedulaInactivo(pd);
                    documento.add(new Paragraph("Cedula cotizante",
                            FontFactory.getFont("arial",
                                    10, // tamaño
                                    Font.ITALIC,
                                    BaseColor.BLACK)));
                    Chunk link1 = new Chunk(pd);
                    link1.setCharacterSpacing(2);
                    documento.add(link1);
                    PdfPTable tabla = new PdfPTable(4);
                    PdfPCell celda1 = new PdfPCell(new Paragraph("Tipo Documento", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
                    PdfPCell celda2 = new PdfPCell(new Paragraph("Numero Documento ", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
                    PdfPCell celda3 = new PdfPCell(new Paragraph("Nombre ", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
                    PdfPCell celda4 = new PdfPCell(new Paragraph("Parentezco", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
                    tabla.addCell(celda1);
                    tabla.addCell(celda2);
                    tabla.addCell(celda3);
                    tabla.addCell(celda4);
                    for (minero min : list) {
                        tabla.addCell(min.getTipo());
                        tabla.addCell(min.getCedulaBene());
                        tabla.addCell(min.getNombreBene());
                        tabla.addCell(min.getParente());
                    }
                    Chunk link = new Chunk(year + "/" + month + "/" + day);
                    link.setCharacterSpacing(5);
                    link.setHorizontalScaling(Element.ALIGN_RIGHT);
                    documento.add(tabla);
                    documento.add(new Phrase(Chunk.NEWLINE));
                    documento.add(new Phrase(Chunk.NEWLINE));
                    documento.add(link);
                    documento.close();
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("Reporte");
                    alerta.setHeaderText("Se realizo reporte correctamente en la siguiente ruta.");
                    alerta.setContentText("C:\\reportes\\ReporteBeneficiario" + "(cedula " + pd + ")" + year + month + day + ".pdf");
                    alerta.showAndWait();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(InicioController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (DocumentException ex) {
                    Logger.getLogger(InicioController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(InicioController.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        ficheroPdf.close();
                    } catch (IOException ex) {
                        Logger.getLogger(InicioController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            } else {
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Error");
                alerta.setContentText("Trabajador no registrado");
                alerta.showAndWait();
            }

        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Error");
            alerta.setContentText("El campo no puede quedar en limpio.");
            alerta.showAndWait();
        }

    }

    @FXML
    private void onBuscarPersonaInactivo(ActionEvent event) throws Exception {
        Persona dao = new Persona();
        TextInputDialog dialog = new TextInputDialog("CEDULA");
        dialog.setTitle("Reporte");
        dialog.setHeaderText("INGRESA LA CEDULA DEL EMPLEADO");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            Persona per = new Persona();
            String pd = result.get();
            int a = 0;
            a = per.busquedaCedulaInactivo(pd);
            if (a > 0) {
                Calendar calendar = Calendar.getInstance();
                day = calendar.get(calendar.DAY_OF_MONTH);
                year = calendar.get(calendar.YEAR);
                month = (calendar.get(Calendar.MONTH) + 1);
                Document documento = new Document();
                FileOutputStream ficheroPdf = new FileOutputStream("C:\\reportes\\" + pd + "(individual)(inactivo)(" + year + month + day + ").pdf");
                PdfWriter.getInstance(documento, ficheroPdf).setInitialLeading(20);
                Image imagenes = Image.getInstance("C:\\Users\\User\\Documents\\Excarbon\\Personal\\src\\img\\LogoExcarbon.png");
                documento.open();
                imagenes.setAlignment(Element.ALIGN_TOP);
                imagenes.setAlignment(Element.ALIGN_RIGHT);
                imagenes.scaleToFit(150, 150);//agg imagen al documento
                documento.add(imagenes);
                documento.add(new Paragraph("INFORME INDIVIDUAL",
                        FontFactory.getFont("arial", // fuente
                                25, // tamaño
                                Font.ITALIC, // estilo
                                BaseColor.GRAY)));
                documento.add(new Phrase(Chunk.NEWLINE));
                List<minero> list = dao.busquedaActivoXcedulaInactivo(pd);
                for (minero min : list) {
                    documento.add(new Paragraph("CEDULA :" + min.getCedula()));
                    documento.add(new Phrase(Chunk.NEWLINE));
                    documento.add(new Paragraph("NOMBRE :" + min.getNombre()));
                    documento.add(new Phrase(Chunk.NEWLINE));
                    documento.add(new Paragraph("CARGO :" + min.getCargo()));
                    documento.add(new Phrase(Chunk.NEWLINE));
                    documento.add(new Paragraph("Centro de costos:" + min.getEmpresa()));
                    documento.add(new Phrase(Chunk.NEWLINE));
                    documento.add(new Paragraph("SALARIO :" + min.getSalario()));
                    documento.add(new Phrase(Chunk.NEWLINE));
                    documento.add(new Paragraph("SALUD :" + min.getSalud()));
                    documento.add(new Phrase(Chunk.NEWLINE));
                    documento.add(new Paragraph("PENSIÓN :" + min.getPension()));
                    documento.add(new Phrase(Chunk.NEWLINE));
                    documento.add(new Paragraph("ARL :" + min.getArl()));
                    documento.add(new Phrase(Chunk.NEWLINE));
                    documento.add(new Paragraph("CAJA COMPENSACIÓN :" + min.getCompensa()));
                    documento.add(new Phrase(Chunk.NEWLINE));
                    documento.add(new Paragraph("TALLA DE CAMISA :" + min.getCamisa()));
                    documento.add(new Phrase(Chunk.NEWLINE));
                    documento.add(new Paragraph("TALLA DE PANTALON :" + min.getPanta()));
                    documento.add(new Phrase(Chunk.NEWLINE));
                    documento.add(new Paragraph("TALLA DE BOTAS :" + min.getBotas()));
                    documento.add(new Phrase(Chunk.NEWLINE));
                    documento.add(new Paragraph("FECHA DE INGRESO :" + min.getFecha_Na()));
                    documento.add(new Phrase(Chunk.NEWLINE));
                    documento.add(new Paragraph("FECHA DE SALIDA :" + min.getFecha_salida()));
                    documento.add(new Phrase(Chunk.NEWLINE));
                    documento.add(new Paragraph("DIAS LABORADOS :" + min.getDiasLaborales()));
                    documento.add(new Phrase(Chunk.NEWLINE));

                }

                Chunk link = new Chunk(year + "/" + month + "/" + day);
                link.setCharacterSpacing(5);
                link.setHorizontalScaling(Element.ALIGN_RIGHT);
                link.setCharacterSpacing(5);
                link.setHorizontalScaling(Element.ALIGN_RIGHT);
                documento.add(new Phrase(Chunk.NEWLINE));
                documento.add(new Phrase(Chunk.NEWLINE));
                documento.add(link);
                documento.close();

                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Reporte");
                alerta.setHeaderText("Se realizo reporte correctamente en la siguiente ruta.");
                alerta.setContentText("C:\\reportes\\" + pd + "(individual)(inactivo)(" + year + month + day + ").pdf");
                alerta.showAndWait();
            } else {
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Error");
                alerta.setContentText("Empleado no encontrado.");
                alerta.showAndWait();
            }

        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Error");
            alerta.setContentText("El campo no puede quedar en limpio.");
            alerta.showAndWait();
        }

    }

    @FXML
    private void onNuevoPrestamo(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Prestamo");
        alert.setHeaderText("Click izquierdo en la opcion que necesites");
        ButtonType buttonTypeOne = new ButtonType("Nuevo prestamo");
        ButtonType buttonTypeTwo = new ButtonType("Actualizar prestamo");
        ButtonType buttonTypeCancel = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne) {
            try {
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("personal/Prestamos.fxml"));
                Scene scene = new Scene(root);
                escenario.setScene(scene);
            } catch (IOException ex) {
                System.out.println("ERROR (IO): " + ex);
            }
        } else if (result.get() == buttonTypeTwo) {
            try {
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("personal/ActualizacionPrestamos.fxml"));
                Scene scene = new Scene(root);
                escenario.setScene(scene);
            } catch (IOException ex) {
                System.out.println("ERROR (IO): " + ex);
            }
        }
    }

    @FXML
    private void onPagos(ActionEvent event) {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Pagos");
        ButtonType loginButtonType = new ButtonType("Pagar", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));
        TextField Cedula = new TextField();
        Cedula.setPromptText("Cedula");
        TextField pago = new TextField();
        pago.setPromptText("Abono");
        gridPane.add(Cedula, 0, 0);
        gridPane.add(pago, 2, 0);
        dialog.getDialogPane().setContent(gridPane);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(Cedula.getText(), pago.getText());

            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();
        result.ifPresent(pair -> {
            System.out.println("Cedula=" + pair.getKey() + ", pago=" + pair.getValue());
            if (Cedula.getText().isEmpty() && pago.getText().isEmpty()) {
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Error");
                alerta.setContentText("No  se permiten campos limpios.");
                alerta.showAndWait();
                dialog.getDialogPane().setContent(gridPane);
            } else {
                if (Cedula.getText().isEmpty()) {
                    Alert alerta = new Alert(Alert.AlertType.WARNING);
                    alerta.setTitle("Error");
                    alerta.setContentText("El campo cedula no puede estar en limpio");
                    alerta.showAndWait();
                    dialog.getDialogPane().setContent(gridPane);
                } else if (pago.getText().isEmpty()) {
                    Alert alerta = new Alert(Alert.AlertType.WARNING);
                    alerta.setTitle("Error");
                    alerta.setContentText("El campo abono no puede estar en limpio");
                    alerta.showAndWait();
                    dialog.getDialogPane().setContent(gridPane);
                } else if (Cedula.getText().length() > 12) {
                    Alert alerta = new Alert(Alert.AlertType.WARNING);
                    alerta.setTitle("Error");
                    alerta.setContentText("El campo cedula no puede tener mas de 12 digitos");
                    alerta.showAndWait();
                    dialog.getDialogPane().setContent(gridPane);
                } else {
                    Persona dao = new Persona();
                    int a = dao.busquedaCedula(pair.getKey());
                    if (a == 0) {
                        Alert alerta = new Alert(Alert.AlertType.WARNING);
                        alerta.setTitle("Error");
                        alerta.setHeaderText("No se encontro ningun prestamo para pago");
                        alerta.setContentText("O trabajador no registrado");
                        alerta.showAndWait();
                        dialog.getDialogPane().setContent(gridPane);
                    } else {
                        int presu = dao.busquedaCedulaXPrestamoActivo(Cedula.getText());
                        if (presu == 1) {
                            Prestamo pre = new Prestamo();
                            int fe = Integer.parseInt(pago.getText());
                            pre.setCedula1(Cedula.getText());
                            pre.setCuotas(fe);
                            int teta = dao.abonoCuenta(pre);

                            int reu = dao.abonoCuentaRefuerzo(pre);
                            if (reu == 1) {
                                Alert alerta = new Alert(Alert.AlertType.WARNING);
                                alerta.setTitle("Pago");
                                alerta.setHeaderText("Se cancelo el prestamo de la cedula  " + Cedula.getText());
                                alerta.showAndWait();
                                dialog.getDialogPane().setContent(gridPane);
                            }
                            Alert alerta = new Alert(Alert.AlertType.WARNING);
                            alerta.setTitle("Pago");
                            alerta.setHeaderText("Se realizo el pago correctamente");
                            alerta.showAndWait();
                            dialog.getDialogPane().setContent(gridPane);
                        } else {
                            Alert alerta = new Alert(Alert.AlertType.WARNING);
                            alerta.setTitle("Error");
                            alerta.setHeaderText("No se realizo el pago");
                            alerta.setContentText("Ya cancelado o no registrado");
                            alerta.showAndWait();
                        }

                    }
                }
            }
        });

    }

    @FXML
    private void onReportePersonaPrestamo(ActionEvent event) throws Exception {
        Persona dao = new Persona();
        TextInputDialog dialog = new TextInputDialog("CEDULA");
        dialog.setTitle("Reporte");
        dialog.setHeaderText("INGRESA LA CEDULA DEL EMPLEADO");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            Persona per = new Persona();
            String pd = result.get();
            int a = 0;
            a = per.busquedaCedula(pd);
            if (a > 0) {
                Calendar calendar = Calendar.getInstance();
                day = calendar.get(calendar.DAY_OF_MONTH);
                year = calendar.get(calendar.YEAR);
                month = (calendar.get(Calendar.MONTH) + 1);
                Document documento = new Document();
                FileOutputStream ficheroPdf = new FileOutputStream("C:\\reportes\\" + pd + "( individual Prestamo )( " + year + month + day + ").pdf");
                PdfWriter.getInstance(documento, ficheroPdf).setInitialLeading(20);
                Image imagenes = Image.getInstance("C:\\Users\\User\\Documents\\Excarbon\\Personal\\src\\img\\LogoExcarbon.png");
                documento.open();
                imagenes.setAlignment(Element.ALIGN_TOP);
                imagenes.setAlignment(Element.ALIGN_RIGHT);
                imagenes.scaleToFit(200, 200);//agg imagen al documento
                documento.add(imagenes);
                documento.add(new Paragraph("INFORME PRESTAMOS",
                        FontFactory.getFont("arial", // fuente
                                25, // tamaño
                                Font.ITALIC, // estilo
                                BaseColor.GRAY)));
                documento.add(new Phrase(Chunk.NEWLINE));
                List<Prestamo> list = per.ReportePrestamosCedula(pd);
                documento.add(new Paragraph("Cedula ",
                        FontFactory.getFont("arial",
                                10, // tamaño
                                Font.ITALIC,
                                BaseColor.BLACK)));
                Chunk link1 = new Chunk(pd);
                link1.setCharacterSpacing(2);
                documento.add(link1);
                PdfPTable tabla = new PdfPTable(8);
                PdfPCell celda1 = new PdfPCell(new Paragraph("Valor Inicial    ", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
                PdfPCell celda2 = new PdfPCell(new Paragraph("Cuotas           ", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
                PdfPCell celda3 = new PdfPCell(new Paragraph("Saldo Cuotas     ", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
                PdfPCell celda4 = new PdfPCell(new Paragraph("Estado           ", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
                PdfPCell celda5 = new PdfPCell(new Paragraph("Ultimo Pago      ", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
                PdfPCell celda6 = new PdfPCell(new Paragraph("total del pago   ", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
                PdfPCell celda7 = new PdfPCell(new Paragraph("Fecha Inicio     ", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
                PdfPCell celda8 = new PdfPCell(new Paragraph("Fecha Final      ", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK))
                );
                tabla.addCell(celda1);
                tabla.addCell(celda2);
                tabla.addCell(celda3);
                tabla.addCell(celda4);
                tabla.addCell(celda5);
                tabla.addCell(celda6);
                tabla.addCell(celda7);
                tabla.addCell(celda8);
                for (Prestamo min : list) {

                    tabla.addCell("" + min.getValor());
                    tabla.addCell("" + min.getCuotas());
                    tabla.addCell("" + min.getSaldo());
                    tabla.addCell(min.getTipo());
                    tabla.addCell("" + min.getAbono());
                    tabla.addCell("" + min.getPago());
                    tabla.addCell(min.getFecha_());
                    tabla.addCell(min.getFechaFin());
                }
                Chunk link = new Chunk(year + "/" + month + "/" + day);
                link.setCharacterSpacing(5);
                link.setHorizontalScaling(Element.ALIGN_RIGHT);
                documento.add(tabla);
                documento.add(new Phrase(Chunk.NEWLINE));
                documento.add(new Phrase(Chunk.NEWLINE));
                documento.add(link);
                documento.close();
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Reporte");
                alerta.setHeaderText("Se realizo reporte correctamente en la siguiente ruta.");
                alerta.setContentText("C:\\reportes\\" + pd + "( individual Prestamo )( " + year + month + day + ").pdf");
                alerta.showAndWait();
            } else {
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Error");
                alerta.setContentText("Empleado no encontrado.");
                alerta.showAndWait();
            }
        }

    }

    @FXML
    private void onTotalReporte(ActionEvent event) throws Exception {
     
        Persona dao = new Persona();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Reporte ");
        alert.setHeaderText("Presiona click izquierdo en aceptar para generar el reporte");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Calendar calendar = Calendar.getInstance();
            day = calendar.get(calendar.DAY_OF_MONTH);
            year = calendar.get(calendar.YEAR);
            month = (calendar.get(Calendar.MONTH) + 1);
                            Document documento = new Document();
                FileOutputStream ficheroPdf = new FileOutputStream("C:\\reportes\\( Prestamo )( " + year + month + day + ").pdf");
                PdfWriter.getInstance(documento, ficheroPdf).setInitialLeading(20);
                Image imagenes = Image.getInstance("C:\\Users\\User\\Documents\\Excarbon\\Personal\\src\\img\\LogoExcarbon.png");
                documento.open();
                imagenes.setAlignment(Element.ALIGN_TOP);
                imagenes.setAlignment(Element.ALIGN_RIGHT);
                imagenes.scaleToFit(200, 200);//agg imagen al documento
                documento.add(imagenes);
                documento.add(new Paragraph("INFORME PRESTAMOS",
                        FontFactory.getFont("arial", // fuente
                                25, // tamaño
                                Font.ITALIC, // estilo
                                BaseColor.GRAY)));
                documento.add(new Phrase(Chunk.NEWLINE));
                List<Prestamo> list = dao.ReportePrestamosTotal();
                
                PdfPTable tabla = new PdfPTable(8);
                PdfPCell celda1 = new PdfPCell(new Paragraph("Cedula    ", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
                PdfPCell celda2 = new PdfPCell(new Paragraph("Nombre           ", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
                PdfPCell celda3 = new PdfPCell(new Paragraph("valor    ", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
                PdfPCell celda4 = new PdfPCell(new Paragraph("Saldo Cuota          ", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
                PdfPCell celda5 = new PdfPCell(new Paragraph("Estado      ", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
                PdfPCell celda6 = new PdfPCell(new Paragraph("Abono   ", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
                PdfPCell celda7 = new PdfPCell(new Paragraph("Fecha  inicio     ", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK)));
                PdfPCell celda8 = new PdfPCell(new Paragraph("Fecha final      ", FontFactory.getFont("Arial", 8, Font.BOLD, BaseColor.BLACK))
                );
                tabla.addCell(celda1);
                tabla.addCell(celda2);
                tabla.addCell(celda3);
                tabla.addCell(celda4);
                tabla.addCell(celda5);
                tabla.addCell(celda6);
                tabla.addCell(celda7);
                tabla.addCell(celda8);
            for (Prestamo min : list) {
                    tabla.addCell(min.getCedula1());
                    tabla.addCell(min.getNombre1());
                    tabla.addCell("" + min.getValor());
                    tabla.addCell("" + min.getSaldo());
                    tabla.addCell(min.getTipo());
                    tabla.addCell("" + min.getPago());
                    tabla.addCell(min.getFecha_());
                    tabla.addCell(min.getFechaFin());
                }
          Chunk link = new Chunk(year + "/" + month + "/" + day);
                link.setCharacterSpacing(5);
                link.setHorizontalScaling(Element.ALIGN_RIGHT);
                documento.add(tabla);
                documento.add(new Phrase(Chunk.NEWLINE));
                documento.add(new Phrase(Chunk.NEWLINE));
                documento.add(link);
                documento.close();
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Reporte");
                alerta.setHeaderText("Se realizo reporte correctamente en la siguiente ruta.");
                alerta.setContentText("C:\\reportes\\(Prestamo )( " + year + month + day + ").pdf");
                alerta.showAndWait();
        } else {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Reporte");
            alerta.setHeaderText("No se genero ningun reporte.");
            alerta.showAndWait();
        }

    }

}
