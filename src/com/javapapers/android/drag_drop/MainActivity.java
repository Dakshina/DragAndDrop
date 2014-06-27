package com.javapapers.android.drag_drop;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Rect;
import android.view.*;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.View.DragShadowBuilder;
import android.widget.AbsoluteLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.util.Log;

import com.javapapers.android.drag_drop.R;


public class MainActivity extends Activity implements OnTouchListener,OnDragListener{
	private static final String LOGCAT = null;
	private ImageView image1,image2,image3,image4,image5,image6,image7;
	private AbsoluteLayout mainLayout;
	int point=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mainLayout = (AbsoluteLayout)findViewById(R.id.center);
		image1=(ImageView)findViewById(R.id.imageView1);
		image2=(ImageView)findViewById(R.id.imageView2);
		image3=(ImageView)findViewById(R.id.imageView3);
		image4=(ImageView)findViewById(R.id.imageView4);
		image5=(ImageView)findViewById(R.id.imageView5);
		image6=(ImageView)findViewById(R.id.imageView6);
		image7=(ImageView)findViewById(R.id.imageView7);
		
		image1.setOnTouchListener(this);
		image2.setOnTouchListener(this);
		image3.setOnTouchListener(this);
		image7.setOnTouchListener(this);
		mainLayout.setOnTouchListener(this);
		findViewById(R.id.center).setOnDragListener(this);
		 findViewById(R.id.pink).setOnDragListener(this);
		
	}
	private boolean dragging = false;
	private Rect hitRect = new Rect();

	public boolean onTouch(View v, MotionEvent event) { 
//		    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
//		      DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
//			  view.startDrag(null, shadowBuilder, view, 0);
//			  view.setVisibility(View.INVISIBLE);
//			  return true;
//		    }
//		    else {
//		    	return false;
//		    }
		boolean eventConsumed = true;
		int x = (int)event.getX();
		int y = (int)event.getY();

		int action = event.getAction();
		if (action == MotionEvent.ACTION_DOWN) {
			if (v == image1) {
				point=1;
				System.out.println(" view 0000000000 is clicked");
				image1.setImageResource(R.drawable.hello);
				dragging = true;
				eventConsumed = false;
			}
			if (v == image2){
				point=2;
				System.out.println(" view 1111111111 is clicked");

				image2.setImageResource(R.drawable.photo1);
				dragging = true;
				eventConsumed = false;
			}
			if (v == image3){
				point=3;
				System.out.println(" view 222 is clicked");

				image3.setImageResource(R.drawable.photo2);
				dragging = true;
				eventConsumed = false;
			}
			if (v == image7){
				point=4;
				System.out.println(" view 222 is clicked");

				image7.setImageResource(R.drawable.ic_launcher);
				dragging = true;
				eventConsumed = false;
			}
		} else if (action == MotionEvent.ACTION_UP) {

			if (dragging) {

				image4.getHitRect(hitRect);
				if (hitRect.contains(x, y)) {
					if(point==1){
						image1.setImageResource(R.drawable.hello);
						setSameAbsoluteLocation(image1, image4);
					}
					if(point==2){
						image2.setImageResource(R.drawable.photo1);
						setSameAbsoluteLocation(image2, image4);
					}
					if(point==3){
						image3.setImageResource(R.drawable.photo2);
						setSameAbsoluteLocation(image3, image4);
					}
					if(point==4){
						image3.setImageResource(R.drawable.ic_launcher);
						setSameAbsoluteLocation(image7, image4);
					}
				}
				image5.getHitRect(hitRect);
				if (hitRect.contains(x, y)) {
					if(point==1){
						image1.setImageResource(R.drawable.hello);
						setSameAbsoluteLocation(image1, image5);
					}
					if(point==2){
						image2.setImageResource(R.drawable.photo1);
						setSameAbsoluteLocation(image2, image5);
					}
					if(point==3){
						image3.setImageResource(R.drawable.photo2);
						setSameAbsoluteLocation(image3, image5);
					}
				}
				image6.getHitRect(hitRect);
				if (hitRect.contains(x, y)) {
					if(point==1){
						image1.setImageResource(R.drawable.hello);
						setSameAbsoluteLocation(image1, image6);
					}
					if(point==2){
						image2.setImageResource(R.drawable.photo1);
						setSameAbsoluteLocation(image2, image6);
					}
					if(point==3){
						image3.setImageResource(R.drawable.photo2);
						setSameAbsoluteLocation(image3, image6);
					}
				}


			}
			dragging = false;
			eventConsumed = false;

		} else if (action == MotionEvent.ACTION_MOVE) {
			if (v != image1 && point == 1 ) {
				if (dragging) {
					setAbsoluteLocationCentered(image1, x, y);
				}

			}
			if (v != image2 && point == 2) {
				if (dragging) {
					setAbsoluteLocationCentered(image2, x, y);
				}

			}
			if (v != image3 && point == 3) {
				if (dragging) {
					setAbsoluteLocationCentered(image3, x, y);
				}

			}
			if (v != image7 && point == 4) {
				if (dragging) {
					setAbsoluteLocationCentered(image7, x, y);
				}

			}
		}


		return eventConsumed;
	}  
	
	@SuppressWarnings("deprecation")
	private void setSameAbsoluteLocation(View v1, View v2) {
		AbsoluteLayout.LayoutParams alp2 = (AbsoluteLayout.LayoutParams) v2.getLayoutParams();
		setAbsoluteLocation(v1, alp2.x, alp2.y);
	}


	private void setAbsoluteLocationCentered(View v, int x, int y) {
		setAbsoluteLocation(v, x - v.getWidth() / 2, y - v.getHeight() / 2);
	}


	private void setAbsoluteLocation(View v, int x, int y) {
		AbsoluteLayout.LayoutParams alp = (AbsoluteLayout.LayoutParams) v.getLayoutParams();
		alp.x = x;
		alp.y = y;
		v.setLayoutParams(alp);
	}

    public boolean onDrag(View layoutview, DragEvent dragevent) {
	      int action = dragevent.getAction();
	      switch (action) {
	      case DragEvent.ACTION_DRAG_STARTED:
	          Log.d(LOGCAT, "Drag event started");
	    	break;
	      case DragEvent.ACTION_DRAG_ENTERED:
	    	  Log.d(LOGCAT, "Drag event entered into "+layoutview.toString());
	    	break;
	      case DragEvent.ACTION_DRAG_EXITED:
	    	  Log.d(LOGCAT, "Drag event exited from "+layoutview.toString());
	    	break;
	      case DragEvent.ACTION_DROP:
	    	Log.d(LOGCAT, "Dropped");
	    	View view = (View) dragevent.getLocalState();
	        ViewGroup owner = (ViewGroup) view.getParent();
	        owner.removeView(view);
	        AbsoluteLayout container = (AbsoluteLayout) layoutview;
	        container.addView(view);
	        view.setVisibility(View.VISIBLE);
	        break;
	      case DragEvent.ACTION_DRAG_ENDED:
	    		  Log.d(LOGCAT, "Drag ended");
		      break;
	      default:
	        break;
	      }
	      return true;
    }
}
