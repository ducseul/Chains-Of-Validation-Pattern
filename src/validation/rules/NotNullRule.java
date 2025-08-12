    package validation.rules;

    import validation.ValidationRule;

    import java.util.function.Function;

    public class NotNullRule<T> implements ValidationRule<T> {
        private final Function<T, Object> getter;
        private final String errorMessage;

        private NotNullRule(Function<T, Object> getter, String errorMessage) {
            this.getter = getter;
            this.errorMessage = errorMessage;
        }

        public static <T> NotNullRule<T> of(Function<T, Object> getter, String errorMessage) {
            return new NotNullRule<>(getter, errorMessage);
        }

        @Override
        public boolean test(T entity) {
            return getter.apply(entity) != null;
        }

        @Override
        public String getErrorMessage() {
            return errorMessage;
        }
    }