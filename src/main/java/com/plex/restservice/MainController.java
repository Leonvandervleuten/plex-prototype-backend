package com.plex.restservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.plex.restservice.Dto.NameAndIdList;
import com.plex.restservice.challenge.Challenge;
import org.json.JSONException;
import org.json.JSONObject;
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
    public List<Challenge> getChallenges() {
        return mainService.getChallenges();
    }


    @CrossOrigin
    @GetMapping("/challenges/{id}")
    public Challenge getChallengesById(@PathVariable("id") String id) {
        return mainService.getChallengeById(Long.parseLong(id));
    }

    @CrossOrigin
    @GetMapping("/challengelist/{id}")
    public List<Challenge> getChallengeList(@PathVariable("id") String id) {
        return mainService.getChallengeList(Long.parseLong(id));
    }

    @CrossOrigin
    @PostMapping(value = "/projectlist")
    public String getData(@RequestBody NameAndIdList nameAndIdList) throws JSONException, JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(nameAndIdList);
        JSONObject object = new JSONObject(json);
        return mainService.createProjectList(object).toString();
    }

    @CrossOrigin
    @GetMapping("/projectlist/student")
    public String getProjectListForStudent(){
      return mainService.projectListForStudent().toString();
    }
}
