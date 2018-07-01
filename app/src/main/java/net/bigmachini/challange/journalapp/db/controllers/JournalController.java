package net.bigmachini.challange.journalapp.db.controllers;

import android.content.Context;

import net.bigmachini.challange.journalapp.db.AppDatabase;
import net.bigmachini.challange.journalapp.db.dao.JournalDAO;
import net.bigmachini.challange.journalapp.db.entities.JournalEntity;

import java.util.Date;
import java.util.List;

public class JournalController extends BaseController {
    private JournalDAO mJournalDao;

    public JournalController(Context context) {
        super(context);
        mJournalDao = AppDatabase.getDatabase(super.mContext).mJournalDao();
    }

    public List<JournalEntity> getJournalById(int journalId) {
        return mJournalDao.getJournalsById(journalId);
    }

    public List<JournalEntity> getAllJournals(String userId) {
        return mJournalDao.getAll(userId);
    }

    public void deleteById(int journalId) {
        mJournalDao.deleteById(journalId);
    }


    public int getCount() {
        return mJournalDao.getCount();
    }

    public long createJournal(JournalEntity journalEntity) {
        return mJournalDao.insert(journalEntity);
    }

    public void updateJournal(JournalEntity journalEntity) {
        Date dateUpdated = new Date();
        mJournalDao.update(journalEntity.getJournalEntry(), dateUpdated, journalEntity.getId());
    }

    public void deleteAllJournals() {
        mJournalDao.deleteAll();
    }
}

