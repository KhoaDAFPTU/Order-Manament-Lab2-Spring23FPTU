/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordermanager;

import core.CusList;
import core.OrderList;
import core.ProductList;
import tool.Menu;

/**
 *
 * @author DLCT
 */
public class OrderManager{

   public static ProductList prolist = new ProductList();
    public static CusList cuslist = new CusList();
    public static OrderList orderlist = new OrderList();

    public static void main(String[] args) throws Exception {
       
            Menu menu = new Menu("Order Manager");
            menu.addOption("Show Product", "Show Customer", "Sreach Customer", "Add Customer", "Update Customer",
                                "Sava the information of Customer", "Print all orders", "Print all pending Orders", "Add Order", "Update Order", "Save Order");
            while (true) {

                menu.display();

                switch (menu.getUser()) {
                    case 1: {
                        prolist.printAll();
                        break;
                    }
                    case 2: {
                        cuslist.printAll();
                        break;
                    }
                    case 3: {
                        cuslist.search();
                        break;
                    }
                    case 4: {
                        cuslist.addCustomer();
                        break;
                    }
                    case 5: {
                        cuslist.updateCustomer();
                        break;
                    }
                    case 6: {
                        cuslist.writeToFile();
                        break;
                    }
                    case 7: {
                        orderlist.printAll();
                        break;
                    }
                    case 8: {
                        orderlist.printFalse();
                        break;
                    }
                    case 9: {
                        orderlist.addOrder();
                        break;
                    }
                    case 10: {
                        orderlist.updateOrder();
                        break;
                    }
                    case 11: {
                        orderlist.saveToFile();
                        break;
                    }
                }

            }
       
    }
}

