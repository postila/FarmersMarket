public class Sheep extends Animal{

    public Sheep(String name, String gender){
        super(name, gender);
        super.price = 1000;
    }

    public int getPrice(){
        return this.price;
    }
}
