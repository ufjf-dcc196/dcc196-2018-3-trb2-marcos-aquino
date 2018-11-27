package marcos_matheus.trabalho_dcc196;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import marcos_matheus.trabalho_dcc196.dominio.entidades.Inscrito;

public class InscritoAdapter extends RecyclerView.Adapter<InscritoAdapter.ViewHolderInscrito> {

    private List<Inscrito> dadosInscrito;

    public InscritoAdapter(List<Inscrito> dadosInscrito){

        this.dadosInscrito = dadosInscrito;
    }

    @NonNull
    @Override
    public InscritoAdapter.ViewHolderInscrito onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.linha_inscritos, parent, false);

        ViewHolderInscrito holderInscrito = new ViewHolderInscrito(view);

        return holderInscrito;
    }

    @Override
    public void onBindViewHolder(@NonNull InscritoAdapter.ViewHolderInscrito holder, int position) {

        if((dadosInscrito != null) && (dadosInscrito.size() > 0)) {

            Inscrito inscrito = dadosInscrito.get(position);

//            holder.txtNome.setText(inscrito.nome);
//            holder.txtEmail.setText(inscrito.email);
        }
    }

    @Override
    public int getItemCount() {
        return dadosInscrito.size();
    }


    public class ViewHolderInscrito extends RecyclerView.ViewHolder{

        public TextView txtNome;
        public TextView txtEmail;

        public ViewHolderInscrito(@NonNull View itemView) {
            super(itemView);

            txtNome = (TextView) itemView.findViewById(R.id.txtNomeParticipante);
            txtEmail = (TextView) itemView.findViewById(R.id.txtEmailParticipante);
        }
    }

}
