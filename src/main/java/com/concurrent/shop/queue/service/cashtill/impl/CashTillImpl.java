package com.concurrent.shop.queue.service.cashtill.impl;

import com.concurrent.shop.queue.customer.Customer;
import com.concurrent.shop.queue.service.cashtill.AbstractCashTill;
import lombok.Getter;

/**
 * @version 1.0
 */
@Getter
public class CashTillImpl extends AbstractCashTill {

    public CashTillImpl(long maximalAmountOfCustomersInQueue, long customersServedPerHour) {
        super(maximalAmountOfCustomersInQueue, customersServedPerHour);
    }

    @Override
    public void serve(Customer customer) {
        try {
            Thread.sleep(TIME_TO_SERVE_SINGLE_CUSTOMER);
            System.out.println(this + " cash till is serving customer " + customer);
            customer.isServed().set(true);
            customers.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
