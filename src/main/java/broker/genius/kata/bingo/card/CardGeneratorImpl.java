package broker.genius.kata.bingo.card;

import broker.genius.kata.bingo.caller.NumberGeneratorImpl;
import broker.genius.kata.bingo.game.NumberGenerator;
import lombok.Getter;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class CardGeneratorImpl implements broker.genius.kata.bingo.game.CardGenerator {
    private static @Getter
    Object[][] bingoCard, winnerCard;
    private static final int BINGO_CARD_MAX_ROW = 5;
    private static final int BINGO_CARD_MAX_COLUMN = 5;

    public CardGeneratorImpl() {
        bingoCard = new Object[BINGO_CARD_MAX_ROW][BINGO_CARD_MAX_COLUMN];
    }

    public Object[][] getWinnerCard() {
        winnerCard = new Object[BINGO_CARD_MAX_ROW][BINGO_CARD_MAX_COLUMN];
        for (int i = 0; i < winnerCard.length; i++) {
            for (int j = 0; j < winnerCard[i].length; j++) {
                winnerCard[i][j] = 'X';
            }
        }
        return winnerCard;
    }

    private void generateRow(int rowChar, int min, int bound) {
        NumberGenerator numberGenerator = new NumberGeneratorImpl(min, bound);
        AtomicInteger column = new AtomicInteger();
        Arrays.stream(bingoCard[rowChar]).forEach(cell -> {
            if (rowChar == 2 && column.get() == 2)
                cell = 'X';
            else if (cell == null)
                cell = numberGenerator.getCardNumber().generate();
            bingoCard[rowChar][column.get()] = cell;
            column.getAndIncrement();
        });
    }

    private void generateRowB() {
        generateRow(0, 1, 15);
    }

    private void generateRowI() {
        generateRow(1, 16, 30);
    }

    private void generateRowN() {
        generateRow(2, 31, 45);
    }

    private void generateRowG() {
        generateRow(3, 46, 60);
    }

    private void generateRowO() {
        generateRow(4, 61, 75);
    }

    public Object[][] generatePlayCard() {
        generateRowB();
        generateRowI();
        generateRowN();
        generateRowG();
        generateRowO();
        return bingoCard;
    }
}
