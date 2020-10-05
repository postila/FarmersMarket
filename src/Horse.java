public class Horse extends Animal{

    public Horse(String name, String gender){
        super(name, gender);
        super.price = 50000;
    }
    public void getPrice(){
        System.out.println(this.price);
    }
}
