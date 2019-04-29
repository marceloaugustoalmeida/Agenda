package com.example.marce.agenda;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.marce.agenda.converter.AlunoConverter;
import com.example.marce.agenda.dao.AlunoDAO;
import com.example.marce.agenda.modelo.Aluno;

import java.util.List;

//A primeira variavel da AsyncTask é a entrada do metodo doInBackground podendo ser alterado para o que quiser, lembrando que tem q passar a variavel no
//execute da chamada: EX:                 new EnviaAlunosTask(this).execute(XXX);
//A terceira variavel da AsyncTask é o mesmo do retorno do doInBackground e entrada do onPostExecute
public class EnviaAlunosTask extends AsyncTask<Object, Object, String> {

    private final Context context;
    private ProgressDialog dialog;

    public EnviaAlunosTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(Object[] objects) {

        AlunoDAO dao = new AlunoDAO(context);
        List<Aluno> alunos = dao.buscaAlunos();
        dao.close();

        AlunoConverter conversor = new AlunoConverter();

        String json = conversor.converterParaJSON(alunos);

        WebClient client = new WebClient();
        String resposta = client.post(json);
        return resposta;
    }

    //O objeto de entrada é a resposta do doInBackground
    @Override
    protected void onPostExecute(String resposta) {
        dialog.dismiss();
        Toast.makeText(context, resposta, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPreExecute() {
        dialog = ProgressDialog.show(context, "Aguarde", "Enviando alunos...", true, true);
    }
}
