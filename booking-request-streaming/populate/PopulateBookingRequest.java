
import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import static org.neo4j.driver.v1.Values.parameters;

public class PopulateSongs {

    private String bookingRequestDir = "./bookingRequests";

    public static void main(String[] args) {
        // PopulateSongs ps = new PopulateSongs();
        ps.populate();
    }

    public void populate() {
        Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "database"));
        Session session = driver.session();
        Collection<File> files = FileUtils.listFiles(new File(this.songsDir), new RegexFileFilter("^(.*.mp3)"), DirectoryFileFilter.DIRECTORY);
        for (File file : files) {
            if (file.getName().endsWith(".mp3")) {
                try {
                    Mp3File mp3file = new Mp3File(file.getAbsolutePath());
                    if (mp3file.hasId3v1Tag()) {
                        ID3v1 id3v1Tag = mp3file.getId3v1Tag();
                        session.run("create (s: BookingRequest{flightNo: {flightNo}, from: {from}, to: {to}})"
                                , parameters(
                                        "flightNo", id3v1Tag.getFlightNo(),
                                        "from", id3v1Tag.getFrom(),
                                        "to", id3v1Tag.getTo()
                                        
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (UnsupportedTagException e) {
                    e.printStackTrace();
                } catch (InvalidDataException e) {
                    e.printStackTrace();
                }
            }
        }
        session.run("create index on :BookingRequest(type)");
        session.run("create constraint on (u:User) assert u.username is unique");
        session.close();
        driver.close();
    }
}