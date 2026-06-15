/*
 * mosfet_with_explicit_metal_and_dielectric.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:56 by COMSOL 6.3.0.290. */
public class mosfet_with_explicit_metal_and_dielectric {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Semiconductor_Module\\Transistors");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ec", "ConductiveMedia", "geom1");
    model.component("comp1").physics().create("semi", "Semiconductor", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/ec", true);
    model.study("std1").feature("stat").setSolveFor("/physics/semi", true);

    model.param().set("L", "1e-6[m]");
    model.param().descr("L", "Out-of-plane thickness");
    model.param().set("pdop", "1e17[1/cm^3]");
    model.param().descr("pdop", "p-doping");
    model.param().set("ndop", "1e20[1/cm^3]");
    model.param().descr("ndop", "n-doping");

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new double[]{3, 0.7});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").set("type", "closed");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.67, 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.7, 1, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.5, 2, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.7, 2, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.7, 3, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.7, 3, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 2.3, 4, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.7, 4, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 2.5, 5, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.7, 5, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 3, 6, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.7, 6, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 3, 7, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.67, 7, 1);
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new double[]{0.5, 0.1});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new double[]{0, 0.7});
    model.component("comp1").geom("geom1").feature().duplicate("r3", "r2");
    model.component("comp1").geom("geom1").feature("r3").set("pos", new double[]{2.5, 0.7});
    model.component("comp1").geom("geom1").feature().duplicate("r4", "r3");
    model.component("comp1").geom("geom1").feature("r4").set("size", new String[]{"1.6", "30[nm]"});
    model.component("comp1").geom("geom1").feature("r4").set("pos", new double[]{0.7, 0.7});
    model.component("comp1").geom("geom1").feature().duplicate("r5", "r4");
    model.component("comp1").geom("geom1").feature("r5").set("size", new double[]{3, 0.1});
    model.component("comp1").geom("geom1").feature("r5").set("pos", new double[]{0, -0.1});
    model.component("comp1").geom("geom1").feature("fin").set("repairtoltype", "absolute");
    model.component("comp1").geom("geom1").feature("fin").set("absrepairtol", 3.0E-6);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("mce1", "MeshControlEdges");
    model.component("comp1").geom("geom1").feature("mce1").selection("input").set("fin", 6);
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("AroraMobilityModel", "AroraMobilityModel", "Arora mobility model");
    model.component("comp1").material("mat1").propertyGroup()
         .create("PowerLawMobilityModel", "PowerLawMobilityModel", "Power law mobility model");
    model.component("comp1").material("mat1").propertyGroup().create("Auger", "Auger", "Auger recombination");
    model.component("comp1").material("mat1").propertyGroup().create("Direct", "Direct", "Direct recombination");
    model.component("comp1").material("mat1").propertyGroup()
         .create("SRH", "SRH", "Shockley\u2013Read\u2013Hall recombination");
    model.component("comp1").material("mat1").propertyGroup()
         .create("FletcherMobilityModel", "FletcherMobilityModel", "Fletcher mobility model");
    model.component("comp1").material("mat1").propertyGroup()
         .create("CaugheyThomasMobilityModel", "CaugheyThomasMobilityModel", "Caughey\u2013Thomas mobility model");
    model.component("comp1").material("mat1").propertyGroup()
         .create("SemicondMaterial", "SemicondMaterial", "Semiconductor material");
    model.component("comp1").material("mat1").propertyGroup()
         .create("LombardiSurfaceMobilityModel", "LombardiSurfaceMobilityModel", "Lombardi surface mobility model");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ImpactIonization", "ImpactIonization", "Impact ionization");
    model.component("comp1").material("mat1").propertyGroup()
         .create("SlotboomModel", "SlotboomModel", "Slotboom model");
    model.component("comp1").material("mat1").propertyGroup()
         .create("JainRoulstonModel", "JainRoulstonModel", "Jain\u2013Roulston model");
    model.component("comp1").material("mat1").propertyGroup()
         .create("KlaassenUnifiedMobilityModel", "KlaassenUnifiedMobilityModel", "Klaassen unified mobility model");
    model.component("comp1").material("mat1").label("Si - Silicon");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"11.7", "0", "0", "0", "11.7", "0", "0", "0", "11.7"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"131[W/(m*K)]", "0", "0", "0", "131[W/(m*K)]", "0", "0", "0", "131[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "2329[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "700[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("AroraMobilityModel").label("Arora mobility model");
    model.component("comp1").material("mat1").propertyGroup("AroraMobilityModel")
         .set("mun0_ref_arora", "1252[cm^2/(V*s)]");
    model.component("comp1").material("mat1").propertyGroup("AroraMobilityModel")
         .set("mup0_ref_arora", "407[cm^2/(V*s)]");
    model.component("comp1").material("mat1").propertyGroup("AroraMobilityModel")
         .set("mun_min_ref_arora", "88[cm^2/(V*s)]");
    model.component("comp1").material("mat1").propertyGroup("AroraMobilityModel")
         .set("mup_min_ref_arora", "54.3[cm^2/(V*s)]");
    model.component("comp1").material("mat1").propertyGroup("AroraMobilityModel")
         .set("Nn0_ref_arora", "1.26e17[1/cm^3]");
    model.component("comp1").material("mat1").propertyGroup("AroraMobilityModel")
         .set("Np0_ref_arora", "2.35e17[1/cm^3]");
    model.component("comp1").material("mat1").propertyGroup("AroraMobilityModel").set("alpha0_arora", "0.88");
    model.component("comp1").material("mat1").propertyGroup("AroraMobilityModel").set("beta1_arora", "-0.57");
    model.component("comp1").material("mat1").propertyGroup("AroraMobilityModel").set("beta2_arora", "-2.33");
    model.component("comp1").material("mat1").propertyGroup("AroraMobilityModel").set("beta3_arora", "-2.33");
    model.component("comp1").material("mat1").propertyGroup("AroraMobilityModel").set("beta4_arora", "-0.146");
    model.component("comp1").material("mat1").propertyGroup("AroraMobilityModel").set("Tref_arora", "300[K]");
    model.component("comp1").material("mat1").propertyGroup("PowerLawMobilityModel")
         .label("Power law mobility model");
    model.component("comp1").material("mat1").propertyGroup("PowerLawMobilityModel")
         .set("mun0_pl", "1448[cm^2/(V*s)]");
    model.component("comp1").material("mat1").propertyGroup("PowerLawMobilityModel")
         .set("mup0_pl", "473[cm^2/(V*s)]");
    model.component("comp1").material("mat1").propertyGroup("PowerLawMobilityModel").set("alphan_pl", "2.33");
    model.component("comp1").material("mat1").propertyGroup("PowerLawMobilityModel").set("alphap_pl", "2.23");
    model.component("comp1").material("mat1").propertyGroup("PowerLawMobilityModel").set("Tref_pl", "300[K]");
    model.component("comp1").material("mat1").propertyGroup("Auger").label("Auger recombination");
    model.component("comp1").material("mat1").propertyGroup("Auger").set("Cn", "2.8e-31[cm^6/s]");
    model.component("comp1").material("mat1").propertyGroup("Auger").set("Cp", "9.9e-32[cm^6/s]");
    model.component("comp1").material("mat1").propertyGroup("Direct").label("Direct recombination");
    model.component("comp1").material("mat1").propertyGroup("Direct").set("C", "0[cm^3/s]");
    model.component("comp1").material("mat1").propertyGroup("SRH")
         .label("Shockley\u2013Read\u2013Hall recombination");
    model.component("comp1").material("mat1").propertyGroup("SRH").set("taun", "10[us]");
    model.component("comp1").material("mat1").propertyGroup("SRH").set("taup", "10[us]");
    model.component("comp1").material("mat1").propertyGroup("FletcherMobilityModel")
         .label("Fletcher mobility model");
    model.component("comp1").material("mat1").propertyGroup("FletcherMobilityModel")
         .set("F1_fl", "1.04e21[1/(cm^1*V*s)]");
    model.component("comp1").material("mat1").propertyGroup("FletcherMobilityModel").set("F2_fl", "7.45e13[1/cm^2]");
    model.component("comp1").material("mat1").propertyGroup("FletcherMobilityModel").set("Tref_fl", "300[K]");
    model.component("comp1").material("mat1").propertyGroup("CaugheyThomasMobilityModel")
         .label("Caughey\u2013Thomas mobility model");
    model.component("comp1").material("mat1").propertyGroup("CaugheyThomasMobilityModel").set("alphan0_ct", "1.11");
    model.component("comp1").material("mat1").propertyGroup("CaugheyThomasMobilityModel").set("alphap0_ct", "1.21");
    model.component("comp1").material("mat1").propertyGroup("CaugheyThomasMobilityModel").set("vn0_ct", "1e7[cm/s]");
    model.component("comp1").material("mat1").propertyGroup("CaugheyThomasMobilityModel")
         .set("vp0_ct", "8.37e6[cm/s]");
    model.component("comp1").material("mat1").propertyGroup("CaugheyThomasMobilityModel").set("betan1_ct", "0.66");
    model.component("comp1").material("mat1").propertyGroup("CaugheyThomasMobilityModel").set("betap1_ct", "0.17");
    model.component("comp1").material("mat1").propertyGroup("CaugheyThomasMobilityModel").set("betan2_ct", "-0.87");
    model.component("comp1").material("mat1").propertyGroup("CaugheyThomasMobilityModel").set("betap2_ct", "-0.52");
    model.component("comp1").material("mat1").propertyGroup("CaugheyThomasMobilityModel").set("Tref_ct", "300[K]");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").label("Semiconductor material");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").set("Eg0", "1.12[V]");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").set("chi0", "4.05[V]");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial")
         .set("Nv", "(T/300[K])^(3/2)*1.04e19[1/cm^3]");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial")
         .set("Nc", "(T/300[K])^(3/2)*2.8e19[1/cm^3]");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").set("mun", "1450[cm^2/(V*s)]");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").set("mup", "500[cm^2/(V*s)]");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("LombardiSurfaceMobilityModel")
         .label("Lombardi surface mobility model");
    model.component("comp1").material("mat1").propertyGroup("LombardiSurfaceMobilityModel")
         .set("deltan_ls", "5.82e14[V/s]");
    model.component("comp1").material("mat1").propertyGroup("LombardiSurfaceMobilityModel")
         .set("deltap_ls", "2.05e14[V/s]");
    model.component("comp1").material("mat1").propertyGroup("LombardiSurfaceMobilityModel")
         .set("mun1_ls", "4.75e7[cm^2/(V*s)]");
    model.component("comp1").material("mat1").propertyGroup("LombardiSurfaceMobilityModel")
         .set("mup1_ls", "9.93e7[cm^2/(V*s)]");
    model.component("comp1").material("mat1").propertyGroup("LombardiSurfaceMobilityModel")
         .set("mun2_ls", "1.74e5[cm^2/(V*s)]");
    model.component("comp1").material("mat1").propertyGroup("LombardiSurfaceMobilityModel")
         .set("mup2_ls", "8.84e5[cm^2/(V*s)]");
    model.component("comp1").material("mat1").propertyGroup("LombardiSurfaceMobilityModel")
         .set("alphan_ls", "0.125");
    model.component("comp1").material("mat1").propertyGroup("LombardiSurfaceMobilityModel")
         .set("alphap_ls", "0.0317");
    model.component("comp1").material("mat1").propertyGroup("LombardiSurfaceMobilityModel").set("Tref_ls", "1[K]");
    model.component("comp1").material("mat1").propertyGroup("LombardiSurfaceMobilityModel")
         .set("Eref_ls", "1[V/cm]");
    model.component("comp1").material("mat1").propertyGroup("LombardiSurfaceMobilityModel")
         .set("Nref_ls", "1[1/cm^3]");
    model.component("comp1").material("mat1").propertyGroup("ImpactIonization").label("Impact ionization");
    model.component("comp1").material("mat1").propertyGroup("ImpactIonization").set("an", "0.426[1/V]");
    model.component("comp1").material("mat1").propertyGroup("ImpactIonization").set("ap", "0.243[1/V]");
    model.component("comp1").material("mat1").propertyGroup("ImpactIonization").set("bn", "4.81E5[V/cm]");
    model.component("comp1").material("mat1").propertyGroup("ImpactIonization").set("bp", "6.53E5[V/cm]");
    model.component("comp1").material("mat1").propertyGroup("ImpactIonization").set("cnii", "3.05E-4[1/K]");
    model.component("comp1").material("mat1").propertyGroup("ImpactIonization").set("cpii", "5.35E-4[1/K]");
    model.component("comp1").material("mat1").propertyGroup("ImpactIonization").set("dn", "6.86E-4[1/K]");
    model.component("comp1").material("mat1").propertyGroup("ImpactIonization").set("dp", "5.67E-4[1/K]");
    model.component("comp1").material("mat1").propertyGroup("SlotboomModel").label("Slotboom model");
    model.component("comp1").material("mat1").propertyGroup("SlotboomModel").set("Eref_sb", "0.00692[V]");
    model.component("comp1").material("mat1").propertyGroup("SlotboomModel").set("Nref_sb", "1.3e17[1/cm^3]");
    model.component("comp1").material("mat1").propertyGroup("SlotboomModel").set("alpha_sb", "0.5");
    model.component("comp1").material("mat1").propertyGroup("JainRoulstonModel").label("Jain\u2013Roulston model");
    model.component("comp1").material("mat1").propertyGroup("JainRoulstonModel").set("An_jr", "3.5e-8[V]");
    model.component("comp1").material("mat1").propertyGroup("JainRoulstonModel").set("Bn_jr", "0[V]");
    model.component("comp1").material("mat1").propertyGroup("JainRoulstonModel").set("Cn_jr", "0[V]");
    model.component("comp1").material("mat1").propertyGroup("JainRoulstonModel").set("Ap_jr", "3.5e-8[V]");
    model.component("comp1").material("mat1").propertyGroup("JainRoulstonModel").set("Bp_jr", "0[V]");
    model.component("comp1").material("mat1").propertyGroup("JainRoulstonModel").set("Cp_jr", "0[V]");
    model.component("comp1").material("mat1").propertyGroup("JainRoulstonModel").set("Nref_jr", "1[1/cm^3]");
    model.component("comp1").material("mat1").propertyGroup("JainRoulstonModel").set("alpha_jr", "0.5");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel")
         .label("Klaassen unified mobility model");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel")
         .set("T_ref_kl", "300[K]");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel")
         .set("mu_e_max_kl", "1414.0[cm^2/V/s]");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel")
         .set("mu_h_max_kl", "470.5[cm^2/V/s]");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel")
         .set("mu_e_min_kl", "68.5[cm^2/V/s]");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel")
         .set("mu_h_min_kl", "44.9[cm^2/V/s]");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel")
         .set("theta_e_kl", "2.285");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel")
         .set("theta_h_kl", "2.247");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel")
         .set("alpha_e_1_kl", "0.711");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel")
         .set("alpha_h_1_kl", "0.719");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel")
         .set("N_ref_e_1_kl", "9.20e16[cm^-3]");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel")
         .set("N_ref_h_1_kl", "2.23e17[cm^-3]");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel").set("c_D_kl", "0.21");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel").set("c_A_kl", "0.50");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel")
         .set("N_ref_D_kl", "4.0e20[cm^-3]");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel")
         .set("N_ref_A_kl", "7.2e20[cm^-3]");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel").set("f_BH_kl", "3.828");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel").set("f_CW_kl", "2.459");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel")
         .set("N_BH_kl", "1.36e20[cm^-3]");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel")
         .set("P_CW_kl", "3.97e13");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel").set("s_1_kl", "0.89233");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel").set("s_2_kl", "0.41372");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel").set("s_3_kl", "0.19778");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel").set("s_4_kl", "0.28227");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel")
         .set("s_5_kl", "0.005978");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel").set("s_6_kl", "1.80618");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel").set("s_7_kl", "0.72169");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel").set("r_1_kl", "0.7643");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel").set("r_2_kl", "2.2999");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel").set("r_3_kl", "6.5502");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel").set("r_4_kl", "2.3670");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel")
         .set("r_5_kl", "-0.01552");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel").set("r_6_kl", "0.6478");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel")
         .set("m_e_kl", "me_const");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel")
         .set("m_h_kl", "1.258*me_const");
    model.component("comp1").material("mat1").selection().set(2);
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").selection().set(1, 3, 5);
    model.component("comp1").material("mat2").propertyGroup("def").set("electricconductivity", new String[]{"1e7"});
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").selection().set(4);
    model.component("comp1").material("mat3").propertyGroup("def").set("relpermittivity", new String[]{"4.5"});

    model.component("comp1").physics("ec").selection().set(1, 3, 5);
    model.component("comp1").physics("ec").prop("d").set("d", "L");
    model.component("comp1").physics("ec").create("ncd1", "NormalCurrentDensity", 1);
    model.component("comp1").physics("ec").feature("ncd1").selection().set(6);
    model.component("comp1").physics("ec").feature("ncd1").set("nJ", "semi.nJ");
    model.component("comp1").physics("ec").feature().duplicate("ncd2", "ncd1");
    model.component("comp1").physics("ec").feature("ncd2").selection().set(16);
    model.component("comp1").physics("ec").feature().duplicate("ncd3", "ncd2");
    model.component("comp1").physics("ec").feature("ncd3").selection().set(4);
    model.component("comp1").physics("ec").create("pot1", "ElectricPotential", 1);
    model.component("comp1").physics("ec").feature("pot1").selection().set(7);
    model.component("comp1").physics("ec").feature().duplicate("pot2", "pot1");
    model.component("comp1").physics("ec").feature("pot2").selection().set(2);
    model.component("comp1").physics("ec").feature().duplicate("pot3", "pot2");
    model.component("comp1").physics("ec").feature("pot3").selection().set(17);
    model.component("comp1").physics("ec").feature("pot3").set("V0", 1);
    model.component("comp1").physics("semi").selection().set(2, 4);
    model.component("comp1").physics("semi").prop("d").set("d", "L");
    model.component("comp1").physics("semi").create("ccn1", "ChargeConservation", 2);
    model.component("comp1").physics("semi").feature("ccn1").selection().set(4);
    model.component("comp1").physics("semi").create("term1", "Terminal", 1);
    model.component("comp1").physics("semi").feature("term1").selection().set(12);
    model.component("comp1").physics("semi").feature("term1").set("Phic", "4.1[V]");
    model.component("comp1").physics("semi").feature("term1").set("TerminalType", "Voltage");
    model.component("comp1").physics("semi").feature("term1").set("V0", 2);
    model.component("comp1").physics("semi").create("mc1", "MetalContact", 1);
    model.component("comp1").physics("semi").feature("mc1").selection().set(6);
    model.component("comp1").physics("semi").feature("mc1").set("V0", "V");
    model.component("comp1").physics("semi").feature().duplicate("mc2", "mc1");
    model.component("comp1").physics("semi").feature("mc2").selection().set(16);
    model.component("comp1").physics("semi").feature().duplicate("mc3", "mc2");
    model.component("comp1").physics("semi").feature("mc3").selection().set(4);
    model.component("comp1").physics("semi").create("adm1", "AnalyticDopingModel", 2);
    model.component("comp1").physics("semi").feature("adm1").selection().set(2);
    model.component("comp1").physics("semi").feature("adm1").set("NAc", "pdop");
    model.component("comp1").physics("semi").feature().duplicate("adm2", "adm1");
    model.component("comp1").physics("semi").feature("adm2").set("impurityDistribution", "box");
    model.component("comp1").physics("semi").feature("adm2").set("impurityType", "donor");
    model.component("comp1").physics("semi").feature("adm2").set("NDc", "ndop");
    model.component("comp1").physics("semi").feature("adm2").set("rb", new String[]{"0[um]", "0.6[um]", "0"});
    model.component("comp1").physics("semi").feature("adm2").set("jwidth", "0.6[um]");
    model.component("comp1").physics("semi").feature("adm2").set("jdepth", "0.1[um]");
    model.component("comp1").physics("semi").feature("adm2").set("AsymmetricJunctionDepth", true);
    model.component("comp1").physics("semi").feature("adm2").set("jda", new String[]{"0.2[um]", "0.25[um]", "0"});
    model.component("comp1").physics("semi").feature("adm2").set("Nb_src", "root.comp1.semi.adm1.NAc");
    model.component("comp1").physics("semi").feature().duplicate("adm3", "adm2");
    model.component("comp1").physics("semi").feature("adm3").set("rb", new String[]{"2.4[um]", "0.6[um]", "0"});
    model.component("comp1").physics("semi").create("tar1", "TrapAssistedRecombination", 2);
    model.component("comp1").physics("semi").feature("tar1").selection().set(2);

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 3);
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature().move("map1", 1);
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(6);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(21);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemcount", 10);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemratio", 5);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("reverse", true);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(23);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 400);
    model.component("comp1").mesh("mesh1").feature("size1").set("hauto", 4);
    model.component("comp1").mesh("mesh1").feature("size2").set("hauto", 3);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u7535\u52bf (ec)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("solutionparams", "parent");
    model.result("pg1").feature("surf1").set("colortable", "Dipole");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature().create("str1", "Streamline");
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("solutionparams", "parent");
    model.result("pg1").feature("str1").set("expr", new String[]{"ec.Ex", "ec.Ey"});
    model.result("pg1").feature("str1").set("titletype", "none");
    model.result("pg1").feature("str1").set("posmethod", "uniform");
    model.result("pg1").feature("str1").set("udist", 0.02);
    model.result("pg1").feature("str1").set("maxlen", 0.4);
    model.result("pg1").feature("str1").set("maxsteps", 5000);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("inheritcolor", false);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("data", "parent");
    model.result("pg1").feature("str1").selection().geom("geom1", 1);
    model.result("pg1").feature("str1").selection().set(1, 2, 4, 5, 6, 7, 8, 15, 16, 17, 18, 20);
    model.result("pg1").feature("str1").set("inheritplot", "surf1");
    model.result("pg1").feature("str1").feature().create("col1", "Color");
    model.result("pg1").feature("str1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg1").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg1").feature("str1").feature().create("filt1", "Filter");
    model.result("pg1").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u7535\u573a (ec)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("solutionparams", "parent");
    model.result("pg2").feature("surf1").set("expr", "ec.normE");
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature().create("str1", "Streamline");
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("solutionparams", "parent");
    model.result("pg2").feature("str1").set("expr", new String[]{"ec.Ex", "ec.Ey"});
    model.result("pg2").feature("str1").set("titletype", "none");
    model.result("pg2").feature("str1").set("posmethod", "uniform");
    model.result("pg2").feature("str1").set("udist", 0.02);
    model.result("pg2").feature("str1").set("maxlen", 0.4);
    model.result("pg2").feature("str1").set("maxsteps", 5000);
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("inheritcolor", false);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("data", "parent");
    model.result("pg2").feature("str1").selection().geom("geom1", 1);
    model.result("pg2").feature("str1").selection().set(1, 2, 4, 5, 6, 7, 8, 15, 16, 17, 18, 20);
    model.result("pg2").feature("str1").set("inheritplot", "surf1");
    model.result("pg2").feature("str1").feature().create("col1", "Color");
    model.result("pg2").feature("str1").feature("col1").set("expr", "ec.normE");
    model.result("pg2").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg2").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg2").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg2").feature("str1").feature().create("filt1", "Filter");
    model.result("pg2").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u7535\u5b50\u6d53\u5ea6 (semi)");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("expr", "semi.N");
    model.result("pg3").feature("surf1").set("unit", "1/cm^3");
    model.result("pg3").feature("surf1").set("colortable", "Prism");
    model.result("pg3").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg3").feature("surf1").set("resolution", "norefine");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u7a7a\u7a74\u6d53\u5ea6 (semi)");
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("expr", "semi.P");
    model.result("pg4").feature("surf1").set("unit", "1/cm^3");
    model.result("pg4").feature("surf1").set("colortable", "Prism");
    model.result("pg4").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg4").feature("surf1").set("resolution", "norefine");
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").label("\u7535\u52bf (semi)");
    model.result("pg5").feature().create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("expr", "V2");
    model.result("pg5").feature("surf1").set("resolution", "norefine");
    model.result("pg5").feature("surf1").set("smooth", "internal");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("data", "parent");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").set("data", "dset1");
    model.result("pg6").create("surf2", "Surface");
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf2").set("expr", "semi.Nnetdop");
    model.result("pg6").feature("surf2").set("unit", "1/cm^3");
    model.result("pg6").feature("surf2").set("coloring", "gradient");
    model.result("pg6").feature("surf2").set("colorscalemode", "logarithmic");
    model.result("pg6").feature("surf2").set("topcolor", "red");
    model.result("pg6").feature("surf2").set("bottomcolor", "custom");
    model.result("pg6").feature("surf2").set("custombottomcolor", new double[]{1, 0.8, 0.8});
    model.result("pg6").feature("surf2").set("smooth", "internal");
    model.result("pg6").feature("surf2").set("showsolutionparams", "on");
    model.result("pg6").feature("surf2").set("data", "parent");
    model.result("pg6").feature("surf2").set("titletype", "none");
    model.result("pg6").feature("surf2").feature().create("filt1", "Filter");
    model.result("pg6").feature("surf2").feature("filt1").set("expr", "semi.Na-semi.Nd > 1[1/cm^3]");
    model.result("pg6").feature("surf2").feature("filt1").set("useder", true);
    model.result("pg6").feature("surf1").set("expr", "semi.Nnetdop");
    model.result("pg6").feature("surf1").set("unit", "1/cm^3");
    model.result("pg6").feature("surf1").set("coloring", "gradient");
    model.result("pg6").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg6").feature("surf1").set("topcolor", "blue");
    model.result("pg6").feature("surf1").set("bottomcolor", "custom");
    model.result("pg6").feature("surf1").set("custombottomcolor", new double[]{0.8, 0.8, 1});
    model.result("pg6").feature("surf1").set("smooth", "internal");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("data", "parent");
    model.result("pg6").feature("surf1").set("titletype", "none");
    model.result("pg6").feature("surf1").feature().create("filt1", "Filter");
    model.result("pg6").feature("surf1").feature("filt1").set("expr", "semi.Nd-semi.Na > 1[1/cm^3]");
    model.result("pg6").feature("surf1").feature("filt1").set("useder", true);
    model.result("pg6").set("titletype", "manual");
    model.result("pg6")
         .set("title", "\u51c0\u63ba\u6742\u5242\u6d53\u5ea6 \\vert N<sub>d</sub> - N<sub>a</sub>\\vert: P \u578b (\u7ea2\u8272), N \u578b (\u84dd\u8272)");
    model.result("pg6").set("showlegendsmaxmin", true);
    model.result("pg6").set("showlegendsunit", true);
    model.result("pg6").set("legendpos", "alternating");
    model.result("pg6").feature("surf2").label("P \u578b");
    model.result("pg6").feature("surf1").label("N \u578b");
    model.result("pg6").label("\u51c0\u63ba\u6742\u5242\u6d53\u5ea6 (semi)");
    model.result("pg1").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("expr", "semi.log10N");
    model.result("pg3").feature("surf1").set("colortable", "Rainbow");
    model.result("pg3").feature("surf1").set("colorscalemode", "linear");
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("expr", "semi.log10P");
    model.result("pg4").feature("surf1").set("colortable", "Rainbow");
    model.result("pg4").feature("surf1").set("colorscalemode", "linear");
    model.result("pg4").run();
    model.result().dataset().duplicate("dset2", "dset1");
    model.result().dataset("dset2").selection().geom("geom1", 2);
    model.result().dataset("dset2").selection().geom("geom1", 2);
    model.result().dataset("dset2").selection().set(1, 2, 3, 5);
    model.result().dataset().create("extr1", "Extrude2D");
    model.result().dataset("extr1").set("data", "dset2");
    model.result().dataset().duplicate("dset3", "dset2");
    model.result().dataset("dset3").selection().geom("geom1", 2);
    model.result().dataset("dset3").selection().set();
    model.result().dataset("dset3").selection().geom("geom1", 2);
    model.result().dataset("dset3").selection().set(4);
    model.result().dataset().duplicate("extr2", "extr1");
    model.result().dataset("extr2").set("data", "dset3");
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").run();
    model.result("pg7").set("titletype", "none");
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", "1");
    model.result("pg7").feature("surf1").set("data", "extr1");
    model.result("pg7").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg7").run();
    model.result("pg7").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg7").feature("surf1").feature("mtrl1").set("family", "custom");
    model.result("pg7").feature("surf1").feature("mtrl1").set("metallic", 0.3);
    model.result("pg7").run();
    model.result("pg7").create("surf2", "Surface");
    model.result("pg7").feature("surf2").set("data", "extr1");
    model.result("pg7").feature("surf2").set("expr", "V2");
    model.result("pg7").feature("surf2").set("colortable", "GrayBody");
    model.result("pg7").run();
    model.result("pg7").create("surf3", "Surface");
    model.result("pg7").feature("surf3").set("data", "extr2");
    model.result("pg7").feature("surf3").set("expr", "1");
    model.result("pg7").feature("surf3").create("mtrl1", "MaterialAppearance");
    model.result("pg7").run();
    model.result("pg7").feature("surf3").feature("mtrl1").set("appearance", "custom");
    model.result("pg7").feature("surf3").feature("mtrl1").set("family", "sand");
    model.result("pg7").set("showlegends", false);

    model.view("view2").set("showgrid", false);
    model.view("view2").camera().setIndex("position", 11, 0);
    model.view("view2").camera().setIndex("position", 8, 1);
    model.view("view2").camera().set("position", new int[]{11, 8, 13});
    model.view("view2").camera().setIndex("up", -0.15, 0);
    model.view("view2").camera().setIndex("up", 1, 1);
    model.view("view2").camera().set("up", new double[]{-0.15, 1, -0.25});

    model.result("pg7").run();
    model.result("pg7").run();

    model.title("\u5177\u6709\u663e\u5f0f\u91d1\u5c5e\u548c\u4ecb\u7535\u57df\u7684 MOSFET");

    model
         .description("\u672c\u4f8b\u5728\u201cMOS \u6676\u4f53\u7ba1 (MOSFET) \u7684\u76f4\u6d41\u7279\u6027\u201d\u6a21\u578b\u7684\u57fa\u7840\u4e0a\u8fdb\u884c\u4e86\u6539\u8fdb\uff0c\u5bf9\u91d1\u5c5e\u548c\u4ecb\u7535\u57df\u8fdb\u884c\u663e\u5f0f\u5efa\u6a21\uff0c\u800c\u4e0d\u662f\u901a\u8fc7\u8fb9\u754c\u6761\u4ef6\u8fdb\u884c\u5b9a\u4e49\u3002\u56e0\u6b64\uff0c\u53ef\u4ee5\u89c2\u5bdf\u5230\u91d1\u5c5e\u548c\u7edd\u7f18\u4f53\u5185\u90e8\u7684\u7535\u52bf\u5206\u5e03\u60c5\u51b5\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("mosfet_with_explicit_metal_and_dielectric.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
