package logic;

import event.TextChangeHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by username on 8/8/15.
 */
public class TextService {
    List<TextChangeHandler> textChangeHandlers = new ArrayList<TextChangeHandler>();
    public String getText() {
        return textData;
    }
    public void onTextChange(TextChangeHandler textChangeHandler) {
        this.textChangeHandlers.add(textChangeHandler);
    }

    public void setText(String text) {
        textData = text;
        triggerTextChange(textData);
    }

    private void triggerTextChange(String text) {
        for (TextChangeHandler textChangeHandler : textChangeHandlers) {
            textChangeHandler.onTextChange(text);
        }
    }

    private static TextService instance = new TextService();
    public static TextService getInstance() {
        return instance;
    }
    private TextService() {}

    private String textData = "<----------------------------------------------------------------SYNCED AREA------------------------------------------------------------------------------->\n" +
            "ON\n" +
            "        THIS\n" +
            "   AREA\n" +
            "                               EVE TH              SYNCRONIZED!!!!!!!111\n" +
            "                                     ING    IS\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "                                                                                         You have a lot of possibilities,\n" +
            "                                                                                               for instance, you can:\n" +
            "                                                                                                         write text,\n" +
            "                                                                                                         read text\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "            _______\n" +
            "          _/       \\_\n" +
            "         / __________\\\n" +
            "         [           ]\n" +
            "         [  | |   [] ]\n" +
            "~~~~~~~~~[  | |      ]~~~~~~~~~~~";
}
