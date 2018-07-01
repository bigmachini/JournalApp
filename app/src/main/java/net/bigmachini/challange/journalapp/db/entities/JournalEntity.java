package net.bigmachini.challange.journalapp.db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;

import net.bigmachini.challange.journalapp.db.structures.JournalStructure;

import java.util.Date;

@Entity(tableName = "journal_table")
public class JournalEntity {
    /**
     * The Remote ID of the model as found in the backend database.
     */
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "title")
    @Expose
    private String title;

    @ColumnInfo(name = "journal_entry")
    @Expose
    private String journalEntry;

    @ColumnInfo(name = "created_at")
    @Expose
    private Date createAt;

    @ColumnInfo(name = "updated_at")
    @Expose
    private Date updatedAt;
    @ColumnInfo(name = "last_read")
    @Expose
    private Date lastRead;

    @ColumnInfo(name = "user_id")
    @Expose
    private String userId;

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getJournalEntry() {
        return journalEntry;
    }

    public void setJournalEntry(String journalEntry) {
        this.journalEntry = journalEntry;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getLastRead() {
        return lastRead;
    }

    public void setLastRead(Date lastRead) {
        this.lastRead = lastRead;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    // Need this empty constructor for the database (SugarORM)
    public JournalEntity() {
    }

    @Ignore
    private JournalEntity(JournalEntity.JournalBuilder journalBuilder) {
        this.title = journalBuilder.title;
        this.journalEntry = journalBuilder.journalEntry;
        this.createAt = new Date();
    }


    public static class JournalBuilder {

        private final String title;
        private final String journalEntry;

        public JournalBuilder(JournalStructure journalStructure) {
            this.title = journalStructure.title;
            this.journalEntry = journalStructure.journalEntry;
        }

        public JournalEntity build() {
            return new JournalEntity(this);
        }
    }

    @Override
    public String toString() {
        return this.getTitle();
    }
}