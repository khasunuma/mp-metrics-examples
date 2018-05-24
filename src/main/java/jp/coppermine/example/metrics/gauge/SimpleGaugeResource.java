package jp.coppermine.example.metrics.gauge;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.metrics.annotation.Gauge;

@Path("gauge")
@RequestScoped
public class SimpleGaugeResource {
    
    @GET
    @Path("value")
    @Gauge(unit = "ms", name = "gauge", absolute = true)
    public long getValue() {
        return 450;
    }
    
}
