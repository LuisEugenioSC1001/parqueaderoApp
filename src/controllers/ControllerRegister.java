/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import models.ModelClient;
import models.ModelRecord;
import models.ModelVehicle;
import models.QueriesClient;
import models.QueriesRecord;
import models.QueriesVehicle;
import views.ViewRegister;

/**
 *
 * @author mrles
 */
public class ControllerRegister implements ActionListener{
    //ViewRegister vistaHome = new ViewRegister();
    ModelClient modeloCliente = new ModelClient();
    ModelVehicle modeloVehiculo = new ModelVehicle();
    ModelRecord modeloRegistro = new ModelRecord();
    ViewRegister vistaRegister= new ViewRegister();

    public ControllerRegister(ViewRegister vistaRegister, ModelClient modeloCliente, ModelVehicle modeloVehiculo, ModelRecord modeloRegistro) {
        this.modeloCliente = modeloCliente;
        this.modeloRegistro = modeloRegistro;
        this.modeloVehiculo = modeloVehiculo;
        this.vistaRegister = vistaRegister;

        vistaRegister.btnRegistrar.addActionListener(this);
           }

    @Override
    public void actionPerformed(ActionEvent e) {
        QueriesClient queriesClient= new QueriesClient();
        QueriesVehicle queriesVehicle= new QueriesVehicle();
        QueriesRecord queriesRecord=new QueriesRecord();
        
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
        String fechaEntrada=formato.format(entrada);
        modeloRegistro.setHora_ingreso(fechaEntrada);
        
        
         if(queriesClient.registrarClientes(modeloCliente) && 
                queriesVehicle.registrarVehiculos(modeloVehiculo)&&
                queriesRecord.ingresoRegistro(modeloRegistro) ){
            
            JOptionPane.showMessageDialog(null, "Registro ingresado correctamente");
            
        }else{
            
             JOptionPane.showMessageDialog(null, "Error en el registro");
            
        }
        
        
        
        
        
    }
}
