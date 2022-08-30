/**
 * @author Tim Jarvis
 * Mini-Project 2: BlackJack
 * Due Date: 8/7/2022
 * 
 * Program Simulates BlackJack Game
 */


import java.util.*;

public class BlackJack {
    
    /** 
     * @param args
     */
    public static void main ( String [] args) {
        
        CardDeck [] cards = new CardDeck [4];
        CardValues [] value = new CardValues [4];

        int [] dealt_cards = new int [30];

        int used_cards, money, bet;
        int play_again = 1;

        boolean over_21_ace;
        boolean dealer_over_21_ace;
        boolean undecided;

        Scanner input = new Scanner ( System.in );
        
        //How much money do you want to start with in the bank.  Must be increment of $5
        do {
            System.out.print("\nHow much money would do you want to start with? (Must be increment of $5) \n$");
            money = input.nextInt();

            if (money % 5 != 0) {
                System.out.print("\nBet must be increment of $5\n");
            }
        }
        while (money % 5 != 0);

        do {
            do {

                undecided = true;
                used_cards = 4;

                // Bet must be increment of $5 and not more than the money you have
                System.out.print("\nHow much money would do you want to bet? (Must be increment of $5) \n$");
                bet = input.nextInt();

                if (bet % 5 != 0) {
                    System.out.print("\nBet must be increment of $5\n");
                }
                else if (bet > money) {
                    System.out.print("\nBet must be lower than the money you have!!! You only have $" + money + "\n");
                }
            }
            while (bet % 5 != 0 | bet > money);

            System.out.println("\nCards are Dealt!!!!");


            // Card Deck Object Created. Dealt Cards array contains ints of first 4 cards dealt.
            // Each int in array corresponds to specific card in deck
            for (int i = 0; i < 4; i++) {
                cards[i] = new CardDeck(dealt_cards);
                dealt_cards[i] = cards[i].getCard_Num();
            }

            // New Card Value object is created to get the values of each of the 4 specific cards dealt
            for (int j = 0; j < 4; j++) {
                value[j] = new CardValues(cards[j].getCard());
            }

            // getCard() method called and assigned to variable; actual value and suit of card assigned
            String player_card1 = cards[0].getCard();
            String dealer_card1 = cards[1].getCard();
            String player_card2 = cards[2].getCard();
            String dealer_card2 = cards[3].getCard();

            // Player and dealer cards displayed.  1st dealer card is face down; value unknown
            System.out.println("\nPlayer Card 1: " + player_card1);
            System.out.println("\nDealer Card 1: \tUnknown");
            System.out.println("\nPlayer Card 2: " + player_card2);
            System.out.println("\nDealer Card 2: " + dealer_card2);

            // Dealer hand displayed
            System.out.println("\n\nDealer:\n");  
            System.out.println("Card 1: Unknown\t\t\t\tPoint Value: Unknown");
            System.out.println("Card 2: " + dealer_card2 + "\t\t\tPoint Value: " + value[3].getCardValue());
            
            // Player hand displayed
            System.out.println("\n\nPlayer:\n");
            System.out.println("Card 1: " + player_card1 + "\t\t\tPoint Value: " + value[0].getCardValue());
            System.out.println("Card 2: " + player_card2 + "\t\t\tPoint Value: "+ value[2].getCardValue());

            // Player hand point total displayed
            int player_hand_total = value[0].getCardValue() + value[2].getCardValue();
            System.out.println("\nPlayer Point Total: " + player_hand_total);
            
            // Initialize variable; 2 player cards currently dealt
            // Array of length 10 created; player hand
            int player_cards = 2;
            String [] player_hand = new String [10];
            player_hand[0] = player_card1;
            player_hand[1] = player_card2;

            // Initialize variable; 2 dealer cards currently dealt
            // Array of length 10 created; dealer hand
            int dealer_cards = 2;
            String [] dealer_hand = new String [10];
            dealer_hand[0] = dealer_card1;
            dealer_hand[1] = dealer_card2;

            // If player hand does not equal 21 player can request a new card
            while (player_hand_total < 21) {
                over_21_ace = false;
                System.out.print("\n\nDo you want to Hit (Enter 1) or Stay (Enter 2): ");
                int hit = input.nextInt();

                //player requests a new card
                if (hit == 1) {
                    //used card & player card are incremented keeping track of total (player + dealer cards dealt) and cards in player hand
                    used_cards++;
                    player_cards++;

                    // Print out player hand with values
                    // Ace values are 11
                    if (over_21_ace == false) {
                        CardDeck hit_card = new CardDeck(dealt_cards);
                        dealt_cards[used_cards] = hit_card.getCard_Num();
                        player_hand[player_cards - 1] = hit_card.getCard();

                        // new Hand object called to get hand values; hand array and number of cards in player hand called
                        Hand print_player_hand = new Hand (player_hand, player_cards);
                        System.out.println("\n\nPlayer Hand:\n");
                        print_player_hand.printHand();
                        player_hand_total = print_player_hand.getHandValue();

                        // If player hand total is over 21.  Check to see if Ace is in Hand
                        if (player_hand_total > 21) {
                            for (int i = 0; i < player_cards; i++) {
                                if (player_hand[i].substring(0, 1).equals("A")) {
                                    over_21_ace = true;
                                    System.out.print("\nPoint Total over 21!  New Ace Value = 1\n");
                                    break;
                                }
                            }
                        }
                    }
                    // If Ace is in hand print out player hand with Ace value now set to 1
                    if (over_21_ace == true) {
                        Hand print_ace_player_hand = new Hand (player_hand, player_cards);
                        System.out.println("\n\nPlayer Hand:\n");
                        print_ace_player_hand.printAceHand();
                        player_hand_total = print_ace_player_hand.getAceHandValue();
                    }
                }

                // Player decides to stay and not hit
                else if (hit == 2) {
                    break;
                }

                else {
                    System.out.println("\nYou Must Enter a Value of 1 to Hit or 2 to Stay!!");
                }
            }

            // Check to see if player hand = 21; player is a winner
            if (player_hand_total == 21) {
                money += bet;
                System.out.println("\nWinner Winner!!  BlackJack!  Bet of $" + bet + " won.  New total money to bet: $" + money);
            }

            // Check to see if player hand > 21; player is a loser
            else if (player_hand_total > 21) {
                money -= bet;
                System.out.println("\nHand Total > 21    You LOSE!!  Bet of $" + bet + " lost.  New total money to bet: $" + money);
            }


            //If player hasn't Lost and 'Stays' with a Hand the dealer begins to play
            else {

                //Initial Dealer Cards And Values Displayed; Initial Unknown Card flipped over
                System.out.println("\n\nDealer Hand:\n");
                System.out.println("Card 1: " + dealer_card1 + "\t\t\tPoint Value: " + value[1].getCardValue());
                System.out.println("Card 2: " + dealer_card2 + "\t\t\tPoint Value: " + value[3].getCardValue());

                int dealer_hand_total = value[1].getCardValue() + value[3].getCardValue();
                System.out.println("\nDealer Point Total: " + dealer_hand_total);
                
                //Loop Determining Winner between player or Dealer
                do {

                    //Player Loses if dealer cards = 21;  Player decreases money value by the amount bet
                    if (dealer_hand_total == 21) {
                        money -= bet;
                        System.out.println("\nDealer Hand = 21    You LOSE!!  \n\nBet of $" + bet + " lost.  New total money to bet: $" + money);
                        undecided = false;
                    }
                    
                    //Player wins if dealer cards add up to more than 21; Player increases money value by the amount bet
                    else if (dealer_hand_total > 21) {
                        money += bet;
                        System.out.println("\nWinner Winner!!  Dealer Over 21 Bet of $" + bet + " won.  New total money to bet: $" + money);
                        undecided = false;
                    }
                    
                    //If dealer card total is 17 or greater (but less than 21) then the dealer points are compared to player hand to determine winner
                    else if (dealer_hand_total >= 17) {

                        //if dealer hand is greater than player hand; player loses, loses amount bet
                        if (dealer_hand_total > player_hand_total) {
                            money -= bet;
                            System.out.println("\nDealer Hand Total: " + dealer_hand_total + " > Player Hand Total: " + player_hand_total);
                            System.out.println("\nYou LOSE!!  Bet of $" + bet + " lost.  New total money to bet: $" + money);
                            undecided = false;
                        }
                        
                        //if dealer hand is les than player hand; player wins, wins amount bet
                        else if (dealer_hand_total < player_hand_total) {
                            money += bet;
                            System.out.println("\nDealer Hand Total: " + dealer_hand_total + " < Player Hand Total: " + player_hand_total);
                            System.out.println("\nWinner Winner!!  Bet of $" + bet + " won.  New total money to bet: $" + money);
                            undecided = false;
                        }

                        //if dealer and player tie; player gets their bet back
                        else {
                            System.out.println("\nIt's a TIE!!   Dealer Hand Total: " + dealer_hand_total + " = Player Hand Total: " + player_hand_total); 
                            System.out.println("\n$" + bet + " bet returned. Total money remains: $" + money);
                            undecided = false;
                        }
                    }
                    
                    //if dealer cards are less than 17 dealer needs to take a new card and it to hand
                    else {
                        dealer_over_21_ace = false;
                        used_cards++;
                        dealer_cards++;
                        

                        //Dealer takes a new card and adds the card number to dealt cards array
                        //Card number and suit are added to the dealer hand array
                        if (dealer_over_21_ace == false) {
                            CardDeck hit_card_dealer = new CardDeck(dealt_cards);
                            dealt_cards[used_cards] = hit_card_dealer.getCard_Num();
                            dealer_hand[dealer_cards - 1] = hit_card_dealer.getCard();
                            
                            //prints out dealer cards, equivalent values and hand total
                            Hand print_dealer_hand = new Hand (dealer_hand, dealer_cards);
                            System.out.println("\n\nDealer Hand:\n");
                            print_dealer_hand.printHand();
                            dealer_hand_total = print_dealer_hand.getHandValue();
                            

                            //if dealer hand is > 21; the hand is checked to see if there is an Ace in the hand
                            if (dealer_hand_total > 21) {
                                for (int i = 0; i < dealer_cards; i++) {
                                    if (dealer_hand[i].substring(0, 1).equals("A")) {
                                        dealer_over_21_ace = true;
                                        System.out.print("\nPoint Total over 21!  New Ace Value = 1\n");
                                    }
                                }
                            }
                        }
                        
                        //if there is an Ace in the Ace value is set to 1 instead of 11
                        //Card values are showsn with new Ace values and hand total displayed
                        if (dealer_over_21_ace == true) {
                            Hand print_ace_dealer_hand = new Hand (dealer_hand, dealer_cards);
                            System.out.println("\n\nDealer Hand:\n");
                            print_ace_dealer_hand.printAceHand();
                            dealer_hand_total = print_ace_dealer_hand.getAceHandValue();
                        }
                    }
                }
                while(undecided);
            }

            //If you still have money you can play again if you want
            while (money > 0) {
                System.out.println("\n\nDo you want to play again? (Enter 1 for Yes or Enter 2 for No) \n");
                play_again = input.nextInt();

                if (play_again == 1 | play_again == 2) {
                    break;
                }

                else {
                    System.out.println("\nMust Enter a Value of 1 for Yes or 2 for No.  No other values accepted.\n");
                }
            }

            //If you dont have money (money = 0) you cant play again even if you want to
            if (money == 0) {
                System.out.println("\nUnfortunately you lost all your money.  Game is over!");
                play_again = 2;
            }

            //Re-initialize arrays to be empty so all cards can be used if played again (shuffles cards)
            dealt_cards = new int [30];
        }
        while(play_again == 1);
    }
}
