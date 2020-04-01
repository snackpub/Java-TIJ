package com.snackpub.core.io;


import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.math.BigDecimal;


/**
 * 测试模板
 *
 * @author snackpub
 * @date 2020-3-27
 */
public class TestModel implements Externalizable {

    private String name;

    private String sex;

	private BigDecimal money = new BigDecimal("0.0");


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	@Override
	public String toString() {
		return "TestModel{" +
				"name='" + name + '\'' +
				", sex='" + sex + '\'' +
				", money=" + money +
				'}';
	}

	@Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.name);
        out.writeObject(this.sex);
        out.writeObject(this.money);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = (String) in.readObject();
        sex = (String) in.readObject();
		money = (BigDecimal) in.readObject();
    }
}
