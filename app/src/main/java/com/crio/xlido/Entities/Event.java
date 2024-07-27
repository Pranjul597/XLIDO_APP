package com.crio.xlido.Entities;

public class Event {
    
    private String eventName;
    private Integer eventId;
    private Integer organiserId;

    public Event(String eventName, Integer organiserId){
        this.eventName = eventName;
        this.eventId = null;
        this.organiserId = organiserId;
    }

    public Event(Integer eventId, Event event){
        this.eventId = eventId;
        this.eventName = event.getEventName();
        this.organiserId = event.getOrganiserId();
    }

    public String getEventName(){
        return eventName;
    }

    public Integer getEventId(){
        return eventId;
    }

    public Integer getOrganiserId(){
        return organiserId;
    }
}
