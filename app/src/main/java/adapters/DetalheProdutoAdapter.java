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

import models.Loja;
import models.Produto;
import models.ProdutoLoja;

import static utils.ConverterDinheiroKt.converterDinheiro;
import static utils.IpServidorKt.ipServidor;

public class DetalheProdutoAdapter extends ArrayAdapter<Loja> {


    public DetalheProdutoAdapter(Context ctx, List<Loja> items){
        super(ctx, 0, items);
    }

    @android.support.annotation.NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull android.view.ViewGroup parent) {

        View v = convertView;

        if(v == null){
            v = android.view.LayoutInflater.from(getContext()).inflate(R.layout.list_item_lojas_detalhes, null);
        }

        Loja item = getItem(position);

        TextView txtLugar = v.findViewById(R.id.txtLugar);
        TextView txtEstoque = v.findViewById(R.id.txtEstoque);


        txtLugar.setText(item.getLogradouro() + ", " + item.getBairro() + ", " + item.getCidade() + " - " + item.getEstado());

        txtEstoque.setText("Estoque: "+ converterDinheiro(item.getEstoque()));

        return v;
    }
}
