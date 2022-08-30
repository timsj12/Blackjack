/**
 * @author Tim Jarvis
 * Mini-Project 2: BlackJack
 * Due Date: 8/7/2022
 * 
 * CardDeck Class Creates the Deck of Class and grabs cards when cards are dealt
 */


/**
 * @param suit array of the 4 suits in a card deck
 * @param card_value array of the 13 different card values in a card deck
 * @param card_deck arry of every card in a card deck
 * @param hand_value value of all the cards in the hand
 * @param ace_hand_value value of all the cards in the hand if hand had ace = 1 in the hand
 * @param test boolean check to see if card num is in array or not
 * @param cards array of numbers representing cards
 * @param card_num associated number for a given card
 * @param N number of cards in deck
 */
public class CardDeck {
    private String suit [] = {"Spades", "Clubs", "Hearts", "Diamonds"};
    private String  card_value [] = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
    private String [] card_deck = new String [52];

    private boolean test = false;
    private int cards [];
    private String card_suit_value;
    private int card_num;
    private int N = 52;
    
    /**
     * Constructor CardDeck
     * @param c sets the array of cards to check to see what cards have already been used
     */
    public CardDeck(int [] c) {
        setCard_Num(c);
    }

    /**
     * Void Method setCard_Num gets the value of a random number for a card not currently in the cards array
     * @param cards array of cards already used
     */
    private void setCard_Num(int [] cards) {
        this.cards = cards;

        // generate a random integer number between 0 and 51 and see if that number is in cards already
        // repeat until new card_num selected that is not in array
        do {
            card_num = (int)(N * Math.random());
            for (int element : cards) {
                if (element == card_num) {
                    test = true;
                    break;
                }
            }
        }
        while (test);

        // send card_num to setCard
        setCard(card_num);
    }
    /**
     * getCard_Num()
     * @return returns card number for selected card
     */
    public int getCard_Num() {
        return card_num;
    }

    /**
     * setCard method takes the card_num and gets the string of card suit and value from card deck
     * @param card_num associated number for a given card
     */
    private void setCard(int card_num) {
        int i = 0;

        // Generates the deck of cards
        for (int j = 0; j < 4; j++) {
            for (int n = 0; n < 13; n++) {
                String card = card_value[n] + " of " + suit[j];
                card_deck[i] = card;
                i++;
            }
        }
        // Gets the card for the given card num
        card_suit_value = (card_deck[card_num]);
    }
    
    /**
     * getCard()
     * @return card_suit_value card suit and value from the deck for dealing to player or dealer
     */
    public String getCard() {
        return card_suit_value;
    }
}
