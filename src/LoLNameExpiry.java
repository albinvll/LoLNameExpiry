import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.league.League;
import com.merakianalytics.orianna.types.core.match.Match;
import com.merakianalytics.orianna.types.core.summoner.Summoner;
import org.joda.time.DateTime;
import org.joda.time.Days;

public class LoLNameExpiry {
    public static void main(String[] args) {
        Orianna.setRiotAPIKey("");
        Orianna.setDefaultRegion(Region.EUROPE_WEST);
        //Maximum and minimum days before name expiration
        final int MAX = 915;
        final int MIN = 150;
        
        Summoner summoner = Orianna.summonerNamed("sore loser").get();
        int level = summoner.getLevel();
        //If the summoner is level 30 or over, maximum number of days is 915, and if he's lower than level 5, minimum is 150
        int daysLimit = level >= 30 ? MAX : (level <= 5 ? MIN : level*30);
        System.out.println(summoner.getName() + " is level " + summoner.getLevel() + " on the " + summoner.getRegion() + " server.");
        Match lastMatch = Orianna.matchHistoryForSummoner(summoner).get().get(0);
        DateTime lastMatchDate = lastMatch.getCreationTime();
        DateTime current = new DateTime();
        System.out.println("Days left before name expiration: " + (daysLimit - Days.daysBetween(lastMatchDate, current).getDays()));
    }
}
