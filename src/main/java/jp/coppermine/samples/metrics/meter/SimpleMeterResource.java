package jp.coppermine.samples.metrics.meter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.metrics.Meter;
import org.eclipse.microprofile.metrics.annotation.Metric;

@Path("meter")
@ApplicationScoped
public class SimpleMeterResource {

    @Inject
    @Metric(name = "meter", absolute = true)
    private Meter meter;
    
    @GET
    @Path("ping")
    public String ping() {
        meter.mark();
        return "pong";
    }
    
}
