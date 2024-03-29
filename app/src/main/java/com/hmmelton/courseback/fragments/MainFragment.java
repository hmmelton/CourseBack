package com.hmmelton.courseback.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.hmmelton.courseback.R;
import com.hmmelton.courseback.adapters.BookListAdapter;
import com.hmmelton.courseback.utils.Authentication;

/**
 * Created by harrison on 8/28/15.
 */
public class MainFragment extends android.support.v4.app.Fragment {

    @SuppressWarnings("unused")
    private final String TAG = "MainFragment";

    private static ProgressBar mProgressBar;
    private static RecyclerView mContent;

    public MainFragment() {

    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_main, container, false);
        setHasOptionsMenu(true);

        mProgressBar = (ProgressBar) mainView.findViewById(R.id.main_progress_bar);

        // initialize main RecyclerView
        mContent = (RecyclerView) mainView.findViewById(R.id.bookList);
        setRecyclerView();
        setAdapter();

        // initialize FloatingActionButton used to add cards to the database
        final FloatingActionButton fab = (FloatingActionButton) mainView.findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            AddBookDialog fragment = new AddBookDialog();
            fragment.show(getActivity().getFragmentManager(), "test");
        });

        return mainView;
    }

    /**
     * This method initializes the passed RecyclerView.
     */
    private void setRecyclerView() {
        mContent.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity() );
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mContent.setLayoutManager(llm);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                break;
            case R.id.action_logout:
                mProgressBar.setVisibility(View.VISIBLE);
                Authentication.unauthorize(); // log user out
                Authentication.isUserSignedIn(getActivity()); // takes user back to log in screen
                break;
            case R.id.action_refresh:
                setAdapter();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * This method sets the adapter for the View containing books available for sale.
     */
    @SuppressWarnings("unchecked")
    private void setAdapter() {
        mProgressBar.setVisibility(View.VISIBLE);
        // TODO: query all books in database & set up adapter with them

    }

    /**
     * This method adds new books to the
     */
    public static void updateAdapter() {
        // get adapter of current list
        BookListAdapter adapter = ((BookListAdapter) mContent.getAdapter());

        mProgressBar.setVisibility(View.VISIBLE);
        // query for new books
        // TODO: query new books & update adapter
    }
}
