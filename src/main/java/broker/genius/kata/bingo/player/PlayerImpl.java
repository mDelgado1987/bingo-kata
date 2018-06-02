package broker.genius.kata.bingo.player;

import broker.genius.kata.bingo.card.CardGeneratorImpl;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

public class PlayerImpl implements broker.genius.kata.bingo.game.Player {
    @Setter
    @Getter
    Object[][] bingoCard;

    @Override
    public Boolean isMyBingoCardWinner() {
        return Arrays.deepEquals(bingoCard, new CardGeneratorImpl().getWinnerCard());
    }

    @Override
    public void markCardCell(Object x) {
        Arrays.stream(bingoCard).forEach(row -> {
            if (Arrays.asList(row).contains(x))
                Arrays.asList(row).set(Arrays.asList(row).indexOf(x), 'X');
        });
    }



}
