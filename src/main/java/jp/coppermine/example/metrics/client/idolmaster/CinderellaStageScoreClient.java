package jp.coppermine.example.metrics.client.idolmaster;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static jp.coppermine.example.metrics.client.Endpoint.APPLICATION_METRICS;
import static jp.coppermine.example.metrics.client.Endpoint.APPLICATION_PATH;

import java.io.StringReader;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

@Path("imas-cg")
public class CinderellaStageScoreClient {

    @GET
    public String getAll() {
        Jsonb jsonb = JsonbBuilder.create();
        List<JsonRequest> request = jsonb.fromJson(getClass().getResourceAsStream("/imas-cg-score.json"), JsonRequest.ListType);
        
        Client client = ClientBuilder.newClient();
        client.target(APPLICATION_PATH).path("imas-cg/stage/result/import").request().post(Entity.entity(request, APPLICATION_JSON));
        
        JsonResponse response = client.target(APPLICATION_METRICS).request(MediaType.APPLICATION_JSON).get(JsonResponse.class);
        return String.format("[METRICS] play.combo.mean: %5.2f, play.score.mean: %9.2f", response.getCombo().getMean(), response.getScore().getMean());
    }

    @GET
    @Path("{music}")
    public String get(@PathParam("music") @DefaultValue("play") String music) {
        Jsonb jsonb = JsonbBuilder.create();
        List<JsonRequest> request = jsonb.fromJson(getClass().getResourceAsStream("/imas-cg-score.json"), JsonRequest.ListType);
        
        Client client = ClientBuilder.newClient();
        client.target(APPLICATION_PATH).path("imas-cg/stage/all-result/import").request().post(Entity.entity(request, APPLICATION_JSON));
        
        String text = client.target(APPLICATION_METRICS).request(MediaType.APPLICATION_JSON).get(String.class);
        JsonObject jsonObject = Json.createReader(new StringReader(text)).readObject();
        JsonObject combo = jsonObject.getJsonObject(music + ".combo");
        JsonObject score = jsonObject.getJsonObject(music + ".score");
        return String.format("[METRICS] %s.combo.mean: %5.2f, %s.score.mean: %9.2f", music, combo.getJsonNumber("mean").doubleValue(), music, score.getJsonNumber("mean").doubleValue());
    }
    
}
