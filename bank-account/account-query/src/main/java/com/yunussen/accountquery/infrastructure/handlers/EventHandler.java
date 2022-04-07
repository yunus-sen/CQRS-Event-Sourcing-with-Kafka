package com.yunussen.accountquery.infrastructure.handlers;

import com.yunussen.accountcommon.events.AccountClosedEvent;
import com.yunussen.accountcommon.events.AccountOpenedEvent;
import com.yunussen.accountcommon.events.FundsDepositedEvent;
import com.yunussen.accountcommon.events.FundsWithdrawnEvents;

public interface EventHandler {
    void on(AccountOpenedEvent event);

    void on(FundsDepositedEvent event);

    void on(FundsWithdrawnEvents event);

    void on(AccountClosedEvent event);
}
