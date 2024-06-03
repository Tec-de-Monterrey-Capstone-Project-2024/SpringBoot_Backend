package com.springboot.connectmate.socket;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.springboot.connectmate.dtos.ThresholdBreachInsight.ThresholdBreachInsightGenericDTO;
import com.springboot.connectmate.services.impl.SocketServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Slf4j
@Component
public class SocketModule {
    private final SocketIOServer server;
    private final SocketServiceImpl socketService;

    public SocketModule(SocketIOServer server, SocketServiceImpl socketService){
        this.server = server;
        this.socketService = socketService;

        server.addConnectListener(this.onConnected());
        server.addDisconnectListener(this.onDisconnected());
        server.addEventListener("add_insight", ThresholdBreachInsightGenericDTO.class, this.onChatReceived());
    }

    private DataListener<ThresholdBreachInsightGenericDTO> onChatReceived() {
        return (senderClient, data, ackSender) -> {
            log.info(data.toString());
            senderClient.getNamespace().getBroadcastOperations().sendEvent("add_insight", data);
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

            log.info("Socket ID[{}] - room[{}] - username [{}]  disconnected to chat module through",
                    client.getSessionId().toString(),
                    room,
                    username);
        };
    }
}
