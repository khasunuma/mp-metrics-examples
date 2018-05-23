package jp.coppermine.example.metrics.client.counter;

import javax.json.bind.annotation.JsonbProperty;

public class JsonResponse {

    @JsonbProperty("counter")
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
