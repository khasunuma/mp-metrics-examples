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

/**
 * Summarize the score in "THE IDOLM@STER CINDERELLA GIRLS STARLIGHT STAGE".
 * 
 * <p>This class exposes two {@link Histogram} metrics - play.combo and 
 * play.score - to record the score (combo and score point).</p>
 * <p>It is designed for "THE IDOLM@STER CINDERELLA GIRLS STARLIGHT STAGE",
 * but it might also adapt to "THE IDOLM@STER MILLION LIVE! THEATER DAYS".</p>
 */
@Path("imas-cg/stage/result")
@ApplicationScoped
public class CinderellaStageScoreResource {
    
    /**
     * Exposes combo count as "play.combo" histogram.
     */
    @Inject
    @Metric(name = "play.combo", absolute = true)
    private Histogram combo;
    
    /**
     * Exposes score point as "play.score" histogram.
     */
    @Inject
    @Metric(name = "play.score", absolute = true)
    private Histogram score;
    
    /**
     * Input a score on demand using XML or JSON format.
     * 
     * @param scoreSheet a play score
     * @return Input data to confirm (XML or JSON format)
     * @see ScoreSheet
     */
    @POST
    @Path("input")
    @Consumes({APPLICATION_XML, APPLICATION_JSON})
    public String input(ScoreSheet scoreSheet) {
        // update two histogram metrics
        combo.update(scoreSheet.getCombo());
        score.update(scoreSheet.getScore());
        
        return scoreSheet.toString();
    }
    
    /**
     * Import score records using XML or JSON format.
     * 
     * @param scoreSheets list of play scores
     * @return Input data to confirm (XML or JSON format)
     * @see ScoreSheet
     */
    @POST
    @Path("import")
    @Consumes({APPLICATION_XML, APPLICATION_JSON})
    public String importFromXml(List<ScoreSheet> scoreSheets) {
        scoreSheets.forEach(this::input);
        return scoreSheets.stream().map(e -> e.toString()).collect(joining("\n"));
    }
    
}
