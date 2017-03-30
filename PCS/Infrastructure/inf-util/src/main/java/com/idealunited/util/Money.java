/**
 * Created on 2006-07-25
 */
package com.idealunited.util;

import java.util.Currency;

public final class Money implements Comparable {

	/**
	 * 金额的数量.
	 */
	private Long amount;
	/**
	 * 金额的货币类型.
	 */
	private Currency currency;

	/**
	 * Magic number.
	 */
	private final int magic = 32;

	/**
	 * 构造一个Money对象.
	 * 
	 * @param moneyAmount
	 *            金额数量,精确到厘
	 * @param moneyCurrency
	 *            金额的货币类型,必须确保参数moneyCurrency不为空.
	 * @throw NullPointerException 如果参数moneyCurrency为空，则抛出空异常. Example: //一元钱:1
	 *        * 100 * 10 Money money = new Money(1000,
	 *        Currency.getInstance("CNY"));
	 */
	public Money(final long moneyAmount, final Currency moneyCurrency) {
		if (null == moneyCurrency) {
			throw new NullPointerException("The currency must not be null.");
		}
		this.amount = moneyAmount;
		this.currency = moneyCurrency;
	}

	/**
	 * 获得人民币的对象.
	 * 
	 * @param amount
	 *            金额数量,精确到厘
	 * @return Money object <br>
	 *         返回的Money对象的货币类型为CNY,人民币. Example: //一元钱:1 * 100 * 10 Money money
	 *         = Money.rmb(1000);
	 */
	public static Money rmb(final Long amount) {
		return new Money(amount, Currency.getInstance("CNY"));
	}

