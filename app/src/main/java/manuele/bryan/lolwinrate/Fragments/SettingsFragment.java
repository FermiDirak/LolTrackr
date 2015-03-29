package manuele.bryan.lolwinrate.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import manuele.bryan.lolwinrate.Activities.SplashScreenActivity;
import manuele.bryan.lolwinrate.Databases.PreferencesDataBase;
import manuele.bryan.lolwinrate.R;

public class SettingsFragment extends Fragment {
    private Context context;

    Button resetUserButton;

    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        resetUserButton = (Button) view.findViewById(R.id.settingsResetUserButton);

        resetUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferencesDataBase preferences = new PreferencesDataBase(context);
                preferences.emptyJSON();
                preferences.defaultUserInfo();

                Intent intent = new Intent(context, SplashScreenActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                TaskStackBuilder.create(context).addNextIntentWithParentStack(intent).startActivities();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.context = activity.getBaseContext();
    }

}
