package com.dlt.practice.coding_challenges;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DetermineWinnerTest {

    private static final Team team1 = new Team("Sally", "Roger");
    private static final Team team2 = new Team("Eric", "Rebecca");
    private static final Team team3 = new Team("Tony", "Shannon");

    private final ByteArrayOutputStream printOut = new ByteArrayOutputStream();

    @Before
    public void setUpEach() {
        System.setOut(new PrintStream(printOut));
    }

    @After
    public void cleanUpEach() {
        System.setOut(System.out);
        team1.getScores().clear();
        team2.getScores().clear();
        team3.getScores().clear();
    }

    @Test
    public void generateTeamsScores() {
        List<Team> teams = List.of(team1, team2, team3);
        int numberOfRounds = 4;

        TeamUtils.generateTeamsScores(teams, numberOfRounds);

        teams.forEach(team -> {
            List<Integer> scores = team.getScores();
            assertEquals(numberOfRounds, scores.size());
            scores.forEach(score -> assertTrue(score >= 0));
        });
    }

    @Test
    public void generateTeamsScores_emptyTeamsInput() {

        List<Team> teams = List.of();
        TeamUtils.generateTeamsScores(teams, 3);

        assertEquals(0, teams.size());
    }

    @Test
    public void generateTeamsScores_noRounds() {

        List<Team> teams = List.of(team1, team2, team3);
        int numberOfRounds = 0;

        TeamUtils.generateTeamsScores(teams, numberOfRounds);

        teams.forEach(team -> assertEquals(numberOfRounds, team.getScores().size()));
    }

    @Test
    public void revealResults1() {
        team1.getScores().addAll(List.of(2, 4, 5));
        team2.getScores().addAll(List.of(8, 3, 4));
        team3.getScores().addAll(List.of(9, 2, 1));

        List<Team> teams = List.of(team1, team2, team3);
        TeamUtils.revealResults1(teams);

        assertEquals("\nFinal scores\nSally and Roger scored 11\nEric and Rebecca scored 15\n" +
                "Tony and Shannon scored 12\n\nWinner\nEric and Rebecca scored 15\n\n", printOut.toString());
    }

    @Test
    public void revealResults2() {
        team1.getScores().addAll(List.of(2, 4, 5));
        team2.getScores().addAll(List.of(8, 3, 4));
        team3.getScores().addAll(List.of(9, 2, 1));

        List<Team> teams = List.of(team1, team2, team3);
        TeamUtils.revealResults2(teams);

        assertEquals(
                "Now for the results, the WINNER is...\nEric and Rebecca scored 15\n\nThen we have... \nTony and Shannon scored 12\n\nThen we have... \nSally and Roger scored 11\n\n",
                printOut.toString());
    }

    @Test
    public void revealResults1_unevenRounds() {
        team1.getScores().addAll(List.of(2, 5));
        team2.getScores().addAll(List.of(8, 3, 4));
        team3.getScores().addAll(List.of(9));

        List<Team> teams = List.of(team1, team2, team3);
        TeamUtils.revealResults1(teams);

        assertEquals("\nFinal scores\nSally and Roger scored 7\nEric and Rebecca scored 15\n" +
                "Tony and Shannon scored 9\n\nWinner\nEric and Rebecca scored 15\n\n",
                printOut.toString());
    }

    @Test
    public void revealResults2_unevenRounds() {
        team1.getScores().addAll(List.of(2, 5));
        team2.getScores().addAll(List.of(8, 3, 4));
        team3.getScores().addAll(List.of(9));

        List<Team> teams = List.of(team1, team2, team3);
        TeamUtils.revealResults2(teams);

        assertEquals(
                "Now for the results, the WINNER is...\nEric and Rebecca scored 15\n\nThen we have... \n" +
                        "Tony and Shannon scored 9\n\nThen we have... \nSally and Roger scored 7\n\n",
                printOut.toString());
    }

    @Test
    public void revealResults1_noScores() {

        List<Team> teams = List.of(team1, team2, team3);
        TeamUtils.revealResults1(teams);

        assertEquals("Not started\n", printOut.toString());
    }

    @Test
    public void revealResults2_noScores() {

        List<Team> teams = List.of(team1, team2, team3);
        TeamUtils.revealResults2(teams);

        assertEquals("Not started\n", printOut.toString());
    }

    @Test
    public void revealResults1_noTeams() {

        List<Team> teams = List.of();
        TeamUtils.revealResults1(teams);

        assertEquals("Not started\n", printOut.toString());
    }

    @Test
    public void revealResults2_noTeams() {

        List<Team> teams = List.of();
        TeamUtils.revealResults2(teams);

        assertEquals("Not started\n", printOut.toString());
    }

    @Test
    public void revealResults1_tiedWinner() {

        List<Team> teams = List.of(team1, team2, team3);
        team1.getScores().addAll(List.of(3, 3, 3, 3));
        team2.getScores().addAll(List.of(4, 2, 2, 4));
        team3.getScores().addAll(List.of(5, 0, 3, 2));

        TeamUtils.revealResults1(teams);

        assertEquals(
                "\nFinal scores\nSally and Roger scored 12\nEric and Rebecca scored 12\nTony and Shannon scored 10" +
                        "\n\nWinners\nSally and Roger scored 12\nEric and Rebecca scored 12\n\n",
                printOut.toString());
    }

    @Test
    public void revealResults2_tiedWinner() {

        List<Team> teams = List.of(team1, team2, team3);
        team1.getScores().addAll(List.of(3, 3, 3, 3));
        team2.getScores().addAll(List.of(4, 2, 2, 4));
        team3.getScores().addAll(List.of(5, 0, 3, 2));

        TeamUtils.revealResults2(teams);

        assertEquals("Now for the results, the WINNER is...\nIt's a TIE!\nSally and Roger scored 12\n" +
                "Eric and Rebecca scored 12\n\nThen we have... \nTony and Shannon scored 10\n\n",
                printOut.toString());
    }

    @Test
    public void revealResults2_tiedNonWinner() {

        List<Team> teams = List.of(team1, team2, team3);
        team1.getScores().addAll(List.of(3, 4, 2, 5));
        team2.getScores().addAll(List.of(1, 4, 2, 3));
        team3.getScores().addAll(List.of(5, 0, 3, 2));

        TeamUtils.revealResults2(teams);

        assertEquals("Now for the results, the WINNER is...\nSally and Roger scored 14\n\n" +
                "Then we have... \nIt's a TIE!\nEric and Rebecca scored 10\nTony and Shannon scored 10\n\n",
                printOut.toString());
    }

    @Test
    public void revealResults1_tiedNonWinner() {

        List<Team> teams = List.of(team1, team2, team3);
        team1.getScores().addAll(List.of(3, 4, 2, 5));
        team2.getScores().addAll(List.of(1, 4, 2, 3));
        team3.getScores().addAll(List.of(5, 0, 3, 2));

        TeamUtils.revealResults1(teams);

        assertEquals(
                "\nFinal scores\nSally and Roger scored 14\nEric and Rebecca scored 10\n" +
                "Tony and Shannon scored 10\n\nWinner\nSally and Roger scored 14\n\n",
                printOut.toString());
    }
}