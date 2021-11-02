package com.plex.restservice;

import com.plex.restservice.challenge.Challenge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1")
public class MainController {

    private final MainService mainService;

    @Autowired
    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @CrossOrigin
    @GetMapping("/challenges")
    public List<Challenge> getChallenges(){
        return mainService.getChallenges();
    }

    @CrossOrigin
    @GetMapping("/challenges/{id}")
    public Challenge getChallengesById(@PathVariable("id") String id){
        return mainService.getChallengeById(Long.parseLong(id));
    }

    @CrossOrigin
    @GetMapping("/challengelist/{id}")
    public List<Challenge> getChallengeList(@PathVariable("id") String id){
        return mainService.getChallengeList(Long.parseLong(id));
    }
}
