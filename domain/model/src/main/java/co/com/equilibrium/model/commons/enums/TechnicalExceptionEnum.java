package co.com.equilibrium.model.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TechnicalExceptionEnum {
    SECRET_EXCEPTION ("001", "An error ocurred while trying to get AWS secrets"),
    INTERNAL_SERVER_ERROR ("002", "Internal server error"),

    BODY_MISSING_ERROR ("003", "Missing parameters per body"),

    HEADER_MISSING_ERROR ("004", "Missing parameters per header"),
    SAVE_PERSON_ERROR("005", "Error in save person"),

    FIND_PERSON_ERROR("006", "Error in find person"),
    TOKEN_INVALID("007", "Token invalid o expired"),
    PARAM_MISSING_ERROR("008", "Missing params" ),
    ERROR_INACTIVATE_PERSON("009", "Error in inactivate person"),
    SAVE_COURSE_ERROR("010", "Error in save course"),
    FIND_COURSE_ERROR("011", "Error in find Course"),
    UPDATE_COURSE_ERROR("012", "Error in update course"),
    DELETE_COURSE_ERROR("013", "Error in delete course"),
    SAVE_PLAN_ERROR("014", "Error in save plan"),
    FIND_PLAN_ERROR("015", "Error in find Plan"),
    DELETE_PLAN_ERROR("015", "Error in delete Plan"),
    UPDATE_PLAN_ERROR("015", "Error in Update Plan"),
    SAVE_PLAN_COURSE_ERROR("016", "Error in save course in plan"),
    DELETE_PLAN_COURSE_ERROR("017", "Error in delete course in plan"),
    SAVE_PAY_ERROR("018", "Error save pay"),
    SAVE_CLASS_ERROR("019", "Error in save class"),
    UPDATE_CLASS_ERROR("020", "Error in update class"),
    FIND_CLASS_ERROR("021", "Error in get all class"),
    DELETE_CLASS_ERROR("022", "Error in delete class"),
    TRANSACTION_REJECT("023", "Transaction is reject"),
    NO_VACANCIES("024", "There are no vacancies for this class"),
    ERROR_PLAN_FINALIZATED("025", "you cannot take more classes you exceeded the quota");
    private final String code;
    private final String message;
}
