public class Main {
    public static void main(String[] args) {

        System.out.println("=== Initializing Farm System ===");

        LocationDatabase db = LocationDatabase.getInstance();
        Farmer farmer = new Farmer(db);
        db.addObserver(farmer);

        System.out.println("\n=== Creating Cattle (Factory Pattern) ===");
        Cattle dairy = CattleFactory.createCattle("dairy", "D001", "North Field");
        Cattle beef = CattleFactory.createCattle("beef", "B001", "South Field");

        CattleList cattleList = new CattleList();
        cattleList.Add(dairy);
        cattleList.Add(beef);

        System.out.println("\n=== Veterinary Visit in Autumn (Visitor Pattern) ===");
        CattleVisitor vet = new VeterinaryVisitor();
        cattleList.Accept(vet);

        System.out.println("\n=== Manual Ear Tag for One Cattle ===");
        beef.applyEarTag(); // only beef is tagged manually

        System.out.println("\n=== Ministry Visit in Spring (Visitor Pattern) ===");
        CattleVisitor ministry = new MinistryVisitor();
        cattleList.Accept(ministry);

        System.out.println("\n=== Transmitting Location (Adapter Pattern) ===");
        CommunicationDevice zigbee = new ZigbeeDevice();
        CommunicationDevice adaptedBluetooth = new BluetoothToZigbeeAdapter(new BluetoothDevice());
        zigbee.sendLocation(dairy.getId(), dairy.getLocation());
        adaptedBluetooth.sendLocation(beef.getId(), beef.getLocation());

        System.out.println("\n=== Storing Location (Singleton + Observer) ===");
        db.storeLocation(dairy.getId(), dairy.getLocation()); // inside
        db.storeLocation(beef.getId(), "Outside Fence");       // outside (triggers alert)

        System.out.println("\n=== Feeding Schedule (Abstract Factory Pattern) ===");

        FeedFactory dairyFeedFactory = new DairyFeedFactory();
        FeedFactory beefFeedFactory = new BeefFeedFactory();

        System.out.println("\n--- Dairy Cattle Feed ---");
        dairyFeedFactory.createCarbohydrate().describe();
        dairyFeedFactory.createProtein().describe();

        System.out.println("\n--- Beef Cattle Feed ---");
        beefFeedFactory.createCarbohydrate().describe();
        beefFeedFactory.createProtein().describe();

        System.out.println("\n=== System Complete ===");
    }
}
