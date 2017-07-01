package com.playdate.Utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.util.TypedValue;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.playdate.R;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;


/**
 * Created by admin on 1/12/2016.
 */
public class ProjectUtilities {

    public static int location_fetch_time_interval = 5 * 60000;

    public static double latitude, longitude;

    public static String imagePaths[] = {"assets://wall01.jpg",
            "assets://wall02.jpg", "assets://wall03.jpg",
            "assets://wall04.jpg", "assets://wall05.jpg",
            "assets://wall06.jpg", "assets://wall07.jpg",
            "assets://wall08.jpg", "assets://wall09.jpg",
            "assets://wall10.jpg", "assets://wall11.jpg",
            "assets://wall12.jpg", "assets://wall01.jpg",
            "assets://wall02.jpg", "assets://wall03.jpg",
            "assets://wall04.jpg", "assets://wall05.jpg",
            "assets://wall06.jpg", "assets://wall07.jpg",
            "assets://wall08.jpg", "assets://wall09.jpg",
            "assets://wall10.jpg", "assets://wall11.jpg", "assets://wall12.jpg"};

    public static String names[] = {"Neha", "Deepa", "Chitra", "Shefali", "Mahima", "Priyanka", "Dimple",
            "Aakansh", "Ravina", "Punam", "sunita", "Minakshi", "manisha", "Payal", "Pooja", "Sharmita", "Yogita",
            "Tejal", "Kajal", "Katrina", "Dipika", "Meena", "Shital", "Dimple"};
    private static final int SECOND_MILLIS = 1000;
    private static final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
    private static final int HOUR_MILLIS = 60 * MINUTE_MILLIS;
    private static final int DAY_MILLIS = 24 * HOUR_MILLIS;
    public static ProgressDialog pDialog;

    public static RequestQueue mVolleyQueue;
    public static Typeface mTypeface;
    public static String share_link = "https://goo.gl/30y5oq";
    public static String[] thumbColumns = {MediaStore.Video.Thumbnails.DATA};
    public static String[] mediaColumns = {MediaStore.Video.Media._ID};
    // Define variable
    static Boolean isInternetPresent = false;
    static ConnectionDetector cd;

    public static void setEditTextFont(Context mContext, EditText mEditText) {
        mTypeface = Typeface.createFromAsset(mContext.getAssets(),
                "fonts/OpenSans-Regular_2.ttf");

        mEditText.setTypeface(mTypeface);
    }

    public static void setTextViewRegularFont(Context mContext, TextView mTextView) {
        mTypeface = Typeface.createFromAsset(mContext.getAssets(),
                "fonts/OpenSans-Regular_2.ttf");
        mTextView.setTypeface(mTypeface);
    }

    public static void setTextViewBoldFont(Context mContext, TextView mTextView) {
        mTypeface = Typeface.createFromAsset(mContext.getAssets(),
                "fonts/OpenSans-Semibold_2.ttf");

        mTextView.setTypeface(mTypeface);
    }


    // This method is for checking internet connection
    public static Boolean checkInternetAvailable(Context mContext) {
        cd = new ConnectionDetector(mContext);
        isInternetPresent = cd.isConnectingToInternet();

        if (isInternetPresent) {
            return true;
        } else {
            return false;
        }

    }

