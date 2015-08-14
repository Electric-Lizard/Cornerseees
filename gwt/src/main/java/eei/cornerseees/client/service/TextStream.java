package eei.cornerseees.client.service;

import eei.cornerseees.client.event.TextChangeHandler;
import eei.cornerseees.shared.model.Lizard;

/**
 * Created by username on 8/8/15.
 */
public interface TextStream {
    void save(String text);
    void onChange(TextChangeHandler textChangeHandler);
    void sendLizard(Lizard lizard);
}
