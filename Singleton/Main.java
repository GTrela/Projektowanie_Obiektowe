public class Main {

    public static void main(String[] args) {
        Singleton tmp = Singleton.getInstance("Janusz","Kowalski",23);
        System.out.println(tmp.getName()+" "+tmp.getSurname() +". Nr lotu:"+tmp.getLotNumber());
        Singleton cos = Singleton.getInstance("Janusz","Kowalski",23);
    }
}
