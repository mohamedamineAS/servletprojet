package com.xadmin.servlet_p.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xadmin.servlet_p.bean.User;
import com.xadmin.servlet_p.dao.UserDAO;

/**
 * Servlet implementation class InsertUser
 */
@WebServlet("/InsertUser")
public class InsertServle extends HttpServlet {
    private static final long serialVersionUID = 1L;
     
 
         
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            // Récupérer les données du formulaire
            int id = Integer.parseInt(request.getParameter("id"));
            String uname = request.getParameter("uname");
            String upwd = request.getParameter("upwd");
            String uemail = request.getParameter("uemail");
            String umobile = request.getParameter("umobile");

            // Créer un nouvel objet utilisateur
            User newUser = new User(id, uname, upwd, uemail, umobile);
            
            // Insérer l'utilisateur dans la base de données
            UserDAO userDAO = new UserDAO();
            try {
                userDAO.insertUser(newUser);
            } catch (SQLException e) {
                // Gérer les erreurs de la base de données lors de l'insertion de l'utilisateur
                e.printStackTrace();
                // Vous pouvez rediriger vers une page d'erreur appropriée ici
            }
            
            // Redirection vers la page des utilisateurs après l'insertion
           
          
           response.sendRedirect("users");
        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            // Appeler doGet pour traiter les requêtes POST
            doGet(request, response);
        }
    }


