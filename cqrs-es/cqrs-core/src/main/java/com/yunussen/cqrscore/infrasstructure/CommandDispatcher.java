package com.yunussen.cqrscore.infrasstructure;

import com.yunussen.cqrscore.commands.BaseCommand;
import com.yunussen.cqrscore.commands.CommandHandlerMethod;

public interface CommandDispatcher {

    <T extends BaseCommand> void registerHandle(Class<T> type, CommandHandlerMethod<T> handler);

    void send(BaseCommand command);
}
