package br.com.secoti.mywallet.ui.lancamento;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import br.com.secoti.mywallet.R;
import br.com.secoti.mywallet.room.entity.Lancamento;
import br.com.secoti.mywallet.ui.viewmodel.LancamentoListViewModel;
import br.com.secoti.mywallet.util.DataUtil;

public class LancamentoFormFragment extends DialogFragment {

    private LancamentoListViewModel lancamentoListViewModel;

    private FragmentActivity context;

    private EditText txtData;
    private EditText txtValor;
    private Spinner spinnerCategoria;

    private Button salvarBt;
    private DatePickerDialog datePickerDialog;

    public static LancamentoFormFragment newInstance() {
        return new LancamentoFormFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.lancamento_form_fragment, container, false);

        this.txtData = view.findViewById(R.id.dataEditText);
        this.txtData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               datePickerDialog.show();
            }
        });

        this.txtValor = view.findViewById(R.id.valorEditText);
        this.spinnerCategoria = view.findViewById(R.id.categoriaSpinner);

        this.populateSpinnerCategoria(view);
        Button cancelarBt = view.findViewById(R.id.cancelButton);
            cancelarBt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });

        this.salvarBt = view.findViewById(R.id.saveButton);
        salvarBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Lancamento lancamento = new Lancamento(DataUtil.stringToDate(txtData.getText().toString()),
                        new Float(txtValor.getText().toString()),
                        "",1);

                lancamentoListViewModel.save(lancamento);
                dismiss();
            }
        });

        this.datePickerDialog = new DatePickerDialog(this.getContext());
        this.datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar cal = Calendar.getInstance();
                    cal.set(year,month,dayOfMonth);
                txtData.setText(new SimpleDateFormat("dd/MM/yyyy").format(cal.getTime()));
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.lancamentoListViewModel = ViewModelProviders.of(getActivity()).get(LancamentoListViewModel.class);
    }

    @Override
    public void onResume() {
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();

        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;

        getDialog().getWindow().setAttributes((WindowManager.LayoutParams) params);
        super.onResume();
    }

    private void populateSpinnerCategoria(View view) {
        String[] array_spinner=new String[5];
        array_spinner[0]="option 1";
        array_spinner[1]="option 2";
        array_spinner[2]="option 3";
        array_spinner[3]="option 4";
        array_spinner[4]="option 5";
        Spinner s = (Spinner) view.findViewById(R.id.categoriaSpinner);
        ArrayAdapter adapter = new ArrayAdapter(view.getContext(), android.R.layout.simple_spinner_item, array_spinner);
        s.setAdapter(adapter);
    }


}
