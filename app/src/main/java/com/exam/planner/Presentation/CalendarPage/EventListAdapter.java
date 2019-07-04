package com.exam.planner.Presentation.CalendarPage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.exam.planner.Logic.Events.Event;
import com.exam.planner.R;

import java.util.ArrayList;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.ViewHolder> {
    private static final String TAG = "EventListAdapter";

    private Context mContext;
    private ArrayList<Event> mEvents = new ArrayList<>();

    public EventListAdapter(Context context, ArrayList<Event> events){
        mContext = context;
        mEvents = events;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_eventitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");

        //Get Event info and populate cells
        Event e = mEvents.get(position);
        holder.eventName.setText(e.getName());
        holder.eventStartDate.setText(CalendarFormatter.dateToString(e.getStartYear(), e.getStartMonth(), e.getStartDay()));
        holder.eventStartTime.setText(CalendarFormatter.timeToString(e.getStartHour(), e.getStartMinute()));
        holder.eventEndDate.setText(CalendarFormatter.dateToString(e.getEndYear(), e.getEndMonth(), e.getEndDay()));
        holder.eventEndTime.setText(CalendarFormatter.timeToString(e.getEndHour(), e.getEndMinute()));

        holder.eventItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on " + mEvents.get(position));

                Event e = mEvents.get(position);

                Intent editEventIntent = new Intent(mContext, EventEditActivity.class);
                editEventIntent.putExtra("eventPos", position);
                editEventIntent.putExtra("eventName", e.getName());

                editEventIntent.putExtra("eventStartYear", e.getStartYear());
                editEventIntent.putExtra("eventStartMonth", e.getStartMonth());
                editEventIntent.putExtra("eventStartDay", e.getStartDay());
                editEventIntent.putExtra("eventStartHour", e.getStartHour());
                editEventIntent.putExtra("eventStartMinute", e.getStartMinute());

                editEventIntent.putExtra("eventEndYear", e.getEndYear());
                editEventIntent.putExtra("eventEndMonth", e.getEndMonth());
                editEventIntent.putExtra("eventEndDay", e.getEndDay());
                editEventIntent.putExtra("eventEndHour", e.getEndHour());
                editEventIntent.putExtra("eventEndMinute", e.getEndMinute());

                ((Activity)mContext).startActivityForResult(editEventIntent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mEvents.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
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
