package net.bigmachini.challange.journalapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.bigmachini.challange.journalapp.db.controllers.JournalController;
import net.bigmachini.challange.journalapp.db.entities.JournalEntity;

import java.util.Date;

public class JournalCreateActivity extends BaseActivity {

    Button btnSubmit;
    EditText edtJournalEntry, edtTitle;
    Context mContext;
    JournalController mJournalController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_create);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mContext = JournalCreateActivity.this;
        mJournalController = new JournalController(mContext);
        edtJournalEntry = findViewById(R.id.edt_journal_entry);
        edtTitle = findViewById(R.id.edt_title);
        btnSubmit = findViewById(R.id.btn_post);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String journalEntry = edtJournalEntry.getText().toString().trim();
                String journalTitle = edtTitle.getText().toString().trim();

                if (journalEntry == null || journalEntry.isEmpty() || journalEntry.length() == 0) {
                    edtJournalEntry.setError(getString(R.string.journal_entry_mandatory));
                    return;
                }

                if (journalTitle == null || journalTitle.isEmpty() || journalTitle.length() == 0) {
                    edtTitle.setError(getString(R.string.journal_title_mandatory));
                    return;
                }

                JournalEntity journalEntity = new JournalEntity();
                journalEntity.setTitle(journalTitle);
                journalEntity.setJournalEntry(journalEntry);
                journalEntity.setUserId(mAuth.getUid());
                journalEntity.setCreateAt(new Date());
                journalEntity.setUpdatedAt(new Date());

                if(Constants.gSelectedEntry == null) {
                    mJournalController.createJournal(journalEntity);
                    Toast.makeText(mContext, "Journal Entry Added ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    journalEntity = new JournalEntity();
                    journalEntity.setTitle(journalTitle);
                    journalEntity.setJournalEntry(journalEntry);
                    journalEntity.setUpdatedAt(new Date());
                    Toast.makeText(mContext, "Journal Entry updated ", Toast.LENGTH_SHORT).show();
                    mJournalController.updateJournal(journalEntity);
                }
                startActivity(new Intent(JournalCreateActivity.this, JournalListActivity.class));
                finish();
            }
        });

        if(Constants.gSelectedEntry != null)
        {
            edtTitle.setText(Constants.gSelectedEntry.getTitle());
            edtJournalEntry.setText(Constants.gSelectedEntry.getJournalEntry());
        }
    }

}
