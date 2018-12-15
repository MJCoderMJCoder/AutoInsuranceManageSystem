package com.lzf.ui.plugin;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JCheckBox;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;

public class CheckTreeManager extends MouseAdapter implements TreeSelectionListener {

	private CheckTreeSelectionModel selectionModel = null;

	private JTree tree = null;
	int hotspot = new JCheckBox().getPreferredSize().width;

	public CheckTreeManager(JTree tree) {
		this.tree = tree;
		selectionModel = new CheckTreeSelectionModel(tree.getModel());
		CheckTreeCellRenderer checkTreeCellRenderer = new CheckTreeCellRenderer(tree.getCellRenderer(), selectionModel);
		tree.setCellRenderer(checkTreeCellRenderer);
		tree.addMouseListener(this); // 鼠标监听
		selectionModel.addTreeSelectionListener(this); // 树选择监听
	}

	public void mouseClicked(MouseEvent me) {
		TreePath path = tree.getPathForLocation(me.getX(), me.getY());
		if (path == null)
			return;
		if (me.getX() > tree.getPathBounds(path).x + hotspot)
			return;

		boolean selected = selectionModel.isPathSelected(path, true);
		selectionModel.removeTreeSelectionListener(this);

		try {
			// System.out.println(path);
			if (selected)
				selectionModel.removeSelectionPath(path);
			else
				selectionModel.addSelectionPath(path);
		} finally {
			selectionModel.addTreeSelectionListener(this);
			tree.treeDidChange();
		}
	}

	public CheckTreeSelectionModel getSelectionModel() {
		return selectionModel;
	}

	public void valueChanged(TreeSelectionEvent e) {
		tree.treeDidChange();
	}

}