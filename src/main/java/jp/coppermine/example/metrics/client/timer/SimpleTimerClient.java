package jp.coppermine.example.metrics.client.timer;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static jp.coppermine.example.metrics.client.Endpoint.APPLICATION_METRICS;
import static jp.coppermine.example.metrics.client.Endpoint.APPLICATION_PATH;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

@Path("timer")
@RequestScoped
public class SimpleTimerClient {
    
    @GET
    public String getValue() {
        Client client = ClientBuilder.newClient();
        
        client.target(APPLICATION_PATH).path("timer").request().get();
        client.target(APPLICATION_PATH).path("timer").request().get();
        client.target(APPLICATION_PATH).path("timer").request().get();
        
        JsonResponse response = client.target(APPLICATION_METRICS).path("timer").request(APPLICATION_JSON).get(JsonResponse.class);
        
        return String.format("[METRICS] timer.count: %d, timer.meanRate: %f", response.getTimer().getCount(), response.getTimer().getMeanRate());
    }

}
