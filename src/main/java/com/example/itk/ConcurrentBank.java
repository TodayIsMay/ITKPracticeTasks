package com.example.itk;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class ConcurrentBank {
    private final ConcurrentHashMap<Long, BankAccount> accounts = new ConcurrentHashMap<>();
    private final AtomicLong accountNumberGenerator = new AtomicLong(1);

    public BankAccount createAccount(int initialBalance) {
        long accountNumber = accountNumberGenerator.getAndIncrement();
        BankAccount account = new BankAccount(accountNumber, initialBalance);
        accounts.put(accountNumber, account);
        return account;
    }

    public boolean transfer(BankAccount from, BankAccount to, int amount) {
        BankAccount firstLock;
        BankAccount secondLock;
        if (from.getAccountNumber() < to.getAccountNumber()) {
            firstLock = from;
            secondLock = to;
        } else {
            firstLock = to;
            secondLock = from;
        }

        firstLock.getLock().lock();
        secondLock.getLock().lock();

        try {
            if (!from.withdraw(amount)) {
                return false;
            }
            to.deposit(amount);
            return true;
        } finally {
            secondLock.getLock().unlock();
            firstLock.getLock().unlock();
        }
    }

    public long getTotalBalance() {
        List<BankAccount> accountList = new ArrayList<>(accounts.values());
        accountList.sort(Comparator.comparingLong(BankAccount::getAccountNumber));

        for (BankAccount account : accountList) {
            account.getLock().lock();
        }

        int total = 0;

        try {
            for (BankAccount account : accountList) {
                total += account.getBalance();
            }
        } finally {
            for (int i = accountList.size() - 1; i >= 0; i--) {
                accountList.get(i).getLock().unlock();
            }
        }

        return total;
    }
}