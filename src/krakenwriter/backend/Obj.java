package krakenwriter.backend;

public abstract class Obj {

    public String title;
    public String description;
    private ID id = null;

    public Obj(String title, String description) {
        this.title = title;
        this.description = description;
        this.id = new ID(this);
    }

    public Obj(String title, String description, String id) {
        this.title = title;
        this.description = description;
        this.id = ID.toID(id);
    }

    public Obj(String title, String description, ID id) {
        this.title = title;
        this.description = description;
        this.id = id;
    }

    public ID getID() {
        return id;
    }
}
