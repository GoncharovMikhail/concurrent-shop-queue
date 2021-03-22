package com.concurrent.shop.queue.context;

import com.concurrent.shop.queue.customer.Customer;
import com.concurrent.shop.queue.service.cashtill.CashTill;
import com.concurrent.shop.queue.service.service.DistributingService;

/**
 * @version 1.0
 */
public interface ApplicationContext {

    ApplicationContext setDistributingService(DistributingService distributingService);

    ApplicationContext setCashTill(CashTill cashTill);

    ApplicationContext setCustomer(Customer customer);

    void start();
}
