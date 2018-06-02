package broker.genius.kata.bingo.stepsdefinitions;

import broker.genius.kata.bingo.caller.NumberGeneratorImpl;
import broker.genius.kata.bingo.game.Game;
import cucumber.api.java8.En;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.AllOf.allOf;

public class BingoNumbers implements En {

    private int number;
    private List<Integer> numbers = new ArrayList<>();
    @Setter
    Game game;

    public BingoNumbers() {
        Given("^I have a Bingo caller$", () -> {
            game = Game.builder().numberGenerator(new NumberGeneratorImpl()).build();
        });
        When("^I call a number$", () -> {
            number = game.getNumberGenerator().getCardNumber().generate();
        });
        Then("^the number is between (\\d+) and (\\d+) inclusive$", (Integer min, Integer max) -> {
            assertThat("The number is between (\\d+) and (\\d+) inclusive", number, allOf(greaterThan(min), lessThan(max)));
        });

        When("^I call a number (\\d+) times$", (Integer times) -> {
            for (int i = 0; i < times; i++)
                numbers.add(game.getNumberGenerator().getCardNumber().generate());
        });
        Then("^all numbers between (\\d+) and (\\d+) are present$", (Integer min, Integer max) -> {
            numbers.stream().sorted().forEach(number -> assertThat(String.format("The number is between (%d) and (%d) inclusive",min,max), number, allOf(greaterThanOrEqualTo(min), lessThanOrEqualTo(max))));
        });

        And("^no number has been called more than once$", () -> {
            assertThat(numbers.stream().distinct().count(), equalTo(75L));
        })
        ;
    }
}
