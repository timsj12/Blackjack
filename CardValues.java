/**
 * @author Tim Jarvis
 * 
 * CardValues Class Gets the Values for Each of the Cards
 */


/**
 * @param point int value representing the value of a card
 * @param ace_point int value representing the value of a card if ace = 1
 */
public class CardValues {
    private int point, ace_point;

    /**
     * CardValues constructor
     * @parm card1 String of the card that the value is being found for
     */
    public CardValues (String card1) {
        setCardValue(card1);
        setAceCardValue(card1);
    }

    /**
     * void setCardValue method sets the value of the card
     * @param card String of the card that the value is being found for'
     * @param value of the card
     * @param letter first letter of the card name
     */
    private void setCardValue(String card) {
        int value;
        String letter = card.substring(0, 1);
        
        // switch statement associating card value with first letter of card name
        switch (letter) {
            case "A":   value = 11; 
                        break;
            case "2":   value = 2;
                        break;
            case "3":   value = 3;
                        break;
            case "4":   value = 4;
                        break;
            case "5":   value = 5;
                        break;
            case "6":   value = 6;
                        break;
            case "7":   value = 7;
                        break;
            case "8":   value = 8;
                        break;
            case "9":   value = 9;
                        break;
            default:    value = 10;
                        break;
        }
        point = value;
    }

    /**
     * void setCardValue method sets the value of the card
     * @param card String of the card that the value is being found for'
     * @param value of the card
     * @param letter first letter of the card name
     */
    private void setAceCardValue(String card) {
        int value;
        String letter = card.substring(0, 1);
        
        switch (letter) {
            case "A":   value = 1; 
                        break;
            case "2":   value = 2;
                        break;
            case "3":   value = 3;
                        break;
            case "4":   value = 4;
                        break;
            case "5":   value = 5;
                        break;
            case "6":   value = 6;
                        break;
            case "7":   value = 7;
                        break;
            case "8":   value = 8;
                        break;
            case "9":   value = 9;
                        break;
            default:    value = 10;
                        break;
        }
        ace_point = value;
    }

    /**
     * void getCardValue method sets the value of the card
     * @return point int card value
     */
    public int getCardValue() {
        return point;
    }

    /**
     * void getCardValue method sets the value of the card
     * @return ace_point int card value
     */
    public int getAceCardValue() {
        return ace_point;
    }
}
