package br.com.secoti.mywallet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import br.com.secoti.mywallet.ui.lancamento.LancamentoListFragment;
import br.com.secoti.mywallet.ui.main.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, LancamentoListFragment.newInstance())
                    .commitNow();
        }
    }
}
