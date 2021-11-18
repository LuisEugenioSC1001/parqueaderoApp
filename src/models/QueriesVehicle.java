/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author mrles
 */
public class QueriesVehicle extends ModeloBD{
    PreparedStatement consultaSQL;
    ResultSet resultadoSQL;
    
    public boolean registrarVehiculos(ModelVehicle vehiculo){
        
        Connection conexion = conectarBD_HemaSoft();
        String queryvehiculo = "INSERT INTO vehiculos(placa, marca, tipo)"
                + "VALUES (?,?,?)";
        
        try{
            
            consultaSQL = conexion.prepareStatement(queryvehiculo);
            
            consultaSQL.setString(1, vehiculo.getPlaca());
            consultaSQL.setString(2, vehiculo.getMarca());
            consultaSQL.setString(3, vehiculo.getTipo());
            
            int resultado = consultaSQL.executeUpdate();
            
            if(resultado > 0){
                return true;
            }else{
                return false;
            }
            
        }catch(Exception error){
            System.out.println("UPPS error" + error);
            
            
            return false;
        
        }
        
    }
    
    public ModelVehicle buscarVehiculos(String placa){
        
        Connection conexion = conectarBD_HemaSoft();
        String queryvehiculo = "SELECT * from vehiculos where placa=?";
        
        try{
           
            consultaSQL = conexion.prepareStatement(queryvehiculo);
            
            consultaSQL.setString(1,placa);
            
            resultadoSQL= consultaSQL.executeQuery();
            
            ModelVehicle vehiculo = new ModelVehicle();
            
            if(resultadoSQL.next()){
                vehiculo.setPlaca(resultadoSQL.getString("placa"));
                vehiculo.setMarca(resultadoSQL.getString("marca"));
                vehiculo.setTipo(resultadoSQL.getString("tipo"));
                
                return vehiculo;
                
            }else{
                return null;
            }
           
       
        }catch(Exception error){
            System.out.println("UPPS error" + error);
        
            return null;
        }
        
        
    }
}
