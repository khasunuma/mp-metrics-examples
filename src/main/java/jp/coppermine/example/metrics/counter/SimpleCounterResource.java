package jp.coppermine.example.metrics.counter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.metrics.Counter;
import org.eclipse.microprofile.metrics.annotation.Metric;

/**
 * An simple example to use {@link Counter} metrics.
 */
@Path("counter")
@ApplicationScoped
public class SimpleCounterResource {
    
    /**
     * {@link Counter} metrics put to application registry 
     * named "counter" strictly.
     * 
     * <p>It's enabled to inject {@code Counter}, {@code Meter}, 
     * {@code Histogram} and {@code Timer} metrics to field with 
     * {@code @metrics} annotation.
     * Field injection is seemed that easiest way to use metrics without 
     * {@code Gauge} metrics.</p>
     * <p>In {@code @metrics}, using {@code name} parameter to set specific
     *  name to the metric. And to suppress enclosing object name if set 
     *  {@code absolute} parameter to {@code true}. 
     * (in default, it is {@code false}.)</p>
     */
    @Inject
    @Metric(name = "counter", absolute = true)
    private Counter counter;
    
    /**
     * Event to increment the counter.
     * 
     * <p>It's important that once it or {@link #decrement()} is called to 
     * register the metric. Otherwise, the metric is not registered.</p>
     * 
     * @return {@link Response} object means HTTP 200
     */
    @GET
    @Path("inc")
    public Response increment() {
        counter.inc();
        return Response.ok().build();
    }
    
    /**
     * Event to decrement the counter.
     * 
     * <p>It's important that once it or {@link #increment()} is called to 
     * register the metric. Otherwise, the metric is not registered.</p>
     * 
     * @return {@link Response} object means HTTP 200
     */
    @GET
    @Path("dec")
    public Response decrement() {
        counter.dec();
        return Response.ok().build();
    }
    
}
