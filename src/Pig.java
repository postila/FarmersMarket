public class Pig extends Animal{

    public Pig(String name, String gender){
        super(name, gender);
        super.price = 1500;
    }
    public void getPrice(){
        System.out.println(this.price);
    }
}
