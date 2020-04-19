package com.myPocket.myPocket.controller.configuration;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.annotation.PostConstruct;

@Configuration
@ComponentScan(basePackages = "com.myPocket.myPocket")
@EntityScan(basePackages = "com.myPocket.myPocket")
@EnableWebMvc
public class MainConfig {

    @Bean
    public Gson gson() {
      return new Gson();
    }

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostConstruct
    public void init() throws Exception {
//        User user = new User("ernest", bCryptPasswordEncoder().encode("123456789"));
//        user.getWallet().addAccount("pko");
//        user.getWallet().addAccount("nest");
//        user.getWallet().addExpenseCategory("dom");
//        user.getWallet().addExpenseCategory("samochod");
//        user.getWallet().addExpenseCategory("jedzenie");
//        user.getWallet().addExpenseCategory("kino");
//        user.getWallet().addRevenueCategory("praca");
//        user.getWallet().addRevenueCategory("biznes");
//        Account pko = user.getWallet().getAccountList().get(0);
//        Account nest = user.getWallet().getAccountList().get(1);
//        ExpenseCategory dom = user.getWallet().getExpenceList().get(0);
//        ExpenseCategory samochod = user.getWallet().getExpenceList().get(1);
//        ExpenseCategory jedzenie = user.getWallet().getExpenceList().get(2);
//        ExpenseCategory kino = user.getWallet().getExpenceList().get(3);
//        pko.addExpense(new Expense(dom, pko, 650));
//        pko.addExpense(new Expense(dom, pko, 700));
//        pko.addExpense(new Expense(dom, pko, 842));
//        nest.addExpense(new Expense(dom, nest, 999));
//        nest.addExpense(new Expense(dom, nest, 987));
//        nest.addExpense(new Expense(dom, nest, 123));
//        pko.addExpense(new Expense(jedzenie, pko, 842.44));
//        pko.addExpense(new Expense(kino, pko, 933.08));
//        pko.addExpense(new Expense(samochod, pko, 5842));
//        pko.addExpense(new Expense(jedzenie, pko, 1842));
//
//        Map<ExpenseCategory, Double> expencesMap = user.getWallet().getExpencesMap();
//
//        Data data = new Data();
//        Datasets datasets = new Datasets();
//
//        Collection<Double> values = expencesMap.values();
//        Double[] doubles = values.toArray(new Double[values.size()]);
//        double[] doublePrimitives = ArrayUtils.toPrimitive(doubles);
//        datasets.setData(doublePrimitives);
//
//        List<String> colorsList = expencesMap.keySet().stream().map(e -> e.getColor()).collect(Collectors.toList());
//        String[] colors = colorsList.toArray(new String[colorsList.size()]);
//        datasets.setBackgroundColor(colors);
//
//        data.setDatasets(datasets);
//
//        List<String> labelsList = expencesMap.keySet().stream().map(e -> e.getCategoryName()).collect(Collectors.toList());
//        String[] labels = labelsList.toArray(new String[labelsList.size()]);
//        data.setLabels(labels);
//
//        System.out.println(doublePrimitives.toString());
//        System.out.println(colorsList);
//        System.out.println(labelsList);
    }

}
