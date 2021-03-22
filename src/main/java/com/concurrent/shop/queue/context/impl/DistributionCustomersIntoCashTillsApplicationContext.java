package com.concurrent.shop.queue.context.impl;

import com.concurrent.shop.queue.context.AbstractApplicationContext;
import com.concurrent.shop.queue.service.cashtill.CashTill;

/**
 * @version 1.0
 */
public class DistributionCustomersIntoCashTillsApplicationContext extends AbstractApplicationContext {

    //todo ???????
    @Override
    public void start() {
        customers.stream()
            .parallel()
            .filter(customer -> !customer.isServed().get())
            .forEach(customer -> {
                distributingServices.stream()
                    .parallel()
                    .forEach(distributingService -> {
                        CashTill servingCashTill = distributingService.distribute(cashTills, customer);
                        servingCashTill.serve(customer);
                    });
            });
    }
}
