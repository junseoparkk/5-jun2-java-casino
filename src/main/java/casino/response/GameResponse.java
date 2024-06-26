package casino.response;

import static casino.domain.game.roulette.RouletteBetType.*;
import static casino.response.GameAsciiMessage.BACCARAT_GREET;
import static casino.response.GameAsciiMessage.BLACKJACK_GREET;
import static casino.response.GameAsciiMessage.ROULETTE_GREET;
import static casino.response.GameAsciiMessage.SLOT_MACHINE_GREET;
import static casino.response.GameAsciiMessage.STRAIGHT_DOWN;
import static casino.response.GameAsciiMessage.STRAIGHT_UP;

import casino.domain.game.Card;
import casino.domain.game.roulette.RouletteBetType;
import casino.domain.game.roulette.RouletteColorType;
import casino.domain.game.slotmachine.SlotMachineResult;
import casino.domain.participant.Participant;
import casino.domain.type.ChipType;
import casino.domain.type.GameType;
import casino.dto.RouletteGameResultDto;
import casino.dto.SlotMachineGameResultDto;
import java.awt.Toolkit;
import java.util.List;
import java.util.Map;

public class GameResponse {
    public void printGameOption() {
        System.out.println("!! 카지노 게임장에 입장하셨습니다 !!");
        System.out.println("======================================================");
        System.out.println("|                    게임장 메인 화면                    |");
        System.out.println("======================================================");
        System.out.println("1. 슬롯 머신 (Slot Machine)");
        System.out.println("2. 룰렛 (Roulette)");
        System.out.println("3. 블랙잭 (Blackjack)");
        System.out.println("Q. 나가기");
    }

    public void printGameGreet(GameType type) {
        if (type == GameType.SLOT_MACHINE) {
            System.out.println(SLOT_MACHINE_GREET.getMessage());
        } else if (type == GameType.ROULETTE) {
            System.out.println(ROULETTE_GREET.getMessage());
        } else if (type == GameType.BLACKJACK) {
            System.out.println(BLACKJACK_GREET.getMessage());
        } else if (type == GameType.BACCARAT) {
            System.out.println(BACCARAT_GREET.getMessage());
        }
    }

    public void printSlotMachineResult(SlotMachineGameResultDto dto) {
        if (dto.result() == SlotMachineResult.JACKPOT_SEVEN) {
            System.out.println(GameAsciiMessage.JACKPOT_SEVEN.getMessage());
        } else if (dto.result() == SlotMachineResult.JACKPOT) {
            System.out.println(GameAsciiMessage.JACKPOT.getMessage());
        } else if (dto.result() == SlotMachineResult.STRAIGHT_UP) {
            System.out.println(STRAIGHT_UP.getMessage());
        } else if (dto.result() == SlotMachineResult.STRAIGHT_DOWN) {
            System.out.println(STRAIGHT_DOWN.getMessage());
        }
        printNumbers(dto.numbers());
        System.out.println("[ 당첨금 ] : " + String.format("%,d", dto.result().getWinningAmount()) + " 원");
    }

    public void printPlayerChips(Map<ChipType, Integer> chips) {
        System.out.println("============================== 플레이어 보유 칩 ==============================");
        for (Map.Entry<ChipType, Integer> entry : chips.entrySet()) {
            ChipType type = entry.getKey();
            int count = entry.getValue();
            System.out.print("[" + type.name() + "]:" + count + " ");
        }
        printBlankLine();
    }

    public void printRouletteBetType() {
        List<RouletteBetType> types = List.of(values());
        System.out.println("==========================================================================");
        for (int i = 0; i < types.size() - 1; i++) {
            int typeNumber = types.get(i).getTypeNumber();
            String explanation = types.get(i).getExplanation();
            int dividendMultiple = types.get(i).getDividendMultiple();
            System.out.println("[" + typeNumber + "] " + explanation + " : [배당금] 베팅 금액의 " + dividendMultiple + " 배");
        }
        System.out.println("==========================================================================");
    }

