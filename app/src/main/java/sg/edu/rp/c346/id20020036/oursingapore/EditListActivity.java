package sg.edu.rp.c346.id20020036.oursingapore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditListActivity extends AppCompatActivity {

    EditText etID, etTitle, etSingers, etYear;
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
        etSingers = (EditText) findViewById(R.id.etSingers);
        etYear = (EditText) findViewById(R.id.etYear);
        rb = findViewById(R.id.rb3);

        Intent i = getIntent();
        final sg.edu.rp.c346.id20020036.ourndpsongs.Song currentSong = (sg.edu.rp.c346.id20020036.ourndpsongs.Song) i.getSerializableExtra("song");

        etID.setText(currentSong.getId()+"");
        etTitle.setText(currentSong.getTitle());
        etSingers.setText(currentSong.getSingers());
        etYear.setText(currentSong.getYearReleased()+"");
        /*switch (currentSong.getStars()){
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
                sg.edu.rp.c346.id20020036.ourndpsongs.DBHelper dbh = new sg.edu.rp.c346.id20020036.ourndpsongs.DBHelper(sg.edu.rp.c346.id20020036.ourndpsongs.EditListActivity.this);
                currentSong.setTitle(etTitle.getText().toString().trim());
                currentSong.setSingers(etSingers.getText().toString().trim());
                int year = 0;
                try {
                    year = Integer.valueOf(etYear.getText().toString().trim());
                } catch (Exception e){
                    Toast.makeText(sg.edu.rp.c346.id20020036.ourndpsongs.EditListActivity.this, "Invalid year", Toast.LENGTH_SHORT).show();
                    return;
                }
                currentSong.setYearReleased(year);
                String star = String.valueOf(getStars());
                String s = String.valueOf(star.charAt(0));
                int stars = Integer.parseInt(s);
                currentSong.setStars(stars);
                //int selectedRB = rg.getCheckedRadioButtonId();
                //RadioButton rb = (RadioButton) findViewById(selectedRB);
                //currentSong.setStars(Integer.parseInt(rb.getText().toString()));
                int result = dbh.updateSong(currentSong);
                if (result>0){
                    Toast.makeText(sg.edu.rp.c346.id20020036.ourndpsongs.EditListActivity.this, "Song updated", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(sg.edu.rp.c346.id20020036.ourndpsongs.EditListActivity.this, "Update failed", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sg.edu.rp.c346.id20020036.ourndpsongs.DBHelper dbh = new sg.edu.rp.c346.id20020036.ourndpsongs.DBHelper(sg.edu.rp.c346.id20020036.ourndpsongs.EditListActivity.this);
                int result = dbh.deleteSong(currentSong.getId());
                if (result>0){
                    Toast.makeText(sg.edu.rp.c346.id20020036.ourndpsongs.EditListActivity.this, "Song deleted", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(sg.edu.rp.c346.id20020036.ourndpsongs.EditListActivity.this, "Delete failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private float getStars()
    {
        float stars = rb.getRating();
        return stars;
    }


}