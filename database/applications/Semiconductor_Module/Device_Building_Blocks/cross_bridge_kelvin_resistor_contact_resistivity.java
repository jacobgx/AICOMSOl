/*
 * cross_bridge_kelvin_resistor_contact_resistivity.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:49 by COMSOL 6.3.0.290. */
public class cross_bridge_kelvin_resistor_contact_resistivity {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Semiconductor_Module\\Device_Building_Blocks");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("semi", "Semiconductor", "geom1");
    model.component("comp1").physics().create("gb", "GeneralFormBoundaryPDE", "geom1");
    model.component("comp1").physics("gb").prop("EquationForm").set("form", "Automatic");
    model.component("comp1").physics("gb").prop("Units").set("DependentVariableQuantity", "none");
    model.component("comp1").physics("gb").prop("Units").set("CustomDependentVariableUnit", "V");
    model.component("comp1").physics("gb").field("dimensionless").field("V2D");
    model.component("comp1").physics("gb").field("dimensionless").component(new String[]{"V2D"});
    model.component("comp1").physics("gb").prop("Units").set("CustomSourceTermUnit", "V*m^-2");
    model.component("comp1").physics("gb").feature("gfeq1")
         .set("Ga", new String[][]{{"-V2DTx", "-V2DTy", "-V2DTz"}});

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/semi", true);
    model.study("std1").feature("stat").setSolveFor("/physics/gb", true);

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");

