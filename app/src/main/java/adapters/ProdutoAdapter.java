package adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartgame.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import models.Produto;

import static utils.ConverterDinheiroKt.converterDinheiro;
import static utils.IpServidorKt.ipServidor;

public class ProdutoAdapter extends ArrayAdapter<Produto> {


    public ProdutoAdapter(Context ctx, List<Produto> items){
        super(ctx, 0, items);
    }

    @android.support.annotation.NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull android.view.ViewGroup parent) {

        View v = convertView;

        if(v == null){
            v = android.view.LayoutInflater.from(getContext()).inflate(R.layout.list_item, null);
        }

        Produto item = getItem(position);


        TextView nomeProduto = v.findViewById(R.id.nome_produto);
        TextView txtValor = v.findViewById(R.id.valor_produto);

        ImageView imagemViagem = v.findViewById(R.id.foto_produto);

        Picasso.with(getContext()).load(ipServidor()+"/smartgames/"+item.getImagem()).into(imagemViagem);

        nomeProduto.setText(item.getNome());

        txtValor.setText("Apartir de "+ converterDinheiro(item.getValor()));

        return v;
    }
}