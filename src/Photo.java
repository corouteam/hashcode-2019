import java.util.HashSet;
import java.util.Set;

public class Photo {
    int id;
    String orientation;
    HashSet<String> tags;
    boolean checked = false;
    boolean finalChecked = false;

    public Photo(int id, String orientation, HashSet<String> tags) {
        this.id = id;
        this.orientation = orientation;
        this.tags = tags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }
}
