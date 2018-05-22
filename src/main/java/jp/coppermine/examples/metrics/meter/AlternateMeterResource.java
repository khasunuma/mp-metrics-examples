package jp.coppermine.examples.metrics.meter;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.metrics.annotation.Metered;

@Path("alt-meter")
@ApplicationScoped
public class AlternateMeterResource {
    
    @GET
    @Path("ping")
    @Metered(name = "alt-meter", absolute = true)
    public String ping() {
        return "pong";
    }
    
}
