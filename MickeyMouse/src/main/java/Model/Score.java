package Model;

import java.io.Serial;
import java.io.Serializable;

public class Score implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String nickname;
    private int highscore;

    public Score(String nickname, int highscore) {
        this.nickname = nickname;
        this.highscore = highscore;
    }

    public int getHighscore() {
        return highscore;
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }

    public String string() {
        return nickname + " " + highscore;
    }
}
