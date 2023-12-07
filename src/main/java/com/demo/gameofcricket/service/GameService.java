package com.demo.gameofcricket.service;

import com.demo.gameofcricket.entity.Player;

import java.util.List;
import java.util.Map;

public interface GameService
{
    void startMatch(String team1,String team2);

    void playInning(String teamName);

    void completeMatch();

    List<Player> getPlayersByTeam(String teamName);

    Map<String, Integer> getRunsByTeams();

    String getWinningTeam();
}
