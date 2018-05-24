package jp.coppermine.example.metrics.meter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.metrics.Meter;
import org.eclipse.microprofile.metrics.annotation.Metric;

/**
 * An simple example to use {@link Meter} metric.
 */
@Path("meter")
@ApplicationScoped
public class SimpleMeterResource {

    /**
     * {@link Meter} metric put to application registry named "meter".
     * 
     * <p>It's enabled to inject {@code Counter}, {@code Meter}, 
     * {@code Histogram} and {@code Timer} metrics to field with 
     * {@code @metrics} annotation.
     * Field injection is seemed that easiest way to use metrics without 
     * {@code Gauge} metrics.</p>
     */
    @Inject
    @Metric(name = "meter", absolute = true)
    private Meter meter;
    
    /**
     * Event to access the meter metric named "meter" and register it at 
     * first time.
     * 
     * <p>On each event, call {@link Meter#mark()} to count the frequency.
     * {@code Meter} holds frequency per second and calculates the mean 
     * while last 1, 5, 15 seconds and whole time.</p>
     * <p>cf. {@code Meter} metric means 'events per second'. {@code Timer} 
     * metric means 'seconds per event'.</p>
     * 
     * @return {@link Response} object means HTTP 200
     */
    @GET
    @Path("ping")
    public Response ping() {
        // call each events
        meter.mark();
        
        return Response.ok().build();
    }
    
}
