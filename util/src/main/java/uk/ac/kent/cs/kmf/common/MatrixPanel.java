package uk.ac.kent.cs.kmf.common;

import java.awt.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * @author Octavian Patrascoiu
 *
 */
public class MatrixPanel 
	extends JPanel
{
	/** Constructor */
	public MatrixPanel() {
		super(new GridBagLayout());
		//--- Give the panel a border gap of 10 pixels ---
		setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
		//--- Get the constraints ---
		constraints = new GridBagConstraints();
		//--- Add space around all components to avoid clutter ---
		constraints.insets = new Insets(2, 2, 2, 2);
		constraints.ipadx = 5;
		constraints.ipady = 5;
		constraints.weightx = 0.0;
		constraints.weighty = 0.0;
		//--- Initialize common variables ---
		line = 0;
		column = 0;
		itemBorder = new EmptyBorder(new Insets(0, 0, 0, 10));
		buttonSize = new Dimension(80, 25);
		listSize = new Dimension(300, 100);
		fieldSize = 25;
	}
	
	// Add a component
	public Component add(Component c, int line, int column) {
		constraints.gridx = column; 
		constraints.gridy = line;
		constraints.anchor = GridBagConstraints.WEST;
		add(c, constraints);
		return c; 
	}

	// Add a component
	public Component add(Component c, int line, int column, int anchor) {
		constraints.gridx = column; 
		constraints.gridy = line;
		constraints.anchor = anchor;
		add(c, constraints);
		return c; 
	}

	// Add a label
	public JLabel addLabel(String tag, int line, int column) {
		JLabel label = new JLabel(tag);
		label.setBorder(itemBorder);
		add(label, line, column);
		return label; 
	}

	// Add a text field
	public JTextField addTextField(String initialContent, int line, int column) {
		JTextField field = new JTextField(fieldSize);
		field.setText(initialContent);
		add(field, line, column);
		return field; 
	}
	
	// Add a list
	public JList addList(Vector objects, int line, int column) {
		JList list = new JList(objects);
		JScrollPane c = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		c.setPreferredSize(listSize);
		add(c, line, column);
		return list; 
	}
	public JList addList(Vector objects, Dimension dim, int line, int column) {
		JList list = new JList(objects);
		JScrollPane c = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		c.setPreferredSize(dim);
		add(c, line, column);
		return list; 
	}
	
	// Add a button
	public JButton addButton(String label, int line, int column) {
		JButton button = new JButton(label);
		button.setPreferredSize(buttonSize);
		add(button, line, column);
		return button; 
	}

	/** Button size */
	public Dimension getButtonSize() {	return buttonSize; }
	public void setButtonSize(Dimension dimension) { buttonSize = dimension; }

	/** Field size */
	public int getFieldSize() {	return fieldSize; }
	public void setFieldSize(int i) { fieldSize = i; }

	/** List size */
	public Dimension getListSize() { return listSize; }
	public void setListSize(Dimension i) { listSize = i; }

	/** Item border */
	public EmptyBorder getItemBorder() { return itemBorder;	}
	public void setItemBorder(EmptyBorder border) {	itemBorder = border; }

	/** Constraints */
	public GridBagConstraints getConstraints() { return constraints; }
	public void setConstraints(GridBagConstraints constraints) { this.constraints = constraints; }

	// Local properties
	protected GridBagConstraints constraints;
	protected int line;
	protected int column;
	protected EmptyBorder itemBorder;
	protected int fieldSize;
	protected Dimension listSize;
	protected Dimension buttonSize;
}
