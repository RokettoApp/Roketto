package it.rokettoapp.roketto.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import it.rokettoapp.roketto.R;
import it.rokettoapp.roketto.model.Program;

public class ProgramRecyclerViewAdapter
        extends RecyclerView.Adapter<ProgramRecyclerViewAdapter.ProgramViewHolder> {

    private final List<Program> mProgramList;
    private final Context mContext;

    public ProgramRecyclerViewAdapter(Context context, List<Program> programList) {

        this.mProgramList = programList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ProgramViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        view = layoutInflater.inflate(R.layout.recycleview_row_layout, parent, false);

        return new ProgramViewHolder(view, p -> {

//            Intent intent = new Intent(mContext, ProgramActivity.class);
//            intent.putExtra("Program", mProgramList.get(p));
//            mContext.startActivity(intent);
        });
    }

    @Override
    public void onBindViewHolder(@NonNull ProgramViewHolder holder, int position) {

        Program program = mProgramList.get(position);

        holder.name.setText(program.getName());
        holder.description.setText(program.getDescription());

        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM", Locale.US);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(program.getStartDate());
        holder.startDateDay.setText(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
        holder.startDateMonth.setText(dateFormat.format(calendar.get(Calendar.MONTH)));
        holder.startDateYear.setText(String.valueOf(calendar.get(Calendar.YEAR)));
    }

    @Override
    public int getItemCount() {

        return mProgramList.size();
    }

    public static class ProgramViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        public TextView name;
        public TextView description;
        public Button event_button;
        public TextView startDateDay;
        public TextView startDateMonth;
        public TextView startDateYear;

//        CardView cardView;
        ProgramRecyclerViewAdapter.ClickListener listener;

        public ProgramViewHolder(View itemView,
                                 ProgramRecyclerViewAdapter.ClickListener listener) {

            super(itemView);
            this.listener = listener;

            name = (TextView) itemView.findViewById(R.id.event_title);
            description = (TextView) itemView.findViewById(R.id.event_d);
//            cardView = (CardView) itemView.findViewById(R.id.event_card);
            event_button = (Button) itemView.findViewById(R.id.idbutton);
            event_button.setOnClickListener(this);

            View date = itemView.findViewById(R.id.date);
            startDateDay = date.findViewById(R.id.txtGiorno);
            startDateMonth = date.findViewById(R.id.txtMese);
            startDateYear = date.findViewById(R.id.txtAnno);
        }

        @Override
        public void onClick(View v) {

            listener.onClick(this.getLayoutPosition());
        }
    }

    public interface ClickListener{

        void onClick(int p);
    }
}
