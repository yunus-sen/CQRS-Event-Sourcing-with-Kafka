package com.yunussen.accountcmd.api.commands;

import com.yunussen.cqrscore.commands.BaseCommand;
import lombok.Data;

@Data
public class WithdrawFundsCommand extends BaseCommand {
    private double amount;
}
