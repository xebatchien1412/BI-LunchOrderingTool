package bi.lunch.constant;

public class UserCRUDConstants {
	public static final Object[] REGISTERED_SUCCESSFULLY = { 1, "Registered Successfully", 1 };
	public static final Object[] PASSWORD_DOES_NOT_MATCH = { 2, "Your password does not match", 0 };
	public static final Object[] USER_NOT_FOUND = { 4, "User not found", 0 };
	public static final Object[] USER_UPDATED_SUCCESSFULLY = { 5, "User updated successfully", 1 };
	public static final Object[] USER_UPDATED_FAIL = { 6, "User updated fail", 0 };
	public static final Object[] USER_DELETED_SUCCESSFULLY = { 7, "User deleted successfully", 1 };
	public static final Object[] USER_DELETED_FAIL = { 8, "User deleted fail", 0 };
	public static final Object[] ERROR_HAPPENED = { 10, "There was a problem occured, please contact administrator",
			-1 };
}
