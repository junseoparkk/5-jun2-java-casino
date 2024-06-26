package casino;

import casino.request.CasinoInputView;
import casino.response.CasinoResponse;
import casino.request.ExchangeInputView;
import casino.response.ExchangeResponse;
import casino.request.GameInputView;
import casino.response.GameResponse;
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
    public CasinoResponse casinoResponse() {
        return new CasinoResponse();
    }

    public CasinoInputView casinoInputView() {
        return new CasinoInputView(scanner());
    }

    public ExchangeResponse exchangeResponse() {
        return new ExchangeResponse();
    }

    public ExchangeInputView exchangeInputView() {
        return new ExchangeInputView(scanner());
    }

    public GameResponse gameResponse() {
        return new GameResponse();
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
