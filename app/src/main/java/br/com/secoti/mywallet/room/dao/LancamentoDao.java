package br.com.secoti.mywallet.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.secoti.mywallet.room.entity.Lancamento;

@Dao
public interface LancamentoDao {

    @Insert
    void insert(Lancamento lancamento);

    @Update
    void update(Lancamento lancamento);

    @Delete
    void delete(Lancamento lancamento);

    @Query("DELETE FROM lancamento_table")
    void deleteAllLancamentos();

    @Query("SELECT * FROM lancamento_table ORDER BY data")
    LiveData<List<Lancamento>> getAllLancamentos();
}
