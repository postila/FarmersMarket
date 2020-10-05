public class Horse extends Animal{

    public Horse(String name, String gender){
        super(name, gender);
        super.price = 50000;
    }
    public int getPrice(){
        return this.price;
    }
}
