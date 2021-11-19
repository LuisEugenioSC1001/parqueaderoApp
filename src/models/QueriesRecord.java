/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mrles
 */
public class QueriesRecord extends ModeloBD {

    PreparedStatement consultaSQL;
    ResultSet resultadoSQL;

    public boolean ingresoRegistro(ModelRecord registro) {

        Connection conexion = conectarBD_HemaSoft();
        String queryregistro = "INSERT INTO registro( placa, cedula, hora_ingreso, estado, posicion) "
                + "VALUES (?,?,?,?,?)";

        try {

            consultaSQL = conexion.prepareStatement(queryregistro);

            consultaSQL.setString(1, registro.getPlaca());
            consultaSQL.setInt(2, registro.getCedula());
            consultaSQL.setString(3, registro.getHora_ingreso());
            consultaSQL.setString(4, registro.getEstado());
            consultaSQL.setInt(5, registro.getPosicion());
            
            int resultado = consultaSQL.executeUpdate();

            if (resultado > 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception error) {
            System.out.println("ey error" + error);

            return false;
        }

    }

    public ModelRecord buscarregistro(int idregistro) {

        Connection conexion = conectarBD_HemaSoft();
        String queryregistro = "SELECT * from registro where idregistro=?";

        try {

            consultaSQL = conexion.prepareStatement(queryregistro);

            consultaSQL.setInt(1, idregistro);

            resultadoSQL = consultaSQL.executeQuery();

            ModelRecord registro = new ModelRecord();

            if (resultadoSQL.next()) {

                registro.setPlaca(resultadoSQL.getString("placa"));
                registro.setCedula(resultadoSQL.getInt("cedula"));
                registro.setHora_ingreso(resultadoSQL.getString("hora_ingreso"));
                registro.setHora_salida(resultadoSQL.getString("hora_salida"));
                registro.setEstado(resultadoSQL.getString("estado"));

                return registro;

            } else {
                return null;
            }

        } catch (Exception error) {
            System.out.println("UPPS error" + error);

            return null;
        }

    }

    public List<ModelRecord> getRegistrosActivos() {
        Connection conexion = conectarBD_HemaSoft();
        String queryregistro = "SELECT * from registro where estado=?";
        ArrayList<ModelRecord> dataBD = new ArrayList<ModelRecord>();

        try {

            consultaSQL = conexion.prepareStatement(queryregistro);

            consultaSQL.setString(1, "activo");

            resultadoSQL = consultaSQL.executeQuery();

            while (resultadoSQL.next()) {
                ModelRecord modelRecord = new ModelRecord();

                modelRecord.setIdregistro(resultadoSQL.getInt("idregistro"));
                modelRecord.setCedula(resultadoSQL.getInt("cedula"));
                modelRecord.setEstado(resultadoSQL.getString("estado"));
                modelRecord.setHora_ingreso(resultadoSQL.getString("hora_ingreso"));
                modelRecord.setHora_salida(resultadoSQL.getString("hora_salida"));
                modelRecord.setPlaca(resultadoSQL.getString("placa"));
                modelRecord.setPosicion(resultadoSQL.getInt("posicion"));

                dataBD.add(modelRecord);
            }

            return dataBD;

        } catch (Exception error) {
            System.out.println("UPPS error" + error);

            return null;
        }
    }

    public List<ModelRecord> getRegistros() {
        Connection conexion = conectarBD_HemaSoft();
        String queryregistro = "SELECT * from registro";
        ArrayList<ModelRecord> dataBD = new ArrayList<ModelRecord>();

        try {

            consultaSQL = conexion.prepareStatement(queryregistro);

            resultadoSQL = consultaSQL.executeQuery();

            

            while (resultadoSQL.next()) {
                ModelRecord modelRecord = new ModelRecord();
                
                modelRecord.setIdregistro(resultadoSQL.getInt("idregistro"));
                modelRecord.setCedula(resultadoSQL.getInt("cedula"));
                modelRecord.setEstado(resultadoSQL.getString("estado"));
                modelRecord.setHora_ingreso(resultadoSQL.getString("hora_ingreso"));
                modelRecord.setHora_salida(resultadoSQL.getString("hora_salida"));
                modelRecord.setPlaca(resultadoSQL.getString("placa"));
                modelRecord.setPosicion(Integer.parseInt(resultadoSQL.getString("posicion")));

                dataBD.add(modelRecord);
            }

            return dataBD;

        } catch (Exception error) {
            System.out.println("UPPS error" + error);

            return null;
        }
    }

    public boolean actualizarRegistro(ModelRecord registro) {

        Connection conexion = conectarBD_HemaSoft();
        String queryregistro = "UPDATE registro SET placa=?,cedula=?,hora_ingreso=?,hora_salida=?,estado=?,posicion=? WHERE idregistro=?";

        try {

            consultaSQL = conexion.prepareStatement(queryregistro);

            consultaSQL.setString(1, registro.getPlaca());
            consultaSQL.setInt(2, registro.getCedula());
            consultaSQL.setString(3, registro.getHora_ingreso());
            consultaSQL.setString(4, registro.getHora_salida());
            consultaSQL.setString(5, registro.getEstado());
            consultaSQL.setInt(6, registro.getPosicion());
            consultaSQL.setInt(7, registro.getIdregistro());

            int resultado = consultaSQL.executeUpdate();

            if (resultado > 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception error) {
            System.out.println("ey error" + error);

            return false;
        }

    }

    public boolean liquidarRegistro(ModelRecord registro) {

        Connection conexion = conectarBD_HemaSoft();
        String queryregistro = "UPDATE registro SET placa=?,cedula=?,hora_ingreso=?,hora_salida=?,estado=?,posicion=? WHERE idregistro=?";

        try {

            consultaSQL = conexion.prepareStatement(queryregistro);

            consultaSQL.setString(1, registro.getPlaca());
            consultaSQL.setInt(2, registro.getCedula());
            consultaSQL.setString(3, registro.getHora_ingreso());
            consultaSQL.setString(4, registro.getHora_salida());
            consultaSQL.setString(5, "inactivo");
            consultaSQL.setInt(6, registro.getPosicion());
            consultaSQL.setInt(7, registro.getIdregistro());

            int resultado = consultaSQL.executeUpdate();

            if (resultado > 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception error) {
            System.out.println("ey error" + error);

            return false;
        }

    }
}
