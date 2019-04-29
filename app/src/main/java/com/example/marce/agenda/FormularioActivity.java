package com.example.marce.agenda;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.marce.agenda.dao.AlunoDAO;
import com.example.marce.agenda.modelo.Aluno;
import com.example.marce.agenda.retrofit.RetrofitInicializador;

import java.io.File;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormularioActivity extends AppCompatActivity {

    public static final int CODIGO_CAMERA = 567;
    private FormularioHelper formularioHelper;
    private String caminhoFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        formularioHelper = new FormularioHelper(this);

        Intent intent = getIntent();
        Aluno aluno = (Aluno) intent.getSerializableExtra("aluno");
        if (aluno != null) {
            formularioHelper.preencheFormulario(aluno);
        }

        Button botaoFoto = (Button) findViewById(R.id.formulario_botao_foto);
        botaoFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //caminho do app
                caminhoFoto = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
                //caminho completo do arquivo
                File arquivoFoto = new File(caminhoFoto);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(arquivoFoto));
                startActivityForResult(intent, CODIGO_CAMERA);
            }
        });

        Button btnSalvar = (Button) findViewById(R.id.btn_salvar);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Aluno aluno = formularioHelper.pegaAluno();

                AlunoDAO dao = new AlunoDAO(FormularioActivity.this);

                if (aluno.getId() != null) {
                    dao.altera(aluno);
                } else {
                    dao.insere(aluno);
                }
                dao.close();

                Toast.makeText(FormularioActivity.this, "SALVO", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //abrir a foto que tirou
        if (requestCode == CODIGO_CAMERA && resultCode == Activity.RESULT_OK) {
            formularioHelper.carregaImagem(caminhoFoto);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_formulario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_formulario_ok:
                Aluno aluno = formularioHelper.pegaAluno();

                AlunoDAO dao = new AlunoDAO(this);

                if (aluno.getId() != null) {
                    dao.altera(aluno);
                } else {
                    dao.insere(aluno);
                }
                dao.close();

                Call call = new RetrofitInicializador().getAlunoService().insere(aluno);

                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        Toast.makeText(FormularioActivity.this, "OnResponse", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        Toast.makeText(FormularioActivity.this, "onFailure", Toast.LENGTH_SHORT).show();
                    }
                });

                Toast.makeText(FormularioActivity.this, "SALVO", Toast.LENGTH_SHORT).show();

                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
