package com.concurrent.shop.queue.context;

import com.concurrent.shop.queue.customer.Customer;
import com.concurrent.shop.queue.service.cashtill.CashTill;
import com.concurrent.shop.queue.service.service.DistributingService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @version 1.0
 */
public abstract class AbstractApplicationContext implements ApplicationContext {
    protected Collection<CashTill> cashTills = new ArrayList<>();
    protected Collection<DistributingService> distributingServices = new ArrayList<>();
    /* TODO: 22.03.2021 а если у меня несколько DistributingService'ов и я хочу, чтоб они все асинхронно процессли покупателей?
         Какую тогда коллекцию лучше использовать? */
    protected Collection<Customer> customers = new CopyOnWriteArrayList<>();

    @Override
    public ApplicationContext setDistributingService(DistributingService distributingService) {
        this.distributingServices.add(distributingService);
        return this;
    }

    @Override
    public ApplicationContext setCashTill(CashTill cashTill) {
        this.cashTills.add(cashTill);
        return this;
    }

    @Override
    public ApplicationContext setCustomer(Customer customer) {
        this.customers.add(customer);
        return this;
    }
}
