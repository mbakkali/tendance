package insa.tc.tendance.requests;

import android.os.AsyncTask;

import org.springframework.web.client.RestTemplate;

import insa.tc.tendance.MainActivity;
import insa.tc.tendance.database.User;

/**
 * Created by patrik on 12/06/16.
 */

    public class FriendDelRequest extends AsyncTask<User, Void,Void>{

        @Override
        protected Void doInBackground(User... params) {
            String path = MainActivity.SERVEUR_URL +"/user/friend";
            String url = path + "/" + params[0].getId_user() + "/" + params[1].getId_user();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.delete(url);
            return null;
        }
    }
