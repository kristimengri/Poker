import org.euler.HandEvaluationResult;
import org.euler.HandTypes;
import org.euler.PokerHandIdentifier;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PokerHandIdentifierTest {


    @Test
    public void testEvaluateHand() {

        // Test case for evaluating a high card hand
        List<String> highCardHand = Arrays.asList("2H", "4D", "7S", "JH", "AH");
        HandEvaluationResult result = PokerHandIdentifier.evaluateHand(highCardHand);
        assertEquals(HandTypes.HIGH_CARD, result.getHandType());
        assertEquals(Arrays.asList(14, 11, 7, 4, 2), result.getRanks());

        // Test case for evaluating a high card hand 1
        List<String> highCardHand1 = Arrays.asList("KH", "AH", "QH", "JH", "8C");
        result = PokerHandIdentifier.evaluateHand(highCardHand1);
        assertEquals(HandTypes.HIGH_CARD, result.getHandType());
        assertEquals(Arrays.asList(14, 13, 12, 11, 8), result.getRanks());

        // Test case for evaluating for one pair
        List<String> onePairCardHand = Arrays.asList("2H", "4D", "2S", "JH", "AH");
        result = PokerHandIdentifier.evaluateHand(onePairCardHand);
        assertEquals(HandTypes.ONE_PAIR, result.getHandType());
        assertEquals(Arrays.asList(2, 2, 14, 11, 4), result.getRanks());

        // Test case for evaluating for one pair 1
        List<String> onePairCardHand1 = Arrays.asList("AH", "8D", "AS", "JH", "2H");
        result = PokerHandIdentifier.evaluateHand(onePairCardHand1);
        assertEquals(HandTypes.ONE_PAIR, result.getHandType());
        assertEquals(Arrays.asList(14, 14, 11, 8, 2), result.getRanks());

        // Test case for evaluating for two pair
        List<String> twoPairCardHand = Arrays.asList("2H", "4D", "2S", "4H", "AH");
        result = PokerHandIdentifier.evaluateHand(twoPairCardHand);
        assertEquals(HandTypes.TWO_PAIR, result.getHandType());
        assertEquals(Arrays.asList(4, 4, 2, 2, 14), result.getRanks());

        // Test case for evaluating for two pair
        List<String> twoPair1CardHand = Arrays.asList("8H", "KD", "TS", "KH", "8C");
        result = PokerHandIdentifier.evaluateHand(twoPair1CardHand);
        assertEquals(HandTypes.TWO_PAIR, result.getHandType());
        assertEquals(Arrays.asList(13, 13, 8, 8, 10), result.getRanks());

        // Test case for evaluating for three of a kind
        List<String> threeOfAKindCardHand = Arrays.asList("2H", "AD", "2S", "4H", "2H");
        result = PokerHandIdentifier.evaluateHand(threeOfAKindCardHand);
        assertEquals(HandTypes.THREE_OF_A_KIND, result.getHandType());
        assertEquals(Arrays.asList(2, 2, 2, 14, 4), result.getRanks());

        // Test case for evaluating for three of a kind 1
        List<String> threeOfAKindCardHand1 = Arrays.asList("TH", "TD", "2S", "4H", "TC");
        result = PokerHandIdentifier.evaluateHand(threeOfAKindCardHand1);
        assertEquals(HandTypes.THREE_OF_A_KIND, result.getHandType());
        assertEquals(Arrays.asList(10, 10, 10, 4, 2), result.getRanks());

        // Test case for evaluating for straight
        List<String> straightCardHand = Arrays.asList("3H", "6D", "5S", "7H", "4H");
        result = PokerHandIdentifier.evaluateHand(straightCardHand);
        assertEquals(HandTypes.STRAIGHT, result.getHandType());
        assertEquals(Arrays.asList(7, 6, 5, 4, 3), result.getRanks());

        // Test case for evaluating for straight 1
        List<String> straightCardHand1 = Arrays.asList("AH", "2H", "5S", "3H", "4H");
        result = PokerHandIdentifier.evaluateHand(straightCardHand1);
        assertEquals(HandTypes.STRAIGHT, result.getHandType());
        assertEquals(Arrays.asList(14, 5, 4, 3, 2), result.getRanks());

        // Test case for evaluating a flush hand
        List<String> flushCardHand = Arrays.asList("2H", "4H", "7H", "JH", "AH");
        result = PokerHandIdentifier.evaluateHand(flushCardHand);
        assertEquals(HandTypes.FLUSH, result.getHandType());
        assertEquals(Arrays.asList(14, 11, 7, 4, 2), result.getRanks());

        // Test case for evaluating a flush hand 1
        List<String> flushCardHand1 = Arrays.asList("AH", "JH", "TH", "QH", "3H");
        result = PokerHandIdentifier.evaluateHand(flushCardHand1);
        assertEquals(HandTypes.FLUSH, result.getHandType());
        assertEquals(Arrays.asList(14, 12, 11, 10, 3), result.getRanks());

        // Test case for evaluating a full house hand
        List<String> fullHouseCardHand = Arrays.asList("2H", "4H", "2D", "2S", "4C");
        result = PokerHandIdentifier.evaluateHand(fullHouseCardHand);
        assertEquals(HandTypes.FULL_HOUSE, result.getHandType());
        assertEquals(Arrays.asList(2, 2, 2, 4, 4), result.getRanks());

        // Test case for evaluating a full house hand 1
        List<String> fullHouseCardHand1 = Arrays.asList("KH", "AH", "KD", "KS", "AC");
        result = PokerHandIdentifier.evaluateHand(fullHouseCardHand1);
        assertEquals(HandTypes.FULL_HOUSE, result.getHandType());
        assertEquals(Arrays.asList(13, 13, 13, 14, 14), result.getRanks());

        // Test case for evaluating a four of a kind hand
        List<String> fourOfAKindCardHand = Arrays.asList("2H", "4C", "2D", "2S", "2C");
        result = PokerHandIdentifier.evaluateHand(fourOfAKindCardHand);
        assertEquals(HandTypes.FOUR_OF_A_KIND, result.getHandType());
        assertEquals(Arrays.asList(2, 2, 2, 2, 4), result.getRanks());

        // Test case for evaluating a four of a kind hand1
        List<String> fourOfAKindCardHand1 = Arrays.asList("6H", "KC", "KD", "KS", "KH");
        result = PokerHandIdentifier.evaluateHand(fourOfAKindCardHand1);
        assertEquals(HandTypes.FOUR_OF_A_KIND, result.getHandType());
        assertEquals(Arrays.asList(13, 13, 13, 13, 6), result.getRanks());

        // Test case for evaluating a straight flush hand
        List<String> straightFlushCardHand = Arrays.asList("6H", "3H", "2H", "5H", "4H");
        result = PokerHandIdentifier.evaluateHand(straightFlushCardHand);
        assertEquals(HandTypes.STRAIGHT_FLUSH, result.getHandType());
        assertEquals(Arrays.asList(6, 5, 4, 3, 2), result.getRanks());

        // Test case for evaluating a straight flush hand 1
        List<String> straightFlushCardHand1 = Arrays.asList("2H", "3H", "AH", "5H", "4H");
        result = PokerHandIdentifier.evaluateHand(straightFlushCardHand1);
        assertEquals(HandTypes.STRAIGHT_FLUSH, result.getHandType());
        assertEquals(Arrays.asList(14, 5, 4, 3, 2), result.getRanks());

        // Test case for evaluating a royal flush hand
        List<String> royalFlushCardHand = Arrays.asList("AH", "QH", "TH", "KH", "JH");
        result = PokerHandIdentifier.evaluateHand(royalFlushCardHand);
        assertEquals(HandTypes.ROYAL_FLUSH, result.getHandType());
        assertEquals(Arrays.asList(14, 13, 12, 11, 10), result.getRanks());

        // Test case for evaluating a royal flush hand
        List<String> royalFlushCardHand1 = Arrays.asList("AD", "QD", "TD", "KD", "JD");
        result = PokerHandIdentifier.evaluateHand(royalFlushCardHand1);
        assertEquals(HandTypes.ROYAL_FLUSH, result.getHandType());
        assertEquals(Arrays.asList(14, 13, 12, 11, 10), result.getRanks());
    }


    @Test
    public void testComparisonBetweenTwoHighCards() {

        // Test case for comparing two hands where player 1 wins
        List<String> player1cards = Arrays.asList("AH", "JD", "7C", "4S", "2H"); // High card Ace
        List<String> player2cards = Arrays.asList("KS", "TH", "8C", "5H", "3S"); // High card King
        HandEvaluationResult player1Eval = PokerHandIdentifier.evaluateHand(player1cards);
        HandEvaluationResult player2Eval = PokerHandIdentifier.evaluateHand(player2cards);
        int result = PokerHandIdentifier.compareHands(player1Eval.getHandType(), player2Eval.getHandType(), player1Eval.getRanks(), player2Eval.getRanks());
        assertEquals(1, result); // Player 1 should win

        player1cards = Arrays.asList("2H", "JD", "7C", "TS", "KH"); // High card King
        player2cards = Arrays.asList("6S", "TH", "8C", "5H", "QS"); // High card Queen
        player1Eval = PokerHandIdentifier.evaluateHand(player1cards);
        player2Eval = PokerHandIdentifier.evaluateHand(player2cards);
        result = PokerHandIdentifier.compareHands(player1Eval.getHandType(), player2Eval.getHandType(), player1Eval.getRanks(), player2Eval.getRanks());
        assertEquals(1, result); // Player 1 should win

        player1cards = Arrays.asList("8H", "JC", "7C", "TS", "KS"); // High card King
        player2cards = Arrays.asList("6C", "AH", "8C", "QC", "7S"); // High card Ace
        player1Eval = PokerHandIdentifier.evaluateHand(player1cards);
        player2Eval = PokerHandIdentifier.evaluateHand(player2cards);
        result = PokerHandIdentifier.compareHands(player1Eval.getHandType(), player2Eval.getHandType(), player1Eval.getRanks(), player2Eval.getRanks());
        assertEquals(0, result); // Player 2 should win

        player1cards = Arrays.asList("2H", "3S", "4C", "5S", "KH"); // High card King
        player2cards = Arrays.asList("6S", "TC", "8H", "AS", "QD"); // High card Ace
        player1Eval = PokerHandIdentifier.evaluateHand(player1cards);
        player2Eval = PokerHandIdentifier.evaluateHand(player2cards);
        result = PokerHandIdentifier.compareHands(player1Eval.getHandType(), player2Eval.getHandType(), player1Eval.getRanks(), player2Eval.getRanks());
        assertEquals(0, result); // Player 2 should win
    }


    @Test
    public void testComparisonBetweenTwoOnePairs() {

        List<String> player1cards = Arrays.asList("AH", "AS", "7C", "8S", "9D"); // One pair Aces
        List<String> player2cards = Arrays.asList("KH", "KS", "8D", "3S", "2C"); // One pair Kings
        HandEvaluationResult player1Eval = PokerHandIdentifier.evaluateHand(player1cards);
        HandEvaluationResult player2Eval = PokerHandIdentifier.evaluateHand(player2cards);
        int result = PokerHandIdentifier.compareHands(player1Eval.getHandType(), player2Eval.getHandType(), player1Eval.getRanks(), player2Eval.getRanks());
        assertEquals(1, result); // Player 1 should win

        player1cards = Arrays.asList("AH", "AS", "7C", "8S", "9D"); // One pair Aces 9 High
        player2cards = Arrays.asList("AC", "KS", "AD", "3S", "2C"); // One pair Aces King High
        player1Eval = PokerHandIdentifier.evaluateHand(player1cards);
        player2Eval = PokerHandIdentifier.evaluateHand(player2cards);
        result = PokerHandIdentifier.compareHands(player1Eval.getHandType(), player2Eval.getHandType(), player1Eval.getRanks(), player2Eval.getRanks());
        assertEquals(0, result); // Player 2 should win

        player1cards = Arrays.asList("2H", "AS", "7C", "8S", "2D"); // One pair deuces
        player2cards = Arrays.asList("JC", "KS", "3D", "3S", "2C"); // One pair threes
        player1Eval = PokerHandIdentifier.evaluateHand(player1cards);
        player2Eval = PokerHandIdentifier.evaluateHand(player2cards);
        result = PokerHandIdentifier.compareHands(player1Eval.getHandType(), player2Eval.getHandType(), player1Eval.getRanks(), player2Eval.getRanks());
        assertEquals(0, result); // Player 2 should win

    }

    @Test
    public void testComparisonBetweenTwoTwoPairs() {

        List<String> player1cards = Arrays.asList("AH", "AS", "7C", "7S", "9D"); // Two pair (Aces and Sevens)
        List<String> player2cards = Arrays.asList("KH", "KS", "8D", "3S", "8C"); // Two pair (Kings and Eights)
        HandEvaluationResult player1Eval = PokerHandIdentifier.evaluateHand(player1cards);
        HandEvaluationResult player2Eval = PokerHandIdentifier.evaluateHand(player2cards);
        int result = PokerHandIdentifier.compareHands(player1Eval.getHandType(), player2Eval.getHandType(), player1Eval.getRanks(), player2Eval.getRanks());
        assertEquals(1, result); // Player 1 should win


        player1cards = Arrays.asList("6S", "5C", "6C", "8D", "8H"); // Two pair (Eights and Sixes)
        player2cards = Arrays.asList("5D", "8C", "AD", "8S", "5S"); // Two pair (Eights and Fives)
        player1Eval = PokerHandIdentifier.evaluateHand(player1cards);
        player2Eval = PokerHandIdentifier.evaluateHand(player2cards);
        result = PokerHandIdentifier.compareHands(player1Eval.getHandType(), player2Eval.getHandType(), player1Eval.getRanks(), player2Eval.getRanks());
        assertEquals(1, result); // Player 1 should win

        player1cards = Arrays.asList("6S", "5C", "6C", "8D", "8H"); // Two pair (Eights and Sixes) 5 High
        player2cards = Arrays.asList("6H", "6D", "4D", "8S", "8C"); // Two pair (Eights and Sixes) 4 High
        player1Eval = PokerHandIdentifier.evaluateHand(player1cards);
        player2Eval = PokerHandIdentifier.evaluateHand(player2cards);
        result = PokerHandIdentifier.compareHands(player1Eval.getHandType(), player2Eval.getHandType(), player1Eval.getRanks(), player2Eval.getRanks());
        assertEquals(1, result); // Player 1 should win

        player1cards = Arrays.asList("6H", "6D", "4D", "5S", "4H"); // Two pair (Sixes and Fours)
        player2cards = Arrays.asList("6S", "5C", "6C", "5D", "8H"); // Two pair (Sixes and Fives)
        player1Eval = PokerHandIdentifier.evaluateHand(player1cards);
        player2Eval = PokerHandIdentifier.evaluateHand(player2cards);
        result = PokerHandIdentifier.compareHands(player1Eval.getHandType(), player2Eval.getHandType(), player1Eval.getRanks(), player2Eval.getRanks());
        assertEquals(0, result); // Player 2 should win

    }

    @Test
    public void testComparisonBetweenTwoThreeOfAKinds() {

        List<String> player1cards = Arrays.asList("AH", "AS", "7C", "AC", "9D"); // Three of a Kind Aces
        List<String> player2cards = Arrays.asList("KH", "KS", "8D", "3S", "KC"); // Three of a Kind Kings
        HandEvaluationResult player1Eval = PokerHandIdentifier.evaluateHand(player1cards);
        HandEvaluationResult player2Eval = PokerHandIdentifier.evaluateHand(player2cards);
        int result = PokerHandIdentifier.compareHands(player1Eval.getHandType(), player2Eval.getHandType(), player1Eval.getRanks(), player2Eval.getRanks());
        assertEquals(1, result); // Player 1 should win

        player1cards = Arrays.asList("AH", "AS", "7C", "AC", "9D"); // Three of a Kind Aces 9 High
        player2cards = Arrays.asList("AH", "AS", "8D", "3S", "AC"); // Three of a Kind Aces 8 High
        player1Eval = PokerHandIdentifier.evaluateHand(player1cards);
        player2Eval = PokerHandIdentifier.evaluateHand(player2cards);
        result = PokerHandIdentifier.compareHands(player1Eval.getHandType(), player2Eval.getHandType(), player1Eval.getRanks(), player2Eval.getRanks());
        assertEquals(1, result); // Player 1 should win

        player1cards = Arrays.asList("AH", "AS", "7C", "AC", "2D"); // Three of a Kind Aces 7,2 High
        player2cards = Arrays.asList("AH", "AS", "7D", "3S", "AC"); // Three of a Kind Aces 7,3 High
        player1Eval = PokerHandIdentifier.evaluateHand(player1cards);
        player2Eval = PokerHandIdentifier.evaluateHand(player2cards);
        result = PokerHandIdentifier.compareHands(player1Eval.getHandType(), player2Eval.getHandType(), player1Eval.getRanks(), player2Eval.getRanks());
        assertEquals(0, result); // Player 2 should win

        player1cards = Arrays.asList("3H", "4S", "3D", "2S", "3C"); // Three of a Kind Fives
        player2cards = Arrays.asList("4H", "2S", "5C", "5H", "5D"); // Three of a Kind Threes
        player1Eval = PokerHandIdentifier.evaluateHand(player1cards);
        player2Eval = PokerHandIdentifier.evaluateHand(player2cards);
        result = PokerHandIdentifier.compareHands(player1Eval.getHandType(), player2Eval.getHandType(), player1Eval.getRanks(), player2Eval.getRanks());
        assertEquals(0, result); // Player 2 should win
    }

    @Test
    public void testComparisonBetweenTwoStraights() {

        List<String> player1cards = Arrays.asList("KH", "AS", "QC", "JC", "TD"); // Straight A-T
        List<String> player2cards = Arrays.asList("3H", "7S", "6D", "5S", "4C"); // Straight 7-3
        HandEvaluationResult player1Eval = PokerHandIdentifier.evaluateHand(player1cards);
        HandEvaluationResult player2Eval = PokerHandIdentifier.evaluateHand(player2cards);
        int result = PokerHandIdentifier.compareHands(player1Eval.getHandType(), player2Eval.getHandType(), player1Eval.getRanks(), player2Eval.getRanks());
        assertEquals(1, result); // Player 1 should win

        player1cards = Arrays.asList("6H", "9S", "8C", "7C", "5D"); // Straight 9-5
        player2cards = Arrays.asList("8H", "4S", "6D", "7S", "5C"); // Straight 8-4
        player1Eval = PokerHandIdentifier.evaluateHand(player1cards);
        player2Eval = PokerHandIdentifier.evaluateHand(player2cards);
        result = PokerHandIdentifier.compareHands(player1Eval.getHandType(), player2Eval.getHandType(), player1Eval.getRanks(), player2Eval.getRanks());
        assertEquals(1, result); // Player 1 should win

        player1cards = Arrays.asList("AH", "4S", "3C", "2C", "5D"); // Straight A-5
        player2cards = Arrays.asList("TH", "QS", "9D", "8S", "JC"); // Straight Q-J
        player1Eval = PokerHandIdentifier.evaluateHand(player1cards);
        player2Eval = PokerHandIdentifier.evaluateHand(player2cards);
        result = PokerHandIdentifier.compareHands(player1Eval.getHandType(), player2Eval.getHandType(), player1Eval.getRanks(), player2Eval.getRanks());
        assertEquals(0, result); // Player 2 should win
    }


    @Test
    public void testComparisonBetweenTwoFlushes() {

        List<String> player1cards = Arrays.asList("KH", "AH", "5H", "TH", "9H"); // Flush Ace High
        List<String> player2cards = Arrays.asList("QH", "5H", "9H", "TH", "4H"); // Flush Queen High
        HandEvaluationResult player1Eval = PokerHandIdentifier.evaluateHand(player1cards);
        HandEvaluationResult player2Eval = PokerHandIdentifier.evaluateHand(player2cards);
        int result = PokerHandIdentifier.compareHands(player1Eval.getHandType(), player2Eval.getHandType(), player1Eval.getRanks(), player2Eval.getRanks());
        assertEquals(1, result); // Player 1 should win

        player1cards = Arrays.asList("5H", "JH", "4H", "3H", "2H"); // Flush Jack High
        player2cards = Arrays.asList("2H", "5H", "4H", "TH", "QH"); // Flush Queen High
        player1Eval = PokerHandIdentifier.evaluateHand(player1cards);
        player2Eval = PokerHandIdentifier.evaluateHand(player2cards);
        result = PokerHandIdentifier.compareHands(player1Eval.getHandType(), player2Eval.getHandType(), player1Eval.getRanks(), player2Eval.getRanks());
        assertEquals(0, result); // Player 2 should win

        player1cards = Arrays.asList("5H", "TH", "4H", "3H", "2H"); // Flush Ten High
        player2cards = Arrays.asList("2C", "5C", "4C", "9C", "8C"); // Flush Nine High
        player1Eval = PokerHandIdentifier.evaluateHand(player1cards);
        player2Eval = PokerHandIdentifier.evaluateHand(player2cards);
        result = PokerHandIdentifier.compareHands(player1Eval.getHandType(), player2Eval.getHandType(), player1Eval.getRanks(), player2Eval.getRanks());
        assertEquals(1, result); // Player 1 should win
    }

    @Test
    public void testComparisonBetweenTwoFullHouses() {


        List<String> player1cards = Arrays.asList("5H", "AH", "5C", "AC", "5S"); // Full House Fives and Aces
        List<String> player2cards = Arrays.asList("QH", "7D", "7H", "7C", "QC"); // Full House Sevens and Queens
        HandEvaluationResult player1Eval = PokerHandIdentifier.evaluateHand(player1cards);
        HandEvaluationResult player2Eval = PokerHandIdentifier.evaluateHand(player2cards);
        int result = PokerHandIdentifier.compareHands(player1Eval.getHandType(), player2Eval.getHandType(), player1Eval.getRanks(), player2Eval.getRanks());
        assertEquals(0, result); // Player 2 should win

        player1cards = Arrays.asList("5H", "AH", "5C", "AC", "5S"); // Full House Fives and Aces
        player2cards = Arrays.asList("5H", "5S", "KH", "KC", "5C"); // Full House Fives and Kings
        player1Eval = PokerHandIdentifier.evaluateHand(player1cards);
        player2Eval = PokerHandIdentifier.evaluateHand(player2cards);
        result = PokerHandIdentifier.compareHands(player1Eval.getHandType(), player2Eval.getHandType(), player1Eval.getRanks(), player2Eval.getRanks());
        assertEquals(1, result); // Player 1 should win

        player1cards = Arrays.asList("4H", "KH", "4C", "KC", "4S"); // Full House Fours and Kings
        player2cards = Arrays.asList("AH", "AS", "5H", "5C", "5D"); // Full House Fives and Aces
        player1Eval = PokerHandIdentifier.evaluateHand(player1cards);
        player2Eval = PokerHandIdentifier.evaluateHand(player2cards);
        result = PokerHandIdentifier.compareHands(player1Eval.getHandType(), player2Eval.getHandType(), player1Eval.getRanks(), player2Eval.getRanks());
        assertEquals(0, result); // Player 2 should win

        player1cards = Arrays.asList("4H", "KH", "4C", "KC", "4S"); // Full House Fours and Kings
        player2cards = Arrays.asList("KH", "8S", "8H", "KC", "8C"); // Full House Eights and Kings
        player1Eval = PokerHandIdentifier.evaluateHand(player1cards);
        player2Eval = PokerHandIdentifier.evaluateHand(player2cards);
        result = PokerHandIdentifier.compareHands(player1Eval.getHandType(), player2Eval.getHandType(), player1Eval.getRanks(), player2Eval.getRanks());
        assertEquals(0, result); // Player 2 should win

    }

    @Test
    public void testComparisonBetweenTwoFourOfAKinds() {

        List<String> player1cards = Arrays.asList("5H", "5D", "5C", "AC", "5S"); // Four of a Kind Fives Ace High
        List<String> player2cards = Arrays.asList("KH", "5D", "5H", "5C", "5S"); // Four of a Kind Fives King High
        HandEvaluationResult player1Eval = PokerHandIdentifier.evaluateHand(player1cards);
        HandEvaluationResult player2Eval = PokerHandIdentifier.evaluateHand(player2cards);
        int result = PokerHandIdentifier.compareHands(player1Eval.getHandType(), player2Eval.getHandType(), player1Eval.getRanks(), player2Eval.getRanks());
        assertEquals(1, result); // Player 1 should win

        player1cards = Arrays.asList("AH", "AD", "6C", "AC", "AS"); // Four of a Kind Aces
        player2cards = Arrays.asList("KH", "2D", "2H", "2C", "2S"); // Four of a Kind Deuces
        player1Eval = PokerHandIdentifier.evaluateHand(player1cards);
        player2Eval = PokerHandIdentifier.evaluateHand(player2cards);
        result = PokerHandIdentifier.compareHands(player1Eval.getHandType(), player2Eval.getHandType(), player1Eval.getRanks(), player2Eval.getRanks());
        assertEquals(1, result); // Player 1 should win

        player1cards = Arrays.asList("2H", "2D", "6C", "2C", "2S"); // Four of a Kind Deuces
        player2cards = Arrays.asList("3H", "3D", "5H", "3C", "3S"); // Four of a Kind Threes
        player1Eval = PokerHandIdentifier.evaluateHand(player1cards);
        player2Eval = PokerHandIdentifier.evaluateHand(player2cards);
        result = PokerHandIdentifier.compareHands(player1Eval.getHandType(), player2Eval.getHandType(), player1Eval.getRanks(), player2Eval.getRanks());
        assertEquals(0, result); // Player 2 should win

    }

    @Test
    public void testComparisonBetweenTwoStraightFlushes() {

        List<String> player1cards = Arrays.asList("TH", "9H", "QH", "JH", "KH"); // Straight Flush K-9
        List<String> player2cards = Arrays.asList("QH", "9H", "JH", "TH", "8H"); // Straight Flush Q-8
        HandEvaluationResult player1Eval = PokerHandIdentifier.evaluateHand(player1cards);
        HandEvaluationResult player2Eval = PokerHandIdentifier.evaluateHand(player2cards);
        int result = PokerHandIdentifier.compareHands(player1Eval.getHandType(), player2Eval.getHandType(), player1Eval.getRanks(), player2Eval.getRanks());
        assertEquals(1, result); // Player 1 should win


        player1cards = Arrays.asList("TH", "9H", "QH", "JH", "KH"); // Straight Flush K-9
        player2cards = Arrays.asList("2H", "6H", "3H", "5H", "4H"); // Straight Flush 6-2
        player1Eval = PokerHandIdentifier.evaluateHand(player1cards);
        player2Eval = PokerHandIdentifier.evaluateHand(player2cards);
        result = PokerHandIdentifier.compareHands(player1Eval.getHandType(), player2Eval.getHandType(), player1Eval.getRanks(), player2Eval.getRanks());
        assertEquals(1, result); // Player 1 should win

        player1cards = Arrays.asList("2S", "6S", "3S", "5S", "4S"); // Straight Flush 6-2
        player2cards = Arrays.asList("TH", "9H", "QH", "JH", "KH"); // Straight Flush K-9
        player1Eval = PokerHandIdentifier.evaluateHand(player1cards);
        player2Eval = PokerHandIdentifier.evaluateHand(player2cards);
        result = PokerHandIdentifier.compareHands(player1Eval.getHandType(), player2Eval.getHandType(), player1Eval.getRanks(), player2Eval.getRanks());
        assertEquals(0, result); // Player 2 should win

        player1cards = Arrays.asList("2S", "AS", "3S", "5S", "4S"); // Straight Flush 5-A
        player2cards = Arrays.asList("2S", "6S", "3S", "5S", "4S"); // Straight Flush 6-2
        player1Eval = PokerHandIdentifier.evaluateHand(player1cards);
        player2Eval = PokerHandIdentifier.evaluateHand(player2cards);
        result = PokerHandIdentifier.compareHands(player1Eval.getHandType(), player2Eval.getHandType(), player1Eval.getRanks(), player2Eval.getRanks());
        assertEquals(0, result); // Player 2 should win

        player1cards = Arrays.asList("QS", "KS", "JS", "TS", "9S"); // Straight Flush K-9
        player2cards = Arrays.asList("2S", "AS", "3S", "5S", "4S"); // Straight Flush 5-A
        player1Eval = PokerHandIdentifier.evaluateHand(player1cards);
        player2Eval = PokerHandIdentifier.evaluateHand(player2cards);
        result = PokerHandIdentifier.compareHands(player1Eval.getHandType(), player2Eval.getHandType(), player1Eval.getRanks(), player2Eval.getRanks());
        assertEquals(1, result); // Player 1 should win
    }


    @Test
    public void testDifferentHands(){

        /* One Pair vs High Card */
        List<String> player1cards = Arrays.asList("AH", "4H", "4D", "3H", "8D"); // One Pair Fours
        List<String> player2cards = Arrays.asList("QH", "9H", "JH", "TH", "3D"); // High Card Queen
        HandEvaluationResult player1Eval = PokerHandIdentifier.evaluateHand(player1cards);
        HandEvaluationResult player2Eval = PokerHandIdentifier.evaluateHand(player2cards);
        int result = PokerHandIdentifier.compareHands(player1Eval.getHandType(), player2Eval.getHandType(), player1Eval.getRanks(), player2Eval.getRanks());
        assertEquals(1, result); // Player 1 should win

        /* One Pair vs Two Pair */
        player1cards = Arrays.asList("AH", "4H", "4D", "3H", "8D"); // One Pair Fours
        player2cards = Arrays.asList("QH", "4H", "3D", "3H", "4D"); // Two Pair Fours and Threes
        player1Eval = PokerHandIdentifier.evaluateHand(player1cards);
        player2Eval = PokerHandIdentifier.evaluateHand(player2cards);
        result = PokerHandIdentifier.compareHands(player1Eval.getHandType(), player2Eval.getHandType(), player1Eval.getRanks(), player2Eval.getRanks());
        assertEquals(0, result); // Player 2 should win


        /* Two Pair vs Three of a Kind */
        player1cards = Arrays.asList("2H", "4H", "2D", "3H", "2S"); // Three of a Kind Deuces
        player2cards = Arrays.asList("QH", "4H", "3D", "3H", "4D"); // Two Pair Fours and Threes
        player1Eval = PokerHandIdentifier.evaluateHand(player1cards);
        player2Eval = PokerHandIdentifier.evaluateHand(player2cards);
        result = PokerHandIdentifier.compareHands(player1Eval.getHandType(), player2Eval.getHandType(), player1Eval.getRanks(), player2Eval.getRanks());
        assertEquals(1, result); // Player 1 should win

        /* Three of a Kind vs Straight */
        player1cards = Arrays.asList("2H", "4H", "2D", "3H", "2S"); // Three of a Kind Deuces
        player2cards = Arrays.asList("AH", "5H", "2D", "3H", "4D"); // Straight 5-A
        player1Eval = PokerHandIdentifier.evaluateHand(player1cards);
        player2Eval = PokerHandIdentifier.evaluateHand(player2cards);
        result = PokerHandIdentifier.compareHands(player1Eval.getHandType(), player2Eval.getHandType(), player1Eval.getRanks(), player2Eval.getRanks());
        assertEquals(0, result); // Player 2 should win

        /* Straight vs Flush */
        player1cards = Arrays.asList("2H", "4H", "KH", "5H", "AH"); // Flush
        player2cards = Arrays.asList("AH", "5H", "2D", "3H", "4D"); // Straight 5-A
        player1Eval = PokerHandIdentifier.evaluateHand(player1cards);
        player2Eval = PokerHandIdentifier.evaluateHand(player2cards);
        result = PokerHandIdentifier.compareHands(player1Eval.getHandType(), player2Eval.getHandType(), player1Eval.getRanks(), player2Eval.getRanks());
        assertEquals(1, result); // Player 1 should win

        /* Flush vs Full House */
        player1cards = Arrays.asList("2H", "4H", "KH", "5H", "AH"); // Flush
        player2cards = Arrays.asList("4H", "3D", "4D", "3H", "4S"); // Full House Fours and Threes
        player1Eval = PokerHandIdentifier.evaluateHand(player1cards);
        player2Eval = PokerHandIdentifier.evaluateHand(player2cards);
        result = PokerHandIdentifier.compareHands(player1Eval.getHandType(), player2Eval.getHandType(), player1Eval.getRanks(), player2Eval.getRanks());
        assertEquals(0, result); // Player 2 should win

        /* Full House vs Four of a Kind */
        player1cards = Arrays.asList("2H", "2S", "2C", "2D", "AH"); // Four of a Kind Deuces
        player2cards = Arrays.asList("4H", "3D", "4D", "3H", "4S"); // Full House Fours and Threes
        player1Eval = PokerHandIdentifier.evaluateHand(player1cards);
        player2Eval = PokerHandIdentifier.evaluateHand(player2cards);
        result = PokerHandIdentifier.compareHands(player1Eval.getHandType(), player2Eval.getHandType(), player1Eval.getRanks(), player2Eval.getRanks());
        assertEquals(1, result); // Player 1 should win

        /* Four of a Kind vs Straight Flush */
        player1cards = Arrays.asList("2H", "2S", "2C", "2D", "AH"); // Four of a Kind Deuces
        player2cards = Arrays.asList("AH", "3H", "2H", "4H", "5H"); // Straight Flush 5-A
        player1Eval = PokerHandIdentifier.evaluateHand(player1cards);
        player2Eval = PokerHandIdentifier.evaluateHand(player2cards);
        result = PokerHandIdentifier.compareHands(player1Eval.getHandType(), player2Eval.getHandType(), player1Eval.getRanks(), player2Eval.getRanks());
        assertEquals(0, result); // Player 2 should win

        /* Straight Flush vs Royal Flush */
        player1cards = Arrays.asList("TH", "AH", "QH", "KH", "JH"); // Royal Flush A-10
        player2cards = Arrays.asList("AH", "3H", "2H", "4H", "5H"); // Straight Flush 5-A
        player1Eval = PokerHandIdentifier.evaluateHand(player1cards);
        player2Eval = PokerHandIdentifier.evaluateHand(player2cards);
        result = PokerHandIdentifier.compareHands(player1Eval.getHandType(), player2Eval.getHandType(), player1Eval.getRanks(), player2Eval.getRanks());
        assertEquals(1, result); // Player 1 should win


        /* Three of a Kind vs Full House */
        player1cards = Arrays.asList("2H", "4H", "2D", "3H", "2S"); // Three of a Kind Deuces
        player2cards = Arrays.asList("4C", "4H", "2D", "2H", "2S"); // Full House Deuces and Fours
        player1Eval = PokerHandIdentifier.evaluateHand(player1cards);
        player2Eval = PokerHandIdentifier.evaluateHand(player2cards);
        result = PokerHandIdentifier.compareHands(player1Eval.getHandType(), player2Eval.getHandType(), player1Eval.getRanks(), player2Eval.getRanks());
        assertEquals(0, result); // Player 2 should win


        /* Straight vs Straight Flush */
        player1cards = Arrays.asList("8H", "9H", "JH", "7H", "TH"); // Straight Flush J-7
        player2cards = Arrays.asList("AH", "5H", "2D", "3H", "4D"); // Straight 5-A
        player1Eval = PokerHandIdentifier.evaluateHand(player1cards);
        player2Eval = PokerHandIdentifier.evaluateHand(player2cards);
        result = PokerHandIdentifier.compareHands(player1Eval.getHandType(), player2Eval.getHandType(), player1Eval.getRanks(), player2Eval.getRanks());
        assertEquals(1, result); // Player 1 should win


        /* Flush vs Straight Flush */
        player1cards = Arrays.asList("2H", "4H", "KH", "5H", "AH"); // Flush King High
        player2cards = Arrays.asList("6H", "5H", "4H", "2H", "3H"); // Straight Flush 6-2
        player1Eval = PokerHandIdentifier.evaluateHand(player1cards);
        player2Eval = PokerHandIdentifier.evaluateHand(player2cards);
        result = PokerHandIdentifier.compareHands(player1Eval.getHandType(), player2Eval.getHandType(), player1Eval.getRanks(), player2Eval.getRanks());
        assertEquals(0, result); // Player 2 should win

        /* Royal Flush vs High Card */
        player1cards = Arrays.asList("TH", "AH", "QH", "KH", "JH"); // Royal Flush A-10
        player2cards = Arrays.asList("AH", "3D", "8S", "4H", "5H"); // High Card Ace
        player1Eval = PokerHandIdentifier.evaluateHand(player1cards);
        player2Eval = PokerHandIdentifier.evaluateHand(player2cards);
        result = PokerHandIdentifier.compareHands(player1Eval.getHandType(), player2Eval.getHandType(), player1Eval.getRanks(), player2Eval.getRanks());
        assertEquals(1, result); // Player 1 should win

    }


}
