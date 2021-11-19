/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.ModelClient;
import models.ModelRecord;
import models.ModelVehicle;
import models.QueriesClient;
import models.QueriesRecord;
import views.ViewHistory;
import views.ViewHome;
import views.ViewRegister;

/**
 *
 * @author mrles
 */
public class ControllerHome implements ActionListener {

    QueriesRecord queriesRecord = new QueriesRecord();
    ModelClient modeloCliente = new ModelClient();
    ModelVehicle modeloVehiculo = new ModelVehicle();
    ModelRecord modeloRegistro = new ModelRecord();
    ViewHome viewHome = new ViewHome();

    public ControllerHome(ViewHome viewHome, ModelClient modelClient, ModelRecord modelRecord, ModelVehicle modelVehicle) {
        List<ModelRecord> datos = queriesRecord.getRegistrosActivos();
        this.viewHome = viewHome;
        this.modeloCliente = modelClient;
        this.modeloRegistro = modelRecord;
        this.modeloVehiculo = modelVehicle;

        viewHome.btn_Home.addActionListener(this);
        viewHome.btn_addRegister.addActionListener(this);
        viewHome.btn_History.addActionListener(this);
        viewHome.btn_Liquidar.addActionListener(this);
        viewHome.Btn_Guardar.setVisible(false);
        viewHome.Btn_Guardar.addActionListener(this);

        DefaultTableModel modelo = (DefaultTableModel) viewHome.TableRegisters.getModel();
        Object[] fila = new Object[5];

        for (ModelRecord dato : datos) {
            fila[0] = dato.getIdregistro();
            fila[1] = dato.getPlaca();
            fila[2] = dato.getCedula();
            fila[3] = dato.getHora_ingreso();
            fila[4] = dato.getPosicion();
            modelo.addRow(fila);
        }

        viewHome.TableRegisters.setModel(modelo);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewHome.btn_Home) {

        } else if (e.getSource() == viewHome.btn_History) {
            ViewHistory viewHistory = new ViewHistory();
            viewHistory.setVisible(true);
            viewHome.setVisible(false);
            ControllerHistory controllerHistory = new ControllerHistory(viewHistory, modeloCliente, modeloRegistro, modeloVehiculo);
        } else if (e.getSource() == viewHome.btn_addRegister) {
            ViewRegister viewRegister = new ViewRegister();
            viewRegister.setVisible(true);
            viewHome.setVisible(false);
            ControllerRegister controllerRegister = new ControllerRegister(viewRegister, modeloCliente, modeloVehiculo, modeloRegistro);
        } else if (e.getSource() == viewHome.btn_Liquidar) {
            long valor = 0;
            if (viewHome.TableRegisters.getSelectedRow() > -1) {
                QueriesClient queriesClient = new QueriesClient();

                ModelRecord findData = queriesRecord.buscarregistro(Integer.parseInt(viewHome.TableRegisters.getModel().getValueAt(viewHome.TableRegisters.getSelectedRow(), 0).toString()));
                
                ModelClient cliente = queriesClient.buscarCliente(findData.getCedula());

                try {
                    Date salida = new Date();
                    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String fechaSalida = formato.format(salida);
                    findData.setHora_salida(fechaSalida);
                    
                    modeloRegistro.setHora_salida(fechaSalida);
                    String entrada = findData.getHora_ingreso();
                    Date fechaEntrada = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(entrada);

                    long tiempoDiff = salida.getTime() - fechaEntrada.getTime();
                    TimeUnit unidadTiempo = TimeUnit.MINUTES;
                    long tiempoRegistro = unidadTiempo.convert(tiempoDiff, TimeUnit.MILLISECONDS);
                    valor = tiempoRegistro * 150;

                } catch (ParseException error) {
                    System.out.println("uppss." + error);
                }

                viewHome.Lbl_HoraEntrada.setText(findData.getHora_ingreso());
                viewHome.Lbl_HoraSalida.setText(findData.getHora_salida());
                viewHome.Lbl_Placa.setText(findData.getPlaca());
                viewHome.Lbl_Cedula.setText(String.valueOf(findData.getCedula()));
                viewHome.Lbl_NombreConductor.setText(cliente.getNombres() + " " + cliente.getApellidos());
                viewHome.Lbl_ValorAPagar.setText(String.valueOf(valor));

                viewHome.Btn_Guardar.setVisible(true);
                
                modeloRegistro.setIdregistro(findData.getIdregistro());
                modeloRegistro.setPlaca(findData.getPlaca());
                modeloRegistro.setCedula(findData.getCedula());
                modeloRegistro.setHora_ingreso(findData.getHora_ingreso());
                modeloRegistro.setEstado(findData.getEstado());
                modeloRegistro.setPosicion(findData.getPosicion());
                
            } else {
                JOptionPane.showMessageDialog(null, "Por favor seleccione un registro en la tabla");
            }

        } else if (e.getSource() == viewHome.Btn_Guardar) {

            if (queriesRecord.liquidarRegistro(modeloRegistro)) {
                JOptionPane.showMessageDialog(null, "El registro se ha liquidado");
            } else {
                JOptionPane.showMessageDialog(null, "No se ha podido liquidar el vehiculo");
            }

        }

    }

}
