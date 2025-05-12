import java.util.ArrayList;

interface CattleVisitor {
    void visit(DairyCattle cattle);
    void visit(BeefCattle cattle);
}

abstract class Cattle {
    private String id;
    private String location;
    private boolean vaccinated = false;
    private boolean earTag = false;

    public Cattle(String id, String location) {
        this.id = id;
        this.location = location;
    }

    public String getId() { return id; }
    public String getLocation() { return location; }

    public boolean isVaccinated() { return vaccinated; }
    public boolean hasEarTag() { return earTag; }

    public void vaccinate() {
        vaccinated = true;
        System.out.println(getType() + " " + id + " vaccinated.");
    }

    public void applyEarTag() {
        earTag = true;
        System.out.println("Ear tag applied to " + getType() + " " + id);
    }

    abstract String getType();
    public abstract void Accept(CattleVisitor visitor);
}

class CattleList {
    public void Add(Cattle cattle)
    {   cattles.add(cattle);
    }
    public void Remove(Cattle cattle)
    {   cattles.remove(cattle);}

    public void Accept(CattleVisitor visitor)
    {   for(Cattle cattle : cattles){
        cattle.Accept(visitor);
    }

    }
    private ArrayList<Cattle> cattles = new ArrayList<Cattle>();

}

class BeefCattle extends Cattle {
    public BeefCattle(String id, String location) {
        super(id, location);
    }

    @Override
    public String getType() {
        return "Beef cattle";
    }

    @Override
    public void Accept(CattleVisitor visitor) {
        visitor.visit(this);
    }
}

class DairyCattle extends Cattle {
    public DairyCattle(String id, String location) {
        super(id, location);
    }

    @Override
    public String getType() {
        return "Dairy cattle";
    }

    @Override
    public void Accept(CattleVisitor visitor) {
        visitor.visit(this);
    }
}

public class VeterinaryVisitor implements CattleVisitor {
    public void visit(DairyCattle cattle) {
        if (!cattle.isVaccinated()) cattle.vaccinate();
    }

    public void visit(BeefCattle cattle) {
        if (!cattle.isVaccinated()) cattle.vaccinate();
    }
}

class MinistryVisitor implements CattleVisitor {
    @Override
    public void visit(DairyCattle cattle) {
        if (!cattle.hasEarTag()) {
            System.out.println("⚠️  Cattle with ID " + cattle.getId() + " has no ear tag! Applying one...");
            cattle.applyEarTag();
        } else {
            System.out.println("✔️  Cattle with ID " + cattle.getId() + " already has an ear tag.");
        }
    }

    @Override
    public void visit(BeefCattle cattle) {
        if (!cattle.hasEarTag()) {
            System.out.println("⚠️  Cattle with ID " + cattle.getId() + " has no ear tag! Applying one...");
            cattle.applyEarTag();
        } else {
            System.out.println("✔️  Cattle with ID " + cattle.getId() + " already has an ear tag.");
        }
    }
}


