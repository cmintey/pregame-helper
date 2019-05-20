package models;

public class Marcher {

    private String name;
    private String year;
    private String part;
    private String spots;
    private int primarySpot;
    private int secondarySpot;

    public Marcher(String name, String year, String part, String spots){
        this.name = name;
        this.year = year;
        this.part = part;
        this.spots = spots;
        this.primarySpot = -1;
        this.secondarySpot = -1;
    }

    public Marcher(String name, String year, String part, String spots, int spot1, int spot2){
        this.name = name;
        this.year = year;
        this.part = part;
        this.spots = spots;
        this.primarySpot = spot1;
        this.secondarySpot = spot2;
    }

    public int getPrimarySpot(){
        return primarySpot;
    }

    public int getSecondarySpot(){
        return secondarySpot;
    }

    public String getName(){
        return name;
    }

    public String getPart(){
        return part;
    }

    public String getSpots(){
        return spots;
    }

    public String getYear(){
        return year;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPart(String part){
        this.part = part;
    }

    public void setPrimarySpot(int primarySpot){
        this.primarySpot = primarySpot;
    }

    public void setSecondarySpot(int secondarySpot){
        this.secondarySpot = secondarySpot;
    }

    public void setSpots(String spots){
        this.spots = spots;
    }

    public void setYear(String year){
        this.year = year;
    }

    public boolean hasPrimarySpot(){
        return (this.primarySpot != -1);
    }

    public boolean hasSecondarySpot(){
        return (this.secondarySpot != -1);
    }

    public String asCSV(){
        return this.name + "," +
                this.year + "," +
                this.part + "," +
                this.spots + "," +
                this.primarySpot + "," +
                this.secondarySpot + "\n";

    }

    @Override
    public boolean equals(Object obj){
        if (this == obj){
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        return name;
    }
}
