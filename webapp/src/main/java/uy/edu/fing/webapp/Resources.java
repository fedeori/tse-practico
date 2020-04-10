package uy.edu.fing.webapp;

import uy.edu.fing.practico.business.dto.ResourceDto;
import uy.edu.fing.practico.business.service.ResourceService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("resources")
class Resources extends HttpServlet {
    @EJB
    ResourceService resourceService;

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        String code = request.getParameter("code");
        String priceString = request.getParameter("price");
        String quantityString = request.getParameter("quantity");
        String idString = request.getParameter("id");

        int id = Integer.parseInt(idString);
        int quantity = Integer.parseInt(quantityString);
        double price = Double.parseDouble(priceString);

        ResourceDto dto = new ResourceDto();
        dto.setCode(code);
        dto.setUnitPrice(price);
        dto.setQuantity(quantity);

        try {
            resourceService.addResource(dto, id);
            response.sendRedirect(request.getContextPath());
        } catch (Exception e) {
            PrintWriter writer = response.getWriter();
            writer.println("<html>NOT Created! To expensive" +
                    "<a href=index.jsp>BACK</a>" +
                    "</html>");
            writer.flush();
        }
    }
}
