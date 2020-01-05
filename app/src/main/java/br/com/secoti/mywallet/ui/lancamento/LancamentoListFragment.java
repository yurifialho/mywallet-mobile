package br.com.secoti.mywallet.ui.lancamento;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import br.com.secoti.mywallet.R;
import br.com.secoti.mywallet.adapters.lancamento.LancamentoListAdapter;
import br.com.secoti.mywallet.room.entity.Lancamento;
import br.com.secoti.mywallet.ui.viewmodel.LancamentoListViewModel;

public class LancamentoListFragment extends Fragment {

    private LancamentoListViewModel lancamentoListViewModel;
    private RecyclerView recyclerView;
    private LancamentoListAdapter listAdapter;

    private List<Lancamento> lancamentos;
    private FloatingActionButton novoLacamentoBt;

    public static LancamentoListFragment newInstance() {
        return new LancamentoListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lancamento_list_fragment, container, false);

        this.recyclerView = view.findViewById(R.id.lancamento_list);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        this.listAdapter = new LancamentoListAdapter();
        this.recyclerView.setAdapter(this.listAdapter);

        this.novoLacamentoBt = view.findViewById(R.id.novoLancamentoBt);
        this.novoLacamentoBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                LancamentoFormFragment lancamentoFormFragment = LancamentoFormFragment.newInstance();
                lancamentoFormFragment.show(fm,"lancamento_form_fragment");
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.lancamentoListViewModel = ViewModelProviders.of(getActivity()).get(LancamentoListViewModel.class);
        this.lancamentoListViewModel.getLancamentos().observe(this, new Observer<List<Lancamento>>() {
            @Override
            public void onChanged(List<Lancamento> lancamentos) {
                listAdapter.setLancamentos(lancamentos);
            }
        });
    }

}
