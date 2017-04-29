package com.sdaacademy.kudas.tomasz.communicationhttptestapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.sdaacademy.kudas.tomasz.communicationhttptestapp.R;
import com.sdaacademy.kudas.tomasz.communicationhttptestapp.model.Task;

import java.util.List;

/**
 * Created by RENT on 2017-04-29.
 */

public class TaskAdapter extends ArrayAdapter<Task> {

    public TaskAdapter(Context context, List<Task> listTask) {
        super(context, 0, listTask);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Task task = getItem(position);
        if (convertView==null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.single_row, parent, false);
        }

        TextView id = (TextView) convertView.findViewById(R.id.positionNumber);
        TextView value = (TextView) convertView.findViewById(R.id.messageTextView);
        CheckBox complitedCheckBox = (CheckBox) convertView.findViewById(R.id.checkBoxMessage);

        id.setText("" + position);
        value.setText("" + task.getValue());
        complitedCheckBox.setChecked(task.isCompleted());

        return convertView;

    }
}
