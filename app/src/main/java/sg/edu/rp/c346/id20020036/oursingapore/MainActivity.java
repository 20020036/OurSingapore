package sg.edu.rp.c346.id20020036.oursingapore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etTitle, etAuthor, etNum;
    Button btnInsert, btnShowList;
    //RadioGroup rg;
    RatingBar rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitle = (EditText) findViewById(R.id.etTitle);
        etAuthor = (EditText) findViewById(R.id.etAuthor);
        etNum = (EditText) findViewById(R.id.etNum);
        btnInsert = (Button) findViewById(R.id.btnInsertBook);
        btnShowList = (Button) findViewById(R.id.btnShowList);
        //rg = (RadioGroup) findViewById(R.id.rgStars);
        rb = findViewById(R.id.ratingBar2);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = etTitle.getText().toString().trim();
                String author = etAuthor.getText().toString().trim();
                if (title.length() == 0 || author.length() == 0){
                    Toast.makeText(MainActivity.this, "Incomplete data", Toast.LENGTH_SHORT).show();
                    return;
                }


                String year_str = etNum.getText().toString().trim();
                int num = 0;
                try {
                    num = Integer.valueOf(year_str);
                } catch (Exception e){
                    Toast.makeText(MainActivity.this, "Invalid year", Toast.LENGTH_SHORT).show();
                    return;
                }

                DBHelper dbh = new DBHelper(MainActivity.this);
                String star = String.valueOf(getStars());
                String s = String.valueOf(star.charAt(0));
                int stars = Integer.parseInt(s);
                dbh.insertBook(title, author, num, stars);
                dbh.close();
                Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_LONG).show();

                etTitle.setText("");
                etAuthor.setText("");
                etNum.setText("");

            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(MainActivity.this, ListActivity.class);
                startActivity(i);
            }
        });

    }

    private float getStars()
    {
        float stars = rb.getRating();
        return stars;
    }



    /*private int getStars() {
        int stars = 1;
        switch (rg.getCheckedRadioButtonId()) {
            case R.id.radio1:
                stars = 1;
                break;
            case R.id.radio2:
                stars = 2;
                break;
            case R.id.radio3:
                stars = 3;
                break;
            case R.id.radio4:
                stars = 4;
                break;
            case R.id.radio5:
                stars = 5;
                break;
        }
        return stars;
    }*/

}
