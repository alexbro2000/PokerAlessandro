public class Player{
 public static void Player(String[] args) { }
    private int money;
    private String name;

    public Player(String name, int money) {
        this.name = name;
        this.money = money;

    }
   

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void takeMoney(int amount) {
        this.money += amount;
    }

    public void giveMoney(int amount) {
        this.money -= amount;
    }

    public void getCard() {
        Deck deck = new Deck();
        deck.getCard();
   
    

    }
}
 