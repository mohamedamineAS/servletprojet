package com.xadmin.servlet_p.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xadmin.servlet_p.dao.medicineDAO;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private medicineDAO medicineDao;

    public void init() {
        medicineDao = new medicineDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if(idParam != null && !idParam.isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                medicineDao.deleteMedicine(id);
            } catch (NumberFormatException e) {
                // Handle case where the ID is not a valid integer
                e.printStackTrace();
            } catch (SQLException e) {
                // Handle SQL errors during the deletion
                e.printStackTrace();
            }
        } else {
            // Handle case where no ID is provided in the request
        }
        response.sendRedirect("medicines"); // Redirect to the list of medicines
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
