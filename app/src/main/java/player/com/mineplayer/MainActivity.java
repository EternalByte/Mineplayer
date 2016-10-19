package player.com.mineplayer;

import android.content.ContentResolver;
//import android.database.Cursor;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listview;

    Ladapter mLadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = (ListView)findViewById(R.id.listview);

       ArrayList<Song> s= getAllSongs();

        ArrayList<String> stringArrayList=new ArrayList<>();

        if(s!=null) {
            mLadapter=new Ladapter(s,this);
            listview.setAdapter(mLadapter);
            mLadapter.notifyDataSetChanged();

        }
        }

//    @Override
  //  protected void onResume() {
    //    super.onResume();
    //}


    public ArrayList<Song> getAllSongs() {
ArrayList<Song> arrayList=new ArrayList<>();
        Uri allsongsuri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";
        String song_name;
        String fullpath;
        Uri p;
        Song s=null;

        String[] STAR = null;
        Cursor cursor = managedQuery(allsongsuri, STAR, selection, null, null);
        //or
        //Cursor cursor = getActivity().getContentResolver().query(allsongsuri, null, null, null, selection);


        if (cursor != null) {
            if (cursor.moveToFirst()) {

                do {
                     song_name = cursor
                            .getString(cursor
                                    .getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                    int song_id = cursor.getInt(cursor
                            .getColumnIndex(MediaStore.Audio.Media._ID));

                     fullpath = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                    String Duration = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
                    p= Uri.parse(fullpath);
                    s = new Song(song_name,p);
                    arrayList.add(s);
                    Log.e("Muttu", "Song Name ::" + song_name + " Song Id :" + song_id + " fullpath ::" + fullpath + " Duration ::" + Duration);

                } while (cursor.moveToNext());

            }
       //     cursor.close();

        }
return arrayList;
    }
        public class Song
        {
            String songName;
            Uri path;

            public Song(String song_name,Uri fullpath)
            {
                this.path=fullpath;
                this.songName=song_name;
            }
            public String getSong_name()
            {
                return songName;

            }
            public Uri getPath()
            {
                return path;
            }



        }
    }




//
//
//       public void getSongList() {
//            //query external audio
//            ContentResolver musicResolver = getContentResolver();
//            Uri musicUri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
//             Cursor musicCursor = musicResolver.query(musicUri, null, null, null, null);
//            //iterate over results if valid
//            if (musicCursor != null && musicCursor.moveToFirst()) {
//                //get columns
//                int titleColumn = musicCursor.getColumnIndex
//                        (android.provider.MediaStore.Audio.Media.TITLE);
//                int idColumn = musicCursor.getColumnIndex
//                        (android.provider.MediaStore.Audio.Media._ID);
//                int artistColumn = musicCursor.getColumnIndex
//                        (android.provider.MediaStore.Audio.Media.ARTIST);
//                //add songs to list
//                do {
//                    long thisId = musicCursor.getLong(idColumn);
//                    String thisTitle = musicCursor.getString(titleColumn);
//                    String thisArtist = musicCursor.getString(artistColumn);
//
//
//                    songList.add(new Song(thisId, thisTitle, thisArtist));
//                }
//                while (musicCursor.moveToNext());
//            }
//
//        }



