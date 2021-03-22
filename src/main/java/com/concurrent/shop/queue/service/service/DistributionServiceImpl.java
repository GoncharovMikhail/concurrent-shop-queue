package com.concurrent.shop.queue.service.service;

import com.concurrent.shop.queue.customer.Customer;
import com.concurrent.shop.queue.service.cashtill.CashTill;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;

/**
 * @version 1.0
 */
@NoArgsConstructor
public class DistributionServiceImpl implements DistributingService {
    private final Object LOCK = new Object();

    public CashTill distribute(Collection<CashTill> allCashTills, Customer customer) {
        Optional<CashTill> availableCashTillFastestCustomersQueueOptional = findAvailableCashTillFastestCustomersQueue(allCashTills);

        if (availableCashTillFastestCustomersQueueOptional.isPresent()) {
            CashTill bestCashTill = availableCashTillFastestCustomersQueueOptional.get();
            onMinimalWaitingTimeAvailableCashTillFound(bestCashTill, customer);
        }
        while (availableCashTillFastestCustomersQueueOptional.isEmpty()) {
            onNoAvailableCashTillFound();
        }
        CashTill bestCashTill = availableCashTillFastestCustomersQueueOptional.get();

        onMinimalWaitingTimeAvailableCashTillFound(bestCashTill, customer);
        return bestCashTill;
    }

    private Optional<CashTill> findAvailableCashTillFastestCustomersQueue(Collection<CashTill> allCashTills) {
        return allCashTills.stream()
            .parallel()
            .filter(cashTill -> !isCustomersQueueFull(cashTill))
            .min(Comparator.comparingDouble(this::totalAwaitingTime));
    }

    //todo хз тут нпе
    private boolean isCustomersQueueFull(CashTill cashTill) {
        return cashTill.getCustomersQueue().remainingCapacity() == 0;
    }

    private void onMinimalWaitingTimeAvailableCashTillFound(CashTill cashTill, Customer customer) {
        try {
            cashTill.getCustomersQueue().put(customer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private long totalAwaitingTime(CashTill cashTill) {
        return cashTill.getCustomersQueue().size() * 3600L / cashTill.getAmountOfCustomersServedPerHour();
    }

    private void onNoAvailableCashTillFound() {
        synchronized (LOCK) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                notify();
            }
        }
    }
}
