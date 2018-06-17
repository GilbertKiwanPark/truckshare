/*
 * Copyright 2016 NAVER Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.gilbertpark.fourwheels;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nhn.android.maps.NMapActivity;
import com.nhn.android.maps.NMapCompassManager;
import com.nhn.android.maps.NMapController;
import com.nhn.android.maps.NMapLocationManager;
import com.nhn.android.maps.NMapOverlay;
import com.nhn.android.maps.NMapOverlayItem;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.nmapmodel.NMapError;
import com.nhn.android.maps.nmapmodel.NMapPlacemark;
import com.nhn.android.maps.overlay.NMapPOIdata;
import com.nhn.android.maps.overlay.NMapPOIitem;
import com.nhn.android.mapviewer.overlay.NMapCalloutCustomOverlay;
import com.nhn.android.mapviewer.overlay.NMapCalloutOverlay;
import com.nhn.android.mapviewer.overlay.NMapMyLocationOverlay;
import com.nhn.android.mapviewer.overlay.NMapOverlayManager;
import com.nhn.android.mapviewer.overlay.NMapPOIdataOverlay;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

import Data.PcRoomData2;
import NaverMapApi.NMapCalloutCustomOverlayView;
import NaverMapApi.NMapViewerResourceProvider;
import Utils.BackPressExitHandler;
import Utils.DeveloperKeyUtil;
import Utils.LocationNameMatcher;
import Utils.SharedPreferenceManager;
import butterknife.ButterKnife;

/**
 * Sample class for map viewer library.
 *
 * @author kyjkim
 */
public class MapViewer extends NMapActivity {
    Context thisActivity = this;
    public static Activity nMapActivity;
    private BackPressExitHandler backPressExitHandler;

    DatabaseReference mDatabase;
    HashMap<String, PcRoomData2> pcroomMap = new HashMap<>();

    private static final String LOG_TAG = "MapViewer";
    private static final boolean DEBUG = false;
    public NGeoPoint myPoint = new NGeoPoint();
    public boolean first = true;
    SharedPreferenceManager pref;
    String siName = "";
    String doName = "";

    // set your Client ID which is registered for MapViewer library.
    private static final String CLIENT_ID = new DeveloperKeyUtil().NaverMapClientKey;

    private MapContainerView mMapContainerView;

    private NMapView mMapView;
    private NMapController mMapController;

    private static final int NMAP_ZOOMLEVEL_DEFAULT = 11;
    private static final int NMAP_VIEW_MODE_DEFAULT = NMapView.VIEW_MODE_VECTOR;
    private static final boolean NMAP_TRAFFIC_MODE_DEFAULT = false;
    private static final boolean NMAP_BICYCLE_MODE_DEFAULT = false;

    private static final String KEY_ZOOM_LEVEL = "MapViewer.zoomLevel";
    private static final String KEY_CENTER_LONGITUDE = "MapViewer.centerLongitudeE6";
    private static final String KEY_CENTER_LATITUDE = "MapViewer.centerLatitudeE6";
    private static final String KEY_VIEW_MODE = "MapViewer.viewMode";
    private static final String KEY_TRAFFIC_MODE = "MapViewer.trafficMode";
    private static final String KEY_BICYCLE_MODE = "MapViewer.bicycleMode";

    private SharedPreferences mPreferences;
    private LocationNameMatcher locationNameMatcher;

    private NMapOverlayManager mOverlayManager;

    private NMapMyLocationOverlay mMyLocationOverlay;
    private NMapLocationManager mMapLocationManager;
    private NMapCompassManager mMapCompassManager;

    private NMapViewerResourceProvider mMapViewerResourceProvider;

    private NMapPOIdataOverlay mFloatingPOIdataOverlay;
    private NMapPOIitem mFloatingPOIitem;

    private ImageView ivFilter;

    private static boolean USE_XML_LAYOUT = true;

    /**
     * Called when the activity is first created.
     */
    boolean showDetail = false;

    int filterPartyCount = 0;

    public LinearLayout root;
//    public ImageView img;
//    public TextView name;
//    public TextView tvDistance;
//    public TextView reservation;
//    public TextView price;
//    public TextView cpu;
//    public TextView gpu;

    //TextView getLocation;
    Button btnMyLocation;
    public viewPointAsyncTask myTask;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_viewer);
        ButterKnife.bind(this);
        pref = new SharedPreferenceManager(this);
        locationNameMatcher = new LocationNameMatcher();


        root = findViewById(R.id.pcroom_panel_at_mv_ll_root);
