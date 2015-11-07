<%@include file="includes/header.jsp" %>

<script src="<c:url value="includes/jquery-1.11.3.min.js"/>"></script>

<script>
    // Script en JQuery permettant l'ajout dynamique de nouvelle lignes de formulaires
    // pour un renseigner un nouveau contact telephonique
    $(document).ready(function() {
        var max_fields      = 10; //maximum input boxes allowed
        var wrapper         = $(".input_fields_wrap"); //Fields wrapper
        var add_button      = $(".add_field_button"); //Add button ID
        var x = ${requestScope.nbTelephone}; //initlal text box count
        $(add_button).click(function(e){ //on add input button click
            e.preventDefault();
            if(x < max_fields){ //max input box allowed
                x++; //text box increment
                var phone_name = "phone_" + x;
                $(wrapper).append('' +
                '<tr>' +
                '<td></td>' +
                '<td>' +
                '<select name=\"' +
                phone_name +
                '_type\">' +
                '<c:forEach var="type" items="${requestScope.typesTelephone}">' +
                '<option value="${type}">${type}</option>' +
                '</c:forEach>' +
                '</select>' +
                '</td>' +
                '<td><input type="text" name=\"' +
                phone_name +
                '\" placeholder="autre telephone"/><button class="remove_field">-</button></td>' +
                '</tr>');
            }
        });

        $(wrapper).on("click",".remove_field", function(e){ //user click on remove text
            e.preventDefault(); $(this).parent('td').parent('tr').remove(); x--;
        })
    });
</script>

<form action="${pageContext.request.contextPath}/updateContact?id=${requestScope.contactSelected.getId()}" method="post">
    <table class="input_fields_wrap">
        <tr>
            <td>Nom :</td>
            <td><input type="text" name="contact_lastname" value="${requestScope.contactSelected.getNom()}"/></td>
            <td></td>
        </tr>
        <tr>
            <td>Prenom :</td>
            <td><input type="text" name="contact_firstname" value="${requestScope.contactSelected.getPrenom()}"/></td>
            <td></td>
        </tr>
        <tr>
            <td>Telephone :<td>
            <td></td>
            <td></td>
        </tr>
        <c:set var="count" value="1" scope="page" />
        <c:forEach var="telephone" items="${requestScope.contactTelephones}">
        <tr>
            <td></td>
            <td>
                <select name="phone_${count}_type">
                    <c:forEach var="type" items="${requestScope.typesTelephone}">
                        <c:choose>
                            <c:when test="${type==telephone.getType()}">
                                <option value="${type}" selected>${type}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${type}">${type}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </td>
            <td>
                <input type="text" name="phone_${count}" value="${telephone.getValeur()}">
            </td>
        </tr>
            <c:set var="count" value="${count + 1}" scope="page"/>
        </c:forEach>
        <tr>
            <td></td>
            <td></td>
            <td>
                <button class="add_field_button">+</button>
            </td>
        </tr>
    </table>
    <hr/>
    <input type="submit" value="Modifier"/>
</form>
<br>

<%@include file="includes/footer.jsp" %>
