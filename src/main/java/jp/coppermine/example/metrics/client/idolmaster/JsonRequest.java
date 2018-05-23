package jp.coppermine.example.metrics.client.idolmaster;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class JsonRequest implements Serializable {
    
    /**
     * Serial version UID of this class.
     */
    private static final long serialVersionUID = -5706391959832846148L;
    
    public static Type ListType = new ArrayList<JsonRequest>() {
        private static final long serialVersionUID = 1L;
    }.getClass().getGenericSuperclass();
    
    private String music;
    
    private int combo;
    
    private int score;

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public int getCombo() {
        return combo;
    }

    public void setCombo(int combo) {
        this.combo = combo;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return String.format("%s: combo %d, score %d", music, combo, score);
    }
    
}
