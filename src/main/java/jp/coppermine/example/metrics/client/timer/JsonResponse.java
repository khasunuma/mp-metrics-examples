package jp.coppermine.example.metrics.client.timer;

import java.io.Serializable;

import javax.json.bind.annotation.JsonbProperty;

import jp.coppermine.example.metrics.client.json.JsonTimer;

public class JsonResponse implements Serializable {

    /**
     * Serial version UID of this class.
     */
    private static final long serialVersionUID = -7957479740262101519L;

    @JsonbProperty
    private JsonTimer timer;

    public JsonTimer getTimer() {
        return timer;
    }

    public void setTimer(JsonTimer timer) {
        this.timer = timer;
    }

}
