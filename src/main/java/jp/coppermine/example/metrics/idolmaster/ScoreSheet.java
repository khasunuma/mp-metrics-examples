package jp.coppermine.example.metrics.idolmaster;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(FIELD)
public class ScoreSheet implements Serializable {
    
    /**
     * Serial version UID of this class.
     */
    private static final long serialVersionUID = 3877145379099377088L;

    @XmlElement
    private String music;
    
    @XmlElement
    private int combo;
    
    @XmlElement
    private int score;
    
    public ScoreSheet() {
        
    }
    
    public ScoreSheet(String music, int combo, int score) {
        this.music = music;
        this.combo = combo;
        this.score = score;
    }

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
        return String.format("%s: %d combo, score %d points", music, combo, score);
    }

}
