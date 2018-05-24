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
import org.eclipse.microprofile.metrics.annotation.Metric;

@Path("imas-cg/stage/result")
@ApplicationScoped
public class CinderellaStageScoreResource {
    
    @Inject
    @Metric(name = "play.combo", absolute = true)
    private Histogram combo;
    
    @Inject
    @Metric(name = "play.score", absolute = true)
    private Histogram score;
    
    @POST
    @Path("input")
    @Consumes({APPLICATION_XML, APPLICATION_JSON})
    public String input(ScoreSheet scoreSheet) {
        combo.update(scoreSheet.getCombo());
        score.update(scoreSheet.getScore());
        return scoreSheet.toString();
    }
    
    @POST
    @Path("import")
    @Consumes({APPLICATION_XML, APPLICATION_JSON})
    public String importFromXml(List<ScoreSheet> scoreSheets) {
        scoreSheets.forEach(this::input);
        return scoreSheets.stream().map(e -> e.toString()).collect(joining("\n"));
    }
    
}
