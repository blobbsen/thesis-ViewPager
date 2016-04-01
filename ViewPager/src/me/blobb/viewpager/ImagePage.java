package me.blobb.viewpager;

import me.blobb.viewpager.R;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * @author andre.boddenberg@gmx.de
 * 
 * The ImagePage class provides a simple Fragment, which shows a Bitmap.
 * The Bitmap must be passed to the newInstance method.
 * 
 * 
 *@param bitmap The Bitmap Field makes it possible for the newInstance method to save its passed Bitmap,<br>
 *				so the onCreateView can access it.		 	
 */
public class ImagePage extends Fragment {
	private Bitmap bitmap;
	
	
	/**
	 * The Method newInstance works like an Constructor to create a new image View<br>
	 * The passed Bitmap is saved in a class field, so the onCreateView method can access it.
	 * 
	 * @param bmap Bitmap to be displayed in this fragment.
	 * 
	 * @return returns an ImagePage fragment.
	 */
	public ImagePage newInstance(Bitmap bmap) //Bitmap bmap
	{	
		//overwrite local bitmap with passed one
		this.bitmap = bmap;
		
		// creating new ImageView
		//ImagePage f = new ImagePage();
		
		Bundle bdl = new Bundle(1);
	    this.setArguments(bdl);
	    
	    return this;
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) 
	{
		// create View and inflate image_page layout
		View v = inflater.inflate(R.layout.image_page, container, false);
		
		// setting the bitmap to the imageView 
		((ImageView) v.findViewById(R.id.imageView1)).setImageBitmap(this.bitmap);
		
        return v;
	}
	
}
