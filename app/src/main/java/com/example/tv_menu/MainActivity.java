package com.example.tv_menu;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listView1;
    ListView listView2;
    String[] list1;
    String[] list2_2;

    FragmentContents1 fragmentContents1;
    FragmentContents2_1 fragmentContents2_1;
    FragmentContents2_2 fragmentContents2_2;
    FragmentContents2_3 fragmentContents2_3;
    FragmentContents3 fragmentContents3;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView1 = (ListView)findViewById(R.id.listview_1depth);
        listView2 = (ListView)findViewById(R.id.listview_2depth);

        list1 = getResources().getStringArray(R.array.list1);
        list2_2 = getResources().getStringArray(R.array.list2_2);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list1);
        listView1.setAdapter(arrayAdapter);

        listView1.setOnItemClickListener(this);
        listView2.setOnItemClickListener(this);

        fragmentContents1 = new FragmentContents1();
        fragmentContents2_1 = new FragmentContents2_1();
        fragmentContents2_2 = new FragmentContents2_2();
        fragmentContents2_3 = new FragmentContents2_3();
        fragmentContents3 = new FragmentContents3();
        fragmentManager = getSupportFragmentManager();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if(position == 0) {
            if(parent == listView1) {
                if(listView2.getVisibility() == View.VISIBLE) {
                    Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_slide_in);
                    listView2.startAnimation(animation);
                }
                listView2.setVisibility(View.GONE);

                if(!fragmentContents1.isVisible()) {
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.main_container, fragmentContents1);
                    fragmentTransaction.commit();
                }
            } else if(parent == listView2 && !fragmentContents2_1.isVisible()) {
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_container, fragmentContents2_1);
                fragmentTransaction.commit();
            }
        } else if(position == 1) {
            if(parent == listView1) {
                listView2.setVisibility(View.VISIBLE);

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list2_2){
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        return super.getView(position, convertView, parent);
                    }
                };
                listView2.setAdapter(arrayAdapter);

                Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_slide_out);
                listView2.startAnimation(animation);

            } else if(parent == listView2 && !fragmentContents2_2.isVisible()) {
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_container, fragmentContents2_2);
                fragmentTransaction.commit();
            }
        } else if(position == 2) {
            if(parent == listView1) {
                if(listView2.getVisibility() == View.VISIBLE) {
                    Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_slide_in);
                    listView2.startAnimation(animation);
                }
                listView2.setVisibility(View.GONE);

                if(!fragmentContents3.isVisible()) {
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.main_container, fragmentContents3);
                    fragmentTransaction.commit();
                }
            } else if(parent == listView2 && !fragmentContents2_3.isVisible()) {
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_container, fragmentContents2_3);
                fragmentTransaction.commit();
            }
        }
    }
}
