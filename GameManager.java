import java.util.Scanner;

class GameManager {
    public static void main(String[] args) {
        Deck deck = new Deck();
        Scanner input = new Scanner(System.in);

        // (4) SHUFFLE DECK BEFORE DEALING
        deck.shuffle();

        // Ask user for number of players
        System.out.print("How many players are playing poker? ");
        int numPlayers = input.nextInt();
        Player[] players = new Player[numPlayers];

        // Create players and deal 2 cards each
        for (int i = 0; i < numPlayers; i++) {
            System.out.print("Enter player " + (i + 1) + "'s name: ");
            players[i] = new Player(input.next())
                .addCard(deck.dealCard())
                .addCard(deck.dealCard());
        }

        // (5) DEAL & DISPLAY COMMUNITY CARDS (FLOP, TURN, RIVER)
        Card[] community = new Card[5];
        for (int i = 0; i < 5; i++) {
            community[i] = deck.dealCard();  
        }

        System.out.println("\nCommunity Cards:");
        for (int i = 0; i < 5; i++) {
            System.out.print("Card " + (i + 1) + ": ");
            community[i].display(Card.DisplayMode.FANCY);
            System.out.println();
        }

        // Display player hands
        System.out.println();
        for (Player player : players) {
            System.out.println("Player " + player.getName() + "'s hand:");
            player.displayHand(Card.DisplayMode.FANCY);
            System.out.println();
        }

        input.close();
    }
}
