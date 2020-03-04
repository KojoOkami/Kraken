package krakenwriter.backend;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class VisualObject extends Obj {

    private int x;
    private int y;
    private int width;
    private int height;

    private ArrayList<VisualObject> parentObjects;
    private ArrayList<VisualObject> childObjects;

    public VisualObject(String title, String description, int x, int y, int width, int height, VisualObject[] parents, VisualObject[] children) { //Basic Constructor
        super(title, description);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        
        if (parents != null) {
            parentObjects.addAll(Arrays.asList(parents));
        } else {
            parentObjects = new ArrayList();
        }
        
        if (children != null) {
            childObjects.addAll(Arrays.asList(children));
        } else {
            childObjects = new ArrayList();
        }
    }

    public VisualObject(String title, String description, int x, int y, int width, int height, String id, VisualObject[] parents, VisualObject[] children) { //Basic Constructor
        super(title, description, id);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        
        if (parents != null) {
            parentObjects.addAll(Arrays.asList(parents));
        } else {
            parentObjects = new ArrayList();
        }
        
        if (children != null) {
            childObjects.addAll(Arrays.asList(children));
        } else {
            childObjects = new ArrayList();
        }
    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public void setDimensions(int w, int h) {
        this.width = w;
        this.height = h;
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    public void append(VisualObject child) {
        this.childObjects.add(child);
        child.parentObjects.add(this);
    }
    
    public void remove(VisualObject child) {
        if (childObjects.contains(child)) {
            childObjects.remove(child);
            child.parentObjects.remove(this);
        }
    }
    
    public VisualObject extract() {
        for (VisualObject p : parentObjects) {
            p.remove(this);
            for (VisualObject c : childObjects) {
                c.parentObjects.remove(this);
                p.append(c);
            }
        }
        return this;
    }

}
