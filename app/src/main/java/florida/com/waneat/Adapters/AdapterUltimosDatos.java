package florida.com.waneat.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.List;

import florida.com.waneat.Models.Product;
import florida.com.waneat.R;

/**
 * Created by David on 18/02/2018.
 */

public class AdapterUltimosDatos  extends RecyclerView.Adapter<AdapterUltimosDatos.MyViewHolder> {

    private List<Product> productosLista;

    public AdapterUltimosDatos(List<Product> productosLista) {
        this.productosLista = productosLista;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ultimos_datos, parent, false);


        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return productosLista.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder{

        private EditText uno, dos, tres;

        public MyViewHolder(View view) {
            super(view);

            uno = (EditText) view.findViewById(R.id.uno);
            dos = (EditText) view.findViewById(R.id.dos);
            tres = (EditText) view.findViewById(R.id.tres);
        }
    }


}