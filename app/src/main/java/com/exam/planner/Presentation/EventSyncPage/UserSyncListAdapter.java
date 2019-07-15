package com.exam.planner.Presentation.EventSyncPage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.exam.planner.DSO.User;
import com.exam.planner.Logic.Events.Event;
import com.exam.planner.Logic.Login.data.Repository;
import com.exam.planner.Persistence.Stubs.UserPersistenceStub;
import com.exam.planner.R;

import java.util.ArrayList;

public class UserSyncListAdapter extends RecyclerView.Adapter<UserSyncListAdapter.ViewHolder> {
    private static final String TAG = "EventListSyncAdapter";

    private Context mContext;
    private ArrayList<User> mUsers;
    private ArrayList<Event> selectedUserE;
    private EventSyncListAdapter eAdaptor;
    private RecyclerView eventListView;

    public UserSyncListAdapter(Context context, ArrayList<User> users, EventSyncListAdapter eAdaptor, RecyclerView eventListView) {
        mContext = context;
        mUsers = users;
        this.eAdaptor = eAdaptor;
        this.eventListView = eventListView;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_useritem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");
        final Repository repo = Repository.getInstance(UserPersistenceStub.getInstance());

        String data = mUsers.get(position).getUsername().concat(" - ID: "+mUsers.get(position).getId());
        Log.d(TAG, "onBindViewHolder: called " + data);
        holder.userName.setText(data);
        holder.userName.setTextSize(30);
        holder.userItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onBindViewHolder: " + repo.getUser().getDisplayName());
                Log.d(TAG, "onBindViewHolder: " + mUsers.get(position).getUsername());

                if(repo.getUser().getDisplayName().equals(mUsers.get(position).getUsername())){
                    Toast.makeText(mContext, "That is you... can't do that", Toast.LENGTH_LONG).show();
                    selectedUserE = new ArrayList<>();
                    eAdaptor = new EventSyncListAdapter(mContext, selectedUserE);
                    eventListView.setAdapter(eAdaptor);
                    eventListView.setLayoutManager(new LinearLayoutManager(mContext));
                } else {
                    selectedUserE = mUsers.get(position).getPlanner().getEventsList();
                    eAdaptor = new EventSyncListAdapter(mContext, selectedUserE);
                    eventListView.setAdapter(eAdaptor);
                    eventListView.setLayoutManager(new LinearLayoutManager(mContext));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView userName;
        RelativeLayout userItemLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.user_name_field);
            userItemLayout = itemView.findViewById(R.id.user_item_layout);
        }
    }

    public ArrayList<Event> getNewEvents(){ return eAdaptor.getCopyEvents(); }
}
