package khi.fast.log.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import khi.fast.log.R;
import khi.fast.log.model.Player;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.MyViewHolder> {
    private List<Player> players;

    public TestAdapter(List<Player> players) {
        this.players = players;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView playerName;
        public TextView playerPosition;

        public MyViewHolder(View view) {
            super(view);
            playerName = (TextView) view.findViewById(R.id.player_name);
            playerPosition = (TextView) view.findViewById(R.id.player_position);
        }
    }

    @Override
    public TestAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_test, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Player player = players.get(position);
        holder.playerName.setText(player.getName());
        holder.playerPosition.setText(player.getPosition());
    }



    @Override
    public int getItemCount() {
        return players.size();
    }
}