    public void printRouletteBetOptions(RouletteBetType betType) {
        if (betType == COLUMN_BET) {
            System.out.println("[1] 1열 숫자에 베팅 (1, 4, 7, 19, 13, 16, 19, 22, 25, 28, 31, 34)");
            System.out.println("[2] 2열 숫자에 베팅 (2, 5, 8, 11, 14, 17, 20, 23, 26, 29, 32, 35)");
            System.out.println("[3] 3열 숫자에 베팅 (3, 6, 9, 12, 15, 18, 21, 24, 27, 30, 33, 36)");
        } else if (betType == DOZEN_BET) {
            System.out.println("[1] '1st 12' 에 베팅 (1 ~ 12 사이의 번호)");
            System.out.println("[2] '2nd 12' 에 베팅 (13 ~ 24 사이의 번호)");
            System.out.println("[3] '3rd 12' 에 베팅 (25 ~ 26 사이의 번호)");
        } else if (betType == HIGH_LOW_NUMBER_BET) {
            System.out.println("[1] Low Number 에 베팅 (1 ~ 18 사이의 번호)");
            System.out.println("[2] High Number 에 베팅 (19 ~ 36 사이의 번호)");
        } else if (betType == EVEN_ODD_NUMBER_BET) {
            System.out.println("[1] Even Number 에 베팅 (0을 제외한 모든 짝수 번호)");
            System.out.println("[2] Odd Number 에 베팅 (0을 제외한 모든 홀수 번호)");
        } else {
            System.out.println("[1] Black Number 에 베팅 (0을 제외한 모든 검정색 번호)");
            System.out.println("[2] Red Number 에 베팅 (0을 제외한 모든 빨간색 번호)");
        }
    }

    public void printRouletteGameResult(RouletteGameResultDto dto) {
        String numberColor = RouletteColorType.findColorNameByNumber(dto.winningNumber());
        RouletteBetType betType = dto.betType();
        System.out.println("[당첨 번호] : " + dto.winningNumber() + " [" + numberColor + "]");
        if (dto.totalWinningAmount() != 0) {
            System.out.println("[당첨 환산 금액] : " + String.format("%,d", dto.totalWinningAmount()) + " 원 (x" + betType.getDividendMultiple() + "배)");
        } else {
            System.out.println("[당첨 환산 금액] : 0 원");
        }
    }

    public void printCards(Participant participant) {
        String top = "┌────┐";
        String middle = "| %s |";
        String bottom = "└────┘";
        List<Card> cards = participant.getCards();



        if (participant.isPlayer()) {
            System.out.println("[ Player " + participant.getName() + " 초기 카드 ]");
        } else {
            printBlankLine();
            System.out.println("[ Dealer 초기 카드 ]");
            middle = "| ## |";
        }

        for (int i = 0; i < cards.size(); i++) {
            System.out.print(top);
        }
        printBlankLine();
        for (Card card : cards) {
            System.out.printf(middle, card.toString());
        }
        printBlankLine();
        for (int i = 0; i < cards.size(); i++) {
            System.out.print(bottom);
        }
        printBlankLine();
    }

    public void printBlackjackValue(int playerResult, int dealerResult) {
        printBlankLine();
        System.out.println("플레이어 카드 합 : " + playerResult);
        System.out.println("딜러 카드 합 : " + dealerResult);
        printBlankLine();
    }

    public void printBlankLine() {
        System.out.println();
    }

    public void printException(String exceptionMessage) {
        System.out.println(exceptionMessage);
    }

    private void printNumbers(int[] numbers) {
        Thread thread = new Thread(new BeepTask(numbers.length));
        StringBuilder topRow = new StringBuilder();
        StringBuilder middleRow = new StringBuilder();
        StringBuilder bottomRow = new StringBuilder();

        thread.start();

        try {
            for (int number : numbers) {
                final String top = "┌───┐";
                final String middle = "| " + number + " |";
                final String bottom = "└───┘";

                // 각 숫자 추가 시마다 각 행에 해당 부분을 추가
                topRow.append(top).append(" ");
                middleRow.append(middle).append(" ");
                bottomRow.append(bottom).append(" ");

                // 전체 행을 다시 출력
                System.out.println("\r\033[K" + topRow.toString());
                System.out.println(middleRow.toString());
                System.out.println(bottomRow.toString());

                System.out.print("\033[F\033[F\033[F");

                Thread.sleep(500); // 1초 동안 대기
            }
        } catch (InterruptedException e) {
            System.err.println("Thread was interrupted.");
        }
    }

    static class BeepTask implements Runnable {
        private final int count;

        public BeepTask(int count) {
            this.count = count;
        }

        @Override
        public void run() {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            try {
                for (int i = 0; i < count; i++) {
                    toolkit.beep();
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
