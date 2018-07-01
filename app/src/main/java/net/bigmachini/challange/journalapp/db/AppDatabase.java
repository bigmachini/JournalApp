package net.bigmachini.challange.journalapp.db;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import net.bigmachini.challange.journalapp.db.dao.JournalDAO;
import net.bigmachini.challange.journalapp.db.entities.DateConverter;
import net.bigmachini.challange.journalapp.db.entities.JournalEntity;


@Database(entities = {JournalEntity.class}, version = 2, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;
    public static final String DATABASE_NAME = "Journals.db";

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE_NAME)
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyDatabase() {
        INSTANCE = null;
    }

    // DAO's
    public abstract JournalDAO mJournalDao();
}