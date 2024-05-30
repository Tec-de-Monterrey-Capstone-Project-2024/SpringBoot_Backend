package com.springboot.connectmate.socket;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.springboot.connectmate.models.ThresholdBreachInsight;
import com.springboot.connectmate.services.SocketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Slf4j
@Component
public class SocketModule {
    private final SocketIOServer server;
    private final SocketService socketService;

    public SocketModule(SocketIOServer server, SocketService socketService){
        this.server = server;
        this.socketService = socketService;

        server.addConnectListener(this.onConnected());
        server.addDisconnectListener(this.onDisconnected());
        server.addEventListener("new_insight", ThresholdBreachInsight.class, this.onChatReceived());
    }

    private DataListener<ThresholdBreachInsight> onChatReceived() {
        return (senderClient, data, ackSender) -> {
            log.info(data.toString());
            socketService.saveInsight(senderClient, data);
        };
    }

    private ConnectListener onConnected() {
        return (client) -> {
            var params = client.getHandshakeData().getUrlParams();
            String room = params.get("connectItemType").stream().collect(Collectors.joining());
            String username = params.get("username").stream().collect(Collectors.joining());

            client.joinRoom(room);
            log.info("Socket ID[{}] - room[{}] - username [{}]  Connected to chat module through",
                    client.getSessionId().toString(),
                    room,
                    username);
        };
    }

    private DisconnectListener onDisconnected() {
        return (client) -> {
            var params = client.getHandshakeData().getUrlParams();
            String room = params.get("connectItemType").stream().collect(Collectors.joining());
            String username = params.get("username").stream().collect(Collectors.joining());

            log.info("Socket ID[{}] - room[{}] - username [{}]  discnnected to chat module through",
                    client.getSessionId().toString(),
                    room,
                    username);
        };
    }
}
