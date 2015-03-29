package manuele.bryan.lolwinrate.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import manuele.bryan.lolwinrate.Adapters.UserStatsPagerAdapter;
import manuele.bryan.lolwinrate.R;

public class UserStatsMainFragment extends Fragment {
    Context context;

    UserStatsPagerAdapter pagerAdapter;
    ViewPager viewPager;


    public static UserStatsMainFragment newInstance() {
        UserStatsMainFragment fragment = new UserStatsMainFragment();
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
        View view = inflater.inflate(R.layout.fragment_user_stats_main, container, false);

        viewPager = (ViewPager) view.findViewById(R.id.userStatsViewPager);
        pagerAdapter = new UserStatsPagerAdapter(getActivity(), getChildFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        return view;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.context = activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

}
