package com.yunussen.accountcmd.infrastructure;

import com.yunussen.cqrscore.commands.BaseCommand;
import com.yunussen.cqrscore.commands.CommandHandlerMethod;
import com.yunussen.cqrscore.infrasstructure.CommandDispatcher;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class AccountCommandDispatcher implements CommandDispatcher {

    private final Map<Class<? extends BaseCommand>, List<CommandHandlerMethod>> routes = new HashMap<>();

    @Override
    public <T extends BaseCommand> void registerHandle(Class<T> type, CommandHandlerMethod<T> handler) {
        var handlers = routes.computeIfAbsent(type, c -> new LinkedList<>());
        handlers.add(handler);
    }

    @Override
    public void send(BaseCommand command) {
        var handlers = routes.get(command.getClass());
        if (handlers == null || handlers.isEmpty()) {
            throw new RuntimeException("no command handler was registered");
        }
        if (handlers.size() > 1) {
            throw new RuntimeException("cannot send command to more than one command !");
        }

        handlers.get(0).handle(command)

        ;

    }
}
