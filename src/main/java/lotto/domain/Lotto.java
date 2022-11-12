package lotto.domain;

import static lotto.constant.SystemValue.LOTTERY_NUMBERS_SIZE;

import java.util.List;

public class Lotto {

    private static final String USER_LOTTERY_NUMBERS_SIZE_ERROR = "로또 번호는 6자리여야 합니다";
    private static final String USER_LOTTERY_NUMBERS_CONFLICT_ERROR = "로또 번호에 중복이 포함될 수 없습니다";
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        checkSizeValid(numbers);
        checkConflictValid(numbers);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public static void checkSizeValid(List<Integer> numbers) {
        boolean isSize = numbers.size() == LOTTERY_NUMBERS_SIZE;
        if (!isSize) {
            throw new IllegalArgumentException(USER_LOTTERY_NUMBERS_SIZE_ERROR);
        }
    }

    public static void checkConflictValid(List<Integer> numbers) {
        boolean isConflict = numbers.stream()
                .distinct()
                .count() == LOTTERY_NUMBERS_SIZE;
        if (!isConflict) {
            throw new IllegalArgumentException(USER_LOTTERY_NUMBERS_CONFLICT_ERROR);
        }
    }
}
