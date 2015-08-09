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
            " \n" +
            " \n" +
            " \n" +
            "                                                                                       ДЮЮЮЮЮЮЮЮЮЮЮЮЮЮЮЮЮЮЮЮЮЮЮЮЮЮЮЮЮЮЮЮЮЮЮЮЮЮЮЮЮЮЮЮЮД!!!!!!!!\n" +
            " \n" +
            " \n" +
            " \n" +
            "                                                                                         You have a lot of possibilities,\n" +
            "                                                                                               for instance, you can:\n" +
            "  ГЛАВНОЕ ЧТО РАБОТАЕТ\n" +
            "                  спасибо =*                                                                                    write text,\n" +
            "                           =)                                                                              read text\n" +
            " \n" +
            " \n" +
            "~~~~~~~~~~\n" +
            " /\\    /\\\n" +
            "|/\\|  |/\\|\n" +
            "         \n" +
            "         \n" +
            "   _____  \n" +
            "  |_____|\n" +
            " \n" +
            "fucked up a bittttttttttt\n" +
            " \n" +
            " ____      _     __________ ____  _  _  ____   ___  \n" +
            "|  _ \\    / \\   |__  / ____|  _ \\| || ||___ \\ / _ \\\n" +
            "| |_) |  / _ \\    / /|  _| | |_) | || |_ __) | | | |                     а          а                                             а\n" +
            "|  _ <  / ___ \\  / /_| |___|  _ <|__   _/ __/| |_| |\n" +
            "|_| \\_\\/_/   \\_\\/____|_____|_| \\_\\  |_||_____|\\___/\n" +
            " \n" +
            " _  _  ____   ___    _  _  ____   ___    _  _  ____   ___    _  _  ____   ___  \n" +
            "| || ||___ \\ / _ \\  | || ||___ \\ / _ \\  | || ||___ \\ / _ \\  | || ||___ \\ / _ \\\n" +
            "| || |_ __) | | | | | || |_ __) | | | | | || |_ __) | | | | | || |_ __) | | | |\n" +
            "|__   _/ __/| |_| | |__   _/ __/| |_| | |__   _/ __/| |_| | |__   _/ __/| |_| |\n" +
            "   |_||_____|\\___/     |_||_____|\\___/     |_||_____|\\___/     |_||_____|\\___/\n" +
            "                                                                               \n" +
            " _  _  ____   ___    _  _  ____   ___    _  _  ____   ___    _  _  ____   ___  \n" +
            "| || ||___ \\ / _ \\  | || ||___ \\ / _ \\  | || ||___ \\ / _ \\  | || ||___ \\ / _ \\\n" +
            "| || |_ __) | | | | | || |_ __) | | | | | || |_ __) | | | | | || |_ __) | | | |\n" +
            "|__   _/ __/| |_| | |__   _/ __/| |_| | |__   _/ __/| |_| | |__   _/ __/| |_| |\n" +
            "   |_||_____|\\___/     |_||_____|\\___/     |_||_____|\\___/     |_||_____|\\___/\n" +
            "                                                                               \n" +
            " _  _  ____   ___    _  _  ____   ___  \n" +
            "| || ||___ \\ / _ \\  | || ||___ \\ / _ \\\n" +
            "| || |_ __) | | | | | || |_ __) | | | |\n" +
            "|__   _/ __/| |_| | |__   _/ __/| |_| |\n" +
            "   |_||_____|\\___/     |_||_____|\\___/\n" +
            "get memed\n" +
            " _  _  ____   ___  \n" +
            "| || ||___ \\ / _ \\\n" +
            "| || |_ __) | | | |   мо\n" +
            "|__   _/ __/| |_| |с        кин            пекc\n" +
            "   |_||_____|\\___/                 лауд\n" +
            " \n" +
            " ____       _           _         __  \n" +
            "|  _ \\ _ __(_)_   _____| |_   ____\\ \\\n" +
            "| |_) | '__| \\ \\ / / _ \\ __| |_____| |        л   л  \n" +
            "|  __/| |  | |\\ V /  __/ |_  |_____| |    АХАХ  о  \n" +
            "|_|   |_|  |_| \\_/ \\___|\\__|       | |\n" +
            "                                  /_/\n" +
            " \n" +
            " \n" +
            "                ~~~~~~~\n" +
            "ваваахах     ~~~~~~~~~~~~~~~~~~~\n" +
            " \n" +
            "            _!_!_!_\n" +
            "          _/       \\_\n" +
            "         / __________\\\n" +
            "         [           ]                                                                   Надо к бабуле сходить и цветы полить и умыться и поесть тупо сделали\n" +
            "         [  | |   [] ]\n" +
            "~~~~~~~~~[  | |  .   ]~~~~~~~~~~~его.Себе!!!!!              У.ДА.ЧИМ.           НИЧЕГО, ОЙ ПРОСТИ =( стер(((((((((ЭТО Я ВЫРЕЗОООООООООООООООООООООООООЛt emedd";
}
