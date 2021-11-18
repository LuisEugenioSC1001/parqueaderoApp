/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import models.ModelClient;
import models.ModelRecord;
import models.ModelVehicle;
import models.QueriesClient;
import models.QueriesRecord;
import models.QueriesVehicle;
import views.ViewHome;

/**
 *
 * @author mrles
 */
public class ControllerHome implements ActionListener{
    ViewHome vistaHome = new ViewHome();
    ModelClient modeloCliente = new ModelClient();
    ModelVehicle modeloVehiculo = new ModelVehicle();
    ModelRecord modeloRegistro = new ModelRecord();
    QueriesClient queriesClient=new QueriesClient();
    QueriesRecord queriesRecord=new QueriesRecord();
    QueriesVehicle QueriesVehicle= new QueriesVehicle();
    

    public ControllerHome(ViewHome vistaHome, ModelClient modeloCliente, ModelVehicle modeloVehiculo, ModelRecord modeloRegistro, QueriesClient queriesClient,
            QueriesRecord queriesRecord, QueriesVehicle queriesVehicle) {
        this.modeloCliente = modeloCliente;
        this.modeloRegistro = modeloRegistro;
        this.modeloVehiculo = modeloVehiculo;
        this.vistaHome = vistaHome;
        
        vistaHome.btn_facturar.addActionListener(this);

    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()== vistaHome.btn_facturar){
            
          modeloRegistro.setCedula(Integer.parseInt(vistaHome.tf_documento.getText()));
          modeloRegistro.setPlaca(vistaHome.tf_placa.getText());
          modeloRegistro.setCedula(Integer.parseInt(vistaHome.tf_documento.getText()));
          modeloRegistro.setHora_ingreso(vistaHome.tf_horaEntrada.getText());
          modeloRegistro.setHora_salida(vistaHome.tf_HoraSalida.getText());
          modeloRegistro.setEstado("false");
          
          queriesRecord.ingresoRegistro(modeloRegistro);
          
          
          
        
        }
        
    }
}
