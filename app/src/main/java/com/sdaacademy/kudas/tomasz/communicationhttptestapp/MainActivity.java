package com.sdaacademy.kudas.tomasz.communicationhttptestapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sdaacademy.kudas.tomasz.communicationhttptestapp.adapter.TaskAdapter;
import com.sdaacademy.kudas.tomasz.communicationhttptestapp.dto.TaskDTO;
import com.sdaacademy.kudas.tomasz.communicationhttptestapp.model.Task;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.messageList)
    ListView messageList;

    @BindView(R.id.messageText)
    EditText messageText;

    @BindView(R.id.sendMessageButton)
    Button sendMessageButton;
    List<Task> list = new ArrayList<>();
    ArrayAdapter arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        arrayAdapter = new TaskAdapter(this, list);
        messageList.setAdapter(arrayAdapter);
        AsyncClassGet asyncClassGet = new AsyncClassGet();
        asyncClassGet.execute();
        arrayAdapter.notifyDataSetChanged();
    }

    private class AsyncClassGet extends AsyncTask<Void, Void, List<TaskDTO>> {

        @Override
        protected List<TaskDTO> doInBackground(Void... params) {
            List<TaskDTO> results;
            String url = "https://shrouded-fjord-81597.herokuapp.com/api/task/all/8";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
            TaskDTO[] resultTab = restTemplate.getForObject(url, TaskDTO[].class);
            results = Arrays.asList(resultTab);
            return results;
        }

        @Override
        protected void onPostExecute(List<TaskDTO> taskDTO) {
            list.addAll(TaskMaper.TaskDTOToTask(taskDTO));
            arrayAdapter.notifyDataSetChanged();


        }
    }

        private class AsyncClassPut extends AsyncTask<String, Void, TaskDTO> {

            @Override
            protected TaskDTO doInBackground(String... params) {

                String url = "https://shrouded-fjord-81597.herokuapp.com/api/task/";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
                TaskDTO tempDTO = new TaskDTO(8l,false,params[0]);

                restTemplate.put(url, tempDTO);
                return null;
            }

            @Override
            protected void onPreExecute() {

                super.onPreExecute();
            }

            //        public void getResponse() {
//            String s = readStream();
//            Gson gson = new Gson();
//            List<Task> tasks = Gson.fromJson(s,)
//        }
    }

    @OnClick(R.id.sendMessageButton)
    void onSendMessageButtonClick() {
        AsyncClassPut asyncClassPut = new AsyncClassPut();
        asyncClassPut.execute(messageText.getText().toString());
    }


}
