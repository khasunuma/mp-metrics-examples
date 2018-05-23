package jp.coppermine.example.metrics.idolmaster;

import static java.util.stream.Collectors.joining;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.eclipse.microprofile.metrics.Histogram;
import org.eclipse.microprofile.metrics.Metadata;
import org.eclipse.microprofile.metrics.MetricRegistry;
import org.eclipse.microprofile.metrics.MetricType;

@Path("imas-cg/scores")
@ApplicationScoped
public class CinderellaStageMultipleScoreResource {
    
    @Inject
    private MetricRegistry registory;
    
    @POST
    @Path("input")
    @Consumes({APPLICATION_XML, APPLICATION_JSON})
    public String input(ScoreSheet scoreSheet) {
        Metadata comboHistogramMetadata = new Metadata(scoreSheet.getMusic() + ".combo", MetricType.HISTOGRAM);
        Metadata scoreHistogramMetadata = new Metadata(scoreSheet.getMusic() + ".score", MetricType.HISTOGRAM);
        
        Histogram combo = registory.histogram(comboHistogramMetadata);
        Histogram score = registory.histogram(scoreHistogramMetadata);
        
        combo.update(scoreSheet.getCombo());
        score.update(scoreSheet.getScore());
        return scoreSheet.toString();
    }
    
    @POST
    @Path("import")
    @Consumes({APPLICATION_XML, APPLICATION_JSON})
    public String input(List<ScoreSheet> scoreSheets) {
        scoreSheets.forEach(this::input);
        return scoreSheets.stream().map(e -> e.toString()).collect(joining("\n"));
    }
    
}
