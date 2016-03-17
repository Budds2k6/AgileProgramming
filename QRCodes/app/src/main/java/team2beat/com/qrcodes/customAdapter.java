package team2beat.com.qrcodes;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by arturpopov on 17/03/2016.
 */
public class customAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;

    public customAdapter(Context context, String[] values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        System.out.println("getView " + position + " " + convertView);
        TextView textView;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.not_on_reg, null);
            textView = (TextView)convertView.findViewById(R.id.secondLine);
            textView.setText(values[position]);
        }

       /*
        if (text.contains("Not on Register"))
        {
            rowView= inflater.inflate(R.layout.not_on_reg, parent, false);
        } else if (text.contains("Not Present"))
        {
            rowView=inflater.inflate(R.layout.not_on_reg, parent, false);
        }
        else
            rowView=inflater.inflate(R.layout.not_on_reg, parent, false);
        textView.setText(values[position]);
        // change the icon for Windows and iPhone
        String s = values[position];
        if (s.startsWith("iPhone")) {
            imageView.setImageResource(R.drawable.checkmark);
        } else {
            imageView.setImageResource(R.drawable.checkmark);
        }
*/
        return convertView;
    }
}
