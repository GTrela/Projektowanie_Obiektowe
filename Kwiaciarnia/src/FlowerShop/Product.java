package FlowerShop;

public class Product {

    int id;
    String name;
    double price;
    
    public Product(int id, String name, double price)
    {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    void setId (int id)
    {
        this.id = id;
    }

    void setName (String name)
    {
        this.name = name;
    }

    void setPrice (double price)
    {
        this.price = price;
    }

    int getId ()
    {
        return this.id;
    }

    String getName ()
    {
        return this.name;
    }

    double getPrice ()
    {
        return this.price;
    }
}
