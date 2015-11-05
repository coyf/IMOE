package fr.sigl.imoe.tp3.servlets;

import fr.sigl.imo.tp3.bean.ContactManagerRemote;
import fr.sigl.imoe.tp3.delegate.ContactsServiceDelegate;
import fr.sigl.imoe.tp3.delegate.mock.ContactsServiceDelegateMock;
import fr.sigl.imoe.tp3.dto.Contact;
import fr.sigl.imoe.tp3.dto.Telephone;

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
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;


/**
 * Created by flo on 02/11/15.
 */
@WebServlet(name = "ContactListServlet",
            urlPatterns = {"/addContact", "/contacts", "/createContact", "/deleteContact"})
public class ContactServlet extends HttpServlet {

    // Pour utiliser le bouchon
    @Inject
    ContactsServiceDelegateMock mock = new ContactsServiceDelegateMock();

    /**
     * Injection de l'EJB ContactManagerRemote.
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

    protected Telephone ajouterTelephone(String phone_str, String phone_type_str)
    {
        Telephone tel = manager.creerTelephone(phone_str, phone_type_str);
        return tel;
    }

    protected void listerContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupération des contacts en base
        request.setAttribute("contacts", manager.listerTousContacts());
        request.getRequestDispatcher("/contacts.jsp").forward(request, response);
    }

    protected void ajouterContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("typesTelephone", manager.listerTousTypesTelephone());
        request.getRequestDispatcher("/add_contact.jsp").forward(request, response);
    }

    protected void createContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("contactAdded", true);
        List<Telephone> telephones = new ArrayList<>();
        String phone = request.getParameter("phone_1");
        String phone_type = request.getParameter("phone_1_type");
        // On itère sur chacun des numéros de téléphones ajoutés
        int x = 2;
        while (phone != null && phone != ""){
            Telephone tel = ajouterTelephone(phone, phone_type);
            telephones.add(tel);
            phone = request.getParameter("phone_" + x);
            phone_type = request.getParameter("phone_" + x + "_type");
            x++;
        }
        manager.creerContact(request.getParameter("contact_lastname"), request.getParameter("contact_firstname"), telephones);
        listerContact(request, response);
    }

    protected void supprimerContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        manager.supprimerContact(Integer.parseInt(id));
        listerContact(request, response);
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
            if (url_str.contains("?"))
                url_str = url_str.substring(0, url_str.indexOf('?'));

            System.out.println("ContactServlet : url_str : " + url_str);

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
