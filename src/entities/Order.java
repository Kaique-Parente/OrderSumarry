package entities;

import entities.enums.OrderStatus;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private LocalDateTime moment;
    private OrderStatus status;
    
    private Client client;
    List<OrderItem> items = new ArrayList<>();
    
    private static DateTimeFormatter fm1 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private static DateTimeFormatter fm2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    public Order() {}

    public Order(LocalDateTime moment, OrderStatus status, Client client) {
        this.moment = moment;
        this.status = status;
        this.client = client;
    }

    public LocalDateTime getMoment() {
        return moment;
    }

    public void setMoment(LocalDateTime moment) {
        this.moment = moment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }
    
    public void removeItem(OrderItem item) {
        items.remove(item);
    }
    
    public Double total() {
        double sum = 0;
        for(OrderItem c : items) {
             sum += c.subTotal();
        }
        return sum;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Order moment: " + moment.format(fm1) + "\n");
        sb.append("Order status: " + status + "\n");
        sb.append(String.format("Client: %s (%s) - %s\n", client.getName(), client.getBirthDate().format(fm2), client.getEmail()));
        sb.append("Order items:\n");
        for(OrderItem c : items) {
            sb.append(String.format("%s, $%.2f, Quantity: %d, Subtotal: $%.2f\n", c.getProduct().getName(), c.getPrice(), c.getQuantity(), c.subTotal()));
        }
        sb.append(String.format("Total price: $%.2f", total()));
        
        return sb.toString();
    }
}
