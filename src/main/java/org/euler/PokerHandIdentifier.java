package org.euler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PokerHandIdentifier {
    public static void main(String[] args) {
        int winsP1 = 0;  // Number of hands player1 wins

        List<String> hands = new ArrayList<>();  // Hands saved in a list of Strings
        String filePath = "src/main/resources/poker.txt";   // Adding the poker.txt file to the
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    hands.add(line.trim());
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        System.out.println("Number of hands read: " + hands.size());

        for (String hand : hands) {
            List<String> handCards = Arrays.asList(hand.split(" "));
            List<String> player1Cards = handCards.subList(0, 5);  //subtract first 5 cards
            List<String> player2Cards = handCards.subList(5, 10); //subtract second 5 cards

            HandEvaluationResult player1Result = evaluateHand(player1Cards); // Evaluate player 1 hand (HandType and ranked cards)
            HandEvaluationResult player2Result = evaluateHand(player2Cards); // Evaluate player 2 hand (HandType and ranked cards)

            HandTypes player1HandType = player1Result.getHandType(); //handType for player 1
            HandTypes player2HandType = player2Result.getHandType();  //handType for player 2

            List<Integer> player1Ranks = player1Result.getRanks();  //HandRank for player 1 (Ranked after poker rules)
            List<Integer> player2Ranks = player2Result.getRanks();  //HandRank for player 2 (Ranked after poker rules)

            int result = compareHands(player1HandType, player2HandType, player1Ranks, player2Ranks);  //Comparison between hands, return 1 if player 1 wins and return 0 if player 2 wins

            if (result > 0) {
                winsP1++; //Increase by 1 the wins of player 1 each time result is 1.
            }
        }
        System.out.println("Player 1 wins: " + winsP1);  //Print the number of how many hands player 1 won.

    }


    //Hand evaluate method
    public static HandEvaluationResult evaluateHand(List<String> cards) {
        // Determine hand type
        Map<HandTypes, List<Integer>> handResult = determineHandType(cards);
        HandTypes handType = handResult.keySet().iterator().next(); // Get the hand type from the result map
        List<Integer> result = handResult.get(handType); // Get the list of ranks forming the hand

        return new HandEvaluationResult(handType, result);
    }

    private static Map<HandTypes, List<Integer>> determineHandType(List<String> cards) {

        List<Integer> ranks = new ArrayList<>();
        Set<Character> suits = new HashSet<>();

        for (String card : cards) {
            char rankChar = card.charAt(0);
            char suitChar = card.charAt(1);

            // Convert rank character to rank value
            int rankValue = getRankValue(rankChar);
            ranks.add(rankValue);

            // Collect suits
            suits.add(suitChar);
        }
        Map<Integer, Integer> rankCount = new HashMap<>();

        //Populate rankCount with card ranks and their counts
        for (int rank : ranks) {
            rankCount.put(rank, rankCount.getOrDefault(rank, 0) + 1);
        }
        //Result keeps handTypes and a list of integers for hand ranked card
        Map<HandTypes, List<Integer>> result = new HashMap<>();

        /*======= Logic for determine hand===========*/

        //First check if is flush, straight ,straight flush or royal flush. If none of them is true then check for the rest handTypes because suits are no more important after that.
        // Check for flush
        boolean isFlush = (suits.size() == 1);
        // All cards have the same suit

        boolean isStraight = isStraight(ranks);

        // Check for royal flush
        if (isFlush && isStraight && ranks.contains(10) && ranks.contains(14)) {
            Collections.sort(ranks, Collections.reverseOrder());
            result.put(HandTypes.ROYAL_FLUSH, ranks);
        }
        // Check for straight flush
        else if (isFlush && isStraight) {
            Collections.sort(ranks, Collections.reverseOrder());
            result.put(HandTypes.STRAIGHT_FLUSH, ranks);
        }
        // Check for flush
        else if (isFlush) {
            Collections.sort(ranks, Collections.reverseOrder());
            result.put(HandTypes.FLUSH, ranks);
        }
        // Check for straight
        else if (isStraight) {
            Collections.sort(ranks, Collections.reverseOrder());
            result.put(HandTypes.STRAIGHT, ranks);
        } else {

            //Now I check for paired cards.
            // Determine the type of poker hand and the cards forming the hand
            List<Integer> pairCards = new ArrayList<>();
            List<Integer> threeOfAKindCards = new ArrayList<>();
            List<Integer> fourOfAKindCards = new ArrayList<>();

            // Gather cards for each type of hand
            for (Map.Entry<Integer, Integer> entry : rankCount.entrySet()) {
                int rank = entry.getKey();
                int count = entry.getValue();

                if (count == 2) {
                    pairCards.add(rank);
                } else if (count == 3) {
                    threeOfAKindCards.add(rank);
                } else if (count == 4) {
                    fourOfAKindCards.add(rank);
                }
            }

            // Sort cards in descending order for each type of hand
            pairCards.sort(Comparator.reverseOrder());
            threeOfAKindCards.sort(Comparator.reverseOrder());
            fourOfAKindCards.sort(Comparator.reverseOrder());

            // Identify the hand type and construct the result list

            if (!fourOfAKindCards.isEmpty()) {
                List<Integer> handCards = new ArrayList<>();
                int fourKindRank = fourOfAKindCards.get(0); // Get the rank of the four of a kind
                handCards.add(fourKindRank);
                handCards.add(fourKindRank);
                handCards.add(fourKindRank);
                handCards.add(fourKindRank);
                handCards.add(getLastCardNotInFourOfAKind(ranks, fourKindRank));
                result.put(HandTypes.FOUR_OF_A_KIND, handCards);

            } else if (!threeOfAKindCards.isEmpty() && !pairCards.isEmpty()) {
                List<Integer> handCards = new ArrayList<>();
                handCards.add(threeOfAKindCards.get(0));
                handCards.add(threeOfAKindCards.get(0));
                handCards.add(threeOfAKindCards.get(0));
                handCards.add(pairCards.get(0));
                handCards.add(pairCards.get(0));
                result.put(HandTypes.FULL_HOUSE, handCards);

            } else if (!threeOfAKindCards.isEmpty()) {
                List<Integer> handCards = new ArrayList<>();
                handCards.add(threeOfAKindCards.get(0));
                handCards.add(threeOfAKindCards.get(0));
                handCards.add(threeOfAKindCards.get(0));
                fillRemainingCards(handCards, ranks, threeOfAKindCards, 2);
                result.put(HandTypes.THREE_OF_A_KIND, handCards);

            } else if (pairCards.size() >= 2) {
                List<Integer> handCards = new ArrayList<>();
                handCards.add(pairCards.get(0));
                handCards.add(pairCards.get(0));
                handCards.add(pairCards.get(1));
                handCards.add(pairCards.get(1));
                fillRemainingCards(handCards, ranks, pairCards, 4);
                result.put(HandTypes.TWO_PAIR, handCards);

            } else if (pairCards.size() == 1) {
                List<Integer> handCards = new ArrayList<>();
                handCards.add(pairCards.get(0));
                handCards.add(pairCards.get(0));
                fillRemainingCards(handCards, ranks, pairCards, 3);
                result.put(HandTypes.ONE_PAIR, handCards);

            } else {
                List<Integer> handCards = new ArrayList<>();
                fillRemainingCards(handCards, ranks, Collections.emptyList(), 5);
                result.put(HandTypes.HIGH_CARD, handCards);
            }

        }
        return result;
    }


    //This method is used to compare two hands
    public static int compareHands(HandTypes handType1, HandTypes handType2, List<Integer> ranks1, List<Integer> ranks2) {
        //Valued each hand of poker from high card to royal flush with points from 1 to 10 to make the hand comparison
        HashMap<HandTypes, Integer> handRanking = new HashMap<>();
        handRanking.put(HandTypes.HIGH_CARD, 1);
        handRanking.put(HandTypes.ONE_PAIR, 2);
        handRanking.put(HandTypes.TWO_PAIR, 3);
        handRanking.put(HandTypes.THREE_OF_A_KIND, 4);
        handRanking.put(HandTypes.STRAIGHT, 5);
        handRanking.put(HandTypes.FLUSH, 6);
        handRanking.put(HandTypes.FULL_HOUSE, 7);
        handRanking.put(HandTypes.FOUR_OF_A_KIND, 8);
        handRanking.put(HandTypes.STRAIGHT_FLUSH, 9);
        handRanking.put(HandTypes.ROYAL_FLUSH, 10);

        int handRanking1 = handRanking.getOrDefault(handType1, 0);  //get hand ranking points for player 1
        int handRanking2 = handRanking.getOrDefault(handType2, 0);  //get hand ranking points for player 2

        if (handRanking1 > handRanking2) {
            return 1;  //if player One hand is stronger than player Two than return 1 immediately
        } else if (handRanking1 < handRanking2) {  //else if player Two hand is stronger than player One than return 0 because I count only player One winnings.
            return 0;
        } else {
            // else there is a draw of hand points which means there is the same hand type for both players.
            // now check for specific hand comparison
            switch (handType1) {
                case HIGH_CARD:
                    return compareSpecificRanksForHighCardToRoyalFlush(ranks1, ranks2);
                case ONE_PAIR:
                    return compareSpecificRanksForHighCardToRoyalFlush(ranks1, ranks2);
                case TWO_PAIR:
                    return compareSpecificRanksForHighCardToRoyalFlush(ranks1, ranks2);
                case THREE_OF_A_KIND:
                    return compareSpecificRanksForHighCardToRoyalFlush(ranks1, ranks2);
                case STRAIGHT:
                    return compareSpecificRanksForStraightAndStraightFlushOnly(ranks1, ranks2);
                case FLUSH:
                    return compareSpecificRanksForHighCardToRoyalFlush(ranks1, ranks2);
                case FULL_HOUSE:
                    return compareSpecificRanksForHighCardToRoyalFlush(ranks1, ranks2);
                case FOUR_OF_A_KIND:
                    return compareSpecificRanksForHighCardToRoyalFlush(ranks1, ranks2);
                case STRAIGHT_FLUSH:
                    return compareSpecificRanksForStraightAndStraightFlushOnly(ranks1, ranks2);
                case ROYAL_FLUSH:
                    return compareSpecificRanksForHighCardToRoyalFlush(ranks1, ranks2);
            }

        }
        return 0;
    }


    //This comparison for specific ranks high card to royal flush but does not include straight and straight flush due to Ace to Five Straight (Ace is 1 point in that case)
    private static int compareSpecificRanksForHighCardToRoyalFlush(List<Integer> ranks1, List<Integer> ranks2) {

        for (int i = 0; i < ranks1.size(); i++) {
            int rank1 = ranks1.get(i);
            int rank2 = ranks2.get(i);

            // Compare ranks at the current position
            if (rank2 > rank1) {
                return 0; // If ranks in ranks2 are greater, return 0 (indicating ranks2 wins)
            } else if (rank1 > rank2) {
                return 1; // If ranks in ranks1 are greater, return 1 (indicating ranks1 wins)
            }
            // If ranks are equal, continue to the next position
        }
        // If all specific positions are tied, return 0 (indicating a tie)
        return 0;
    }

    //This comparison is specific only for straight and straight flush due to Ace to Five Straight (Ace is 1 point)
    private static int compareSpecificRanksForStraightAndStraightFlushOnly(List<Integer> ranks1, List<Integer> ranks2) {
        // Sort ranks in descending order to identify the highest card in each hand
        Collections.sort(ranks1, Collections.reverseOrder());
        Collections.sort(ranks2, Collections.reverseOrder());

        // Check if both hands contain Ace (A) and treat Ace as both high and low
        boolean hasAce1 = ranks1.contains(14); // Ace represented as 14
        boolean hasAce2 = ranks2.contains(14); // Ace represented as 14

        // Handle Ace (A) as the lowest card (value 1) for straights like A, 2, 3, 4, 5
        if (hasAce1) {
            ranks1.remove(Integer.valueOf(14)); // Remove high Ace (14)
            ranks1.add(1); // Add low Ace (1) for comparison
            Collections.sort(ranks1, Collections.reverseOrder()); // Re-sort after modification
        }

        if (hasAce2) {
            ranks2.remove(Integer.valueOf(14)); // Remove high Ace (14)
            ranks2.add(1); // Add low Ace (1) for comparison
            Collections.sort(ranks2, Collections.reverseOrder()); // Re-sort after modification
        }

        // Compare ranks after handling Ace and sorting
        for (int i = 0; i < ranks1.size(); i++) {
            int rank1 = ranks1.get(i);
            int rank2 = ranks2.get(i);

            // Compare ranks at the current position
            if (rank2 > rank1) {
                return 0; // ranks2 wins
            } else if (rank1 > rank2) {
                return 1; // ranks1 wins
            }
        }

        // If all ranks are equal after comparison, this is also not important due to the fact that I know that there is draw hands.
        return 0;
    }


    //Fill remaining cards for pairs.
    private static void fillRemainingCards(List<Integer> result, List<Integer> ranks, List<Integer> identifiedCards, int cardsToAdd) {
        List<Integer> remainingCards = new ArrayList<>();
        for (int rank : ranks) {
            if (!identifiedCards.contains(rank)) {
                remainingCards.add(rank);
            }
        }
        //sort cards high to low (descending order) for comparison
        remainingCards.sort(Comparator.reverseOrder());

        // Determine how many cards can be added based on the available remaining cards
        int cardsToFill = Math.min(cardsToAdd, remainingCards.size());

        // Fill the remaining cards in the result list
        for (int i = 0; i < cardsToFill; i++) {
            result.add(remainingCards.get(i));
        }


    }

    // This method is used to convert rank character to rank value for cards like (T,J,Q,K,A)
    private static int getRankValue(char rankChar) {
        switch (rankChar) {
            case '2':
                return 2;
            case '3':
                return 3;
            case '4':
                return 4;
            case '5':
                return 5;
            case '6':
                return 6;
            case '7':
                return 7;
            case '8':
                return 8;
            case '9':
                return 9;
            case 'T':
                return 10;
            case 'J':
                return 11;
            case 'Q':
                return 12;
            case 'K':
                return 13;
            case 'A':
                return 14; // Ace can be high (value 14) or low (value 1), depending on the context
            default:
                return -1; // Invalid rank (this is not important due to the fact that we know for sure that cannot be illegal cards
        }
    }


    // Method check if the ranks form a straight
    private static boolean isStraight(List<Integer> ranks) {
        Collections.sort(ranks);
        // Check for a standard high straight (2, 3, 4, 5, 6 up to 10, J, Q, K, A)
        boolean isStandardStraight = true;
        for (int i = 0; i < ranks.size() - 1; i++) {
            if (ranks.get(i + 1) != ranks.get(i) + 1) {
                isStandardStraight = false;
                break;
            }
        }

        Collections.sort(ranks, Collections.reverseOrder());

        // In here we check for a special Ace-low straight (A, 5, 4, 3, 2)
        boolean isAceLowStraight = false;
        if (ranks.contains(14) && ranks.contains(2) && ranks.contains(3) && ranks.contains(4) && ranks.contains(5)) {
            // Check if Ace (14) is at the beginning of the sequence because we used Collections.reverseOrder() a little before
            if (ranks.get(0) == 14 && ranks.get(1) == 5 && ranks.get(2) == 4 && ranks.get(3) == 3 && ranks.get(4) == 2) {
                isAceLowStraight = true;
            }
        }
        // I return true if either standard high straight or Ace-low straight if formed
        return isStandardStraight || isAceLowStraight;
    }


    //This method gets the last card in a four of a kind hand type.
    private static int getLastCardNotInFourOfAKind(List<Integer> ranks, int fourKindRank) {
        for (int rank : ranks) {
            if (rank != fourKindRank) {
                return rank;
            }
        }
        return -1; // Default return if no valid card is found but not important because there is no invalid card.
    }
}


