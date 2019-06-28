package com.exam.planner.DSO.Events;

public class EventTimeInvalidFormatException extends EventValidationException{
    EventTimeInvalidFormatException(String message){
        super(message);
    }
}
