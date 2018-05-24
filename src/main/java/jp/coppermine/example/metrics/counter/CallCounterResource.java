package jp.coppermine.example.metrics.counter;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.metrics.Counter;
import org.eclipse.microprofile.metrics.annotation.Counted;

/**
 * Another example to use {@link Counter} metric.
 * 
 * <p>It works same as {@link SimpleCounterResource} using {@code Counted} 
 * annotation on method.</p>
 * 
 * @see SimpleCounterResource
 */
@Path("call")
@ApplicationScoped
public class CallCounterResource {
    
    /**
     * Event to increment the counter metric named "called" and register it 
     * at first time.
     * 
     * <p>In {@code @Counted}, using {@code monotonic} to switch increment 
     * or decrement the counter when the method is called. If the value is 
     * {@code true}, it means to increment. Otherwise, to decrement.
     * in default, it is {@code false}. Other parameters are same in case
     * of {@link SimpleCounterResource}</p>
     * 
     * @return {@link Response} object means HTTP 200
     */
    @GET
    @Counted(monotonic = true, name = "called", absolute = true)
    public Response call() {
        return Response.ok().build();
    }
    
}
