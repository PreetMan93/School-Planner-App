package com.exam.planner.DSO;


import com.exam.planner.Logic.Events.Event;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

public class Planner {

    private ArrayList<Event> eventsList;
    private String id;

    public Planner(String id){
        eventsList = new ArrayList<>();
        this.id = id;
    }

    public String getId(){ return id; }

    public void addEvent(Event e){
        eventsList.add(e);
    }

    public void editEvent(String eventId, String eventName, int startYear, int startMonth, int startDay, int startHour, int startMinute, int endYear, int endMonth, int endDay, int endHour, int endMinute, int repeatYear, int repeatMonth, int repeatDay, boolean[] repeatDays){
        Event editEvent;
        if (!eventId.equals("-1"))
            editEvent = this.getEvent(eventId);
        else {
            editEvent = new Event();
            this.addEvent(editEvent);
        }

        editEvent.editName(eventName);
        try {
            editEvent.editStartDate(startYear, startMonth, startDay, startHour, startMinute);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            editEvent.editEndDate(endYear, endMonth, endDay, endHour, endMinute);
        } catch (Exception e) {
            e.printStackTrace();
        }

        boolean doRepeat = false;
        for (boolean b: repeatDays)
            doRepeat = doRepeat || b;
        if (doRepeat) {
            if (editEvent.getCopyId() == null)
                editEvent.editCopyId(java.util.UUID.randomUUID().toString());

            Calendar repCal = Calendar.getInstance();
            repCal.set(Calendar.YEAR, startYear);
            repCal.set(Calendar.MONTH, startMonth);
            repCal.set(Calendar.DAY_OF_MONTH, startDay);
            repCal.add(Calendar.DAY_OF_MONTH, 1);

            Calendar endCal = Calendar.getInstance();
            endCal.set(Calendar.YEAR, repeatYear);
            endCal.set(Calendar.MONTH, repeatMonth);
            endCal.set(Calendar.DAY_OF_MONTH, repeatDay);

            while (repCal.compareTo(endCal) <= 0) {
                if (repeatDays[repCal.get(Calendar.DAY_OF_WEEK) - 1]){
                    Event repEvent = new Event();
                    try {
                        editEvent.eventCopy(repEvent, repCal.get(Calendar.YEAR), repCal.get(Calendar.MONTH), repCal.get(Calendar.DAY_OF_MONTH));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    this.addEvent(repEvent);
                }
                repCal.add(Calendar.DAY_OF_MONTH, 1);
            }
        }
    }

    public boolean removeEvent(String id){
        boolean ret = false;
        for (Iterator<Event> iterator = eventsList.iterator(); iterator.hasNext();) {
            Event next = iterator.next();
            if (next.getId() != null && next.getId().equals(id)){
                iterator.remove();
                ret = true;
            }
        }
        return ret;
    }

    public boolean removeEventCopies(String copyId){
        boolean ret = false;
        for (Iterator<Event> iterator = eventsList.iterator(); iterator.hasNext();) {
            Event next = iterator.next();
            if (next.getCopyId() != null && next.getCopyId().equals(copyId)){
                iterator.remove();
                ret = true;
            }
        }
        return ret;
    }

    public boolean eventListContains(Event e){
        for (Event element : eventsList){
            if(e.compare(element))
                return true;
        }
        return false;
    }

    public Event getEvent(String id){
        for (Event e: eventsList)
            if (e.getId().equals(id))
                return e;
        return null;
    }

    public ArrayList<Event> getEventsList(){ return eventsList; }

    public ArrayList<Event> getEventsList(int year, int month, int day) {
        ArrayList<Event> retList = new ArrayList<>();
        for (Event e: eventsList)
            if (e.getStartYear() == year && e.getStartMonth() == month && e.getStartDay() == day)
                retList.add(e);
        return retList;
    }
}
