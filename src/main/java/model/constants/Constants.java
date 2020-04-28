package model.constants;

public final class Constants {

    public static String ADDRESS = "{adress}";

    public static String GET_USERS_BY_TOPIC = ADDRESS+"usersByTopic?topic=%s";

    public static String UPDATE_USER = ADDRESS+"user";

    public static String UPDATE_CHAT = ADDRESS+"chat";

    public static String GET_CHAT_BY_ID = ADDRESS+"chatById?id=%d";

    public static String GET_USER_BY_ID = ADDRESS+"userById?id=%d";

    public static String GET_USER_BY_NICKNAME = ADDRESS+"userByNickname?nickname=%s";

    public static String GET_ALL_CHATS = ADDRESS+"chats";
}
