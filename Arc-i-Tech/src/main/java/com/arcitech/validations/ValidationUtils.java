/**
 * Copyright (c) 2025, Arc-i-Tech. All rights reserved.
 * visit www.arc-i-tech.in
 *
 */
package com.arcitech.validations;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
/**
 * @author ajdes
 * 
 */



public class ValidationUtils {

    private final String value;
    private final List<String> errors = new ArrayList<>();
    private final List<Predicate<String>> rules = new ArrayList<>();

    private ValidationUtils(String value) {
        this.value = value;
    }

    // Factory method
    public static ValidationUtils of(String value) {
        return new ValidationUtils(value);
    }

    // ---- Common Validations ----

    public ValidationUtils isNotBlank() {
        rules.add(v -> v != null && !v.trim().isEmpty());
        return this;
    }

    public ValidationUtils onlyAlphabets() {
        rules.add(v -> v != null && v.matches("^[A-Za-z]+$"));
        return this;
    }

    public ValidationUtils onlyAlphabetsWithSpaces() {
        rules.add(v -> v != null && v.matches("^[A-Za-z\\s]+$"));
        return this;
    }

    public ValidationUtils onlyNumeric() {
        rules.add(v -> v != null && v.matches("^\\d+$"));
        return this;
    }

    public ValidationUtils minLength(int length) {
        rules.add(v -> v != null && v.length() >= length);
        return this;
    }

    public ValidationUtils maxLength(int length) {
        rules.add(v -> v != null && v.length() <= length);
        return this;
    }

    public ValidationUtils containsNumber() {
        rules.add(v -> v != null && v.matches(".*\\d.*"));
        return this;
    }

    public ValidationUtils containsAlphabet() {
        rules.add(v -> v != null && v.matches(".*[A-Za-z].*"));
        return this;
    }

    public ValidationUtils matchesRegex(String regex) {
        Objects.requireNonNull(regex, "Regex cannot be null");
        rules.add(v -> v != null && v.matches(regex));
        return this;
    }


    public boolean validate() {
        return rules.stream().allMatch(r -> r.test(value));
    }

    public List<String> validateWithErrors() {

    	List<String> failed = new ArrayList<>();
        if (value == null) {
            failed.add("Value cannot be null");
            return failed;
        }

        for (Predicate<String> rule : rules) {
            if (!rule.test(value)) {
                failed.add("Validation failed for: " + rule.toString());
            }
        }
        return failed;
    }
}
