package org.euler;

import java.util.List;

public class HandEvaluationResult {
    private HandTypes handType;
    private List<Integer> ranks;

    public HandEvaluationResult(HandTypes handType, List<Integer> ranks) {
        this.handType = handType;
        this.ranks = ranks;
    }

    public HandTypes getHandType() {
        return handType;
    }

    public List<Integer> getRanks() {
        return ranks;
    }
}

