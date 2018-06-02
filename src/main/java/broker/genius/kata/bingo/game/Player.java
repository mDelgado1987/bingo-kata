package broker.genius.kata.bingo.game;

public interface Player {
    void markCardCell(Object x);

    Object[][] getBingoCard();

    Boolean isMyBingoCardWinner();

    void setBingoCard(Object[][] bingoCard);
}
