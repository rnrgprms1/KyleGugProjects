package coms362.cards.war;

import coms362.cards.abstractcomp.Move;
import coms362.cards.abstractcomp.Player;
import coms362.cards.abstractcomp.Table;
import coms362.cards.app.ViewFacade;
import coms362.cards.fiftytwo.DealButton;
import coms362.cards.fiftytwo.PickupInitCmd;
import coms362.cards.fiftytwo.ResetButton;
import events.remote.*;
import model.Card;
import model.Location;
import model.Pile;

import java.util.Map;
import java.util.Random;

public class WarInit implements Move {

    public Map<Integer, Player> players;
    String title = "War Game   ";
    Pile player1Pile;
    Pile player2Pile;

    public WarInit(Map<Integer, Player> players) {
        this.players = players;
        player1Pile = new Pile("player1Pile", new Location(250,100));
        player2Pile = new Pile("player2Pile", new Location(250,500));
    }



    public void apply(Table table){
        Random random = table.getRandom();
        Pile temp = new Pile("temp", null);
        try {
            for (String suit : Card.suits) {
                for (int i = 1; i <= 13; i++) {
                    Card card = new Card();
                    card.setSuit(suit);
                    card.setNumber(i);
                    temp.cards.put(card.getId(), card);
                }
            }
            Boolean[] arr = new Boolean[52];
            for(int i = 0; i<52; i++){
                arr[i] = true;
            }
            Card tempCard;
            int rand;
            Random random2 = new Random();
            for(int i = 0; i<26; i++){
                rand = random2.nextInt(52);
                while(!arr[rand]){
                    rand = random2.nextInt(52);
                }
                if(arr[rand]){
                    tempCard = temp.cards.get(rand+1);
                    tempCard.setX(250);
                    tempCard.setY(100);
                    tempCard.setFaceUp(false);
                    player1Pile.cards.put(tempCard.getId(), tempCard);
                    arr[rand] = false;
                }
            }
            for(int i = 0; i<26; i++){
                rand = random2.nextInt(52);
                while(!arr[rand]){
                    rand = random2.nextInt(52);
                }
                if(arr[rand]){
                    tempCard = temp.cards.get(rand+1);
                    tempCard.setX(250);
                    tempCard.setY(500);
                    tempCard.setFaceUp(false);
                    player2Pile.cards.put(tempCard.getId(), tempCard);
                    arr[rand] = false;
                }
            }
            table.addPile(player1Pile);
            table.addPile(player2Pile);
            table.addPile(new Pile("player1Show", new Location(250, 200)));
            table.addPile(new Pile("player2Show", new Location(250, 400)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void apply(ViewFacade view) {
        view.send(new SetupTable());
        view.send(new SetGameTitleRemote(title));

        for (Player p : players.values()){
            String role = "Player "+p.getPlayerNum();
            view.send(new SetBottomPlayerTextRemote(role, p));
        }

        String id = "";
        DealButton dealButton = new DealButton("DEAL", new Location(0, 0));
        view.register(dealButton); //so we can find it later.
        view.send(new CreateButtonRemote(dealButton));
        //view.send(new CreateButtonRemote(Integer.toString(getNextId()), "reset", "RestartGame", "Reset", new Location(500,0)));
        //view.send(new CreateButtonRemote(Integer.toString(getNextId()), "clear", "ClearTable", "Clear Table", new Location(500,0)));
    }
}
