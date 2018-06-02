package broker.genius.kata.bingo.caller;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NumberGeneratorImpl implements broker.genius.kata.bingo.game.NumberGenerator {

    private int MIN = 1;
    private int BOUND = 75;

    public NumberGeneratorImpl(int min, int bound) {
        this.MIN = min;
        this.BOUND = bound;
    }

    public NumberGeneratorImpl() {
    }

    @FunctionalInterface
    public interface RandomCardNumber {
        public int generate();
    }

    private List<Integer> usedNumbers = new ArrayList<>();
    public @Getter
    RandomCardNumber cardNumber = () -> {
        Random random = new Random();
        int r = random.nextInt((BOUND - MIN) + 1) + MIN;
        while (usedNumbers.contains(r) && usedNumbers.size() <= BOUND)
            r = random.nextInt((BOUND - MIN) + 1) + MIN;
        usedNumbers.add(r);
        return r;
    };
}
