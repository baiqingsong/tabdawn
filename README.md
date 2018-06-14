# nav和下方tab的实现

* [导包](#导包)
* [自定义viewpage](#自定义viewpage)
* [nav](#nav)
    * [xml文件](#xml文件)
    * [adapter文件](#adapter文件)
    * [activity中](#activity中)
* [bottomtab](#bottomtab)
    * [style文件](#style文件)
    * [xml文件](#xml文件)
    * [adapter文件](#adapter文件)
    * [activity中](#activity中)


## 导包
```
compile 'com.android.support:design:26.1.0'
```
采用tabLayout和viewPager来结合

## 自定义viewpage
由于要设置是否可以滑动切换功能，采用重定义viewPager来实现
```
public class ViewPagerSlide extends ViewPager {
    private boolean isSlide = false;//是否可以进行滑动

    public void setSlide(boolean slide) {
        isSlide = slide;
    }

    public ViewPagerSlide(Context context) {
        super(context);
    }
    public ViewPagerSlide(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return isSlide && super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return isSlide && super.onTouchEvent(ev);

    }
}
```

## nav

### xml文件
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent" android:layout_height="match_parent"
    android:orientation="vertical">
    <android.support.design.widget.TabLayout
        android:id="@+id/tabLayout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="#32CD32"
        app:tabIndicatorColor="#f00"
        app:tabSelectedTextColor="#444"
        app:tabMode="fixed"
        app:tabTextColor="#fff" />


    <com.yipai.tabdawn.ViewPagerSlide
        android:id="@+id/viewPager"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>

</LinearLayout>
```

### adapter文件
```
public class TopTabAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private String[] titles;
    public TopTabAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }
    public TopTabAdapter(FragmentManager fm, List<Fragment> fragments, String[] titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position < fragments.size()) {
            fragment = fragments.get(position);
        } else {
            fragment = fragments.get(0);
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (titles != null && titles.length > 0)
            return titles[position];
        return null;
    }
}
```

### activity中
```
public class TopTabActivity extends AppCompatActivity{
    private TabLayout tabLayout;
    private ViewPagerSlide viewPager;
    private TopTabAdapter mAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_tab);
        initView();
        addListener();
    }
    private void initView(){
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
    }
    private void addListener(){
        viewPager.setSlide(true);
        List<Fragment> fragments = new ArrayList<>();
        Fragment fragment1 = new TabFragment1();
        Fragment fragment2 = new TabFragment2();
        Fragment fragment3 = new TabFragment3();
        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);
        mAdapter = new TopTabAdapter(getSupportFragmentManager(), fragments, new String[]{"tab1", "tab2", "tab3"});
        viewPager.setAdapter(mAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
```

## bottomtab

### style文件
```
    <style name="BottomTab" parent="Widget.Design.TabLayout">
        <item name="tabIndicatorColor">?attr/colorAccent</item>
        <item name="tabIndicatorHeight">0dp</item>
        <item name="tabPaddingStart">12dp</item>
        <item name="tabTextColor">#aaa</item>
        <item name="tabPaddingEnd">12dp</item>
        <item name="tabSelectedTextColor">#f00</item>
    </style>
```

### xml文件
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent" android:layout_height="match_parent"
    android:orientation="vertical">
    <com.yipai.tabdawn.ViewPagerSlide
        android:id="@+id/viewPager"
        android:layout_width="fill_parent"
        android:layout_height="0dp"
        android:layout_weight="1" />

    <android.support.design.widget.TabLayout
        android:id="@+id/tabLayout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        style="@style/BottomTab"/>
</LinearLayout>
```

### adapter文件
```
public class BottomTabAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private String[] titles;
    public BottomTabAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }
    public BottomTabAdapter(FragmentManager fm, List<Fragment> fragments, String[] titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position < fragments.size()) {
            fragment = fragments.get(position);
        } else {
            fragment = fragments.get(0);
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (titles != null && titles.length > 0)
            return titles[position];
        return null;
    }
}
```

### activity中
```
public class BottomTabActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPagerSlide viewPager;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_tab);
        initView();
        addListener();
    }
    private void initView(){
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
    }
    private void addListener(){
        viewPager.setSlide(false);
        fragments = new ArrayList<>();
        fragments.add(new TabFragment1());
        fragments.add(new TabFragment2());
        fragments.add(new TabFragment3());
        BottomTabAdapter adapter = new BottomTabAdapter(getSupportFragmentManager(), fragments, new String[]{"首页", "礼物卡片", "我的"});
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        for(int i = 0; i < tabLayout.getTabCount(); i ++){
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            Drawable drawable = null;
            switch (i){
                case 0:
                    drawable = getResources().getDrawable(R.drawable.bg_home);
                    break;
                case 1:
                    drawable = getResources().getDrawable(R.drawable.bg_gift_card);
                    break;
                case 2:
                    drawable = getResources().getDrawable(R.drawable.bg_mine);
                    break;
            }
            tab.setIcon(drawable);
        }
    }
}
```
