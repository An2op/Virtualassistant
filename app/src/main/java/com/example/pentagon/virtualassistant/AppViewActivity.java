package com.example.pentagon.virtualassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

public class AppViewActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager recyclerViewLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_app_view);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        // Passing the column number 1 to show online one column in each row.
        recyclerViewLayoutManager = new GridLayoutManager(AppViewActivity.this, 1);

        recyclerView.setLayoutManager(recyclerViewLayoutManager);

        adapter = new AppsAdapter(AppViewActivity.this, new ApkInfoExtractor(AppViewActivity.this).GetAllInstalledApkInfo());

        recyclerView.setAdapter(adapter);
    }
    @Override
    public void onBackPressed() {
        Intent i=new Intent(AppViewActivity.this,MainActivity.class);
        startActivity(i);
        finish();

    }
}
