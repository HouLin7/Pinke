package com.gome.core.greendao;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.SqlUtils;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.gome.work.core.model.dao.FileItemInfo;

import com.gome.work.core.model.dao.FileTransferTaskInfo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "FILE_TRANSFER_TASK_INFO".
*/
public class FileTransferTaskInfoDao extends AbstractDao<FileTransferTaskInfo, Long> {

    public static final String TABLENAME = "FILE_TRANSFER_TASK_INFO";

    /**
     * Properties of entity FileTransferTaskInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property FileId = new Property(1, long.class, "fileId", false, "FILE_ID");
        public final static Property State = new Property(2, String.class, "state", false, "STATE");
        public final static Property CreateDate = new Property(3, long.class, "createDate", false, "CREATE_DATE");
        public final static Property ContentLength = new Property(4, long.class, "contentLength", false, "CONTENT_LENGTH");
        public final static Property DownloadUrl = new Property(5, String.class, "downloadUrl", false, "DOWNLOAD_URL");
        public final static Property FileName = new Property(6, String.class, "fileName", false, "FILE_NAME");
        public final static Property FilePath = new Property(7, String.class, "filePath", false, "FILE_PATH");
        public final static Property Direct = new Property(8, int.class, "direct", false, "DIRECT");
        public final static Property TransferLength = new Property(9, long.class, "transferLength", false, "TRANSFER_LENGTH");
        public final static Property FromSourceCode = new Property(10, String.class, "fromSourceCode", false, "FROM_SOURCE_CODE");
        public final static Property FromSourceName = new Property(11, String.class, "fromSourceName", false, "FROM_SOURCE_NAME");
        public final static Property MimeType = new Property(12, String.class, "mimeType", false, "MIME_TYPE");
    }

    private DaoSession daoSession;


    public FileTransferTaskInfoDao(DaoConfig config) {
        super(config);
    }
    
    public FileTransferTaskInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"FILE_TRANSFER_TASK_INFO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"FILE_ID\" INTEGER NOT NULL ," + // 1: fileId
                "\"STATE\" TEXT NOT NULL ," + // 2: state
                "\"CREATE_DATE\" INTEGER NOT NULL ," + // 3: createDate
                "\"CONTENT_LENGTH\" INTEGER NOT NULL ," + // 4: contentLength
                "\"DOWNLOAD_URL\" TEXT NOT NULL ," + // 5: downloadUrl
                "\"FILE_NAME\" TEXT NOT NULL ," + // 6: fileName
                "\"FILE_PATH\" TEXT," + // 7: filePath
                "\"DIRECT\" INTEGER NOT NULL ," + // 8: direct
                "\"TRANSFER_LENGTH\" INTEGER NOT NULL ," + // 9: transferLength
                "\"FROM_SOURCE_CODE\" TEXT," + // 10: fromSourceCode
                "\"FROM_SOURCE_NAME\" TEXT," + // 11: fromSourceName
                "\"MIME_TYPE\" TEXT);"); // 12: mimeType
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"FILE_TRANSFER_TASK_INFO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, FileTransferTaskInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getFileId());
        stmt.bindString(3, entity.getState());
        stmt.bindLong(4, entity.getCreateDate());
        stmt.bindLong(5, entity.getContentLength());
        stmt.bindString(6, entity.getDownloadUrl());
        stmt.bindString(7, entity.getFileName());
 
        String filePath = entity.getFilePath();
        if (filePath != null) {
            stmt.bindString(8, filePath);
        }
        stmt.bindLong(9, entity.getDirect());
        stmt.bindLong(10, entity.getTransferLength());
 
        String fromSourceCode = entity.getFromSourceCode();
        if (fromSourceCode != null) {
            stmt.bindString(11, fromSourceCode);
        }
 
        String fromSourceName = entity.getFromSourceName();
        if (fromSourceName != null) {
            stmt.bindString(12, fromSourceName);
        }
 
        String mimeType = entity.getMimeType();
        if (mimeType != null) {
            stmt.bindString(13, mimeType);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, FileTransferTaskInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getFileId());
        stmt.bindString(3, entity.getState());
        stmt.bindLong(4, entity.getCreateDate());
        stmt.bindLong(5, entity.getContentLength());
        stmt.bindString(6, entity.getDownloadUrl());
        stmt.bindString(7, entity.getFileName());
 
        String filePath = entity.getFilePath();
        if (filePath != null) {
            stmt.bindString(8, filePath);
        }
        stmt.bindLong(9, entity.getDirect());
        stmt.bindLong(10, entity.getTransferLength());
 
        String fromSourceCode = entity.getFromSourceCode();
        if (fromSourceCode != null) {
            stmt.bindString(11, fromSourceCode);
        }
 
        String fromSourceName = entity.getFromSourceName();
        if (fromSourceName != null) {
            stmt.bindString(12, fromSourceName);
        }
 
        String mimeType = entity.getMimeType();
        if (mimeType != null) {
            stmt.bindString(13, mimeType);
        }
    }

    @Override
    protected final void attachEntity(FileTransferTaskInfo entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public FileTransferTaskInfo readEntity(Cursor cursor, int offset) {
        FileTransferTaskInfo entity = new FileTransferTaskInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getLong(offset + 1), // fileId
            cursor.getString(offset + 2), // state
            cursor.getLong(offset + 3), // createDate
            cursor.getLong(offset + 4), // contentLength
            cursor.getString(offset + 5), // downloadUrl
            cursor.getString(offset + 6), // fileName
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // filePath
            cursor.getInt(offset + 8), // direct
            cursor.getLong(offset + 9), // transferLength
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // fromSourceCode
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // fromSourceName
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12) // mimeType
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, FileTransferTaskInfo entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setFileId(cursor.getLong(offset + 1));
        entity.setState(cursor.getString(offset + 2));
        entity.setCreateDate(cursor.getLong(offset + 3));
        entity.setContentLength(cursor.getLong(offset + 4));
        entity.setDownloadUrl(cursor.getString(offset + 5));
        entity.setFileName(cursor.getString(offset + 6));
        entity.setFilePath(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setDirect(cursor.getInt(offset + 8));
        entity.setTransferLength(cursor.getLong(offset + 9));
        entity.setFromSourceCode(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setFromSourceName(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setMimeType(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(FileTransferTaskInfo entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(FileTransferTaskInfo entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(FileTransferTaskInfo entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getFileItemInfoDao().getAllColumns());
            builder.append(" FROM FILE_TRANSFER_TASK_INFO T");
            builder.append(" LEFT JOIN FILE_ITEM_INFO T0 ON T.\"FILE_ID\"=T0.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected FileTransferTaskInfo loadCurrentDeep(Cursor cursor, boolean lock) {
        FileTransferTaskInfo entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        FileItemInfo fileItemInfo = loadCurrentOther(daoSession.getFileItemInfoDao(), cursor, offset);
         if(fileItemInfo != null) {
            entity.setFileItemInfo(fileItemInfo);
        }

        return entity;    
    }

    public FileTransferTaskInfo loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<FileTransferTaskInfo> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<FileTransferTaskInfo> list = new ArrayList<FileTransferTaskInfo>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<FileTransferTaskInfo> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<FileTransferTaskInfo> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
