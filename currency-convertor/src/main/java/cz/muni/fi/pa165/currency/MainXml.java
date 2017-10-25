package cz.muni.fi.pa165.currency;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.Currency;

public class MainXml {
    public static void main(String[] args){
        // open/read the application context file
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        // instantiate our spring dao object from the application context
        ExchangeRateTable table = (ExchangeRateTable) ctx.getBean("table");

        // create a FileEventType object from the application context
        CurrencyConvertor convertor = (CurrencyConvertor) ctx.getBean("currencyConvertor");

        System.out.println(convertor.convert(Currency.getInstance("EUR"), Currency.getInstance("CZK"), new BigDecimal("1")));
    }
}
