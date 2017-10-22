import java.util.HashMap;

// File Name: Singleton.java
public class Singleton {

    private static HashMap<String, Singleton> TicketMap = null;
    private String Name;
    private String Surname;
    private int LotNumber;
    /* A private Constructor prevents any other
     * class from instantiating.
     */
    private  Singleton ()
    {

    }

    private Singleton(String Name, String Surname, int LotNumber) {
        this.LotNumber = LotNumber;
        this.Name = Name;
        this.Surname = Surname;
    }

    /* Static 'instance' method */
    public static Singleton getInstance(String Name, String Surname, int LotNumber) {

        if (TicketMap == null) {
            TicketMap = new HashMap<String, Singleton>();
        }

        if (TicketMap.get(Name + Surname) != null) {
            return TicketMap.get(Name + Surname);
        } else {
            Singleton temp = new Singleton(Name, Surname, LotNumber);
            TicketMap.put(Name + Surname, temp);
            return temp;
        }
    }

    public int getLotNumber() {
        return LotNumber;
    }

    public String getSurname() {

        return Surname;
    }

    public String getName() {

        return Name;
    }
}