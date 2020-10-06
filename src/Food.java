public abstract class Food {
    public int price;
    public int amount;

    public int getFoodPrice(){
        return price;
    }
    public void setAmount(int kg){
       amount = kg;
       System.out.println(amount);
    }
    public int getAmount(){
        return this.amount;
    }
}
