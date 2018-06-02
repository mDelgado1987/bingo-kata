package broker.genius.kata.bingo.card;


import lombok.Getter;

public class CardColumnImpl {
    @Getter
    String column;
    @Getter
    int lowerBound, upperBound;
}
