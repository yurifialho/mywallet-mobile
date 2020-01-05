package br.com.secoti.mywallet.room.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import br.com.secoti.mywallet.room.dao.LancamentoDao;
import br.com.secoti.mywallet.room.database.PrincipalDatabase;
import br.com.secoti.mywallet.room.entity.Lancamento;

public class LancamentoRepository {

    private LancamentoDao lancamentoDao;
    private LiveData<List<Lancamento>> lancamentos;

    public LancamentoRepository(Application app) {
        PrincipalDatabase database = PrincipalDatabase.getInstance(app);
        this.lancamentoDao = database.lancamentoDao();
        this.lancamentos = this.lancamentoDao.getAllLancamentos();
    }

    public void insert(Lancamento lancamento) {
        new InsertLancamentoAsyncTask(lancamentoDao).execute(lancamento);
    }

    public void update(Lancamento lancamento) {
        new UpdateLancamentoAsyncTask(lancamentoDao).execute(lancamento);
    }

    public void delete(Lancamento lancamento) {
        new DeleteLancamentoAsyncTask(lancamentoDao).execute(lancamento);
    }

    public void deleteAll() {
        new DeleteAllLancamentoAsyncTask(lancamentoDao).execute();
    }

    public LiveData<List<Lancamento>> listAll() {
        return this.lancamentos;
    }

    private static class InsertLancamentoAsyncTask extends AsyncTask<Lancamento, Void, Void> {
        private LancamentoDao lancamentoDao;

        private InsertLancamentoAsyncTask(LancamentoDao dao) {
            this.lancamentoDao = dao;
        }

        @Override
        protected Void doInBackground(Lancamento... lancamentos) {
            this.lancamentoDao.insert(lancamentos[0]);
            return null;
        }
    }

    private static class UpdateLancamentoAsyncTask extends AsyncTask<Lancamento, Void, Void> {
        private LancamentoDao lancamentoDao;

        private UpdateLancamentoAsyncTask(LancamentoDao dao) {
            this.lancamentoDao = dao;
        }

        @Override
        protected Void doInBackground(Lancamento... lancamentos) {
            this.lancamentoDao.update(lancamentos[0]);
            return null;
        }
    }

    private static class DeleteLancamentoAsyncTask extends AsyncTask<Lancamento, Void, Void> {
        private LancamentoDao lancamentoDao;

        private DeleteLancamentoAsyncTask(LancamentoDao dao) {
            this.lancamentoDao = dao;
        }

        @Override
        protected Void doInBackground(Lancamento... lancamentos) {
            this.lancamentoDao.delete(lancamentos[0]);
            return null;
        }
    }

    private static class DeleteAllLancamentoAsyncTask extends AsyncTask<Void, Void, Void> {
        private LancamentoDao lancamentoDao;

        private DeleteAllLancamentoAsyncTask(LancamentoDao dao) {
            this.lancamentoDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            this.lancamentoDao.deleteAllLancamentos();
            return null;
        }
    }
}
