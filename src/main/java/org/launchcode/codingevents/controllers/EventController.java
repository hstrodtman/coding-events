package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventData;
import org.launchcode.codingevents.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("events")
public class EventController {

    @GetMapping
    public String displayEvents(Model model){
       model.addAttribute("title", "All Events");
       model.addAttribute("events", EventData.getAll());
        return "events/index";
    }

    @GetMapping("create")
    public String renderCreateEventForm(Model model){
        model.addAttribute("title", "Add Event");
        return "events/create";
    }

    @PostMapping("create")
    public String createEvent(@ModelAttribute Event newEvent){
        EventData.add(newEvent);
        return "redirect:";
    }

    @GetMapping("delete")
    public String renderDeleteEventForm(Model model){
        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", EventData.getAll());
        return "events/delete";
    }

    @PostMapping("delete")
    public String deleteEvent(@RequestParam(required = false) int[] eventIds){
        if(eventIds !=null){
            for (int id : eventIds){
                EventData.remove(id);
            }
        }
        return "redirect:";
    }
}
