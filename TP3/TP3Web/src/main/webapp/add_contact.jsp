<%@include file="includes/header.jsp" %>
<script src="<c:url value="includes/jquery-1.11.3.min.js"/>"></script>

<script>
    // Script en JQuery permettant l'ajout dynamique de nouvelle lignes de formulaires
    // pour un renseigner un nouveau contact telephonique
    $(document).ready(function() {
        var max_fields      = 10; //maximum input boxes allowed
        var wrapper         = $(".input_fields_wrap"); //Fields wrapper
        var add_button      = $(".add_field_button"); //Add button ID
        var x = 1; //initlal text box count
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

<form action="${pageContext.request.contextPath}/createContact" method="post">
    <table class="input_fields_wrap">
    <tr>
        <td>Nom :</td>
        <td><input type="text" name="contact_lastname"/></td>
        <td></td>
    </tr>
    <tr>
        <td>Prenom :</td>
        <td><input type="text" name="contact_firstname"/></td>
        <td></td>
    </tr>
    <tr>
        <td>Telephone :</td>
        <td>
            <select name="phone_1_type">
                <c:forEach var="type" items="${requestScope.typesTelephone}">
                    <option value="${type}">${type}</option>
                </c:forEach>
            </select>
        </td>
        <td>
            <input type="text" name="phone_1" placeholder="telephone principal">
            <button class="add_field_button">+</button>
        </td>
    </tr>
    </table>
    <hr/>
    <input type="submit" value="Ajouter"/>
</form>
<br>


<%@include file="includes/footer.jsp" %>
