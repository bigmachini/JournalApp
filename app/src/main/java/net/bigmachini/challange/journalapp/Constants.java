package net.bigmachini.challange.journalapp;

import com.google.firebase.auth.FirebaseAuth;

import net.bigmachini.challange.journalapp.db.entities.JournalEntity;

public class Constants {
    // global topic to receive app wide push notifications
    public static final String TOPIC_GLOBAL = "global";

    // broadcast receiver intent filters
    public static final String FIREBASE_USER = "FIREBASE_USER";
    public static FirebaseAuth gAuth = null;
    public static JournalEntity gSelectedEntry = null;

}

