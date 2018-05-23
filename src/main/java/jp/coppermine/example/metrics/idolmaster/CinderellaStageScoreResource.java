package jp.coppermine.example.metrics.idolmaster;

import static java.util.stream.Collectors.joining;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.eclipse.microprofile.metrics.Histogram;
import org.eclipse.microprofile.metrics.annotation.Metric;

@Path("cg-stage/score")
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
    @Consumes({"application/xml", "application/json"})
    public String input(ScoreSheet scoreSheet) {
        combo.update(scoreSheet.getCombo());
        score.update(scoreSheet.getScore());
        return scoreSheet.toString();
    }
    
    @POST
    @Path("import")
    @Consumes({"application/xml", "application/json"})
    public String importFromXml(List<ScoreSheet> scoreSheets) {
        scoreSheets.forEach(this::input);
        return scoreSheets.stream().map(e -> e.toString()).collect(joining("\n"));
    }
    
}
