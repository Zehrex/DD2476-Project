1
https://raw.githubusercontent.com/harvestcore/ipmdroid/master/app/src/main/java/com/agm/ipmanager/status/EventsAdapter.java
package com.agm.ipmanager.status;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.agm.ipmanager.events.Event;
import com.agm.ipmanager.events.EventType;
import com.agm.ipmanager.R;

import java.util.ArrayList;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.Holder> {
    Context context;
    LayoutInflater inflater;
    ArrayList<Event> data;

    public EventsAdapter(Context context, ArrayList<Event> data) {
        this.inflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = inflater.inflate(R.layout.fragment_event_row, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Event event = data.get(position);
        holder.eventRowText.setText(event.message);
        holder.eventRowTimestamp.setText(event.timestamp);

        if (event.type == EventType.DEPLOY) {
            holder.eventRowIcon.setImageResource(R.drawable.docker);
        } else if (event.type == EventType.MACHINE) {
            holder.eventRowIcon.setImageResource(R.drawable.machine);
        } else {
            holder.eventRowIcon.setImageResource(R.drawable.info);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        ConstraintLayout eventRowLayout;
        TextView eventRowText;
        TextView eventRowTimestamp;
        ImageView eventRowIcon;


        public Holder(@NonNull View itemView) {
            super(itemView);
            eventRowLayout = itemView.findViewById(R.id.eventRowLayout);
            eventRowText = itemView.findViewById(R.id.eventRowText);
            eventRowTimestamp = itemView.findViewById(R.id.eventRowTimestamp);
            eventRowIcon = itemView.findViewById(R.id.eventRowIcon);
        }
    }
}
