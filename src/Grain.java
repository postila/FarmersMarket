public class Grain extends Food{

    public Grain(int amount){
        super.price = 200;
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
