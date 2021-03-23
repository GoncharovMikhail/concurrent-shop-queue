package com.concurrent.shop.queue.customer;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @version 1.0
 */
public interface Customer {

    long getId();

    AtomicBoolean isServed();
}
