package com.hmmelton.textrack.fragments;

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

import com.hmmelton.textrack.R;
import com.hmmelton.textrack.adapters.BookListAdapter;
import com.hmmelton.textrack.utils.Authentication;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

/**
 * Created by harrison on 8/28/15.
 */
public class MainFragment extends android.support.v4.app.Fragment {

    private ProgressBar mProgressBar;

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

        // initialize main RecyclerView
        final RecyclerView recycler = (RecyclerView) mainView.findViewById(R.id.bookList);
        setRecyclerView(recycler);

        ParseQuery<ParseObject> query = new ParseQuery<>("Book");
        query.findInBackground(((objects, e) ->
            recycler.setAdapter(new BookListAdapter(objects))));

        final FloatingActionButton fab = (FloatingActionButton) mainView.findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            AddBookDialog fragment = new AddBookDialog();
            fragment.show(getActivity().getFragmentManager(), "test");
        });

        return mainView;
    }

    /**
     * This method initializes the passed RecyclerView.
     * @param recycler view to be initialized
     */
    private void setRecyclerView(RecyclerView recycler) {
        recycler.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity() );
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(llm);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.global, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_settings:
                break;
            case R.id.action_logout:
                mProgressBar.setVisibility(View.VISIBLE);
                ParseUser.logOut();
                Authentication.isUserSignedIn(getActivity());
                break;
            case R.id.action_refresh:
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
