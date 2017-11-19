import org.omg.CORBA.LongHolder;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hotelik!");
        Hotel hotel = new Hotel();
        hotel.loadRooms("out/production/Hotel/rooms.csv");
        hotel.addRoom(100,3,"Testowy pokoj");
        hotel.saveRooms("out/production/Hotel/rooms.csv");
    }
}
