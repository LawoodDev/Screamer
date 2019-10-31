package fr.eclipseteam.scrimer.api.controller;

import fr.eclipseteam.scrimer.App;
import fr.eclipseteam.scrimer.api.model.Conv;
import fr.eclipseteam.scrimer.api.model.Message;
import fr.eclipseteam.scrimer.api.model.request.MessageRequest;
import fr.eclipseteam.scrimer.api.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Map;

@Controller
public class TChatController {
    @GetMapping(value = "/api")
    @ResponseBody
    public String getIndex() {
        return"Ceci est l'entrée sde l'api";
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
                value = "/api/conv/create")
    @ResponseBody
    public Conv createConv(@RequestBody ArrayList<User> users) {
        if(users.size()!= 2)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        Conv conv = new Conv(users.get(0), users.get(1));

        for(User us:users)
            App.getInstance().getActiveUserList().get(us.getUuid()).getConvList().add(conv.getUuid());

        App.getInstance().getConvList().put(conv.getUuid(),conv);
        return conv;
    }

    @GetMapping(value = "/api/conv")
    @ResponseBody
    public Map<String, Conv> convList() {
        return App.getInstance().getConvList();
    }

    @GetMapping(value = "/api/conv/{uuid}")
    @ResponseBody
    public Conv getConv(@RequestParam String uuid) {
        return App.getInstance().getConvList().get(uuid);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
                    value = "/api/conv/send")
    @ResponseBody
    public Conv createMessage(@RequestBody MessageRequest reqMessage) {
        Conv c = App.getInstance().getConvList().get(reqMessage.getConvId());


        c.getUsersMessages().get(reqMessage.getUserId()).add(new Message(reqMessage.getContent()));
        return App.getInstance().getConvList().get(reqMessage.getConvId());
    }
}
