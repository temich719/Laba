package com.epam.esm.controller;

import com.epam.esm.errors.AnswerMessageJson;
import com.epam.esm.exception.InvalidInputDataException;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

@Controller
public abstract class AbstractController {

    protected final Gson gson;
    protected final AnswerMessageJson answerMessageJson;

    @Autowired
    public AbstractController(Gson gson, AnswerMessageJson answerMessageJson) {
        this.gson = gson;
        this.answerMessageJson = answerMessageJson;
    }

    protected void bindingResultCheck(BindingResult bindingResult) throws InvalidInputDataException {
        if (bindingResult.hasErrors()) {
            StringBuilder exceptionMessage = new StringBuilder();
            for (ObjectError objectError : bindingResult.getAllErrors()) {
                exceptionMessage.append(objectError.getDefaultMessage()).append(" ");
            }
            throw new InvalidInputDataException(exceptionMessage.toString());
        }
    }

}
