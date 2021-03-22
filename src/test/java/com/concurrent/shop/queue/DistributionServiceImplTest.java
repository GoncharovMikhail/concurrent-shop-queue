package com.concurrent.shop.queue;

import com.concurrent.shop.queue.customer.Customer;
import com.concurrent.shop.queue.customer.CustomerImpl;
import com.concurrent.shop.queue.service.cashtill.CashTill;
import com.concurrent.shop.queue.service.cashtill.impl.CashTillImpl;
import com.concurrent.shop.queue.service.service.DistributingService;
import com.concurrent.shop.queue.service.service.DistributionServiceImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mihovel
 * @version 1.0
 */
public class DistributionServiceImplTest {
    private final DistributingService distributingService = new DistributionServiceImpl();

    private final List<CashTill> cashTills = new ArrayList<CashTill>() {
        {
            add(new CashTillImpl(20, 10));
            add(new CashTillImpl(20, 15));
            add(new CashTillImpl(20, 20));
        }
    };

    private final List<Customer> customers = new ArrayList<Customer>() {
        {
            add(new CustomerImpl());
            add(new CustomerImpl());
            add(new CustomerImpl());
            add(new CustomerImpl());
            add(new CustomerImpl());
        }
    };

    @Test
    public void test() {
        customers.stream()
            .parallel()
            .forEach(customer -> distributingService.distribute(cashTills, customer));
    }
}
