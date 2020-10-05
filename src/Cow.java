public class Cow extends Animal{

    public Cow(String name, String gender){
        super(name,gender);
        super.price = 12000;
    }
    public int getPrice(){
        return this.price;
    }
}
