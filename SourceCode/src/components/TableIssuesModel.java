package components;

import models.IssueModel;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TableIssuesModel extends AbstractTableModel {
    public static final int COLUMN_ISID = 0;
    public static final int COLUMN_BID = 1;
    public static final int COLUMN_UID = 2;
    public static final int COLUMN_ISSUEDATE = 3;
    public static final int COLUMN_RETURNDATE = 4;

    public static final String[] COLUMN_NAMES = {
            "ISID",
            "BID",
            "UID",
            "ISSUE DATE",
            "RETURN DATE"
    };

    private final List<IssueModel> issueModelList;

    public TableIssuesModel(List<IssueModel> issueModelList) {
        this.issueModelList = issueModelList;
    }

    @Override
    public int getRowCount() {
        return issueModelList.size();
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
        IssueModel issue = issueModelList.get(rowIndex);
        String val = "";

        switch (columnIndex)
        {
            case COLUMN_ISID:
                val = String.valueOf(issue.getISID());
                break;
            case COLUMN_BID:
                val = String.valueOf(issue.getBID());
                break;
            case COLUMN_UID:
                val = String.valueOf(issue.getUID());
                break;
            case COLUMN_ISSUEDATE:
                val = String.valueOf(issue.getIssueDate());
                break;
            case COLUMN_RETURNDATE:
                val = String.valueOf(issue.getReturnDate());
                break;
        }
        return val;
    }
}
