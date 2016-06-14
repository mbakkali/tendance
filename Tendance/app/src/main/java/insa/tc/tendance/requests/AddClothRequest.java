package insa.tc.tendance.requests;

import android.os.AsyncTask;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import insa.tc.tendance.MainActivity;
import insa.tc.tendance.database.ClotheWithFile;

/**
 * Created by Patrik on 14/06/2016.
 */

public class AddClothRequest extends AsyncTask<ClotheWithFile, Void, Void> {

    @Override
    protected Void doInBackground(ClotheWithFile... params) {
        String path = "/clothe/add";
        String uri = MainActivity.SERVEUR_URL + path;

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.postForObject(uri, params[0], Void.class);
        return null;
    }
}
