package com.crio.xlido.repositories;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.HashMap;
import com.crio.xlido.Entities.*;

import java.util.List;

public class EventRepository {
    
    private final Map<Integer, Event> storage = new HashMap<>();
    private AtomicInteger eventId = new AtomicInteger(0);
    
    public Event save(Event event){
        Event newEvent = new Event(eventId.incrementAndGet(), event);
        storage.putIfAbsent(newEvent.getEventId(), newEvent);
        return newEvent;
    }

    public void delete(Integer eventId){
        storage.remove(eventId);
    }

    public boolean isEventPresent(Integer eventId){
        if(storage.containsKey(eventId))
            return true;
        return false;
    }

    public Integer getOrganiserId(Integer eventId){
        return storage.get(eventId).getOrganiserId();

    }



}
