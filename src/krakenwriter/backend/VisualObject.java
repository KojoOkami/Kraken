package krakenwriter.backend;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class VisualObject extends Obj {

    private int x;
    private int y;
    private int width;
    private int height;

    private ArrayList<ID> parentIDs;
    private ArrayList<ID> childIDs;
    
    private ArrayList<VisualObject> parentObjects;
    private ArrayList<VisualObject> childObjects;

    public VisualObject(String title, String description, int x, int y, int width, int height, ID[] parents, ID[] children) { //Basic Constructor
        super(title, description);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        if (parents != null) {
        	parentIDs.addAll(Arrays.asList(parents));
        } else {
        	parentIDs = new ArrayList<ID>();
        }

        if (children != null) {
        	childIDs.addAll(Arrays.asList(children));
        } else {
        	childIDs = new ArrayList<ID>();
        }
    }

    public VisualObject(String title, String description, int x, int y, int width, int height, String id, ID[] parents, ID[] children) { //Basic Constructor
        super(title, description, id);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        if (parents != null) {
        	parentIDs.addAll(Arrays.asList(parents));
        } else {
        	parentIDs = new ArrayList<ID>();
        }

        if (children != null) {
        	childIDs.addAll(Arrays.asList(children));
        } else {
        	childIDs = new ArrayList<ID>();
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
    
    public ID[] getParentIDs() {
    	return parentIDs.toArray(new ID[parentObjects.size()]);
    }
    
    public ID[] getChildIDs() {
    	return childIDs.toArray(new ID[childObjects.size()]);
    }
    
    public void initParents(ArrayList<VisualObject> parents) {
    	parentObjects = parents;
    	parentIDs = null;
    }
    
    public void initChildren(ArrayList<VisualObject> children) {
    	childObjects = children;
    	childIDs = null;
    }
    
    public ArrayList<VisualObject> getParents() {
    	return parentObjects;
    }
    
    public ArrayList<VisualObject> getChildren() {
    	return childObjects;
    }
    
    public void update(String title, String description, int x, int y, int width, int height) {
    	this.title = title;
    	this.description = description;
    	this.setPos(x, y);
    	this.setDimensions(width, height);
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
