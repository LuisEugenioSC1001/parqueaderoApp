/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import models.ModelClient;
import models.ModelRecord;
import models.ModelVehicle;
import views.ViewRegister;

/**
 *
 * @author mrles
 */
public class ControllerRegister {
    ViewRegister vistaHome = new ViewRegister();
    ModelClient modeloCliente = new ModelClient();
    ModelVehicle modeloVehiculo = new ModelVehicle();
    ModelRecord modeloRegistro = new ModelRecord();

    public ControllerRegister(ViewRegister vistaHome, ModelClient modeloCliente, ModelVehicle modeloVehiculo, ModelRecord modeloRegistro) {
        this.modeloCliente = modeloCliente;
        this.modeloRegistro = modeloRegistro;
        this.modeloVehiculo = modeloVehiculo;
        this.vistaHome = vistaHome;

    
    }
}
