package jp.coppermine.example.metrics.client.gauge;

import java.io.Serializable;

import javax.json.bind.annotation.JsonbProperty;

public class JsonResponse implements Serializable {

    /**
     * Serial version UID of this class.
     */
    private static final long serialVersionUID = 3848288526497873347L;

    @JsonbProperty("gauge")
    private long gauge;

    public long getGauge() {
        return gauge;
    }

    public void setGauge(long gauge) {
        this.gauge = gauge;
    }

}
