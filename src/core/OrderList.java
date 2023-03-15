/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import static ordermanager.OrderManager.cuslist;
import static ordermanager.OrderManager.prolist;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import tool.Menu;
import tool.MyUtil;
import tool.StringEx;

/**
 *
 * @author DLCT
 */
public class OrderList{

    List<Order> orders = new ArrayList();

    Scanner sc = new Scanner(System.in);
    private final String fName = "src\\order.txt";

    public OrderList() {
        readFromFile();
    }

    // đọc file ra danh sách book
    public void readFromFile() {
        File f = new File(fName);
        if (!f.exists()) {
            System.out.println("File is not existed!");
            System.exit(0);
        }
        try {
            FileReader fr = new FileReader(f); //giúp đọc 1 ký tự
            BufferedReader bf = new BufferedReader(fr); //đọc 1 dòng
            String line; //biến chứa 1 dòng text
            while ((line = bf.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(line, ",");
                String orderId = stk.nextToken().trim().toUpperCase();
                String customerId = stk.nextToken().trim().toUpperCase();
                String productId = stk.nextToken().trim().toUpperCase();
                int orderQuantity = Integer.parseInt(stk.nextToken().trim().toUpperCase());
                String orderDate = stk.nextToken().trim().toUpperCase();
                boolean status = Boolean.parseBoolean(stk.nextToken().trim());
                Order ord = new Order(orderId, customerId, productId,orderQuantity, orderDate, status);
                orders.add(ord);
            }
            bf.close();
            fr.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void saveToFile() throws Exception {
        if (orders.isEmpty()) {
            System.out.println("Empty list!");
        } else {
            PrintWriter pw = new PrintWriter(fName);
            for (Order o : orders) {
                pw.println(o);
            }
            pw.close();
            System.out.println("Writting file: Done.");
        }
    }
    // Thêm 1 book với data được nhập từ bàn phím

    public void addOrder() {
        String orderId, customerId, productId, orderDate; //các biến chứa data được nhập
        boolean status;
        int orderQuantity;

        while (true) {
            System.out.println("Data of new Orders: ");
            int pos;            
            do {
                orderId = MyUtil.readPattern("ID", "D[\\d]{3}");

                Order oo = new Order(orderId);
                pos = orders.indexOf(oo);
                if (pos < 0) {
                    break;
                }
                System.out.println("ID is duplicated");
            } while (true);
            cuslist.printAll();
            do {
                System.out.print("CustomerId: ");
                customerId = MyUtil.sc.nextLine();
                Customer cust = new Customer(customerId);
                pos = cuslist.cus.indexOf(cust);

                if (pos != -1) {
                    customerId = cuslist.cus.get(pos).getCustomerId();
                    break;
                } else {
                    System.out.println("Enter again!");
                }
            } while (true);
            prolist.printAll();
            do {
                System.out.print("ProductId: ");
                productId = MyUtil.sc.nextLine();
                Product prod = new Product(productId);
                pos = prolist.pro.indexOf(prod);
                if (pos != -1) {
                    productId = prolist.pro.get(pos).getProductId();
                    break;
                } else {
                    System.out.println("Enter again!");
                }
            } while (true);

            orderQuantity = MyUtil.readInt("Quantity", 0);
            System.out.println("Date");
            orderDate = MyUtil.sc.nextLine();
            System.out.println("Status");
            status = Boolean.parseBoolean(MyUtil.sc.nextLine());

            orders.add(new Order(orderId, customerId, productId, orderQuantity, orderDate, status)); //thêm p vào danh sách

            if (askUser("Do you want to continue?")) {
                break;
            }
        }

    }

    public void removeOrder() {
        String ID;  //ID do user nhập
        while (true) {
            System.out.print("ID of removed Orders: ");
            ID = MyUtil.sc.nextLine().trim().toUpperCase();
            Order order = new Order(ID);
            int pos = orders.indexOf(order);
            if (pos < 0) {
                System.out.println("Not found");
            } else {
                orders.remove(pos);
                System.out.println("Removed");
            }

            if (askUser("Do you want to continue?")) {
                break;
            }
        }
    }

    //sửa name, phone 1 pulisher dựa trên ID được nhập
    public void update() {
        String orderId, customerId, productId, orderDate; //các biến chứa data được nhập
        boolean status;
        int orderQuantity;
        printAll();
        while (true) {
            System.out.print("ID of update Order: ");
            orderId = MyUtil.sc.nextLine().trim().toUpperCase();
            Order order = new Order(orderId);
            int pos = orders.indexOf(order);
            if (pos < 0) {
                System.out.println("Not found");
            } else {

                orderId = MyUtil.readPattern("OrderId", "D[\\d]{3}");
//                for (int i = 0; i < orders.size(); i++) {
//                    pos = orderId.indexOf(orders.get(i).getOrderId());
//                    if(pos  < 0 ){
//                        System.out.println("ID is duplicates");
//                    }
//                }
                if (!orderId.isEmpty()) {
                    orders.get(pos).setOrderId(orderId);

                    customerId = MyUtil.readPattern("CusId", "C[\\d]{3}");
                    if (!customerId.isEmpty()) {
                        orders.get(pos).setCustomerId(customerId);
                    }

                    productId = MyUtil.readPattern("ProductId", "P[\\d]{3}");
                    if (!productId.isEmpty()) {
                        orders.get(pos).setProductId(productId);
                    }

                    orderQuantity = MyUtil.readInt("Quantity", 0);
                    if (orderQuantity != -1) {
                        orders.get(pos).setOrderQuantity(orderQuantity);
                    }

                    System.out.print("Date: ");
                    orderDate = MyUtil.sc.nextLine();
                    if (!orderDate.isEmpty()) {
                        orders.get(pos).setOrderDate(orderDate);
                    }

                    System.out.print("Status: ");
                    status = Boolean.parseBoolean(MyUtil.sc.nextLine());
                    if (!customerId.isEmpty()) {
                        orders.get(pos).setStatus(status);
                    }

                    System.out.println("Updated");
                }

                if (askUser("Do you want to continue?")) {
                    break;
                }
            }
        }
    }

    public void updateOrder() throws Exception {
        Menu menuBook = new Menu("Choose option");
        menuBook.addOption("Update Order", "Delete Order", "Exit");
        boolean change = false;
        while (true) {

            menuBook.display();

            switch (menuBook.getUser()) {
                case 1: {
                    this.update();
                    break;
                }
                case 2: {
                    this.removeOrder();
                    break;
                }
                case 3: {
                    if (change) {
                        System.out.println("You do not save data to file."
                                + "It can make lose some data.");
                        if (!askUser("Do you want to save before back?")) {
                            this.saveToFile();
                        }
                    }
                    return;
                }

            }
        }
    }
    
    public void printFalse(){
        System.out.println(StringEx.repeat("-", 65));
        System.out.printf("%-10s%-15s%-13s%-10s%-12s%s\n",
                "OrderId", "CusId", "ProId", "Quantity", "Date", "Status");
        Customer cus = new Customer();
        for (Order order : orders) {
            if(order.getStatus() == false){
            System.out.printf("\n%-10s%-15s%-13s%-10s%-12s%s\n", order.getOrderId(), order.getCustomerId(), order.getProductId(),
                    order.getOrderQuantity(), order.getOrderDate(), order.getStatus());
        }
    }
                System.out.println(StringEx.repeat("-", 65));
    


    }
    public void printAll() {
        System.out.println(StringEx.repeat("-", 95));
        Set<String> customerIds = new HashSet<>();
        for (Order o: orders) {
            if (!o.getCustomerId().isEmpty()) {
                customerIds.add(o.getCustomerId());
            }
        }

        // fetch customer names
        Map<String, String> m = new HashMap<>();
        for (String id: customerIds) {
            String cusName = cuslist.getNameById(id);
            m.put(id, cusName);
        }
        // assign to order
        for (Order o: orders) {
            o.setCustomerName(m.getOrDefault(o.getCustomerId(), ""));
        }

        // sort
        Collections.sort(orders, (o1, o2) -> {
            return o1.getCustomerName().compareToIgnoreCase(o2.getCustomerName());
        });

        // print
         System.out.printf("%-10s%-15s%-13s%-10s%-12s%s\n",
                "OrderId", "CusId", "ProId", "Quantity", "Date", "Status");
        Customer cus = new Customer();
        for (Order order : orders) {

            System.out.printf("\n%-10s%-15s%-13s%-10s%-12s%s\n", order.getOrderId(), order.getCustomerId(), order.getProductId(),
                    order.getOrderQuantity(), order.getOrderDate(), order.getStatus());
        }

        System.out.println(StringEx.repeat("-", 95));
    
    }
    public boolean askUser(String title) {
        System.out.println("\n" + title);
        System.out.println("Yes/No\n");
        return !MyUtil.readAskUser();
    }

    public void run() throws Exception {
        //Create menu for Book
        Menu menuBook = new Menu("Order Management");
        menuBook.addOption("Print","Print Depending","Add Order","Update Order","Save Order","Exit");
        boolean change = false;
        while (true) {

            menuBook.display();

            switch (menuBook.getUser()) {
                case 1: {
                    this.printAll();
                    break;
                }                  
                case 2: {
                    this.printFalse();
                    break;
                }
                case 3: {
                    this.addOrder();
                    break;
                }
                case 4: {
                    this.updateOrder();
                    break;
                }
                case 5: {
                    this.saveToFile();
                    break;
                }              
                case 6: {
                    if (change) {
                        System.out.println("You do not save data to file."
                                + "It can make lose some data.");
                        if (!askUser("Do you want to save before back?")) {
                            this.saveToFile();
                        }
                    }
                    return;
                }
            }
        }
    }
}
