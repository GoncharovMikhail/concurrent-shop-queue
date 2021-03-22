package com.concurrent.shop.queue;

import com.concurrent.shop.queue.context.ApplicationContext;
import com.concurrent.shop.queue.context.impl.DistributionCustomersIntoCashTillsApplicationContext;
import com.concurrent.shop.queue.customer.CustomerImpl;
import com.concurrent.shop.queue.service.cashtill.impl.CashTillImpl;
import com.concurrent.shop.queue.service.service.DistributionServiceImpl;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

/**
 * @version 1.0
 */
public class ApplicationRunner {
    private static final long MAXIMUM_CUSTOMERS_IN_CASH_TILL_FOR_ALL_CASHTILLS = 25L;

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext applicationContext = new DistributionCustomersIntoCashTillsApplicationContext();

        /* 5 касс. */
        applicationContext
            .setCashTill(new CashTillImpl(MAXIMUM_CUSTOMERS_IN_CASH_TILL_FOR_ALL_CASHTILLS, 13))
            .setCashTill(new CashTillImpl(MAXIMUM_CUSTOMERS_IN_CASH_TILL_FOR_ALL_CASHTILLS, 15))
            .setCashTill(new CashTillImpl(MAXIMUM_CUSTOMERS_IN_CASH_TILL_FOR_ALL_CASHTILLS, 17))
            .setCashTill(new CashTillImpl(MAXIMUM_CUSTOMERS_IN_CASH_TILL_FOR_ALL_CASHTILLS, 19))
            .setCashTill(new CashTillImpl(MAXIMUM_CUSTOMERS_IN_CASH_TILL_FOR_ALL_CASHTILLS, 21))
            /* 2 распределяющих сервиса. */
            .setDistributingService(new DistributionServiceImpl())
            .setDistributingService(new DistributionServiceImpl());
        /* Добавляем покупателей. */
        ZonedDateTime now = ChronoUnit.SECONDS.addTo(ZonedDateTime.now(), 10);
        while (now.isAfter(ZonedDateTime.now())) {
            applicationContext.setCustomer(new CustomerImpl());
            Thread.sleep(500L);
        }
        /* Запускаем. */
        applicationContext.start();
    }
}
