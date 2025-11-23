package com.example.itk;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private final long accountNumber;
    private int balance;
    private final Lock lock = new ReentrantLock();

    public BankAccount(long accountNumber, int balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public Lock getLock() {
        return lock;
    }

    public void deposit(int amount) {
        lock.lock();

        try {
            balance += amount;
            System.out.printf("%s: Deposit %d. new balance: %d\n", Thread.currentThread().getName(), amount, balance);
        } finally {
            lock.unlock();
        }
    }

    public boolean withdraw(int amount) {
        lock.lock();
        try {
            balance -= amount;
            System.out.printf("%s: Withdraw %d. new balance: %d\n", Thread.currentThread().getName(), amount, balance);
        } finally {
            lock.unlock();
        }
        return true;
    }

    public int getBalance() {
            return balance;
    }
}
