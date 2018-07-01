package net.bigmachini.challange.journalapp.adapters;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.bigmachini.challange.journalapp.Constants;
import net.bigmachini.challange.journalapp.JournalDetailsActivity;
import net.bigmachini.challange.journalapp.R;
import net.bigmachini.challange.journalapp.db.controllers.JournalController;
import net.bigmachini.challange.journalapp.db.entities.JournalEntity;

import java.util.List;

public class JournalAdapter extends RecyclerView.Adapter<JournalAdapter.ViewHolder> {
    private final Context mContext;
    private List<JournalEntity> journals;
    JournalController mJournalController;

    public JournalAdapter(Context context, String userId) {
        this.mContext = context;
        mJournalController = new JournalController(mContext);
        this.journals = mJournalController.getAllJournals(userId);
        ContextWrapper cw = new ContextWrapper(context);

    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView tvTitle;
        TextView tvCreatedAt;
        TextView tvUpdatedAt;
        LinearLayout linearLayout;

        public ViewHolder(View view) {
            super(view);
            tvTitle = view.findViewById(R.id.tv_title);
            tvCreatedAt = view.findViewById(R.id.tv_time_created);
            tvUpdatedAt = view.findViewById(R.id.tv_time_last_edited);
            linearLayout = view.findViewById(R.id.layout);
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public JournalAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_journal, parent, false);
        return new ViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final JournalEntity journal = journals.get(position);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constants.gSelectedEntry = journal;
                mContext.startActivity(new Intent(mContext, JournalDetailsActivity.class));
            }
        });


  

        holder.tvCreatedAt.setText(journal.getCreateAt().toString());
        holder.tvTitle.setText(journal.getTitle());
        holder.tvUpdatedAt.setText(journal.getUpdatedAt().toString());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return journals.size();
    }
}
