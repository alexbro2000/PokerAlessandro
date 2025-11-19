import java.util.*;

public class AIplayer {
    private static Player[] players;
    private static Deck deck;
    private static int currentBet = 10;
    private static int pot = 0;
    private static Scanner scan;

    /**
     * Simple AI decision system:
     * - strong hand → raise
     * - medium hand → call
     * - weak hand → check or fold occasionally
     */
    public static void decideMove(Player player) {
        int strength = handValue(player);

        if (strength > 80) {
            // Strong hand → raise
            int raiseAmount = 20;
            System.out.println(player.getName() + " raises by " + raiseAmount);
            currentBet += raiseAmount;
            pot += player.betChips(currentBet);
        } 
        else if (strength > 40) {
            // Medium strength → call
            System.out.println(player.getName() + " calls the bet of " + currentBet);
            pot += player.betChips(currentBet);
        } 
        else {
            // Weak → 50/50 chance to check or fold
            if (Math.random() < 0.5) {
                System.out.println(player.getName() + " checks.");
            } else {
                System.out.println(player.getName() + " folds.");
                player.folded = true;
            }
        }
    }

    /**
     * Extremely basic evaluation:
     * Sum of card values + small bonus for same-suit cards.
     */
    public static int handValue(Player player) {
        List<Card> hand = player.fullHand();
        hand.sort((a, b) -> b.getValue() - a.getValue());

        int value = 0;
        for (Card c : hand) value += c.getValue();

        // small bonus if many cards share suit
        Map<String, Integer> suitCount = new HashMap<>();
        for (Card c : hand) {
            suitCount.put(c.getSuit(), suitCount.getOrDefault(c.getSuit(), 0) + 1);
        }

        int maxSuit = Collections.max(suitCount.values());
        value += (maxSuit - 1) * 5;

        return value;
    }

    public static void main(String[] args) {
        scan = new Scanner(System.in);
        deck = new Deck();

        System.out.println("How many players?");
        int numPlayers = scan.nextInt();
        players = new Player[numPlayers];

        // Initialize players
        for (int i = 0; i < numPlayers; i++) {
            System.out.print("Enter name for player " + (i + 1) + ": ");
            players[i] = new Player(scan.next());
        }

        // Deal 2 hole cards
        for (int i = 0; i < 2; i++) {
            for (Player p : players) {
                p.addCard(deck.getCard(), i);
            }
        }

        // Simulate 3 rounds of community cards (3, then 1, then 1)
        int[] dealSizes = {3, 1, 1};
        int index = 2;

        for (int round = 0; round < dealSizes.length; round++) {
            System.out.println("\n--- Round " + (round + 1) + " ---");

            for (Player p : players) {
                if (!p.folded) {
                    p.showHand(false);
                    decideMove(p);
                }
            }

            // Deal community cards
            for (int i = 0; i < dealSizes[round]; i++) {
                for (Player p : players) {
                    p.addCard(deck.getCard(), index);
                }
                index++;
            }
        }

        // Reveal all hands
        System.out.println("\n--- Revealing Hands ---");
        for (Player p : players) {
            p.showHand(true);
            System.out.println(p.getName() + "'s value: " + handValue(p));
        }

        // Determine winner
        Player winner = Arrays.stream(players)
                .filter(p -> !p.folded)
                .max(Comparator.comparingInt(Game::handValue))
                .orElse(null);

        if (winner != null) {
            System.out.println("\nWinner: " + winner.getName());
            System.out.println("Pot: " + pot + " chips!");
        } else {
            System.out.println("Everyone folded. No winner.");
        }
    }
}