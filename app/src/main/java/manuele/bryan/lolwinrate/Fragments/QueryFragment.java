package manuele.bryan.lolwinrate.Fragments;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import manuele.bryan.lolwinrate.R;

public class QueryFragment extends DialogFragment {
    public QueryFragmentListener queryFragmentListener;

    public interface QueryFragmentListener {
        public void onQuerySubmit(int sortBy, int region, int filter, int sortOrder, int queueType,
                                  int rankedTier, int time);
    }

    public static QueryFragment newInstance() {
        QueryFragment fragment = new QueryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_query, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onSubmitButton(int sortBy, int region, int filter, int sortOrder, int queueType,
                               int rankedTier, int time) {
        if (queryFragmentListener != null) {
            queryFragmentListener.onQuerySubmit(sortBy, region, filter, sortOrder,
                    queueType, rankedTier, time);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            queryFragmentListener = (QueryFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        queryFragmentListener = null;
    }

}
