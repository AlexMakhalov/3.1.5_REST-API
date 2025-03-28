package ru.makhalov;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.makhalov.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javax.swing.UIManager.put;

@Component
public class Communication {

    private final static String URL = "http://94.198.50.185:7081/api/users";
    private final RestTemplate restTemplate;
    private final HttpHeaders headers;

    public Communication(RestTemplate restTemplate, HttpHeaders headers) {
        this.restTemplate = restTemplate;
        this.headers = headers;
        this.headers.set("Cookie",
                String.join(";", restTemplate.headForHeaders(URL).get("Set-Cookie")));
    }


    public String getAllUsers() {
        String response = restTemplate.exchange(URL, HttpMethod.GET, null, String.class).getBody();
        return response;
    }

    public String createUser() {
        User user = new User(3L,"James","Brown",(byte) 22);
        HttpEntity<User> request = new HttpEntity<>(user, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(URL, request, String.class);
        return response.getBody();
    }

    public String updateUser() {
        User user = new User(3L,"Thomas","Shelby",(byte) 22);
        HttpEntity<User> request = new HttpEntity<>(user, headers);
            String response = restTemplate.exchange(URL,HttpMethod.PUT,request, String.class).getBody();
        return response;
    }
    public String deleteUser() {
        HttpEntity<String> request = new HttpEntity<>(headers);
        String response = restTemplate.exchange(URL+"/3", HttpMethod.DELETE, request, String.class).getBody();

        return response;
    }

    public String getResponse(){
        return createUser() + updateUser() +deleteUser();
    }
}
