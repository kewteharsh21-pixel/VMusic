package com.v.music;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import android.content.Context;
import java.util.*;

public class MainActivity extends Activity {
    LinearLayout root, content; TextView nowTitle; TextView toast;
    int purple=Color.rgb(124,60,255), bg=Color.rgb(7,9,16), panel=Color.rgb(17,21,34), muted=Color.rgb(150,156,175);
    @Override public void onCreate(Bundle b){super.onCreate(b); getWindow().setStatusBarColor(bg); build();}
    TextView tv(String s,float size,int color){ TextView t=new TextView(this); t.setText(s); t.setTextSize(size); t.setTextColor(color); t.setGravity(Gravity.CENTER_VERTICAL); t.setPadding(0,0,0,0); return t; }
    GradientDrawable round(int c,float r){GradientDrawable g=new GradientDrawable();g.setColor(c);g.setCornerRadius(r);return g;}
    TextView pill(String s){TextView t=tv(s,14,Color.WHITE);t.setGravity(Gravity.CENTER);t.setPadding(18,10,18,10);t.setBackground(round(panel,60));return t;}
    void build(){
        root=new LinearLayout(this); root.setOrientation(LinearLayout.VERTICAL); root.setBackgroundColor(bg);
        ScrollView scroll=new ScrollView(this); scroll.setFillViewport(true); content=new LinearLayout(this); content.setOrientation(LinearLayout.VERTICAL); content.setPadding(18,14,18,95); scroll.addView(content); root.addView(scroll,new LinearLayout.LayoutParams(-1,0,1));
        showHome();
        LinearLayout player=new LinearLayout(this); player.setGravity(Gravity.CENTER_VERTICAL); player.setPadding(14,8,14,8); player.setBackgroundColor(panel);
        TextView art=tv("♫",22,Color.WHITE); art.setGravity(Gravity.CENTER); art.setBackground(round(Color.rgb(96,45,180),12)); player.addView(art,new LinearLayout.LayoutParams(48,48));
        LinearLayout pi=new LinearLayout(this); pi.setOrientation(LinearLayout.VERTICAL); pi.setPadding(10,0,8,0); nowTitle=tv("Raataan Lambiyan",13,Color.WHITE); pi.addView(nowTitle); pi.addView(tv("V Music • Now playing",11,muted)); player.addView(pi,new LinearLayout.LayoutParams(0,56,1));
        Button play=new Button(this); play.setText("▶"); play.setTextColor(Color.WHITE); play.setBackground(round(purple,80)); play.setOnClickListener(v->showToast("Play / pause")); player.addView(play,new LinearLayout.LayoutParams(48,48));
        root.addView(player,new LinearLayout.LayoutParams(-1,64));
        LinearLayout nav=new LinearLayout(this); nav.setGravity(Gravity.CENTER); nav.setBackgroundColor(Color.rgb(13,16,25)); String[] ns={"⌂\nHome","⌕\nSearch","▤\nLibrary","⇩\nDownloads"}; for(String n:ns){TextView x=tv(n,12,muted);x.setGravity(Gravity.CENTER);nav.addView(x,new LinearLayout.LayoutParams(0,64,1)); final String q=n; x.setOnClickListener(v->{ if(q.contains("Home"))showHome(); else if(q.contains("Search"))showSearch(); else if(q.contains("Downloads"))showDownloads(); else showLibrary();});} root.addView(nav,new LinearLayout.LayoutParams(-1,64)); setContentView(root);
    }
    void clear(){content.removeAllViews();}
    void showHome(){clear(); LinearLayout top=new LinearLayout(this);top.setGravity(Gravity.CENTER_VERTICAL);TextView l=tv("V",40,Color.WHITE);l.setTypeface(null,1);top.addView(l,new LinearLayout.LayoutParams(0,58,1));top.addView(tv("♧   ⚙",22,muted));content.addView(top);
        TextView search=pill("⌕   Search songs, artists, albums..."); content.addView(search,new LinearLayout.LayoutParams(-1,52)); space(14);
        LinearLayout hero=new LinearLayout(this);hero.setOrientation(LinearLayout.VERTICAL);hero.setPadding(20,20,20,15);hero.setBackground(round(Color.rgb(70,32,125),24)); TextView h=tv("Good Evening",25,Color.WHITE);h.setTypeface(null,1);hero.addView(h);hero.addView(tv("Let the music flow.",14,Color.LTGRAY));Button p=new Button(this);p.setText("▶");p.setTextColor(purple);p.setBackground(round(Color.WHITE,60));LinearLayout.LayoutParams bp=new LinearLayout.LayoutParams(58,52);bp.gravity=Gravity.RIGHT;hero.addView(p,bp);p.setOnClickListener(v->showToast("Playing V Music"));content.addView(hero,new LinearLayout.LayoutParams(-1,160));
        section("Made for You",new String[]{"TOP HITS","CHILL VIBES","WORKOUT"}); sectionSongs(); }
    void section(String title,String[] cards){space(22);TextView h=tv(title+"                         See all",18,Color.WHITE);h.setTypeface(null,1);content.addView(h);LinearLayout row=new LinearLayout(this);for(String s:cards){TextView c=tv(s,13,Color.WHITE);c.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL);c.setPadding(8,8,8,12);c.setBackground(round(Color.rgb(70+(int)(Math.random()*80),35,120+(int)(Math.random()*90)),18));row.addView(c,new LinearLayout.LayoutParams(0,125,1));spaceX(row,7);}content.addView(row);}
    void sectionSongs(){space(22);TextView h=tv("Recently Played",18,Color.WHITE);h.setTypeface(null,1);content.addView(h);String[][] ss={{"Raataan Lambiyan","Jubin Nautiyal • Shreya Ghoshal"},{"Sunflower","Pop playlist"},{"Night Changes","One Direction"}};for(String[] s:ss){LinearLayout r=song(s[0],s[1]);content.addView(r);}}
    LinearLayout song(String a,String b){LinearLayout r=new LinearLayout(this);r.setGravity(Gravity.CENTER_VERTICAL);r.setPadding(0,10,0,10);TextView im=tv("♫",20,Color.WHITE);im.setGravity(Gravity.CENTER);im.setBackground(round(Color.rgb(83,43,145),12));r.addView(im,new LinearLayout.LayoutParams(52,52));LinearLayout inf=new LinearLayout(this);inf.setOrientation(LinearLayout.VERTICAL);inf.setPadding(12,0,0,0);inf.addView(tv(a,14,Color.WHITE));inf.addView(tv(b,11,muted));r.addView(inf,new LinearLayout.LayoutParams(0,60,1));TextView more=tv("⋮",24,muted);r.addView(more,new LinearLayout.LayoutParams(35,52));r.setOnClickListener(v->{nowTitle.setText(a);showToast("Playing: "+a);});return r;}
    void showSearch(){clear();content.addView(tv("‹   Search",28,Color.WHITE),new LinearLayout.LayoutParams(-1,58));content.addView(pill("⌕   Search songs, artists, albums..."));space(20);content.addView(tv("Trending Searches",18,Color.WHITE));String[] tags={"Arijit Singh","The Weeknd","Lofi","Bollywood","Punjabi","Taylor Swift"};LinearLayout row=new LinearLayout(this);row.setPadding(0,12,0,0);for(String x:tags){row.addView(pill(x),new LinearLayout.LayoutParams(-2,48));spaceX(row,6);}content.addView(row);content.addView(tv("\nPopular Artists\n\nArijit Singh     The Weeknd     Post Malone",17,Color.WHITE));}
    void showLibrary(){clear();content.addView(tv("Library",28,Color.WHITE));space(20);content.addView(tv("♥  Favorites",18,Color.WHITE));content.addView(tv("\n▤  Playlists\n\n♫  Recently Played",18,Color.WHITE));}
    void showDownloads(){clear();content.addView(tv("Downloads",28,Color.WHITE));space(18);content.addView(tv("Songs                                      Albums",14,purple));space(10);String[][] d={{"Raataan Lambiyan","7.2 MB"},{"Sunflower","6.9 MB"},{"Chaleya","5.8 MB"},{"Zindagi Kuch Toh Bata","6.4 MB"}};for(String[] x:d){LinearLayout r=song(x[0],x[1]+" • Offline");content.addView(r);}}
    void space(int h){Space s=new Space(this);content.addView(s,new LinearLayout.LayoutParams(1,h));} void spaceX(LinearLayout r,int w){Space s=new Space(this);r.addView(s,new LinearLayout.LayoutParams(w,1));}
    void showToast(String s){ if(toast!=null)((ViewGroup)toast.getParent()).removeView(toast);toast=tv(s,13,Color.WHITE);toast.setGravity(Gravity.CENTER);toast.setPadding(20,12,20,12);toast.setBackground(round(Color.rgb(38,42,58),40));addContentView(toast,new ViewGroup.LayoutParams(-2,48));toast.setX(80);toast.setY(getResources().getDisplayMetrics().height-190);toast.postDelayed(()->{if(toast!=null&&toast.getParent()!=null)((ViewGroup)toast.getParent()).removeView(toast);},1400);}
}
