package components;

import models.UserModel;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TableUserModel extends AbstractTableModel {
    public static final int COLUMN_UID = 0;
    public static final int COLUMN_USERNAME = 1;
    public static final int COLUMN_PASSWORD = 2;
    public static final int COLUMN_NAME = 3;
    public static final int COLUMN_ADMIN = 4;
    public static final int COLUMN_TEACHER = 5;

    public static final String[] COLUMN_NAMES = {
            "UID",
            "Usuario",
            "Senha",
            "Nome",
            "Admin",
            "Professor"
    };

    private final List<UserModel> userModelList;

    public TableUserModel(List<UserModel> userModelList) {
        this.userModelList = userModelList;
    }

    @Override
    public int getRowCount() {
        return userModelList.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public String getColumnName( int column )
    {
        return COLUMN_NAMES[column];
    }


    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        UserModel user = userModelList.get(rowIndex);
        String val = "";

        switch (columnIndex)
        {
            case COLUMN_UID:
                val = String.valueOf(user.getUID());
                break;
            case COLUMN_USERNAME:
                val = user.getUsername();
                break;
            case COLUMN_PASSWORD:
                val = user.getPassword();
                break;
            case COLUMN_NAME:
                val = user.getName();
                break;
            case COLUMN_ADMIN:
                val = String.valueOf(user.getAdmin());
                break;
            case COLUMN_TEACHER:
                val = String.valueOf(user.getTeacher());
                break;
        }
        return val;
    }
}
