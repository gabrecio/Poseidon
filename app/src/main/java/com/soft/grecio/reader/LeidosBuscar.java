package com.soft.grecio.reader;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class LeidosBuscar extends Fragment {

    // List view
    private ListView lv;

    // Listview Adapter
    ArrayAdapter<String> adapter;

    // Search EditText
    EditText inputSearch;


    // ArrayList for Listview
    ArrayList<HashMap<String, String>> productList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_leidos_buscar, container, false);
        // Listview Data
        String products[] = {"Del Bono C1- Lec 1520", "Del Bono C2- Lec 1000", "Del Bono C3- Lec 1020", "Del Bono C4- Lec 152", "Del Bono C5- Lec 150",
                "Las Rosas 100- Lec 100", "Las Rosas 102- Lec 1770","Las Rosas 104- Lec 1577",
                "Portal del este C1- Lec 150", "Portal del este C2- Lec 152", "Portal del este C3- Lec 154",
                "Portales del sol C1- Lec 620", "Portales del sol C2- Lec 520", "Portales del sol C3- Lec 120"};
        lv = (ListView) rootView.findViewById(R.id.list_view);
        inputSearch = (EditText) rootView.findViewById(R.id.inputSearch);

        // Adding items to listview
        adapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item, R.id.product_name, products);
        lv.setAdapter(adapter);

        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                LeidosBuscar.this.adapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });

        return rootView;
    }


}
