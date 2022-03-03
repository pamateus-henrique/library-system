package components;

import models.BookModel;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TableBooksModel extends AbstractTableModel {
    public static final int COLUMN_BID = 0;
    public static final int COLUMN_TITLE = 1;
    public static final int COLUMN_AUTHOR = 2;
    public static final int COLUMN_EDITION = 3;
    public static final int COLUMN_PUBLISHER = 4;
    public static final int COLUMN_YEAR = 5;
    public static final int COLUMN_ISBN = 6;

    public static final String[] COLUMN_NAMES = {
            "BID",
            "Título",
            "Autor",
            "Edição",
            "Editora",
            "Ano",
            "ISBN"
    };

    private final List<BookModel> booksModelList;

    public TableBooksModel(List<BookModel> booksModelList) {
        this.booksModelList = booksModelList;
    }

    @Override
    public int getRowCount() {
        return booksModelList.size();
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
        BookModel book = booksModelList.get(rowIndex);
        String val = "";

        switch (columnIndex)
        {
            case COLUMN_BID:
                val = String.valueOf(book.getBID());
                break;
            case COLUMN_TITLE:
                val = book.getTitle();
                break;
            case COLUMN_AUTHOR:
                val = book.getAuthor();
                break;
            case COLUMN_EDITION:
                val = String.valueOf(book.getEdition());
                break;
            case COLUMN_PUBLISHER:
                val = book.getPublisher();
                break;
            case COLUMN_YEAR:
                val = String.valueOf(book.getYear());
                break;
            case COLUMN_ISBN:
                val = String.valueOf(book.getISBN());
                break;
        }
        return val;
    }
}
