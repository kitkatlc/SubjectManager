package kitkat.com.subjectmanager.database.subjectdatabase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import kitkat.com.subjectmanager.R;
import kitkat.com.subjectmanager.database.table.SubjectEntity;

public class SubjectAdapter extends BaseAdapter {

    private Context mcontext;
    private List<SubjectEntity> mdata;
    private class ViewHolder{
        public TextView room;
        public TextView lesson;
        public TextView coursename;
        private TextView day;
        private TextView month;
        private TextView weekday;
    }

    public SubjectAdapter(Context context, List<SubjectEntity> data){
        mcontext = context;
        mdata = data;
    }

    @Override
    public int getCount() {
        return mdata != null ? mdata.size() : 0;
    }

    @Override
    public SubjectEntity getItem(int position) {
        return mdata != null ? mdata.get(position) : null;
    }

    @Override
    /*
    /    public long getItemId(int position) {
        return mdata != null ? mdata.get(position).getId() : 0;
    }
     */
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(mcontext).inflate(R.layout.item_layout,parent,false);
            holder = new ViewHolder();
            holder.day = convertView.findViewById(R.id.first1);
            holder.month = convertView.findViewById(R.id.first2);
            holder.weekday = convertView.findViewById(R.id.first3);
            holder.coursename=convertView.findViewById(R.id.unity_coursename);
            holder.room=convertView.findViewById(R.id.unity_courseroom);
            holder.lesson=convertView.findViewById(R.id.unity_courselesson);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        SubjectEntity data = getItem(position);
        if(data != null){
            holder.day.setText(data.getDay1()+"日");
            holder.month.setText(data.getMonth1()+"月");
            holder.weekday.setText(data.getWEEK());
            holder.lesson.setText("第"+data.getLesson()+"节");
            holder.coursename.setText(data.getCoursename());
            holder.room.setText("教室"+data.getRoom());
        }

        return convertView;
    }

}

