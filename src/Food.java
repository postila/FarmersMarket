public abstract class Food {
    public int price;
    public int amount;

    public int getFoodPrice(){
        return price;
    }
    public int setAmount(int kg){
        return this.amount += kg;
    }
    public int getAmount(){
        return this.amount;
    }
}
