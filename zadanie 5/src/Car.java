import java.util.Random;

public class Car {
    private final String carName;
    private double currentPosX;
    private double currentPosY;
    private double speed;
    private Street street;
    private Coordinates previousLocation;
    private final Coordinates destination;

    public Coordinates getDestination() {
        return destination;
    }

    public String getCarName() {
        return carName;
    }

    public Car(String licencePlate, Street startingStreet, boolean fromStartToEnd, double speedKmH) {
        carName = licencePlate;
        speed = speedKmH;

        street = startingStreet;
        if (fromStartToEnd) {
            previousLocation = street.getStart();
            destination = street.getEnd();
        } else {
            previousLocation = street.getEnd();
            destination = street.getStart();
        }

        currentPosX = previousLocation.getX();
        currentPosY = previousLocation.getY();

        startingStreet.addCar(this);
    }

    public boolean moveCar() {
        double distanceTraveled = ((speed / 3600) * 1000) / 30;
        double distanceToDestination = Math.sqrt(Math.pow((destination.getX() - currentPosX), 2)
                + Math.pow((destination.getY() - currentPosY), 2));

        double ratio = distanceTraveled / distanceToDestination;
        boolean reachedDestination = (ratio >= 1);
        if (reachedDestination) {
            currentPosX = destination.getX();
            currentPosY = destination.getY();
            return true;
        } else {
            currentPosX = ((1 - ratio) * currentPosX + ratio * destination.getX());
            currentPosY = ((1 - ratio) * currentPosY + ratio * destination.getY());
            return false;
        }
    }

    public void setNewStreet(Street street) {
        this.street.removeCar(this);
        this.street = street;

        previousLocation = destination;
        if (destination.getX() == street.getStart().getX() && destination.getY() == street.getStart().getY()) {
            destination.setX(street.getEnd().getX());
            destination.setY(street.getEnd().getY());
        } else {
            destination.setX(street.getStart().getX());
            destination.setY(street.getStart().getY());
        }
        street.addCar(this);

        Random rnd = new Random();
        speed = rnd.nextDouble() * 80 + 20; //prędkość poruszania się na nowej ulicy
    }


    public void showCurrentStatus() {

        System.out.printf("Samochód: %s\n" +
                "\tPrędkość: %s\n" +
                "\tLokalizacja: %s\n" +
                "\tDzielnica: %s\n" +
                "\tUlica: %s \n%n", carName, String.format("%.2f km/h", speed), (String.format("%.2f", currentPosX) + ", " + String.format("%.2f", currentPosY)), street.getDistrict(), street.getName());

    }

}
