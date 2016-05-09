package com.hmmelton.courseback.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.hmmelton.courseback.R;

/**
 * Created by harrison on 7/12/15.
 */
public class AddBookDialog extends DialogFragment {

    private final String TAG = "AddBookDialog";

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private Bitmap mImageBytes;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.add_book_layout, null, false);

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
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
            mImageBytes = (Bitmap) extras.get("data");
        }
    }

    /**
     * This method gathers all information required to create a new Book object and save it to the
     * database.
     */
    private void addNewBook() {
        String title = getBookInfo(R.id.add_book_title);

        String course = getBookInfo(R.id.add_book_condition);

        String isbn = getBookInfo(R.id.add_book_isbn);

        String edition = getBookInfo(R.id.add_book_edition);

        String price = getBookInfo(R.id.add_book_price);

        // TODO: create and add book
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
}
