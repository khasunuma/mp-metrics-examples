package jp.coppermine.example.metrics.timer;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Random;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.metrics.Timer;
import org.eclipse.microprofile.metrics.annotation.Metric;

/**
 * An simple example to use {@link Timer} metric. 
 */
@Path("timer")
@ApplicationScoped
public class SimpleTimerResource {
    
    /**
     * {@link Timer} metric put to application registry named "timer".
     * 
     * <p>It's enabled to inject {@code Counter}, {@code Meter}, 
     * {@code Histogram} and {@code Timer} metrics to field with 
     * {@code @metrics} annotation.
     * Field injection is seemed that easiest way to use metrics without 
     * {@code Gauge} metrics.</p>
     */
    @Inject
    @Metric(name = "timer", absolute = true)
    private Timer timer;
    
    /**
     * Event to access the meter metric named "timer" and register it at 
     * first time.
     * 
     * <p>On each event, call {@link Timer#time()} on the start point and 
     * {@link Timer.Context#close()} on the end point. Then it calculates 
     * the time between the start point and the end point.</p>
     * <p>cf. {@code Timer} metric means 'seconds per event'. {@code Meter} 
     * metric means 'events per second'.</p>
     * 
     * @return {@link Response} object means HTTP 200
     */
    @GET
    public Response execute() {
        // begin of event
        Timer.Context context = timer.time();
        
        // some procedure to require much time
        // In this case, create random 100,000 numbers and sort them
        List<Integer> list = new Random().ints(100000, 1, 100000).boxed().collect(toList());
        list.sort((e1, e2) -> e1 - e2);
        
        // end of event - calculates the time automatically
        context.close();
        
        return Response.ok().build();
    }
    
}
