package colosseum19.a300dpi.colosseum2k19.API;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import colosseum19.a300dpi.colosseum2k19.Interfaces.ApiCallback;
import colosseum19.a300dpi.colosseum2k19.Interfaces.CallbackInterface;
import colosseum19.a300dpi.colosseum2k19.Model.Fixture;
import colosseum19.a300dpi.colosseum2k19.Model.Score;

public class BackupApi {

    String BASE_URL;
    String TAG = "BackupApi";

    public BackupApi() {
        BASE_URL = "https://skylife.tech/api/colosseum/";
    }

    public void checkIfBackup(Context ctx, final ApiCallback callback) {
        String URL = BASE_URL + "backup/get.php";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            boolean isBackup = response.getBoolean("is_backup");
                            callback.shouldUseBackup(true, isBackup);
                        }catch (Exception e){
                            callback.shouldUseBackup(false, false);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.shouldUseBackup(false, false);
                    }
                });

        CustomRequestQueue.getRequestQueue(ctx).add(jsonObjectRequest);

    }


    public void getFixtures(Context ctx, final String gameName, final int day, final CallbackInterface callback){
        String URL = BASE_URL + "currentEvents/get.php?game_name="+gameName+"&day="+day;
        Log.d(TAG, "getFixtures, gamename "+gameName);
        Log.d(TAG, "getFixtures, day "+day);

        StringRequest fixtureRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                try{
                    Log.d(TAG, "onResponse: "+response);
                    JSONArray responseArray = new JSONArray(response);
                    callback.setFixtureData(Fixture.parseIntoList(responseArray), false);
                }catch (Exception e){
                    Log.d(TAG, "onResponse catch: "+e.toString());
                    callback.setFixtureData(null, true);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onError: "+error.toString());
                callback.setFixtureData(null, true);
            }
        });

        CustomRequestQueue.getRequestQueue(ctx).add(fixtureRequest);
    }


    public void getScores(Context ctx, final String gameName, final int day, final CallbackInterface callback){
        Log.d(TAG, "getScores, gamename "+gameName);
        Log.d(TAG, "getScores, day "+day);
        String URL = BASE_URL + "score/get.php?game_name="+gameName+"&day="+day;

        StringRequest fixtureRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: "+response);
                try{
                    JSONArray responseArray = new JSONArray(response);
                    callback.setScoreData(Score.parseIntoList(responseArray), false);
                }catch (Exception e){
                    Log.d(TAG, "onResponse catch: "+e.toString());
                    callback.setFixtureData(null, true);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onError: "+error.toString());
                callback.setFixtureData(null, true);
            }
        });

        CustomRequestQueue.getRequestQueue(ctx).add(fixtureRequest);

    }


}