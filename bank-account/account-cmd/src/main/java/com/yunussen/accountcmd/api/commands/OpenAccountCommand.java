package com.yunussen.accountcmd.api.commands;

import com.yunussen.accountcommon.dto.AccountType;
import com.yunussen.cqrscore.commands.BaseCommand;
import lombok.Data;

@Data
public class OpenAccountCommand extends BaseCommand {
    private String accountHolder;
    private AccountType accountType;
    private double openingBalance;
}
