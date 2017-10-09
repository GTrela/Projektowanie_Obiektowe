/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kwiaciarnia;

/**
 *
 * @author trela_1103395
 */
public class Product {
    int id;
    String name;
    double price;
    
    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    
    void setName (String name){
        this.name = name;
    }
    String getName () {
        return this.name;
    }
    void setPrice (double price) {
        this.price = price; 
    }
    double getPrice (){
        return this.price;
    }
    
    void setId (int id) {
        this.id = id;
    }
    
    int getId (){
        return this.id;
    }
}
