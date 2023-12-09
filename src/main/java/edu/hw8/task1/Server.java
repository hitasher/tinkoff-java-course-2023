package edu.hw8.task1;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.function.Consumer;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Server {
    private static final Logger LOGGER = LogManager.getLogger();
    private final int port;
    private final ExecutorService executorService;
    private final Semaphore semaphore;
    private Consumer<String> messageConsumer;
    private ServerSocketChannel channel;

    public Server(int port, int maximumConnections) {
        this.port = port;
        this.semaphore = new Semaphore(maximumConnections);
        this.executorService = Executors.newFixedThreadPool(maximumConnections);
        this.messageConsumer = message -> LOGGER.info("Ваня: {}", message);
    }

    @SneakyThrows
    public void start() {
        try (ServerSocketChannel channel = ServerSocketChannel.open()) {
            this.channel = channel;
            Selector selector = Selector.open();
            channel.configureBlocking(false);
            channel.register(selector, SelectionKey.OP_ACCEPT);
            channel.bind(new InetSocketAddress(port));
            processConnections(channel, selector);
        }
    }

    @SneakyThrows
    public void stop() {
        channel.close();
        executorService.shutdown();
    }

    public void setMessageConsumer(Consumer<String> messageConsumer) {
        this.messageConsumer = messageConsumer;
    }

    @SneakyThrows
    private void processConnections(ServerSocketChannel channel, Selector selector) {
        while (channel.isOpen()) {
            if (selector.selectNow() > 0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isAcceptable() && semaphore.tryAcquire()) {
                        acceptRequestFrom(channel);
                        iterator.remove();
                    }
                }
            }
        }
    }

    @SneakyThrows
    private void acceptRequestFrom(ServerSocketChannel channel) {
        SocketChannel clientChannel = channel.accept();
        executorService.execute(new QuotesWorker(clientChannel)
            .afterClosing(semaphore::release)
            .onMessage(messageConsumer)
        );
    }
}
