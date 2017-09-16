package com.moldedbits.genesis.passage;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.moldedbits.genesis.R;
import com.moldedbits.genesis.models.Passage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MOLDEDBITS on 15-09-2017.
 */

public class PassageListAdaptor extends RecyclerView.Adapter<PassageListAdaptor.PassageViewHolder> {

    private final Context context;
    private List<Passage> passageList;
    private PassageClickListener clickListener ;

    PassageListAdaptor(Context context, PassageClickListener clickListener) {
        this.context = context;
        this.clickListener = clickListener;
    }

    @Override
    public PassageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.rv_passage, parent, false);
        return new PassageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PassageViewHolder holder, int position) {
        holder.tvCategoryName.setText(passageList.get(position).getDisplayName().getEnglish());
    }

    @Override
    public int getItemCount() {
            return passageList.size();
    }

    public void setPassageList(List<Passage> passages) {
        this.passageList = passages;
    }

    public interface PassageClickListener {
        void onItemClick(View view, int position);
    }
    class PassageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.passage_name)
        TextView tvCategoryName;
        @BindView(R.id.cvPassage)
        CardView cvPassage;

        PassageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
