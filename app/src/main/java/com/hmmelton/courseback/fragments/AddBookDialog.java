package com.hmmelton.courseback.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.firebase.client.Firebase;
import com.hmmelton.courseback.CourseBackApplication;
import com.hmmelton.courseback.R;
import com.hmmelton.courseback.models.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by harrison on 7/12/15.
 * This method creates a dialog that allows the user to add a book to the database.
 */
public class AddBookDialog extends DialogFragment {

    @SuppressWarnings("unused")
    private final String TAG = "AddBookDialog";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.add_book_layout, null, false);

        Map<View, Integer> spinners = new HashMap<>();
        spinners.put(view.findViewById(R.id.add_book_condition), R.array.conditions_array);
        spinners.put(view.findViewById(R.id.add_book_annotations), R.array.annotations_array);
        // Initialize spinners
        initSpinners(spinners);

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view);
        builder.setPositiveButton(android.R.string.ok, (dialog, which) ->
                        addNewBook())
                .setNegativeButton(android.R.string.cancel, (dialog, which) ->
                        AddBookDialog.this.getDialog().cancel());
        return builder.create();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * This method gathers all information required to create a new Book object and save it to the
     * database.
     */
    private void addNewBook() {
        String title = getBookInfo(R.id.add_book_title);
        String isbn = getBookInfo(R.id.add_book_isbn);
        String edition = getBookInfo(R.id.add_book_edition);
        String price = getBookInfo(R.id.add_book_price);
        String condition = getSpinnerSelection(R.id.add_book_condition);
        String annotations = getSpinnerSelection(R.id.add_book_annotations);

        // TODO: create and add book
        // User currently logged in
        User user = CourseBackApplication.getUser();

        Date date = new Date();
        String id = user.getId() + date.getTime();

        //
        Map<String, Object> newBook = new HashMap<>();
        newBook.put("title", title);
        newBook.put("isbn", isbn);
        newBook.put("edition", edition);
        newBook.put("price", price);
        newBook.put("condition", condition);
        newBook.put("annotations", annotations);
        newBook.put("userId", user.getId());
        newBook.put("dateAdded", date);
        newBook.put("id", id);

        // Global database instance
        Firebase database = CourseBackApplication.getDatabase().child("books").child(id);

        // Upload new book to database
        database.setValue(newBook, (firebaseError, firebase) -> {
            if (firebaseError != null) {
                // TODO: make Toast work
                // Update the book list adapter to show new book
                MainFragment.updateAdapter();
            } else {
                // TODO: make Toast work
            }
        });

    }

    /**
     * This method retrieves information from the view with the passed resource id.
     * @param id Integer id of the view whose information is collected
     * @return String representation of the contents of the view
     */
    private String getBookInfo(int id) {
        return ((EditText) getDialog().findViewById(id))
                .getText()
                .toString()
                .trim();
    }

    /**
     * This method retrieves the selected item from the Spinner with the given ID.
     * @param id ID of Spinner whose selection is being retrieved
     * @return
     */
    private String getSpinnerSelection(int id) {
        return ((Spinner) getDialog().findViewById(id))
                .getSelectedItem()
                .toString();
    }

    /**
     * This method initializes Spinners in the UI.
     * @param spinners Integer arrays each holding the ID & string-array ID of Spinner & its
     *                 contents, respectively
     */
    private void initSpinners(Map<View, Integer> spinners) {
        for (View v : spinners.keySet()) {
            // Spinner used to select condition of book
            Spinner spinner = (Spinner) v;
            // Create adapter from string-array resource
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                    spinners.get(v), android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
        }
    }

}
