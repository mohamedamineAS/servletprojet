package com.xadmin.servlet_p.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xadmin.servlet_p.bean.medicine;
import com.xadmin.servlet_p.dao.medicineDAO;

@WebServlet("/medicines")
public class medicineServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private medicineDAO medicineDao;

    public medicineServlet() {
        super();
    }

    public void init() throws ServletException {
        medicineDao = new medicineDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertMedicine(request, response);
                    break;
                case "/delete":
                    deleteMedicine(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateMedicine(request, response);
                    break;
                default:
                    listMedicines(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("medicine-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id")); 
        medicine existingMedicine = medicineDao.selectMedicine(id);

        // Envoyer le médicament récupéré à la page JSP pour affichage dans le formulaire d'édition
        RequestDispatcher dispatcher = request.getRequestDispatcher("medicine-form.jsp");
        request.setAttribute("medicine", existingMedicine);
        dispatcher.forward(request, response);
    }

    private void insertMedicine(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String medId = request.getParameter("med_id");
        String medName = request.getParameter("med_name");
        String medCompName = request.getParameter("med_comp_name");
        
        int medQuantity = Integer.parseInt(request.getParameter("med_quantity"));
        int medPrice = Integer.parseInt(request.getParameter("med_price"));

        medicine newMedicine = new medicine(medId, medName, medCompName, medQuantity, medPrice);
        medicineDao.insertMedicine(newMedicine);
        response.sendRedirect("medicines");
    }

    private void updateMedicine(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        
        String medId = request.getParameter("med_id");
        String medName = request.getParameter("med_name");
        String medCompName = request.getParameter("med_comp_name");
        
        int medQuantity = Integer.parseInt(request.getParameter("med_quantity"));
        int medPrice = Integer.parseInt(request.getParameter("med_price"));

        medicine med = new medicine(medId, medName, medCompName, medQuantity, medPrice);
        med.setId(id);
        medicineDao.updateMedicine(med);
        response.sendRedirect("medicines");
    }

    private void deleteMedicine(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        medicineDao.deleteMedicine(id);
        response.sendRedirect("medicines");
    }

    private void listMedicines(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<medicine> listMedicines = medicineDao.selectAllMedicines();
        request.setAttribute("listMedicines", listMedicines);
        RequestDispatcher dispatcher = request.getRequestDispatcher("medicine-list.jsp");
        dispatcher.forward(request, response);
    }	
}
