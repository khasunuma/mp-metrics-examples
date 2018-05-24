package jp.coppermine.example.metrics.gauge;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.metrics.annotation.Gauge;

/**
 * A simple example to use {@link Gauge} metric.
 */
@Path("gauge")
@RequestScoped
public class SimpleGaugeResource {
    
    /**
     * Register {@link Gauge} metric named "gauge" and obtain the metric value.
     * 
     * <p>It's important that {@code @Gauge} annotation is only attached 
     * to methods. Cannot expose {@code Gauge} metrics using field injection 
     * with {@code @Metric} annotation.</p>
     * <p>It's different from the other metric types that any {@code Gauge} 
     * metrics are required their unit because it's undefined in default.
     * Using {@code unit} parameter of {@code @Gauge} annotation to specify 
     * the unit. The other metric types can also specify the unit if to need 
     * to change from the default unit. See also MicroProfile Metrics 1.0 
     * specification:
     * {@link https://github.com/eclipse/microprofile-metrics/releases/tag/1.0}</p>
     * <p>{@code Gauge} can expose {@code long}, {@code int} or {@code double} 
     * value. Pay attention to avoid to register {@code Gauge} as same name 
     * because Metrics 1.0 does not allow redefine metrics. (Metrics 1.1 or 
     * later can do.)</p>
     * 
     * @return metric value
     */
    @GET
    @Path("value")
    @Gauge(unit = "ms", name = "gauge", absolute = true)
    public long getValue() {
        return 450;
    }
    
}
