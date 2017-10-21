public class Main {

    public static void main(String[] args) {
        Person tmp = Singleton.getInstance("Janusz","Kowalski",23);
        System.out.println(tmp.getName());
    }
}
