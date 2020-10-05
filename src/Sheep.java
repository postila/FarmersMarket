public class Sheep extends Animal{

    public Sheep(String name, String gender){
        super(name, gender);
        super.price = 1000;
    }

    public void getPrice(){
        System.out.println(this.price);
    }
}
