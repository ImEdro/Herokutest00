package edu.co.sergio.mundo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import edu.co.sergio.mundo.vo.Obra_de_Arte;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Isabel-Fabian
 * @since 12/08/2015
 * @version 2
 * Clase que permite la gestion de la tabla Depto en la base de datos.
 * 
 * CREATE TABLE Depto(
 *     id_depto integer,
 *     nom_depto varchar(40),
 *     PRIMARY KEY(id_depto)
 * );
 */
 

public class Obra_de_ArteDAO implements IBaseDatos<Obra_de_Arte> {

	/**
	 * Funcion que permite obtener una lista de los departamentos existentes en la base de datos
	 * @return List<Departamento> Retorna la lista de Departamentos existentes en la base de datos
	 */
	public List<Obra_de_Arte> findAll() {
		List<Obra_de_Arte> departamentos= null;
	    String query = "SELECT * FROM Obra_de_Arte";
	    Connection connection = null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(Obra_de_ArteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	    try {
	    Statement st = connection.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    int valor =0;
	    String nombre = null;
            String descripcion = null;
            String estilo = null;
	
	    while (rs.next()){
	    	if(departamentos == null){
	    		departamentos= new ArrayList<Obra_de_Arte>();
	    	}
	      
	        Obra_de_Arte registro= new Obra_de_Arte();
	        
	        nombre = rs.getString("nombre");
	        registro.setNombre(nombre) ;
	        descripcion = rs.getString("descripcion");
	        registro.setDescripcion(descripcion) ;
                estilo = rs.getString("estilo");
	        registro.setEstilo(estilo) ;
                valor = rs.getInt("valor");
	        registro.setValor(valor);
                
	        departamentos.add(registro);
	    }
	    st.close();
	    
	    } catch (SQLException e) {
			System.out.println("Problemas al obtener la lista de Departamentos");
			e.printStackTrace();
		}
	    
	    return departamentos;
	}

	
	/**
	 * Funcion que permite realizar la insercion de un nuevo registro en la tabla Departamento
	 * @param Departamento recibe un objeto de tipo Departamento 
	 * @return boolean retorna true si la operacion de insercion es exitosa.
	 */
	public boolean insert(Obra_de_Arte t) {
		boolean result=false;
		Connection connection=null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(Obra_de_ArteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	    String query = " insert into Obra_de_Arte(nombre,descripcion,estilo,valor)"  + " values (?,?,?,?)";
        PreparedStatement preparedStmt=null;
	    try {
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setString (1, t.getNombre());
                        preparedStmt.setString (2, t.getDescripcion());
                        preparedStmt.setString (3, t.getEstilo());
                        preparedStmt.setInt (4, t.getValor());
			result= preparedStmt.execute();
	    } catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Funcion que permite realizar la actualizacion de un nuevo registro en la tabla Departamento
	 * @param Departamento recibe un objeto de tipo Departamento 
	 * @return boolean retorna true si la operacion de actualizacion es exitosa.
	 */
	public boolean update(Obra_de_Arte t) {
		boolean result=false;
		Connection connection= null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(Obra_de_ArteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
		String query = "update Obra_de_Arte set nom_depto = ? where id_depto = ?";
		PreparedStatement preparedStmt=null;
		try {
		    preparedStmt = connection.prepareStatement(query);
		    preparedStmt.setString(1, t.getNombre());
                    preparedStmt.setInt   (2, t.getValor());
		    if (preparedStmt.executeUpdate() > 0){
		    	result=true;
		    }
			    
		} catch (SQLException e) {
				e.printStackTrace();
		}
			
		return result;
	}

	/**
	 * Funcion que permite realizar la eliminario de registro en la tabla Departamento
	 * @param Departamento recibe un objeto de tipo Departamento 
	 * @return boolean retorna true si la operacion de borrado es exitosa.
	 */
	public boolean delete(Obra_de_Arte t) {
	   boolean result=false;
	   Connection connection = null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(Obra_de_ArteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	   String query = "delete from Obra_de_Arte where id_depto = ?";
	   PreparedStatement preparedStmt=null;
	   try {
		     preparedStmt = connection.prepareStatement(query);
		     preparedStmt.setInt(1, t.getValor());
		    result= preparedStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	   
	   return result;
	}
}
