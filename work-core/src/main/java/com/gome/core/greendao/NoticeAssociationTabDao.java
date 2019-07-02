package com.gome.core.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.gome.work.core.model.im.NoticeAssociationTab;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "NOTICE_ASSOCIATION_TAB".
*/
public class NoticeAssociationTabDao extends AbstractDao<NoticeAssociationTab, Long> {

    public static final String TABLENAME = "NOTICE_ASSOCIATION_TAB";

    /**
     * Properties of entity NoticeAssociationTab.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property AppId = new Property(1, String.class, "appId", false, "APP_ID");
        public final static Property AppName = new Property(2, String.class, "appName", false, "APP_NAME");
        public final static Property UnReadCount = new Property(3, int.class, "unReadCount", false, "UN_READ_COUNT");
        public final static Property Title = new Property(4, String.class, "title", false, "TITLE");
        public final static Property Content = new Property(5, String.class, "content", false, "CONTENT");
        public final static Property SendTime = new Property(6, long.class, "sendTime", false, "SEND_TIME");
        public final static Property Icon = new Property(7, String.class, "icon", false, "ICON");
        public final static Property IsShield = new Property(8, int.class, "isShield", false, "IS_SHIELD");
        public final static Property IsTop = new Property(9, int.class, "isTop", false, "IS_TOP");
        public final static Property MsgType = new Property(10, int.class, "msgType", false, "MSG_TYPE");
        public final static Property GroupType = new Property(11, int.class, "groupType", false, "GROUP_TYPE");
        public final static Property IsDelete = new Property(12, int.class, "isDelete", false, "IS_DELETE");
        public final static Property DataUseId = new Property(13, String.class, "dataUseId", false, "DATA_USE_ID");
    }


    public NoticeAssociationTabDao(DaoConfig config) {
        super(config);
    }
    
    public NoticeAssociationTabDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"NOTICE_ASSOCIATION_TAB\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"APP_ID\" TEXT," + // 1: appId
                "\"APP_NAME\" TEXT," + // 2: appName
                "\"UN_READ_COUNT\" INTEGER NOT NULL ," + // 3: unReadCount
                "\"TITLE\" TEXT," + // 4: title
                "\"CONTENT\" TEXT," + // 5: content
                "\"SEND_TIME\" INTEGER NOT NULL ," + // 6: sendTime
                "\"ICON\" TEXT," + // 7: icon
                "\"IS_SHIELD\" INTEGER NOT NULL ," + // 8: isShield
                "\"IS_TOP\" INTEGER NOT NULL ," + // 9: isTop
                "\"MSG_TYPE\" INTEGER NOT NULL ," + // 10: msgType
                "\"GROUP_TYPE\" INTEGER NOT NULL ," + // 11: groupType
                "\"IS_DELETE\" INTEGER NOT NULL ," + // 12: isDelete
                "\"DATA_USE_ID\" TEXT);"); // 13: dataUseId
        // Add Indexes
        db.execSQL("CREATE UNIQUE INDEX " + constraint + "IDX_NOTICE_ASSOCIATION_TAB_APP_ID_DESC_DATA_USE_ID_DESC ON \"NOTICE_ASSOCIATION_TAB\"" +
                " (\"APP_ID\" DESC,\"DATA_USE_ID\" DESC);");
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"NOTICE_ASSOCIATION_TAB\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, NoticeAssociationTab entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String appId = entity.getAppId();
        if (appId != null) {
            stmt.bindString(2, appId);
        }
 
        String appName = entity.getAppName();
        if (appName != null) {
            stmt.bindString(3, appName);
        }
        stmt.bindLong(4, entity.getUnReadCount());
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(5, title);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(6, content);
        }
        stmt.bindLong(7, entity.getSendTime());
 
        String icon = entity.getIcon();
        if (icon != null) {
            stmt.bindString(8, icon);
        }
        stmt.bindLong(9, entity.getIsShield());
        stmt.bindLong(10, entity.getIsTop());
        stmt.bindLong(11, entity.getMsgType());
        stmt.bindLong(12, entity.getGroupType());
        stmt.bindLong(13, entity.getIsDelete());
 
        String dataUseId = entity.getDataUseId();
        if (dataUseId != null) {
            stmt.bindString(14, dataUseId);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, NoticeAssociationTab entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String appId = entity.getAppId();
        if (appId != null) {
            stmt.bindString(2, appId);
        }
 
        String appName = entity.getAppName();
        if (appName != null) {
            stmt.bindString(3, appName);
        }
        stmt.bindLong(4, entity.getUnReadCount());
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(5, title);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(6, content);
        }
        stmt.bindLong(7, entity.getSendTime());
 
        String icon = entity.getIcon();
        if (icon != null) {
            stmt.bindString(8, icon);
        }
        stmt.bindLong(9, entity.getIsShield());
        stmt.bindLong(10, entity.getIsTop());
        stmt.bindLong(11, entity.getMsgType());
        stmt.bindLong(12, entity.getGroupType());
        stmt.bindLong(13, entity.getIsDelete());
 
        String dataUseId = entity.getDataUseId();
        if (dataUseId != null) {
            stmt.bindString(14, dataUseId);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public NoticeAssociationTab readEntity(Cursor cursor, int offset) {
        NoticeAssociationTab entity = new NoticeAssociationTab( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // appId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // appName
            cursor.getInt(offset + 3), // unReadCount
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // title
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // content
            cursor.getLong(offset + 6), // sendTime
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // icon
            cursor.getInt(offset + 8), // isShield
            cursor.getInt(offset + 9), // isTop
            cursor.getInt(offset + 10), // msgType
            cursor.getInt(offset + 11), // groupType
            cursor.getInt(offset + 12), // isDelete
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13) // dataUseId
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, NoticeAssociationTab entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setAppId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setAppName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setUnReadCount(cursor.getInt(offset + 3));
        entity.setTitle(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setContent(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setSendTime(cursor.getLong(offset + 6));
        entity.setIcon(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setIsShield(cursor.getInt(offset + 8));
        entity.setIsTop(cursor.getInt(offset + 9));
        entity.setMsgType(cursor.getInt(offset + 10));
        entity.setGroupType(cursor.getInt(offset + 11));
        entity.setIsDelete(cursor.getInt(offset + 12));
        entity.setDataUseId(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(NoticeAssociationTab entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(NoticeAssociationTab entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(NoticeAssociationTab entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
