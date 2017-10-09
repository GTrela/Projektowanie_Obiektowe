package kwiaciarnia;

/**
 * Created by Grzegorz Trela on 09.10.17.
 */
public class client {
    private int id;
    private String name;

    public client(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
