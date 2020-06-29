package khi.fast.log.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import khi.fast.log.R;
import khi.fast.log.log_details.PollFragment;

public class PollAdapter extends RecyclerView.Adapter<PollAdapter.SingleViewHolder> {

    private int checkedPosition = 0;
    private Context context;

    public PollAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public SingleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.poll_list_item, viewGroup, false);

        return new SingleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SingleViewHolder singleViewHolder, int position) {
        singleViewHolder.bind(position);
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    class SingleViewHolder extends RecyclerView.ViewHolder {


        public TextView poll_text_view;
        public View view_poll;
        public ImageView line;

        SingleViewHolder(@NonNull View view) {
            super(view);
            poll_text_view = (TextView) view.findViewById(R.id.poll_text_view);
            view_poll = (View)view.findViewById(R.id.view_poll);
            line= (ImageView) view.findViewById(R.id.line);
        }

        void bind(int position) {
            System.out.println("Bind: 1");

            if(position==getItemCount()-1){
                view_poll.setVisibility(View.GONE);
            }

            if (checkedPosition == -1) {
                line.setVisibility(View.GONE);
            } else {
                if (checkedPosition == getAdapterPosition()) {
                    //line.setVisibility(View.VISIBLE);
                } else {
                    line.setVisibility(View.GONE);
                }
            }
            poll_text_view.setText("hello "+position);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PollFragment.submit.setVisibility(View.VISIBLE);
                    line.setVisibility(View.VISIBLE);

                    System.out.println("bind: "+checkedPosition+" "+getAdapterPosition());

                    if (checkedPosition != getAdapterPosition()) {
                        notifyItemChanged(checkedPosition);
                        checkedPosition = getAdapterPosition();
                    }
                }
            });
        }
    }

}