//        img = findViewById(R.id.pcroom_panel_at_mv_iv_img);
//        name = findViewById(R.id.pcroom_panel_at_mv_tv_name);
//        tvDistance = findViewById(R.id.pcroom_panel_at_mv_tv_viewmap);
//        reservation = findViewById(R.id.pcroom_panel_at_mv_tv_reservation_available);
//        price = findViewById(R.id.pcroom_panel_at_mv_tv_price);
//        cpu = findViewById(R.id.pcroom_panel_at_mv_tv_cpu);
//        gpu = findViewById(R.id.pcroom_panel_at_mv_tv_gpu);
        //getLocation = findViewById(R.id.map_tv_getlocation);
        ivFilter = findViewById(R.id.mapview_iv_filter);
        ivFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapViewer.this, FilterActivity.class);
                startActivity(intent);
            }
        });
        btnMyLocation = findViewById(R.id.map_mylocation);
        btnMyLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                first = true;
                myTask = new viewPointAsyncTask();
                myTask.execute();
            }
        });

        mDatabase = FirebaseDatabase.getInstance().getReference();
        //getLocation.setText(pref.getValue(SharedPreferenceManager.MY_DONG, "찾는중"));
        siName = pref.getValue(SharedPreferenceManager.MY_GU, "");
        doName = pref.getValue(SharedPreferenceManager.MY_DO, "");

        // if (USE_XML_LAYOUT) {
        mMapView = (NMapView) findViewById(R.id.mapView);

        float distance = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 200,
                getResources().getDisplayMetrics()
        );
        root.animate().translationY(distance).setDuration(10).start();

        nMapActivity = MapViewer.this;
        // set a registered Client Id for Open MapViewer Library
        mMapView.setClientId(CLIENT_ID);

        // initialize map view
        mMapView.setClickable(true);
        mMapView.setEnabled(true);
        mMapView.setFocusable(true);
        mMapView.setFocusableInTouchMode(true);
        mMapView.requestFocus();

        // register listener for map state changes
        mMapView.setOnMapStateChangeListener(onMapViewStateChangeListener);

        // use map controller to zoom in/out, pan and set map center, zoom level etc.
        mMapController = mMapView.getMapController();
        //mMapController.setZoomEnabled(false);
        // use built in zoom controls

        // create resource provider
        mMapViewerResourceProvider = new NMapViewerResourceProvider(this);

        // set data provider listener
        super.setMapDataProviderListener(onDataProviderListener);

        // create overlay manager
        mOverlayManager = new NMapOverlayManager(this, mMapView, mMapViewerResourceProvider);
        // register callout overlay listener to customize it.
        mOverlayManager.setOnCalloutOverlayListener(onCalloutOverlayListener);
        // register callout overlay view listener to customize it.
        mOverlayManager.setOnCalloutOverlayViewListener(onCalloutOverlayViewListener);

        // location manager
        mMapLocationManager = new NMapLocationManager(this);
        mMapLocationManager.setOnLocationChangeListener(onMyLocationChangeListener);


        // compass manager
        mMapCompassManager = new NMapCompassManager(this);

        // create my location overlay
        mMyLocationOverlay = mOverlayManager.createMyLocationOverlay(mMapLocationManager, mMapCompassManager);
        setPcroomData(doName, siName);

        startMyLocation();
        myTask = new viewPointAsyncTask();
        myTask.execute();

        backPressExitHandler = new BackPressExitHandler(this);
    }

    private void setPcroomData(String myDoName, String mySiName) {
        try {
            myDoName = locationNameMatcher.map.get(myDoName);
            mySiName = locationNameMatcher.map.get(mySiName);

            mDatabase.child("pcroom2").child(myDoName).child(mySiName).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        PcRoomData2 temp = ds.getValue(PcRoomData2.class);

                        if (myPoint.getLatitude() == 0.0f && myPoint.getLongitude() == 0.0f) {
                            myPoint.set(Double.parseDouble(pref.getValue(pref.MY_LOCATION_LONGITUDE, "0")), Double.parseDouble(pref.getValue(pref.MY_LOCATION_LATITUDE, "0")));
                        }
                        NGeoPoint thisPoint = new NGeoPoint(Double.parseDouble(temp.getLongitude()), Double.parseDouble(temp.getLatitude()));
                        double distance = (Math.round(((myPoint.getDistance(myPoint, thisPoint)) + 90) / 100));
                        temp.setDistance(distance / 10);
                        temp.setKey(ds.getKey());

                        pcroomMap.put(ds.getKey(), temp);
                    }
                    DrawPOIdataOverlay();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        } catch (Exception e) {

        }
    }

    @Override
    protected void onStart() {
        pref.put(SharedPreferenceManager.MY_MAPCOUNT, "0");
        pref.put(SharedPreferenceManager.MY_MAP_INTERVAL, "10000");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.e("onResume", "onr");
        super.onResume();
    }

    @Override
    protected void onStop() {
        Log.e("onStop", "onStop");
        pref.put(SharedPreferenceManager.MY_MAP_INTERVAL, "1000000");
        //stopMyLocation();
        super.onStop();
    }

    @Override
    protected void onPause() {
        Log.e("onPause", "onPause");
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        // save map view state such as map center position and zoom level.
        Log.e("onDestroy", "onDestroy");
        pref.put(SharedPreferenceManager.MY_MAPCOUNT, "10000");
        stopMyLocation();
        saveInstanceState();
        super.onDestroy();
    }

    /* Test Functions */

    @Override
    public void onBackPressed() {
        backPressExitHandler.onBackPressed();
    }

    private void startMyLocation() {

        if (mMyLocationOverlay != null) {
            if (!mOverlayManager.hasOverlay(mMyLocationOverlay)) {
                mOverlayManager.addOverlay(mMyLocationOverlay);
            }

            if (mMapLocationManager.isMyLocationEnabled()) {
                mMyLocationOverlay.setCompassHeadingVisible(true);
                mMapCompassManager.enableCompass();
                mMapView.setAutoRotateEnabled(true, false);
                mMapContainerView.requestLayout();
                mMapView.postInvalidate();
            } else {
                boolean isMyLocationEnabled = mMapLocationManager.enableMyLocation(true);
                if (!isMyLocationEnabled) {
                    //Toast.makeText(MapViewer.this, "Please enable a My Location source in system settings",Toast.LENGTH_LONG).show();

                    Intent goToSettings = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(goToSettings);

                    return;
                }
            }
        }
    }

    private void stopMyLocation() {
        if (mMyLocationOverlay != null) {
            mMapLocationManager.disableMyLocation();

            if (mMapView.isAutoRotateEnabled()) {
                mMyLocationOverlay.setCompassHeadingVisible(false);
                mMapCompassManager.disableCompass();
                mMapView.setAutoRotateEnabled(false, false);
                mMapContainerView.requestLayout();
            }
        }
    }

    private void DrawPOIdataOverlay() {
        mOverlayManager.clearOverlays();

        NMapPOIdata poiData = new NMapPOIdata(2, mMapViewerResourceProvider);
        poiData.beginPOIdata(2);

        for (HashMap.Entry<String, PcRoomData2> entry : pcroomMap.entrySet()) {
            PcRoomData2 data = entry.getValue();
            int markerId;
            if (Integer.parseInt(data.getReservation_count()) >= filterPartyCount) {
                markerId = 6000; // enabled
            } else {
                markerId = 6001; // disabled
            }
            poiData.addPOIitem(Double.parseDouble(data.getLongitude()), Double.parseDouble(data.getLatitude()), "", markerId, data.getKey());
        }

        poiData.endPOIdata();

        // create POI data overlay
        NMapPOIdataOverlay poiDataOverlay = mOverlayManager.createPOIdataOverlay(poiData, null);

        // set event listener to the overlay
        poiDataOverlay.setOnStateChangeListener(onPOIdataStateChangeListener);

        // select an item
        //poiDataOverlay.selectPOIitem(0, true);

        // show all POI data
        //poiDataOverlay.showAllPOIdata(0);
    }

    /* NMapDataProvider Listener */
    private final OnDataProviderListener onDataProviderListener = new OnDataProviderListener() {

        @Override
        public void onReverseGeocoderResponse(NMapPlacemark placeMark, NMapError errInfo) {

            if (showDetail) {
                float distance = TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 200,
                        getResources().getDisplayMetrics()
                );
                root.animate().translationY(distance).setDuration(700).start();
                showDetail = !showDetail;
            }

            if (DEBUG) {
                Log.e(LOG_TAG, "onReverseGeocoderResponse: placeMark="
                        + ((placeMark != null) ? placeMark.toString() : null));
            }

            if (errInfo != null) {
                Log.e(LOG_TAG, "Failed to findPlacemarkAtLocation: error=" + errInfo.toString());

                //Toast.makeText(MapViewer.this, errInfo.toString(), Toast.LENGTH_LONG).show();
                return;
            }

            if (mFloatingPOIitem != null && mFloatingPOIdataOverlay != null) {
                mFloatingPOIdataOverlay.deselectFocusedPOIitem();

                if (placeMark != null) {
                    mFloatingPOIitem.setTitle(placeMark.toString());
                }
                mFloatingPOIdataOverlay.selectPOIitemBy(mFloatingPOIitem.getId(), false);
            }

            Log.e("onDataProviderListener", placeMark.dongName + "|" + placeMark.toString());

            if (placeMark.dongName.equals(null) || placeMark.dongName.equals("")) {

            } else {
                pref.put(SharedPreferenceManager.MY_DO, placeMark.doName);
                pref.put(SharedPreferenceManager.MY_GU, placeMark.siName);
                pref.put(SharedPreferenceManager.MY_DONG, placeMark.dongName);
                setPcroomData(placeMark.doName, placeMark.siName);
            }
            //getLocation.setText(pref.getValue(SharedPreferenceManager.MY_DONG, "찾는중"));
        }
    };

    /* MyLocation Listener */
    private final NMapLocationManager.OnLocationChangeListener onMyLocationChangeListener = new NMapLocationManager.OnLocationChangeListener() {

        @Override
        public boolean onLocationChanged(NMapLocationManager locationManager, NGeoPoint myLocation) {

            if (mMapController != null) {
                // mMapController.animateTo(myLocation);
                myPoint = myLocation;
            }

            return true;
        }

        @Override
        public void onLocationUpdateTimeout(NMapLocationManager locationManager) {

            // stop location updating
            //       Runnable runnable = new Runnable() {
            //          public void run() {
            //             stopMyLocation();
            //          }
            //       };
            //       runnable.run();

            //Toast.makeText(MapViewer.this, "Your current location is temporarily unavailable.", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onLocationUnavailableArea(NMapLocationManager locationManager, NGeoPoint myLocation) {

            Log.e("OLUA", "Your current location is unavailable area.");
            //Toast.makeText(MapViewer.this, "Your current location is unavailable area.", Toast.LENGTH_LONG).show();

            stopMyLocation();
        }

    };

    /* MapView State Change Listener*/
    private final NMapView.OnMapStateChangeListener onMapViewStateChangeListener = new NMapView.OnMapStateChangeListener() {

        @Override
        public void onMapInitHandler(NMapView mapView, NMapError errorInfo) {

            if (errorInfo == null) { // success
                // restore map view state such as map center position and zoom level.
                restoreInstanceState();

            } else { // fail
                Log.e(LOG_TAG, "onFailedToInitializeWithError: " + errorInfo.toString());

                //Toast.makeText(MapViewer.this, errorInfo.toString(), Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onAnimationStateChange(NMapView mapView, int animType, int animState) {
            if (DEBUG) {
                Log.e(LOG_TAG, "onAnimationStateChange: animType=" + animType + ", animState=" + animState);
            }
        }

        @Override
        public void onMapCenterChange(NMapView mapView, NGeoPoint center) {
            if (DEBUG) {
                Log.e(LOG_TAG, "onMapCenterChange: center=" + center.toString());
            }
            Log.e("onMapCenterChange", center.getLongitude() + ", " + center.getLatitude());
            findPlacemarkAtLocation(center.getLongitude(), center.getLatitude());
        }

        @Override
        public void onZoomLevelChange(NMapView mapView, int level) {
            if (DEBUG) {
                Log.e(LOG_TAG, "onZoomLevelChange: level=" + level);
            }
        }

        @Override
        public void onMapCenterChangeFine(NMapView mapView) {
        }
    };

    private final NMapView.OnMapViewTouchEventListener onMapViewTouchEventListener = new NMapView.OnMapViewTouchEventListener() {

        @Override
        public void onLongPress(NMapView mapView, MotionEvent ev) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onLongPressCanceled(NMapView mapView) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onSingleTapUp(NMapView mapView, MotionEvent ev) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onTouchDown(NMapView mapView, MotionEvent ev) {

        }

        @Override
        public void onScroll(NMapView mapView, MotionEvent e1, MotionEvent e2) {
        }

        @Override
        public void onTouchUp(NMapView mapView, MotionEvent ev) {
            // TODO Auto-generated method stub

        }

    };

    private final NMapView.OnMapViewDelegate onMapViewTouchDelegate = new NMapView.OnMapViewDelegate() {

        @Override
        public boolean isLocationTracking() {
            if (mMapLocationManager != null) {
                if (mMapLocationManager.isMyLocationEnabled()) {
                    return mMapLocationManager.isMyLocationFixed();
                }
            }
            return false;
        }

    };

    /* POI data State Change Listener*/
    private final NMapPOIdataOverlay.OnStateChangeListener onPOIdataStateChangeListener = new NMapPOIdataOverlay.OnStateChangeListener() {

        @Override
        public void onCalloutClick(NMapPOIdataOverlay poiDataOverlay, NMapPOIitem item) {
            if (DEBUG) {
                Log.e(LOG_TAG, "onCalloutClick: title=" + item.getTitle());
            }
            // [[TEMP]] handle a click event of the callout
            Intent intent = new Intent(MapViewer.this, HistoryActivity.class);
            intent.putExtra("pcRoomKey", String.valueOf(item.getTag()));
            startActivity(intent);
        }

        @Override
        public void onFocusChanged(NMapPOIdataOverlay poiDataOverlay, NMapPOIitem item) {

            if (item != null) {
                Log.e(LOG_TAG, "onFocusChanged: " + item.toString());
                for (HashMap.Entry<String, PcRoomData2> entry : pcroomMap.entrySet()) {
                    PcRoomData2 data = entry.getValue();
                    //}
                    //for (PcRoomData2 data : pcroomMap) {
                    if (data.getKey().equals(item.getTag())) {
                        setOverlayView(data);
                        root.setVisibility(View.VISIBLE);
                        btnMyLocation.setVisibility(View.GONE);
                        if (!showDetail) {
                            float distance = TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 0,
                                    getResources().getDisplayMetrics()
                            );
                            root.animate().translationY(distance).setDuration(700).start();
                            showDetail = !showDetail;
                        }
                        break;
                    }
                }
            } else {
                if (showDetail) {
                    float distance = TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 200,
                            getResources().getDisplayMetrics()
                    );
                    root.animate().translationY(distance).setDuration(700).start();
                    btnMyLocation.setVisibility(View.VISIBLE);
                    showDetail = !showDetail;
                }

                Log.e(LOG_TAG, "onFocusChanged: ");
            }
        }
    };

    private void setOverlayView(final PcRoomData2 data) {

//        Picasso.get().load(data.getPhoto_list().get(0)).into(img);
//        price.setText("시간당 " + data.getMembership_pay() + "원(회원가)");
//        cpu.setText("CPU " + data.getCpu());
//        gpu.setText("GPU " + data.getGpu());

        //NGeoPoint thisPoint = new NGeoPoint(Double.parseDouble(data.getLongitude()), Double.parseDouble(data.getLatitude()));
        //double distance = (Math.round(((myPoint.getDistance(myPoint, thisPoint)) + 90) / 100));
        //tvDistance.setText(data.getDistance() + "km");

//        try {
//            float averageRb = 0.0f;
//            for (HashMap.Entry<String, ReviewData> entry : data.getReview_map().entrySet()) {
//                ReviewData reveiwData = entry.getValue();
//                averageRb += reveiwData.getStars();
//            }
//            averageRb /= data.getReview_map().size();
//            rb.setRating(averageRb);
//            reviewCount.setText("(후기 " + data.getReview_map().size() + ")");
//        } catch (Exception e) {
//            rb.setRating(0.0f);
//            reviewCount.setText("(후기 없음)");
//        }
        //name.setText(data.getName());
        //reservation.setText("(" + data.getReservation_count() + "인 가능)");
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapViewer.this, ReservationActivity.class);
                startActivity(intent);
            }
        });
    }

    private final NMapPOIdataOverlay.OnFloatingItemChangeListener onPOIdataFloatingItemChangeListener = new NMapPOIdataOverlay.OnFloatingItemChangeListener() {

        @Override
        public void onPointChanged(NMapPOIdataOverlay poiDataOverlay, NMapPOIitem item) {
            NGeoPoint point = item.getPoint();

            if (DEBUG) {
                Log.e(LOG_TAG, "onPointChanged: point=" + point.toString());
            }

            findPlacemarkAtLocation(point.longitude, point.latitude);
            item.setTitle(null);
        }
    };

    private final NMapOverlayManager.OnCalloutOverlayListener onCalloutOverlayListener = new NMapOverlayManager.OnCalloutOverlayListener() {

        @Override
        public NMapCalloutOverlay onCreateCalloutOverlay(NMapOverlay itemOverlay, NMapOverlayItem overlayItem,
                                                         Rect itemBounds) {

            // handle overlapped items
            if (itemOverlay instanceof NMapPOIdataOverlay) {
                NMapPOIdataOverlay poiDataOverlay = (NMapPOIdataOverlay) itemOverlay;

                // check if it is selected by touch event
                if (!poiDataOverlay.isFocusedBySelectItem()) {
                    int countOfOverlappedItems = 1;

                    NMapPOIdata poiData = poiDataOverlay.getPOIdata();
                    for (int i = 0; i < poiData.count(); i++) {
                        NMapPOIitem poiItem = poiData.getPOIitem(i);

                        // skip selected item
                        if (poiItem == overlayItem) {
                            continue;
                        }

                        // check if overlapped or not
                        if (Rect.intersects(poiItem.getBoundsInScreen(), overlayItem.getBoundsInScreen())) {
                            countOfOverlappedItems++;
                        }
                    }

                    if (countOfOverlappedItems > 1) {
                      //  String text = countOfOverlappedItems + " overlapped items for " + overlayItem.getTitle();
                        //Toast.makeText(MapViewer.this, text, Toast.LENGTH_LONG).show();
                        return null;
                    }
                }
            }

            // use custom old callout overlay
//            if (overlayItem instanceof NMapPOIitem) {
//                NMapPOIitem poiItem = (NMapPOIitem) overlayItem;
//
//                if (poiItem.showRightButton()) {
//                    return new NMapCalloutCustomOldOverlay(itemOverlay, overlayItem, itemBounds,
//                            mMapViewerResourceProvider);
//                }
//            }

            // use custom callout overlay
            return new NMapCalloutCustomOverlay(itemOverlay, overlayItem, itemBounds, mMapViewerResourceProvider);

            // set basic callout overlay
            //return new NMapCalloutBasicOverlay(itemOverlay, overlayItem, itemBounds);
        }

    };

    private final NMapOverlayManager.OnCalloutOverlayViewListener onCalloutOverlayViewListener = new NMapOverlayManager.OnCalloutOverlayViewListener() {

        @Override
        public View onCreateCalloutOverlayView(NMapOverlay itemOverlay, NMapOverlayItem overlayItem, Rect itemBounds) {

            if (overlayItem != null) {
                // [TEST] 말풍선 오버레이를 뷰로 설정함
                String title = overlayItem.getTitle();
                if (title != null && title.length() > 5) {
                    return new NMapCalloutCustomOverlayView(MapViewer.this, itemOverlay, overlayItem, itemBounds);
                }
            }

            // null을 반환하면 말풍선 오버레이를 표시하지 않음
            return null;
        }

    };

    /* Local Functions */
    private static boolean mIsMapEnlared = false;

    private void restoreInstanceState() {
        mPreferences = getPreferences(MODE_PRIVATE);

        int level = mPreferences.getInt(KEY_ZOOM_LEVEL, NMAP_ZOOMLEVEL_DEFAULT);
        int viewMode = mPreferences.getInt(KEY_VIEW_MODE, NMAP_VIEW_MODE_DEFAULT);
        boolean trafficMode = mPreferences.getBoolean(KEY_TRAFFIC_MODE, NMAP_TRAFFIC_MODE_DEFAULT);
        boolean bicycleMode = mPreferences.getBoolean(KEY_BICYCLE_MODE, NMAP_BICYCLE_MODE_DEFAULT);

        mMapController.setMapViewMode(viewMode);
        mMapController.setMapViewTrafficMode(trafficMode);
        mMapController.setMapViewBicycleMode(bicycleMode);

        if (mIsMapEnlared) {
            mMapView.setScalingFactor(2.0F);
        } else {
            mMapView.setScalingFactor(1.0F);
        }
    }

    private void saveInstanceState() {
        if (mPreferences == null) {
            return;
        }

        NGeoPoint center = mMapController.getMapCenter();
        int level = mMapController.getZoomLevel();
        int viewMode = mMapController.getMapViewMode();
        boolean trafficMode = mMapController.getMapViewTrafficMode();
        boolean bicycleMode = mMapController.getMapViewBicycleMode();

        SharedPreferences.Editor edit = mPreferences.edit();

        edit.putInt(KEY_CENTER_LONGITUDE, center.getLongitudeE6());
        edit.putInt(KEY_CENTER_LATITUDE, center.getLatitudeE6());
        edit.putInt(KEY_ZOOM_LEVEL, level);
        edit.putInt(KEY_VIEW_MODE, viewMode);
        edit.putBoolean(KEY_TRAFFIC_MODE, trafficMode);
        edit.putBoolean(KEY_BICYCLE_MODE, bicycleMode);

        edit.commit();

    }

    /**
     * Container view class to rotate map view.
     */
    public class MapContainerView extends ViewGroup {

        public MapContainerView(Context context) {
            super(context);
        }

        @Override
        protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
            final int width = getWidth();
            final int height = getHeight();
            final int count = getChildCount();
            for (int i = 0; i < count; i++) {
                final View view = getChildAt(i);
                final int childWidth = view.getMeasuredWidth();
                final int childHeight = view.getMeasuredHeight();
                final int childLeft = (width - childWidth) / 2;
                final int childTop = (height - childHeight) / 2;
                view.layout(childLeft, childTop, childLeft + childWidth, childTop + childHeight);
            }

            if (changed) {
                mOverlayManager.onSizeChanged(width, height);
            }
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            int w = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
            int h = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
            int sizeSpecWidth = widthMeasureSpec;
            int sizeSpecHeight = heightMeasureSpec;

            final int count = getChildCount();
            for (int i = 0; i < count; i++) {
                final View view = getChildAt(i);

                if (view instanceof NMapView) {
                    if (mMapView.isAutoRotateEnabled()) {
                        int diag = (((int) (Math.sqrt(w * w + h * h)) + 1) / 2 * 2);
                        sizeSpecWidth = MeasureSpec.makeMeasureSpec(diag, MeasureSpec.EXACTLY);
                        sizeSpecHeight = sizeSpecWidth;
                    }
                }

                view.measure(sizeSpecWidth, sizeSpecHeight);
            }
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    class viewPointAsyncTask extends AsyncTask {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            int thisInterval = 0;
            while (true) {
                try {
                    if (first) {
                        publishProgress();
                        first = !first;
                    }

                    thisInterval += 100;
                    Thread.sleep(100);

                    if (thisInterval < Integer.parseInt(pref.getValue(SharedPreferenceManager.MY_MAP_INTERVAL, "10000"))) {
                        continue;
                    } else {
                        thisInterval = 0;
                    }

                    pref.put(SharedPreferenceManager.MY_MAPCOUNT, String.valueOf(Integer.parseInt(pref.getValue(SharedPreferenceManager.MY_MAPCOUNT, "1000")) + 1));

                    if (Integer.parseInt(pref.getValue(SharedPreferenceManager.MY_MAPCOUNT, "1000")) > 10000) {
                        first = true;
                        break;
                    }

                    if (Integer.parseInt(pref.getValue(SharedPreferenceManager.MY_MAPCOUNT, "1000")) % 10 == 0) {
                        //Log.e("POINT", Integer.parseInt(pref.getValue(SharedPreferenceManager.MY_MAPCOUNT, "1000")) + " " + myPoint.getLongitude() + ", " + myPoint.getLatitude());
                    }
                    if (myPoint.getLongitude() == 0.0 || myPoint.getLatitude() == 0.0) {

                    } else {
                        pref.put(SharedPreferenceManager.MY_LOCATION_LATITUDE, String.valueOf(myPoint.getLatitude()));
                        pref.put(SharedPreferenceManager.MY_LOCATION_LONGITUDE, String.valueOf(myPoint.getLongitude()));
                        first = true;
                        break;
                    }
                    findPlacemarkAtLocation(myPoint.longitude, myPoint.latitude);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Object[] values) {
            super.onProgressUpdate(values);
            NGeoPoint thisPoint = new NGeoPoint(Double.parseDouble(pref.getValue(SharedPreferenceManager.MY_LOCATION_LONGITUDE, "0.0")), Double.parseDouble(pref.getValue(SharedPreferenceManager.MY_LOCATION_LATITUDE, "0.0")));
            mMapController.setMapCenter(thisPoint, 14);
        }
    }
}

