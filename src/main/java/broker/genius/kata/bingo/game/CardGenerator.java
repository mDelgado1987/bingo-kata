package broker.genius.kata.bingo.game;

public interface CardGenerator {
    Object[][] generatePlayCard();

    Object[][] getWinnerCard();
}
