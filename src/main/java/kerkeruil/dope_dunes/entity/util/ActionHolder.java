package kerkeruil.dope_dunes.entity.util;

public class ActionHolder<T> {
    private final String name;
    private final Integer attackDuration;
    private final String animation;
    private static Integer active;
    private ActionHolder(String name, Integer attackDuration, String animation){
        this.name = name;
        this.attackDuration = attackDuration;
        this.animation = animation;
        this.active = 0;
    };
}
