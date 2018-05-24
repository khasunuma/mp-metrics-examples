package jp.coppermine.example.metrics.client.gauge;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static jp.coppermine.example.metrics.client.Endpoint.APPLICATION_METRICS;
import static jp.coppermine.example.metrics.client.Endpoint.APPLICATION_PATH;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

@Path("gauge")
@RequestScoped
public class SimpleGaugeClient {

    @GET
    public String getValue() {
        Client client = ClientBuilder.newClient();
        
        client.target(APPLICATION_PATH).path("gauge").path("value").request().get();
        
        JsonResponse responseLong = client.target(APPLICATION_METRICS).path("gauge").request(APPLICATION_JSON).get(JsonResponse.class);
        
        return String.format("[METRICS] gauge: %d", responseLong.getGauge());
    }

}
