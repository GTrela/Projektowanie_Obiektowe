import java.util.HashMap;

// File Name: Singleton.java
public class Singleton {

    private static HashMap<String, Person> TicketMap = null;

    /* A private Constructor prevents any other
     * class from instantiating.
     */
    private Singleton() {

    }

    /* Static 'instance' method */
    public static Person getInstance(String Name, String Surname, int LotNumber) {

        if (TicketMap == null) {
            TicketMap = new HashMap<String, Person>();
        }

        if (TicketMap.get(Name + Surname) != null) {
            return TicketMap.get(Name + Surname);
        } else {
            Person temp = new Person(Name, Surname, LotNumber);
            TicketMap.put(Name + Surname,temp);
            return temp;
        }
    }

}