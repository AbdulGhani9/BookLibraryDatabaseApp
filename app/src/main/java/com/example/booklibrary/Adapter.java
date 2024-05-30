package com.example.booklibrary;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    List<Model> modelList;
    OnItemCLickListener onItemCLickListener;

    public void setOnItemCLickListener(OnItemCLickListener onItemCLickListener) {
        this.onItemCLickListener = onItemCLickListener;
    }

    Context context;


    public Adapter(List<Model> modelList1) {
        modelList = modelList1;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler, null);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Model model = modelList.get(position);
        holder.author.setText(model.getAuthor());
//        Log.d("tagOn", "onBindViewHolder: "+model.getId());
        holder.bookId.setText(Integer.toString(model.getId()));
        holder.pages.setText(Integer.toString(model.getPages()));
        holder.bookTitle.setText(model.getTitle());
        holder.category.setText(model.getCategory());



    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView bookTitle, bookId, author, pages, category;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookTitle = itemView.findViewById(R.id.bookTitle);
            bookId = itemView.findViewById(R.id.book_id_txt);
            author = itemView.findViewById(R.id.bookAuthor);
            pages = itemView.findViewById(R.id.page);
            category=itemView.findViewById(R.id.category);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemCLickListener!=null){
                        int position = getAdapterPosition();
                       onItemCLickListener.onItemCLick(v,position);
                    }else {
                        Toast.makeText(itemView.getContext(), "null", Toast.LENGTH_SHORT).show();
                    }
                }
            });




        }
    }
        interface OnItemCLickListener {
            void onItemCLick(View view, int position);
        }

}
