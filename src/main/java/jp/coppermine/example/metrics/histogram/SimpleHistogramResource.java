package jp.coppermine.example.metrics.histogram;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.eclipse.microprofile.metrics.Histogram;
import org.eclipse.microprofile.metrics.annotation.Metric;

@Path("histogram")
@ApplicationScoped
public class SimpleHistogramResource {

    @Inject
    @Metric(name = "histogram", absolute = true)
    private Histogram histogram;
    
    @POST
    public void receive(@FormParam("value") int value) {
        histogram.update(value);
    }
    
}
