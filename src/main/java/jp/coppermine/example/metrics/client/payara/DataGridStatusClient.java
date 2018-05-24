package jp.coppermine.example.metrics.client.payara;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static jp.coppermine.example.metrics.client.Endpoint.APPLICATION_METRICS;
import static jp.coppermine.example.metrics.client.Endpoint.APPLICATION_PATH;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

@Path("payara")
@RequestScoped
public class DataGridStatusClient {

    @GET
    @Path("data-grid")
    public String getMemberCount() {
        Client client = ClientBuilder.newClient();
        
        client.target(APPLICATION_PATH).path("payara").path("cluster/members").request().get();
        
        JsonResponse response = client.target(APPLICATION_METRICS).path("payara.data-grid.members").request(APPLICATION_JSON).get(JsonResponse.class);
        
        return "[METRICS] payara.data-grid.members: " + response.getValue();
    }
    
}
