package com.carpentersblocksreborn.block;

import net.minecraft.block.WoodButtonBlock;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

public class ButtonCarpentersBlock extends WoodButtonBlock implements ButtonModel {
    public ButtonCarpentersBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isArmed() {
        return false;
    }

    @Override
    public boolean isSelected() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public boolean isPressed() {
        return false;
    }

    @Override
    public boolean isRollover() {
        return false;
    }

    @Override
    public void setArmed(boolean b) {

    }

    @Override
    public void setSelected(boolean b) {

    }

    @Override
    public void setEnabled(boolean b) {

    }

    @Override
    public void setPressed(boolean b) {

    }

    @Override
    public void setRollover(boolean b) {

    }

    @Override
    public void setMnemonic(int key) {

    }

    @Override
    public int getMnemonic() {
        return 0;
    }

    @Override
    public void setActionCommand(String s) {

    }

    @Override
    public String getActionCommand() {
        return null;
    }

    @Override
    public void setGroup(ButtonGroup group) {

    }

    @Override
    public void addActionListener(ActionListener l) {

    }

    @Override
    public void removeActionListener(ActionListener l) {

    }

    @Override
    public Object[] getSelectedObjects() {
        return new Object[0];
    }

    @Override
    public void addItemListener(ItemListener l) {

    }

    @Override
    public void removeItemListener(ItemListener l) {

    }

    @Override
    public void addChangeListener(ChangeListener l) {

    }

    @Override
    public void removeChangeListener(ChangeListener l) {

    }
}
