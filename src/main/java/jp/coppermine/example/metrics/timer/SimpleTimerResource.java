package jp.coppermine.example.metrics.timer;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Random;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.metrics.Timer;
import org.eclipse.microprofile.metrics.annotation.Metric;

@Path("timer")
@ApplicationScoped
public class SimpleTimerResource {
    
    @Inject
    @Metric(name = "timer", absolute = true)
    private Timer timer;
    
    @GET
    public void execute() {
        Timer.Context context = timer.time();
        
        List<Integer> list = new Random().ints(100000, 1, 100000).boxed().collect(toList());
        list.sort((e1, e2) -> e1 - e2);
        
        context.close();
    }
    
}
