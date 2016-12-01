package webSocket;

/**
 * Created by zhaobofan on 16/12/1.
 */

import org.slf4j.Logger;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value="/hello",configurator=GetHttpSessionConfigurator.class)
public class WebsocketTest {
    private static final Set<WebsocketTest> onlineUsers =
            new CopyOnWriteArraySet<WebsocketTest>();
    private String userName;
    private Session session;
    private HttpSession httpSession;

    public WebsocketTest() {
        System.out.println("WebsocketTest..");
    }

    @OnOpen
    public void onopen(Session session, EndpointConfig config) {
        System.out.println("连接成功");
        try {
            session.getBasicRemote().sendText("hello client...");
            while (true){
                session.getBasicRemote().sendText("Hi,Hi,Hi...");
                Thread.sleep(8000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        this.session = session;
        this.httpSession = (HttpSession) config.getUserProperties()
                .get(HttpSession.class.getName());
        this.userName=(String) httpSession.getAttribute("userName");
        onlineUsers.add(this);
        String message = String.format("* %s %s", userName, " from websocket 上线了...");
        broadcast(message);
    }

    @OnClose
    public void onclose(Session session) {
        System.out.println("close....");

    }

    @OnMessage
    public void onsend(Session session, String msg) {
        try {
            broadcast(msg);
            session.getBasicRemote().sendText("哇client" + session.getId() + "say:" + msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void broadcast(String msg) {
        for (WebsocketTest client : onlineUsers) {
            try {
                synchronized (client) {
                    client.session.getBasicRemote().sendText(msg);
                }
            } catch (IOException e) {
                onlineUsers.remove(client);
                try {
                    client.session.close();
                } catch (IOException e1) {
                    // Ignore
                }
                String message = String.format("* %s %s",
                        client.userName, " from websocket 已经离开...");
                broadcast(message);
            }
        }
    }

    @OnError
    public void onError(Throwable t) throws Throwable {

    }

}