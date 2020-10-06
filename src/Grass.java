public class Grass extends Food{

    public Grass(int amount){
        super.price = 150;
        super.amount = amount;
    }
    public int getFoodPrice(){
        return this.price;
    }
    public int setAmount(int kg){
        return amount += kg;
    }
    public int getAmount(){
        return this.amount;
    }
}
