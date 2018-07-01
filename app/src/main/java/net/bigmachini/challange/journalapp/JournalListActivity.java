package net.bigmachini.challange.journalapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import net.bigmachini.challange.journalapp.adapters.JournalAdapter;
import net.bigmachini.challange.journalapp.db.controllers.JournalController;

public class JournalListActivity extends BaseActivity {

    RecyclerView rvJournals;
    JournalController mJournalController;
    Context mContext;
    private LinearLayoutManager mLayoutManager;
    private JournalAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(JournalListActivity.this, JournalCreateActivity.class));
                Constants.gSelectedEntry = null;
            }
        });

        mContext = JournalListActivity.this;
        mJournalController = new JournalController(mContext);
        rvJournals = findViewById(R.id.rv_journal_list);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        rvJournals.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);

        rvJournals.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new JournalAdapter(mContext, mAuth.getUid());
        rvJournals.setAdapter(mAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAdapter != null) {
            mAdapter = new JournalAdapter(mContext, mAuth.getUid());
            rvJournals.setAdapter(mAdapter);
        }
    }
}
