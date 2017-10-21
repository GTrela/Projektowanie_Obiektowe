public class Person {
    private String name;
    private String surname;
    private int lotNumber;

    public Person (String name, String surname, int lotNumber){
        this.name = name;
        this.surname = surname;
        this.lotNumber = lotNumber;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getLotNumber() {
        return lotNumber;
    }
}
