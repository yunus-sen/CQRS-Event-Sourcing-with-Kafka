package com.yunussen.accountcmd.api.commands;

import com.yunussen.cqrscore.commands.BaseCommand;

public class CloseAccountCommand extends BaseCommand {
    public CloseAccountCommand(String id) {
        super(id);
    }
}
