package jp.coppermine.example.metrics.client.histogram;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static jp.coppermine.example.metrics.client.Endpoint.APPLICATION_METRICS;
import static jp.coppermine.example.metrics.client.Endpoint.APPLICATION_PATH;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;

@Path("histogram")
@RequestScoped
public class SimpleHistogramClient {

    @GET
    public String getValue() {
        Client client = ClientBuilder.newClient();
        
        client.target(APPLICATION_PATH).path("histogram").request().post(Entity.form(new Form().param("value", "100")));
        client.target(APPLICATION_PATH).path("histogram").request().post(Entity.form(new Form().param("value", "75")));
        client.target(APPLICATION_PATH).path("histogram").request().post(Entity.form(new Form().param("value", "125")));
        client.target(APPLICATION_PATH).path("histogram").request().post(Entity.form(new Form().param("value", "200")));
        client.target(APPLICATION_PATH).path("histogram").request().post(Entity.form(new Form().param("value", "150")));
        
        JsonResponse response = client.target(APPLICATION_METRICS).path("histogram").request(APPLICATION_JSON).get(JsonResponse.class);
        
        return String.format("[METRICS] histogram.count: %d, histogram.min: %d, histogram.max: %d, histogram.mean: %f", response.getHistogram().getCount(), response.getHistogram().getMin(), response.getHistogram().getMax(), response.getHistogram().getMean());
    }

}
