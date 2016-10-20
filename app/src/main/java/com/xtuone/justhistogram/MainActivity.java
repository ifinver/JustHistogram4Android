package com.xtuone.justhistogram;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        JustHistogram jh = (JustHistogram) findViewById(R.id.just_histogram);

        List<Summery> summeryList = new ArrayList<>();
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        int day = c.get(Calendar.DAY_OF_MONTH);
        for (int i = 1; i <= 15; i++) {
            Summery temp = new Summery();
            temp.setTimeInMillis(c.getTimeInMillis());
            c.set(Calendar.DAY_OF_MONTH, day - i);

            temp.setRec_sys_wifi(23+i);
            temp.setSend_sys_wifi(16+i);


            temp.setSend_mine_wifi(6+i);
            temp.setRec_mine_wifi(9+i);
            summeryList.add(temp);
        }


        jh.setData(summeryList,false);
    }
}
