package JDBC;

import javax.swing.table.AbstractTableModel;
import java.util.List;

class MyTableModel extends AbstractTableModel {

    Object [][]data;
    String [] columnNames;

    public void getColumnNames(List<String> l) {
        columnNames = new String[l.size()];
        for (int z = 0; z<l.size();z++) {
            columnNames[z] = l.get(z);
        }
    }

    public void getData(List<String> list) {
        int count=0;

        data = new Object[list.size()][columnNames.length];
        for (int i = 0; i< (list.size()/columnNames.length);i++) {
            for (int j = 0; j< columnNames.length ;j++) {


                data[i][j] = list.get(count);
                count++;
            }
        }
    }



    public int getRowCount() {
        return data.length;
    }


    public int getColumnCount() {
        return columnNames.length;
    }


    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }


    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
    }


    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }
}