    model.param().set("l", "5[um]");
    model.param().descr("l", "\u63a5\u89e6\u7a97\u53e3\u5927\u5c0f");
    model.param().set("w", "l+5[um]");
    model.param().descr("w", "\u6269\u6563\u62bd\u5934\u5bbd\u5ea6");
    model.param().set("lb", "w");
    model.param().descr("lb", "\u6865\u7684\u957f\u5ea6");
    model.param().set("d", "5000[angstrom]");
    model.param().descr("d", "Si \u7684\u539a\u5ea6");
    model.param().set("Rs", "11.0[ohm]");
    model.param().descr("Rs", "\u8584\u5c42\u7535\u963b");
    model.param().set("mun0", "1450[cm^2/(V*s)]");
    model.param().descr("mun0", "\u7535\u5b50\u8fc1\u79fb\u7387");
    model.param().set("Nd0", "1/(d*e_const*mun0*Rs)");
    model.param().descr("Nd0", "Rs \u7684\u63ba\u6742\u6d53\u5ea6");
    model.param().set("rho_c", "4.5e-8[ohm*cm^2]");
    model.param().descr("rho_c", "\u6bd4\u63a5\u89e6\u7535\u963b\u7387");
    model.param().set("lt", "sqrt(rho_c/Rs)");
    model.param().descr("lt", "\u4f20\u8f93\u957f\u5ea6");
    model.param().set("V0", "100[uV]");
    model.param().descr("V0", "\u5916\u52a0\u7535\u538b");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq1")
         .label("\u6b63\u65b9\u5f62 1 - \u63a5\u89e6\u7a97\u53e3");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq1").set("size", "l");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq1").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("sq1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .label("\u591a\u8fb9\u5f62 1 - \u6865");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "-w/2-lb", 0, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "-w/2", 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "-w/2-lb", 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "w/2", 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "w/2", 2, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "w/2", 2, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "w/2", 3, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "-w/2-lb", 3, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "-w/2", 4, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "-w/2-lb", 4, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "-w/2", 5, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "-w/2", 5, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "-w/2-lb", 6, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "-w/2", 6, 1);
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "d", 0);
    model.component("comp1").geom("geom1").runPre("fin");

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

    model.component("comp1").geom("geom1").run();

    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").set("mun", new String[]{"mun0"});

    model.component("comp1").physics("semi").prop("ModelProperties").set("Compute", "MajorityPsi");
    model.component("comp1").physics("semi").create("adm1", "AnalyticDopingModel", 3);
    model.component("comp1").physics("semi").feature("adm1").selection().all();
    model.component("comp1").physics("semi").feature("adm1").set("impurityType", "donor");
    model.component("comp1").physics("semi").feature("adm1").set("NDc", "Nd0");
    model.component("comp1").physics("semi").create("mc1", "MetalContact", 2);
    model.component("comp1").physics("semi").feature("mc1")
         .label("\u91d1\u5c5e\u63a5\u89e6 1 - \u8f93\u5165\u7535\u6d41");
    model.component("comp1").physics("semi").feature("mc1").selection().set(1);
    model.component("comp1").physics("semi").feature("mc1").set("V0", "V0");
    model.component("comp1").physics("semi").create("mc2", "MetalContact", 2);
    model.component("comp1").physics("semi").feature("mc2")
         .label("\u91d1\u5c5e\u63a5\u89e6 2 - \u6865\u5f0f\u7535\u538b\u8868");
    model.component("comp1").physics("semi").feature("mc2").selection().set(7);
    model.component("comp1").physics("semi").feature("mc2").set("TerminalType", "Current");
    model.component("comp1").physics("semi").feature("mc2").set("V0_init", "V0");
    model.component("comp1").physics("semi").create("mc3", "MetalContact", 2);
    model.component("comp1").physics("semi").feature("mc3")
         .label("\u91d1\u5c5e\u63a5\u89e6 3 - \u63a5\u89e6\u7a97\u53e3");
    model.component("comp1").physics("semi").feature("mc3").selection().set(11);

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").label("\u63a5\u89e6\u7a97\u53e3");
    model.component("comp1").selection("sel1").set(11);

    model.component("comp1").physics("semi").feature("mc3").selection().named("sel1");
    model.component("comp1").physics("semi").feature("mc3").set("ContactResistance", true);
    model.component("comp1").physics("semi").feature("mc3").set("rho_c", "rho_c");
    model.component("comp1").physics("gb").selection().set(4, 11);
    model.component("comp1").physics("gb").feature("gfeq1")
         .label("\u4e00\u822c\u5f62\u5f0f\u504f\u5fae\u5206\u65b9\u7a0b 1 - \u53c2\u8003\u8bba\u6587\u4e2d\u65b9\u7a0b 17 \u7684\u5de6\u4fa7");
    model.component("comp1").physics("gb").feature("gfeq1")
         .setIndex("Ga", new String[]{"V2DTx", "-V2DTy", "-V2DTz"}, 0);
    model.component("comp1").physics("gb").feature("gfeq1")
         .setIndex("Ga", new String[]{"V2DTx", "V2DTy", "-V2DTz"}, 0);
    model.component("comp1").physics("gb").feature("gfeq1")
         .setIndex("Ga", new String[]{"V2DTx", "V2DTy", "V2DTz"}, 0);
    model.component("comp1").physics("gb").feature("gfeq1").setIndex("f", 0, 0);
    model.component("comp1").physics("gb").feature("gfeq1").setIndex("da", 0, 0);
    model.component("comp1").physics("gb").create("src1", "SourceTerm", 2);
    model.component("comp1").physics("gb").feature("src1")
         .label("\u6e90 1 - \u53c2\u8003\u8bba\u6587\u4e2d\u65b9\u7a0b 17 \u7684\u53f3\u4fa7");
    model.component("comp1").physics("gb").feature("src1").selection().named("sel1");
    model.component("comp1").physics("gb").feature("src1").setIndex("f", "V2D/lt^2", 0);
    model.component("comp1").physics("gb").create("dir1", "DirichletBoundary", 1);
    model.component("comp1").physics("gb").feature("dir1")
         .label("\u72c4\u5229\u514b\u96f7\u8fb9\u754c\u6761\u4ef6 1 - \u8f93\u5165\u7535\u6d41");
    model.component("comp1").physics("gb").feature("dir1").selection().set(4);
    model.component("comp1").physics("gb").feature("dir1").setIndex("r", "V0", 0);

    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(1);
    model.component("comp1").selection("sel2").label("\u8f93\u5165\u7535\u6d41\uff08\u4e8c\u7ef4\u6a21\u578b\uff09");
    model.component("comp1").selection("sel2").set(4);

    model.component("comp1").physics("gb").feature("dir1").selection().named("sel2");
    model.component("comp1").physics("gb").feature("dir1").setIndex("weakConstraints", 1, 0);

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").label("\u79ef\u5206 1 - \u8f93\u5165\u7535\u6d41");
    model.component("comp1").cpl("intop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop1").selection().named("sel2");
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").label("\u79ef\u5206 2 - \u6865\u5f0f\u7535\u538b\u8868");
    model.component("comp1").cpl("intop2").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop2").selection().set(13);

    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").geom(1);
    model.component("comp1").selection("sel3")
         .label("\u6865\u5f0f\u7535\u538b\u8868\uff08\u4e8c\u7ef4\u6a21\u578b\uff09");
    model.component("comp1").selection("sel3").set(13);

    model.component("comp1").cpl("intop2").selection().named("sel3");
    model.component("comp1").cpl().create("intop3", "Integration");
    model.component("comp1").cpl("intop3").set("axisym", true);
    model.component("comp1").cpl("intop3").label("\u79ef\u5206 3 - \u63a5\u89e6\u7a97\u53e3");
    model.component("comp1").cpl("intop3").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop3").selection().named("sel1");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("I2D_1", "intop1(V2D_lm[V/m]/Rs)");
    model.component("comp1").variable("var1")
         .descr("I2D_1", "\u8f93\u5165\u7535\u6d41\uff08\u4e8c\u7ef4\u6a21\u578b\uff09");
    model.component("comp1").variable("var1").set("I2D_2", "intop2(V2D_lm[V/m]/Rs)");
    model.component("comp1").variable("var1")
         .descr("I2D_2", "\u7535\u538b\u8868\u7535\u6d41\uff08\u4e8c\u7ef4\u6a21\u578b\uff09");
    model.component("comp1").variable("var1").set("I2D_3", "intop3(V2D/rho_c)");
    model.component("comp1").variable("var1")
         .descr("I2D_3", "\u63a5\u89e6\u7a97\u53e3\u7535\u6d41\uff08\u4e8c\u7ef4\u6a21\u578b\uff09");

    model.component("comp1").physics("gb").create("dir2", "DirichletBoundary", 1);
    model.component("comp1").physics("gb").feature("dir2")
         .label("\u72c4\u5229\u514b\u96f7\u8fb9\u754c\u6761\u4ef6 2 - \u6865\u5f0f\u7535\u538b\u8868");
    model.component("comp1").physics("gb").feature("dir2").selection().named("sel3");
    model.component("comp1").physics("gb").feature("dir2").setIndex("r", "V2D_ode", 0);
    model.component("comp1").physics("gb").feature("dir2").setIndex("weakConstraints", 1, 0);
    model.component("comp1").physics("gb").create("ge1", "GlobalEquations", -1);
    model.component("comp1").physics("gb").feature("ge1")
         .label("\u5168\u5c40\u65b9\u7a0b 1 - \u6865\u5f0f\u7535\u538b\u8868");
    model.component("comp1").physics("gb").feature("ge1").set("CustomDependentVariableUnit", "1");
    model.component("comp1").physics("gb").feature("ge1").set("DependentVariableQuantity", "none");
    model.component("comp1").physics("gb").feature("ge1").setIndex("CustomDependentVariableUnit", "V", 0, 0);
    model.component("comp1").physics("gb").feature("ge1").set("CustomSourceTermUnit", "1");
    model.component("comp1").physics("gb").feature("ge1").set("SourceTermQuantity", "none");
    model.component("comp1").physics("gb").feature("ge1").setIndex("CustomSourceTermUnit", "A", 0, 0);
    model.component("comp1").physics("gb").feature("ge1").setIndex("name", "V2D_ode", 0, 0);
    model.component("comp1").physics("gb").feature("ge1").setIndex("equation", "I2D_2", 0, 0);

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().set(18, 19, 22, 25);
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("numelem", "20*l/5[um]");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", 1.2);
    model.component("comp1").mesh("mesh1").feature("size").set("hgrad", 1.3);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(4, 11);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("elemratio", 5);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 1 - Rc vs. l");
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "l", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "l", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("plistarr", "range(5,10,65)", 0);
    model.study("std1").feature("param").setIndex("punit", "um", 0);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");

    return model;
  }

  public static Model run2(Model model) {
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u7535\u5b50\u6d53\u5ea6 (semi)");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 7, 0);
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("expr", "semi.N");
    model.result("pg1").feature("vol1").set("unit", "1/cm^3");
    model.result("pg1").feature("vol1").set("colortable", "Prism");
    model.result("pg1").feature("vol1").set("colorscalemode", "logarithmic");
    model.result("pg1").feature("vol1").set("resolution", "norefine");
    model.result("pg1").feature("vol1").set("smooth", "internal");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("data", "parent");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u7a7a\u7a74\u6d53\u5ea6 (semi)");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevel", 7, 0);
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("vol1", "Volume");
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("expr", "semi.P");
    model.result("pg2").feature("vol1").set("unit", "1/cm^3");
    model.result("pg2").feature("vol1").set("colortable", "Prism");
    model.result("pg2").feature("vol1").set("colorscalemode", "logarithmic");
    model.result("pg2").feature("vol1").set("resolution", "norefine");
    model.result("pg2").feature("vol1").set("smooth", "internal");
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("data", "parent");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u7535\u52bf (semi)");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 7, 0);
    model.result("pg3").feature().create("vol1", "Volume");
    model.result("pg3").feature("vol1").set("showsolutionparams", "on");
    model.result("pg3").feature("vol1").set("expr", "V");
    model.result("pg3").feature("vol1").set("resolution", "norefine");
    model.result("pg3").feature("vol1").set("smooth", "internal");
    model.result("pg3").feature("vol1").set("showsolutionparams", "on");
    model.result("pg3").feature("vol1").set("data", "parent");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").create("vol2", "Volume");
    model.result("pg4").create("vol1", "Volume");
    model.result("pg4").feature("vol2").set("expr", "semi.Nnetdop");
    model.result("pg4").feature("vol2").set("unit", "1/cm^3");
    model.result("pg4").feature("vol2").set("coloring", "gradient");
    model.result("pg4").feature("vol2").set("colorscalemode", "logarithmic");
    model.result("pg4").feature("vol2").set("topcolor", "red");
    model.result("pg4").feature("vol2").set("bottomcolor", "custom");
    model.result("pg4").feature("vol2").set("custombottomcolor", new double[]{1, 0.8, 0.8});
    model.result("pg4").feature("vol2").set("smooth", "internal");
    model.result("pg4").feature("vol2").set("showsolutionparams", "on");
    model.result("pg4").feature("vol2").set("data", "parent");
    model.result("pg4").feature("vol2").set("titletype", "none");
    model.result("pg4").feature("vol2").feature().create("filt1", "Filter");
    model.result("pg4").feature("vol2").feature("filt1").set("expr", "semi.Na-semi.Nd > 1[1/cm^3]");
    model.result("pg4").feature("vol2").feature("filt1").set("useder", true);
    model.result("pg4").feature("vol1").set("expr", "semi.Nnetdop");
    model.result("pg4").feature("vol1").set("unit", "1/cm^3");
    model.result("pg4").feature("vol1").set("coloring", "gradient");
    model.result("pg4").feature("vol1").set("colorscalemode", "logarithmic");
    model.result("pg4").feature("vol1").set("topcolor", "blue");
    model.result("pg4").feature("vol1").set("bottomcolor", "custom");
    model.result("pg4").feature("vol1").set("custombottomcolor", new double[]{0.8, 0.8, 1});
    model.result("pg4").feature("vol1").set("smooth", "internal");
    model.result("pg4").feature("vol1").set("showsolutionparams", "on");
    model.result("pg4").feature("vol1").set("data", "parent");
    model.result("pg4").feature("vol1").set("titletype", "none");
    model.result("pg4").feature("vol1").feature().create("filt1", "Filter");
    model.result("pg4").feature("vol1").feature("filt1").set("expr", "semi.Nd-semi.Na > 1[1/cm^3]");
    model.result("pg4").feature("vol1").feature("filt1").set("useder", true);
    model.result("pg4").set("titletype", "manual");
    model.result("pg4")
         .set("title", "\u51c0\u63ba\u6742\u5242\u6d53\u5ea6 \\vert N<sub>d</sub> - N<sub>a</sub>\\vert: P \u578b (\u7ea2\u8272), N \u578b (\u84dd\u8272)");
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").set("legendpos", "alternating");
    model.result("pg4").feature("vol2").label("P \u578b");
    model.result("pg4").feature("vol1").label("N \u578b");
    model.result("pg4").label("\u51c0\u63ba\u6742\u5242\u6d53\u5ea6 (semi)");
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("data", "dset2");
    model.result().numerical("gev1").set("expr", new String[]{"V2D_ode"});
    model.result().numerical("gev1").set("descr", new String[]{"\u72b6\u6001\u53d8\u91cf V2D_ode"});
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").setIndex("looplevel", 7, 0);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").label("\u4e00\u822c\u5f62\u5f0f\u8fb9\u754c\u504f\u5fae\u5206\u65b9\u7a0b");
    model.result("pg5").feature("surf1").set("expr", "V2D");
    model.result("pg1").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").label("\u8ba1\u7b97\u7ec4 1 - Rc vs. l");
    model.result().evaluationGroup("eg1").set("data", "dset2");
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "\u6865\u5f0f\u7535\u538b\u8868\uff08\u4e8c\u7ef4\u6a21\u578b\uff09", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "semi.V0_2", 1);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "\u6865\u5f0f\u7535\u538b\u8868", 1);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "I2D_1", 2);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "I2D_3", 3);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "semi.I0_1", 4);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "\u8f93\u5165\u7535\u6d41", 4);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "semi.I0_3", 5);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "\u63a5\u89e6\u7a97\u53e3\u7535\u6d41", 5);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "I2D_2", 6);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "semi.I0_2", 7);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "\u7535\u538b\u8868\u7535\u6d41", 7);
    model.result().evaluationGroup("eg1").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("Rc vs. l");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("ylabel", "\u5f00\u5c14\u6587\u63a5\u89e6\u7535\u963b (\u03a9)");
    model.result("pg6").set("axislimits", true);
    model.result("pg6").set("xmin", 10);
    model.result("pg6").set("xmax", "1e4");
    model.result("pg6").set("ymin", 0.01);
    model.result("pg6").set("ymax", 10);
    model.result("pg6").set("xlog", true);
    model.result("pg6").set("ylog", true);
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").setIndex("expr", "-V2D_ode/I2D_1", 0);
    model.result("pg6").feature("glob1").setIndex("descr", "Rc\uff08\u4e8c\u7ef4\u6a21\u578b\uff09", 0);
    model.result("pg6").feature("glob1").setIndex("expr", "semi.V0_2/semi.I0_1", 1);
    model.result("pg6").feature("glob1").setIndex("descr", "Rc", 1);
    model.result("pg6").feature("glob1").set("xdata", "expr");
    model.result("pg6").feature("glob1").set("xdataexpr", "l^2");
    model.result("pg6").feature("glob1").set("xdataunit", "\u00b5m^2");
    model.result("pg6").feature("glob1").set("xdatadescractive", true);
    model.result("pg6").feature("glob1").set("xdatadescr", "\u63a5\u89e6\u533a\u57df");
    model.result("pg6").feature("glob1").set("linestyle", "cycle");
    model.result("pg6").feature("glob1").set("linewidth", 2);
    model.result("pg6").run();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/semi", true);
    model.study("std2").feature("stat").setSolveFor("/physics/gb", true);

    model.component("comp1").geom("geom1").run();

    model.study("std2").label("\u7814\u7a76 2 - Rc vs. w");
    model.study("std2").create("param", "Parametric");
    model.study("std2").feature("param").setIndex("pname", "l", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "m", 0);
    model.study("std2").feature("param").setIndex("pname", "l", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "m", 0);
    model.study("std2").feature("param").setIndex("pname", "w", 0);
    model.study("std2").feature("param").setIndex("plistarr", "5.5 7 10 16 30 60", 0);
    model.study("std2").feature("param").setIndex("punit", "um", 0);
    model.study("std2").createAutoSequences("all");

    model.sol().create("sol11");
    model.sol("sol11").study("std2");
    model.sol("sol11").label("\u53c2\u6570\u5316\u89e3 2");

    model.batch("p2").feature("so1").set("psol", "sol11");
    model.batch("p2").run("compute");

    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").label("\u7535\u5b50\u6d53\u5ea6 (semi) 1");
    model.result("pg7").set("data", "dset4");
    model.result("pg7").setIndex("looplevel", 6, 0);
    model.result("pg7").set("showlegendsmaxmin", true);
    model.result("pg7").feature().create("vol1", "Volume");
    model.result("pg7").feature("vol1").set("showsolutionparams", "on");
    model.result("pg7").feature("vol1").set("expr", "semi.N");
    model.result("pg7").feature("vol1").set("unit", "1/cm^3");
    model.result("pg7").feature("vol1").set("colortable", "Prism");
    model.result("pg7").feature("vol1").set("colorscalemode", "logarithmic");
    model.result("pg7").feature("vol1").set("resolution", "norefine");
    model.result("pg7").feature("vol1").set("smooth", "internal");
    model.result("pg7").feature("vol1").set("showsolutionparams", "on");
    model.result("pg7").feature("vol1").set("data", "parent");
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").label("\u7a7a\u7a74\u6d53\u5ea6 (semi) 1");
    model.result("pg8").set("data", "dset4");
    model.result("pg8").setIndex("looplevel", 6, 0);
    model.result("pg8").set("showlegendsmaxmin", true);
    model.result("pg8").feature().create("vol1", "Volume");
    model.result("pg8").feature("vol1").set("showsolutionparams", "on");
    model.result("pg8").feature("vol1").set("expr", "semi.P");
    model.result("pg8").feature("vol1").set("unit", "1/cm^3");
    model.result("pg8").feature("vol1").set("colortable", "Prism");
    model.result("pg8").feature("vol1").set("colorscalemode", "logarithmic");
    model.result("pg8").feature("vol1").set("resolution", "norefine");
    model.result("pg8").feature("vol1").set("smooth", "internal");
    model.result("pg8").feature("vol1").set("showsolutionparams", "on");
    model.result("pg8").feature("vol1").set("data", "parent");
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").label("\u7535\u52bf (semi) 1");
    model.result("pg9").set("data", "dset4");
    model.result("pg9").setIndex("looplevel", 6, 0);
    model.result("pg9").feature().create("vol1", "Volume");
    model.result("pg9").feature("vol1").set("showsolutionparams", "on");
    model.result("pg9").feature("vol1").set("expr", "V");
    model.result("pg9").feature("vol1").set("resolution", "norefine");
    model.result("pg9").feature("vol1").set("smooth", "internal");
    model.result("pg9").feature("vol1").set("showsolutionparams", "on");
    model.result("pg9").feature("vol1").set("data", "parent");
    model.result().create("pg10", "PlotGroup3D");
    model.result("pg10").set("data", "dset4");
    model.result("pg10").create("vol2", "Volume");
    model.result("pg10").create("vol1", "Volume");
    model.result("pg10").feature("vol2").set("expr", "semi.Nnetdop");
    model.result("pg10").feature("vol2").set("unit", "1/cm^3");
    model.result("pg10").feature("vol2").set("coloring", "gradient");
    model.result("pg10").feature("vol2").set("colorscalemode", "logarithmic");
    model.result("pg10").feature("vol2").set("topcolor", "red");
    model.result("pg10").feature("vol2").set("bottomcolor", "custom");
    model.result("pg10").feature("vol2").set("custombottomcolor", new double[]{1, 0.8, 0.8});
    model.result("pg10").feature("vol2").set("smooth", "internal");
    model.result("pg10").feature("vol2").set("showsolutionparams", "on");
    model.result("pg10").feature("vol2").set("data", "parent");
    model.result("pg10").feature("vol2").set("titletype", "none");
    model.result("pg10").feature("vol2").feature().create("filt1", "Filter");
    model.result("pg10").feature("vol2").feature("filt1").set("expr", "semi.Na-semi.Nd > 1[1/cm^3]");
    model.result("pg10").feature("vol2").feature("filt1").set("useder", true);
    model.result("pg10").feature("vol1").set("expr", "semi.Nnetdop");
    model.result("pg10").feature("vol1").set("unit", "1/cm^3");
    model.result("pg10").feature("vol1").set("coloring", "gradient");
    model.result("pg10").feature("vol1").set("colorscalemode", "logarithmic");
    model.result("pg10").feature("vol1").set("topcolor", "blue");
    model.result("pg10").feature("vol1").set("bottomcolor", "custom");
    model.result("pg10").feature("vol1").set("custombottomcolor", new double[]{0.8, 0.8, 1});
    model.result("pg10").feature("vol1").set("smooth", "internal");
    model.result("pg10").feature("vol1").set("showsolutionparams", "on");
    model.result("pg10").feature("vol1").set("data", "parent");
    model.result("pg10").feature("vol1").set("titletype", "none");
    model.result("pg10").feature("vol1").feature().create("filt1", "Filter");
    model.result("pg10").feature("vol1").feature("filt1").set("expr", "semi.Nd-semi.Na > 1[1/cm^3]");
    model.result("pg10").feature("vol1").feature("filt1").set("useder", true);
    model.result("pg10").set("titletype", "manual");
    model.result("pg10")
         .set("title", "\u51c0\u63ba\u6742\u5242\u6d53\u5ea6 \\vert N<sub>d</sub> - N<sub>a</sub>\\vert: P \u578b (\u7ea2\u8272), N \u578b (\u84dd\u8272)");
    model.result("pg10").set("showlegendsmaxmin", true);
    model.result("pg10").set("showlegendsunit", true);
    model.result("pg10").set("legendpos", "alternating");
    model.result("pg10").feature("vol2").label("P \u578b");
    model.result("pg10").feature("vol1").label("N \u578b");
    model.result("pg10").label("\u51c0\u63ba\u6742\u5242\u6d53\u5ea6 (semi) 1");
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").set("data", "dset4");
    model.result().numerical("gev2").set("expr", new String[]{"V2D_ode"});
    model.result().numerical("gev2").set("descr", new String[]{"\u72b6\u6001\u53d8\u91cf V2D_ode"});
    model.result().create("pg11", "PlotGroup3D");
    model.result("pg11").set("data", "dset4");
    model.result("pg11").setIndex("looplevel", 6, 0);
    model.result("pg11").create("surf1", "Surface");
    model.result("pg11").label("\u4e00\u822c\u5f62\u5f0f\u8fb9\u754c\u504f\u5fae\u5206\u65b9\u7a0b 1");
    model.result("pg11").feature("surf1").set("expr", "V2D");
    model.result("pg7").run();
    model.result().evaluationGroup().duplicate("eg2", "eg1");
    model.result().evaluationGroup("eg2").label("\u8ba1\u7b97\u7ec4 2 - Rc vs. w");
    model.result().evaluationGroup("eg2").set("data", "dset4");
    model.result().evaluationGroup("eg2").run();
    model.result("pg6").run();
    model.result().duplicate("pg12", "pg6");
    model.result("pg12").run();
    model.result("pg12").label("Rc vs. w");
    model.result("pg12").set("data", "dset4");
    model.result("pg12").set("xmin", 0);
    model.result("pg12").set("xmax", 70);
    model.result("pg12").set("ymin", 0.1);
    model.result("pg12").set("xlog", false);
    model.result("pg12").run();
    model.result("pg12").feature("glob1").set("xdata", "solution");
    model.result("pg12").run();
    model.result("pg3").run();
    model.result().duplicate("pg13", "pg3");
    model.result("pg13").run();
    model.result("pg13").label("\u6a21\u578b\u7f29\u7565\u56fe");
    model.result("pg13").setIndex("looplevel", 1, 0);
    model.result("pg13").set("view", "new");
    model.result("pg13").set("showlegends", false);
    model.result("pg13").run();

    model.view("view3").camera().set("viewscaletype", "manual");
    model.view("view3").camera().set("zscale", 5);

    model.result("pg13").run();
    model.result("pg13").feature("vol1").set("colortable", "JupiterAuroraBorealis");
    model.result("pg13").feature("vol1").create("tran1", "Transparency");
    model.result("pg13").run();
    model.result("pg13").run();
    model.result("pg13").create("str1", "Streamline");
    model.result("pg13").feature("str1").set("expr", new String[]{"semi.JX", "semi.JY", "semi.JZ"});
    model.result("pg13").feature("str1").set("descr", "\u603b\u7535\u6d41\u5bc6\u5ea6\uff0c\u8282\u70b9\u503c");
    model.result("pg13").feature("str1").set("posmethod", "start");
    model.result("pg13").feature("str1").set("startmethod", "coord");
    model.result("pg13").feature("str1")
         .set("xcoord", "-15 -15 -15 -15 -15 -15 -15 -15 -15 -15 -15 -15 -15 -15 -15 -15 -15 -15");
    model.result("pg13").feature("str1")
         .set("ycoord", "-4.17 -2.5 -0.83 0.83 2.5 4.17 -4.17 -2.5 -0.83 0.83 2.5 4.17 -4.17 -2.5 -0.83 0.83 2.5 4.17");
    model.result("pg13").feature("str1")
         .set("zcoord", "0.083 0.083 0.083 0.083 0.083 0.083 0.25 0.25 0.25 0.25 0.25 0.25 0.417 0.417 0.417 0.417 0.417 0.417");
    model.result("pg13").feature("str1").set("pointtype", "arrow");
    model.result("pg13").run();
    model.result("pg13").create("arws1", "ArrowSurface");
    model.result("pg13").feature("arws1").set("expr", new String[]{"semi.JX", "semi.JY", "semi.JZ"});
    model.result("pg13").feature("arws1").set("descr", "\u603b\u7535\u6d41\u5bc6\u5ea6\uff0c\u8282\u70b9\u503c");
    model.result("pg13").feature("arws1").set("placement", "uniformani");
    model.result("pg13").feature("arws1").set("arrowcount", 18);
    model.result("pg13").feature("arws1").set("weight", new int[]{1, 1, 10});
    model.result("pg13").feature("arws1").set("arrowbase", "head");
    model.result("pg13").feature("arws1").set("scaleactive", true);
    model.result("pg13").feature("arws1").set("scale", "1e-6");
    model.result("pg13").feature("arws1").create("sel1", "Selection");
    model.result("pg13").feature("arws1").feature("sel1").selection().set(1);
    model.result("pg13").run();
    model.result("pg13").create("arws2", "ArrowSurface");
    model.result("pg13").feature("arws2").set("expr", new String[]{"semi.JX", "semi.JY", "semi.JZ"});
    model.result("pg13").feature("arws2").set("descr", "\u603b\u7535\u6d41\u5bc6\u5ea6\uff0c\u8282\u70b9\u503c");
    model.result("pg13").feature("arws2").set("arrowcount", 30);
    model.result("pg13").feature("arws2").set("arrowlength", "logarithmic");
    model.result("pg13").feature("arws2").set("scaleactive", true);
    model.result("pg13").feature("arws2").set("scale", "1.5e-6");
    model.result("pg13").feature("arws2").create("sel1", "Selection");
    model.result("pg13").feature("arws2").feature("sel1").selection().named("sel1");
    model.result("pg13").run();

    model
         .title("\u63d0\u53d6\u8de8\u6865\u5f00\u5c14\u6587\u7535\u963b\u5668\u7684\u6bd4\u63a5\u89e6\u7535\u963b\u7387");

    model
         .description("\u8fd9\u4e2a\u57fa\u51c6\u793a\u4f8b\u6784\u5efa\u4e24\u4e2a\u8de8\u6865\u5f00\u5c14\u6587\u7535\u963b\u5668\u6a21\u578b\uff0c\u7528\u4e8e\u63d0\u53d6\u6bd4\u63a5\u89e6\u7535\u963b\u7387\u3002\u7b2c\u4e00\u4e2a\u6a21\u578b\u4f7f\u7528\u201c\u534a\u5bfc\u4f53\u201d\u63a5\u53e3\u4e2d\u5185\u7f6e\u7684\u63a5\u89e6\u7535\u963b\u7279\u5f81\uff0c\u4ee5\u4e09\u7ef4\u6a21\u5f0f\u6a21\u62df\u8be5\u7cfb\u7edf\u3002\u53e6\u4e00\u4e2a\u6a21\u578b\u662f\u53c2\u8003\u6587\u732e\u4e2d\u5f00\u53d1\u7684\u7cfb\u7edf\u7684\u4e8c\u7ef4\u8fd1\u4f3c\uff0c\u901a\u8fc7\u8fb9\u754c\u504f\u5fae\u5206\u65b9\u7a0b\u6570\u5b66\u63a5\u53e3\u5b9e\u73b0\u3002\u5f00\u5c14\u6587\u63a5\u89e6\u7535\u963b\u968f\u63a5\u89e6\u7a97\u53e3\u5927\u5c0f\u548c\u6269\u6563\u62bd\u5934\u5bbd\u5ea6\u7684\u53d8\u5316\u7684\u8ba1\u7b97\u7ed3\u679c\u5728\u4e24\u4e2a\u6a21\u578b\u4e4b\u95f4\u5177\u6709\u5f88\u597d\u7684\u76f8\u5173\u6027\uff0c\u5e76\u4e0e\u53c2\u8003\u6587\u732e\u4e2d\u663e\u793a\u7684\u7ed3\u679c\u975e\u5e38\u543b\u5408\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();
    model.sol("sol9").clearSolutionData();
    model.sol("sol10").clearSolutionData();
    model.sol("sol11").clearSolutionData();
    model.sol("sol12").clearSolutionData();
    model.sol("sol13").clearSolutionData();
    model.sol("sol14").clearSolutionData();
    model.sol("sol15").clearSolutionData();
    model.sol("sol16").clearSolutionData();
    model.sol("sol17").clearSolutionData();

    model.label("cross_bridge_kelvin_resistor_contact_resistivity.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
