package fr.sigl.imoe.tp3.servlets;

import fr.sigl.imo.tp3.bean.ContactManagerRemote;
import fr.sigl.imoe.tp3.delegate.ContactsServiceDelegate;
import fr.sigl.imoe.tp3.delegate.mock.ContactsServiceDelegateMock;
import fr.sigl.imoe.tp3.dto.Contact;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;


/**
 * Created by flo on 02/11/15.
 */
@WebServlet(name = "ContactListServlet",
            urlPatterns = {"/addContact", "/contacts", "/createContact"})
public class ContactServlet extends HttpServlet {

    // Pour utiliser le bouchon
    @Inject
    ContactsServiceDelegateMock mock = new ContactsServiceDelegateMock();

    /**
     * Injection de l'EJB PersonManagerRemote.
     */
    @EJB
    private ContactManagerRemote manager;

    /**
     * Méthode d'initialisation de la Servlet.
     *
     * @param config	Informations de configuration de la servlet.
     * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void listerContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("contacts", mock.listerTousContacts());
        request.getRequestDispatcher("/contacts.jsp").forward(request, response);
    }

    protected void ajouterContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/add_contact.jsp").forward(request, response);
    }

    protected void createContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("contactAdded", true);
        // TODO : ajouter la liste des téléphonnes (pour l'instant mis à null)
        manager.creerContact(request.getParameter("contact_lastname"), request.getParameter("contact_firstname"), null);
        request.getRequestDispatcher("/contacts.jsp").forward(request, response);
    }

    protected void supprimerContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/delete_contact.jsp").forward(request, response);
    }

    protected void editerContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/edit_contact.jsp").forward(request, response);
    }


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //request.setAttribute("types", ...);
            // lecture URL puis dispatche vers le bon jsp
            String url_str = request.getRequestURL().toString().substring(request.getRequestURL().toString().lastIndexOf("/"));
            if (url_str == null)
                url_str = "/";
            //System.out.println("url inc : " + url_str);
            switch (url_str){
                case "/contacts" :
                    listerContact(request, response);
                    break;
                case "/addContact":
                    ajouterContact(request, response);
                    break;
                case "/createContact":
                    createContact(request, response);
                    break;
                case "/deleteContact":
                    supprimerContact(request, response);
                    break;
                case "/editContact":
                    editerContact(request, response);
                    break;
                default:
                    listerContact(request, response);
            }

        } catch (Exception e) {
            throw new ServletException("Impossible d'afficher la page de création. Cause : " + e.getMessage());
        }
    }
}
