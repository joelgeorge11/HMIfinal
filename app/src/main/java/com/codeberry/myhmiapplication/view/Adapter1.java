package com.codeberry.myhmiapplication.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codeberry.myhmiapplication.R;

import java.util.List;

public class Adapter1 extends RecyclerView.Adapter<Adapter1.ViewHolder> {

    List<String> titles;
    List<Integer> images;
    LayoutInflater inflater;


    public Adapter1(ControlFragment ctx, List<String> titles, List<Integer> images){
        this.titles = titles;
        this.images = images;
        this.inflater = LayoutInflater.from(ctx.getContext());
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_grid_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(position==0) {
            holder.title.setText(titles.get(position));
            holder.gridIcon.setImageResource(images.get(0));
        }else{
            holder.title.setText(titles.get(position));
            holder.gridIcon.setImageResource(images.get(1));
        }
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView gridIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView2);
            gridIcon = itemView.findViewById(R.id.imageView2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), "You clicked "+titles.get(getAdapterPosition()),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
