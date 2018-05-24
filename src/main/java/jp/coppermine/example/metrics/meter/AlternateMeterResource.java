package jp.coppermine.example.metrics.meter;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.metrics.annotation.Metered;

/**
 * Another example to use {@link Meter} metric.
 * 
 * @see SimpleMeterResource
 */
@Path("alt-meter")
@ApplicationScoped
public class AlternateMeterResource {
    
    /**
     * Event to increment the counter metric named "alt-meter" and register 
     * it at first time.
     * 
     * <p>Each call the method, It's seemed to call 
     * {@link org.eclipse.microprofile.metrics.Meter#mark()} to update the 
     * metric implicitly.
     * 
     * @return {@link Response} object means HTTP 200
     */
    @GET
    @Path("ping")
    @Metered(name = "alt-meter", absolute = true)
    public Response ping() {
        // fire an event - needless to call Meter#mark() method
        
        return Response.ok().build();
    }
    
}
