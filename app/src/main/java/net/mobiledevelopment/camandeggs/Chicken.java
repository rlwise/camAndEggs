package net.mobiledevelopment.camandeggs;

/**
 * Created by robbwise on 3/21/18.
 */

public class Chicken {
    private int id;
    private String breed;
    private String name;
    private int eggs;

    //constructors
    public Chicken(){};

    public Chicken(String breed, String name, int eggs){
        super();
        this.breed = breed;
        this.name = name;
        this.eggs = eggs;
    }

    //getters
    public int getId(){
        return this.id;
    }
    public String getBreed(){
        return this.breed;
    }

    public String getName(){
        return this.name;
    }

    public int getEggs(){
        return this.eggs;
    }

    //setters
    public void setId(int i){
        this.id = i;
    }
    public void setBreed(String b){
        this.breed = b;
    }

    public void setName(String n){
        this.name = n;
    }

    public void setEggs(int e){
        this.eggs = e;
    }

    @Override
    public String toString(){
        return "Chicken [id=" + id + ", breed=" + breed + ", name=" + name + ", eggs=" + eggs + "]";
    }
}
