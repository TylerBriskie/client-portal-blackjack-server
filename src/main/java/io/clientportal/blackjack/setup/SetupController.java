package io.clientportal.blackjack.setup;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SetupController {

    @Autowired
    private SetupService setupService;

    @RequestMapping("/gimme")
    public String getFirstCards(){
        return "BlackJack!!";
    }

    //this is a test route
    @RequestMapping("/testing")
    public StateOfHand getFirsthand(){
        return setupService.getFirsthand();
    }

    @RequestMapping("/getCurrentHand")
    public List<StateOfHand> getCurrentHand(){
        return setupService.getCurrentHand();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/setup/")
    public List<StateOfHand> setFirstHandPost(@RequestBody List<Player> players) {
        return setupService.setFirstHandPost(players);
    }

}
