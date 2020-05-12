package coms362.cards.socket;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jetty.util.ajax.JSON;

public class SocketEvent {
    public Map map;
    private int socketId = 0; 

    public SocketEvent(String message, int socketHash) {
    	System.out.println("Creating SocketEvent from json="+message);
    	socketId = socketHash;
    	JSON json = JSON.getDefault();
        try {
            map = (Map) json.parse(message);
        } catch (Exception e) {
            // ignore
        } finally {
            if (map == null) {
                map = new HashMap();
            }
        	System.out.println("Creating socketEvent socket= "+socketHash+ ": "+map);

        }
    }
    
    public SocketEvent(Map<String,String> map, int socketId){
    	this.map = map;
    	this.socketId = socketId;
    }
    
    public int getSocketId(){
    	return socketId;
    }
    
	public String getName(){
    	Object eventObj = map.get("event");
    	return (eventObj == null) ? null: (String ) eventObj;
    }

    public Object get(String key) {
        System.out.println("LOOKING FOR: " + key + " with map " + map);
        return map.get(key);
    }
}
