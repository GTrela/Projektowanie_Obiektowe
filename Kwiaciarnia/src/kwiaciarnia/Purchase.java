package kwiaciarnia;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gregorz Trela on 09.10.17.
 */
public class Purchase {
    int ClientId;
    Map<Product, Integer> Products;
    Timestamp PurchaseDate;
    Double Total;

    public Purchase() {
        Products = new HashMap<Product,Integer>();
    }
}
