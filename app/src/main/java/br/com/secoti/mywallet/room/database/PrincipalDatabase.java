package br.com.secoti.mywallet.room.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.Date;

import br.com.secoti.mywallet.room.dao.LancamentoDao;
import br.com.secoti.mywallet.room.entity.Lancamento;
import br.com.secoti.mywallet.room.entity.converters.DataConverter;

@Database(entities = {Lancamento.class}, version = 1)
@TypeConverters({DataConverter.class})
public abstract class PrincipalDatabase extends RoomDatabase {

    private static PrincipalDatabase instance;

    public abstract LancamentoDao lancamentoDao();

    public static synchronized PrincipalDatabase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    PrincipalDatabase.class, "mywalletdatabase")
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private LancamentoDao lancamentoDao;

        private PopulateDbAsyncTask(PrincipalDatabase db) {
            lancamentoDao = db.lancamentoDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            lancamentoDao.insert(new Lancamento(new Date(), 1.56f, "Descrição 001", 1));
            lancamentoDao.insert(new Lancamento(new Date(), 1.57f, "Descrição 002", 1));
            lancamentoDao.insert(new Lancamento(new Date(), 1.58f, "Descrição 003", 1));
            lancamentoDao.insert(new Lancamento(new Date(), 1.59f, "Descrição 004", 1));
            return null;
        }
    }
}
