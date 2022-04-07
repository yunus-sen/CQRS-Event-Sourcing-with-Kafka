package com.yunussen.accountcmd;

import com.yunussen.accountcmd.api.commands.*;
import com.yunussen.cqrscore.infrasstructure.CommandDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class CommandApplication {
	@Autowired
	private CommandDispatcher commandDispatcher;

	@Autowired
	private CommandHandler commandHandler;

	public static void main(String[] args) {
		SpringApplication.run(CommandApplication.class, args);
	}

	@PostConstruct
	public void registeredHandleras(){
		commandDispatcher.registerHandle(OpenAccountCommand.class,commandHandler::handle);
		commandDispatcher.registerHandle(DepositFundsCommand.class,commandHandler::handle);
		commandDispatcher.registerHandle(WithdrawFundsCommand.class,commandHandler::handle);
		commandDispatcher.registerHandle(CloseAccountCommand.class,commandHandler::handle);

	}
}
