package jp.coppermine.example.metrics.client.histogram;

import java.io.Serializable;

import javax.json.bind.annotation.JsonbProperty;

import jp.coppermine.example.metrics.client.json.JsonHistogram;

public class JsonResponse implements Serializable {

    /**
     * Serial version UID of this class.
     */
    private static final long serialVersionUID = -4722474074749364859L;
    
    @JsonbProperty
    private JsonHistogram histogram;

    public JsonHistogram getHistogram() {
        return histogram;
    }

    public void setHistogram(JsonHistogram histogram) {
        this.histogram = histogram;
    }

}
