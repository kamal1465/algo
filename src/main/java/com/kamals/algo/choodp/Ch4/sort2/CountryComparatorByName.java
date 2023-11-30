package com.kamals.algo.choodp.Ch4.sort2;

import java.util.Comparator;

public class CountryComparatorByName implements Comparator<Country>
{
   public int compare(Country country1, Country country2)
   {
      return country1.getName().compareTo(country2.getName());
   }

}
