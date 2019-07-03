package com.exam.planner.Logic.Events;

public class EventTimeOutOfBoundsException extends EventValidationException{
    EventTimeOutOfBoundsException(String message){
        super(message);
    }
}
