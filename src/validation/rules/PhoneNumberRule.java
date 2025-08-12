package validation.rules;

import validation.ValidationRule;

import java.util.function.Function;

public class PhoneNumberRule<T> implements ValidationRule<T> {
    private final Function<T, String> getter;
    private static final String regex = "^(\\+84|0)(3[2-9]|5[6|8|9]|7[0|6-9]|8[1-5]|9[0-9])\\d{7}$";

    public PhoneNumberRule(Function<T, String> getter) {
        this.getter = getter;
    }

    public static <T> PhoneNumberRule<T> of (Function<T, String> getter) {
        return new PhoneNumberRule<>(getter);
    }

    @Override
    public boolean test(T entity) {
        String phone = getter.apply(entity);
        return phone != null && phone.matches(regex);
    }

    @Override
    public String getErrorMessage() {
        return "Invalid phone number.";
    }
}