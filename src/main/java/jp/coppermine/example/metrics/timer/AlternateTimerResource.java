package jp.coppermine.example.metrics.timer;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Random;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.metrics.annotation.Timed;

/**
 * Another example to use {@Timer} metric.
 * 
 * @See SimpleTimerResource
 */
@Path("alt-timer")
@ApplicationScoped
public class AlternateTimerResource {
    
    /**
     * Event to increment the counter metric named "alt-timer" and register it 
     * at first time.
     * 
     * <p>Each call the method, It's seemed to call 
     * {@link org.eclipse.microprofile.metrics.Timer#time()} and 
     * {@link org.eclipse.microprofile.metrics.Timer.Context#close} to update 
     * the metric implicitly.
     * 
     * @return {@link Response} object means HTTP 200
     */
    @GET
    @Timed(name = "alt-timer", absolute = true)
    public Response execute() {
        // begin to event - needless to call Timer#time() method
        
        // some procedure to require much time
        // In this case, create random 100,000 numbers and sort them
        List<Integer> list = new Random().ints(100000, 1, 100000).boxed().collect(toList());
        list.sort((e1, e2) -> e1 - e2);
        
        // end to event - needless to call Timer.Context#close() method
        
        return Response.ok().build();
    }
    
}
