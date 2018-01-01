package io.github.ryanhoo.firFlight.data.source.local.db.tables;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

import io.github.ryanhoo.firFlight.data.model.Courses;

/**
 * Created with Android Studio.
 * User: ryan.hoo.j@gmail.com
 * Date: 6/1/16
 * Time: 12:59 PM
 * Desc: AppTable
 */
public final class AppTable implements BaseColumns, BaseTable<Courses> {

    private static final String TAG = "AppTable";

    // Table Name
    public static final String TABLE_NAME = "course";

    // Columns
    public static final String COLUMN_ID = _ID; // "_id"
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_CLASS_NUM = "created_at";
    public static final String COLUMN_ICON_URL = "icon_url";
    public static final String COLUMN_VEDIO_HLS = "vedio_hls";

    // Create & Delete
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME +
                    " ( " +
                    COLUMN_ID + " TEXT PRIMARY KEY UNIQUE, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_CLASS_NUM + " INTEGER, " +
                    COLUMN_ICON_URL + " TEXT , " +
                    COLUMN_VEDIO_HLS + " TEXT" +
                    " );";

    public static final String DELETE_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public static final String QUERY_ALL_APPS = "SELECT * FROM " + TABLE_NAME + ";";

    public static final String WHERE_ID_EQUALS = COLUMN_ID + "=?";

    @Override
    public String createTableSql() {
        return CREATE_TABLE;
    }

    @Override
    public String deleteTableSql() {
        return DELETE_TABLE;
    }

    @Override
    public ContentValues toContentValues(Courses courses) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, courses.getId());
        contentValues.put(COLUMN_NAME, courses.getName());
        contentValues.put(COLUMN_ICON_URL, courses.getTeacherAvatar());

        String vidioHls = "";
        Courses.PresentationVideoBean videoBean = courses.getPresentationVideo();
        if (videoBean != null) {
            Courses.PresentationVideoBean.HlsBean hlsBean = videoBean.getHls();
            if (hlsBean != null) {
                vidioHls = hlsBean.getMobileMid();
            }
        }
        contentValues.put(COLUMN_VEDIO_HLS, vidioHls);
        contentValues.put(COLUMN_CLASS_NUM, courses.getNumOfClasses());

        return contentValues;
    }

    @Override
    public Courses parseCursor(Cursor c) {
        Courses course = new Courses();
        course.setId(c.getString(c.getColumnIndexOrThrow(COLUMN_ID)));
        course.setName(c.getString(c.getColumnIndexOrThrow(COLUMN_NAME)));
        course.setTeacherAvatar(c.getString(c.getColumnIndexOrThrow(COLUMN_ICON_URL)));
        Courses.PresentationVideoBean videoBean = new Courses.PresentationVideoBean();
        Courses.PresentationVideoBean.HlsBean hlsBean = new Courses.PresentationVideoBean.HlsBean();
        hlsBean.setMobileMid(c.getString(c.getColumnIndexOrThrow(COLUMN_VEDIO_HLS)));
        videoBean.setHls(hlsBean);
        course.setPresentationVideo(videoBean);
        course.setNumOfClasses(c.getInt(c.getColumnIndexOrThrow(COLUMN_CLASS_NUM)));
        return course;
    }
}
