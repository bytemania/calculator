package org.dublin.jroad.domain;

import org.dublin.jroad.domain.util.CryptosParameterResolver;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(CryptosParameterResolver.class)
class CryptoCurrencyTest {

    private static CryptoCurrency btc;
    private static CryptoCurrency usdt;

    @BeforeAll
    static void beforeAll(Map<String, CryptoCurrency> cryptos) {
        btc = cryptos.get("BTC");
        usdt = cryptos.get("USDT");
    }

    @Test
    @DisplayName("A CryptoCurrency must have a symbol")
    void cryptoCurrencyMustHaveSymbol() {
        assertThat(btc.symbol()).isEqualTo("BTC");
    }

    @Test
    @DisplayName("A CryptoCurrency must have a price")
    void cryptoCurrencyHasPrice() {
        assertThat(btc.priceUsd().doubleValue()).isEqualTo(29920.0121812065);
    }

    @Test
    @DisplayName("A CryptoCurrency must have marketCap")
    void cryptoCurrencyHasMarketCap() {
        assertThat(btc.marketCap().doubleValue()).isEqualTo(0.5133);
    }

    @Test
    @DisplayName("A cryptoCurrency must show if is stable or not")
    void cryptoCurrencyStable() {
        assertThat(btc.stable()).isFalse();
        assertThat(usdt.stable()).isTrue();
    }

}