package com.gradle.jacoco.gitdiff.parsing;

public class Coverage {
    private final Metric instructions;
    private final Metric lines;
    private final Metric branches;
    private final Metric methods;
    private final Metric classes;

    private Coverage(Metric instructions, Metric lines, Metric branches, Metric methods, Metric classes) {
        this.instructions = instructions;
        this.lines = lines;
        this.branches = branches;
        this.methods = methods;
        this.classes = classes;
    }

    public static Coverage empty() {
        return Builder.get().build();
    }

    public Coverage add(Coverage other) {
        return new Coverage(
                instructions.add(other.instructions),
                lines.add(other.lines),
                branches.add(other.branches),
                methods.add(other.methods),
                classes.add(other.classes));
    }

    public Metric instructions() {
        return instructions;
    }

    public Metric lines() {
        return lines;
    }

    public Metric branches() {
        return branches;
    }

    public Metric methods() {
        return methods;
    }

    public Metric classes() {
        return classes;
    }

    @Override public String toString() {
        return String.format("Coverage(instructions = %s, lines = %s, branches = %s, methods = %s, classes = %s",
                instructions, lines, branches, methods, classes);
    }

    public static class Builder {
        private Metric instructions = new Metric(0, 0);
        private Metric lines = new Metric(0, 0);
        private Metric branches = new Metric(0, 0);
        private Metric methods = new Metric(0, 0);
        private Metric classes = new Metric(0, 0);

        public static Builder get() {
            return new Builder();
        }

        public Builder instructions(Metric metric) {
            instructions = metric;
            return this;
        }

        public Builder lines(Metric metric) {
            lines = metric;
            return this;
        }

        public Builder branches(Metric metric) {
            branches = metric;
            return this;
        }

        public Builder methods(Metric metric) {
            methods = metric;
            return this;
        }

        public Builder classes(Metric metric) {
            classes = metric;
            return this;
        }

        public Coverage build() {
            return new Coverage(instructions, lines, branches, methods, classes);
        }
    }
}