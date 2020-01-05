package br.com.secoti.mywallet.adapters.lancamento;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

import br.com.secoti.mywallet.R;
import br.com.secoti.mywallet.room.entity.Lancamento;
import br.com.secoti.mywallet.util.DataUtil;

public class LancamentoListAdapter extends RecyclerView.Adapter<LancamentoListAdapter.LancamentoListViewHolder> {

    private List<Lancamento> lancamentos;

    public static class LancamentoListViewHolder extends RecyclerView.ViewHolder {

        public TextView txtData;
        public TextView txtCategoria;
        public TextView txtValor;
        public TextView txtDescricao;

        public LancamentoListViewHolder(@NonNull View view) {
            super(view);
            //this.linearLayout = view.findViewById(R.id.template_list_item);
            this.txtData = view.findViewById(R.id.item_line_data);
            this.txtCategoria = view.findViewById(R.id.item_line_categoria);
            this.txtValor = view.findViewById(R.id.item_line_valor);
            this.txtDescricao = view.findViewById(R.id.item_line_descricao);
        }

        public void setLancamentoToView(Lancamento lancamento, int position) {
          /*  if(position % 2 == 0) {
                this.linearLayout.setBackgroundColor(this.linearLayout.getResources().getColor(R.color.colorListItemBackgroud,
                        this.itemView.getContext().getTheme()));
            } else {
                this.linearLayout.setBackgroundColor(this.linearLayout.getResources().getColor(R.color.colorListItemBackgroudWhite,
                        this.itemView.getContext().getTheme()));
            }*/


            this.txtData.setText(DataUtil.dateToString(lancamento.getData()));
            this.txtCategoria.setText(lancamento.getCategoria()+"");
            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(2);
            this.txtValor.setText(df.format(lancamento.getValor()));
            this.txtDescricao.setText(lancamento.getDescricao());
        }

        public Lancamento getLancamentoFromView(){
            return null;
        }
    }

    @NonNull
    @Override
    public LancamentoListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lancamento_list_item_fragment, parent, false);
        LancamentoListViewHolder vh = new LancamentoListViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull LancamentoListViewHolder holder, int position) {
        holder.setLancamentoToView(lancamentos.get(position), position);
    }

    @Override
    public int getItemCount() {
        if(this.lancamentos != null)
            return lancamentos.size();

        return 0;
    }

    public void setLancamentos(List<Lancamento> lancamentos) {
        this.lancamentos = lancamentos;
        notifyDataSetChanged();
    }
}