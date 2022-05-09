package com.kamals.algo.designpatterns;

public class DecoratorTest
{
    public static void main(String[] args)
    {
        Person person = new Person("Kamal");

        Person decoratedPerson = new DecoratedPerson("Dr.", person);

        Person decoratedPerson2 = new DecoratedPerson("Major", decoratedPerson);

        System.out.println(decoratedPerson.name);
        System.out.println(person.name);
        System.out.println(decoratedPerson2.name);

        System.out.println(decoratedPerson);
        System.out.println(person);
        System.out.println(decoratedPerson2);
    }


    static class Person
    {
        private String name;

        Person(String name)
        {
            this.name = name;
        }

        public String getName()
        {
            return name;
        }

        @Override
        public String toString()
        {
            return getName();
        }
    }

    static class DecoratedPerson extends Person
    {
        private String salutation;
        private Person person;

        DecoratedPerson(String salutation, Person person)
        {
            super(person.name);
            this.person = person;
            this.salutation = salutation;
        }

        @Override
        public String getName()
        {
            return salutation + " " + person.getName();
        }
    }
}
