package com.concurrent.shop.queue.service.cashtill;

import com.concurrent.shop.queue.customer.Customer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @version 1.0
 */
public abstract class AbstractCashTill implements CashTill {
    protected final long maximalAmountOfCustomersInQueue;
    protected final long customersServedPerHour;
    protected BlockingQueue<Customer> customers;

    protected final long TIME_TO_SERVE_SINGLE_CUSTOMER;


    protected AbstractCashTill(long maximalAmountOfCustomersInQueue, long customersServedPerHour) {
        this.maximalAmountOfCustomersInQueue = maximalAmountOfCustomersInQueue;
        this.customersServedPerHour = customersServedPerHour;

        this.customers = new ArrayBlockingQueue<>((int) maximalAmountOfCustomersInQueue);
        this.TIME_TO_SERVE_SINGLE_CUSTOMER = 3600L / customersServedPerHour;
    }

    @Override
    public long getAmountOfCustomersServedPerHour() {
        return this.customersServedPerHour;
    }

    @Override
    public BlockingQueue<Customer> getCustomersQueue() {
        return this.customers;
    }
}
