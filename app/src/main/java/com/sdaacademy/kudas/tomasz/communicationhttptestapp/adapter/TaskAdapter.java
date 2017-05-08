package com.sdaacademy.kudas.tomasz.communicationhttptestapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.sdaacademy.kudas.tomasz.communicationhttptestapp.R;
import com.sdaacademy.kudas.tomasz.communicationhttptestapp.TaskMaper;
import com.sdaacademy.kudas.tomasz.communicationhttptestapp.dto.TaskDTO;
import com.sdaacademy.kudas.tomasz.communicationhttptestapp.model.Task;
import com.sdaacademy.kudas.tomasz.communicationhttptestapp.service.TaskService;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by RENT on 2017-04-29.
 */

public class TaskAdapter extends ArrayAdapter<Task> {

    private TaskService taskService;
    private int editPosition;


    public TaskAdapter(Context context, List<Task> listTask, TaskService taskService, int editPosition) {
        super(context, 0, listTask);
        this.taskService = taskService;
        this.editPosition = editPosition;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Task task = getItem(position);
        if (true) {
            if (position == editPosition) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.single_row_edit, parent, false);
            } else {

                convertView = LayoutInflater.from(getContext()).inflate(R.layout.single_row, parent, false);
            }
            CheckBox complitedCheckBox = (CheckBox) convertView.findViewById(R.id.checkBoxMessage);
            complitedCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    task.setCompleted(!task.isCompleted());
                    Call<TaskDTO> callPutChange = taskService.putTask(TaskMaper.TaskToTaskDTO(new Task(task.getId(), task.isCompleted(), task.getValue())));
                    callPutChange.enqueue(new Callback<TaskDTO>() {
                        @Override
                        public void onResponse(Call<TaskDTO> call, Response<TaskDTO> response) {
                            Toast.makeText(getContext(), "" + response.body().isCompleted(), Toast.LENGTH_LONG).show();

                        }

                        @Override
                        public void onFailure(Call<TaskDTO> call, Throwable t) {
                        }
                    });
                }
            });
        }
        TextView id = (TextView) convertView.findViewById(R.id.positionNumber);
        if (position == editPosition) {
            final EditText value = (EditText) convertView.findViewById(R.id.messageEditView);
            value.setText("" + task.getValue());
            Button saveButton = (Button) convertView.findViewById(R.id.saveButton);
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    task.setValue(value.getText().toString());
                    Call<TaskDTO> callPutChange = taskService
                            .putTask(TaskMaper.TaskToTaskDTO(
                                    new Task(task.getId(), task.isCompleted(), task.getValue())));
                    callPutChange.enqueue(new Callback<TaskDTO>() {
                        @Override
                        public void onResponse(Call<TaskDTO> call, Response<TaskDTO> response) {
                            Toast.makeText(getContext(), "Save!" + task.getValue(), Toast.LENGTH_LONG).show();
                            editPosition = -1;
                            notifyDataSetChanged();
                        }

                        @Override
                        public void onFailure(Call<TaskDTO> call, Throwable t) {
                        }
                    });

                }
            });

        } else {
            TextView value = (TextView) convertView.findViewById(R.id.messageTextView);
            value.setText("" + task.getValue());
        }
        CheckBox complitedCheckBox = (CheckBox) convertView.findViewById(R.id.checkBoxMessage);
        id.setText("" + position);
        complitedCheckBox.setChecked(task.isCompleted());
        return convertView;
    }

}
