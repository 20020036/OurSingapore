package sg.edu.rp.c346.id20020036.oursingapore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Books> versionList;

    public CustomAdapter(Context context, int resource, ArrayList<Books> objects) {
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        versionList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater)parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(layout_id, parent, false);

        TextView tvTitle = rowView.findViewById(R.id.tvTitle);
        TextView tvNum = rowView.findViewById(R.id.tvNum);

        TextView tvAuthor = rowView.findViewById(R.id.tvAuthor);
        RatingBar rb = rowView.findViewById(R.id.ratingBar);
        ImageView iv = rowView.findViewById(R.id.iv);

        Books currentVersion = versionList.get(position);

        int year = Integer.parseInt(currentVersion.getNum());
        tvTitle.setText(currentVersion.getTitle());
        tvNum.setText(currentVersion.getNum());
        if(year < 2019)
        {
            iv.setVisibility(View.INVISIBLE);
        }

        tvAuthor.setText(currentVersion.getAuthor());
        rb.setRating(currentVersion.getStars());

        return rowView;
    }

}

