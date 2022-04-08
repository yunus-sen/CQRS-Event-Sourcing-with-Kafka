package com.yunussen.accountcmd.api.controllers;

import com.yunussen.accountcmd.api.commands.OpenAccountCommand;
import com.yunussen.accountcmd.dto.OpenAccountResponse;
import com.yunussen.accountcommon.dto.BaseResponse;
import com.yunussen.cqrscore.infrasstructure.CommandDispatcher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.UUID;

@RestController
@RequestMapping(path = {"/v1/api/openBankAccount"})
public class OpenAccountController {

    private final Logger logger = LogManager.getLogger(OpenAccountController.class);
    @Autowired
    private CommandDispatcher commandDispatcher;

    @PostMapping
    public ResponseEntity<BaseResponse> openAccount(@RequestBody OpenAccountCommand command) {
        var id = UUID.randomUUID().toString();
        command.setId(id);
        try {
            commandDispatcher.send(command);
            return new ResponseEntity<>(new OpenAccountResponse("Bank account creation request comleted  successfuly.", command.getId()), HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            logger.error(MessageFormat.format("Client made bad request -{0}", ex.toString()));
            return new ResponseEntity<>(new BaseResponse(ex.toString()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            String safeErrorMessage = MessageFormat.format("Error while prosesing request  to open  a new bank account for id - {0}", id);
            return new ResponseEntity<>(new OpenAccountResponse(safeErrorMessage, id), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
