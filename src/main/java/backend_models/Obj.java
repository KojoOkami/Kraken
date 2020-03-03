package backend_models;

public abstract class Obj {

    public String title;
    public String description;
    private String ID = null;

    public Obj(String title, String description) {
        this.title = title;
        this.description = description;
        this.ID = ID.generateID();
    }

    public Obj(String title, String description, String id) {
        this.title = title;
        this.description = description;
        this.ID = id;
    }

    public String getID() {
        return ID;
    }
}
