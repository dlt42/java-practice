package com.dlt.practice.coding_challenges;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class Joke {
    private String id;
    private String joke;
    private int status;

    @Override
    public String toString() {
        List<String> list = Arrays.asList(id, Integer.toString(status), joke);
        String result = String.join(": ", list);
        return result;
    }
}

/*
 * Calls https://icanhazdadjoke.com/ API and outputs a joke
 * 
 * Example Response:
 * {
 * "id":"ZvzkyXnWLuc",
 * "joke":"What do you call a droid that takes the long way around? R2 detour.",
 * "status":200
 * }
 */

public class JokeAPI {

    private static HttpRequest createRequest() {
        // Create the request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://icanhazdadjoke.com/"))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .header("Accept", "application/json")
                .build();
        return request;
    }

    public static Optional<Joke> theirs(String body) {
        try {
            Joke joke = new Gson().fromJson(body, Joke.class);
            return joke != null ? Optional.of(joke) : Optional.empty();
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public static Optional<Joke> mine(String body) {
        try {
            Joke joke = new ObjectMapper().readValue(body, Joke.class);
            return Optional.of(joke);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public static void main(String[] args) {
        // Create a client and a request
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = createRequest();
        HttpResponse<String> response = null;

        // Send the request and capture the response
        try {
            response = client.send(request, BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Map the response to the Joke class
        Optional<Joke> joke = response == null ? Optional.empty() : mine(response.body());

        // If a joke was retrieved then display it
        joke.ifPresent(System.out::println);

        // Laugh :)
    }
}
