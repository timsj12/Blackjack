/**
 * Hand Class Prints The Cards and Values for Each of the Card in the Hand
 */


/**
 * @param hand_cards String storing the cards in the hand
 * @param value CardValues Object storing the associated value object for each card
 * @param cards int representing the number of cards in hand
 * @param hand_value value of all the cards in the hand
 * @param ace_hand_value value of all the cards in the hand if hand had ace = 1 in the hand
 */
public class Hand {
    private String hand_cards [];
    private CardValues [] value = new CardValues [10];
    private int cards;
    private int hand_value = 0;
    private int ace_hand_value = 0;

    /**
     * Hand class constructor
     * @parm hand_cards array of String; cards in hand
     * @parm cards number of cards in hand
     */
    public Hand(String [] hand_cards, int cards) {
        this.hand_cards = hand_cards;
        this.cards = cards;
    }
    
    /**
     * void printHand() method prints the card and value of each card in hand
     * prints total hand value
     */
    public void printHand() {
        //Places new CardValues object in value array
        for (int i = 0; i < cards; i++) {
            value[i] = new CardValues(hand_cards[i]);
        }
        // prints out card and card value for each card. 
        for (int j = 0; j < cards; j++) {
            System.out.println("Card " + (j + 1) + ": " + hand_cards[j] + "\t\t\tPoint Value: " + value[j].getCardValue());
            hand_value += value[j].getCardValue();
        }
        System.out.println("\nPoint Total: " + hand_value);
    }

    /**
     * void method prints the card and value of each card in hand if there is an ace = 1 card in hand
     * prints total hand value
     */
    public void printAceHand() {
        //Places new CardValues object in value array
        for (int i = 0; i < cards; i++) {
            value[i] = new CardValues(hand_cards[i]);
        }
        // prints out card and card value for each card
        for (int j = 0; j < cards; j++) {
            System.out.println("Card " + (j + 1) + ": " + hand_cards[j] + "\t\t\tPoint Value: " + value[j].getAceCardValue());
            ace_hand_value += value[j].getAceCardValue();
        }
        System.out.println("\nPoint Total: " + ace_hand_value);
    }

    /**
     * public method getHandValue()
     * @return hand_value total value of all cards in hand
     */
    public int getHandValue() {
        return hand_value;
    }

    /**
     * public mehtod getAceHandValue()
     * @return ace_hand_value total value in then hand if 1 card in the hand is an Ace = 1 card
     */
    public int getAceHandValue() {
        return ace_hand_value;
    }
}
