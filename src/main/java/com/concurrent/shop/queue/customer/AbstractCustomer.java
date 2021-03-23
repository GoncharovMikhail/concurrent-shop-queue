package com.concurrent.shop.queue.customer;

/**
 * @author Mihovel
 * @version 1.0
 */
public abstract class AbstractCustomer implements Customer {
    protected final long id;

    protected AbstractCustomer(long id) {
        this.id = id;
    }

    @Override
    public long getId() {
        return this.id;
    }
}
