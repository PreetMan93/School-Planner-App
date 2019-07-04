package com.exam.planner.Logic.Events;

public class EventTimeInvalidFormatException extends EventValidationException{
    EventTimeInvalidFormatException(String message){
        super(message);
    }
}
