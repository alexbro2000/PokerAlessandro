// Deck.java
public class Deck{
private Card card1;

    public Card getRandomCard() {
        // Generate a number from 1 to 13.
        // Math.random() gives a random number from 0 to 1.
        int number = (int) Math.floor((Math.random() * 13)) + 1;

        // Generate a number from 0 to 3.
        int suitNumber = (int) Math.floor((Math.random() * 3));
        char suit;
        switch (suitNumber) {
            case 0:
                suit = 'h';
                break;
            case 1:
                suit = 's';
                break;
            case 2:
                suit = 'd';
                break;
            case 3:
                suit = 'c';
                break;
            default:
                throw new Error("wtf did you do");
        }

        // this is equivalent to:
        if (suitNumber == 0) {
            suit = 'h';
        } else if (suitNumber == 1) {
            // etc etc.
        } else {
            throw new Error("wtf did you do");
        }

        return new Card(number, suit);
    }

    public Deck() {
        this.card1 = getRandomCard();
    }

    public Card getCard() {
        return this.card1;
    }
}

