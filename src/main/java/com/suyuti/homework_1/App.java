/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.suyuti.homework_1;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author mehmet.dindar
 */
public class App {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("homework1-pu");
        EntityManager em = emf.createEntityManager();
        Foo1(em);
        Foo2(em);
        Foo3(em);
        Foo4(em);
        Foo5(em);
        Foo6(em);
        Foo7(em);
        Foo8(em);
        Foo9(em);
        Foo10(em);
        Foo11(em);
    }

    // Tüm City nesnelerini listeleyen sorguyu yazınız
    public static void Foo1(EntityManager em) {
        List<City> result1 = em.createQuery("SELECT c FROM City c", City.class).getResultList();
        System.out.println("Foo1");
        System.out.println(result1);
    }

    // Tüm Country nesnelerinin sayısını veren sorguyu yazınız.
    public static void Foo2(EntityManager em) {
        Long result = em.createQuery("SELECT COUNT(c) FROM Country c", Long.class).getSingleResult();
        System.out.println("Foo2");
        System.out.println(result);
    }

    // Tüm Country nesnelerinden en yüksek popülasyon değerini bulunuz.
    public static void Foo3(EntityManager em) {
        Integer result = em.createQuery("SELECT max(c.population) FROM Country c", Integer.class).getSingleResult();
        System.out.println("Foo3");
        System.out.println(result);
    }

    // Tüm Country nesnelerinden en yüksek popülasyona sahip Country nesnesini bulunuz
    public static void Foo4(EntityManager em) {
        Country result = em.createQuery("SELECT c FROM Country c WHERE c.population = (SELECT max(c.population) FROM Country c)", Country.class).getSingleResult();
        System.out.println("Foo4");
        System.out.println(result.getName() + " " + result.getPopulation());
    }

    // GNPOId bilgisi null olan Country nesnelerini bulunuz
    public static void Foo5(EntityManager em) {
        List<Country> result = em.createQuery("SELECT c FROM Country c WHERE c.gNPOld = NULL", Country.class).getResultList();
        System.out.println("Foo5");
        System.out.println(result);
    }

    // Ortalama yaşam ömrü (LifeExpectancy) 50 senenin altında olan ülkeleri listeleyiniz.
    public static void Foo6(EntityManager em) {
        List<Country> result = em.createQuery("SELECT c FROM Country c WHERE c.lifeExpectancy < 50", Country.class).getResultList();
        System.out.println("Foo6");
        System.out.println(result);
    }

    // GNPOId bilgisi null ve Continent bilgisi Asia olan Country nesnelerin, ortalama yaşam ömrünü (LifeExpectancy) bulunuz. 
    public static void Foo7(EntityManager em) {
        List<Country> result = em.createQuery("SELECT avg(c.lifeExpectancy) FROM Country c WHERE c.gNPOld = null AND c.continent = 'ASIA'", Country.class).getResultList();
        System.out.println("Foo7");
        System.out.println(result);
    }
    
    // Popülasyonu 199000 ile 220000 arasında olan şehirlerin Name bilgilerini listeleyiniz.
    public static void Foo8(EntityManager em) {
        List<String> result = em.createQuery("SELECT c.name from City c WHERE c.population > 199000 AND c.population < 220000", String.class).getResultList();
        System.out.println("Foo8");
        System.out.println(result);
    }

    // CountryLanguage nesnelerinden CountryCode ve Language bilgilerini listeleyen sorguyu yazınız.    
    public static void Foo9(EntityManager em) {
        List<String> result = em.createQuery("SELECT c.countrylanguagePK.countryCode from Countrylanguage c", String.class).getResultList();
        System.out.println("Foo9");
        System.out.println(result);
    }

    //CountryCode bilgisine göre tüm City entity nesnelerini gruplayarak listeleyiniz.
    public static void Foo10(EntityManager em) {
        List<City> result = em.createQuery("SELECT c from City c GROUP BY c.countryCode", City.class).getResultList();
        System.out.println("Foo10");
        System.out.println(result);
    }
    
    //Country ve City entity nesnelerini INNER JOIN anahtar kelimesiyle, aralarındaki ilişkiye göre birleştirerek listeleyiniz.
    public static void Foo11(EntityManager em) {
        List<Object[]> result = em.createQuery("SELECT c, country.name from City c INNER JOIN c.countryCode country").getResultList();
        System.out.println("Foo11");
        for (Object[] oo:result) {
            System.out.print(oo[0] + " - " + oo[1] + ", ");
        }
    }
}
