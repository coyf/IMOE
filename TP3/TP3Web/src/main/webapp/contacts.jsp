<%@include file="includes/header.jsp" %>

<center>
<!-- A completer -->
    <table border="1">
        <tr>
            <td><b>Nom</b></td>
            <td><b>Prénom</b></td>
            <td><b>Téléphones</b></td>
            <td><b>[COMMANDES]</b></td>
        </tr>
        <c:forEach var="contact" items="${requestScope.contacts}">
            <tr>
                <td>${contact.getNom()}</td>
                <td>${contact.getPrenom()}</td>
                <td>
                <c:forEach var="tel" items="${contact.getTelephones()}">
                    ${tel.getValeur()}
                    <br/>
                </c:forEach>
                </td>
                <td><a href="#">Editer</a><br/><a href=""> Supprimer</a></td>
            </tr>
        </c:forEach>
    </table>
    <a href="addContact">Ajouter un nouveau contact</a>
</center>

<%@include file="includes/footer.jsp" %>
