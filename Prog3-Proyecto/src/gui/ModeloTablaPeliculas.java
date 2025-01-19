package gui;

import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

public class ModeloTablaPeliculas extends DefaultTableModel {

	private static final long serialVersionUID = 1L;

	public ModeloTablaPeliculas() {
        addColumn("Poster");
        addColumn("Título");
    }

    @Override
    public boolean isCellEditable(int row, int column) { //if usuario != admin, return false
        return false;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 0) return ImageIcon.class; 
        return String.class;
    }
}
