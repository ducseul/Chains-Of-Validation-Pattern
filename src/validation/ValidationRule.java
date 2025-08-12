package validation;

public interface ValidationRule<T> {
    boolean test(T entity);
    String getErrorMessage();
}