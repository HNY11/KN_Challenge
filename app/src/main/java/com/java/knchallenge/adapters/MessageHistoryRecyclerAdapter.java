package com.java.knchallenge.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.java.knchallenge.R;
import com.java.knchallenge.data.local.entity.ContactEntity;
import com.java.knchallenge.data.local.entity.MessageHistoryEntity;
import com.java.knchallenge.utils.TimeConverter;

import java.util.List;

/**
 * Created By Himanshu on 21-02-2020
 */
public class MessageHistoryRecyclerAdapter extends RecyclerView.Adapter<MessageHistoryRecyclerAdapter.MessageHistoryViewHolder> {

    private Context mContext;
    private List<MessageHistoryEntity> messageHistoryEntityList;

    public MessageHistoryRecyclerAdapter(Context context){
        mContext=context;
    }

    public void setData(List<MessageHistoryEntity> messages) {
        this.messageHistoryEntityList = messages;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MessageHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MessageHistoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MessageHistoryViewHolder holder, int position) {
        if (messageHistoryEntityList != null){
            MessageHistoryEntity message = messageHistoryEntityList.get(position);
            holder.txtName.setText(message.getName());
            holder.txtOtp.setText(mContext.getResources().getString(R.string.otp,message.getOtp()));
            holder.txtTime.setText(TimeConverter.getTimeFromMilliSec(message.getCreatedAt()));
        }else {
            // Covers the case of message not been sent yet.
            holder.txtName.setText(mContext.getResources().getString(R.string.no_messages));
        }
    }

    @Override
    public int getItemCount() {
        if (messageHistoryEntityList != null)
            return messageHistoryEntityList.size();
        else return 1;
    }

    class MessageHistoryViewHolder extends RecyclerView.ViewHolder{

        private TextView txtName, txtOtp, txtTime;

        MessageHistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName=itemView.findViewById(R.id.text_name);
            txtOtp=itemView.findViewById(R.id.text_otp);
            txtTime=itemView.findViewById(R.id.text_time);
        }
    }



}
