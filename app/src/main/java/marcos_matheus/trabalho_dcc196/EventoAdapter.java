package marcos_matheus.trabalho_dcc196;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import marcos_matheus.trabalho_dcc196.dominio.entidades.Evento;

public class EventoAdapter extends RecyclerView.Adapter<EventoAdapter.ViewHolderEvento> {

    private List<Evento> dadosEvento;

    public EventoAdapter(List<Evento> dadosEvento){

        this.dadosEvento = dadosEvento;
    }

    @NonNull
    @Override
    public EventoAdapter.ViewHolderEvento onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.linha_eventos, parent, false);

        ViewHolderEvento holderEvento = new ViewHolderEvento(view, parent.getContext());

        return holderEvento;
    }

    @Override
    public void onBindViewHolder(@NonNull EventoAdapter.ViewHolderEvento holder, int position) {

        if((dadosEvento != null) && (dadosEvento.size() > 0)) {

            Evento evento = dadosEvento.get(position);

            holder.txtTitulo.setText(evento.titulo);
            holder.txtData.setText(evento.data);
        }
    }

    @Override
    public int getItemCount() {
        return dadosEvento.size();
    }


    public class ViewHolderEvento extends RecyclerView.ViewHolder{

        public TextView txtTitulo;
        public TextView txtData;

        public ViewHolderEvento(@NonNull View itemView, final Context context) {
            super(itemView);

            txtTitulo = (TextView) itemView.findViewById(R.id.txtTituloEvento);
            txtData = (TextView) itemView.findViewById(R.id.txtDataEvento);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(dadosEvento.size() > 0){

                        Evento evento = dadosEvento.get(getLayoutPosition());
                        Toast.makeText(context, "Evento " + evento.titulo + " selecionado.", Toast.LENGTH_SHORT).show();


                        Intent it = new Intent(context, CadastroEvento.class);
                        it.putExtra("EVENTO", evento);
                        ((AppCompatActivity)context).startActivityForResult(it, 0);

                    }
                }
            });
        }
    }
}
