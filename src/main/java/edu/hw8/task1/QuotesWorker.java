package edu.hw8.task1;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.function.Consumer;
import lombok.SneakyThrows;

public class QuotesWorker implements Runnable {
    private final static int BUFFER_SIZE = 1024;
    private final SocketChannel clientChannel;
    private final ByteBuffer byteBuffer = ByteBuffer.allocate(BUFFER_SIZE);
    private Runnable after;
    private Consumer<String> messageConsumer;

    public QuotesWorker(SocketChannel clientChannel) {
        this.clientChannel = clientChannel;
    }

    @SneakyThrows @Override
    public void run() {
        Selector selector = Selector.open();
        clientChannel.configureBlocking(false);
        clientChannel.register(selector, SelectionKey.OP_READ);
        while (clientChannel.isConnected()) {
            if (selector.selectNow() > 0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isReadable()) {
                        String keyword = readKeywordFromClient();
                        if (keyword == null) {
                            break;
                        }
                        if (messageConsumer != null) {
                            messageConsumer.accept(keyword);
                        }
                        writeQuoteToClient(QuotesStorage.getQuote(keyword));
                    }
                    iterator.remove();
                }
            }
        }
        if (after != null) {
            after.run();
        }
    }

    @SneakyThrows
    private String readKeywordFromClient() {
        try {
            StringBuilder keyword = new StringBuilder();
            int read = clientChannel.read(byteBuffer);
            if (read <= 0) {
                return null;
            }
            while (read > 0) {
                byteBuffer.flip();
                byte[] bytesArray = new byte[byteBuffer.remaining()];
                byteBuffer.get(bytesArray);
                keyword.append(new String(bytesArray, StandardCharsets.UTF_8));
                byteBuffer.clear();
                read = clientChannel.read(byteBuffer);
            }
            return keyword.toString();
        } catch (Exception e) {
            return null;
        }
    }

    @SneakyThrows
    private void writeQuoteToClient(String message) {
        clientChannel.write(ByteBuffer.wrap(message.getBytes(StandardCharsets.UTF_8)));
    }

    public QuotesWorker afterClosing(Runnable after) {
        this.after = after;
        return this;
    }

    public QuotesWorker onMessage(Consumer<String> messageSupplier) {
        this.messageConsumer = messageSupplier;
        return this;
    }
}
