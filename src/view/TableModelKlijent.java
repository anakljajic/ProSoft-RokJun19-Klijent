/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import domain.LinijaStanica;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author anakl
 */
public class TableModelKlijent extends AbstractTableModel {

    private List<LinijaStanica> linijeStanice;
    String[] columnNames = new String[]{"Naziv medjustanice"};

    public TableModelKlijent(List<LinijaStanica> linijeStanice) {
        this.linijeStanice = linijeStanice;
    }

    @Override
    public int getRowCount() {
        return linijeStanice.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        LinijaStanica ls = linijeStanice.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return ls.getStanica().getNazivStanice();
            default:
                return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public void azuriraj(List<LinijaStanica> linijeStanice) {
        this.linijeStanice = linijeStanice;
        fireTableDataChanged();
    }

}
