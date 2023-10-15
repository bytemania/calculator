package org.dublin.jroad.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.dublin.jroad.domain.Util.BITCOIN;
import static org.dublin.jroad.domain.Util.ETH;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CryptoCurrencyConverterTest {
    @Test
    @DisplayName("A crypto must be converted in one to another")
    void convertCrypto() {
        CryptoCurrencyConverter converter = new CryptoCurrencyConverter(BITCOIN, ETH, BigDecimal.valueOf(17.31D));
        assertEquals(1817.55D, converter.convert(BigDecimal.valueOf(105)).doubleValue());
    }
}