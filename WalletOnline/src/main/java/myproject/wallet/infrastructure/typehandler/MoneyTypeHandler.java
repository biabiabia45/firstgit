package myproject.wallet.infrastructure.typehandler;

import myproject.wallet.domain.valueobject.Money;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MoneyTypeHandler extends BaseTypeHandler<Money> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Money parameter, JdbcType jdbcType) throws SQLException {
        // 将 Money 对象转换为数据库支持的格式
        ps.setBigDecimal(i, parameter.getAmount()); // 假设 Money 存储为 BigDecimal
    }

    @Override
    public Money getNullableResult(ResultSet rs, String columnName) throws SQLException {
        // 从 ResultSet 中读取数据库值并转换为 Money 对象
        BigDecimal amount = rs.getBigDecimal(columnName);
        return new Money(amount); // 假设 Money 有一个接受 BigDecimal 的构造函数
    }

    @Override
    public Money getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        BigDecimal amount = rs.getBigDecimal(columnIndex);
        return new Money(amount);
    }

    @Override
    public Money getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        BigDecimal amount = cs.getBigDecimal(columnIndex);
        return new Money(amount);
    }
}
