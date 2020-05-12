package coms362.cards.fiftytwo;

import coms362.cards.abstractcomp.Rules;

/**
 * Single player setting up rules
 */
public class P52SGameFactory extends P52GameFactory {

    @Override
    public Rules createRules() {
        return new P52SRules();
    }
}
