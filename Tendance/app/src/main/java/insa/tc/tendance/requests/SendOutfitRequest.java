package insa.tc.tendance.requests;

import android.os.AsyncTask;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import insa.tc.tendance.MainActivity;
import insa.tc.tendance.database.Outfit;

/**
 * Created by patrik on 12/06/16.
 */
public class SendOutfitRequest extends AsyncTask<Outfit, Void, Outfit>{
    @Override
    protected Outfit doInBackground(Outfit... params) {
        Outfit outfit = params[0];
        final String path = "/outfit/add";
        String url = MainActivity.SERVEUR_URL + path;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        outfit = restTemplate.postForObject(url, outfit, Outfit.class, outfit);
        return outfit;
    }
}
