package player.com.mineplayer;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ananthakrishna on 10-09-2016.
 */
class Ladapter extends BaseAdapter
{

    ArrayList<MainActivity.Song> data;
    Context context;
    LayoutInflater layoutInflater;


    public Ladapter(ArrayList<MainActivity.Song> datas, Context context) {
        super();
        this.data = datas;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        convertView= layoutInflater.inflate(R.layout.reslayout, null);

        TextView txt=(TextView)convertView.findViewById(R.id.l_text);

        txt.setText(data.get(position).getSong_name());
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context,Player.class);
                i.putExtra("SONG_URI",data.get(position).getPath().toString());
                context.startActivity(i);
            }
        });



        return convertView;

    }

}
