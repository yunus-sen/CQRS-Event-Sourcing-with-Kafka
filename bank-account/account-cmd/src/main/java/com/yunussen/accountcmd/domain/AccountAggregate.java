package com.yunussen.accountcmd.domain;

import com.yunussen.accountcmd.api.commands.OpenAccountCommand;
import com.yunussen.accountcommon.events.AccountClosedEvent;
import com.yunussen.accountcommon.events.AccountOpenedEvent;
import com.yunussen.accountcommon.events.FundsDepositedEvent;
import com.yunussen.accountcommon.events.FundsWithdrawnEvents;
import com.yunussen.cqrscore.domain.AggregateRoot;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
public class AccountAggregate extends AggregateRoot {
    private Boolean active;
    private double balance;

    public AccountAggregate(OpenAccountCommand command) {
        raiseEvent(AccountOpenedEvent.builder()
                .id(command.getId())
                .accountHolder(command.getAccountHolder())
                .createdDate(new Date())
                .accountType(command.getAccountType())
                .openingBalance(command.getOpeningBalance())
                .build());
    }

    public void apply(AccountOpenedEvent event) {
        this.id = event.getId();
        this.active = true;
        this.balance = event.getOpeningBalance();
    }

    public void depositFunds(double amount) {
        if (this.active.equals(false)) {
            throw new IllegalArgumentException("Funds cannot be deposited into a closed account!");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("the deposit amount must be greater than 0!");
        }
        raiseEvent(FundsDepositedEvent.builder()
                .id(this.id)
                .amount(amount)
                .build());
    }

    public void apply(FundsDepositedEvent event) {
        this.id = event.getId();
        this.balance += event.getAmount();
    }

    public void withdrawFunds(double amount) {
        if (this.active.equals(false)) {
            throw new IllegalArgumentException("Funds cannot be withdraw from a closed account!");
        }
        if (balance <= 0 || balance < amount) {
            throw new IllegalArgumentException("the funds amount cannot be greater than balance.");
        }
        raiseEvent(FundsWithdrawnEvents.builder()
                .id(this.id)
                .amount(amount)
                .build());
    }

    public void apply(FundsWithdrawnEvents event) {
        this.id = event.getId();
        this.balance -= event.getAmount();
    }

    public void closeAccount() {
        if (this.active.equals(false)) {
            throw new IllegalArgumentException("the Account has already been  closeded.");
        }
        raiseEvent(AccountClosedEvent.builder()
                .id(this.id)
                .build());
    }

    public void apply(AccountClosedEvent event) {
        this.id = event.getId();
        this.active = false;
    }

    public double getBalance() {
        return balance;
    }
}
