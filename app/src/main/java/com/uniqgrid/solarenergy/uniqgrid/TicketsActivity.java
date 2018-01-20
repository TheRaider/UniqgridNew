package com.uniqgrid.solarenergy.uniqgrid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class TicketsActivity extends AppCompatActivity {

    RecyclerView rvTicketHistory;
    ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tickets);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        overridePendingTransition(R.anim.slide_in_up,R.anim.hold);

        rvTicketHistory = (RecyclerView) findViewById(R.id.rvTicketHistory);
        rvTicketHistory.setAdapter(new TicketsAdapter());
        rvTicketHistory.setLayoutManager(new LinearLayoutManager(TicketsActivity.this));


        if(getSupportActionBar()!=null){
            getSupportActionBar().setTitle("Tickets");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ivBack = (ImageView) findViewById(R.id.ivBack);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return true;
        }
    }


    class TicketsAdapter extends RecyclerView.Adapter<TicketsAdapter.TicketViewHolder>{


        class TicketViewHolder extends RecyclerView.ViewHolder{

            public TicketViewHolder(View itemView) {
                super(itemView);
            }
        }

        @Override
        public TicketViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View customView = getLayoutInflater().inflate(R.layout.item_ticket,parent,false);
            return new TicketViewHolder(customView);
        }

        @Override
        public void onBindViewHolder(TicketViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 5;
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.hold, R.anim.slide_down);
    }


}
