package id.sheendev.qrbarcodescanner.app.module.history;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.sheendev.qrbarcodescanner.R;
import id.sheendev.qrbarcodescanner.data.module.database.ScannerData;
import id.sheendev.qrbarcodescanner.shared.util.DateUtils;


public class ScannerDataAdapter extends RecyclerView.Adapter<ScannerDataAdapter.MyViewHolder> {

    private List<ScannerData>      model;
    private setOnItemClickListener setOnItemClickListener;


    public ScannerDataAdapter(List<ScannerData> model) {
        this.model = model;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_scanner_data, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        ScannerData codeData = model.get(position);
        holder.txtData.setText(codeData.getData());
        holder.txtDate.setText(DateUtils.timestampToReadable(codeData.getDate(), "dd MMMM yyyy HH:mm"));

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_date)
        TextView  txtDate;
        @BindView(R.id.txt_data)
        TextView  txtData;
        @BindView(R.id.img_delete)
        ImageView imgDelete;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(view1 -> {
                if (setOnItemClickListener != null)
                    setOnItemClickListener.onClick(model.get(getAdapterPosition()));
            });
            imgDelete.setOnClickListener(v -> {
                if (setOnItemClickListener != null)
                    setOnItemClickListener.onDeleteClicked(model.get(getAdapterPosition()));
            });
        }
    }

    public void setSetOnItemClickListener(setOnItemClickListener setOnItemClickListener) {
        this.setOnItemClickListener = setOnItemClickListener;
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public interface setOnItemClickListener {
        public void onClick(ScannerData codeData);

        public void onDeleteClicked(ScannerData codeData);
    }
}
