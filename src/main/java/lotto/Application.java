package lotto;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Calculator;
import lotto.domain.LotteryDrawMachine;
import lotto.domain.Lotto;
import lotto.util.InputValidator;
import lotto.view.Output;

public class Application {

    public static void main(String[] args) {
        LotteryDrawMachine lotteryDrawMachine = new LotteryDrawMachine();

        Output.enterPurchaseCost();
        int purchaseCost = Integer.parseInt(Console.readLine());
        if (!InputValidator.checkPurchaseCost(purchaseCost)) {
            throw new IllegalArgumentException("[ERROR] 잘못된 구매 금액입니다.");
        }

        int purchaseCount = purchaseCost / 1000;
        Output.purchaseCountNotification(purchaseCount);

        List<List<Integer>> groupOfUserLotteryNumbers = new ArrayList<>();

        for (int count = 0; count < purchaseCount; count++) {
            List<Integer> lotteryNumbers = lotteryDrawMachine.drawLotteryNumbers();
            Lotto lotto = new Lotto(lotteryNumbers);
            groupOfUserLotteryNumbers.add(lotteryNumbers);
        }

        Output.enterWinningNumbers();
        List<String> list = Arrays.asList(Console.readLine().split(","));
        List<Integer> winningNumbers = list.stream()
                .map(winningNumber -> Integer.parseInt(winningNumber))
                .collect(Collectors.toList());
        if (!InputValidator.checkWinningNumbers(winningNumbers)) {
            throw new IllegalArgumentException("[ERROR] 잘못된 당첨 번호입니다.");
        }

        Output.enterBonusNumber();
        int bonusNumber = Integer.parseInt(Console.readLine());

        Output.winningStatistics();
        Calculator calculator = new Calculator();
        List<Integer> matchResults = calculator.calculateMatchResults(groupOfUserLotteryNumbers, winningNumbers,
                bonusNumber);
        Output.printWinningStatistics(matchResults);

        int profit = calculator.calculateProfit(matchResults);
        double earningsRate = calculator.calculateEarningsRate(purchaseCost, profit);
        Output.earningsRateNotification(earningsRate);
    }
}
