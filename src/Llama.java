public class Llama extends Animal{

    public Llama(String name, String gender){
        super(name, gender);
        super.price = 10000;
    }
    public void getPrice(){
        System.out.println(this.price);
    }
}
