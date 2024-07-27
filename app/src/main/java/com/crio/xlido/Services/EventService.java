package com.crio.xlido.Services;

import com.crio.xlido.repositories.EventRepository;
import com.crio.xlido.repositories.UserRepository;
import com.crio.xlido.Entities.*;
import java.util.List;
import javax.lang.model.util.ElementScanner6;


public class EventService {
    
    private EventRepository eventRepository;
    private UserRepository userRepository;

    public EventService(EventRepository eventRepository, UserRepository userRepository){
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public Event createEvent(List<String> tokens) {
        String eventName = tokens.get(1);
        Integer organiserId = Integer.parseInt(tokens.get(2));
        if(!userRepository.userIdPresent(organiserId))
            throw new RuntimeException("User with an id "+organiserId+" does not exist");
        else{
            Event event = new Event(eventName, organiserId);
            event = eventRepository.save(event);
            return event;
        }
    }

    public void deleteEvent(List<String> tokens){
        Integer eventId = Integer.parseInt(tokens.get(1));
        Integer userId = Integer.parseInt(tokens.get(2));

        if(!userRepository.userIdPresent(userId))
            throw new RuntimeException("User with an id "+userId+" does not exist");
        else if(!eventRepository.isEventPresent(eventId))
            throw new RuntimeException("Event with an id "+eventId+" does not exist");
        else if(!eventRepository.getOrganiserId(eventId).equals(userId))
            throw new RuntimeException("User with an id "+userId+" is not a organizer of Event with an id "+eventId);
        else
            eventRepository.delete(eventId);

    }

}
