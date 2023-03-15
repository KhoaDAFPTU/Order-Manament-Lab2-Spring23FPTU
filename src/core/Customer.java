/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

/**
 *
 * @author DLCT
 */
public class Customer {
    private String customerId;
    private String customerName;
    private String customerAddress;
    private String customerPhone;

    public Customer() {
    }
    
    public Customer(String customerId) {
        this.customerId = customerId;
    }

    
    public Customer(String customerId, String customerName, String customerAddress, String customerPhone) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPhone = customerPhone;
    }
    
      @Override
    public boolean equals(Object obj) {
        Customer b = (Customer) obj;
        return this.customerId.equalsIgnoreCase(b.customerId);
    }

    @Override
    public String toString() {
        return customerId + "," + customerName + "," + customerAddress + "," + customerPhone;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }
}
