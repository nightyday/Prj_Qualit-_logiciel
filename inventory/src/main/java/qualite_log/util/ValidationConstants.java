package qualite_log.util;

public class ValidationConstants {
    public static final String EMAIL_REGEX = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$";
    public static final String USERNAME_REGEX = "^[a-zA-Z\\u00C0-\\u00FF]{1,30}$";
    public static final String REF_REGEX = "^[A-Z0-9]{3,}$";
    public static final String VERSION_REGEX = "^[a-zA-Z0-9.]{3,15}$";
    public static final String TOOL_NAME_REGEX = "^[a-zA-Z0-9]{1,30}$";
    

    // Autres regex peuvent être ajoutées ici
}
