package fr.eclipseteam.scrimer.api.controller;

import fr.eclipseteam.scrimer.App;
import fr.eclipseteam.scrimer.api.model.Conv;
import fr.eclipseteam.scrimer.api.model.User;
import fr.eclipseteam.scrimer.api.model.request.LoginRequest;
import fr.eclipseteam.scrimer.api.model.request.RegisterRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.http.HttpResponse;
import java.util.Map;

@Controller
public class UsersController {


    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
                    value = "/api/users/create")
    @ResponseBody
    public User createUser(@RequestBody RegisterRequest us) {
        for(User user: App.getInstance().getActiveUserList().values()) {
            if (user.getEmail().equals(us.getEmail()))
                throw new ResponseStatusException(HttpStatus.FOUND, "User already exist");
        }
        User newUser = new User(us.getEmail(), us.getUserName(),us.getPassword(), "/ressources/img/icons/user.svg");
            App.getInstance().getActiveUserList().put(newUser.getUuid(), newUser);
        return newUser;
    }


    @RequestMapping(method = RequestMethod.OPTIONS, value = "/api/users/login")
    @ResponseBody
    public String  loginUser() {
        return "OK";
    }


    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            value = "/api/users/login")
    @ResponseBody
    public User loginUser(@RequestBody LoginRequest us) {
        for(User user: App.getInstance().getActiveUserList().values()) {
            if (user.getEmail().equals(us.getEmail()))
                if(user.comparePassword(us.getPassword()))
                    return App.getInstance().getActiveUserList().get(user.getUuid());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Email or password wrong");
    }

    @GetMapping(value = "/api/users")
    @ResponseBody
    public Map<String, User> userList() {
        return App.getInstance().getActiveUserList();
    }

    @GetMapping(value = "/api/users/{uuid}")
    @ResponseBody
    public User getUser(@PathVariable("uuid") String uuid) {

        return App.getInstance().getActiveUserList().get(uuid);
    }
}
