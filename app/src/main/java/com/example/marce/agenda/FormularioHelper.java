package com.example.marce.agenda;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.example.marce.agenda.modelo.Aluno;

public class FormularioHelper {

    private EditText nome;
    private EditText endereco;
    private EditText telefone;
    private EditText site;
    private RatingBar nota;
    private ImageView campoFoto;

    private Aluno aluno;

    public FormularioHelper(FormularioActivity activity) {
        nome = activity.findViewById(R.id.nome);
        endereco = activity.findViewById(R.id.endereco);
        telefone = activity.findViewById(R.id.telefone);
        site = activity.findViewById(R.id.site);
        nota = activity.findViewById(R.id.nota);
        campoFoto = (ImageView) activity.findViewById(R.id.formulario_foto);
        aluno = new Aluno();
    }

    public Aluno pegaAluno() {
        aluno.setNome(nome.getText().toString());
        aluno.setEndereco(endereco.getText().toString());
        aluno.setTelefone(telefone.getText().toString());
        aluno.setSite(site.getText().toString());
        aluno.setNota(Double.valueOf(nota.getProgress()));
        aluno.setCaminhoFoto((String) campoFoto.getTag());
        return aluno;
    }

    public void preencheFormulario(Aluno aluno) {
        nome.setText(aluno.getNome());
        endereco.setText(aluno.getEndereco());
        telefone.setText(aluno.getTelefone());
        site.setText(aluno.getSite());
        nota.setProgress(aluno.getNota().intValue());
        carregaImagem(aluno.getCaminhoFoto());
        this.aluno = aluno;
    }

    public void carregaImagem(String caminhoFoto) {
        if (caminhoFoto != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
            //Reduz o tamanho da imagem
            Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
            campoFoto.setImageBitmap(bitmapReduzido);
            //Redimensiona a foto na ImageView
            campoFoto.setScaleType(ImageView.ScaleType.FIT_XY);
            //Cria uma variavel dentro do imageView com o caminho da foto para recuperar no FormularioHelper
            campoFoto.setTag(caminhoFoto);
        }
    }


}
