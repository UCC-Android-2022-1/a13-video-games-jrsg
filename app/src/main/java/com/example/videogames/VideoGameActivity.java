package com.example.videogames;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
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

        configUi();
    }

    private void configUi() {
        Intent intent = getIntent();

        if(intent != null && intent.hasExtra(MainActivity.VG_ID) ){
            int id = intent.getIntExtra(MainActivity.VG_ID, -1);
            String titulo = intent.getStringExtra(MainActivity.VG_TITULO);
            double precio = intent.getDoubleExtra(MainActivity.VG_PRECIO, -1);

            boolean xbox = intent.getBooleanExtra(MainActivity.VG_XBOX, false);
            boolean playstation = intent.getBooleanExtra(MainActivity.VG_PLAYSTATION, false);
            boolean nintendo = intent.getBooleanExtra(MainActivity.VG_NINTENDO, false);
            boolean pc = intent.getBooleanExtra(MainActivity.VG_PC, false);

            String estado = intent.getStringExtra(MainActivity.VG_ESTADO);

            edtTitulo.setText(titulo);
            edtPrecio.setText(precio + "");

            cbXbox.setChecked( xbox );
            cbPlastation.setChecked( playstation );
            cbNintendo.setChecked( nintendo );
            cbPc.setChecked( pc );

            if(estado.equals("Nuevo")){
                rbNuevo.setChecked(true);
            }else{
                rbUsado.setChecked(true);
            }
        }
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
                procesarRespuestaGuardado(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(VideoGameActivity.this, "Error de comunicación", Toast.LENGTH_SHORT).show();
                Log.e("VideoGameActivity", error.getMessage());
            }
        });

        queue.add(request);
    }

    private void procesarRespuestaGuardado(JSONObject response) {
        try {
            boolean ok      = response.getBoolean("ok");
            String error    = response.getString("error");

            if (ok){
                finish();
            }else{
                Toast.makeText(VideoGameActivity.this, "Ha sucedido un error", Toast.LENGTH_SHORT).show();
                Log.e("VideoGameActivity", error);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}