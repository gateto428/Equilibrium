package co.com.equilibrium.r2dbc.plan.data;

import co.com.equilibrium.model.plan.Plan;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlanMapper {
    Plan toEntity(PlanData planData);
    PlanData toData(Plan plan);
}
