package com.demo.gameofcricket.service;

import com.demo.gameofcricket.entity.Inning;
import com.demo.gameofcricket.entity.Player;
import com.demo.gameofcricket.enums.MatchStatus;
import com.demo.gameofcricket.enums.WicketType;
import com.demo.gameofcricket.repository.InningRepository;
import com.demo.gameofcricket.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class GameServiceImpl implements GameService{

    @Autowired
   private PlayerRepository playerRepository;

    @Autowired
    private InningRepository inningRepository;

    private static final int MAX_RUNS=6;
    private static final int MAX_WICKETS=10;
    @Override
    public void startMatch(String team1, String team2)
    {
        //create new teams
        initializePlayers(team1);
        initializePlayers(team2);

        //now we have to create innings for both teams
        Inning inningTeam1=new Inning();
        inningTeam1.setTeamName(team1);
        inningRepository.save(inningTeam1);

        Inning inningTeam2=new Inning();
        inningTeam2.setTeamName(team2);
        inningRepository.save(inningTeam2);

    }

    @Override
    public void playInning(String teamName)
    {
        Inning inning=inningRepository.findByTeamName(teamName);

        if(inning==null)
        {
            //return new RuntimeException("Innings not found for team: "+teamName);
            System.out.println("Innings not found for team: "+teamName);
            return;
        }
        List<Player> players=playerRepository.findAll();

        for(Player player:players)
        {
            int runScored=getRandomRuns();
            player.setScore(player.getScore()+runScored);

            //simulate wicketProbability
            if(isWicket())
            {
                WicketType wicketType=getRandomWicketType();
                System.out.println(player.getName()+" is out! Type: "+wicketType);
                inning.setWickets(inning.getWickets()+1);
            }
        }
        inning.setTotalScore(inning.getTotalScore()+calculateTeamScore(players));
        inningRepository.save(inning);
    }

    @Override
    public void completeMatch()
    {
        Inning inningTeam1 = inningRepository.findByTeamName("Team 1");
        Inning inningTeam2 = inningRepository.findByTeamName("Team 2");

        // Update match status
        inningTeam1.setMatchStatus(MatchStatus.COMPLETED);
        inningTeam2.setMatchStatus(MatchStatus.COMPLETED);

        // Save the innings
        inningRepository.save(inningTeam1);
        inningRepository.save(inningTeam2);

    }

    @Override
    public List<Player> getPlayersByTeam(String teamName) {
        return playerRepository.findAll();
    }

    @Override
    public Map<String, Integer> getRunsByTeams() {
        Map<String, Integer> runsByTeams = new HashMap<>();

        List<Inning> innings = inningRepository.findAll();
        for (Inning inning : innings) {
            runsByTeams.put(inning.getTeamName(), inning.getTotalScore());
        }

        return runsByTeams;
    }

    @Override
    public String getWinningTeam() {
        List<Inning> innings = inningRepository.findAll();

        if (innings.size() != 2) {
            throw new RuntimeException("Invalid number of innings");
        }

        Inning team1Inning = innings.get(0);
        Inning team2Inning = innings.get(1);

        int team1Score = team1Inning.getTotalScore();
        int team2Score = team2Inning.getTotalScore();

        if (team1Score > team2Score) {
            return team1Inning.getTeamName() + " is the winning team!";
        } else if (team2Score > team1Score) {
            return team2Inning.getTeamName() + " is the winning team!";
        } else {
            return "It's a draw!";
        }
    }

    private void initializePlayers(String teamName)
    {
        for(int i=1;i<=11;i++)
        {
            Player player=new Player();
            player.setName(teamName+" Player "+i);
            playerRepository.save(player);
        }
    }
    private  int getRandomRuns()
    {
        return new Random().nextInt(MAX_RUNS+1);
    }
    private boolean isWicket()
    {
        return new Random().nextBoolean();
    }
    private WicketType getRandomWicketType()
    {
       // int random=new Random().nextInt(WicketType.values());
        int random=new Random().nextInt(WicketType.values().length);
        return WicketType.values()[random];
    }

    private  int calculateTeamScore(List<Player>players)
    {
        return players.stream().mapToInt(Player::getScore).sum();
    }
}
