package insa.tc.tendance.requests;

import android.os.AsyncTask;

import org.springframework.web.client.RestTemplate;

import insa.tc.tendance.MainActivity;
import insa.tc.tendance.database.User;

/**
 * Created by patrik on 15/06/16.
 */
public class DeleteProfilRequest extends AsyncTask<User, Void, Void> {

    @Override
    protected Void doInBackground(User... params) {
        String path = MainActivity.SERVEUR_URL + "/user/del";
        User me = params[0];
        String url = path + "/" + me.getId_user();
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(url);
        return null;
    }

}
