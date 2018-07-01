package net.bigmachini.challange.journalapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import net.bigmachini.challange.journalapp.db.controllers.JournalController;
import net.bigmachini.challange.journalapp.db.entities.JournalEntity;

public class JournalDetailsActivity extends BaseActivity {
    JournalController mJournalController;
    Context mContext;
    TextView tvTitle, tvJournal, tvDateCreated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(JournalDetailsActivity.this, JournalCreateActivity.class));
            }
        });

        mContext = JournalDetailsActivity.this;
        tvJournal = findViewById(R.id.tv_journal_entry);
        tvTitle = findViewById(R.id.tv_title);
        tvDateCreated = findViewById(R.id.tv_date_created);

        JournalEntity journal = Constants.gSelectedEntry;
        tvTitle.setText(journal.getTitle());
        tvJournal.setText(journal.getJournalEntry());
        tvDateCreated.setText(journal.getCreateAt().toString());
    }
}
