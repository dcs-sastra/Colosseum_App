package colosseum19.a300dpi.colosseum2k19.API;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class CustomRequestQueue {

    private static RequestQueue instance = null;

    public static RequestQueue getRequestQueue(Context ctx){
        if(instance == null){
            instance = Volley.newRequestQueue(ctx);
        }
        return instance;
    }
}
