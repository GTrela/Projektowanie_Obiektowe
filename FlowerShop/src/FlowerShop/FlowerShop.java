package FlowerShop;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class FlowerShop
{
    List<Purchase> Purchases = new ArrayList<Purchase>();

    public void BuyProduct(int ClientId, Map<Product, Integer> Products)
    {
        Purchase purchase = new Purchase(ClientId, Products);
        Purchases.add(purchase);
    }

    public double Earnings(Timestamp time1, Timestamp time2)
    {
        double sum = 0.0;

        if (time2.before(time1))
        {
            Timestamp temp_time = time1;
            time1 = time2;
            time2 = temp_time;
        }

        for (Purchase purchase : Purchases)
        {

            if (purchase.PurchaseDate.equals(time1) || purchase.PurchaseDate.equals(time2) || (purchase.PurchaseDate.after(time1) && purchase.PurchaseDate.before(time2)))
            {
                sum += purchase.Total;
            }
        }

        return sum;
    }

    public static void main(String[] args)
    {
        FlowerShop kwiaciarnia = new FlowerShop();

        Client Janusz = new Client(1,"Janusz");
        Client Marian = new Client(2,"Marian");

        Map<Product, Integer> JanuszCart = new HashMap<Product, Integer>();
        JanuszCart.put(new Rose(), 3);
        JanuszCart.put(new Tulip(), 2);
        JanuszCart.put(new GreenStrong(), 1);

        kwiaciarnia.BuyProduct(1, JanuszCart);

        System.out.print(kwiaciarnia.Earnings(Timestamp.valueOf("2016-10-02 18:48:05"), new Timestamp(System.currentTimeMillis())));
    }
    
}
