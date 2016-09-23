package es.palma.provajsp1.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.palma.provajsp1.dao.UserDAO;
import es.palma.provajsp1.dao.UserDAOImpl;
import es.palma.provajsp1.entities.UserBean;

/**
 * Servlet implementation class ProvaServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public LoginServlet() {
       System.out.println("################################## LoginServlet inicialitzat ###################################");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	System.out.println("################## LoginServlet processRequest ######################");
       
        UserBean usuari =new UserBean();
        usuari.setId(request.getParameter("txtUserName"));
        usuari.setPassword(request.getParameter("txtPass"));
        UserDAO userDAO=new UserDAOImpl();
        usuari=userDAO.login(usuari);
        
        if (usuari.isValid()) {
        	System.out.println("################################## LoginServlet Success ###################################");
              RequestDispatcher rd=request.getRequestDispatcher("/pages/success.jsp");
            request.setAttribute("uname", usuari.getLl1());
            rd.forward(request, response);
        }
        else
        	/* if name&pass not match then it display error page */
        {
        	System.out.println("################################## LoginServlet Error ###################################");
        	request.getRequestDispatcher("/pages/error.jsp").forward(request, response);
        }

    }




    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

}
