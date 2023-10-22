package org.dublin.jroad.domain.util;

import org.dublin.jroad.domain.CryptoCurrency;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class CryptosParameterResolver implements ParameterResolver {

    private static Map<String, CryptoCurrency> cryptos;

    {
        cryptos = new HashMap<>();

        ClassLoader classLoader = getClass().getClassLoader();
        Path path = new File(classLoader.getResource("data/cryptos.csv").getFile()).toPath();

        try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            br.readLine(); //skip header
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                String[] attributes = line.split(",");
                if (attributes.length != 4) {
                    throw new RuntimeException("Invalid number of attributes in a csv line");
                }
                String symbol = attributes[0];
                BigDecimal price = BigDecimal.valueOf(Double.parseDouble(attributes[1]));
                BigDecimal marketCap = BigDecimal.valueOf(Double.parseDouble(attributes[2]))
                        .divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);
                boolean stable = Boolean.parseBoolean(attributes[3]);
                CryptoCurrency currency = new CryptoCurrency(symbol, price, marketCap, stable);
                cryptos.put(symbol, currency);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        Parameter parameter = parameterContext.getParameter();
        if (parameter.getType() != Map.class) {
            return false;
        }
        Type type = parameter.getParameterizedType();
        if (!(type instanceof ParameterizedType parameterizedType)) {
            return false;
        }

        Type firstParameterType = parameterizedType.getActualTypeArguments()[0];
        if (!firstParameterType.equals(String.class)) {
            return false;
        }

        Type secondParameterType = parameterizedType.getActualTypeArguments()[1];
        return secondParameterType.equals(CryptoCurrency.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return cryptos;
    }
}
