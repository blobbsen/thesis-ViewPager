package me.blobb.viewpager;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import me.blobb.viewpager.R;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;

/**
 * @author andre.boddenberg@gmx.de
 * 
 * 
 * 
 * @param MyPageAdapter pageAdapter needed for the ViewPager
 */
public class MainActivity extends FragmentActivity {
	private MyPageAdapter pageAdapter;
	private ArrayList<Bitmap> bmaps = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_view);
    
        	List<Fragment> fragments = null;
        	
			try 
        	{									
        		fragments  = getFragments(savedInstanceState);
        	} 	
        	catch (IOException e) 
        	{
        		e.printStackTrace();
        	}
            
        pageAdapter = new MyPageAdapter(getSupportFragmentManager(), fragments);
        ViewPager pager = (ViewPager)findViewById(R.id.viewpager);
        pager.setAdapter(pageAdapter);        
    }
    
    @Override
    public void onSaveInstanceState(Bundle outState) {
       super.onSaveInstanceState(outState);
       
       int i = 0;
       
       for(Bitmap bmap : bmaps)
       {
    	   int bytes = bmap.getByteCount();
    	   ByteBuffer buffer = ByteBuffer.allocate(bytes); 
    	   bmap.copyPixelsToBuffer(buffer); 
    	   
    	   byte[] temp = buffer.array();

    	   outState.putByteArray(String.valueOf(i), temp);
    	   
    	   i++;
       }

    }
    
    @Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
    
    private List<Fragment> getFragments(Bundle inState) throws IOException
    {	
    	ArrayList<Bitmap> bmaps = new ArrayList<Bitmap>();
    	
//    	if(inState == null)
//    	{
    		bmaps.add(loadImage("HubbleGalaxyXXL.jpg"));
//    		bmaps.add(loadImage("index.jpg"));
//    		bmaps.add(loadImage("index2.jpg"));
//    		bmaps.add(loadImage("bla.jpg"));
    	
//    	}else{
//    		
//    		ArrayList<byte[]> alByte = new ArrayList<byte[]>();
//    		
//    		for(int i=0; i<4; i++)
//    		{
//    			alByte.add(inState.getByteArray(String.valueOf(i)));
//    			
//    			Bitmap bmp = BitmapFactory.decodeByteArray(alByte.get(i),0,alByte.get(i).length);
//    			bmaps.add(bmp);
//    		}
//    	}
    	
    	this.bmaps = bmaps;
    
    	List<Fragment> fList = new ArrayList<Fragment>();	
    	
//    	ImagePage test = new ImagePage(); //.newInstance(loadImage("HubbleGalaxyXXL.jpg"));
//    	test.newInstance(this.bmaps.get(0));
//    	
//    	ImagePage test2 = new ImagePage(); //.newInstance(loadImage("HubbleGalaxyXXL.jpg"));
//    	test2.newInstance(this.bmaps.get(1));
//    	
//    	ImagePage test3 = new ImagePage(); //.newInstance(loadImage("HubbleGalaxyXXL.jpg"));
//    	test3.newInstance(this.bmaps.get(2));
//    	
//    	ImagePage test4 = new ImagePage(); //.newInstance(loadImage("HubbleGalaxyXXL.jpg"));
//    	test4.newInstance(this.bmaps.get(3));
//    	
    	//_________
    	
    	ImagePage test = new ImagePage(); //.newInstance(loadImage("HubbleGalaxyXXL.jpg"));
    	test.newInstance(bmaps.get(0));
    	
//    	ImagePage test2 = new ImagePage(); //.newInstance(loadImage("HubbleGalaxyXXL.jpg"));
//    	test2.newInstance(bmaps.get(1));
//    	
//    	ImagePage test3 = new ImagePage(); //.newInstance(loadImage("HubbleGalaxyXXL.jpg"));
//    	test3.newInstance(bmaps.get(2));
//    	
//    	ImagePage test4 = new ImagePage(); //.newInstance(loadImage("HubbleGalaxyXXL.jpg"));
//    	test4.newInstance(bmaps.get(3));
    	
    	
    	fList.add(test);
    	fList.add(LogInPage.newInstance());
//    	fList.add(test2);
//    	fList.add(test3);
//    	fList.add(test4);

    	return fList;
    }

    /**
  	 *
     */
    private class MyPageAdapter extends FragmentPagerAdapter {
    	private List<Fragment> fragments;

        public MyPageAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }
        @Override
        public Fragment getItem(int position) {
            return this.fragments.get(position);
        }
     
        @Override
        public int getCount() {
            return this.fragments.size();
        }
    }
    
    /**
     * 
     * Method to parse an Image from the assets folder via assetManager. It returns a Bitmap object.
     * 
     * @param file file to be parsed. give path if file is in subfolder of assets.
     * @return bitmap parsed image from assetfolder.
     * @throws IOException 
     */
    private Bitmap loadImage(String file) throws IOException
    {
    	AssetManager assetManager = getAssets();
        InputStream istr = null;
    
        istr = assetManager.open(file);
     
        Bitmap bitmap = BitmapFactory.decodeStream(istr);

        bitmap = scaleImage(bitmap);
    
        return bitmap;
    }

	private Bitmap scaleImage(Bitmap bitmap) {
		
        int h = bitmap.getHeight();
        int w = bitmap.getWidth();
        
		if(h > 4096 || w > 4098)
	    {							// so dass hier noch und dann ist gut!
	        	/*
	        	if(h == w)
	        	{
	        		bitmap = Bitmap.createScaledBitmap(bitmap, 2048, 2048, true);
	        	}else
	        	{
	        		if(h > w){
	        			
	        			bitmap = Bitmap.createScaledBitmap(bitmap, 4096, 4096, true);
	        			
	        		}else
	        		{
	        			bitmap = Bitmap.createScaledBitmap(bitmap, 4096, 4096, true);
	        		}
	        	}	*/
	        	
				bitmap = Bitmap.createScaledBitmap(bitmap, 2048, 2048, true);
	        	
	    }
		
	 return bitmap;
	
	}
}
   

