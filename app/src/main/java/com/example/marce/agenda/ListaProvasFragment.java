package com.example.marce.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.marce.agenda.modelo.Prova;

import java.util.Arrays;
import java.util.List;

public class ListaProvasFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_provas, container, false);

        List<String> topicosPortugues = Arrays.asList("Sujeito", "Objeto Direto", "Objeto Indireto");
        Prova provaPortuges = new Prova("Portugues", "16/11/2018", topicosPortugues);

        List<String> topicosMatematica = Arrays.asList("Enquações", "Trigonometria");
        Prova provaMatematica = new Prova("Matematica", "16/11/2018", topicosMatematica);

        List<Prova> provas = Arrays.asList(provaMatematica, provaPortuges);

        ArrayAdapter<Prova> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, provas);

        ListView lista = (ListView)view.findViewById(R.id.provas_lista);
        lista.setAdapter(adapter);

//        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Prova prova = (Prova) parent.getItemAtPosition(position);
//                Toast.makeText(getContext(), "Clicou na prova de "+ prova, Toast.LENGTH_SHORT).show();
//                Intent vaiParaDetalhes = new Intent(getContext(), DetalhesProvaActivity.class);
//                vaiParaDetalhes.putExtra("prova", prova);
//                startActivity(vaiParaDetalhes);
//            }
//        });

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Prova prova = (Prova) parent.getItemAtPosition(position);
                Toast.makeText(getContext(), "Clicou na prova de "+ prova, Toast.LENGTH_SHORT).show();

                ProvasActivity provasActivity = (ProvasActivity) getActivity();
                provasActivity.selecionaProva(prova);
            }
        });
        return view;
    }
}
