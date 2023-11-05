package org.dublin.jroad.domain;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Objects;

public record CryptoCurrency(String symbol, BigDecimal priceUsd, BigDecimal marketCap, boolean stable)
        implements Comparable<CryptoCurrency> {

    public static final Comparator<CryptoCurrency> COMPARE_BY_MARKET_CAP_REVERSED = Comparator
            .comparing(CryptoCurrency::marketCap).reversed()
            .thenComparing(CryptoCurrency::symbol);
    @Override
    public int hashCode() {
        return Objects.hashCode(symbol);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (this.getClass() != obj.getClass()) {
            return false;
        }

        return Objects.equals(symbol, ((CryptoCurrency) obj).symbol);
    }

    @Override
    public int compareTo(CryptoCurrency that) {
        return Comparator.comparing(CryptoCurrency::symbol).compare(this, that);
    }
}




