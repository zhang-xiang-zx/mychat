package cn.zhangxiang.mychat.websocket;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhangxiang
 * @date 2024-08-22 11:16
 */
@Slf4j
@ServerEndpoint(value = "/websocket/{userId}")
@Component
public class WebSocket {
    private static ConcurrentHashMap<String, WebSocket> webSocketMap = new ConcurrentHashMap<>();
    private Session session;
    private String userName;

    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userName) {
        this.session = session;
        this.userName = userName;
        String sessionId = session.getId();
        webSocketMap.put(sessionId, this);
        sendMessage(userName + "进入到聊天室", sessionId);
        log.info("【websocket】有新连接：{}", sessionId);
    }

    @OnClose
    public void onClose(@PathParam("userId") Long userId) {
        webSocketMap.remove(userId);
        log.info("【websocket消息】连接断开,总数:{}", webSocketMap.size());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        String sendId = session.getId();
        String uName = webSocketMap.get(sendId).userName;
        System.out.println("【websocket消息】收到"+uName+"发来的消息:" + message);

        webSocketMap.forEach((key, value) -> {
            String id = webSocketMap.get(key).session.getId();
            if (!sendId.equals(id)) {
                String newMsg = uName+"："+message;
                sendMessage(newMsg, key);
            }

        });
    }


    public static void sendMessage(Object msg, String sessionId) {
        WebSocket webSocket = webSocketMap.get(sessionId);
        if (webSocket != null) {
            try {
                webSocket.session.getBasicRemote().sendText(JSON.toJSONString(msg));
                log.info("websocket发送消息成功：用户->{},内容->{}", sessionId, msg);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


}

