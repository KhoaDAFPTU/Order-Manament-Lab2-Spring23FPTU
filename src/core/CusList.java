/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import tool.Menu;
import tool.MyUtil;
import tool.StringEx;

/**
 *
 * @author DLCT
 */
public class CusList{
    List<Customer> cus = new ArrayList();
    Scanner sc = new Scanner(System.in);
    private final String fName = "src\\customers.txt";

    public CusList() {
        readFromFile();
    }

    // đọc file ra danh sách pulish
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
                String ID = stk.nextToken().trim().toUpperCase();
                String name = stk.nextToken().trim().toUpperCase();
                String address = stk.nextToken().trim().toUpperCase();
                String phone = stk.nextToken().trim().toUpperCase();
                Customer customer = new Customer(ID, name, address, phone);
                cus.add(customer);
            }
            bf.close();
            fr.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Thêm 1 pulisher với data được nhập từ bàn phím
    public void addCustomer() {
        String ID, name, address, phone; //các biến chứa data được nhập

        while (true) {
            System.out.println("Data of new customer: ");
            int pos;
            do {
                ID = MyUtil.readPattern("ID", "C[\\d]{3}");
                pos = cus.indexOf(new Customer(ID));
                if (pos < 0) {
                    break;
                }
                System.out.println("ID is duplicated");
            } while (true);

            name = MyUtil.readPattern("Name", ".{5,30}");

            address = MyUtil.readPattern("Address", ".{1,200}");

            phone = MyUtil.readPattern("Phone", "[\\d]{10,12}");

            cus.add(new Customer(ID, name, address, phone)); //thêm p vào danh sách

            if (askUser("Do you want to continue?")) {
                break;
            }
        }
    }

    // tìm 1 book dựa trên ID được nhập
    public void search() {
        String ID;  //ID do user nhập
        while (true) {
            System.out.print("ID of customer: ");
            ID = MyUtil.sc.nextLine().trim().toUpperCase();
            int pos = cus.indexOf(new Customer(ID));
            if (pos < 0){
                System.out.println("\nNot Found");
            }
            for (int i = 0; i < cus.size(); i++) {
                if (cus.get(i).getCustomerId().equals(ID)) {
                    System.out.println("======== ========");
                    System.out.println("Found index of customer " + i + ":");
                    System.out.println("CustomerId: " + cus.get(i).getCustomerId());
                    System.out.println("CustomerName: " + cus.get(i).getCustomerName());
                    System.out.println("CustomerAddress: " + cus.get(i).getCustomerAddress());
                    System.out.println("CustomerPhone: " + cus.get(i).getCustomerPhone());
                }
            }

            if (askUser("Do you want to continue?")) {
                break;
            }
        }
    }

    //sửa name, phone 1 Customer dựa trên ID được nhập
    public void updateCustomer() {
        String ID, name, address, phone; //các biến chứa data được nhập

        while (true) {
            System.out.print("ID of updated customer: ");
            ID = MyUtil.sc.nextLine().trim().toUpperCase();
            int pos = cus.indexOf(new Customer(ID));
            if (pos < 0) {
                System.out.println("Not found");
            } else {

                name = MyUtil.readPattern("Name", ".{5,100}|");
                if (!name.isEmpty()) {
                    cus.get(pos).setCustomerName(name);
                }

                address = MyUtil.readPattern("Address:", ".{1,100}|");

                if (!address.isEmpty()) {
                    cus.get(pos).setCustomerAddress(address);
                }

                phone = MyUtil.readPattern("New phone: ", "[\\d]{10,12}|");
                if (!phone.isEmpty()) {
                    cus.get(pos).setCustomerPhone(phone);
                }

                System.out.println("Updated");
            }

            if (askUser("Do you want to continue?")) {
                break;
            }
        }
    }
       //ghi danh sách lên file
    public void writeToFile() throws Exception {
        if (cus.isEmpty()) {
            System.out.println("Empty list!");
        } else {
            PrintWriter pw = new PrintWriter(fName);
            for (Customer c : cus) {
                pw.println(c);
            }
            pw.close();
            System.out.println("Writting file: Done.");
        }
    }


// Asker after completing
    public boolean askUser(String title) {
        System.out.println("\n" + title);
        System.out.println("Yes/No\n");
        return !MyUtil.readAskUser();
    }

    public void printAll() {
        System.out.println(StringEx.repeat("-", 70));
        System.out.printf("%-10s%-30s%-19s%s\n",   //format khoảng cách
                "Id", "Name", "Address", "Phone");

        for (Customer cus : cus) {
            System.out.printf("\n%-10s %-30s %-19s%s\n", cus.getCustomerId(), cus.getCustomerName(),
                    cus.getCustomerAddress(), cus.getCustomerPhone() );
        }
        System.out.println(StringEx.repeat("-", 70));
    }

    public void run() throws Exception {
        //Create menu for Book
        Menu menuBook = new Menu("Customer Manager");
        menuBook.addOption("List Customer", "Sreach Customer", "Add Customer", "Update Customer", "Sava the information of Customer","Back");
        boolean change = false;
        while (true) {

            menuBook.display();

            switch (menuBook.getUser()) {
                case 1: {
                    this.printAll();
                    
                    break;
                }
                case 2: {
                    this.search();
                    break;
                }
                case 3: {
                    this.addCustomer();
                    break;
                }
                case 4: {
                    this.updateCustomer();
                    change = true;
                    break;
                }               
                case 5: {
                    this.writeToFile();
                    change = false;
                    break;
                }
                case 6: {
                    if (change) {
                        System.out.println("You do not save data to file."
                                + "It can make lose some data.");
                        if (!askUser("Do you want to save before back?")) {
                            this.writeToFile();
                        }
                    }
                    return;
                }
            }
        }
    }

    
    public String getNameById(String id) {
        for (Customer c : cus) {
            if(c.getCustomerId().equals(id))return c.getCustomerName();
        }
        return "";
    }

}


