<%@include file="includes/header.jsp" %>

<form action="${pageContext.request.contextPath}/createContact" method="post">
    <table>
    <tr>
        <td>Nom :</td>
        <td><input type="text" name="contact_lastname"/></td>
    </tr>
    <tr>
        <td>Prenom :</td>
        <td><input type="text" name="contact_firstname"/></td>
    </tr>
    <tr>
        <td>Telephone :</td>
        <td> <input type="text" name="contact_phone"/></td>
    </tr>
    </table>
    <hr/>
    <input type="submit" value="Ajouter"/>
</form>
<br>


<%@include file="includes/footer.jsp" %>
