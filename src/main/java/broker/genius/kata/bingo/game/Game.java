package broker.genius.kata.bingo.game;

import lombok.Builder;
import lombok.Getter;

@Builder
public class Game {

    public @Getter
    NumberGenerator numberGenerator;
    public @Getter
    CardGenerator cardGenerator;
    public @Getter
    Player player;
}
