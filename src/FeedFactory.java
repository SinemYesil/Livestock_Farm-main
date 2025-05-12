interface Carbohydrate {
    void describe();
}

interface Protein {
    void describe();
}

class Corn implements Carbohydrate {
    public void describe() { System.out.println("Carbohydrate: Corn"); }
}

class Wheat implements Carbohydrate {
    public void describe() { System.out.println("Carbohydrate: Wheat"); }
}

class Soybean implements Protein {
    public void describe() { System.out.println("Protein: Soybean"); }
}

class Canola implements Protein {
    public void describe() { System.out.println("Protein: Canola"); }
}

interface FeedFactory {
    Carbohydrate createCarbohydrate();
    Protein createProtein();
}

class DairyFeedFactory implements FeedFactory {
    public Carbohydrate createCarbohydrate() { return new Corn(); }
    public Protein createProtein() { return new Soybean(); }
}

class BeefFeedFactory implements FeedFactory {
    public Carbohydrate createCarbohydrate() { return new Wheat(); }
    public Protein createProtein() { return new Canola(); }
}

 class CattleFactory {
    public static Cattle createCattle(String type, String id, String location) {
        switch (type.toLowerCase()) {
            case "dairy":
                return new DairyCattle(id, location);
            case "beef":
                return new BeefCattle(id, location);
            default:
                throw new IllegalArgumentException("Unknown cattle type: " + type);
        }
    }
}
