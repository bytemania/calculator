package org.dublin.jroad.domain;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// TODO AS ALLOCATOR SHOULD ALLOCATE 20% TO THE BIGGER (bigger cap rank) STABLE AND 80% TO the rest based on the cap
public class Allocator {

    public static Set<CryptoCurrencyToInvest> allocate(Set<CryptoCurrency> cryptos,
                                                       BigDecimal amount,
                                                       BigDecimal minToInvest) {
        List<CryptoCurrency> cryptoCurrencies = cryptos.stream()
                .filter(cryptoCurrency -> !cryptoCurrency.stable())
                .sorted(Comparator.comparing(CryptoCurrency::marketCap).reversed())
                .toList();

        return cryptoCurrencies.stream()
                .map(cryptoCurrency -> new CryptoCurrencyToInvest(cryptoCurrency.symbol(), cryptoCurrency.marketCap().multiply(amount)))
                .collect(Collectors.toSet());
    }

}
