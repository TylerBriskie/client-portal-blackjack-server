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
        return "BlackJack Gimme More!!";
    }

    @RequestMapping("/resetHand/")
    public String resetHand(){
        setupService.resetHand();
        return "Jeez, thanks for doing the Men In Black Flashly Thingy";
    }

    @RequestMapping("/getCurrentHand")
    public List<StateOfHand> getCurrentHand(){
        return setupService.getCurrentHand();
    }

    @RequestMapping(value = "/setup/", method = RequestMethod.POST)
    public List<StateOfHand> setFirstHandPost(@RequestBody List<Player> players) {
        return setupService.setFirstHandPost(players);
    }

    @RequestMapping(value="/hit/{playerId}")
    public List<StateOfHand> hitOneCard(@PathVariable int playerId){
        return setupService.hitOneCard(playerId);
    }


}
