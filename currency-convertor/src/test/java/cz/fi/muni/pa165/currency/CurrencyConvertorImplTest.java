package cz.fi.muni.pa165.currency;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;
import cz.fi.muni.pa165.currency.CurrencyConvertorImpl;
import java.math.BigDecimal;
import java.util.Currency;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.mockito.Mockito;

public class CurrencyConvertorImplTest {
    private static ExchangeRateTable ex;
    private static CurrencyConvertor convertor;

    @BeforeClass
    public static void setup() throws ExternalServiceFailureException {
        ex = Mockito.mock(ExchangeRateTable.class);
        Mockito.when(ex.getExchangeRate(Currency.getInstance("CZK"), Currency.getInstance("CZK")))
                .thenReturn(BigDecimal.ONE);
        Mockito.when(ex.getExchangeRate(Currency.getInstance("CZK"), Currency.getInstance("USD")))
                .thenReturn(new BigDecimal("2.47"));
        Mockito.when(ex.getExchangeRate(Currency.getInstance("USD"), Currency.getInstance("CZK")))
                .thenReturn(new BigDecimal("0.23"));

        convertor = new CurrencyConvertorImpl(ex);
    }

    @Test
    public void testConvert() throws ExternalServiceFailureException  {
        BigDecimal actual1 = convertor.convert(
                Currency.getInstance("CZK"),
                Currency.getInstance("USD"),
                BigDecimal.ZERO
        );
        BigDecimal expected1 = BigDecimal.ZERO;
        Assert.assertEquals(0, expected1.compareTo(actual1));

        BigDecimal actual2 = convertor.convert(
                Currency.getInstance("USD"),
                Currency.getInstance("CZK"),
                new BigDecimal("10")
        );
        BigDecimal expected2 = new BigDecimal("2.3");
        Assert.assertEquals(0, expected2.compareTo(actual2));

        Assert.assertEquals(
                convertor.convert(
                        Currency.getInstance("CZK"),
                        Currency.getInstance("USD"),
                        BigDecimal.ONE
                ), new BigDecimal("2.47")
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertWithNullSourceCurrency() {
        convertor.convert(null, Currency.getInstance("CZK"), BigDecimal.ZERO);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertWithNullTargetCurrency() {
        convertor.convert(Currency.getInstance("CZK"), null, BigDecimal.ZERO);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertWithNullSourceAmount() {
        convertor.convert(Currency.getInstance("CZK"), Currency.getInstance("USD"), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertWithUnknownCurrency() {
        convertor.convert(Currency.getInstance("KKT"), Currency.getInstance("USD"), BigDecimal.ZERO);
        convertor.convert(Currency.getInstance("CZK"), Currency.getInstance("KKT"), BigDecimal.ZERO);
        convertor.convert(Currency.getInstance("KKT"), Currency.getInstance("PPL"), BigDecimal.ZERO);
    }

    @Test(expected = ExternalServiceFailureException.class)
    public void testConvertWithExternalServiceFailure() {
        //
    }

}
