package com.example.marce.agenda;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.marce.agenda.modelo.Prova;


public class ProvasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provas);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction tx = fragmentManager.beginTransaction();
        tx.replace(R.id.frame_principal, new ListaProvasFragment());
        if (estaNoModoPaisagem()) {
            tx.replace(R.id.frame_secundario, new DetalhesProvasFragment());
        }
        tx.commit();
    }

    private boolean estaNoModoPaisagem() {
        return getResources().getBoolean(R.bool.modoPaisagem);
    }

    public void selecionaProva(Prova prova) {
        FragmentManager manager = getSupportFragmentManager();

        if (!estaNoModoPaisagem()) {
            FragmentTransaction tx = manager.beginTransaction();

            DetalhesProvasFragment detalhesFragment = new DetalhesProvasFragment();
            Bundle parametros = new Bundle();
            parametros.putSerializable("prova", prova);
            detalhesFragment.setArguments(parametros);

            tx.replace(R.id.frame_principal, detalhesFragment);
            //adiciona a transação do fragment na pilha pro botão voltar
            tx.addToBackStack(null);
            tx.commit();
        } else {
            DetalhesProvasFragment detalhesFragment = (DetalhesProvasFragment) manager.findFragmentById(R.id.frame_secundario);
            detalhesFragment.populaCamposCom(prova);
        }
    }
}
