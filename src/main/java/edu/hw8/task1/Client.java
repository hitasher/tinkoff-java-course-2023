package edu.hw8.task1;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import lombok.SneakyThrows;

public class Client {
    private final static int BUFFER_SIZE = 1024;
    private final String host;
    private final int port;
    private final ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
    private SocketChannel channel;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @SneakyThrows
    public void start() {
        channel = SocketChannel.open(new InetSocketAddress(host, port));
        channel.configureBlocking(false);
    }

    @SneakyThrows
    public void stop() {
        channel.close();
    }

    @SneakyThrows
    public String getQuoteFromServer(String keyword) {
        channel.write(ByteBuffer.wrap(keyword.getBytes(StandardCharsets.UTF_8)));
        buffer.clear();
        StringBuilder quote = new StringBuilder();
        while (channel.read(buffer) > 0 || quote.isEmpty()) {
            buffer.flip();
            byte[] bytes = new byte[buffer.remaining()];
            buffer.get(bytes);
            quote.append(new String(bytes, StandardCharsets.UTF_8));
            buffer.clear();
        }
        return quote.toString();
    }
}
