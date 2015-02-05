package manuele.bryan.lolwinrate.Fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import manuele.bryan.lolwinrate.R;

public class ServerStatusFragment extends Fragment {
    Context context;

    private HttpServerStatus httpServerStatus;

    public static ServerStatusFragment newInstance() {
        ServerStatusFragment fragment = new ServerStatusFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
        httpServerStatus = new HttpServerStatus();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_server_status, container, false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
    }

    public void update() {
        httpServerStatus.cancel(true);
        httpServerStatus = new HttpServerStatus();
        httpServerStatus.execute();
    }


    private class HttpServerStatus extends AsyncTask<String, String, Integer[]> {
        private final String statusLink = "http://status.leagueoflegends.com/shards/";
        private final String statusCodeArray[] = {"na", "euw", "eune", "br", "tr",
                "ru", "lan", "las", "oce"};

        @Override
        protected void onPreExecute() {
        }
        @Override
        protected Integer[] doInBackground(String... params) {
            Integer[] output = new Integer[9];
            for(int i = 0; i < 9; i++) {
                try {
                    URL url = new URL(statusLink + statusCodeArray[i]);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    InputStream input = connection.getInputStream();
                    InputStreamReader inputReader = new InputStreamReader(input);
                    BufferedReader bufferedReader = new BufferedReader(inputReader);
                    String line = bufferedReader.readLine();
                    if(line.contains("\"name\":\"Game\",\"slug\":\"game\",\"status\":\"online\""))
                    {
                        output[i] = 1;
                    }
                    else
                    {
                        output[i] = 0;
                    }
                    bufferedReader.close();
                    inputReader.close();
                    input.close();
                    connection.disconnect();
                }
                catch(Exception e) {
                    return new Integer[]{-1, -1, -1, -1, -1, -1, -1, -1, -1};
                }
            }
            return output;
        }
        @Override
        protected void onPostExecute(Integer[] result) {
            //indicate server status by editing the views

            for(int i = 0; i < 9; i++) {
                switch(result[i]) {
                    case 0: break; //red
                    case 1: break; //green
                    case -1: break; //yellow
                }
            }
        }
    }

}
