package jp.coppermine.example.metrics.payara;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.metrics.annotation.Gauge;

import com.hazelcast.core.HazelcastInstance;

/**
 * Expose the status of Domain Data Grid.
 * 
 * <p><strong>CAUTION</strong> 
 * This is only enabled on Payara Server or Payara Micro 4.181 / 5.181 or 
 * later. Any other runtime, e.g. OpenLiberty, WildFly Swarm) cannot run this
 * example.</p>
 */
@Path("payara")
@ApplicationScoped
public class DataGridStatusResource {
    
    @Inject
    private HazelcastInstance hazelcastInstance;
    
    /**
     * Expose {@code Gauge} metrics to show count of cluster members.
     * 
     * @return count of cluster members
     */
    @GET
    @Path("cluster/members")
    @Gauge(unit = "unit", name = "payara.data-grid.members", absolute = true)
    public int count() {
        return hazelcastInstance.getCluster().getMembers().size();
    }

}
