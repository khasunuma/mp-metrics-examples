package jp.coppermine.example.metrics.client.meter;

import java.io.Serializable;

import javax.json.bind.annotation.JsonbProperty;

import jp.coppermine.example.metrics.client.json.JsonMeter;

public class JsonResponse implements Serializable {
    
    /**
     * Serial version UID of this class.
     */
    private static final long serialVersionUID = 2158834644360648509L;
    
    @JsonbProperty
    private JsonMeter meter;

    public JsonMeter getMeter() {
        return meter;
    }

    public void setMeter(JsonMeter meter) {
        this.meter = meter;
    }
    
}
