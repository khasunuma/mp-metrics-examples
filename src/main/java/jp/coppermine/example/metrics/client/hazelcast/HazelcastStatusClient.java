package jp.coppermine.example.metrics.client.hazelcast;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.net.URI;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

@Path("hazelcast")
@RequestScoped
public class HazelcastStatusClient {
    
    private static final URI CONTEXT_ROOT = URI.create("http://localhost:8080/metrics-as-toys");
    private static final URI APPLICATION_METRICS = URI.create("http://localhost:8080/metrics/application");
    
    @GET
    public String getMemberCount() {
        Client client = ClientBuilder.newClient();
        
        client.target(CONTEXT_ROOT).path("rest").path("hazelcast/members").request().get();
        
        JsonResponse response = client.target(APPLICATION_METRICS).path("hazelcast-cluster-members").request(APPLICATION_JSON).get(JsonResponse.class);
        
        return "[METRICS] members: " + response.getValue();
    }
    
}
