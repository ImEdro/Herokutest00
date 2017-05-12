package com.crunchify.jsp.servlet;
 
import edu.co.sergio.mundo.dao.Obra_de_ArteDAO;
import edu.co.sergio.mundo.vo.Obra_de_Arte;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import javax.servlet.RequestDispatcher;
 
/**
 * @author Crunchify.com
 */
 
public class HelloCrunchify extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // reading the user input
        
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        String estilo = request.getParameter("estilo");
        String id = request.getParameter("valor");
        
        
        //Se debe incluir validaciones - Lo recuerda: Gestion de Excepciones.
        Obra_de_ArteDAO dao = new Obra_de_ArteDAO();
        
        Obra_de_Arte departamento = new Obra_de_Arte();
        departamento.setValor(Integer.parseInt(id));
        departamento.setNombre(nombre);
        departamento.setDescripcion(descripcion);
        departamento.setEstilo(estilo);
        dao.insert(departamento);
        
        //Listando la informacion  
        List<Obra_de_Arte> departamentos =  dao.findAll();
        request.setAttribute("departamentos", departamentos);
       
       
        //Redireccionando la informacion
        RequestDispatcher redireccion = request.getRequestDispatcher("index.jsp");
        redireccion.forward(request, response);
        
        
        }
}
