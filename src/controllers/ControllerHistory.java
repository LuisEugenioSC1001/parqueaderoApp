/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import models.ModelClient;
import models.ModelRecord;
import models.ModelVehicle;
import models.QueriesRecord;
import views.ViewHistory;
import views.ViewHome;
import views.ViewRegister;

/**
 *
 * @author mrles
 */
public class ControllerHistory implements ActionListener{
    QueriesRecord queriesRecord = new QueriesRecord();
    ModelClient modeloCliente = new ModelClient();
    ModelVehicle modeloVehiculo = new ModelVehicle();
    ModelRecord modeloRegistro = new ModelRecord();
    ViewHistory viewHistory = new ViewHistory();

    public ControllerHistory(ViewHistory viewHistory,ModelClient modelClient, ModelRecord modelRecord, ModelVehicle modelVehicle) {
        List<ModelRecord> datos = queriesRecord.getRegistros();
        this.modeloCliente = modelClient;
        this.modeloRegistro = modelRecord;
        this.modeloVehiculo = modelVehicle;
        this.viewHistory = viewHistory;
        
        viewHistory.btn_Home.addActionListener(this);
        viewHistory.btn_addRegister.addActionListener(this);
        viewHistory.btn_History.addActionListener(this);
        
        DefaultTableModel modelo = (DefaultTableModel) viewHistory.TableRegisters.getModel();
        Object[] fila = new Object[6];
        
        for (ModelRecord dato : datos) {
            fila[0] = dato.getIdregistro();
            fila[1] = dato.getPlaca();
            fila[2] = dato.getCedula();
            fila[3] = dato.getHora_ingreso();
            fila[4] = dato.getHora_salida();
            fila[5] = dato.getPosicion();
            modelo.addRow(fila);
        }
        
        viewHistory.TableRegisters.setModel(modelo);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewHistory.btn_Home) {
            ViewHome viewHome = new ViewHome();
            viewHome.setVisible(true);
            viewHistory.setVisible(false);
            ControllerHome controllerHome = new ControllerHome(viewHome,modeloCliente,modeloRegistro,modeloVehiculo);
        } else if (e.getSource() == viewHistory.btn_History) {
            
        } else if (e.getSource() == viewHistory.btn_addRegister) {
            ViewRegister viewRegister = new ViewRegister();
            viewRegister.setVisible(true);
            viewHistory.setVisible(false);
            ControllerRegister controllerRegister = new ControllerRegister(viewRegister, modeloCliente, modeloVehiculo, modeloRegistro);
        }
    }
    
    
    
}
