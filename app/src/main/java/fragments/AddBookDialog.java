package fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hmmelton.textrack.R;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.io.ByteArrayOutputStream;

/**
 * Created by harrison on 7/12/15.
 */
public class AddBookDialog extends DialogFragment {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private Bitmap mImageBytes;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.add_book_layout, null);

        ((TextView) view.findViewById(R.id.add_book_image)).setOnClickListener(v ->
                dispatchTakePictureIntent());

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view);
        builder.setPositiveButton(android.R.string.ok, (dialog, which) ->
                        addNewBook(builder))
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
        String title = getBookInfo(R.id.add_book_title);

        String course = getBookInfo(R.id.add_book_course);

        String isbn = getBookInfo(R.id.add_book_isbn);

        String edition = getBookInfo(R.id.add_book_edition);

        String price = getBookInfo(R.id.add_book_price);

        ParseObject book = new ParseObject("Book");
        book.put("title", title);
        book.put("course", course);
        book.put("isbn", isbn);
        book.put("edition", edition);
        book.put("price", price);
        book.put("owner", ParseUser.getCurrentUser());
        book.put("image", new ParseFile(bitmapToByteArray()));
        book.saveInBackground();
    }

    /**
     * This method converts an Bitmap to a byte array, allowing the image to be stored in Parse.
     * @return byte array of image data
     */
    private byte[] bitmapToByteArray() {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        mImageBytes.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
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
