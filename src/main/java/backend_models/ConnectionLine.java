package backend_models;

public class ConnectionLine extends VisualObject {
    private VisualObject parentObj;
    private VisualObject childObj;

    public ConnectionLine(VisualObject parent, VisualObject child) {
        super("Connection Line", "", parent.x, parent.y, Math.abs(parent.x-child.x), Math.abs(parent.y-child.y));
        this.parentObj = parent;
        this.childObj = child;
    }

    public void renderSelf() {
        //Renders self on Visual Space
    }
}
