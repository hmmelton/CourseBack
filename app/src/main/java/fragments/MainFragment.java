package fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hmmelton.textrack.R;

/**
 * Created by harrison on 8/28/15.
 */
public class MainFragment extends android.support.v4.app.Fragment {

    public MainFragment() {

    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_main, container, false);

        // initialize main RecyclerView
        RecyclerView recycler = (RecyclerView) mainView.findViewById(R.id.bookList);
        setRecyclerView(recycler);

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
}
