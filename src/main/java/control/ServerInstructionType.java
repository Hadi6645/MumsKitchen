package control;

public enum ServerInstructionType {
	CHECK_EMPLOYEE_EXISTS,
	CHECK_EMPLOYEE_IS_DIETITION,
	GET_RESTAURANTS_LIST,
	GET_MENU,
	CHECK_UNRESERVED_TABLES,
	GET_DINING_SPACE,
	ADD_NEW_FOOD_ORDER,
	CANCEL_FOOD_ORDER,
	CANCEL_RESERVATION,
	ADD_COMPLAINT,
	GET_ACTIVE_COMPLAINTS,
	UPDATE_COMPLAINT,
	UPDATE_TAV_SAGOL,
	ADD_NEW_UPDATE_MENU_REQUEST,
	UPDATE_UPDATE_MENU_REQUEST,
	LOG_OUT,
	GET_DENIED_CUSTOMERS_REPORT,
	GET_RESERVATION_POSSIBILITIES,
	GET_ALL_DENIED_CUSTOMERS_REPORTS,
	GET_FOOD_ORDERS_REPORT,
	GET_ALL_FOOD_ORDERS_REPORT,
	GET_COMPLAINTS_REPORT,
	GET_ALL_COMPLAINTS_REPORT,
	GET_RESTAURANT_REPORTS,
	GET_ALL_RESTAURANTS_REPORTS,
	GET_RESTAURANT_BY_EMPLOYEE,
	MAKE_RESERVATION
}