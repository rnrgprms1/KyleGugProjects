package coms362.cards.war;

import coms362.cards.abstractcomp.Move;
import coms362.cards.abstractcomp.Player;
import coms362.cards.abstractcomp.Table;
import coms362.cards.app.ViewFacade;
import events.remote.*;
import model.Card;
import model.Location;
import model.Pile;

import java.sql.SQLOutput;


//Clicks card from deck and puts in middle
//boolean values to check if player posted or not

/**
 * @author Hyegeun Gug
 */
public class WarCardMove implements Move {
    private Card c;
    private Player p;
    private boolean p1Turn = false;
    private boolean p2Turn = false;
    private Card p1Card;
    private Card p2Card;
    private int winner = 0;
    public WarCardMove(Card c, Player p){
        this.c = c;
        this.p = p;
    }
    //card move center
    @Override
    public void apply(Table table) {
        if(table.getPile("player1Pile").cards.size()==52||table.getPile("player1Pile").cards.size()==0||table.getPile("player2Pile").cards.size()==52||table.getPile("player1Pile").cards.size()==0){
            table.setMatchOver(true);
            System.out.println("Match is over");
        }
        if(table.checkPlayerTurn(1) && table.checkPlayerTurn(2)){
            table.setPlayerTurn(1, false);
            table.setPlayerTurn(2, false);
            p1Card = (Card) table.getPile("player1Show").cards.values().toArray()[0];
            p2Card = (Card) table.getPile("player2Show").cards.values().toArray()[0];
            table.getPile("player1Pile").cards.remove(p1Card.getId());
            table.getPile("player1Show").cards.remove(p1Card.getId());
            table.getPile("player2Pile").cards.remove(p2Card.getId());
            table.getPile("player2Show").cards.remove(p2Card.getId());
            if(p1Card.getNumber() > p2Card.getNumber()){
                winner = 1;
                table.getPile("player1Pile").addCard(p1Card);
                table.getPile("player1Pile").addCard(p2Card);
                p1Card.setY(100);
                p2Card.setY(100);
            }
            else if(p1Card.getNumber() < p2Card.getNumber()){
                winner = 2;
                table.getPile("player2Pile").addCard(p1Card);
                table.getPile("player2Pile").addCard(p2Card);
                p1Card.setY(400);
                p2Card.setY(400);
            }
            else if(p1Card.getNumber() %2 ==0){
                winner = 2;
                table.getPile("player2Pile").addCard(p1Card);
                table.getPile("player2Pile").addCard(p2Card);
                p1Card.setY(400);
                p2Card.setY(400);
            }
            else{
                winner = 1;
                table.getPile("player1Pile").addCard(p1Card);
                table.getPile("player1Pile").addCard(p2Card);
                p1Card.setY(100);
                p2Card.setY(100);
            }
        }
        else if(!table.checkPlayerTurn(p.getPlayerNum())){
            if(p.getPlayerNum()==1){
                c.setX(250);
                c.setY(200);
                table.getPile("player1Show").addCard(c);
                table.setPlayerTurn(1, true);
                p1Turn = true;
            }
            if(p.getPlayerNum()==2){
                c.setX(250);
                c.setY(400);
                table.getPile("player2Show").addCard(c);
                table.setPlayerTurn(2, true);
                p2Turn = true;
            }

        }
        System.out.println(table.getPile("player1Pile").cards.size());
        System.out.println(table.getPile("player2Pile").cards.size());

    }

    @Override
    public void apply(ViewFacade views) {
        try {
            if (winner != 0) {
                if (winner == 1) {
                    views.send(new InsertAtPileTopRemote("player1Pile", p1Card));
                    views.send(new InsertAtPileTopRemote("player1Pile", p2Card));
                } else {
                    views.send(new InsertAtPileTopRemote("player2Pile", p1Card));
                    views.send(new InsertAtPileTopRemote("player2Pile", p2Card));
                }
            }
            if (p.getPlayerNum() == 1) {
                if (p1Turn) {
                    views.send(new RemoveFromPileRemote("player1Pile", c));
                    views.send(new ShowCardRemote(c));
                    views.send(new UpdateRemote(c));
                }
            } else {
                if (p2Turn) {

                    views.send(new RemoveFromPileRemote("player2Pile", c));
                    views.send(new ShowCardRemote(c));
                    views.send(new UpdateRemote(c));
                }
            }

        }
        catch (Exception e){}



    }



}
