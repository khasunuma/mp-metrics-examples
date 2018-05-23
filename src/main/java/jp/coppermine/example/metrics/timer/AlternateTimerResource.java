package jp.coppermine.example.metrics.timer;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Random;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.metrics.annotation.Timed;

@Path("alt-timer")
@ApplicationScoped
public class AlternateTimerResource {
    
    @GET
    @Timed(name = "alt-timer", absolute = true)
    public void execute() {
        List<Integer> list = new Random().ints(100000, 1, 100000).boxed().collect(toList());
        list.sort((e1, e2) -> e1 - e2);
    }
    
}
