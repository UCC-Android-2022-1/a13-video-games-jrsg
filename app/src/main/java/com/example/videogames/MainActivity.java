package com.example.videogames;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements VideoGameAdapter.OnClickListener {
    private RecyclerView recyclerView;
    private VideoGameAdapter adapter;
    private RequestQueue requestQueue;
    private static final String BASE_URL = "http://localhost/videogames/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.lista);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setAdapter( adapter );
        recyclerView.setLayoutManager(layoutManager);

        requestQueue = Volley.newRequestQueue(this);
    }

    @Override
    public void onClick(int position) {
        VideoGame videoGame = adapter.leer(position);
        Intent intent = new Intent(MainActivity.this, VideoGameActivity.class);

        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();

        consultarLista();
    }

    public void consultarLista(){
        String url = BASE_URL + "listar.php";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                procesarLista(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("MainActivity", "Error de comunicación: " + error.getMessage());
            }
        });

        requestQueue.add(request);
    }

    private void procesarLista(JSONObject response) {
        if(response != null){
            try {
                JSONArray videogames = response.getJSONArray("videogames");
                llenarLista(videogames);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void llenarLista(JSONArray videogames) {
        if(videogames != null){
            for(int i = 0; i < videogames.length(); i++){
                VideoGame videoGame = new VideoGame();

                videoGame.setId( videoGame.getId() );
                videoGame.setNombre( videoGame.getNombre() );

                adapter.agregar(videoGame);
            }
        }
    }
}