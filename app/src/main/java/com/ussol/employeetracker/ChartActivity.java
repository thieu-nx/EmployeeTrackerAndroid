package com.ussol.employeetracker;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Event;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.mikephil.charting.utils.Utils;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.SimpleCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;
import com.nightonke.boommenu.Util;
import com.ussol.employeetracker.helpers.ChartFragmentList;
import com.ussol.employeetracker.helpers.ContactToUser;
import com.ussol.employeetracker.helpers.DatabaseAdapter;
import com.ussol.employeetracker.helpers.GetSearchItemSetting;
import com.ussol.employeetracker.helpers.SelectUserAdapter;
import com.ussol.employeetracker.models.IExpGroup;
import com.ussol.employeetracker.models.MasterConstants;
import com.ussol.employeetracker.models.SelectUser;
import com.ussol.employeetracker.models.User;
import com.ussol.employeetracker.utils.ShowAlertDialog;
import com.nightonke.boommenu.BoomMenuButton;

public class ChartActivity extends Activity implements OnItemClickListener ,OnClickListener{

    private static int[] imageResources = new int[]{R.drawable.expandable_list,R.drawable.file , R.drawable.excel};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,               WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.chart_activity);

        // initialize the utilities
        //Utils.init(this);

        ArrayList<ContentItem> objects = new ArrayList<ContentItem>();

        objects.add(new ContentItem("Thống kê nghỉ việc theo tháng năm", "Biểu đồ so sánh nghỉ việc trong 3 năm gần nhất."));
        
        objects.add(new ContentItem("Thống kê nhân viên", "Biểu đồ thống kê nhân sự làm việc,phiên dịch,thử việc"));
        
        objects.add(new ContentItem("Các biểu đồ thống kê nhân viên", "Biểu đồ thống kê nhân sự làm việc,phiên dịch,thử việc"));
        
        objects.add(new ContentItem("Thống kê nhân sự lập trình", "Biểu đồ so sánh tăng giảm LTV trong 3 năm gần nhất."));
        
        objects.add(new ContentItem("Thống kê số LTV thử việc-chính thức theo năm", "Biểu đồ so sánh nhân viên thử việc-chính thức trong 3 năm gần nhất."));
        
        MyAdapter adapter = new MyAdapter(this, objects);

        ListView lv = (ListView) findViewById(R.id.listViewChart);
        assert lv != null;
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> av, View v, int pos, long arg3) {

        Intent i;

        switch (pos) {
            case 0:
                i = new Intent(this, ChartYasumiYearMonthActivity.class);
                startActivity(i);
                break;
            case 1:
                i = new Intent(this, ChartStaffStatusStackedBarActivity.class);
                startActivity(i);
                break;
            case 2:
                i = new Intent(this, ChartFragmentList.class);
                startActivity(i);
                break;
                
            case 3:
                i = new Intent(this, ChartStaffStatusTreeYearMonthActivity.class);
                startActivity(i);
                break;
                
                
            case 4:
                i = new Intent(this, ChartStaffStatusWorkingTrainingThreeYearActivity.class);
                startActivity(i);
                break;
                /*
            case 5:
                i = new Intent(this, PieChartActivity.class);
                startActivity(i);
                break;
            case 6:
                i = new Intent(this, ScatterChartActivity.class);
                startActivity(i);
                break;
            case 7:
                i = new Intent(this, BubbleChartActivity.class);
                startActivity(i);
                break;
            case 8:
                i = new Intent(this, StackedBarActivity.class);
                startActivity(i);
                break;
            case 9:
                i = new Intent(this, StackedBarActivityNegative.class);
                startActivity(i);
                break;
            case 10:
                i = new Intent(this, AnotherBarActivity.class);
                startActivity(i);
                break;
            case 11:
                i = new Intent(this, MultiLineChartActivity.class);
                startActivity(i);
                break;
            case 12:
                i = new Intent(this, BarChartActivityMultiDataset.class);
                startActivity(i);
                break;
            case 13:
                // i = new Intent(this, DrawChartActivity.class);
                // startActivity(i);

                AlertDialog.Builder b = new AlertDialog.Builder(this);
                b.setTitle("Feature not available");
                b.setMessage("Due to recent changes to the data model of the library, this feature is temporarily not available.");
                b.setPositiveButton("OK", null);
                b.create().show();
                break;
            case 14:
                i = new Intent(this, SimpleChartDemo.class);
                startActivity(i);
                break;
            case 15:
                i = new Intent(this, ListViewBarChartActivity.class);
                startActivity(i);
                break;
            case 16:
                i = new Intent(this, ListViewMultiChartActivity.class);
                startActivity(i);
                break;
            case 17:
                i = new Intent(this, InvertedLineChartActivity.class);
                startActivity(i);
                break;
            case 18:
                i = new Intent(this, CandleStickChartActivity.class);
                startActivity(i);
                break;
            case 19:
                i = new Intent(this, CubicLineChartActivity.class);
                startActivity(i);
                break;
            case 20:
                i = new Intent(this, RadarChartActivitry.class);
                startActivity(i);
                break;
            case 21:
                i = new Intent(this, LineChartActivityColored.class);
                startActivity(i);
                break;
            case 22:
                i = new Intent(this, RealtimeLineChartActivity.class);
                startActivity(i);
                break;
            case 23:
                i = new Intent(this, DynamicalAddingActivity.class);
                startActivity(i);
                break;
            case 24:
                i = new Intent(this, PerformanceLineChart.class);
                startActivity(i);
                break;
            case 25:
                i = new Intent(this, BarChartActivitySinus.class);
                startActivity(i);
                break;
            case 26:
                i = new Intent(this, ScrollViewActivity.class);
                startActivity(i);
                break;*/
        }

        overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);
    }

   
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent i = null;

        switch (item.getItemId()) {
            case R.id.menu_search_item_settings:
            	i = new Intent(this, SearchItemMainActivity.class);
				startActivityForResult(i,MasterConstants.CALL_SEARCH_ITEM_ACTIVITY_CODE);
                break;
            case R.id.menu_sort_item_settings:
            	i = new Intent(this, DragNDropListActivity.class);
				startActivityForResult(i , MasterConstants.CALL_SORT_ITEM_ACTIVITY_CODE);
				break;
            case R.id.menu_config_item_settings:
    			i = new Intent(this, SystemConfigPreferencesActivity.class);
    			startActivityForResult(i , MasterConstants.CALL_CONFIG_ITEM_ACTIVITY_CODE);
    			break;
                /*
            case R.id.blog:
                i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://www.xxmassdeveloper.com"));
                startActivity(i);
                break;
            case R.id.website:
                i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://at.linkedin.com/in/philippjahoda"));
                startActivity(i);
                break;*/
        }

        return true;
    }

    /**▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼
	 * onClick
	 * 
	 ▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲*/
	@Override
	public void onClick(View v) {
		/** phân nhánh xử lý theo từng button*/
		switch (v.getId()) {
			case R.id.btnSearchItem:
				Intent intent = new Intent(this, SearchItemMainActivity.class);
				startActivityForResult(intent,MasterConstants.CALL_SEARCH_ITEM_ACTIVITY_CODE);
				break;
			
			case R.id.btnSortList:
				Intent intSort = new Intent(this, DragNDropListActivity.class);
				startActivityForResult(intSort , MasterConstants.CALL_SORT_ITEM_ACTIVITY_CODE);
				break;
			
		}
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch(requestCode){
			case MasterConstants.CALL_SEARCH_ITEM_ACTIVITY_CODE:
				if (resultCode==RESULT_OK){
	    	       
				}
				break;
			case MasterConstants.CALL_SORT_ITEM_ACTIVITY_CODE:
				if (resultCode==RESULT_OK){
	    	        
				}
				break;
			case MasterConstants.CALL_CONFIG_ITEM_ACTIVITY_CODE:
				if (resultCode==RESULT_OK){
	    	        
				}
				break;
		}
	}	
	
    private class ContentItem {
        String name;
        String desc;

        public ContentItem(String n, String d) {
            name = n;
            desc = d;
        }
    }

    private class MyAdapter extends ArrayAdapter<ContentItem> {

        public MyAdapter(Context context, List<ContentItem> objects) {
            super(context, 0, objects);
        }

        /*
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ContentItem c = getItem(position);

            ViewHolder holder = null;

            if (convertView == null) {

                holder = new ViewHolder();

                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, null);
                holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
                holder.tvDesc = (TextView) convertView.findViewById(R.id.tvDesc);

                convertView.setTag(holder);

            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.tvName.setText(c.name);
            holder.tvDesc.setText(c.desc);

            return convertView;
        }

        private class ViewHolder {

            TextView tvName, tvDesc;
        }
    */
        @Override
        public View getView(final int position, View convertView, final ViewGroup parent) {

            ContentItem c = getItem(position);

            final ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.chart_item, null);

                viewHolder = new ViewHolder();
                viewHolder.text = (TextView) convertView.findViewById(R.id.text);
                //viewHolder.bmb1 = (BoomMenuButton) convertView.findViewById(R.id.bmb1);
                viewHolder.bmb2 = (BoomMenuButton) convertView.findViewById(R.id.bmb2);
                //viewHolder.bmb3 = (BoomMenuButton) convertView.findViewById(R.id.bmb3);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.text.setText(c.name);

            /*
            viewHolder.bmb1.clearBuilders();
            for (int i = 0; i < viewHolder.bmb1.getPiecePlaceEnum().pieceNumber(); i++)
                viewHolder.bmb1.addBuilder(BuilderManager.getSimpleCircleButtonBuilder());
            */
            viewHolder.bmb2.clearBuilders();
            for (int i = 0; i < viewHolder.bmb2.getPiecePlaceEnum().pieceNumber(); i++)
                viewHolder.bmb2.addBuilder(new HamButton.Builder()
                        .normalImageRes(getImageResource())
                        .normalTextRes(getTextDisplay(i))
                        .subNormalTextRes(getTextDisplay(i))
                        .listener(new OnBMClickListener() {
                            @Override
                            public void onBoomButtonClick(int index) {
                                    // When the boom-button corresponding this builder is clicked.
                                    switch (index) {
                                        case 0:
                                            Intent intentExpandable = new Intent(getApplicationContext(), ExpandableListUserActivity.class);
                                            Bundle bundleExpandable = new Bundle();
                                            switch (position){
                                                case  0 :
                                                    /**code cua group nghi viec theo nam*/
                                                    bundleExpandable.putInt(DatabaseAdapter.KEY_EXPANDABLE_GROUP, IExpGroup.EXP_GROUP_YASUMI_YEAR);
                                                    /**gán vào bundle để gửi cùng với intent */
                                                    intentExpandable.putExtras(bundleExpandable);
                                                    break;

                                                case 1:
                                                    /**code cua group*/
                                                    bundleExpandable.putInt(DatabaseAdapter.KEY_EXPANDABLE_GROUP, IExpGroup.EXP_GROUP_DEPT);
                                                    /**gán vào bundle để gửi cùng với intent */
                                                    intentExpandable.putExtras(bundleExpandable);
                                                    break;

                                                case 2:
                                                    /**code cua group*/
                                                    bundleExpandable.putInt(DatabaseAdapter.KEY_EXPANDABLE_GROUP, IExpGroup.EXP_GROUP_DEPT);
                                                    /**gán vào bundle để gửi cùng với intent */
                                                    intentExpandable.putExtras(bundleExpandable);
                                                    break;
                                                case 3:
                                                    /**code cua group*/
                                                    bundleExpandable.putInt(DatabaseAdapter.KEY_EXPANDABLE_GROUP, IExpGroup.EXP_GROUP_DEPT);
                                                    /**gán vào bundle để gửi cùng với intent */
                                                    intentExpandable.putExtras(bundleExpandable);
                                                    break;
                                                case 4:
                                                    /**code cua group nhan chinh thuc-hu viec*/
                                                    bundleExpandable.putInt(DatabaseAdapter.KEY_EXPANDABLE_GROUP, IExpGroup.EXP_GROUP_CONTRACT_YEAR);
                                                    /**gán vào bundle để gửi cùng với intent */
                                                    intentExpandable.putExtras(bundleExpandable);
                                                    break;
                                                default:
                                                    /**code cua group*/
                                                    bundleExpandable.putInt(DatabaseAdapter.KEY_EXPANDABLE_GROUP, IExpGroup.EXP_GROUP_DEPT);
                                                    /**gán vào bundle để gửi cùng với intent */
                                                    intentExpandable.putExtras(bundleExpandable);
                                                    break;
                                            }

                                            startActivity(intentExpandable);
                                            break;

                                        case 1:
                                            //Intent intSort = new Intent(getContext(), DragNDropListActivity.class);
                                            //startActivityForResult(intSort, MasterConstants.CALL_SORT_ITEM_ACTIVITY_CODE);
                                            Toast.makeText(getContext(),"Not implement",Toast.LENGTH_SHORT);
                                            break;
                                        case 2:
                                            Intent intentExcelReport = new Intent(getApplicationContext(), GenerateReport.class);
                                            Bundle bundleExcelReport = new Bundle();
                                            switch (position){
                                                case  0 :
                                                    /**code cua group nghi viec theo nam*/
                                                    bundleExcelReport.putInt(DatabaseAdapter.KEY_EXPANDABLE_GROUP, IExpGroup.EXP_GROUP_YASUMI_YEAR);
                                                    /**gán vào bundle để gửi cùng với intent */
                                                    intentExcelReport.putExtras(bundleExcelReport);
                                                    break;

                                                default:
                                                    /**code cua group*/
                                                    bundleExcelReport.putInt(DatabaseAdapter.KEY_EXPANDABLE_GROUP, IExpGroup.EXP_GROUP_DEPT);
                                                    /**gán vào bundle để gửi cùng với intent */
                                                    intentExcelReport.putExtras(bundleExcelReport);
                                                    break;
                                            }

                                            startActivity(intentExcelReport);
                                            break;
                                    }
                                }
                        })
                );

            /*
            viewHolder.bmb3.clearBuilders();
            for (int i = 0; i < viewHolder.bmb3.getButtonPlaceEnum().buttonNumber(); i++)
                viewHolder.bmb3.addBuilder(BuilderManager.getSimpleCircleButtonBuilder());
            */
            return convertView;
        }

        class ViewHolder {
            TextView text;
            BoomMenuButton bmb1;
            BoomMenuButton bmb2;
            BoomMenuButton bmb3;
        }

        private int imageResourceIndex = 0;
        private int textDisplayResourceIndex = 0;
        private int getImageResource() {
            if (imageResourceIndex >= imageResources.length) imageResourceIndex = 0;
            return imageResources[imageResourceIndex++];
        }
        private int getTextDisplay(int i){
            int idx =0;
            switch (i){
                case 0 :
                    idx= R.string.title_activity_chart_bmp_to_exp_user_list;
                    break;
                case 1 :
                    idx= R.string.title_activity_chart_bmb_to_text_file;
                break;
                case 2 :
                    idx= R.string.title_activity_chart_bmb_to_excel_file;
                break;

            }return idx;
        }
    }
}

