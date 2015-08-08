package eei.cornerseees.client.service;

import eei.cornerseees.client.event.ActionHandler;
import eei.cornerseees.client.event.TextChangeHandler;

/**
 * Created by username on 8/8/15.
 */
public interface TextStream {
    void save(String text);
    void onChange(TextChangeHandler textChangeHandler);
}
