package edu.hw6;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Task5 {

    private static final String TOP_STORIES_ENDPOINT = "https://hacker-news.firebaseio.com/v0/topstories.json";
    private static final String NEWS_ENDPOINT_BY_ID = "https://hacker-news.firebaseio.com/v0/item/%d.json";
    private static final Pattern TITLE_PATTERN = Pattern.compile(".*\"title\":\"([^\"]*)\".*");

    private Task5() {
    }

    public static long[] hackerNewsTopStories() {
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpResponse<String> response = client.send(
                HttpRequest
                    .newBuilder()
                    .uri(URI.create(TOP_STORIES_ENDPOINT))
                    .build(),
                HttpResponse
                    .BodyHandlers
                    .ofString()
            );
            String responseString = response.body();
            responseString = responseString.substring(1, responseString.length() - 1);
            return Arrays.stream(responseString.split(",")).mapToLong(Long::parseLong).toArray();
        } catch (Exception e) {
            return new long[] {};
        }
    }

    public static String news(long id) {
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpResponse<String> response = client.send(
                HttpRequest
                    .newBuilder()
                    .uri(URI.create(String.format(NEWS_ENDPOINT_BY_ID, id)))
                    .build(),
                HttpResponse
                    .BodyHandlers
                    .ofString()
            );
            String responseString = response.body();
            Matcher matcher = TITLE_PATTERN.matcher(responseString);
            if (matcher.matches()) {
                return matcher.group(1);
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}
