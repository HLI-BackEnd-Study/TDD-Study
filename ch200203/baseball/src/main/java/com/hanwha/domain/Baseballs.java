package com.hanwha.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Baseballs {

    private final List<Baseball> baseballs;

    private Baseballs(List<Baseball> baseballs) {
        this.baseballs = new ArrayList<>(baseballs);
    }

    public static Baseballs from(List<Baseball> baseballs) {
        return new Baseballs(baseballs);
    }

    public List<Baseball> getBaseballs() {
        return Collections.unmodifiableList(baseballs);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Baseballs baseballs1 = (Baseballs) o;
        return Objects.equals(baseballs, baseballs1.baseballs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseballs);
    }
}
