package com.moldedbits.genesis.passage;

import android.content.Context;
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


public class PassageListAdaptor extends RecyclerView.Adapter<PassageListAdaptor.PassageViewHolder>
        implements View.OnClickListener {

    private final Context context;
    private List<Passage> passageList;
    private PassageSelectedListener passageSelectedListener;

    PassageListAdaptor(Context context, PassageSelectedListener passageSelectedListener) {
        this.context = context;
        this.passageSelectedListener = passageSelectedListener;
    }

    @Override
    public PassageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.rv_category, parent, false);
        return new PassageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PassageViewHolder holder, int position) {
        holder.categorySpanish.setText(passageList.get(position).getDisplayName().getSpanish());
        holder.categoryEnglish.setText(passageList.get(position).getDisplayName().getEnglish());
        holder.status.setText(passageList.get(position).getDifficulty());
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return passageList.size();
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        passageSelectedListener.onPassageSelected(position);
    }

    void setPassageList(List<Passage> passages) {
        this.passageList = passages;
    }

    public interface PassageSelectedListener {
        void onPassageSelected(int passageIndex);
    }

    static class PassageViewHolder extends RecyclerView.ViewHolder  {

        @BindView(R.id.title_original)
        TextView categorySpanish;

        @BindView(R.id.title_translation)
        TextView categoryEnglish;

        @BindView(R.id.status)
        TextView status;

        PassageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
