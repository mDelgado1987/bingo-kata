package broker.genius.kata.bingo.stepsdefinitions;

import broker.genius.kata.bingo.caller.NumberGeneratorImpl;
import broker.genius.kata.bingo.card.CardGeneratorImpl;
import broker.genius.kata.bingo.game.Game;
import broker.genius.kata.bingo.player.PlayerImpl;
import cucumber.api.java.Before;
import cucumber.api.java8.En;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class BingoGame implements En {

    private Game game;
    private Object[][] winnerCard;
    private Boolean playerWins;


    @Before
    public void beforeScenario() {
        game = Game.builder().cardGenerator(new CardGeneratorImpl()).player(new PlayerImpl()).numberGenerator(new NumberGeneratorImpl()).build();
        game.getPlayer().setBingoCard(game.getCardGenerator().generatePlayCard());
        winnerCard = game.getCardGenerator().getWinnerCard();
    }

    public BingoGame() {
        Given("^a player calls Bingo (after|before) all numbers on their card have been called$", (String whenCallBingo) -> {
            if ("after".equalsIgnoreCase(whenCallBingo))
                while (!game.getPlayer().isMyBingoCardWinner())
                    game.getPlayer().markCardCell(game.getNumberGenerator().getCardNumber().generate());
            else {
                int cardNumberGeneratorCalls = 0;
                while (!game.getPlayer().isMyBingoCardWinner() && cardNumberGeneratorCalls < 40) {
                    game.getPlayer().markCardCell(game.getNumberGenerator().getCardNumber().generate());
                    cardNumberGeneratorCalls++;
                }
            }
        });

        When("^I check the card$", () -> playerWins = Arrays.deepEquals(game.getPlayer().getBingoCard(), winnerCard));

        Then("^the player (is|is not) the winner$", (String assumption) -> {
            if ("is".equalsIgnoreCase(assumption)) {
                assertThat(playerWins, equalTo(true));
            }else {
                assertThat(playerWins, equalTo(false));
            }
        });
    }
}
