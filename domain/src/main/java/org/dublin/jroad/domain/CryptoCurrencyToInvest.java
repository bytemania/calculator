package org.dublin.jroad.domain;

import java.math.BigDecimal;

public record CryptoCurrencyToInvest(String cryptoName, BigDecimal amountToInvest, boolean stable) {

}
