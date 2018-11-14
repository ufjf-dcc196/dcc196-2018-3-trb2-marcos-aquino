package marcos_matheus.trabalho_dcc196;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ControleEvento extends RecyclerView.Adapter<ControleEvento.ViewHolder>{


    private List<Evento> eventos;
    private OnEventoClickListener listener;
    private OnEventoLongClickListener longClickListener;

    public interface OnEventoClickListener {
        void onEventoClick(View view, int position);
    }

    public interface OnEventoLongClickListener {
        void onEventoLongClickListener(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public TextView txtNome;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNome = (TextView) itemView.findViewById(R.id.linhaItem);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if(listener != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            listener.onEventoClick(v, position);
                        }
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (longClickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            longClickListener.onEventoLongClickListener(v, position);
                            return true;
                        }
                    }
                    return false;
                }
            });
        }

        @Override
        public void onClick(View v) {
            if(listener != null) {
                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION) {
                    listener.onEventoClick(v, position);
                }
            }

        }

        @Override
        public boolean onLongClick(View v) {
            if(longClickListener != null) {
                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION) {
                    longClickListener.onEventoLongClickListener(v, position);
                }
            }
            return false;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View linhaEvento = inflater.inflate(R.layout.listagem_inscritos, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(linhaEvento);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.txtNome.setText(eventos.get(i).getTitulo());
    }

    @Override
    public int getItemCount() {
        return eventos.size();
    }

    public ControleEvento(List<Evento> eventos) {
        this.eventos = eventos;
    }

    public void setOnEventoClickListener(OnEventoClickListener listener) {
        this.listener = listener;
    }

    public void setOnEventoLongClickListener(OnEventoLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

}
