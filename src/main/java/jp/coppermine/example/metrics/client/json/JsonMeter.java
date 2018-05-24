package jp.coppermine.example.metrics.client.json;

import java.io.Serializable;

import javax.json.bind.annotation.JsonbProperty;

public class JsonMeter implements Serializable {

    /**
     * Serial version UID of this class.
     */
    private static final long serialVersionUID = -560356525020564134L;

    @JsonbProperty
    private long count;
    
    @JsonbProperty
    private double meanRate;
    
    @JsonbProperty
    private double oneMeanRate;
    
    @JsonbProperty
    private double fiveMinRate;
    
    @JsonbProperty
    private double fifteenMinRate;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public double getMeanRate() {
        return meanRate;
    }

    public void setMeanRate(double meanRate) {
        this.meanRate = meanRate;
    }

    public double getOneMeanRate() {
        return oneMeanRate;
    }

    public void setOneMeanRate(double oneMeanRate) {
        this.oneMeanRate = oneMeanRate;
    }

    public double getFiveMinRate() {
        return fiveMinRate;
    }

    public void setFiveMinRate(double fiveMinRate) {
        this.fiveMinRate = fiveMinRate;
    }

    public double getFifteenMinRate() {
        return fifteenMinRate;
    }

    public void setFifteenMinRate(double fifteenMinRate) {
        this.fifteenMinRate = fifteenMinRate;
    }

}
