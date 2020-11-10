package com.chanjet.changsha.bank.pay.command.builder;

import com.chanjet.changsha.bank.pay.command.CsBankCommand;
import com.chanjet.changsha.bank.pay.exception.CreateCommandFailtrueException;

import java.lang.reflect.InvocationTargetException;

/**
 * @author: zsc
 * @create: 2020/11/5 1:53 下午
 **/
public class CsBankCommandBuilder {

    private SpiBuilder spiBuilder;

    public CsBankCommandBuilder(SpiBuilder spiBuilder) {
        this.spiBuilder = spiBuilder;
    }

    public <T extends CsBankCommand<?>> T create(Class<T> clazz) {
        try {
            return clazz.getConstructor(this.spiBuilder.getClass()).newInstance(this.spiBuilder);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | InstantiationException var4) {
            throw new CreateCommandFailtrueException(clazz.getName() + " crate failtrue ", var4);
        }
    }
}
