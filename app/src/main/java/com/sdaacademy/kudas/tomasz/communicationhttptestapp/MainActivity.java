package com.sdaacademy.kudas.tomasz.communicationhttptestapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.sdaacademy.kudas.tomasz.communicationhttptestapp.adapter.TaskAdapter;
import com.sdaacademy.kudas.tomasz.communicationhttptestapp.dto.TaskDTO;
import com.sdaacademy.kudas.tomasz.communicationhttptestapp.model.Task;
import com.sdaacademy.kudas.tomasz.communicationhttptestapp.service.TaskService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnItemLongClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.messageList)
    ListView messageList;

    @BindView(R.id.messageText)
    EditText messageText;

    @BindView(R.id.sendMessageButton)
    Button sendMessageButton;

    private TaskService taskService;
    public List<Task> list = new ArrayList<>();
    public TaskAdapter arrayAdapter;
    private final String BASE_URL = "http://shrouded-fjord-81597.herokuapp.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        taskService = retrofit.create(TaskService.class);
        arrayAdapter = new TaskAdapter(this, list, taskService, -1);
        messageList.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();

        Call<List<TaskDTO>> call = taskService.findAllTaskByUser(8l);
        call.enqueue(new Callback<List<TaskDTO>>() {
            @Override
            public void onResponse(Call<List<TaskDTO>> call, Response<List<TaskDTO>> response) {
                list.addAll(TaskMaper.TaskDTOToTaskList((response.body())));
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<TaskDTO>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "No Connections", Toast.LENGTH_LONG).show();
            }
        });
    }


    @OnClick(R.id.sendMessageButton)
    void onSendMessageButtonClick() {

        Call<TaskDTO> callPutOne = taskService.putTask(TaskMaper.TextToTaskDTO(messageText.getText().toString()));
        callPutOne.enqueue(new Callback<TaskDTO>() {
            @Override
            public void onResponse(Call<TaskDTO> call, Response<TaskDTO> response) {
                arrayAdapter.add(TaskMaper.TaskDTOToTask(response.body()));
                arrayAdapter.notifyDataSetChanged();
                messageText.setText("");
            }

            @Override
            public void onFailure(Call<TaskDTO> call, Throwable t) {
            }
        });

    }

    @OnItemLongClick(R.id.messageList)
    public boolean deleteOnLongClick(final int position) {

        final Task taskD = (Task) messageList.getItemAtPosition(position);
        Toast.makeText(getApplicationContext(), "Element: " + taskD.getValue() + " deleted", Toast.LENGTH_LONG).show();
        Call<Void> delPutOne = taskService.deleteTaskId(taskD.getId());
        delPutOne.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                arrayAdapter.remove(taskD);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Element: " + taskD.getValue() + " NOT deleted", Toast.LENGTH_LONG).show();
            }
        });
        return true;
    }


    @OnItemClick(R.id.messageList)
    public void EditOClick(final int position) {
        final Task taskEdit = (Task) messageList.getItemAtPosition(position);
        Toast.makeText(getApplicationContext(), "Element: " + taskEdit.getValue() + " Edit !", Toast.LENGTH_LONG).show();
        arrayAdapter = new TaskAdapter(this, list, taskService, position);
        messageList.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();
    }

}
