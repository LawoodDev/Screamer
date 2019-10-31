package fr.eclipseteam.scrimer.api.controller;

import fr.eclipseteam.scrimer.App;
import fr.eclipseteam.scrimer.api.model.Conv;
import fr.eclipseteam.scrimer.api.model.User;
import fr.eclipseteam.scrimer.api.model.Vote;
import fr.eclipseteam.scrimer.api.model.request.VoteRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@Controller
public class MatchController {
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            value = "/api/match/vote")
    @ResponseBody
    public Vote voteForPost(@RequestBody VoteRequest vote) {
        Vote v = new Vote(vote.getPostId(), vote.isLike());
        App.getInstance().getActiveUserList().get(vote.getUserId()).getVoteList().put(vote.getPostId(), vote.isLike());
        checkForMatch(App.getInstance().getActiveUserList().get(vote.getUserId()));
        return v;
    }

    public void checkForMatch(User current){
        for (User user: App.getInstance().getActiveUserList().values()) {
            if(user.getUuid()!=current.getUuid()) {
                int count = 0;
                for (String tUser : user.getVoteList().keySet())
                    if (current.getVoteList().containsKey(tUser) && user.getVoteList().containsKey(tUser))
                        if (current.getVoteList().get(tUser) && user.getVoteList().get(tUser))
                            count++;
                if (count == 3) {
                    user.getMatchList().add(current.getUuid());
                    current.getMatchList().add(user.getUuid());
                }
            }
        }
    }

}
