package game.enums;

/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 */
public enum Status {
    HOSTILE_TO_ENEMY, // use this status to be considered hostile towards enemy (e.g., to be attacked by enemy)
    HOSTILE_TO_PLAYER, // can be attack by player
    TALL, // use this status to tell that current instance has "grown".
    INVINCIBLE,
    POWER,
    HEAL,
    FERTILE,
    INDESTRUCTIBLE,
    UNDROPPABLE,
    NON_REMOVABLE_FROM_INVENTORY,
    CAN_SMASH_KOOPA_SHELL,
    CAN_ENTER_SHELL,
    CAN_ATTACK_WITH_FIRE,
    CAN_ENTER_HIGH_GROUND,
    CAN_DROP_KEY,
    ON_FIRE,
    NOT_FOLLOWING,
    VICTORY,
    AGGRESSIVE, //aggressive behaviour
    RESET,
    BLINKABLE,
    INJURED
}
