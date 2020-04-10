package uy.edu.fing.consola;

import uy.edu.fing.practico.business.dto.ResourceDto;
import uy.edu.fing.practico.business.dto.ResourceTypeDto;
import uy.edu.fing.practico.business.service.ResourceServiceRemote;
import uy.edu.fing.practico.business.service.ResourceTypeServiceRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.InputMismatchException;

import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    private static ResourceServiceRemote resourceServiceRemote;
    private static ResourceTypeServiceRemote resourceTypeServiceRemote;
    private static Scanner scan;
    private static Context ctx;

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        props.put(Context.PROVIDER_URL, "http-remoting://127.0.0.1:8080");
        props.put(Context.SECURITY_PRINCIPAL, "admin");
        props.put(Context.SECURITY_CREDENTIALS, "admin");

        try {
            ctx = new InitialContext(props);

            // https://stackoverflow.com/a/40734257/3508426
            resourceTypeServiceRemote = (ResourceTypeServiceRemote) ctx.lookup("" +
                    "ear-1.0-SNAPSHOT/business-1.0-SNAPSHOT/ResourceTypeController!uy.edu.fing.practico.business.service.ResourceTypeServiceRemote");
            resourceServiceRemote = (ResourceServiceRemote) ctx.lookup(
                    "ear-1.0-SNAPSHOT/business-1.0-SNAPSHOT/ResourceController!uy.edu.fing.practico.business.service.ResourceServiceRemote");


        } catch (NamingException ex) {
            System.out.println("Ha ocurrido un error al conectarse con el EJB");
            ex.printStackTrace();
            System.exit(1);
        }

        scan = new Scanner(System.in);

        menu();
    }

    private static void menu() {
        int opt = 0;

        do {
            printMenu();

            try {
                opt = scan.nextInt();

            } catch (InputMismatchException ex) {
                System.out.println();
                System.out.println("not a number");
                System.out.println();

                // scan.nextLine() is used to ignore the non-int readed.
                scan.nextLine();
                continue;
            }

            switch (opt) {
                case 1:
                    handleOption1();
                    break;

                case 2:
                    handleOption2();
                    break;

                case 3:
                    handleOption3();
                    break;

                case 4:
                    handleOption4();
                    break;
            }

        } while (opt != 5);
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("    1- Add Resource");
        System.out.println("    2- List resource types");
        System.out.println("    3- List resources");
        System.out.println("    4- List resources of resource type");
        System.out.println("    5- exit");
        System.out.println();
        System.out.print("opt: ");
    }

    private static void handleOption1() {
        List<ResourceTypeDto> resourceTypeDtos = resourceTypeServiceRemote.listResourceTypes();

        if (resourceTypeDtos.isEmpty()) {
            System.out.println();
            System.out.println("No resource types.");
            System.out.println();

        } else {
            int idResourceType = printResourceTypesAndSelectId(resourceTypeDtos);
            ResourceDto dto = new ResourceDto();


            System.out.print("Code: ");
            String name = scan.nextLine();
            dto.setCode(name);

            System.out.print("Qtty:");
            Integer qtty = scan.nextInt();
            dto.setQuantity(qtty);

            System.out.print("Unit Price:");
            Double unitPrice = scan.nextDouble();
            dto.setUnitPrice(unitPrice);


            try {
                resourceServiceRemote.addResource(dto, idResourceType);
            } catch (Exception ex) {
                System.out.println();
                System.out.println("[ERROR] " + ex.getMessage());
            }
        }
    }

    private static void handleOption2() {
        List<ResourceTypeDto> resourceTypeDtos = resourceTypeServiceRemote.listResourceTypes();

        if (resourceTypeDtos.isEmpty()) {
            System.out.println();
            System.out.println("No resource types.");
            System.out.println();

        } else {
            System.out.println();
            for (ResourceTypeDto n : resourceTypeDtos) {
                System.out.println(n);
            }
        }
    }

    private static void handleOption3() {
        List<ResourceDto> resourceDtos = resourceServiceRemote.listResource();

        if (resourceDtos.isEmpty()) {
            System.out.println();
            System.out.println("No resources");
            System.out.println();

        } else {
            System.out.println();
            for (ResourceDto n : resourceDtos) {
                System.out.println(n);
            }
        }
    }

    private static void handleOption4() {
        List<ResourceTypeDto> resourceTypeDtos = resourceTypeServiceRemote.listResourceTypes();
        if (resourceTypeDtos.isEmpty()) {
            System.out.println();
            System.out.println("No resource types.");
            System.out.println();

        } else {
            int idResourceType = printResourceTypesAndSelectId(resourceTypeDtos);
            List<ResourceDto> resourceDtos = resourceServiceRemote.listResource(idResourceType);
            if (resourceDtos.isEmpty()) {
                System.out.println();
                System.out.println("No resources");
                System.out.println();
            } else {
                System.out.println();
                for (ResourceDto n : resourceDtos) {
                    System.out.println(n);
                }
            }
        }
    }

    private static Integer printResourceTypesAndSelectId(List<ResourceTypeDto> resourceDtos) {
        System.out.println();
        for (ResourceTypeDto n : resourceDtos) {
            System.out.println(n);
        }
        System.out.println();

        System.out.print("Select id: ");
        int id = scan.nextInt();
        scan.nextLine();

        return id;
    }
}
