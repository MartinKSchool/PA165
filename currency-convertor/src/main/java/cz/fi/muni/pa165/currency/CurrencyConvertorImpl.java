package cz.fi.muni.pa165.currency;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Currency;


/**
 * This is base implementation of {@link CurrencyConvertor}.
 *
 * @author petr.adamek@embedit.cz
 */
public class CurrencyConvertorImpl implements CurrencyConvertor {

    private final ExchangeRateTable exchangeRateTable;
    //private final Logger logger = LoggerFactory.getLogger(CurrencyConvertorImpl.class);

    public CurrencyConvertorImpl(ExchangeRateTable exchangeRateTable) {
        this.exchangeRateTable = exchangeRateTable;
    }

    @Override
    public BigDecimal convert(Currency sourceCurrency, Currency targetCurrency, BigDecimal sourceAmount) {
        if(sourceCurrency == null || targetCurrency == null || sourceAmount == null){
            throw new IllegalArgumentException("One or more arguments are null!");
        }
        try{
            BigDecimal rate = this.exchangeRateTable.getExchangeRate(sourceCurrency, targetCurrency);
            return sourceAmount.multiply(rate);
        } catch(ExternalServiceFailureException ex) {
            return null;
        }
    }

}
