package turi.mycompanyapp.manzi;

import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements GetJSONdata.OnDataAvailable,
        RecyclerClickListener.OnRecyclerClickListener {

    private static final String TAG = "MainActivity";
    private ManziRecyclerViewAdapter mManziRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: starts");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activateToolbar(false);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addOnItemTouchListener(new RecyclerClickListener(this, recyclerView, this));

        mManziRecyclerViewAdapter = new ManziRecyclerViewAdapter(new ArrayList<Photo>(), this);
        recyclerView.setAdapter(mManziRecyclerViewAdapter);

        Log.d(TAG, "onCreate: ends");
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume starts");
        super.onResume();
        GetJSONdata getJSONdata = new GetJSONdata(this,"https://www.flickr.com/services/feeds/photos_public.gne", "en-us", true);
//        getJSONdata.executeOnSameThread("android, nougat");
        getJSONdata.execute("android, nougat");
        Log.d(TAG, "onResume ends");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        Log.d(TAG, "onCreateOptionsMenu() returned: " + true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if(id == R.id.action_search){
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
            return true;
        }

        Log.d(TAG, "onOptionsItemSelected() returned: returned");
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDataAvailable(List<Photo> data, DownloadStatus status){
        Log.d(TAG, "onDataAvailable starts");
        if(status == DownloadStatus.OK){
            mManziRecyclerViewAdapter.loadNewData(data);
            Log.d(TAG, "onDownloadComplete: data is " + data);
        } else {
//            download or processing failed
            Log.e(TAG, "onDownloadComplete: failed with status " + status);
        }
        Log.d(TAG, "onDataAvailable ends");
    }

    @Override
    public void onItemClicked(View view, int position) {
        Log.d(TAG, "onItemClicked: starts");
        Toast.makeText(this, "Normal Tap at Position " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongClicked(View view, int position) {
        Log.d(TAG, "onItemClicked: starts");
//        Toast.makeText(this, "Long Tap at position " + position, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, PhotoDetailActivity.class);
        intent.putExtra(PHOTO_TRANSFER,mManziRecyclerViewAdapter.getPhoto(position));
        startActivity(intent);
    }
}

