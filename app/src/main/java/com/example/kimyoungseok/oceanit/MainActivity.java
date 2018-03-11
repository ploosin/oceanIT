package com.example.kimyoungseok.oceanit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "OceanIT.db", null, 1);
        Random random = new Random();
        ArrayList<Integer> db_data = new ArrayList<>();
        int i=0;

        for(int j=0;j<10;j++){
            db_data.add(random.nextInt(100));
            //db_data.add(j);
        }

        LineChart lineChart = (LineChart) findViewById(R.id.chart);
        ArrayList<Entry> entries = new ArrayList<>();

        for(int datum : db_data){
            entries.add(new Entry(i,datum));
            i++;
            dbHelper.insert(datum);
        }

        // DB에 있는 데이터 조회
        Button select = (Button) findViewById(R.id.moveDBPage);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, viewDB.class);
                startActivity(intent);
            }
        });

        LineDataSet dataset = new LineDataSet(entries, "# of Calls");

        LineData data = new LineData(dataset);
        dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
        /*dataset.setDrawCubic(true); //선 둥글게 만들기
        dataset.setDrawFilled(true); //그래프 밑부분 색칠*/

        lineChart.setData(data);
        //lineChart.animateY(5000);
    }
}