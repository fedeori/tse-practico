package uy.edu.fing.webapp;

import uy.edu.fing.practico.business.dto.ResourceTypeDto;
import uy.edu.fing.practico.business.service.ResourceTypeService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("resourcetypes")
class ResourceTypes extends HttpServlet {
    @EJB
    ResourceTypeService resourceTypeService;

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String priceString = request.getParameter("price");
        double price = Double.parseDouble(priceString);

        ResourceTypeDto dto = new ResourceTypeDto();
        dto.setName(name);
        dto.setDecription(description);
        dto.setReferencePrice(price);

        try {
            resourceTypeService.addResourceType(dto);
            response.sendRedirect(request.getContextPath());
        } catch (Exception e) {
            PrintWriter writer = response.getWriter();
            writer.println("<html>NOT Created!" +
                    "<a href=index.jsp>BACK</a>" +
                    "</html>");
            writer.flush();
        }
    }
}
