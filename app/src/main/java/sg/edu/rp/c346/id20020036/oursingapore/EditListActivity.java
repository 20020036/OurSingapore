package sg.edu.rp.c346.id20020036.oursingapore;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditListActivity extends AppCompatActivity {

    EditText etID, etTitle, etAuthor, etNum;
    //RadioButton rb1, rb2, rb3, rb4, rb5;
    Button btnCancel, btnUpdate, btnDelete;
    //RadioGroup rg;
    RatingBar rb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_list);


        /*rb1 = (RadioButton) findViewById(R.id.radio1);
        rb2 = (RadioButton) findViewById(R.id.radio2);
        rb3 = (RadioButton) findViewById(R.id.radio3);
        rb4 = (RadioButton) findViewById(R.id.radio4);
        rb5 = (RadioButton) findViewById(R.id.radio5);
        rg = (RadioGroup) findViewById(R.id.rgStars);*/
        btnCancel = (Button) findViewById(R.id.buttonCancel);
        btnDelete = (Button) findViewById(R.id.buttonDelete);
        btnUpdate = (Button) findViewById(R.id.buttonUpdate);
        etID = (EditText) findViewById(R.id.etiD);
        etTitle = (EditText) findViewById(R.id.etTitle);
        etAuthor = (EditText) findViewById(R.id.etAuthor);
        etNum = (EditText) findViewById(R.id.etNum);
        rb = findViewById(R.id.rb3);

        Intent i = getIntent();
        final Books currentBook = (Books) i.getSerializableExtra("book");

        etID.setText(currentBook.getId()+"");
        etTitle.setText(currentBook.getTitle());
        etAuthor.setText(currentBook.getAuthor());
        etNum.setText(currentBook.getNum()+"");
        /*switch (currentBook.getStars()){
            case 5: rb5.setChecked(true);
                break;
            case 4: rb4.setChecked(true);
                break;
            case 3: rb3.setChecked(true);
                break;
            case 2: rb2.setChecked(true);
                break;
            case 1: rb1.setChecked(true);
        }*/




        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditListActivity.this);
                currentBook.setTitle(etTitle.getText().toString().trim());
                currentBook.setAuthor(etAuthor.getText().toString().trim());
                int num = 0;
                try {
                    num = Integer.valueOf(etNum.getText().toString().trim());
                } catch (Exception e){
                    Toast.makeText(EditListActivity.this, "Invalid year", Toast.LENGTH_SHORT).show();
                    return;
                }
                currentBook.setNum(num);
                String star = String.valueOf(getStars());
                String s = String.valueOf(star.charAt(0));
                int stars = Integer.parseInt(s);
                currentBook.setStars(stars);
                //int selectedRB = rg.getCheckedRadioButtonId();
                //RadioButton rb = (RadioButton) findViewById(selectedRB);
                //currentBook.setStars(Integer.parseInt(rb.getText().toString()));
                int result = dbh.updateBook(currentBook);
                if (result>0){
                    Toast.makeText(EditListActivity.this, "Song updated", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(EditListActivity.this, "Update failed", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(EditListActivity.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to delete the book\n" + currentBook.getTitle());
                myBuilder.setCancelable(true);
                myBuilder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBHelper dbh = new DBHelper(EditListActivity.this);
                        int result = dbh.deleteBook(currentBook.getId());
                        if (result > 0) {
                            Toast.makeText(EditListActivity.this, "Book deleted", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(EditListActivity.this, "Delete failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                myBuilder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(EditListActivity.this, "Delete failed", Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(EditListActivity.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to discard the changes?");
                myBuilder.setCancelable(true);
                myBuilder.setPositiveButton("DO NOT DISCARD", null);
                myBuilder.setNegativeButton("DISCARD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

    }
    private float getStars()
    {
        float stars = rb.getRating();
        return stars;
    }


}