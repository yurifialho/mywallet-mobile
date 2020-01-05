package br.com.secoti.mywallet.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.ArrayList;
import java.util.List;

import br.com.secoti.mywallet.room.entity.Lancamento;
import br.com.secoti.mywallet.room.repository.LancamentoRepository;

public class LancamentoListViewModel extends AndroidViewModel {

    private LancamentoRepository lancamentoRepository;
    private LiveData<List<Lancamento>> lancamentos;

    public LancamentoListViewModel(@NonNull Application  app) {
        super(app);
        this.lancamentoRepository = new LancamentoRepository(app);
        this.lancamentos = this.lancamentoRepository.listAll();

    }

    public LiveData<List<Lancamento>> getLancamentos() {
        return this.lancamentos;
    }

    public void save(Lancamento lancamento) {
       if(lancamento.getId() == 0) {
           this.lancamentoRepository.insert(lancamento);
       } else {
           this.lancamentoRepository.update(lancamento);
       }
    }

    public void delete(Lancamento lancamento) {
        this.lancamentoRepository.delete(lancamento);
    }

    public void deleteAll() {
        this.lancamentoRepository.deleteAll();
    }
}
