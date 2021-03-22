package com.concurrent.shop.queue.service.service;

import com.concurrent.shop.queue.customer.Customer;
import com.concurrent.shop.queue.service.cashtill.CashTill;

import java.util.Collection;

/**
 * @version 1.0
 */
public interface DistributingService {

    CashTill distribute(Collection<CashTill> allCashTills, Customer customer);
}
