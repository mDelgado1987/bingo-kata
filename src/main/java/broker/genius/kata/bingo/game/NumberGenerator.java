package broker.genius.kata.bingo.game;

        import broker.genius.kata.bingo.caller.NumberGeneratorImpl.RandomCardNumber;

public interface NumberGenerator {
    RandomCardNumber getCardNumber();
}
