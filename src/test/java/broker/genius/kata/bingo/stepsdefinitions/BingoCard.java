package broker.genius.kata.bingo.stepsdefinitions;

import broker.genius.kata.bingo.card.CardColumnImpl;
import broker.genius.kata.bingo.card.CardGeneratorImpl;
import broker.genius.kata.bingo.game.Game;
import cucumber.api.DataTable;
import cucumber.api.java8.En;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsEqual.equalTo;

public class BingoCard implements En {

    private Game game;
    private Object[][] bingoCard;

    public BingoCard() {
        Given("^I have a Bingo card generator$", () -> game = Game.builder().cardGenerator(new CardGeneratorImpl()).build());

        When("^I generate a Bingo card$", () -> bingoCard = game.getCardGenerator().generatePlayCard());

        Then("^the generated card has (\\d+) unique spaces$", (Integer spaces) -> {
            AtomicInteger matrixSize = new AtomicInteger();
            Arrays.asList(bingoCard).forEach(column -> matrixSize.addAndGet(Arrays.asList(column).size()));
            assertThat(matrixSize.get(), equalTo(spaces));
        });
        And("^the generated card has (\\d+) FREE space in the middle$", (Integer arg0) -> assertThat(bingoCard[2][2], equalTo('X')));

        And("^column only contains numbers between lowerBound and upperBound inclusive$", (DataTable dataTable) -> {
            List<CardColumnImpl> data = dataTable.asList(CardColumnImpl.class);
            data.forEach(d -> {
                switch (d.getColumn()) {
                    case "B":
                        Arrays.stream(bingoCard[0]).forEach(row -> assertThat((int) row, allOf(greaterThanOrEqualTo(d.getLowerBound()), lessThanOrEqualTo(d.getUpperBound()))));
                        break;
                    case "I":
                        Arrays.stream(bingoCard[1]).forEach(row -> assertThat((int) row, allOf(greaterThanOrEqualTo(d.getLowerBound()), lessThanOrEqualTo(d.getUpperBound()))));
                        break;
                    case "N":
                        Arrays.stream(bingoCard[2]).forEach(row -> {
                            if (row instanceof Integer)
                                assertThat((int) row, allOf(greaterThanOrEqualTo(d.getLowerBound()), lessThanOrEqualTo(d.getUpperBound())));
                        });
                        break;
                    case "G":
                        Arrays.stream(bingoCard[3]).forEach(row -> assertThat((int) row, allOf(greaterThanOrEqualTo(d.getLowerBound()), lessThanOrEqualTo(d.getUpperBound()))));
                        break;
                    case "O":
                        Arrays.stream(bingoCard[4]).forEach(row -> assertThat((int) row, allOf(greaterThanOrEqualTo(d.getLowerBound()), lessThanOrEqualTo(d.getUpperBound()))));
                        break;
                }
            });
        });
    }
}
