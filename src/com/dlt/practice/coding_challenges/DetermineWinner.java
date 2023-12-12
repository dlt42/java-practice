package com.dlt.practice.coding_challenges;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.TreeMap;
import java.util.stream.Collectors;

import lombok.Getter;

public class DetermineWinner {
    public static void main(String[] args) {
        Team team1 = new Team("Sally", "Roger");
        Team team2 = new Team("Eric", "Rebecca");
        Team team3 = new Team("Tony", "Shannon");

        List<Team> teams = List.of(team1, team2, team3);
        int numberOfRounds = 4;

        TeamUtils.generateTeamsScores(teams, numberOfRounds);
        TeamUtils.revealResults1(teams);
        TeamUtils.revealResults2(teams);
    }
}

@Getter
class Team implements Comparable<Team> {
    private final String player1;
    private final String player2;
    private final List<Integer> scores;

    public Team(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
        scores = new ArrayList<>();
    }

    public int sumTotalScore() {
        return scores
                .stream()
                .filter(score -> score > 0)
                .reduce(Integer::sum)
                .orElse(0);
    }

    @Override
    public String toString() {
        String msg = "{0} and {1} scored {2}";
        Object[] values = {
                player1, player2, Integer.toString(sumTotalScore())
        };
        return MessageFormat.format(msg, values);
    }

    @Override
    public int compareTo(Team team) {
        return sumTotalScore() - team.sumTotalScore();
    }
}

class TeamUtils {

    public static void generateTeamsScores(List<Team> teams, int rounds) {
        Random random = new Random();
        teams.forEach(team -> {
            for (int i = 0; i < rounds; i++)
                team.getScores().add(random.nextInt(11));
        });
    }

    public static void revealResults1(List<Team> teams) {
        if (teams.size() == 0 || teams.stream().allMatch(team -> team.getScores().isEmpty())) {
            System.out.println("Not started");
            return;
        }
        Optional<Team> firstWinner = teams.stream().sorted().max(Team::compareTo);
        if (firstWinner.isPresent()) {
            List<Team> winners = teams.stream()
                    .filter((team) -> team.compareTo(firstWinner.get()) == 0)
                    .toList();

            System.out.println("\nFinal scores");
            teams.forEach(team -> System.out.println(team));

            System.out.println("\nWinner" + (winners.size() > 1 ? "s" : ""));
            winners.forEach(winner -> System.out.println(winner));
            System.out.println();
        }
    }

    public static void revealResults2(List<Team> teams) {
        if (teams.size() == 0 || teams.stream().allMatch(team -> team.getScores().isEmpty())) {
            System.out.println("Not started");
            return;
        }

        TreeMap<Integer, List<Team>> scoreTeamsMap = teams
                .stream()
                .collect(Collectors.groupingBy(
                        Team::sumTotalScore,
                        TreeMap::new,
                        Collectors.toList()));

        java.util.Iterator<Integer> scoreResultsIterator = scoreTeamsMap
                .descendingKeySet()
                .stream()
                .iterator();

        System.out.println("Now for the results, the WINNER is...");
        announceResult(scoreResultsIterator.next(), scoreTeamsMap);

        while (scoreResultsIterator.hasNext()) {
            System.out.println("Then we have... ");
            announceResult(scoreResultsIterator.next(), scoreTeamsMap);
        }
    }

    private static void announceResult(int score, TreeMap<Integer, List<Team>> scoreTeamsMap) {
        List<Team> playersWithScore = scoreTeamsMap.get(score);
        if (playersWithScore.size() > 1) {
            System.out.println("It's a TIE!");
        }
        playersWithScore.forEach(team -> System.out.println(team));
        System.out.println();
    }

}