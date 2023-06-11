import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Controller {
    private List<District> districts;
    private List<Car> cars;

    public Controller() {
        districts = new ArrayList<>();

        District district = new District("Retkinia");
        district.addStreet(new Street("Kusocińskiego", new Coordinates(200, 600), new Coordinates(500, 600), district));
        district.addStreet(new Street("Maratońska", new Coordinates(0, 1400), new Coordinates(400, 1400), district));
        district.addStreet(new Street("Popiełuszki", new Coordinates(200, 600), new Coordinates(200, 1000), district));
        district.addStreet(new Street("Armii Krajowej", new Coordinates(500, 600), new Coordinates(500, 800), district));
        district.addStreet(new Street("Konstantynowska", new Coordinates(500, 800), new Coordinates(600, 800), district));
        district.addStreet(new Street("Wyszyńskiego", new Coordinates(200, 1000), new Coordinates(0, 1400), district));
        districts.add(district);

        district = new District("Centrum");
        district.addStreet(new Street("Piotrkowska", new Coordinates(800, 800), new Coordinates(800, 1000), district));
        district.addStreet(new Street("Zachodnia", new Coordinates(600, 800), new Coordinates(600, 1000), district));
        district.addStreet(new Street("Mickwiewicza", new Coordinates(200, 1000), new Coordinates(600, 1000), district));
        district.addStreet(new Street("Piłsudskiego", new Coordinates(600, 1000), new Coordinates(1200, 1000), district));
        districts.add(district);

        district = new District("Bałuty");
        district.addStreet(new Street("Wojska Polskiego", new Coordinates(800, 800), new Coordinates(1200, 1000), district));
        district.addStreet(new Street("Franciszkańska", new Coordinates(600, 1000), new Coordinates(400, 1400), district));
        district.addStreet(new Street("Nowomiejska", new Coordinates(600, 600), new Coordinates(800, 800), district));
        district.addStreet(new Street("Sporna", new Coordinates(550, 1100), new Coordinates(1000, 1100), district));
        district.addStreet(new Street("Staszica", new Coordinates(1200, 1000), new Coordinates(1000, 1100), district));
        districts.add(district);

        cars = new ArrayList<>();
        List<String> licencePlates = new ArrayList<>() {
            {
                add("Mazda");
                add("Audi");
                add("BMW");
                add("Fiat");
                add("Skoda");
            }
        };
        Random rnd = new Random();
        for (String licencePlate : licencePlates) {
            int index = rnd.nextInt(getAllStreets().size());
            Street street = getAllStreets().get(index);

            boolean direction = (rnd.nextInt(2) == 0);

            double velocity = rnd.nextDouble() * 80 + 20; // Od 20 do 100

            cars.add(new Car(licencePlate, street, direction, velocity));
        }
    }

    public void runSimulation() {
        int licznik = 0;
        while (true) {
            try {
                Thread.sleep(1000 / 30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            moveAllCars();

            licznik++;
            if (licznik == 30) {
                clearConsole();

                showDistrictsWithMostAndLeastCars();
                showStreetsWithMostAndLeastCars();

                showCarStatus("Mazda");
                showCarStatus("Audi");
                showCarStatus("BMW");
                showCarStatus("Fiat");
                showCarStatus("Skoda");

                licznik = 0;
            }
        }
    }

    private void moveAllCars() {
        for (Car c : cars) {
            if (c.moveCar()) {
                List<Street> potentialStreets = new ArrayList<>();
                for (Street s : getAllStreets()) {
                    if ((s.getStart().getX() == c.getDestination().getX() && s.getStart().getY() == c.getDestination().getY()) ||
                            (s.getEnd().getX() == c.getDestination().getX() && s.getEnd().getY() == c.getDestination().getY())) {
                        potentialStreets.add(s);
                    }
                }

                Random rnd = new Random();
                int index = rnd.nextInt(potentialStreets.size());
                c.setNewStreet(potentialStreets.get(index));
            }
        }
    }

    public void showCarStatus(String licencePlate) {
        for (Car car : cars) {
            if (car.getCarName().equals(licencePlate)) {
                car.showCurrentStatus();
                break;
            }
        }
    }

    public void showStreetsWithMostAndLeastCars() {
        List<Street> streets = getAllStreets();
        if (!streets.isEmpty()) {
            Street mostCarsStreet = streets.stream().max(Comparator.comparingInt(s -> s.getCars().size())).orElse(null);
            Street leastCarsStreet = streets.stream().min(Comparator.comparingInt(s -> s.getCars().size())).orElse(null);
            if (mostCarsStreet != null && leastCarsStreet != null) {
                System.out.println("Ulica z największą ilością samochodów: " + mostCarsStreet.getName() + " (" + mostCarsStreet.getCars().size() + ")");
                System.out.println("Ulica z najmniejszą ilością samochodów: " + leastCarsStreet.getName() + " (" + leastCarsStreet.getCars().size() + ")");
            }
        } else {
            System.out.println("Brak ulic.");
        }
    }


    public void showDistrictsWithMostAndLeastCars() {
        if (!districts.isEmpty()) {
            District leastCarsDistrict = null;
            District mostCarsDistrict = null;
            int leastCars = cars.size();
            int mostCars = 0;
            for (District d : districts) {
                int carsTotal = 0;
                for (Street s : d.getStreets()) {
                    carsTotal += s.getCars().size();
                }

                if (carsTotal > mostCars) {
                    mostCars = carsTotal;
                    mostCarsDistrict = d;
                }

                if (carsTotal < leastCars) {
                    leastCars = carsTotal;
                    leastCarsDistrict = d;
                }
            }

            if (mostCarsDistrict != null && leastCarsDistrict != null) {
                System.out.println("Dzielnica z największą ilością samochodów: " + mostCarsDistrict.getName() + " (" + mostCars + ")");
                System.out.println("Dzielnica z najmniejszą ilością samochodów: " + leastCarsDistrict.getName() + " (" + leastCars + ")");
            }
        } else {
            System.out.println("Brak dzielnic.");
        }
    }


    private List<Street> getAllStreets() {
        List<Street> streets = new ArrayList<>();
        for (District d : districts) {
            streets.addAll(d.getStreets());
        }
        return streets;
    }

    private void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            System.out.println("Błąd podczas czyszczenia konsoli: " + e.getMessage());
        }
    }
}
