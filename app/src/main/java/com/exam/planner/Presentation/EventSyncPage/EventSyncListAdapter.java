package com.exam.planner.Presentation.EventSyncPage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.exam.planner.Logic.Events.Event;
import com.exam.planner.Logic.Sync.SelectedEvents;
import com.exam.planner.Presentation.CalendarPage.CalendarFormatter;
import com.exam.planner.R;

import java.util.ArrayList;

public class EventSyncListAdapter extends RecyclerView.Adapter<EventSyncListAdapter.ViewHolder> {
    private static final String TAG = "EventListSyncAdapter";

    private Context mContext;
    private ArrayList<Event> mEvents;
    private SelectedEvents selectedEvents;


    public EventSyncListAdapter(Context context, ArrayList<Event> events) {
        mContext = context;
        mEvents = events;
        selectedEvents = new SelectedEvents();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_eventitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");

        //Get Event info and populate cells
        final Event e = mEvents.get(position);
        holder.eventName.setText(e.getName());
        holder.eventStartDate.setText(CalendarFormatter.dateToString(e.getStartYear(), e.getStartMonth(), e.getStartDay()));
        holder.eventStartTime.setText(CalendarFormatter.timeToString(e.getStartHour(), e.getStartMinute()));
        holder.eventEndDate.setText(CalendarFormatter.dateToString(e.getEndYear(), e.getEndMonth(), e.getEndDay()));
        holder.eventEndTime.setText(CalendarFormatter.timeToString(e.getEndHour(), e.getEndMinute()));

        holder.eventItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: CLICKED " + mEvents.get(position));
                if(selectedEvents.contains(mEvents.get(position).getId())){
                    Toast.makeText(mContext, "Removing event from add list", Toast.LENGTH_LONG).show();
                    selectedEvents.remove(e);
                } else {
                    Toast.makeText(mContext, "Adding event to the add list", Toast.LENGTH_LONG).show();
                    selectedEvents.add(e);
                }
            }
        });
    }

    public ArrayList<Event> getCopyEvents() { return selectedEvents.getEvents(); }

    @Override
    public int getItemCount() {
        return mEvents.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView eventName, eventStartDate, eventStartTime, eventEndDate, eventEndTime;
        RelativeLayout eventItemLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            eventName = itemView.findViewById(R.id.event_edit_name_field);
            eventStartDate = itemView.findViewById(R.id.event_layout_start_date);
            eventStartTime = itemView.findViewById(R.id.event_layout_start_time);
            eventEndDate = itemView.findViewById(R.id.event_layout_end_date);
            eventEndTime = itemView.findViewById(R.id.event_layout_end_time);
            eventItemLayout = itemView.findViewById(R.id.event_item_layout);
        }
    }
}
