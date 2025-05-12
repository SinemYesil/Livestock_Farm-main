import java.util.ArrayList;
import java.util.List;
import java.util.Set;

interface Observer {
    void Update(String id, String location);
}

interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(String id, String location);
}

class Farmer implements Observer {
    private LocationDatabase db;

    public Farmer(LocationDatabase db) {
        this.db = db;
    }

    @Override
    public void Update(String id, String location) {
        System.out.println("Notifying farmer: Cattle " + id + " is outside the farm at " + location + "!");
    }
}

public class LocationDatabase implements Subject{


    private static LocationDatabase instance;

    private LocationDatabase() {}

    private static final Set<String> allowedLocations = Set.of(
            "North Field", "South Field", "Barn", "Pasture"
    );


    public static synchronized LocationDatabase getInstance() {
        if (instance == null) {
            instance = new LocationDatabase();
        }
        return instance;
    }
    private boolean isWithinBoundaries(String location) {
        return allowedLocations.contains(location);
    }


    public void storeLocation(String id, String location) {
        System.out.println("Location stored: " + id + " at " + location);

        if (!isWithinBoundaries(location)) {
            System.out.println("ALERT: " + id + " exited the farm! Notifying farmer...");
            notifyObservers(id, location);
        }
    }


    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer o) {
        observers.add(o);
    }

    public void removeObserver(Observer o) {
        observers.remove(o);
    }


    public void notifyObservers(String id, String location) {
        for (Observer o : observers) {
            o.Update(id, location);
        }
    }

}
