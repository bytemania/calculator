package org.dublin.jroad.domain;

import java.math.BigDecimal;

record CryptoCurrency(String name, BigDecimal price, BigDecimal marketCap) {}
