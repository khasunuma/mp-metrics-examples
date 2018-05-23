package jp.coppermine.example.metrics.client.idolmaster;

import java.net.URI;
import java.util.List;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

@Path("client/imas-cg")
public class CinderellaStageScoreClientResource {
    
    private static final URI CONTEXT_ROOT = URI.create("http://localhost:8080/metrics-as-toys");
    private static final URI APPLICATION_METRICS = URI.create("http://localhost:8080/metrics/application");
    
    @GET
    public String importScores() {
        final String json = "[{ \"music\": \"twilight-sky\","
                + "\"combo\": 97,"
                + "\"score\": 627337 },"
                + "{ \"music\": \"orgal-no-kobako\","
                + "\"combo\": 121,"
                + "\"score\": 724057 }]";
        
        Jsonb jsonb = JsonbBuilder.create();
        List<JsonRequest> request = jsonb.fromJson(json, JsonRequest.ListType);
        
        Client client = ClientBuilder.newClient();
        client.target(CONTEXT_ROOT).path("rest").path("imas-cg/score/import").request().post(Entity.entity(request, MediaType.APPLICATION_JSON));
        
        JsonResponse response = client.target(APPLICATION_METRICS).request(MediaType.APPLICATION_JSON).get(JsonResponse.class);
        return String.format("max-combo %d, max-score %d", response.getCombo().getMax(), response.getScore().getMax());
    }
}
