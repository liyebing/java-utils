import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.ObjectUtils;

import com.google.common.base.Objects;

/**
 * 通用的金额类
 *
 * 不可变类  ThreadSafe
 *
 * @author liyebing created on 15/9/2.
 * @version $Id$
 */
public class Money implements Serializable, Comparable<Money> {

    private static final long serialVersionUID = 8902774465289570464L;

    private static final int DEF_SCALE = 2;

    /** 为何这里不用BigDecimal,因为Mdies不支持BigDecimal的序列化与反序列化，坑！！ */
    private float money;

    private Money() {
        money = BigDecimal.ZERO.floatValue();
    }

    private Money(float money) {
        this.money = money;
    }

    public static Money create() {
        return new Money();
    }

    public static Money create(String amount) {
        return new Money(new BigDecimal(amount).floatValue());
    }

    public static Money create(float amount) {
        return new Money(amount);
    }

    public static Money create(BigDecimal money) {
        return new Money(money.floatValue());
    }

    public static Money create(Money money) {
        return new Money(money.getMoney().floatValue());
    }

    public Money plus(BigDecimal money) {
        float amount = (new BigDecimal(Float.toString(this.money)).add(money)).floatValue();
        return Money.create(amount);
    }

    public Money subtract(BigDecimal money) {
        float amount = (new BigDecimal(Float.toString(this.money)).subtract(money)).floatValue();
        return Money.create(amount);
    }

    public Money plus(Money money) {
        float amount = (new BigDecimal(Float.toString(this.money)).add(money.getMoney())).floatValue();
        return Money.create(amount);
    }

    public Money subtract(Money money) {
        float amount = (new BigDecimal(Float.toString(this.money)).subtract(money.getMoney())).floatValue();
        return Money.create(amount);
    }

    public Money divide(Money money) {
        BigDecimal b2 = money.getMoney();
        float amount= Money.create(Float.toString(getMoney().divide(b2, DEF_SCALE, BigDecimal.ROUND_HALF_UP).floatValue())).getMoney().floatValue();
        return Money.create(amount);
    }

    public Money multi(Money money) {
        float amount = Money.create(Float.toString(this.getMoney().multiply(money.getMoney()).setScale(DEF_SCALE, BigDecimal.ROUND_HALF_UP).floatValue()))
                .getMoney().floatValue();
        return Money.create(amount);
    }

    public BigDecimal getMoney() {
        String amount = Float.toString(money);
        return new BigDecimal(amount);
    }

    public float fetchMoneyFloatValue() {
        return money;
    }

    public boolean same(Money money) {
        return compareTo(money) == 0;
    }

    public boolean lessThan(Money o) {
        return compareTo(o) < 0;
    }

    public boolean moreThan(Money o) {
        return compareTo(o) > 0;
    }

    public int compareTo(Money o) {
        return new BigDecimal(Float.toString(this.money)).compareTo(o.getMoney());
    }

    public static void main(String[] args) {

        Money plus= Money.create("10.5").plus(Money.create("0.02"));
        Money sub= plus.subtract(Money.create("0.5"));
        System.out.println(plus.getMoney());
        System.out.println(sub.getMoney());

    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Money)) {
            return false;
        }

        Money that = (Money) o;
        return this.getMoney().compareTo(that.getMoney()) == 0;

    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getMoney());
    }

    public String toString() {
        return ObjectUtils.toString(getMoney());
    }
}