package com.concurrent.shop.queue.service.cashtill;

import com.concurrent.shop.queue.customer.Customer;

import java.util.concurrent.BlockingQueue;

/**
 * @version 1.0
 */
public interface CashTill {

    long getAmountOfCustomersServedPerHour();

    void serve(Customer customer);

    BlockingQueue<Customer> getCustomersQueue();
}
