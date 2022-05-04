package com.example.videogames;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements VideoGameAdapter.OnClickListener {
    private RecyclerView recyclerView;
    private VideoGameAdapter adapter;
    private RequestQueue requestQueue;

    public static final String BASE_URL = "http://192.168.6.111/videogames/";

    private FloatingActionButton fabNuevo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fabNuevo = findViewById(R.id.fabNuevo);

        recyclerView = findViewById(R.id.lista);
        adapter = new VideoGameAdapter(this, this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setAdapter( adapter );
        recyclerView.setLayoutManager(layoutManager);

        requestQueue = Volley.newRequestQueue(this);

        fabNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, VideoGameActivity.class);
                startActivity(intent);
            }
        });
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

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
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

    private void procesarLista(JSONArray response) {
        if(response != null){
            try {
                adapter.limpiar();

                for(int i = 0; i < response.length(); i++){
                    JSONObject fila = response.getJSONObject(i);

                    int id          = fila.getInt("id");
                    String titulo   = fila.getString("titulo");

                    VideoGame videoGame = new VideoGame();

                    videoGame.setId( id );
                    videoGame.setNombre(  titulo );

                    adapter.agregar(videoGame);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}