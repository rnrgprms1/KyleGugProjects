package socket;

import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.OnClose;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/chat/{username}")
public class websocket {
	
	 private static Map<Session, String> sessionUsernameMap = new HashMap<>();
	    private static Map<String, Session> usernameSessionMap = new HashMap<>();
	    private final Logger logger = LoggerFactory.getLogger(websocket.class);

@OnOpen
public void onOpen(Session session, String username) throws IOException {
	logger.info("Open");
    
    sessionUsernameMap.put(session, username);
    usernameSessionMap.put(username, session);
    
    String message="User:" + username ;
    	broadcast(message);
}
@OnMessage
public void onMessage(Session session, String chat) throws IOException {
	logger.info("Chat: :"+chat);
	String username = sessionUsernameMap.get(session);
	
	if (chat.startsWith("@")) 
	{
		sendMessageToPArticularUser(username,  username + ": " + chat);
	}
	else 
	{
    	broadcast(username + ": " + chat);
	}
}
@OnClose
public void onClose(Session session) throws IOException {
	logger.info("Close");
	
	String username = sessionUsernameMap.get(session);
	sessionUsernameMap.remove(session);
	usernameSessionMap.remove(username);
    
	String chat= username + " disconnected";
    broadcast(chat);
}
@OnError
public void onError(Session session, Throwable throwable) {
	logger.info("Error");
	}
private void sendMessageToPArticularUser(String username, String message) 
{	
	try {
		usernameSessionMap.get(username).getBasicRemote().sendText(message);
    } catch (IOException e) {
    	logger.info("Exception: " + e.getMessage().toString());
        e.printStackTrace();
    }
}
private static void broadcast(String chat) 
	      throws IOException 
{	  
	sessionUsernameMap.forEach((session, username) -> {
		synchronized (session) {
          try {
              session.getBasicRemote().sendText(chat);
          } catch (IOException e) {
              e.printStackTrace();
          }
      }
  });
}

}