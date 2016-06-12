package insa.tc.tendance.requests;

import android.os.AsyncTask;
import android.system.StructPollfd;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import insa.tc.tendance.MainActivity;
import insa.tc.tendance.database.User;

public class LoginRequest extends AsyncTask<String,Void,User>{

    @Override
    protected User doInBackground(String... params){
        User user = null;
        final String path = "/login";
        final String requestParams = String.format("?mail=%s&password=%s", params);
        final String url = MainActivity.SERVEUR_URL;
        final String url_local = "http://192.168.1.21:5000";
        final String req = url + path + requestParams; //LOCAL...
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        try {
            user = restTemplate.getForObject(req, User.class);
        }catch (RestClientException rce){}

        return user;
    }
}
