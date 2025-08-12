package validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Validation<T> {
    private final T entity;
    private final List<ValidationRule<T>> rules = new ArrayList<>();

    public Validation(T entity) {
        this.entity = entity;
    }

    public Validation<T> chains(ValidationRule<T> rule) {
        rules.add(rule);
        return this;
    }

    @SafeVarargs
    public final Validation<T> chains(ValidationRule<T>... rules) {
        Collections.addAll(this.rules, rules);
        return this;
    }

    public void validate() {
        for (ValidationRule<T> rule : rules) {
            if (!rule.test(entity)) {
                throw new ValidationException(rule.getErrorMessage());
            }
        }
    }

    public static <T> Validation<T> of(T entity) {
        return new Validation<>(entity);
    }
}