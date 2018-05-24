package jp.coppermine.example.metrics.client.json;

import java.io.Serializable;

import javax.json.bind.annotation.JsonbProperty;

public class JsonHistogram implements Serializable {
        
    /**
     * Serial version UID of this class.
     */
    private static final long serialVersionUID = -3628964777886977411L;

    @JsonbProperty
    private long count;
    
    @JsonbProperty
    private long min;
    
    @JsonbProperty
    private long max;
    
    @JsonbProperty
    private double mean;
    
    @JsonbProperty
    private double stddev;
    
    @JsonbProperty
    private double p50;
    
    @JsonbProperty
    private double p75;
    
    @JsonbProperty
    private double p95;
    
    @JsonbProperty
    private double p98;
    
    @JsonbProperty
    private double p99;
    
    @JsonbProperty
    private double p999;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getMin() {
        return min;
    }

    public void setMin(long min) {
        this.min = min;
    }

    public long getMax() {
        return max;
    }

    public void setMax(long max) {
        this.max = max;
    }

    public double getMean() {
        return mean;
    }

    public void setMean(double mean) {
        this.mean = mean;
    }

    public double getStddev() {
        return stddev;
    }

    public void setStddev(double stddev) {
        this.stddev = stddev;
    }

    public double getP50() {
        return p50;
    }

    public void setP50(double p50) {
        this.p50 = p50;
    }

    public double getP75() {
        return p75;
    }

    public void setP75(double p75) {
        this.p75 = p75;
    }

    public double getP95() {
        return p95;
    }

    public void setP95(double p95) {
        this.p95 = p95;
    }

    public double getP98() {
        return p98;
    }

    public void setP98(double p98) {
        this.p98 = p98;
    }

    public double getP99() {
        return p99;
    }

    public void setP99(double p99) {
        this.p99 = p99;
    }

    public double getP999() {
        return p999;
    }

    public void setP999(double p999) {
        this.p999 = p999;
    }
    
}
