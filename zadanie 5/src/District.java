import java.util.ArrayList;
import java.util.List;

public class District {
    private String name;
    private List<Street> streets;

    public District(String name) {
        this.name = name;
        this.streets = new ArrayList<>();
    }

    public void addStreet(Street street) {
        streets.add(street);
    }

    public String getName() {
        return name;
    }

    public List<Street> getStreets() {
        return streets;
    }
}
