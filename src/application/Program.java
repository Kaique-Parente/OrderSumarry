package application;

import entities.Client;
import entities.Order;
import entities.OrderItem;
import entities.Product;
import entities.enums.OrderStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Scanner;

import java.time.format.DateTimeFormatter;

public class Program {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner e = new Scanner(System.in);
        
        DateTimeFormatter fm1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        System.out.println("Enter cliente data:");
        System.out.print("Name: ");
        String name = e.nextLine();
        System.out.print("Email: ");
        String email = e.next();
        System.out.print("Birth date (DD/MM/YYYY): ");
        LocalDate birthDate = LocalDate.parse(e.next(),fm1);
        Client client = new Client(name, email, birthDate);
        
        System.out.println("Enter order data:");
        System.out.print("Status: ");
        OrderStatus status = OrderStatus.valueOf(e.next());
        LocalDateTime moment = LocalDateTime.now();
        Order order = new Order(moment, status, client);
        
        System.out.print("How many items to this order? ");
        int n = e.nextInt();
        e.nextLine();
        
        OrderItem orderItem = new OrderItem();
        
        for (int i = 0; i < n; i++) {
            System.out.println("");
            System.out.println("Enter #" + (i + 1) + " item data:");
            System.out.print("Product name: ");
            String productName = e.nextLine();
            System.out.print("Product price: ");
            double price = e.nextDouble();
            
            Product product = new Product(productName, price);
            
            System.out.print("Quantity: ");
            int quantity = e.nextInt();
            e.nextLine();
            
            orderItem = new OrderItem(quantity, price, product);
            order.addItem(orderItem);
        }
        System.out.println("");
        System.out.println("ORDER SUMMARY:");
        System.out.println(order);
    }
}