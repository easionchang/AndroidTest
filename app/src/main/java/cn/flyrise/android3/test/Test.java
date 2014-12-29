
package cn.flyrise.android3.test;

import cn.flyrise.android3.test.cache.DiskCacheActivity;
import cn.flyrise.android3.test.cache.ImageDiskCacheActivity;
import cn.flyrise.android3.test.compnent.UseActivity;
import cn.flyrise.android3.test.concurrent.productconsumer.TestPCActivity;
import cn.flyrise.android3.test.concurrent.readerwriter.ReaderWriterTestActivity;
import cn.flyrise.android3.test.concurrent.readerwriter2.ReaderWriterTest2Activity;
import cn.flyrise.android3.test.drawer.MyDrawerTest;
import cn.flyrise.android3.test.effectiveloadbitmap.LoadBitmapActivity;
import cn.flyrise.android3.test.effectiveloadbitmap.LoadBitmapAsyncActivity;
import cn.flyrise.android3.test.effectiveloadbitmap.LoadBitmapAsyncForListActivity;
import cn.flyrise.android3.test.expendlist.CustomExpendlistActivity;
import cn.flyrise.android3.test.expendlist.MyExpendlistActivity;
import cn.flyrise.android3.test.expendlist.ExpendlistWithSimpleAdapter;
import cn.flyrise.android3.test.expendlist.SimpleExpendlist;
import cn.flyrise.android3.test.imageView.TestImageViewActivity;
import cn.flyrise.android3.test.json.TestJsonActivity;
import cn.flyrise.android3.test.list.DragSwitchActivity;
import cn.flyrise.android3.test.list.PullRefresh;
import cn.flyrise.android3.test.viewpager.PagerTab2Activity;
import cn.flyrise.android3.test.viewpager.PagerTabActivity;
import cn.flyrise.android3.test.viewpager.PagerTitleDemoActivity;
import cn.flyrise.android3.test.widthmeasurewidth.TestActivity;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test extends ListActivity {

    static List<Item> itemList;
    static {
        itemList = new ArrayList<Item>();
//        itemList.add(new Item("width mearsurewidth", TestActivity.class));
//        itemList.add(new Item("List -- �϶�", DragSwitchActivity.class));
//        itemList.add(new Item("List -- ����ˢ��", PullRefresh.class));
//        itemList.add(new Item("View Pager", PagerTitleDemoActivity.class));
//        itemList.add(new Item("View Pager With Tab", PagerTabActivity.class));
//        itemList.add(new Item("View Pager Without Tab", PagerTab2Activity.class));
//        itemList.add(new Item("MyDrawerTest", MyDrawerTest.class));
//        itemList.add(new Item("SimpleExpendlist", SimpleExpendlist.class));
//        itemList.add(new Item("ExpendlistWithSimpleAdapter", ExpendlistWithSimpleAdapter.class));
//        itemList.add(new Item("CustomExpendlist", MyExpendlistActivity.class));
//        itemList.add(new Item("CustomExpendlist2", CustomExpendlistActivity.class));
//        itemList.add(new Item("������", TestPCActivity.class));
//        itemList.add(new Item("����д��", ReaderWriterTestActivity.class));
//        itemList.add(new Item("����д��2", ReaderWriterTest2Activity.class));
//
//        itemList.add(new Item("��Ч����ͼƬ", LoadBitmapActivity.class));
//        itemList.add(new Item("��Ч����ͼƬ2", LoadBitmapAsyncActivity.class));
//        itemList.add(new Item("��Ч����ͼƬ3", LoadBitmapAsyncForListActivity.class));
//
//        itemList.add(new Item("���̻���", DiskCacheActivity.class));
//        itemList.add(new Item("ͼƬ�Ĵ��̻���", ImageDiskCacheActivity.class));
//
//        itemList.add(new Item("��ѡ���Ƶ�TextView", UseActivity.class));
        
        itemList.add(new Item("TestJsonActivity", TestJsonActivity.class)); 
        itemList.add(new Item("TestImageViewActivity", TestImageViewActivity.class)); 
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new SimpleAdapter(this, getData(),
                android.R.layout.simple_list_item_1, new String[] {
                    "title"
                }, new int[] {
                    android.R.id.text1
                }));
        
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;
        
        Display display = getWindowManager().getDefaultDisplay();
        screenW = display.getWidth();
        Log.e("screenW", screenW+"");
        
        WindowManager wManager = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
        display = wManager.getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight(); 
        Log.e("screenW", screenW+"");
    }

    private List<Map<String, String>> getData() {
        List<Map<String, String>> data = new ArrayList<Map<String, String>>();
        for (Item item : itemList) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("title", item.title);
            data.add(map);
        }
        return data;
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Item item = itemList.get(position);
        Intent intent = new Intent(this, item.cls);
        intent.putExtra("SSSS", "SSSS>>>");
        startActivity(intent);
    }
    

}

@SuppressWarnings("unchecked")
class Item {
    String title;

    Class cls;

    public Item(String title, Class cls) {
        this.title = title;
        this.cls = cls;
    }
}
