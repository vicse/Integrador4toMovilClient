package com.ore.vicse.integrador4toclient.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ore.vicse.integrador4toclient.R;
import com.ore.vicse.integrador4toclient.models.Cliente;
import com.ore.vicse.integrador4toclient.services.ApiService;
import com.ore.vicse.integrador4toclient.services.ApiServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterClientActivity extends AppCompatActivity {

    private static final String TAG = RegisterClientActivity.class.getSimpleName();

    private EditText nombreInput, correoInput, dniInput, direccionInput, passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_client);

        nombreInput = findViewById(R.id.name_input);
        correoInput = findViewById(R.id.email_input);
        dniInput = findViewById(R.id.dni_input);
        direccionInput = findViewById(R.id.direccion_input);
        passwordInput = findViewById(R.id.password_input);

    }

    public void showLogin(View view){
        Intent intent = new Intent(RegisterClientActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void callRegister(View view){

        String nombre = nombreInput.getText().toString();
        String correo = correoInput.getText().toString();
        String dni = dniInput.getText().toString();
        String direccion = direccionInput.getText().toString();
        String password = passwordInput.getText().toString();

        if (nombre.isEmpty() || correo.isEmpty() || dni.isEmpty() || direccion.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Todos los campos son requeridos" ,Toast.LENGTH_SHORT).show();
            return;
        }

        ApiService service = ApiServiceGenerator.createService(ApiService.class);

        Call<Cliente> call = null;

        call = service.createCliente(nombre,correo,dni,direccion,password);

        call.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                try {

                    int statusCode = response.code();
                    Log.d(TAG, "HTTP status code: " + statusCode);

                    if (response.isSuccessful()) {

                        Cliente cliente = response.body();
                        Log.d(TAG, "cliente: " + cliente);
                        Toast.makeText(RegisterClientActivity.this, "Registro satisfactorio", Toast.LENGTH_LONG).show();
                        finish();
                        Intent intent = new Intent(RegisterClientActivity.this, HomeActivity.class);
                        intent.putExtra("IdCliente", cliente.getId_cliente());
                        startActivity(intent);

                    } else {
                        Log.e(TAG, "onError: " + response.errorBody().string());
                        throw new Exception("Error en el servicio");
                    }

                } catch (Throwable t) {
                    try {
                        Log.e(TAG, "onThrowable: " + t.toString(), t);
                        Toast.makeText(RegisterClientActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    } catch (Throwable x) {
                    }
                }

            }

            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
                Toast.makeText(RegisterClientActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });

    }
}
