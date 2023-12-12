package com.dlt.practice.coding_challenges;

import java.util.Scanner;

public class DoubleOrNothing {
    private int points = 10;
    private boolean playing = true;
    private Scanner scanner = new Scanner(System.in);

    private void doublePoints() {
        this.points *= 2;
    }

    private void outputScore() {
        System.out.println("You have " + this.points + " points");
    }

    private void outputQuestion() {
        System.out.println("Do you want to (P)lay or (T)ake points?");
    }

    private void outputInvalidCommandError() {
        System.out.println("Invalid command");
    }

    private void ouputSuccess() {
        System.out.println("Your points have doubled!");
    }

    private void ouputFailure() {
        System.out.println("You lost!");
    }

    private void ouputResult() {
        System.out.println("You have won " + this.points + " points");
    }

    private boolean askToContinue() {

        outputScore();
        boolean validInput = false;
        boolean result = false;
        while (!validInput) {
            outputQuestion();
            String choice = this.scanner.nextLine();
            if ("T".equals(choice)) {
                validInput = true;
                result = false;
                continue;
            }
            if ("P".equals(choice)) {
                validInput = true;
                result = true;
                continue;
            }
            outputInvalidCommandError();
        }

        return result;
    }

    private boolean shouldDoublePoints() {
        return Math.random() > .5;
    }

    private void clearPoints() {
        this.points = 0;
    }

    private void stopPlaying() {
        this.playing = false;
    }

    private boolean isPlaying() {
        return this.playing;
    }

    public void playGame() {
        while (isPlaying()) {
            if (!askToContinue()) {
                stopPlaying();
            } else if (shouldDoublePoints()) {
                ouputSuccess();
                doublePoints();
            } else {
                ouputFailure();
                clearPoints();
                stopPlaying();
            }
        }
        ouputResult();
    }

    public static void main(String[] args) {
        DoubleOrNothing game = new DoubleOrNothing();
        game.playGame();
    }
}
