package com.youngzy.book.concurrency.ch03;

import java.util.*;

/**
 * 3-9 基本类型 和 引用类型 变量的 线程封闭性
 */
public class Animals {
    Ark ark;
    Species species;
    Gender gender;

    public int loadTheArk(Collection<Animal> candidates) {
        SortedSet<Animal> animals;
        int numPairs = 0;
        Animal candidate = null;

        // animals 被封闭在方法中，不要是他们逸出
        animals = new TreeSet<>(new SpeciesGenderComparator());
        animals.addAll(candidates);

        for (Animal a : animals) {
            if (candidate == null || ! candidate.isPotentialMate(a)) {
                candidate = a;
            } else {
                ark.load(new AnimalPair(candidate, a));
                ++numPairs;
                candidate = null;
            }
        }

        return numPairs;
    }

    class SpeciesGenderComparator implements Comparator<Animal> {

        @Override
        public int compare(Animal o1, Animal o2) {
            int speciesCompare = o1.species.compareTo(o2.species);
            return speciesCompare != 0 ? speciesCompare :
                    o1.gender.compareTo(o2.gender);
        }
    }

    enum Gender {
        MALE, FEMALE
    }

    enum Species {
        AARDVARK, BENGAL_TIGER, CARIBOU, DINGO, ELEPHANT, FROG, GNU, HYENA,
        IGUANA, JAGUAR, KIWI, LEOPARD, MASTADON, NEWT, OCTOPUS,
        PIRANHA, QUETZAL, RHINOCEROS, SALAMANDER, THREE_TOED_SLOTH,
        UNICORN, VIPER, WEREWOLF, XANTHUS_HUMMINBIRD, YAK, ZEBRA
    }

    class Animal {
        Species species;
        Gender gender;

        public boolean isPotentialMate(Animal other) {
            return species == other.species && gender != other.gender;
        }
    }

    class AnimalPair {
        private final Animal one, two;

        public AnimalPair(Animal one, Animal two) {
            this.one = one;
            this.two = two;
        }
    }
    /**
     * 诺亚方舟
     *
     */
    class Ark {
        private final Set<AnimalPair> loadedAnimals = new HashSet<>();

        public void load(AnimalPair pair) {
            loadedAnimals.add(pair);
        }
    }
}
