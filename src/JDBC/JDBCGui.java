package JDBC;



import javax.swing.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JDBCGui {
    private JPanel panel1;
    private JTable table1;
    private JScrollPane scrollPane1;


    public void insertValue(ResultSet rs){
        try {
            List<String> nameList = new ArrayList<String>();
            List<String> Rowlist= new ArrayList<>();


            for (int i = 1; i < rs.getMetaData().getColumnCount() + 1; i++) {
                nameList.add(rs.getMetaData().getColumnName(i));
            }
            //model.addRow(nameList.toArray());
            while (rs.next()) {
                List<String> list = new ArrayList<>();
                for (int i = 1; i < rs.getMetaData().getColumnCount() + 1; i++) {
                    list.add(rs.getObject(i).toString());

                }

                for (String s:list){
                    Rowlist.add(s);
                }

            }
            MyTableModel mtm = new MyTableModel();
            mtm.getColumnNames(nameList);
            mtm.getData(Rowlist);
            table1.setModel(mtm);

            displayTable();

        }
        catch(Exception E) {
            System.out.println(E);
            E.printStackTrace();
        }

    }

    public void displayTable() {
        JFrame frame = new JFrame("JDBCGUI");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

