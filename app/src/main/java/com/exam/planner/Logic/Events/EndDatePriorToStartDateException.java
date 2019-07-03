package com.exam.planner.Logic.Events;

public class EndDatePriorToStartDateException extends EventValidationException{
    EndDatePriorToStartDateException(String message){
        super(message);
    }
}
