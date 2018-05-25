package jp.coppermine.example.metrics.sales;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.metrics.Counter;
import org.eclipse.microprofile.metrics.Metadata;
import org.eclipse.microprofile.metrics.MetricRegistry;
import org.eclipse.microprofile.metrics.MetricType;
import org.eclipse.microprofile.metrics.annotation.Metric;

/**
 * Counts stocked and sold items to sales management for sale and exhibit of 
 * "dojinshi" such as "Comic Market", "COMITIA" and "COMIC1". 
 */
@Path("sales")
@ApplicationScoped
public class SalesResource {
    
    @Inject
    private MetricRegistry registry;
    
    /**
     * Exposes numbers of items.
     */
    @Inject
    @Metric(name = "sales.items.count", absolute = true)
    private Counter items;
    
    /**
     * Register new item.
     * 
     * @param item name of new item, never null
     * @return {@link Response} object means HTTP 200
     */
    @PUT
    @Path("{item}")
    public Response addItem(@PathParam("item") String item) {
        Metadata metadata = new Metadata("sales.items." + item, MetricType.COUNTER);
        registry.counter(metadata);
        
        items.inc();
        
        return Response.ok().build();
    }
    
    /**
     * Cancel to sell an item.
     * 
     * @param item name of cancelled item, never null
     * @return {@link Response} object means HTTP 200
     */
    @DELETE
    @Path("{item}")
    public Response deleteItem(@PathParam("item") String item) {
        Metadata metadata = new Metadata("sales.items." + item, MetricType.COUNTER);
        registry.remove(metadata.getName());
        
        items.dec();
        
        return Response.ok().build();
    }
    
    /**
     * Supplies stocks of an item.
     * 
     * @param item name of supplied item
     * @param amount number of stocks
     * @return {@link Response} object means HTTP 200
     */
    @POST
    @Path("{item}/supply")
    public Response addStockItem(@PathParam("item") String item, @DefaultValue("0") @FormParam("amount") int amount) {
        Metadata metadata = new Metadata("sales.items." + item + ".count", MetricType.COUNTER);
        Counter counter = registry.counter(metadata);
        
        counter.inc(amount);
        
        return Response.ok().build();
    }
    
    /**
     * Sells an item.
     * 
     * @param item item name of sold
     * @param amount number of sold items
     * @return {@link Response} object means HTTP 200 if enable to sell,
     * otherwise the object means HTTP 304
     */
    @POST
    @Path("{item}/sell")
    public Response soldItem(@PathParam("item") String item, @DefaultValue("1") @FormParam("amount") int amount) {
        Metadata metadataCount = new Metadata("sales.items." + item + ".count", MetricType.COUNTER);
        Metadata metadataSold = new Metadata("sales.items." + item + ".sold", MetricType.COUNTER);
        Counter counterCount = registry.counter(metadataCount);
        Counter counterSold = registry.counter(metadataSold);
        
        if (amount > counterCount.getCount()) {
            return Response.status(Response.Status.NOT_MODIFIED).build();
        } else {
            counterSold.inc(amount);
            counterCount.dec(amount);
            
            return Response.ok().build();
        }
    }
    
}
