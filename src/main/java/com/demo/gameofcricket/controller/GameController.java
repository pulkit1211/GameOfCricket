package com.demo.gameofcricket.controller;

import com.demo.gameofcricket.entity.Player;
import com.demo.gameofcricket.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cricket")
public class GameController
{
    @Autowired
    private GameService gameService;

    @PostMapping("/start")
    public void startMatch(@RequestParam String team1,@RequestParam String team2)
    {
        gameService.startMatch(team1,team2);
    }

    @PostMapping("/playInning/{teamName}")
    public void playInning(@PathVariable String teamName)
    {
        gameService.playInning(teamName);
    }

    @PostMapping("/completeMatch")
    public void completeMatch()
    {
        gameService.completeMatch();
    }

    @GetMapping("/players/{teamName}")
    public List<Player> getPlayersByTeam(@PathVariable String teamName)
    {
        return gameService.getPlayersByTeam(teamName);
    }

    @GetMapping("/runs")
    public Map<String, Integer> getRunsByTeams() {
        return gameService.getRunsByTeams();
    }

    @GetMapping("/winningTeam")
    public String getWinningTeam() {
        return gameService.getWinningTeam();
    }
}
