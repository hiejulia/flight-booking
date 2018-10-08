@Category(DockerIT.class) 
public class Checkin-servie-test { 
 
    // Test connection 
    @Test 
    public void testConnection() throws IOException { 
        String baseUrl = System.getProperty("service.url"); 
        URL serviceUrl = new URL(baseUrl + "v1/checkin-services"); 
        HttpURLConnection connection = (HttpURLConnection) serviceUrl.openConnection(); 
        int responseCode = connection.getResponseCode(); 
        assertEquals(200, responseCode); 
    } 
} 
