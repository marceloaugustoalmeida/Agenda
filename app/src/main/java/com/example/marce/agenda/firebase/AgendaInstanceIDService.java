package com.example.marce.agenda.firebase;

import android.util.Log;

import com.example.marce.agenda.retrofit.RetrofitInicializador;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgendaInstanceIDService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("TOKEN FIREBASE = ", "Refreshed token: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(final String refreshedToken) {
        Call<Void> call = new RetrofitInicializador().getDispositivoService().enviaToken(refreshedToken);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.i("TOKEN ENVIADO", refreshedToken);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("TOKEN FALHOU", t.getMessage());
            }
        });
    }
}
