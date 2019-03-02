package gui;

import entities.Book;
import entities.Stock;
import javafx.scene.input.KeyCode;

import java.awt.LayoutManager;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;
import java.awt.SystemColor;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

public abstract class TabPnl extends JPanel {
	protected JComboBox comboBox;
	protected JTextField textField;
	protected JButton btnAdvSearch;
	protected JButton btnAdd;
	protected JScrollPane scrollPane;
	protected JTable table;
	protected JPopupMenu popMenu;

	protected JButton btnDelete;
	protected JButton btnEdit;
	protected JMenuItem deleteItem;
	protected JMenuItem modifyItem;
	protected JMenuItem insertItem;

	public TabPnl() {
		super();
		setForeground(new Color(51, 51, 153));
		setBackground(new Color(255, 255, 255));
		setBorder(new LineBorder(new Color(0, 0, 0)));
		init();
		initListeners();
		// TODO Auto-generated constructor stub
	}

	public TabPnl(boolean arg0) {
		super(arg0);
		init();
		initListeners();
		// TODO Auto-generated constructor stub
	}

	public TabPnl(LayoutManager arg0, boolean arg1) {
		super(arg0, arg1);
		init();
		initListeners();
		// TODO Auto-generated constructor stub
	}

	public TabPnl(LayoutManager layout) {
		super(layout);
		init();
		initListeners();
		// TODO Auto-generated constructor stub
	}

	private void init() {

		table = new JTable();
		setLayout(null);

		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(1, 1, 753, 23);
		toolBar.setFloatable(false);
		add(toolBar);
		comboBox = new JComboBox();
		toolBar.add(comboBox);
		textField = new JTextField();
		toolBar.add(textField);
		textField.setColumns(10);
		btnAdvSearch = new JButton("Advanced Search");
		toolBar.add(btnAdvSearch);
		btnAdd = new JButton("Add");

		toolBar.add(btnAdd);
		btnEdit = new JButton("Modify");
		toolBar.add(btnEdit);
		btnDelete = new JButton("Delete");
		popMenu = new JPopupMenu();
		deleteItem = new JMenuItem("Delete");
		modifyItem = new JMenuItem("Modify");
		insertItem = new JMenuItem("Insert");
		popMenu.add(deleteItem);
		popMenu.add(modifyItem);
		popMenu.add(insertItem);
		popMenu.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                    	if(table.getSelectedRow()==-1) {
                    		deleteItem.setEnabled(false);
                    		modifyItem.setEnabled(false);
                    	}else {
                    		deleteItem.setEnabled(true);
                    		modifyItem.setEnabled(true);
                    	}
                    }
                });
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
                // TODO Auto-generated method stub

            }
        });
		table.setComponentPopupMenu(popMenu);
		toolBar.add(btnDelete);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(1, 24, 753, 661);
		scrollPane.setViewportView(table);
		add(scrollPane);
	}

	public void delete(int[] rows, ArrayList list) {
		try {
			for (int i = 0; i < rows.length; ++i) {
				list.remove(rows[i] - i);
			}
			table.repaint();
			table.clearSelection();
		} catch (IndexOutOfBoundsException e) {
			
		}
	}
	
	
	private void initListeners() {

		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteAction();
			}
		});
		deleteItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteAction();
			}
		});
		insertItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addAction();
			}
		});
		modifyItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editAction();
			}
		});
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					//System.out.println(textField.getText());
					textFieldSearchAction();
				}
			}
		});
		textField.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent arg0) {
				textFieldSearchAction();
			}
		});
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addAction();
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editAction();
			}
		});
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DELETE) {
					deleteAction();
				}
			}
		});
		
		btnAdd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					addAction();
				}
			}
		});
		
		btnEdit.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					editAction();
				}
			}
		});
		
		btnDelete.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					deleteAction();
				}
			}
		});
	}
	
	public abstract void deleteAction();
	public abstract void textFieldSearchAction();
	public abstract void addAction();
	public abstract void editAction();

}
