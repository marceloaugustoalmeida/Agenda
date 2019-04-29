package com.example.marce.agenda;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.marce.agenda.modelo.Prova;

public class DetalhesProvasFragment extends Fragment {

    private TextView compoMateria;
    private TextView compoData;
    private ListView listaTopicos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalhes_provas, container, false);

        compoMateria = (TextView)view.findViewById(R.id.detalhes_prova_materia);
        compoData = (TextView)view.findViewById(R.id.detalhes_prova_data);
        listaTopicos = (ListView)view.findViewById(R.id.detalhes_prova_topicos);

        Bundle paramentos = getArguments();
        if(paramentos != null){
            Prova prova = (Prova) paramentos.getSerializable("prova");
            populaCamposCom(prova);
        }


        return view;
    }

    public void populaCamposCom(Prova prova){
        compoMateria.setText(prova.getMateira());
        compoData.setText(prova.getData());

        ArrayAdapter<String> adapterTopicos = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, prova.getTopicos());
        listaTopicos.setAdapter(adapterTopicos);
    }

}
