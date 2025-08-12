package validation.rules;


import validation.ValidationRule;

import java.util.function.Predicate;

public class CustomRule<T> implements ValidationRule<T> {
    private final Predicate<T> condition;
    private final String errorMessage;

    public CustomRule(Predicate<T> condition, String errorMessage) {
        this.condition = condition;
        this.errorMessage = errorMessage;
    }

    /**
     * If conditional return false => Throw error messages
     * */
    public static <T> CustomRule<T> testIfNot(Predicate<T> condition, String errorMessage) {
        return new CustomRule<>(condition, errorMessage);
    }

    /**
     * If conditional return true => Throw error messages
     * */
    public static <T> CustomRule<T> testIf(Predicate<T> condition, String errorMessage) {
        return new CustomRule<>(Predicate.not(condition), errorMessage);
    }

    @Override
    public boolean test(T entity) {
        return condition.test(entity);
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}