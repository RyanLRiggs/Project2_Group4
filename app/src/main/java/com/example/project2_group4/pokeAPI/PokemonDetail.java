package com.example.project2_group4.pokeAPI;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PokemonDetail {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("types")
    private List<TypeSlot> types;

    @SerializedName("abilities")
    private List<AbilitySlot> abilities;

    @SerializedName("stats")
    private List<StatSlot> stats;

    @SerializedName("moves")
    private List<MoveSlot> moves;

    @SerializedName("sprites")
    private Sprites sprites;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public List<TypeSlot> getTypes() {
        return types;
    }
    public List<AbilitySlot> getAbilities() {
        return abilities;
    }
    public List<StatSlot> getStats() {
        return stats;
    }
    public List<MoveSlot> getMoves() {
        return moves;
    }
    public Sprites getSprites() {
        return sprites;
    }

    // Inner classes for nested JSON
    public static class TypeSlot {
        @SerializedName("type")
        private Type type;

        public Type getType() {
            return type;
        }

        public static class Type {
            @SerializedName("name")
            private String name;

            public String getName() {
                return name;
            }
        }
    }

    public static class AbilitySlot {
        @SerializedName("ability")
        private Ability ability;

        public Ability getAbility() {
            return ability;
        }

        public static class Ability {
            @SerializedName("name")
            private String name;

            public String getName() {
                return name;
            }
        }
    }

    public static class StatSlot {
        @SerializedName("base_stat")
        private int baseStat;

        @SerializedName("stat")
        private Stat stat;

        public int getBaseStat() {
            return baseStat;
        }
        public Stat getStat() {
            return stat;
        }

        public static class Stat {
            @SerializedName("name")
            private String name;

            public String getName() {
                return name;
            }
        }
    }

    public static class MoveSlot {
        @SerializedName("move")
        private Move move;

        public Move getMove() {
            return move;
        }

        public static class Move {
            @SerializedName("name")
            private String name;

            public String getName() {
                return name;
            }
        }
    }

    public static class Sprites {
        @SerializedName("front_default")
        private String frontDefault;

        public String getFrontDefault() {
            return frontDefault;
        }
    }
}
