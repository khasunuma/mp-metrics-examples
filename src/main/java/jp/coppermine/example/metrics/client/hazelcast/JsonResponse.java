package jp.coppermine.example.metrics.client.hazelcast;

import javax.json.bind.annotation.JsonbProperty;

public class JsonResponse {

    @JsonbProperty("hazelcast-cluster-members")
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    
}
