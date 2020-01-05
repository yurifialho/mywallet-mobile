package br.com.secoti.mywallet.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import br.com.secoti.mywallet.room.entity.Lancamento;

public class LancamentoViewModel extends ViewModel {

    private final MutableLiveData<Lancamento> lancamento = new MutableLiveData<Lancamento>();

    public void setLancamento(Lancamento lancamento) {
        this.lancamento.setValue(lancamento);
    }

    public LiveData<Lancamento> getLancamento() {
        return this.lancamento;
    }
}