	/**
	 * 获得金额的数量,精确到厘.
	 * 
	 * @return long Example: //一元钱:1 * 100 * 10 Money money = Money.rmb(1000);
	 *         long amount = money.getAmount();
	 */
	public Long getAmount() {
		return this.amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	/**
	 * 得到金额的货币类型. 人民币为"CNY"
	 * 
	 * @return Currency object Example: //一元钱:1 * 100 * 10 Money money =
	 *         Money.rmb(1000); String currencCode =
	 *         money.getCurrency().getCurrencyCode();
	 */
	public Currency getCurrency() {
		return this.currency;
	}

	/**
	 * 与this货币对象相加.加后的结果不改变相加的两个Money对象.
	 * 
	 * @param money
	 *            Money object
	 * @return Money object <br>
	 *         如果两个money对象的货币类型不相同，则返回null;
	 * @throw NullPointerException 如果参数money为空，则抛出空异常 <br>
	 *        Example: <br>
	 *        Money money1 = Money.rmb(1000); <br>
	 *        Money money2 = Money.rmb(1000); <br>
	 *        Money result = money1.add(money2);
	 */
	public Money add(final Money money) {
		if (null == money) {
			throw new NullPointerException("The parameter must not be null.");
		}

		if (!this.getCurrency().equals(money.getCurrency())) {
			return null;
		}

		long total = this.getAmount();
		total += money.getAmount();
		Money result = new Money(total, this.getCurrency());

		return result;
	}

	/**
	 * 与this货币对象相减.减后的结果不改变相减的两个Money对象.
	 * 
	 * @param money
	 *            Money object
	 * @return Money object <br>
	 *         如果参数money为空，则返回this; <br>
	 *         如果两个money对象的货币类型不相同，则返回null;
	 * @throw NullPointerException 如果参数money为空，则抛出空异常 Example: Money money1 =
	 *        Money.rmb(1000); Money money2 = Money.rmb(1000); Money result =
	 *        money1.subtract(money2);
	 */
	public Money subtract(final Money money) {
		if (null == money) {
			throw new NullPointerException("The parameter must not be null.");
		}

		if (!this.getCurrency().equals(money.getCurrency())) {
			return null;
		}

		long total = this.getAmount();
		total -= money.getAmount();
		Money result = new Money(total, this.getCurrency());

		return result;
	}

	/**
	 * 判断两个Money对象的货币类型是否相同.
	 * 
	 * @param money
	 *            Money object
	 * @return boolean
	 * @throw NullPointerException 如果参数money为空，则抛空异常. <br>
	 *        Example: <br>
	 *        Money money1 = Money.rmb(1000); <br>
	 *        Money money2 = new Money(2000, Currency.getInstance("CNY")); <br>
	 *        if (money1.assertSameCurrencyAs(money2)) { <br>
	 */
	public boolean assertSameCurrencyAs(final Money money) {
		if (null == money) {
			throw new NullPointerException("The parameter must not be null.");
		}

		if (this.getCurrency().equals(money.getCurrency())) {
			return true;
		}

		return false;
	}

	/**
	 * 将金额的数量转换为符号相反的数值.转换后不影响已有的Money对象.
	 * 
	 * @return Money 转换后得到的Money对象的货币类型与转换前Money对象的货币类型相同 Example: Money money =
	 *         Money.rmb(1000); Money result = money.negate();
	 */
	public Money negate() {
		long total = -1 * this.getAmount();
		Money money = new Money(total, this.getCurrency());

		return money;
	}

	/**
	 * 将金额的数量乘以fact后得到新的Money对象．相乘后的结果不影响已有的Money对象.
	 * 
	 * @param factor
	 *            因数
	 * @return Money <br>
	 *         返回的Money对象拥有与原相乘前的Money对象相同的货币类型.乘后的结果采用四舍五入 规则. <br>
	 *         Example: <br>
	 *         Money money1 = Money.rmb(1000); <br>
	 *         double factor = 2.0; <br>
	 *         Money result = money1.multiply(factor);
	 */
	public Money multiply(final double factor) {
		double total = this.getAmount() * factor;
		long result = Math.round(total);
		Money money = new Money(result, this.getCurrency());

		return money;
	}

	/**
	 * 与this对象相除, 返回一个除法运算后货币的集合,该数组有两个Money元素, 该数组各元素之和是原始值.
	 * 
	 * @param denominator
	 *            被除数
	 * @return Money[] <br>
	 *         返回的Money对象数组都拥有与原Money对象相同的货币类型.
	 * @throw IllegalArgumentException 如果参数denominator为0，则抛出参数异常. <br>
	 *        Example: <br>
	 *        Money money1 = Money.rmb(1000); <br>
	 *        int denominator = 2; <br>
	 *        Money[] result = money1.divide(denominator);
	 */
	public Money[] divide(final int denominator) {
		if (0 == denominator) {
			throw new IllegalArgumentException("Parameter must not be zero.");
		}
		long quotient = this.getAmount() / denominator;
		long remainder = this.getAmount() % denominator;
		Currency curr = this.getCurrency();
		Money[] result = new Money[2];
		result[0] = new Money(quotient * denominator, curr);
		result[1] = new Money(remainder, curr);

		return result;
	}

	/**
	 * 判断this对象是否大于money.
	 * 
	 * @param money
	 *            Money object
	 * @return boolean <br>
	 *         如果金额的货币类型不相同，则返回false;
	 * @throw NullPointerException 如果参数money为空，则抛出空异常. <br>
	 *        Example: <br>
	 *        Money money1 = Money.rmb(5030); <br>
	 *        Money money2 = Money.rmb(5020); <br>
	 *        if (money1.greaterThan(money2)) { <br>
	 */
	public boolean greaterThan(final Money money) {
		if (null == money) {
			throw new NullPointerException("The parameter must not be null.");
		}

		if (!this.getCurrency().equals(money.getCurrency())) {
			return false;
		}

		if (this.getAmount() > money.getAmount()) {
			return true;
		}

		return false;
	}

	/**
	 * 判断this对象是否小于money.
	 * 
	 * @param money
	 *            Money object
	 * @return boolean
	 * @throw NullPointerException 如果参数money为空，则抛出空异常. <br>
	 *        如果金额的货币类型不相同，则返回false; <br>
	 *        Example: <br>
	 *        Money money1 = Money.rmb(5010); <br>
	 *        Money money2 = Money.rmb(5020); <br>
	 *        if (money1.lessThan(money2)) { <br>
	 */
	public boolean lessThan(final Money money) {
		if (null == money) {
			throw new NullPointerException("The parameter must not be null.");
		}

		if (null == money) {
			return false;
		}

		if (!this.getCurrency().equals(money.getCurrency())) {
			return false;
		}

		if (this.getAmount() < money.getAmount()) {
			return true;
		}

		return false;
	}

	/**
	 * 判断两个Money对象是否值相同.
	 * 
	 * @param money
	 *            Money object
	 * @return boolean 如果参数money为空，则返回false; <br>
	 *         如果两个money对象的货币类型不相同，则返回false; <br>
	 *         只有在货币类型相同，且金额数量也相同的情况下才返回true. <br>
	 *         Example: <br>
	 *         Money money1 = new Money(1000, Currency.getInstance("CNY")); <br>
	 *         Money money2 = new Money(1000, Currency.getInstance("CNY")); <br>
	 *         if (money1.equals(money2)) { <br>
	 */
	public boolean equals(final Money money) {
		if (null == money) {
			return false;
		}

		if (getAmount() != money.getAmount()) {
			return false;
		}

		if (!getCurrency().equals(money.getCurrency())) {
			return false;
		}

		return true;
	}

	/**
	 * Returns a hash code value for this object.
	 * 
	 * @return int <br>
	 *         Example: <br>
	 *         Money money = Money.rmb(1000); <br>
	 *         int hashCode = money.hashCode();
	 */
	public int hashCode() {
		long temp = this.getAmount();
		return (int) (temp ^ (temp >>> magic));
	}

	/**
	 * a string representation of this Money object.
	 * 
	 * @return String <br>
	 *         Example: <br>
	 *         Money money = Money.rmb(1000); <br>
	 *         System.out.println(money.toString());
	 */
	public String toString() {
		return "Money:" + hashCode() + ",amount:" + getAmount() + ",currency:"
				+ getCurrency().getCurrencyCode();
	}

	/**
	 * 与this对象相比.
	 * 
	 * @param money
	 *            Money
	 * @return int
	 * @throw NullPointerException 如果参数money为空，则抛出空异常; <br>
	 *        如果两个money对象的货币类型不相同，则返回-1; <br>
	 *        如果this对象的金额数量大于money的金额数量，则返回1 <br>
	 *        如果this对象的金额数量小于money的金额数量，则返回-1 <br>
	 *        如果this对象的金额数量等于money的金额数量，则返回0
	 */
	public int compareTo(final Money money) {
		if (null == money) {
			throw new NullPointerException("The parameter must not be null.");
		}

		if (!this.getCurrency().equals(money.getCurrency())) {
			return -1;
		}

		if (this.getAmount() > money.getAmount()) {
			return 1;
		} else if (this.getAmount() < money.getAmount()) {
			return -1;
		} else {
			return 0;
		}
	}

	/**
	 * 与this对象相比.
	 * 
	 * @param o
	 *            any Object
	 * @return int
	 * @see java.lang.Comparable#compareTo(T).
	 * @throw ClassCastException if the object o is not a Money object.
	 */
	public int compareTo(final Object o) {
		if (!(o instanceof Money)) {
			String msg = "Can not case Object o into Money object.";
			throw new ClassCastException(msg);
		}

		return compareTo((Money) o);
	}

	/**
	 * Format money to a readable string.
	 * 
	 * @return Strinmg
	 */
	public String format() {
		String sign = getAmount() >= 0 ? "" : "-";
		long absAmount = Math.abs(this.getAmount());
		absAmount = absAmount % 10 >= 5 ? (absAmount / 10 + 1) * 10 : absAmount;
		String strAmount = String.valueOf(absAmount);
		if (absAmount < 10) {
			strAmount = "000" + strAmount;
		} else if (absAmount < 100) {
			strAmount = "00" + strAmount;
		} else if (absAmount < 1000) {
			strAmount = "0" + strAmount;
		}
		int len = strAmount.length();
		return sign + strAmount.substring(0, len - 3) + "."
				+ strAmount.substring(len - 3, len - 1);
	}

	public static void main(String[] args) {
		System.out.println(Money.rmb(-990L).format());
	}
}