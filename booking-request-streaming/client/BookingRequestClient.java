package client;

/**
 * Created by sampastoriza on 4/16/17.
 */

import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BookingRequestClient {

    private ClientStream cs = null;
    private Thread t;
    private String jwt;

    public static void main(String[] args) throws Exception {
        AudioClient ac = new AudioClient();
        BookingRequestClient bc = new BookingRequestClient();
        ac.beginCLI();
    }

    public void beginCLI() {
        
        
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            line = line.toLowerCase();
            switch (line) {
                case "help":
                    this.printHelpCommands();
                    break;
                case "play":
                    this.playStream();
                    break;
                case "quit":
                case "exit":
                case "q":
                    this.stopStream();
                    return;
                default:
            }
        }
    }

  
    // play stream - setDaemon (true)
    private void playStream() {
        this.connect(jwt);
        t = new Thread(cs);
        t.setDaemon(true);
        t.start();
    }

    private void connect(String jwt) {
        cs = new ClientStream(jwt);
    }

    private void register(Scanner sc) {
        System.out.println("Register Here");
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Username: ");
        String username = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();
        System.out.print("Confirm Password: ");
        String confirmPassword = sc.nextLine();
        if (password.equals(confirmPassword)) {
            this.registerClient(email, username, password);
        }
    }

    private void login(Scanner sc) {
        System.out.print("Username: ");
        String username = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();
        this.loginClient(username, password);
    }

    public void registerClient(String email, String username, String encryptedPassword) {
        try {
            GenericUrl url = new GenericUrl(new URL("http://127.0.0.1:" + 8080 + "/register"));
            HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();

            Map<String, String> json = new HashMap<>();
            json.put("email", email);
            json.put("username", username);
            json.put("password", encryptedPassword);

            HttpContent content = new JsonHttpContent(new JacksonFactory(), json);
            HttpRequest request = requestFactory.buildPostRequest(url, content);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType("application/json");
            request.setHeaders(headers);

            HttpResponse response = request.execute();
            jwt = response.parseAsString();
            System.out.println("jwt " + jwt);
        } catch (MalformedURLException e) {
            System.err.println("Register error 1");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Register error 2");
            e.printStackTrace();
        }
    }

    public void loginClient(String username, String password) {
        try {
            GenericUrl url = new GenericUrl(new URL("http://127.0.0.1:" + 8080 + "/login"));
            HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
            HttpRequest request = requestFactory.buildGetRequest(url);

            HttpHeaders headers = new HttpHeaders();
            headers.setAuthorization(username + ":" + password);
            request.setHeaders(headers);

            HttpResponse response = request.execute();
            jwt = response.parseAsString();
            System.out.println("Response " + jwt);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.err.println("Login error 1");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Login error 2");
        }
    }



}




