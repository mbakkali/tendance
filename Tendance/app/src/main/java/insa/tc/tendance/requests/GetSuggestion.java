package insa.tc.tendance.requests;

import android.os.AsyncTask;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import insa.tc.tendance.MainActivity;

/**
 * Created by patrik on 15/06/16.
 */
public class GetSuggestion extends AsyncTask<Integer, Void, ArrayList> {
    @Override
    protected ArrayList doInBackground(Integer... params) {
        int sexe = params[0];
        int event = params[1];
        ArrayList<ArrayList<String>> suggestion;
        String url = MainActivity.SERVEUR_URL + "/outfit/suggestion/" + sexe + "/" + event;
        RestTemplate restTemplate= new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        suggestion = restTemplate.getForObject(url,ArrayList.class);
        for (ArrayList<String> strings : suggestion) {
            for (String string : strings) {
                System.out.println(string);
            }
        }
        return suggestion;
    }


}
