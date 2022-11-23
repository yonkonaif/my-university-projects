
package UberSystemProgram;

public class CaptainNode {
    private int id;
    private String name;
    private int star;
    private boolean available;
    private CaptainNode right;
    private CaptainNode left;

    public CaptainNode() {
    }
    
    public CaptainNode(int id, String name) {
        this(id, name, 0, true, null, null);
    }
    
    public CaptainNode(int id, String name, int star, boolean available, CaptainNode right, CaptainNode left) {
        this.id = id;
        this.name = name;
        this.star = star;
        this.available = available;
        this.right = right;
        this.left = left;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name.replace('_', ' ');
        //.replace to replace the dashes with spaces
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int sat) {
        star += sat;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public CaptainNode getRight() {
        return right;
    }

    public void setRight(CaptainNode right) {
        this.right = right;
    }

    public CaptainNode getLeft() {
        return left;
    }

    public void setLeft(CaptainNode left) {
        this.left = left;
    }
    
    //Captain Information
    public String info(){
        StringBuilder string = new StringBuilder();
        string.append("\n\t\tID: ").append(id).append("\n").append("\t\tNAME: ").append(getName()).append("\n").append("\t\tAvailable: ").append(available).append("\n").append("\t\tRating Star: ").append(star).append("");
        return string.toString();
    }
}
