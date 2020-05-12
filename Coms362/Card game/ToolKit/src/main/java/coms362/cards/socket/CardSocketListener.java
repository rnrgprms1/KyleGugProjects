package coms362.cards.socket;

import org.eclipse.jetty.websocket.common.WebSocketSession;

public interface CardSocketListener {
    void onConnect(WebSocketSession session);
    void onReceive(SocketEvent event);
    void setCardSocket(CardSocket cardSocket);
}
