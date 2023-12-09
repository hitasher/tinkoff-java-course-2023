package edu.hw8.task1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class TestQuotes {

    private final static String HOST = "localhost";
    private final static int PORT = 8080;
    private final static int MAXIMUM_CONNECTIONS = 4;

    private final static int NUMBER_OF_REQUESTS = 1000;

    @SneakyThrows
    @Test
    public void quotes_shouldCorrectlyWork() {
        List<String> keywords = new ArrayList<>();
        List<String> actualQuotes = new ArrayList<>();
        Server server = new Server(PORT, MAXIMUM_CONNECTIONS);

        server.setMessageConsumer(keywords::add);
        Executors.newSingleThreadExecutor().execute(server::start);

        Thread.sleep(100);

        Client client = new Client(HOST, PORT);
        client.start();
        for (int i = 0; i < NUMBER_OF_REQUESTS; ++i) {
            actualQuotes.add(client.getQuoteFromServer(QuotesStorage.getRandomKeyword()));
        }

        Thread.sleep(100);
        client.stop();
        server.stop();

        List<String> expectedQuotes = keywords.stream().map(QuotesStorage::getQuote).toList();
        assertThat(actualQuotes).isEqualTo(expectedQuotes);
    }

}
