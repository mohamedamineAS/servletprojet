package com.xadmin.servlet_p.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xadmin.servlet_p.bean.medicine;
import com.xadmin.servlet_p.dao.medicineDAO;

/**
 * Servlet implementation class InsertServlet
 */
@WebServlet("/InsertServlet")
public class InsertServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private medicineDAO medicineDao;

    public void init() {
        medicineDao = new medicineDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer les données du formulaire
        String medId = request.getParameter("med_id");
        String medName = request.getParameter("med_name");
        String medCompName = request.getParameter("med_comp_name");
        
        int medQuantity = 0;
        if (request.getParameter("med_quantity") != null && !request.getParameter("med_quantity").isEmpty()) {
            medQuantity = Integer.parseInt(request.getParameter("med_quantity"));
        }
        
        int medPrice = 0;
        if (request.getParameter("med_price") != null && !request.getParameter("med_price").isEmpty()) {
            medPrice = Integer.parseInt(request.getParameter("med_price"));
        }

        // Créer un nouvel objet medicine
        medicine newMedicine = new medicine(medId, medName, medCompName, medQuantity, medPrice);
        
        // Insérer le medicine dans la base de données
        try {
            medicineDao.insertMedicine(newMedicine);
        } catch (SQLException e) {
            // Gérer les erreurs de la base de données lors de l'insertion du medicine
            e.printStackTrace();
            // Vous pouvez rediriger vers une page d'erreur appropriée ici
        }
        
        // Redirection vers la page des medicines après l'insertion
        response.sendRedirect("medicines");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Appeler doGet pour traiter les requêtes POST
        doGet(request, response);
    }
}
