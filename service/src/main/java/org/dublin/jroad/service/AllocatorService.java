package org.dublin.jroad.service;

import org.dublin.jroad.domain.Allocator;
import org.dublin.jroad.domain.CryptoCurrency;
import org.dublin.jroad.domain.CryptoCurrencyToInvest;

import java.math.BigDecimal;
import java.util.Set;

public class AllocatorService {

    private final Allocator allocator;

    public AllocatorService(Allocator allocator) {
        this.allocator = allocator;
    }

    Set<CryptoCurrencyToInvest> allocate(Set<CryptoCurrency> cryptos,
                                          BigDecimal amount,
                                          CryptoCurrency stableCoin,
                                          BigDecimal percentageToAllocateStable,
                                          BigDecimal minToInvest) {

        return allocator.allocate(cryptos, amount, stableCoin, percentageToAllocateStable, minToInvest);
    }
}
