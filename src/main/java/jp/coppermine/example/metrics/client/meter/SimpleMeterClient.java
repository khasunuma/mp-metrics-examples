package jp.coppermine.example.metrics.client.meter;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static jp.coppermine.example.metrics.client.Endpoint.APPLICATION_METRICS;
import static jp.coppermine.example.metrics.client.Endpoint.APPLICATION_PATH;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

@Path("meter")
@RequestScoped
public class SimpleMeterClient {

    @GET
    public String getValue() {
        Client client = ClientBuilder.newClient();
        
        client.target(APPLICATION_PATH).path("meter").path("ping").request().get();
        client.target(APPLICATION_PATH).path("meter").path("ping").request().get();
        client.target(APPLICATION_PATH).path("meter").path("ping").request().get();
        
        JsonResponse response = client.target(APPLICATION_METRICS).path("meter").request(APPLICATION_JSON).get(JsonResponse.class);
        
        return String.format("[METRICS] meter.count: %d, meter.meanRate: %f", response.getMeter().getCount(), response.getMeter().getMeanRate());
    }

}
