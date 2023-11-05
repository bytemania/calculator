package org.dublin.jroad.service;

import org.dublin.jroad.domain.Allocator;
import org.dublin.jroad.domain.CryptoCurrency;
import org.dublin.jroad.domain.CryptoCurrencyToInvest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class AllocatorServiceTest {
    private final static Set<CryptoCurrency> CRYPTOS = Set.of(new CryptoCurrency("BTC", BigDecimal.TEN, BigDecimal.ONE, false));
    private final static BigDecimal AMOUNT = BigDecimal.valueOf(1000L);
    private final static CryptoCurrency STABLE_COIN = new CryptoCurrency("STABLE", BigDecimal.ONE, BigDecimal.ONE, true);
    private final static BigDecimal PERCENTAGE_TO_ALLOCATE_STABLE = BigDecimal.TEN;
    private final static BigDecimal MIN_TO_INVEST = BigDecimal.valueOf(25);

    @Mock
    private Allocator allocator;

    @InjectMocks
    private AllocatorService service;

    @Test
    @DisplayName("AllocatorService should call Allocator to do the allocation")
    void shouldCallAllocator() {
        //given
        Set<CryptoCurrencyToInvest> cryptoCurrencyToInvests = Set.of(
                new CryptoCurrencyToInvest("BTC", AMOUNT, false));

        given(allocator.allocate(CRYPTOS, AMOUNT, STABLE_COIN, PERCENTAGE_TO_ALLOCATE_STABLE, MIN_TO_INVEST))
                .willReturn(cryptoCurrencyToInvests);

        //when
        Set<CryptoCurrencyToInvest> result = service.allocate(CRYPTOS, AMOUNT, STABLE_COIN,
                PERCENTAGE_TO_ALLOCATE_STABLE, MIN_TO_INVEST);

        //then
        assertThat(result).isSameAs(cryptoCurrencyToInvests);
        then(allocator)
                .should(times(1))
                .allocate(CRYPTOS, AMOUNT, STABLE_COIN, PERCENTAGE_TO_ALLOCATE_STABLE, MIN_TO_INVEST);
        verifyNoMoreInteractions(allocator);
    }

    @Test
    @DisplayName("AllocatorService should handle allocator Errors")
    void shouldHandleAllocatorErrors() {
        //given
        String errorMessage = "Unexpected Error allocation money";
        given(allocator.allocate(CRYPTOS, AMOUNT, STABLE_COIN, PERCENTAGE_TO_ALLOCATE_STABLE, MIN_TO_INVEST))
                .willThrow(new RuntimeException(errorMessage));

        //when
        //then

        assertThatThrownBy(() -> service.allocate(CRYPTOS, AMOUNT, STABLE_COIN, PERCENTAGE_TO_ALLOCATE_STABLE, MIN_TO_INVEST))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining(errorMessage);

        then(allocator)
                .should(times(1))
                .allocate(CRYPTOS, AMOUNT, STABLE_COIN, PERCENTAGE_TO_ALLOCATE_STABLE, MIN_TO_INVEST);
        verifyNoMoreInteractions(allocator);
    }
}