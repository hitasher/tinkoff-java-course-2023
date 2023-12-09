package edu.hw6;

import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public final class Task6 {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final int MIN_PORT = 0;
    private static final int MAX_PORT = 49151;
    private static final String LOG_PATTERN = "%-10s %-7s %s";
    private static final Map<Integer, String> PORT_TO_NAME = Map.of(
        3306, "MySQL Database",
        80, "HTTP",
        443, "HTTPS",
        3722, "Xserve RAID",
        5353, "mDNS"
    );

    private Task6() {
    }

    public static void logBusyPorts() {
        LOGGER.info(LOG_PATTERN.formatted("Протокол", "Порт", "Сервис"));
        for (int currentPort = MIN_PORT; currentPort < MAX_PORT; ++currentPort) {
            String portName = PORT_TO_NAME.getOrDefault(currentPort, "");
            if (isTCPPortBusy(currentPort)) {
                LOGGER.info(String.format(LOG_PATTERN, "TCP", currentPort, portName));
            }
            if (isUDPPortBusy(currentPort)) {
                LOGGER.info(String.format(LOG_PATTERN, "UDP", currentPort, portName));
            }
        }
    }

    private static boolean isTCPPortBusy(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    private static boolean isUDPPortBusy(int port) {
        try (DatagramSocket datagramSocket = new DatagramSocket(port)) {
            return false;
        } catch (Exception e) {
            return true;
        }
    }
}
