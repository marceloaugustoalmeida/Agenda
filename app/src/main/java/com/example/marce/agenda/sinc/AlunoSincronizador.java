package com.example.marce.agenda.sinc;

import android.content.Context;
import android.widget.Toast;

import com.example.marce.agenda.ListaAlunosActivity;
import com.example.marce.agenda.dao.AlunoDAO;
import com.example.marce.agenda.dto.AlunoSync;
import com.example.marce.agenda.event.AtualizaListaAlunoEvent;
import com.example.marce.agenda.retrofit.RetrofitInicializador;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlunoSincronizador {

    private final Context context;
    private EventBus bus = EventBus.getDefault();

    public AlunoSincronizador(Context context) {
        this.context = context;
    }

    public void buscaAlunos() {
        Call<AlunoSync> call = new RetrofitInicializador().getAlunoService().lista();
        call.enqueue(new Callback<AlunoSync>() {
            @Override
            public void onResponse(Call<AlunoSync> call, Response<AlunoSync> response) {
                AlunoSync alunoSync = response.body();
                AlunoDAO dao = new AlunoDAO(context);
                dao.sincroniza(alunoSync.getAlunos());
                dao.close();
                bus.post(new AtualizaListaAlunoEvent());
            }

            @Override
            public void onFailure(Call<AlunoSync> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                bus.post(new AtualizaListaAlunoEvent());
            }
        });
    }

}
