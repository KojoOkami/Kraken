package backend_models;

public class Label extends VisualObject {

    public Label(String title, String description, int x, int y, int width, int height, String id) {
        super(title, description, x, y, width, height, id);
    }

    public Label(String title, String description, int x, int y, int width, int height, VisualObject[] parentObjects, String id) {
        super(title, description, x, y, width, height, parentObjects, id);
    }

    @Override
    public String generateID() { //Generates ID but with a document identifier on the front
        return "LBL" + super.generateID();
    }
}
