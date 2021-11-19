/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package parqueaderoapp;

import controllers.ControllerRegister;
import models.ModelClient;
import models.ModelRecord;
import models.ModelVehicle;
import views.ViewRegister;

/**
 *
 * @author mrles
 */
public class ParqueaderoApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ViewRegister vistaHome = new ViewRegister();
        ModelClient modeloCliente = new ModelClient();
        ModelVehicle modeloVehiculo = new ModelVehicle();
        ModelRecord modeloRegistro = new ModelRecord();

        vistaHome.setVisible(true);

        ControllerRegister controladorHome = new ControllerRegister(vistaHome, modeloCliente, modeloVehiculo, modeloRegistro);
    }

}
