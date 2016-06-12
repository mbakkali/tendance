package insa.tc.tendance.requests;

import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import insa.tc.tendance.MainActivity;
import insa.tc.tendance.database.User;

/**
 * Created by Patrik on 10/06/2016.
 */
public class CreateProfilRequest  extends AsyncTask<User, Void, User> {

    @Override
    protected User doInBackground(User... params) {
        User user = null;
        try {
            final String path = "/user/add";

            final String url = MainActivity.SERVEUR_URL + path;
            //final String url = "http://192.168.1.21:5000" + path; //LOCAL...
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            user = restTemplate.postForObject(url, params[0], User.class, params[0]);
            System.out.println(user.getId_user());

        } catch (Exception e) {
            Log.e("CreateProfilRequest", e.getMessage(), e);
        }
        return user;
    }

}
