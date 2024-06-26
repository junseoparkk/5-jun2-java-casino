package casino.domain.game;

import casino.domain.participant.Player;
import casino.domain.type.GameStatus;
import casino.domain.type.GameType;

public class CardGame extends Game {
    public CardGame(GameType gameType, Player player, GameStatus status) {
        super(gameType, player, status);
    }
}
