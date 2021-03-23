package com.concurrent.shop.queue.customer.impl;

import com.concurrent.shop.queue.customer.AbstractCustomer;
import com.concurrent.shop.queue.customer.Customer;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @version 1.0
 */
public class CustomerImpl extends AbstractCustomer {
    private AtomicBoolean served = new AtomicBoolean(false);

    public CustomerImpl(long id) {
        super(id);
    }

    @Override
    public AtomicBoolean isServed() {
        return served;
    }
}
