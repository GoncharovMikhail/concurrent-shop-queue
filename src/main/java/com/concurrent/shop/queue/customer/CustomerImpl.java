package com.concurrent.shop.queue.customer;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @version 1.0
 */
public class CustomerImpl implements Customer {
    private AtomicBoolean served = new AtomicBoolean(false);

    @Override
    public AtomicBoolean isServed() {
        return served;
    }
}
