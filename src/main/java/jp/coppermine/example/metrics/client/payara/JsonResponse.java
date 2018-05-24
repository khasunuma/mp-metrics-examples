package jp.coppermine.example.metrics.client.payara;

import javax.json.bind.annotation.JsonbProperty;

public class JsonResponse {

    @JsonbProperty("payara.data-grid.members")
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    
}
