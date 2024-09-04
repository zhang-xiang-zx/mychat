package cn.zhangxiang.mychat.websocket;

import cn.zhangxiang.mychat.config.exception.MyException;
import cn.zhangxiang.mychat.pojo.dto.LoginRoomDTO;
import cn.zhangxiang.mychat.pojo.entity.User;
import cn.zhangxiang.mychat.service.OnlineUserRoomService;
import cn.zhangxiang.mychat.service.UserService;
import cn.zhangxiang.mychat.utils.Assert;
import cn.zhangxiang.mychat.utils.JwtUtils;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author zhangxiang
 * @date 2024-08-22 11:16
 */
@Slf4j
@ServerEndpoint(value = "/websocket/{userInfo}")
@Component
public class WebSocket {
    private static ConcurrentHashMap<String, Session> webSocketMap = new ConcurrentHashMap<>();

    private static UserService userService;

    private static OnlineUserRoomService onlineUserRoomService;

    // 这样就能解决只注册一次问题
    @Autowired
    public void setUserService(UserService userService){
        WebSocket.userService = userService;
    }

    @Autowired
    public void setOnlineUserRoomService(OnlineUserRoomService onlineUserRoomService){
        WebSocket.onlineUserRoomService = onlineUserRoomService;
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("userInfo") String userInfo) {
        userInfo = "{" + userInfo + "}";
        LoginRoomDTO loginRoomInfo = JSON.parseObject(userInfo, LoginRoomDTO.class);
        String authorization = loginRoomInfo.getAuthorization();
        Long roomId = loginRoomInfo.getRoomId();

        String userId = JwtUtils.getAudience(authorization);
        Boolean isOK = JwtUtils.verifyToken(authorization, userId);

        webSocketMap.put(userId, session);
        if (!isOK) {
            log.info("验证错误");
            sendMessage("5001","token验证错误",userId);
            webSocketMap.remove(userId);
        }

    }

    //    public void onClose(@PathParam("userId") Long userId)
    @OnClose
    public void onClose(Session session) {
        String userId = webSocketMap.entrySet().stream()
                .filter(entry -> entry.getValue().equals(session))
                .findFirst().get().getKey();
        User user = userService.selectUserById(userId);
        log.info("{}断开连接了",user.getNickName());
        webSocketMap.remove(userId);
        log.info("【websocket消息】连接断开,总数:{}", webSocketMap.size());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        String userId = webSocketMap.entrySet().stream()
                .filter(entry -> entry.getValue().equals(session))
                .findFirst().get().getKey();

        User user = userService.selectUserById(userId);
        log.info("【websocket消息】收到" + user.getNickName() + "发来的消息:" + message);
        List<Long> userIdList = onlineUserRoomService.selectRoomUserByUserId(Long.valueOf(userId));
        if(Assert.notEmpty(userIdList)){
            log.info("在用一聊天室的用户有：{}",userIdList);
            for(Long uid : userIdList){
                String onUserId = webSocketMap.entrySet().stream()
                        .filter(entry -> entry.getKey().equals(String.valueOf(uid)))
                        .findFirst().get().getKey();
                sendMessage("200",message,onUserId);
            }
        }else {
            log.info("无用户在用一聊天室");
        }

    }

//    @OnError
//    public void onError(Session session, Throwable throwable) {
//        log.info("进到错误中");
//        String errorMessage = "{\"type\": \"ERROR\", \"message\": \"" + throwable.getMessage() + "\"}";
//        try {
//            session.getBasicRemote().sendText(errorMessage);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                session.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//    }

    public static void sendMessage(String code, Object msg, String userId) {
        Session webSocketSession = webSocketMap.get(userId);
        if (webSocketSession != null) {
            try {
                String resultMessage = "{\"code\": \"" + code + "\", \"message\": \"" + msg + "\"}";
//                String resultMessage = "{type:" + type + ",message:" + msg + "}";
//                String resultMessage = "\"type\":" + type + ",\"message\":" + msg;
//                webSocket.session.getBasicRemote().sendText(JSON.toJSONString(resultMessage));
                webSocketSession.getBasicRemote().sendText(resultMessage);
                log.info("websocket发送消息成功：用户->{},内容->{}", userId, resultMessage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


}

