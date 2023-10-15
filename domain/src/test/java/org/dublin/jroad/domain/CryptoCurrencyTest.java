package org.dublin.jroad.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.dublin.jroad.domain.Util.BITCOIN;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CryptoCurrencyTest {

    @Test
    @DisplayName("A CryptoCurrency must be identified a price")
    void cryptoCurrencyMustHaveAPrice() {
        assertEquals("BTC", BITCOIN.name());
    }

    @Test
    @DisplayName("A CryptoCurrency must have a name")
    void cryptoCurrencyHasName() {
        assertEquals(BigDecimal.valueOf(26926.99D), BITCOIN.price());
    }

    @Test
    @DisplayName("A CryptoCurrency must have marketCap")
    void cryptoCurrencyHasMarketCap() {
        assertEquals(BigDecimal.valueOf(0.4D), BITCOIN.marketCap());
    }

}