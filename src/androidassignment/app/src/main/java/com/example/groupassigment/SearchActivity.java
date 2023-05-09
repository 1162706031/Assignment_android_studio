package com.example.groupassigment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    EditText Searchtext;
    private ExampleAdapter adapter;
    ImageButton bt_mic;
    private List<ExampleItem> exampleList;
    private List<ExampleItem> examples;
    private RecyclerView recyclerView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView((int) R.layout.searchactivity);
        fillExampleList();
        setUpRecyclerView();
        initToolbar();

        this.Searchtext = (EditText) findViewById(R.id.search_input);
        this.Searchtext.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void afterTextChanged(Editable editable) {
                SearchActivity.this.filterQuery(editable.toString());
            }
        });
    }

    private void fillExampleList() {
        this.exampleList = new ArrayList();
        this.exampleList.add(new ExampleItem(R.drawable.ic_filter_list_black_24dp, "One", "sub title"));
        this.exampleList.add(new ExampleItem(R.drawable.ic_filter_list_black_24dp, "Two", "sub title"));
        this.exampleList.add(new ExampleItem(R.drawable.ic_filter_list_black_24dp, "Three", "sub title"));
        this.exampleList.add(new ExampleItem(R.drawable.ic_filter_list_black_24dp, "Four", "sub title"));
        this.exampleList.add(new ExampleItem(R.drawable.ic_filter_list_black_24dp, "Five", "sub title"));
        this.exampleList.add(new ExampleItem(R.drawable.ic_filter_list_black_24dp, "Six", "sub title"));
        this.exampleList.add(new ExampleItem(R.drawable.ic_filter_list_black_24dp, "Seven", "sub title"));
        this.exampleList.add(new ExampleItem(R.drawable.ic_filter_list_black_24dp, "Eight", "sub title"));
        this.exampleList.add(new ExampleItem(R.drawable.ic_filter_list_black_24dp, "Nine", "sub title"));
        this.exampleList.add(new ExampleItem(R.drawable.ic_filter_list_black_24dp, "Ten", "sub title"));
    }

    private void setUpRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.RecyclerView2);
        recyclerView.setHasFixedSize(true);
        LayoutManager layoutManager = new LinearLayoutManager(this);
        this.adapter = new ExampleAdapter(this.exampleList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(this.adapter);
    }

    private void initToolbar() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setTitle((CharSequence) "Filter Activity");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /* access modifiers changed from: private */
    public void filterQuery(String text) {
        ArrayList<ExampleItem> filterdNames = new ArrayList<>();
        for (ExampleItem s : this.exampleList) {
            if (s.getText1().toLowerCase().contains(text) || s.getText2().toLowerCase().contains(text)) {
                filterdNames.add(s);
            }
        }
        this.adapter.setFilter(filterdNames);
    }
}