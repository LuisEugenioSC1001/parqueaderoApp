/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author mrles
 */
public class ModeloBD {
    private final String servidor = "jdbc:mysql://localhost/parking_hemasoft";
    private final String usuario = "root";
    private final String password= "";

    public ModeloBD() {
    }
    
    public Connection conectarBD_HemaSoft(){
        
        Connection conexion = null;
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion= DriverManager.getConnection(servidor, usuario, password);
            System.out.println("YEAHHHH........Conexion BD Exitosa....");
            return conexion;
        }catch(Exception error){
            System.out.println("UPPS....." + error);
            return null;
        }
    }
}
