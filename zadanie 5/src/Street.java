import java.util.ArrayList;
import java.util.List;

public class Street {
    private String name;
    private String district;
    private Coordinates start;
    private Coordinates end;
    private List<Car> cars;

    public Street(String name, Coordinates start, Coordinates end, District district) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.district = district.getName();
        this.cars = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getDistrict() {
        return district;
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void removeCar(Car car) {
        cars.remove(car);
    }

    public Coordinates getStart() {
        return start;
    }

    public Coordinates getEnd() {
        return end;
    }

    public List<Car> getCars() {
        return cars;
    }
}
