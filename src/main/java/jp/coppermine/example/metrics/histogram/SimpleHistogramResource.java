package jp.coppermine.example.metrics.histogram;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.eclipse.microprofile.metrics.Histogram;
import org.eclipse.microprofile.metrics.annotation.Metric;

/**
 * A simple example to use {@link Histogram} metric.
 */
@Path("histogram")
@ApplicationScoped
public class SimpleHistogramResource {

    /**
     * {@link Histogram} metric put to application registry named "histogram".
     * 
     * <p>It's enabled to inject {@code Counter}, {@code Meter}, 
     * {@code Histogram} and {@code Timer} metrics to field with 
     * {@code @metrics} annotation. In addition, {@code Histogram} metrics 
     * cannot expose using method annotation.</p>
     */
    @Inject
    @Metric(name = "histogram", absolute = true)
    private Histogram histogram;
    
    /**
     * Event to put a value to the histogram.
     * 
     * <p>The histogram holds count of event, minimum and maximum value of 
     * whole events and calculates mean, median, standard deviation and more
     * automatically every call {@link Histogram#update(int)} or
     * {@link Histogram#update(long)}.</p>
     * 
     * @param value a value to put to the histogram
     */
    @POST
    public void receive(@FormParam("value") int value) {
        histogram.update(value);
    }
    
}
