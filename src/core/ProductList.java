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
import java.util.StringTokenizer;
import tool.Menu;
import tool.StringEx;

public final class ProductList {
    List<Product> pro = new ArrayList();
    
    private final String fName = "src\\product.txt";

    public ProductList() {
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
                String name = stk.nextToken().trim();
                String unit = stk.nextToken().trim();
                String origin = stk.nextToken().trim();
                double price = Double.parseDouble(stk.nextToken().trim());
                Product p = new Product(ID, name, unit, origin, price);
                pro.add(new Product(ID, name, unit, origin, price));
            }
            bf.close();
            fr.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //ghi danh sách lên file
    public void writeToFile() throws Exception {
        if (pro.isEmpty()) {
            System.out.println("Empty list!");
        } else {
            PrintWriter pw = new PrintWriter(fName);
            for (Product p : pro) {
                pw.println(p);
            }
            pw.close();
            System.out.println("Writting file: Done.");
        }
    }

    // Duyệt xuất ds pulisher
    public void printAll() {
        System.out.println(StringEx.repeat("-", 95));
        System.out.printf("%-10s%-29s%-21s%-25s%s\n",   //format khoảng cách
                "Id", "Name", "Unit", "Place", "Price");

        for (Product p : pro) {
            System.out.printf("\n%-10s%-29s%-21s%-25s%-5s\n", p.getProductId(),
                    p.getProductName(), p.getUnit(),
                    p.getOrigin(),p.getPrice());
        }
        System.out.println(StringEx.repeat("-", 95));
    }

    public void run() throws Exception {

        //Create menu for pulisher
        Menu menuPulisher = new Menu("Product Management");
        menuPulisher.addOption("Show Product","Back");
        boolean change = false;
        while (true) {
            menuPulisher.display();

            switch (menuPulisher.getUser()) {
                
                case 1: {
                    this.printAll();
                    change = false;
                    break;
                }
                case 2: {
                    if (change) {
                    }
                    return;
                }
            }
        }
    }
}
