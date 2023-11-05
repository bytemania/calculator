package org.dublin.jroad.domain;

import org.dublin.jroad.domain.util.CryptosParameterResolver;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(CryptosParameterResolver.class)
class AllocatorTest {
    private static final BigDecimal AMOUNT = BigDecimal.valueOf(1000);
    private static final BigDecimal MIN_TO_INVEST = BigDecimal.valueOf(25);

    private static final Allocator allocator = new Allocator();

    @Test
    @DisplayName("Allocator should skip all the stable coins")
    void shouldSkipTheStableCoins(Map<String, CryptoCurrency> cryptos) {
        //given
        Set<CryptoCurrency> currencies = new TreeSet<>(cryptos.values());
        //when
        Set<CryptoCurrencyToInvest> result = allocator.allocate(currencies, AMOUNT, null, null, MIN_TO_INVEST);
        //then
        assertThat(result)
                .isNotNull()
                .hasSize(10)
                .allSatisfy(cryptoCurrencyToInvest -> {
                    Set<CryptoCurrency> stables = cryptos.values().stream()
                            .filter(CryptoCurrency::stable)
                            .collect(Collectors.toSet());
                    assertThat(cryptoCurrencyToInvest.cryptoName()).isNotIn(stables);
                });
    }

}