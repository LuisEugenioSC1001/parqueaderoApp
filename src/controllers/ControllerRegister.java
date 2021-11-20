/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import models.ModelClient;
import models.ModelRecord;
import models.ModelVehicle;
import models.QueriesClient;
import models.QueriesRecord;
import models.QueriesVehicle;
import models.Settings;
import views.ViewHistory;
import views.ViewHome;
import views.ViewRegister;

/**
 *
 * @author mrles
 */
public class ControllerRegister implements ActionListener {

    ModelClient modeloCliente = new ModelClient();
    ModelVehicle modeloVehiculo = new ModelVehicle();
    ModelRecord modeloRegistro = new ModelRecord();
    ViewRegister vistaRegister = new ViewRegister();
    Settings settings = new Settings();
    public ControllerRegister(ViewRegister vistaRegister, ModelClient modeloCliente, ModelVehicle modeloVehiculo, ModelRecord modeloRegistro) {
        this.modeloCliente = modeloCliente;
        this.modeloRegistro = modeloRegistro;
        this.modeloVehiculo = modeloVehiculo;
        this.vistaRegister = vistaRegister;

        vistaRegister.btnRegistrar.addActionListener(this);
        vistaRegister.btn_Home.addActionListener(this);
        vistaRegister.btn_History.addActionListener(this);
        vistaRegister.btn_NewRegister.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vistaRegister.btnRegistrar) {
            if ((vistaRegister.Txt_cedula.getText().equals("")) || (vistaRegister.Txt_Nombre.getText().equals("")) || (vistaRegister.Txt_Apellidos.getText().equals(""))
                    || (vistaRegister.Txt_TelefonoMovil.getText().equals("")) || (vistaRegister.Txt_TelefonoFijo.getText().equals("")) || (vistaRegister.Txt_placa.getText().equals(""))
                    || (vistaRegister.Txt_Marca.getText().equals("")) || (vistaRegister.Txt_Tipo.getText().equals(""))) {
                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
            } else {

                QueriesClient queriesClient = new QueriesClient();
                QueriesVehicle queriesVehicle = new QueriesVehicle();
                QueriesRecord queriesRecord = new QueriesRecord();

                List<ModelRecord> registrosActivos = queriesRecord.getRegistrosActivos();
                ArrayList<Integer> posiciones = new ArrayList<>();
                for (ModelRecord record : registrosActivos) {
                    posiciones.add(record.getPosicion());
                }
                if (registrosActivos.size() > settings.getCapacidadMax()) {
                    System.out.println(settings.getCapacidadMax());
                    JOptionPane.showMessageDialog(null, "Los cupos estan llenos");
                } else {
                    for (int i = 1; i <= settings.getCapacidadMax(); i++) {
                        if (!posiciones.contains(i)) {
                            modeloRegistro.setPosicion(i);
                            break;
                        }
                    }
                    modeloCliente.setCedula(Integer.parseInt(vistaRegister.Txt_cedula.getText()));
                    modeloCliente.setNombres(vistaRegister.Txt_Nombre.getText());
                    modeloCliente.setApellidos(vistaRegister.Txt_Apellidos.getText());
                    modeloCliente.setTelefono_movil(Integer.parseInt(vistaRegister.Txt_TelefonoMovil.getText()));
                    modeloCliente.setTelefono_fijo(Integer.parseInt(vistaRegister.Txt_TelefonoFijo.getText()));

                    modeloVehiculo.setPlaca(vistaRegister.Txt_placa.getText());
                    modeloVehiculo.setMarca(vistaRegister.Txt_Marca.getText());
                    modeloVehiculo.setTipo(vistaRegister.Txt_Tipo.getText());

                    modeloRegistro.setCedula(Integer.parseInt(vistaRegister.Txt_cedula.getText()));
                    modeloRegistro.setPlaca(vistaRegister.Txt_placa.getText());
                    modeloRegistro.setHora_salida("");
                    modeloRegistro.setEstado("activo");

                    Date entrada = new Date();
                    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String fechaEntrada = formato.format(entrada);
                    modeloRegistro.setHora_ingreso(fechaEntrada);

                    if (queriesClient.registrarClientes(modeloCliente)
                            && queriesVehicle.registrarVehiculos(modeloVehiculo)
                            && queriesRecord.ingresoRegistro(modeloRegistro)) {

                        JOptionPane.showMessageDialog(null, "Registro ingresado correctamente");

                    } else {

                        JOptionPane.showMessageDialog(null, "Error en el registro");

                    }
                }
            }
        } else if (e.getSource() == vistaRegister.btn_Home) {
            ViewHome viewHome = new ViewHome();
            viewHome.setVisible(true);
            vistaRegister.setVisible(false);
            ControllerHome controllerHome = new ControllerHome(viewHome, modeloCliente, modeloRegistro, modeloVehiculo);
        } else if (e.getSource() == vistaRegister.btn_History) {
            ViewHistory viewHistory = new ViewHistory();
            viewHistory.setVisible(true);
            vistaRegister.setVisible(false);
            ControllerHistory controllerHistory = new ControllerHistory(viewHistory, modeloCliente, modeloRegistro, modeloVehiculo);
        } else if (e.getSource() == vistaRegister.btn_NewRegister) {

        }

    }
}
