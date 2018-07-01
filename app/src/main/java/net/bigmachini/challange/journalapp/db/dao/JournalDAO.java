package net.bigmachini.challange.journalapp.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import net.bigmachini.challange.journalapp.db.entities.JournalEntity;

import java.util.Date;
import java.util.List;

@Dao
public interface JournalDAO {
    @Query("SELECT * FROM journal_table WHERE id =:id")
    List<JournalEntity> getJournalsById(int id);

    @Query("SELECT * FROM journal_table WHERE user_id =:userId ORDER BY id DESC")
    List<JournalEntity> getAll(String userId);


    @Query("SELECT COUNT(*) FROM journal_table")
    int getCount();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insert(JournalEntity journal);

    @Delete
    void delete(JournalEntity journal);

    @Query("DELETE FROM journal_table")
    void deleteAll();

    @Query("DELETE  FROM journal_table WHERE id=:id")
    void deleteById(int id);

    /**
     * Updating only saved
     * By customer phone
     */
    @Query("UPDATE journal_table SET title=:title and journal_entry=:journalEntry and updated_at = :updatedAt WHERE id = :journalId")
    void update(String title, String journalEntry, Date updatedAt, int journalId);
}
