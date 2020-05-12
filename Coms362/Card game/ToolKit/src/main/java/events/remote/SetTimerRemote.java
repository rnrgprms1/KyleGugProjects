package events.remote;

import coms362.cards.streams.Marshalls;
import model.Timer;

public class SetTimerRemote implements Marshalls {
    private Timer timer;

    public SetTimerRemote(Timer timer) {
        this.timer = timer;
    }

    public String marshall() {
        return String.format("cards362.setTimer('%s', '%s', %d);\n",
                timer.getId(), timer.getEvtName(), timer.getTime());
    }

    public String stringify() {
        return "CreateTimer " + timer.getId();
    }
}