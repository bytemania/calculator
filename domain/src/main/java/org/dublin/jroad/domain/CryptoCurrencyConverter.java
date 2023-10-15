package org.dublin.jroad.domain;


import java.math.BigDecimal;

record CryptoCurrencyConverter(CryptoCurrency from, CryptoCurrency to, BigDecimal rate) {

    public BigDecimal convert(BigDecimal amount) {
        return rate.multiply(amount);
    }
}
