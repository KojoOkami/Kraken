package backend_models;

public class ComputerFile {

    private String PATH;
    public Object object;
    public String saveStatus;

    public ComputerFile(Obj object) {
        this.PATH = "..\\" + VisualSpace.projectName.replaceAll(" ", "") + "\\" + object.getTitle().replaceAll(" ", "") + ".txt";
        this.object = object;
        saveStatus = null;
    }

    public Object getDocument() {
        //Get the document
        return null;
    }

    public void saveDocument() {

    }

    public Object getText() {

        return null;
    }

    public void saveText() {

    }
}
