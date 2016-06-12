package insa.tc.tendance.requests;

import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import insa.tc.tendance.MainActivity;
import insa.tc.tendance.database.Clothe;
import insa.tc.tendance.database.User;

/**
 * Created by patrik on 12/06/16.
 */
public class GetMyClothesRequest extends AsyncTask<User, Void, Map<String, List<Clothe>>>{
    private class TypesClothes extends HashMap<String, List<Clothe>> {}
    @Override
    protected Map<String, List<Clothe>> doInBackground(User... params) {
        Map<String, List<Clothe>> myclothes = null;
    try {
        final String path = "/clothe";
        final String url = MainActivity.SERVEUR_URL + path;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        myclothes = restTemplate.getForObject(url, TypesClothes.class, params[0]);
   }catch (Exception e){
        Log.e("GetMyClothesRequest", e.getMessage(), e);
    }
        return myclothes;
    }
}
