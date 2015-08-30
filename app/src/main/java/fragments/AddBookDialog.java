package fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.hmmelton.textrack.R;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by harrison on 7/12/15.
 */
public class AddBookDialog extends DialogFragment {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private byte[] mImageBytes;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.add_book_layout, null);

        ((TextView) view.findViewById(R.id.add_book_image)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        addNewBook(builder);
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AddBookDialog.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
            mImageBytes = (byte[]) extras.get("data");
        }
    }

    /**
     * This method opens the device's camera for the user to take a picture of his/her book.
     */
    private void dispatchTakePictureIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        }
    }

    /**
     * This method gathers all information required to create a new Book object and save it to the
     * database.
     * @param builder
     */
    private void addNewBook(AlertDialog.Builder builder) {
        String title = ((TextView) getView().findViewById(R.id.add_book_title))
                .getText()
                .toString()
                .trim();

        String course = ((TextView) getView().findViewById(R.id.add_book_course))
                .getText()
                .toString()
                .trim();

        String isbn = ((TextView) getView().findViewById(R.id.add_book_isbn))
                .getText()
                .toString()
                .trim();

        String edition = ((TextView) getView().findViewById(R.id.add_book_edition))
                .getText()
                .toString()
                .trim();

        String price = ((TextView) getView().findViewById(R.id.add_book_price))
                .getText()
                .toString()
                .trim();

        ParseObject book = new ParseObject("Book");
        book.put("title", title);
        book.put("course", course);
        book.put("isbn", isbn);
        book.put("edition", edition);
        book.put("price", price);
        book.put("owner", ParseUser.getCurrentUser());
        book.put("image", new ParseFile(mImageBytes));

    }
}
