package jp.coppermine.samples.metrics.gauge;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.metrics.annotation.Gauge;

@Path("gauge")
@RequestScoped
public class SimpleGaugeResource {

    @GET
    @Path("long")
    @Gauge(unit = "ms", name = "long-gauge", absolute = true)
    public long longValue() {
        return 450;
    }
    
    @GET
    @Path("int")
    @Gauge(unit = "cycle", name = "int-gauge", absolute = true)
    public int intValue() {
        return 75;
    }
    
    @GET
    @Path("double")
    @Gauge(unit = "percent", name = "double-gauge", absolute = true)
    public double doubleValue() {
        return 37.5;
    }
    
}
