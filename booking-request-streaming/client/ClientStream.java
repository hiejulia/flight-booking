package client;

import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by sampastoriza on 4/18/17.
 */
public class ClientStream implements Runnable {

    
    
    private BookingRequestInputStream bris;
    private AtomicBoolean streamPaused = new AtomicBoolean(false);
    private AtomicBoolean streamStopped = new AtomicBoolean(false);
    private String jwt;

    public ClientStream(String jwt) {
        this.jwt = jwt;
    }

    @Override
    public void run() {
        try {
            GenericUrl url = new GenericUrl(new URL("http://127.0.0.1:" + 8080 + "/play/song/random"));
            this.play(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void playGenre(String genre) {
        try {
            GenericUrl url = new GenericUrl("http://127.0.0.1:" + 8080 + "/play/genre/" + genre);
            HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
            HttpRequest request = requestFactory.buildGetRequest(url);

            HttpHeaders headers = new HttpHeaders();
            headers.setAuthorization("Bearer " + jwt);
            request.setHeaders(headers);

            HttpResponse response = request.execute();
            JsonParser parser = new JsonParser();
            JsonElement song = parser.parse(response.parseAsString());

            if(song.isJsonObject()) {
                JsonObject songInfo = song.getAsJsonObject();

                int id = songInfo.get("id").getAsInt();
                String artist = songInfo.get("artist").getAsString();
                String year = songInfo.get("year").getAsString();
                String album = songInfo.get("album").getAsString();
                String title = songInfo.get("title").getAsString();
                String track = songInfo.get("track").getAsString();

                System.out.println("Artist: " + artist);
                System.out.println("Album: " + album);
                System.out.println("Year: " + year);
                System.out.println("Genre: " + genre);
                System.out.println("Title: " + title);
                System.out.println("Track: " + track);

                URL songUrl = new URL("http://127.0.0.1:" + 8080 + "/song/play/" + id);

                this.ais = AudioSystem.getAudioInputStream(songUrl);
                this.clip = AudioSystem.getClip();
                this.clip.open(ais);
                this.clip.start();
                Thread.sleep(100); // given clip.drain a chance to start
                this.clip.drain();
                while (this.streamPaused.get()) {
                    Thread.sleep(100); // given clip.drain a chance to start
                    this.clip.drain();
                }
                this.playGenre(genre);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    public void stopStream() {
        this.streamStopped.set(true);
        this.streamPaused.set(false);
        this.clip.stop();
    }

    private void play(GenericUrl url) {
        try {
            HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
            HttpRequest request = requestFactory.buildGetRequest(url);

            HttpHeaders headers = new HttpHeaders();
            headers.setAuthorization("Bearer " + jwt);
            request.setHeaders(headers);

            HttpResponse response = request.execute();
            JsonParser parser = new JsonParser();
            JsonElement song = parser.parse(response.parseAsString());

            if(song.isJsonObject()) {
                JsonObject songInfo = song.getAsJsonObject();

                

                System.out.println("Artist: " + artist);
                System.out.println("Album: " + album);
                System.out.println("Year: " + year);
                System.out.println("Genre: " + genre);
                System.out.println("Title: " + title);
                System.out.println("Track: " + track);

                URL songUrl = new URL("http://127.0.0.1:" + 8080 + "/song/play/" + id);

                this.ais = AudioSystem.getAudioInputStream(songUrl);
                this.clip = AudioSystem.getClip();
                this.clip.open(ais);
                this.clip.start();
                Thread.sleep(100); // given clip.drain a chance to start
                this.clip.drain();
                while (this.streamPaused.get()) {
                    Thread.sleep(100); // given clip.drain a chance to start
                    this.clip.drain();
                }
                this.nextSong();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    
    public void nextBookingRequest(){
        if (!this.streamStopped.get()) {
            try {
                this.streamBookingRequest(new GenericUrl(new URL("http://127.0.0.1:" + 8080 + "/stream/bookingRequest/random")));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }
}