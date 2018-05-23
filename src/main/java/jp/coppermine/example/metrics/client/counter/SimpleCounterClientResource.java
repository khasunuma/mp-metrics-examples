package jp.coppermine.example.metrics.client.counter;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.net.URI;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

@Path("client/counter")
@RequestScoped
public class SimpleCounterClientResource {
    
    private static final URI CONTEXT_ROOT = URI.create("http://localhost:8080/metrics-as-toys");
    private static final URI APPLICATION_METRICS = URI.create("http://localhost:8080/metrics/application");
    
    @GET
    public String getValue() {
        Client client = ClientBuilder.newClient();
        
        client.target(CONTEXT_ROOT).path("rest").path("counter").path("inc").request().get();
        
        JsonResponse response = client.target(APPLICATION_METRICS).path("counter").request(APPLICATION_JSON).get(JsonResponse.class);
        
        return "[METRICS] counter: " + response.getValue();
    }
}
