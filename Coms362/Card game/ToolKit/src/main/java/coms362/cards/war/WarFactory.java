package coms362.cards.war;

import coms362.cards.abstractcomp.Rules;
import coms362.cards.fiftytwo.P52GameFactory;

public class WarFactory extends P52GameFactory {

    /**
     * @author Daniel Seeger
     */
    @Override
    public Rules createRules() {
        return new WarRules();
    }

}
