package com.example.phases_of_democracy;

public class User {
    public String username;
    public String useremail;
    public String password;
    public float revenue;
    public float popularity;
    public int farmer_sub;
    public int minimum_wage;
    public int stimulus_spending;
    public int env_spending;
    public int carbon_tax;
    public int prim_edu;
    public int sec_edu;
    public int hig_edu;
    public int nat_curr;
    public int res_spe;
    public int def_spe;
    public int tar_imp;
    public int bor_cont;
    public int heal_spe;
    public int vacci;
    public int birth_con;
    public int infra;
    public int toll_load;
    public int law_enf;
    public int dis_man;
    public int low_income;
    public int middle_income;
    public int high_income;
    public int sal_tax;
    public int bus_tax;
    public int corporate_tax;
    public int cap_gain;
    public int inh_tex;
    public int prop_tax;
    public int unemp_benefits;
    public int pension;
    public int event_no;

    public User(){

    }

    public User(String username, String useremail, String password, int farmer_sub, int minimum_wage, int stimulus_spending, int env_spending, int carbon_tax, int prim_edu, int sec_edu, int hig_edu, int nat_curr, int res_spe, int def_spe, int tar_imp, int bor_cont, int heal_spe, int vacci, int birth_con, int infra, int toll_load, int law_enf, int dis_man, int low_income, int middle_income, int high_income, int sal_tax, int bus_tax,int corporate_tax, int cap_gain, int inh_tex, int prop_tax, int unemp_benefits, int pension,float revenue,float popularity,int event_no) {
        this.username = username;
        this.useremail = useremail;
        this.password = password;
        this.farmer_sub = farmer_sub;
        this.minimum_wage = minimum_wage;
        this.stimulus_spending = stimulus_spending;
        this.env_spending = env_spending;
        this.carbon_tax = carbon_tax;
        this.prim_edu = prim_edu;
        this.sec_edu = sec_edu;
        this.hig_edu = hig_edu;
        this.nat_curr = nat_curr;
        this.res_spe = res_spe;
        this.def_spe = def_spe;
        this.tar_imp = tar_imp;
        this.bor_cont = bor_cont;
        this.heal_spe = heal_spe;
        this.vacci = vacci;
        this.birth_con = birth_con;
        this.infra = infra;
        this.toll_load = toll_load;
        this.law_enf = law_enf;
        this.dis_man = dis_man;
        this.low_income = low_income;
        this.middle_income = middle_income;
        this.high_income = high_income;
        this.sal_tax = sal_tax;
        this.bus_tax = bus_tax;
        this.corporate_tax=corporate_tax;
        this.cap_gain = cap_gain;
        this.inh_tex = inh_tex;
        this.prop_tax = prop_tax;
        this.unemp_benefits = unemp_benefits;
        this.pension = pension;
        this.revenue=revenue;
        this.popularity=popularity;
        this.event_no=event_no;
    }
}
