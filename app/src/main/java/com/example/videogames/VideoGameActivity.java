package com.example.videogames;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class VideoGameActivity extends AppCompatActivity {
    private EditText edtTitulo, edtPrecio;
    private CheckBox cbXbox, cbPlastation, cbNintendo, cbPc;
    private RadioButton rbNuevo, rbUsado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_game);

        edtTitulo = findViewById(R.id.edtTitulo);
        edtPrecio = findViewById(R.id.edtPrecio);

        cbXbox          = findViewById(R.id.xboxOneCheckBox);
        cbPlastation    = findViewById(R.id.playStation4CheckBox);
        cbNintendo      = findViewById(R.id.nintendoSwitchCheckBox);
        cbPc            = findViewById(R.id.pcCheckBox);

        rbNuevo = findViewById(R.id.rbNuevo);
        rbUsado = findViewById(R.id.rbUsado);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.videogame_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.opc_guardar:
                guardar();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void guardar() {
        String titulo = edtTitulo.getText().toString().trim();
        String precio = edtPrecio.getText().toString().trim();

        boolean xbox        = cbXbox.isChecked();
        boolean plastation  = cbPlastation.isChecked();
        boolean nintendo    = cbNintendo.isChecked();
        boolean pc          = cbPc.isChecked();

        String estado = "Nuevo";

        if(rbUsado.isChecked()){
            estado = "Usado";
        }

        RequestQueue queue = Volley.newRequestQueue(this);

        String url = MainActivity.BASE_URL + "guardar.php";



        Map<String, String> mapa = new HashMap<>();
        mapa.put("titulo", titulo);
        mapa.put("precio", precio);

        mapa.put("xbox", String.valueOf(xbox) ); //true
        mapa.put("playstation", String.valueOf(plastation));
        mapa.put("nintendo", String.valueOf(nintendo));
        mapa.put("pc", String.valueOf(pc));

        mapa.put("estado", estado);

        JSONObject parametros = new JSONObject(mapa);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, parametros, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(request);
    }
}