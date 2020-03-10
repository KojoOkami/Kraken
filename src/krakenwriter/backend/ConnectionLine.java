package krakenwriter.backend;

import java.util.ArrayList;

public class ConnectionLine extends VisualObject {
    private VisualObject parentObj;
    private VisualObject childObj;

    public ConnectionLine(VisualObject parent, VisualObject child) {
        super(null, null, parent.x(), parent.y(), Math.abs(parent.x()-child.x()), Math.abs(parent.y()-child.y()), new VisualObject[]{parent}, new VisualObject[]{child});
        this.parentObj = parent;
        this.childObj = child;
    }

    public VisualObject getParentObj() {
        return parentObj;
    }

    public VisualObject getChildObj() {
        return childObj;
    }
}
