package casino.domain.game.roulette;

import casino.domain.game.Game;
import casino.domain.participant.Player;
import casino.domain.type.GameStatus;
import casino.domain.type.GameType;

public class RouletteGame extends Game {
    private static final int MIN_NUMBER = 0;
    private static final int MAX_NUMBER = 36;

    public RouletteGame(GameType gameType, Player player, GameStatus status) {
        super(gameType, player, status);
    }
}
