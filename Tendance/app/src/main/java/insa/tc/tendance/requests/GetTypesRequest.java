package insa.tc.tendance.requests;

import android.os.AsyncTask;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

import insa.tc.tendance.MainActivity;
import insa.tc.tendance.database.Type;

/**
 * Created by Patrik on 13/06/2016.
 */
public class GetTypesRequest extends AsyncTask<Void, Void, List> {
    @Override
    protected List<Type> doInBackground(Void... params) {
        int id;
        String nom;
        List<LinkedHashMap> typesHashMap = null;
        List<Type> types = new ArrayList<>();
        final String path = "/clothe/types";
        final String url = MainActivity.SERVEUR_URL + path;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        typesHashMap =  restTemplate.getForObject(url, List.class);
        for (LinkedHashMap m :typesHashMap) {
            id = (int) m.get("type_id");
            nom = (String) m.get("type_name");
            types.add(new Type(new Long(id), nom));
        }
        return types;
    }
}
