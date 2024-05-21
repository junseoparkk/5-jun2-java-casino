package casino;

import casino.io.casino.CasinoInputView;
import casino.io.casino.CasinoOutputView;
import casino.io.exchange.ExchangeInputView;
import casino.io.exchange.ExchangeOutputView;
import casino.io.game.GameInputView;
import casino.io.game.GameOutputView;
import casino.repository.CasinoMainRepository;
import casino.repository.CasinoRepository;
import casino.request.ConsoleRequest;
import casino.request.Request;
import casino.service.casino.CasinoMainService;
import casino.service.casino.CasinoMainServiceImpl;
import casino.service.exchange.ExchangeService;
import casino.service.exchange.ExchangeServiceImpl;
import casino.service.game.GameService;
import casino.service.game.GameServiceImpl;
import java.util.Scanner;

public class CasinoConfig {
    /**
     * I/O Views, Request
     */
    public CasinoOutputView casinoOutputView() {
        return new CasinoOutputView();
    }

    public CasinoInputView casinoInputView() {
        return new CasinoInputView(scanner());
    }

    public ExchangeOutputView exchangeOutputView() {
        return new ExchangeOutputView();
    }

    public ExchangeInputView exchangeInputView() {
        return new ExchangeInputView(scanner());
    }

    public GameOutputView gameOutputView() {
        return new GameOutputView();
    }

    public GameInputView gameInputView() {
        return new GameInputView(scanner());
    }

    public Request request() {
        return new ConsoleRequest(casinoInputView(), exchangeInputView(), gameInputView());
    }

    /**
     *  Services & Repositories
     */
    public CasinoMainService casinoMainService() {
        return new CasinoMainServiceImpl(casinoRepository());
    }

    public CasinoRepository casinoRepository() {
        return CasinoMainRepository.getInstance();
    }

    public ExchangeService exchangeService() {
        return new ExchangeServiceImpl();
    }

    public GameService gameService() {
        return new GameServiceImpl();
    }

    private Scanner scanner() {
        return new Scanner(System.in);
    }
}
