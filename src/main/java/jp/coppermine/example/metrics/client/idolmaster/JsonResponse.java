package jp.coppermine.example.metrics.client.idolmaster;

import java.io.Serializable;

import javax.json.bind.annotation.JsonbProperty;

import jp.coppermine.example.metrics.client.json.JsonHistogram;

public class JsonResponse implements Serializable {
    
    /**
     * Serial version UID of this class.
     */
    private static final long serialVersionUID = -7602475916561588402L;
    
    @JsonbProperty("play.combo")
    private JsonHistogram combo;
    
    @JsonbProperty("play.score")
    private JsonHistogram score;

    public JsonHistogram getCombo() {
        return combo;
    }

    public void setCombo(JsonHistogram combo) {
        this.combo = combo;
    }

    public JsonHistogram getScore() {
        return score;
    }

    public void setScore(JsonHistogram score) {
        this.score = score;
    }

}
