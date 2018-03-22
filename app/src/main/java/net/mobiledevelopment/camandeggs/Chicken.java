package net.mobiledevelopment.camandeggs;

/**
 * Created by robbwise on 3/21/18.
 */

public class Chicken {
    public String breed;
    public String name;
    private int eggs;

    public int getEggs(){
        return this.eggs;
    }

    public void setEggs(int e){
        this.eggs = e;
    }
}
