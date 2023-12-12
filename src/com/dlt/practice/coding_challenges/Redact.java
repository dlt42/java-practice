package com.dlt.practice.coding_challenges;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Redact {
    private String original;
    private String redacted;

    private String filename;

    public Redact(String filename) {
        this.filename = filename;
    }

    public void now(List<String> wordsToRedact) {
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            br = new BufferedReader(new FileReader(new File(filename)));
            bw = new BufferedWriter(new FileWriter(new File(filename + ".redacted")));
            String st;

            // Read each line
            while ((st = br.readLine()) != null) {

                // Redact the words to redact in the line
                String redacted = wordsToRedact.stream()
                        .reduce(st, (updated, wordToRedact) -> {
                            // Create the regex and ensure the word to redact is not interpreted as part of
                            // the regex.
                            String regex = "(?i)" + Pattern.quote(wordToRedact);

                            // Replace all instances of the word to be redacted with the word 'REDACTED'
                            return updated.replaceAll(regex, "REDACTED");
                        });

                // Write the redacted line
                bw.append(redacted).append("\n");
            }
            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Redact("test.txt")
                .now(Arrays.asList("redaction"));
    }
}
