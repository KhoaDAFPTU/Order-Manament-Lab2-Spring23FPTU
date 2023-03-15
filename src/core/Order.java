/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

public class Order {
    private String orderId;
    private String customerId;
    private String productId;
    private String customerName;
    private int orderQuantity;
    private String orderDate;
    private boolean status;

    
    public Order() {
    }

    public Order(String orderId, String customerId, String customerName) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.customerName = customerName;
    }
    

    public Order(String orderId, String customerId, String productId, int orderQuantity, String orderDate, boolean status) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.productId = productId;
        this.orderQuantity = orderQuantity;
        this.orderDate = orderDate;
        this.status = status;
    }

    public Order(String orderId) {
        this.orderId = orderId;
    }
    
     @Override
    public boolean equals(Object obj) {
        Order o = (Order) obj;
        return this.orderId.equalsIgnoreCase(o.orderId);
    }

    @Override
    public String toString() {
        return orderId + "," + customerId + "," + productId + "," + orderQuantity + ","
                + orderDate + "," + status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
