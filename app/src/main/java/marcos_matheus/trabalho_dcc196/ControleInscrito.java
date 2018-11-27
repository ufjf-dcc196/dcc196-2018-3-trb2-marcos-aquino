//package marcos_matheus.trabalho_dcc196;
//
//import android.content.Context;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import java.util.List;
//
//public class ControleInscrito extends RecyclerView.Adapter<ControleInscrito.ViewHolder>{
//
//    private List<Inscrito> inscritos;
//    private OnInscritoClickListener listener;
//    private OnInscritoLongClickListener longClickListener;
//
//    public interface OnInscritoClickListener {
//        void onInscritoClick(View view, int position);
//    }
//
//    public interface OnInscritoLongClickListener {
//        void onInscritoLongClickListener(View view, int position);
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
//        public TextView txtNome;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            txtNome = (TextView) itemView.findViewById(R.id.linhaItem);
//            itemView.setOnClickListener(new View.OnClickListener(){
//                @Override
//                public void onClick(View v) {
//                    if(listener != null) {
//                        int position = getAdapterPosition();
//                        if(position != RecyclerView.NO_POSITION) {
//                            listener.onInscritoClick(v, position);
//                        }
//                    }
//                }
//            });
//
//            itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    if (longClickListener != null) {
//                        int position = getAdapterPosition();
//                        if (position != RecyclerView.NO_POSITION) {
//                            longClickListener.onInscritoLongClickListener(v, position);
//                            return true;
//                        }
//                    }
//                    return false;
//                }
//            });
//        }
//
//        @Override
//        public void onClick(View v) {
//            if(listener != null) {
//                int position = getAdapterPosition();
//                if(position != RecyclerView.NO_POSITION) {
//                    listener.onInscritoClick(v, position);
//                }
//            }
//
//        }
//
//        @Override
//        public boolean onLongClick(View v) {
//            if(longClickListener != null) {
//                int position = getAdapterPosition();
//                if(position != RecyclerView.NO_POSITION) {
//                    longClickListener.onInscritoLongClickListener(v, position);
//                }
//            }
//            return false;
//        }
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        Context context = viewGroup.getContext();
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View linhaInscrito = inflater.inflate(R.layout.listagem_inscritos, viewGroup, false);
//        ViewHolder viewHolder = new ViewHolder(linhaInscrito);
//
//        return viewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
//        viewHolder.txtNome.setText(inscritos.get(i).getNome());
//    }
//
//    @Override
//    public int getItemCount() {
//        return inscritos.size();
//    }
//
//    public ControleInscrito(List<Inscrito> inscritos) {
//        this.inscritos = inscritos;
//    }
//
//    public void setOnParticipanteClickListener(OnInscritoClickListener listener) {
//        this.listener = listener;
//    }
//
//    public void setOnParticipanteLongClickListener(OnInscritoLongClickListener longClickListener) {
//        this.longClickListener = longClickListener;
//    }
//}
