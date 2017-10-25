package cz.muni.fi.pa165.currency;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.Currency;

public class MainAnnotations {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext acac = new AnnotationConfigApplicationContext("cz.muni.fi.pa165.currency");

        // create a FileEventType object from the application context
        CurrencyConvertor convertor = acac.getBean(CurrencyConvertor.class);

        System.out.println(convertor.convert(Currency.getInstance("EUR"), Currency.getInstance("CZK"), new BigDecimal("1")));
    }
}
