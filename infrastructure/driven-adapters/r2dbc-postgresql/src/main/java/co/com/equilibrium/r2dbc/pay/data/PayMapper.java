package co.com.equilibrium.r2dbc.pay.data;

import co.com.equilibrium.model.pay.Pay;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PayMapper {
    Pay toEntity(PayData payData);
    PayData toData(Pay pay);
}
