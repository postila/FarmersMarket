public class Hay extends Food{
    public Hay(int amount){
        super.price = 100;
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