    //This method hide keyboard
    public static void hideKeyboard(Activity mActivity) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) mActivity
                    .getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(mActivity
                    .getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {

        }
    }

    //This method show progress dialog
    public static void showProgressDialog(Context mContext) {
        pDialog = new ProgressDialog(mContext);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();
    }

    //This method dismiss progress dialog
    public static void dismissProgressDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    //this method show internet diaolg
    public static void internetDialog(Context mContext) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);

        alertDialog.setTitle(mContext.getResources()
                .getString(R.string.warning));
        alertDialog.setMessage(mContext.getResources().getString(
                R.string.internet_error));

        alertDialog.setPositiveButton(
                mContext.getResources().getString(R.string.btn_ok),
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub

                        dialog.cancel();
                    }
                });
        alertDialog.show();
    }

    //this method show internet diaolg
    public static void showAlertDialog(Context mContext, String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);


        alertDialog.setMessage(message);

        alertDialog.setPositiveButton(
                mContext.getResources().getString(R.string.btn_ok),
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub

                        dialog.cancel();
                    }
                });
        alertDialog.show();
    } //this method show internet diaolg

    public static void showAlertDialogWithBack(final Activity mContext, String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton(
                mContext.getResources().getString(R.string.btn_ok),
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        mContext.onBackPressed();
                        dialog.cancel();
                    }
                });
        alertDialog.show();
    }

    //this method show internet diaolg
    public static void showAlertDialogAndResetApp(Context mContext, String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);


        alertDialog.setMessage(message);

        alertDialog.setPositiveButton(
                mContext.getResources().getString(R.string.btn_ok),
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub

                        dialog.cancel();
                    }
                });
        alertDialog.show();
    }

    public static void showToast(Context mContext, String message) {
        Toast.makeText(mContext, "" + message, Toast.LENGTH_SHORT).show();
    }


    /**
     * Converting dp to pixel
     */
    public static int dpToPx(Context mContext, int dp) {
        Resources r = mContext.getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    public static String getTimeAgo(long time) {
        if (time < 1000000000000L) {
            // if timestamp given in seconds, convert to millis
            time *= 1000;
        }

        long now = System.currentTimeMillis();
        if (time > now || time <= 0) {
            return null;
        }

        // TODO: localize
        final long diff = now - time;
        if (diff < MINUTE_MILLIS) {
            return "just now";
        } else if (diff < 2 * MINUTE_MILLIS) {
            return "a minute ago";
        } else if (diff < 50 * MINUTE_MILLIS) {
            return diff / MINUTE_MILLIS + " minutes ago";
        } else if (diff < 90 * MINUTE_MILLIS) {
            return "an hour ago";
        } else if (diff < 24 * HOUR_MILLIS) {
            return diff / HOUR_MILLIS + " hours ago";
        } else if (diff < 48 * HOUR_MILLIS) {
            return "yesterday";
        } else {
            //return diff / DAY_MILLIS + "d ago"; //days ago

            long elapsedDays = diff / DAY_MILLIS;

            if (elapsedDays <= 29) {
                return String.valueOf(elapsedDays) + "d ago";
            } else if (elapsedDays > 29 && elapsedDays <= 58) {
                return "1M ago";
            } else if (elapsedDays > 58 && elapsedDays <= 87) {
                return "2M ago";
            } else if (elapsedDays > 87 && elapsedDays <= 116) {
                return "3M ago";
            } else if (elapsedDays > 116 && elapsedDays <= 145) {
                return "4M ago";
            } else if (elapsedDays > 145 && elapsedDays <= 174) {
                return "5M ago";
            } else if (elapsedDays > 174 && elapsedDays <= 203) {
                return "6M ago";
            } else if (elapsedDays > 203 && elapsedDays <= 232) {
                return "7M ago";
            } else if (elapsedDays > 232 && elapsedDays <= 261) {
                return "8M ago";
            } else if (elapsedDays > 261 && elapsedDays <= 290) {
                return "9M ago";
            } else if (elapsedDays > 290 && elapsedDays <= 319) {
                return "10M ago";
            } else if (elapsedDays > 319 && elapsedDays <= 348) {
                return "11M ago";
            } else if (elapsedDays > 348 && elapsedDays <= 360) {
                return "12M ago";
            } else if (elapsedDays > 360 && elapsedDays <= 720) {
                return "1Y ago";
            } else if (elapsedDays > 720) {
                SimpleDateFormat formatterYear = new SimpleDateFormat("dd/MMM/yyyy");
                Calendar calendarYear = Calendar.getInstance();
                calendarYear.setTimeInMillis(time);
                return formatterYear.format(calendarYear.getTime()) + "";
            } else {
                return "";
            }

        }
    }

    public static long getTimeMili(String givenDateString) {
        long timeInMilliseconds = 0;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date mDate = sdf.parse(givenDateString);
            timeInMilliseconds = mDate.getTime();
            System.out.println("Date in milli :: " + timeInMilliseconds);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeInMilliseconds;
    }

    public static boolean checkPermission(Context mContext) {


        if ((int) PermissionChecker.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(mContext, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            return true;
        } else {
            return false;
        }
    }

    @SuppressLint("NewApi")
    public static String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/"
                            + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"),
                        Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};

                return getDataColumn(context, contentUri, selection,
                        selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    public static String getThumbnailPathForLocalFile(Activity context,
                                                      Uri fileUri) {

        long fileId = getFileId(context, fileUri);

        MediaStore.Video.Thumbnails.getThumbnail(context.getContentResolver(),
                fileId, MediaStore.Video.Thumbnails.MICRO_KIND, null);

        Cursor thumbCursor = null;
        try {

            thumbCursor = context.managedQuery(
                    MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI,
                    thumbColumns, MediaStore.Video.Thumbnails.VIDEO_ID + " = "
                            + fileId, null, null);

            if (thumbCursor.moveToFirst()) {
                String thumbPath = thumbCursor.getString(thumbCursor
                        .getColumnIndex(MediaStore.Video.Thumbnails.DATA));

                return thumbPath;
            }

        } finally {
        }

        return null;
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri
                .getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri
                .getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri
                .getAuthority());
    }

    public static String getDataColumn(Context context, Uri uri,
                                       String selection, String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};

        try {
            cursor = context.getContentResolver().query(uri, projection,
                    selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    public static long getFileId(Activity context, Uri fileUri) {

        Cursor cursor = context.managedQuery(fileUri, mediaColumns, null, null,
                null);

        if (cursor.moveToFirst()) {
            int columnIndex = cursor
                    .getColumnIndexOrThrow(MediaStore.Video.Media._ID);
            int id = cursor.getInt(columnIndex);

            return id;
        }

        return 0;
    }

    /**
     * Get IP address from first non-localhost interface
     *
     * @return address or empty string
     */
    public static String getIPAddress(boolean useIPv4) {
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                for (InetAddress addr : addrs) {
                    if (!addr.isLoopbackAddress()) {
                        String sAddr = addr.getHostAddress();
                        //boolean isIPv4 = InetAddressUtils.isIPv4Address(sAddr);
                        boolean isIPv4 = sAddr.indexOf(':') < 0;

                        if (useIPv4) {
                            if (isIPv4)
                                return sAddr;
                        } else {
                            if (!isIPv4) {
                                int delim = sAddr.indexOf('%'); // drop ip6 zone suffix
                                return delim < 0 ? sAddr.toUpperCase() : sAddr.substring(0, delim).toUpperCase();
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
        } // for now eat exceptions
        return "";
    }

}
