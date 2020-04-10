<%@ page import="java.util.Properties" %>
<%@ page import="javax.naming.Context" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="uy.edu.fing.practico.business.service.ResourceTypeServiceRemote" %>
<%@ page import="uy.edu.fing.practico.business.service.ResourceServiceRemote" %>
<%@ page import="javax.naming.NamingException" %>
<%@ page import="uy.edu.fing.practico.business.dto.ResourceTypeDto" %>
<%@ page import="uy.edu.fing.practico.business.dto.ResourceDto" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
          crossorigin="anonymous">
</head>
<body>

<%
    Properties props = new Properties();
    props.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
    props.put(Context.PROVIDER_URL, "http-remoting://127.0.0.1:8080");
    props.put(Context.SECURITY_PRINCIPAL, "admin");
    props.put(Context.SECURITY_CREDENTIALS, "admin");

    ResourceTypeServiceRemote resourceTypeServiceRemote;
    ResourceServiceRemote resourceServiceRemote;
    List<ResourceTypeDto> resourceTypeDtoList = new ArrayList<>();
    List<ResourceDto> resourceDtoList = new ArrayList<>();
    try {
        InitialContext ctx = new InitialContext(props);
        // https://stackoverflow.com/a/40734257/3508426
        resourceTypeServiceRemote = (ResourceTypeServiceRemote) ctx.
                lookup("practico/uy.edu.fing-business-1.0-SNAPSHOT/ResourceTypeController!uy.edu.fing.practico.business.service.ResourceTypeServiceRemote");
        resourceServiceRemote = (ResourceServiceRemote) ctx.
                lookup("practico/uy.edu.fing-business-1.0-SNAPSHOT/ResourceController!uy.edu.fing.practico.business.service.ResourceServiceRemote");
        resourceTypeDtoList = resourceTypeServiceRemote.listResourceTypes();
        resourceDtoList = resourceServiceRemote.listResource();

%>
<div class="row">
    <div class="col" align=center>
        <h2>Create Resource</h2>
        <form action="resources" method="post">
            <form action="resources" method="post">
                <div class="form-row">
                    <div class="col">
                        <select name="id" class="custom-select" required>
                            <option selected disabled>Select Resource Type</option>
                            <% for (ResourceTypeDto n : resourceTypeDtoList) {%>
                            <option value=<%=n.getId()%>><%=n.getName()%>
                            </option>
                            <% }%>
                        </select>
                    </div>
                    <div class="col">
                        <input type="number" class="form-control" name="price" placeholder="Unit Price" required>
                    </div>
                </div>
                <br>
                <div class="form-row">
                    <div class="col">
                        <input type="number" class="form-control" name="quantity" placeholder="Quantity" required>
                    </div>
                    <div class="col">
                        <input type="text" class="form-control" name="code" placeholder="Code" required>
                    </div>
                </div>
                <br>
                <button type="submit" class="btn btn-primary">Create</button>
            </form>

        </form>
    </div>
    <div class="col" align=center>
        <h2>Create Resource Type</h2>
        <form action="resourcetypes" method="post">
            <fieldset disabled>
                <div class="form-row">
                    <div class="col">
                        <input type="text" class="form-control" name="name" placeholder="Name">
                    </div>
                    <div class="col">
                        <input type="number" class="form-control disabled" name="price"
                               placeholder="Ref Price">
                    </div>
                </div>
                <br>
                <div class="form-row">
                    <div class="col">
                        <input type="text" class="form-control disabled" name="description"
                               placeholder="Description">
                    </div>
                </div>
                <br>
                <button type="submit" class="btn btn-primary disabled">Create</button>
            </fieldset>
        </form>
    </div>
</div>
<hr>
<div class="row">
    <div class="col">
        <h2 align="center">Resources</h2>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Code</th>
                <th scope="col">Quantity</th>
                <th scope="col">Unit Price</th>
                <th scope="col">Resource Type</th>
            </tr>
            </thead>
            <tbody>
            <% for (ResourceDto n : resourceDtoList) {%>
            <tr>
                <th><%=n.getCode()%>
                </th>
                <th><%=n.getQuantity()%>
                </th>
                <th><%=n.getUnitPrice()%>
                </th>
                <th><%=n.getResourceTypeName()%>
                </th>
            </tr>
            <% }%>
            </tbody>
        </table>
    </div>
    <div class="col">
        <h2 align="center">Resource Types</h2>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Name</th>
                <th scope="col">Description</th>
                <th scope="col">Ref. Price</th>
            </tr>
            </thead>
            <tbody>
            <% for (ResourceTypeDto n : resourceTypeDtoList) {%>
            <tr>
                <th><%=n.getName()%>
                </th>
                <th><%=n.getDecription()%>
                </th>
                <th><%=n.getReferencePrice()%>
                </th>
            </tr>
            <% }%>
            </tbody>
        </table>
    </div>
</div>
<%} catch (NamingException ex) {%>
<h4>Ha ocurrido un error al conectarse con el EJB</h4>
<p style="color: red">Verificar JNDI en consola</p>
<% }%>

</body>
</html>

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
<script
        src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script
        src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>

