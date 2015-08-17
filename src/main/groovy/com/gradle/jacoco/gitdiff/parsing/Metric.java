package com.gradle.jacoco.gitdiff.parsing;

import static java.lang.String.format;

public class Metric {
    private final int covered;
    private final int missed;

    public Metric(int covered, int missed) {
        this.covered = covered;
        this.missed = missed;
    }

    public Metric add(Metric other) {
        return new Metric(covered + other.covered, missed + other.missed);
    }

    public int covered() {
        return covered;
    }

    public int missed() {
        return missed;
    }

    public int total() {
        return covered + missed;
    }

    public int percent() {
        if (total() == 0) return 100;

        return 100 * covered / total();
    }

    @Override public String toString() {
        return format("Metric(covered = %d, missed = %d)", covered, missed);
    }
}