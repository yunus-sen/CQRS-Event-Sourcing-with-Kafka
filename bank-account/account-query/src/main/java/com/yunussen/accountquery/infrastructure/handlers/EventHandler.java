package com.yunussen.accountquery.infrastructure.handlers;

import com.yunussen.accountcommon.events.AccountClosedEvent;
import com.yunussen.accountcommon.events.AccountOpenedEvent;
import com.yunussen.accountcommon.events.FundsDepositedEvent;
import com.yunussen.accountcommon.events.FundsWithdrawnEvent;

public interface EventHandler {
    void on(AccountOpenedEvent event);

    void on(FundsDepositedEvent event);

    void on(FundsWithdrawnEvent event);

    void on(AccountClosedEvent event);
}
