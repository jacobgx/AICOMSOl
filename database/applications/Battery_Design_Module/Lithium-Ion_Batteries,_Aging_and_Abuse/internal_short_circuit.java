/*
 * internal_short_circuit.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:24 by COMSOL 6.3.0.290. */
public class internal_short_circuit {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Battery_Design_Module\\Lithium-Ion_Batteries,_Aging_and_Abuse");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("liion", "LithiumIonBatteryMPH", "geom1");
    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");

    model.study().create("std1");
    model.study("std1").create("cdi", "CurrentDistributionInitialization");
    model.study("std1").feature("cdi").set("ftplistmethod", "manual");
    model.study("std1").feature("cdi").set("solnum", "auto");
    model.study("std1").feature("cdi").set("notsolnum", "auto");
    model.study("std1").feature("cdi").set("outputmap", new String[]{});
    model.study("std1").feature("cdi").set("ngenAUX", "1");
    model.study("std1").feature("cdi").set("goalngenAUX", "1");
    model.study("std1").feature("cdi").set("ngenAUX", "1");
    model.study("std1").feature("cdi").set("goalngenAUX", "1");
    model.study("std1").feature("cdi").setSolveFor("/physics/liion", true);
    model.study("std1").feature("cdi").setSolveFor("/physics/ht", true);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("ftplistmethod", "manual");
    model.study("std1").feature("time").set("initialtime", "0");
    model.study("std1").feature("time").set("solnum", "auto");
    model.study("std1").feature("time").set("notsolnum", "auto");
    model.study("std1").feature("time").set("outputmap", new String[]{});
    model.study("std1").feature("time").setSolveFor("/physics/liion", true);
    model.study("std1").feature("time").setSolveFor("/physics/ht", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("R_in", "5[um]", "\u7a7f\u900f\u91d1\u5c5e\u4e1d\u7684\u5185\u534a\u5f84");
    model.param().set("R_out", "L_tot*5", "\u7535\u6c60\u5355\u5143\u5916\u534a\u5f84");
    model.param().set("L_sep", "30[um]", "\u9694\u819c\u539a\u5ea6");
    model.param().set("L_neg", "60[um]", "\u8d1f\u6781\u539a\u5ea6");
    model.param().set("L_pos", "40[um]", "\u6b63\u6781\u539a\u5ea6");
    model.param().set("L_posCC", "6[um]", "\u6b63\u6781\u96c6\u6d41\u4f53\u539a\u5ea6");
    model.param().set("L_negCC", "6[um]", "\u8d1f\u6781\u96c6\u6d41\u4f53\u539a\u5ea6");
    model.param().set("L_tot", "L_negCC+L_neg+L_sep+L_pos+L_posCC", "\u7535\u6c60\u603b\u539a\u5ea6");
    model.param().set("t", "0", "\u521d\u59cb\u5316\u6b65\u9aa4\u7684\u865a\u62df t \u53d8\u91cf");
    model.param().set("t_ramp", "1e-3[s]", "\u9636\u8dc3\u51fd\u6570\u7684\u4e0a\u5347\u65f6\u95f4");
    model.param().set("sigmas_neg", "100[S/m]", "\u7535\u5bfc\u7387\uff0c\u8d1f\u6781");
    model.param().set("sigmas_pos", "100[S/m]", "\u7535\u5bfc\u7387\uff0c\u6b63\u6781");
    model.param().set("epss_neg", "0.62", "\u7535\u6781\u76f8\u4f53\u79ef\u5206\u6570\uff0c\u8d1f\u6781");
    model.param().set("epss_pos", "0.48", "\u7535\u6781\u76f8\u4f53\u79ef\u5206\u6570\uff0c\u6b63\u6781");
    model.param().set("epsl_neg", "0.31", "\u7535\u89e3\u8d28\u76f8\u4f53\u79ef\u5206\u6570\uff0c\u8d1f\u6781");
    model.param().set("epsl_pos", "0.29", "\u7535\u89e3\u8d28\u76f8\u4f53\u79ef\u5206\u6570\uff0c\u6b63\u6781");
    model.param().set("epsl_sep", "0.4", "\u9694\u819c\u5b54\u9699\u7387");
    model.param().set("rp_neg", "2e-6[m]", "\u9897\u7c92\u534a\u5f84\uff0c\u8d1f\u6781");
    model.param().set("rp_pos", "2e-6[m]", "\u9897\u7c92\u534a\u5f84\uff0c\u6b63\u6781");
    model.param()
         .set("i0ref_neg", "48.07[A/m^2]", "\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u8d1f\u6781");
    model.param()
         .set("i0ref_pos", "74.75[A/m^2]", "\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u6b63\u6781");
    model.param().set("T0", "293.15[K]", "\u521d\u59cb\u6e29\u5ea6\u548c\u5916\u90e8\u6e29\u5ea6");
    model.param().set("E_cell", "4[V]", "\u7535\u6c60\u7535\u4f4d");
    model.param().set("Cp_pos", "1000[J/kg/K]", "\u70ed\u5bb9\uff0c\u6b63\u6781");
    model.param().set("rho_pos", "2500[kg/m^3]", "\u5bc6\u5ea6\uff0c\u6b63\u6781");
    model.param().set("k_sep", "0.15[W/m/K]", "\u5bfc\u70ed\u7cfb\u6570\uff0c\u9694\u819c");
    model.param().set("Cp_sep", "1046[J/kg/K]", "\u70ed\u5bb9\uff0c\u9694\u819c");
    model.param().set("rho_sep", "940[kg/m^3]", "\u5bc6\u5ea6\uff0c\u9694\u819c");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"R_out", "L_tot"});
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "L_negCC", 0);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "L_neg", 1);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "L_sep", 2);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "L_pos", 3);
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").set("type", "open");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "R_in", 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "L_negCC+L_neg", 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "R_in", 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "L_negCC+L_neg+L_sep", 1, 1);
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "R_in*2");
    model.component("comp1").geom("geom1").feature("c1").set("angle", 90);
    model.component("comp1").geom("geom1").feature("c1").set("pos", new String[]{"0", "L_negCC+L_neg+L_sep"});
    model.component("comp1").geom("geom1").feature().duplicate("c2", "c1");
    model.component("comp1").geom("geom1").feature("c2").set("pos", new String[]{"0", "L_negCC+L_neg"});
    model.component("comp1").geom("geom1").feature("c2").set("rot", 270);
    model.component("comp1").geom("geom1").run("c2");
    model.component("comp1").geom("geom1").run("c2");
    model.component("comp1").geom("geom1").create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("pol2").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol2").set("type", "open");
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "R_in+L_sep", 0, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "R_in+L_sep", 1, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "L_tot", 1, 1);
    model.component("comp1").geom("geom1").run("pol2");

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").label("\u8d1f\u6781\u96c6\u6d41\u4f53");
    model.component("comp1").selection("sel1").set(1, 9);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u8d1f\u6781");
    model.component("comp1").selection("sel2").set(2, 3, 10);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u9694\u819c");
    model.component("comp1").selection("sel3").set(8, 11);
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").label("\u6b63\u6781");
    model.component("comp1").selection("sel4").set(5, 6, 12);
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").label("\u6b63\u6781\u96c6\u6d41\u4f53");
    model.component("comp1").selection("sel5").set(7, 13);
    model.component("comp1").selection().create("sel6", "Explicit");
    model.component("comp1").selection("sel6").label("\u7a7f\u900f\u7684\u91d1\u5c5e\u4e1d");
    model.component("comp1").selection("sel6").set(4);
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u91d1\u5c5e\u5bfc\u4f53\u57df");
    model.component("comp1").selection("uni1").set("input", new String[]{"sel1", "sel5", "sel6"});
    model.component("comp1").selection().create("sel7", "Explicit");
    model.component("comp1").selection("sel7").label("\u8d1f\u6781\u7ec8\u7aef");
    model.component("comp1").selection("sel7").geom(1);
    model.component("comp1").selection("sel7").set(30);
    model.component("comp1").selection().create("sel8", "Explicit");
    model.component("comp1").selection("sel8").label("\u6b63\u6781\u7ec8\u7aef");
    model.component("comp1").selection("sel8").geom(1);
    model.component("comp1").selection("sel8").set(34);
    model.component("comp1").selection().create("uni2", "Union");
    model.component("comp1").selection("uni2").label("\u7ec8\u7aef");
    model.component("comp1").selection("uni2").set("entitydim", 1);
    model.component("comp1").selection("uni2").set("input", new String[]{"sel7", "sel8"});

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat1").label("Aluminum");
    model.component("comp1").material("mat1").set("family", "aluminum");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23e-6[1/K]", "0", "0", "0", "23e-6[1/K]", "0", "0", "0", "23e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "70[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.33");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("l", "-250[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("m", "-330[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("n", "-350[GPa]");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup().create("linzRes", "linzRes", "Linearized resistivity");
    model.component("comp1").material("mat2").label("Copper");
    model.component("comp1").material("mat2").set("family", "copper");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"17e-6[1/K]", "0", "0", "0", "17e-6[1/K]", "0", "0", "0", "17e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "385[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "8960[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "110[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.35");
    model.component("comp1").material("mat2").propertyGroup("linzRes").label("Linearized resistivity");
    model.component("comp1").material("mat2").propertyGroup("linzRes").set("rho0", "1.72e-8[ohm*m]");
    model.component("comp1").material("mat2").propertyGroup("linzRes").set("alpha", "0.0039[1/K]");
    model.component("comp1").material("mat2").propertyGroup("linzRes").set("Tref", "298[K]");
    model.component("comp1").material("mat2").propertyGroup("linzRes").addInput("temperature");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("int1", "Interpolation");
    model.component("comp1").material("mat3").propertyGroup()
         .create("ElectrodePotential", "ElectrodePotential", "Equilibrium potential");
    model.component("comp1").material("mat3").propertyGroup()
         .create("OperationalSOC", "OperationalSOC", "Operational electrode state of charge");
    model.component("comp1").material("mat3").propertyGroup().create("ic", "ic", "Intercalation strain");
    model.component("comp1").material("mat3").propertyGroup("ic").func().create("int1", "Interpolation");
    model.component("comp1").material("mat3").propertyGroup()
         .create("EquilibriumConcentration", "EquilibriumConcentration", "Equilibrium concentration");
    model.component("comp1").material("mat3").propertyGroup()
         .create("EquilibriumPotentialWithDOCInput", "EquilibriumPotentialWithDOCInput", "Equilibrium potential (using degree of conversion as model input)");
    model.component("comp1").material("mat3").propertyGroup()
         .create("EquilibriumDegreeOfConversion", "EquilibriumDegreeOfConversion", "Equilibrium degree of conversion");
    model.component("comp1").material("mat3").propertyGroup().create("pg1", "def", "Electric conductivity");
    model.component("comp1").material("mat3").propertyGroup("pg1").func().create("int1", "Interpolation");
    model.component("comp1").material("mat3").label("NMC 111, LiNi0.33Mn0.33Co0.33O2 (Positive, Li-ion Battery)");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("funcname", "Eeq");
    model.component("comp1").material("mat3").propertyGroup("def").func("int1")
         .set("table", new String[][]{{"0", "4.44"}, 
         {"0.032", "4.34"}, 
         {"0.102", "4.23"}, 
         {"0.187", "4.13"}, 
         {"0.289", "4.025"}, 
         {"0.38", "3.945"}, 
         {"0.543", "3.835"}, 
         {"0.775", "3.71"}, 
         {"0.872", "3.62"}, 
         {"0.925", "3.51"}, 
         {"0.943", "3.42"}, 
         {"0.957", "3.30"}, 
         {"0.966", "3.165"}, 
         {"0.970", "3.02"}, 
         {"0.972", "2.90"}, 
         {"0.975", "2.688"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("fununit", new String[]{"V"});
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("argunit", new String[]{""});
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("defineinv", true);
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("funcinvname", "Eeq_inv");
    model.component("comp1").material("mat3").propertyGroup("def").set("poissonsratio", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("youngsmodulus", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("thermalconductivity", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("poissonsratio", "0.25");
    model.component("comp1").material("mat3").propertyGroup("def")
         .setPropertyInfo("poissonsratio", "Mechanical and physical properties of LiNi0.33Mn0.33Co0.33O2 (NMC),\nE Chenga, K. Hong, N. Taylor, H. Choe,\nJ. Wolfenstinec, J. Sakamotoa,\nJournal of the European Ceramic Society 37 (2017) 3213\u20133217");
    model.component("comp1").material("mat3").propertyGroup("def").set("youngsmodulus", "78[GPa]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .setPropertyInfo("youngsmodulus", "Mechanical and physical properties of LiNi0.33Mn0.33Co0.33O2 (NMC),\nE Chenga, K. Hong, N. Taylor, H. Choe,\nJ. Wolfenstinec, J. Sakamotoa,\nJournal of the European Ceramic Society 37 (2017) 3213\u20133217");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"3.6[W/(m*K)]", "0", "0", "0", "3.6[W/(m*K)]", "0", "0", "0", "3.6[W/(m*K)]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .setPropertyInfo("thermalconductivity", "Mechanical and physical properties of LiNi0.33Mn0.33Co0.33O2 (NMC),\nE Chenga, K. Hong, N. Taylor, H. Choe,\nJ. Wolfenstinec, J. Sakamotoa,\nJournal of the European Ceramic Society 37 (2017) 3213\u20133217");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"1.2e-5[1/K]", "0", "0", "0", "1.2e-5[1/K]", "0", "0", "0", "1.2e-5[1/K]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .setPropertyInfo("thermalexpansioncoefficient", "Mechanical and physical properties of LiNi0.33Mn0.33Co0.33O2 (NMC),\nE Chenga, K. Hong, N. Taylor, H. Choe,\nJ. Wolfenstinec, J. Sakamotoa,\nJournal of the European Ceramic Society 37 (2017) 3213\u20133217");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("diffusion", new String[]{"1e-14[m^2/s]", "0", "0", "0", "1e-14[m^2/s]", "0", "0", "0", "1e-14[m^2/s]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .setPropertyInfo("diffusion", "Jing Ying Ko et al, J. Electrochem. Soc., 166, A2939");
    model.component("comp1").material("mat3").propertyGroup("def").set("csmax", "49000[mol/m^3]");
    model.component("comp1").material("mat3").propertyGroup("def").descr("csmax", "");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential").label("Equilibrium potential");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential").identifier("eeq");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential")
         .set("Eeq", "def.Eeq(doc)+dEeqdT*(T-298[K])");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential")
         .setPropertyInfo("Eeq", "W. Zheng, M. Shui, J. Shu, S. Gao, D. Xu, L. Chen, L. Feng and Y. Ren, \" GITT studies on oxide cathode LiNi1/3Co1/3Mn1/3O2 synthesized by citric acid assisted high-energy ball milling\", Bull. Mater. Sci., vol. 36, p. A495, 2013");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential")
         .set("dEeqdT", "-10[J/mol/K]/F_const");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential")
         .setPropertyInfo("dEeqdT", "V Viswanathan, D Choi, D Wang, W Xu, S Towne, R Williford, JG Zhang, J Liu and Z Yang \"Effect of entropy change on lithium intercalation in cathodes and anodes on Li-ion battery thermal management\", Journal of Power Sources 195 (2010) 3720-3729");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential").set("cEeqref", "def.csmax");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential")
         .setPropertyInfo("cEeqref", "W. Zheng, M. Shui, J. Shu, S. Gao, D. Xu, L. Chen, L. Feng and Y. Ren, \" GITT studies on oxide cathode LiNi1/3Co1/3Mn1/3O2 synthesized by citric acid assisted high-energy ball milling\", Bull. Mater. Sci., vol. 36, p. A495, 2013");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential").set("doc", "c/cEeqref");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential")
         .descr("doc", "Degree of conversion (state of lithiation)");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential").addInput("concentration");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential").addInput("temperature");
    model.component("comp1").material("mat3").propertyGroup("OperationalSOC")
         .label("Operational electrode state of charge");
    model.component("comp1").material("mat3").propertyGroup("OperationalSOC").identifier("opsoc");
    model.component("comp1").material("mat3").propertyGroup("OperationalSOC").set("socmax", "def.Eeq_inv(E_min)");
    model.component("comp1").material("mat3").propertyGroup("OperationalSOC").set("socmin", "def.Eeq_inv(E_max)");
    model.component("comp1").material("mat3").propertyGroup("OperationalSOC").set("E_max", "4.4[V]");
    model.component("comp1").material("mat3").propertyGroup("OperationalSOC").set("E_min", "3.3[V]");
    model.component("comp1").material("mat3").propertyGroup("ic").label("Intercalation strain");
    model.component("comp1").material("mat3").propertyGroup("ic").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat3").propertyGroup("ic").func("int1").set("funcname", "dVOLdSOL");
    model.component("comp1").material("mat3").propertyGroup("ic").func("int1")
         .set("table", new String[][]{{"1", "0"}, 
         {"0.9260263416001121", "-0.010256410256411108"}, 
         {"0.8670351688384477", "-0.1948717948717955"}, 
         {"0.8113086731119519", "-0.27692307692307727"}, 
         {"0.7506669468964551", "-0.37948717948718036"}, 
         {"0.6949460557657279", "-0.502564102564103"}, 
         {"0.628563822334314", "-0.5846153846153856"}, 
         {"0.55562421185372", "-0.6666666666666674"}, 
         {"0.501531455793751", "-0.7076923076923083"}, 
         {"0.4441600112091916", "-0.7487179487179496"}, 
         {"0.3851716407454113", "-0.953846153846154"}, 
         {"0.3278338237354632", "-1.241025641025642"}, 
         {"0.2737943113352951", "-1.671794871794872"}, 
         {"0.24269440941572107", "-2.0205128205128213"}});
    model.component("comp1").material("mat3").propertyGroup("ic").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat3").propertyGroup("ic").func("int1").set("fununit", new String[]{"%"});
    model.component("comp1").material("mat3").propertyGroup("ic").func("int1").set("argunit", new String[]{"1"});
    model.component("comp1").material("mat3").propertyGroup("ic").identifier("is");
    model.component("comp1").material("mat3").propertyGroup("ic").set("dvol", "dVOLdSOL(c/def.csmax)");
    model.component("comp1").material("mat3").propertyGroup("ic")
         .setPropertyInfo("dvol", "R. Koerver and others, \u201cChemo-mechanical expansion of lithium electrode materials \u2014 on the route to mechanically optimized all-solid-state batteries,\u201d Energy Environ. Sci., vol. 11, pp. 2142\u20132158, 201");
    model.component("comp1").material("mat3").propertyGroup("ic").addInput("concentration");
    model.component("comp1").material("mat3").propertyGroup("EquilibriumConcentration")
         .label("Equilibrium concentration");
    model.component("comp1").material("mat3").propertyGroup("EquilibriumConcentration")
         .set("csEq", "def.csmax*def.Eeq_inv(V)");
    model.component("comp1").material("mat3").propertyGroup("EquilibriumConcentration")
         .addInput("electricpotential");
    model.component("comp1").material("mat3").propertyGroup("EquilibriumPotentialWithDOCInput")
         .label("Equilibrium potential (using degree of conversion as model input)");
    model.component("comp1").material("mat3").propertyGroup("EquilibriumPotentialWithDOCInput")
         .set("Eeq", "def.Eeq(doc)+dEeqdT*(T-298[K])");
    model.component("comp1").material("mat3").propertyGroup("EquilibriumPotentialWithDOCInput")
         .set("dEeqdT", "-10[J/mol/K]/F_const");
    model.component("comp1").material("mat3").propertyGroup("EquilibriumPotentialWithDOCInput")
         .addInput("degreeofconversion");
    model.component("comp1").material("mat3").propertyGroup("EquilibriumPotentialWithDOCInput")
         .addInput("temperature");
    model.component("comp1").material("mat3").propertyGroup("EquilibriumDegreeOfConversion")
         .label("Equilibrium degree of conversion");
    model.component("comp1").material("mat3").propertyGroup("EquilibriumDegreeOfConversion")
         .set("docEq", "def.Eeq_inv(V)");
    model.component("comp1").material("mat3").propertyGroup("EquilibriumDegreeOfConversion")
         .addInput("electricpotential");
    model.component("comp1").material("mat3").propertyGroup("pg1").func("int1").set("source", "file");
    model.component("comp1").material("mat3").propertyGroup("pg1").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat3").propertyGroup("pg1").func("int1").set("importedname", "NMC_333.txt");
    model.component("comp1").material("mat3").propertyGroup("pg1").func("int1").set("importeddim", "2D");
    model.component("comp1").material("mat3").propertyGroup("pg1").func("int1")
         .set("funcnametable", new String[][]{{"log_sigmas", "1"}});
    model.component("comp1").material("mat3").propertyGroup("pg1").func("int1").set("filecolumns", 3);
    model.component("comp1").material("mat3").propertyGroup("pg1").func("int1")
         .set("columnKeys", new String[]{"col1", "col2", "col3"});
    model.component("comp1").material("mat3").propertyGroup("pg1").func("int1")
         .set("columnType", new String[]{"col1", "arg", "col2", "arg", "col3", "value"});
    model.component("comp1").material("mat3").propertyGroup("pg1").func("int1")
         .set("funcnames", new String[]{"col1", "int1", "col2", "int1", "col3", "log_sigmas"});
    model.component("comp1").material("mat3").propertyGroup("pg1").func("int1").set("fununit", new String[]{""});
    model.component("comp1").material("mat3").propertyGroup("pg1").func("int1")
         .set("argunit", new String[]{"1", "1/K"});
    model.component("comp1").material("mat3").propertyGroup("pg1").func("int1").set("sourcetype", "model");
    model.component("comp1").material("mat3").propertyGroup("pg1")
         .set("electricconductivity", new String[]{"10^log_sigmas(x,1000/T)[S/cm]", "0", "0", "0", "10^log_sigmas(x,1000/T)[S/cm]", "0", "0", "0", "10^log_sigmas(x,1000/T)[S/cm]"});
    model.component("comp1").material("mat3").propertyGroup("pg1")
         .setPropertyInfo("electricconductivity", "Ruhul Amin and Yet-Ming Chiang 2016 J. Electrochem. Soc. 163 A1512");
    model.component("comp1").material("mat3").propertyGroup("pg1").set("x", "min(max(1-c/def.csmax,0),1)");
    model.component("comp1").material("mat3").propertyGroup("pg1").descr("x", "Degree of delithiation");
    model.component("comp1").material("mat3").propertyGroup("pg1").addInput("temperature");
    model.component("comp1").material("mat3").propertyGroup("pg1").addInput("concentration");
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").propertyGroup("def").func().create("int1", "Interpolation");
    model.component("comp1").material("mat4").propertyGroup("def").func().create("int2", "Interpolation");
    model.component("comp1").material("mat4").propertyGroup("def").func().create("int3", "Interpolation");
    model.component("comp1").material("mat4").propertyGroup("def").func().create("int4", "Interpolation");
    model.component("comp1").material("mat4").propertyGroup()
         .create("ElectrodePotential", "ElectrodePotential", "Equilibrium potential");
    model.component("comp1").material("mat4").propertyGroup()
         .create("OperationalSOC", "OperationalSOC", "Operational electrode state of charge");
    model.component("comp1").material("mat4").propertyGroup().create("ic", "ic", "Intercalation strain");
    model.component("comp1").material("mat4").propertyGroup("ic").func().create("int1", "Interpolation");
    model.component("comp1").material("mat4").propertyGroup()
         .create("EquilibriumConcentration", "EquilibriumConcentration", "Equilibrium concentration");
    model.component("comp1").material("mat4").propertyGroup()
         .create("EquilibriumPotentialWithDOCInput", "EquilibriumPotentialWithDOCInput", "Equilibrium potential (using degree of conversion as model input)");
    model.component("comp1").material("mat4").propertyGroup()
         .create("EquilibriumDegreeOfConversion", "EquilibriumDegreeOfConversion", "Equilibrium degree of conversion");
    model.component("comp1").material("mat4").label("Graphite, LixC6 MCMB (Negative, Li-ion Battery)");
    model.component("comp1").material("mat4").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat4").propertyGroup("def").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat4").propertyGroup("def").func("int1").set("funcname", "E_int");
    model.component("comp1").material("mat4").propertyGroup("def").func("int1")
         .set("table", new String[][]{{"0", "32.47"}, {"0.333", "28.56"}, {"0.5", "58.06"}, {"1", "108.67"}});
    model.component("comp1").material("mat4").propertyGroup("def").func("int1").set("fununit", new String[]{"GPa"});
    model.component("comp1").material("mat4").propertyGroup("def").func("int1").set("argunit", new String[]{"1"});
    model.component("comp1").material("mat4").propertyGroup("def").func("int2").label("Interpolation 2");
    model.component("comp1").material("mat4").propertyGroup("def").func("int2")
         .set("funcnametable", new String[][]{{"int1", "1"}});
    model.component("comp1").material("mat4").propertyGroup("def").func("int2").set("funcname", "nu_int");
    model.component("comp1").material("mat4").propertyGroup("def").func("int2")
         .set("table", new String[][]{{"0", "0.32"}, {"0.333", "0.39"}, {"0.5", "0.34"}, {"1", "0.24"}});
    model.component("comp1").material("mat4").propertyGroup("def").func("int2").set("fununit", new String[]{""});
    model.component("comp1").material("mat4").propertyGroup("def").func("int3").label("Interpolation 3");
    model.component("comp1").material("mat4").propertyGroup("def").func("int3")
         .set("funcnametable", new String[][]{{"int1", "1"}});
    model.component("comp1").material("mat4").propertyGroup("def").func("int3").set("funcname", "Eeq");
    model.component("comp1").material("mat4").propertyGroup("def").func("int3")
         .set("table", new String[][]{{"0", "2.781186612"}, 
         {"0.01", "1.520893224"}, 
         {"0.02", "0.893922607"}, 
         {"0.03", "0.581284406"}, 
         {"0.04", "0.42452844"}, 
         {"0.05", "0.344895805"}, 
         {"0.06", "0.303146342"}, 
         {"0.07", "0.279578072"}, 
         {"0.08", "0.264093089"}, 
         {"0.09", "0.251347845"}, 
         {"0.1", "0.238588379"}, 
         {"0.11", "0.224803164"}, 
         {"0.12", "0.210294358"}, 
         {"0.13", "0.196408586"}, 
         {"0.14", "0.184624188"}, 
         {"0.15", "0.175188157"}, 
         {"0.16", "0.167373311"}, 
         {"0.17", "0.160452107"}, 
         {"0.18", "0.154025412"}, 
         {"0.19", "0.147948522"}, 
         {"0.2", "0.142214997"}, 
         {"0.21", "0.13688271"}, 
         {"0.22", "0.132033114"}, 
         {"0.23", "0.127747573"}, 
         {"0.24", "0.124091616"}, 
         {"0.25", "0.121103387"}, 
         {"0.26", "0.11878567"}, 
         {"0.27", "0.117102317"}, 
         {"0.28", "0.115980205"}, 
         {"0.29", "0.115317054"}, 
         {"0.3", "0.114993965"}, 
         {"0.31", "0.114890105"}, 
         {"0.32", "0.114886278"}, 
         {"0.33", "0.114884619"}, 
         {"0.34", "0.114873068"}, 
         {"0.35", "0.114824904"}, 
         {"0.36", "0.114644725"}, 
         {"0.37", "0.114372614"}, 
         {"0.38", "0.114017954"}, 
         {"0.39", "0.11359371"}, 
         {"0.4", "0.11311133"}, 
         {"0.41", "0.112575849"}, 
         {"0.42", "0.111980245"}, 
         {"0.43", "0.111297682"}, 
         {"0.44", "0.110470149"}, 
         {"0.45", "0.109393081"}, 
         {"0.46", "0.107900592"}, 
         {"0.47", "0.10576964"}, 
         {"0.48", "0.102783317"}, 
         {"0.49", "0.09889031"}, 
         {"0.5", "0.094391564"}, 
         {"0.51", "0.089921069"}, 
         {"0.52", "0.086112415"}, 
         {"0.53", "0.083265315"}, 
         {"0.54", "0.081326247"}, 
         {"0.55", "0.080074892"}, 
         {"0.56", "0.07928329"}, 
         {"0.57", "0.078778765"}, 
         {"0.58", "0.078447703"}, 
         {"0.59", "0.078220432"}, 
         {"0.6", "0.078055641"}, 
         {"0.61", "0.077929111"}, 
         {"0.62", "0.077826563"}, 
         {"0.63", "0.077739397"}, 
         {"0.64", "0.077662227"}, 
         {"0.65", "0.077591472"}, 
         {"0.66", "0.077524557"}, 
         {"0.67", "0.077459463"}, 
         {"0.68", "0.077394455"}, 
         {"0.69", "0.077327934"}, 
         {"0.7", "0.077258337"}, 
         {"0.71", "0.077184077"}, 
         {"0.72", "0.077103499"}, 
         {"0.73", "0.077014851"}, 
         {"0.74", "0.076916258"}, 
         {"0.75", "0.07680571"}, 
         {"0.76", "0.07668104"}, 
         {"0.77", "0.07653992"}, 
         {"0.78", "0.076379839"}, 
         {"0.79", "0.076198086"}, 
         {"0.8", "0.075991699"}, 
         {"0.81", "0.075757371"}, 
         {"0.82", "0.075491288"}, 
         {"0.83", "0.075188813"}, 
         {"0.84", "0.07484398"}, 
         {"0.85", "0.074448647"}, 
         {"0.86", "0.07399118"}, 
         {"0.87", "0.073454466"}, 
         {"0.88", "0.072812991"}, 
         {"0.89", "0.072028722"}, 
         {"0.9", "0.071045433"}, 
         {"0.91", "0.069780996"}, 
         {"0.92", "0.068116222"}, 
         {"0.93", "0.065874599"}, 
         {"0.94", "0.062770873"}, 
         {"0.95", "0.058253898"}, 
         {"0.96", "0.051075794"}, 
         {"0.97", "0.038790069"}, 
         {"0.98", "0.020172191"}});

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").material("mat4").propertyGroup("def").func("int3").set("extrap", "linear");
    model.component("comp1").material("mat4").propertyGroup("def").func("int3").set("fununit", new String[]{"V"});
    model.component("comp1").material("mat4").propertyGroup("def").func("int3").set("argunit", new String[]{""});
    model.component("comp1").material("mat4").propertyGroup("def").func("int3").set("defineinv", true);
    model.component("comp1").material("mat4").propertyGroup("def").func("int3").set("funcinvname", "Eeq_inv");
    model.component("comp1").material("mat4").propertyGroup("def").func("int4").label("Interpolation 4");
    model.component("comp1").material("mat4").propertyGroup("def").func("int4")
         .set("funcnametable", new String[][]{{"int2", "1"}});
    model.component("comp1").material("mat4").propertyGroup("def").func("int4").set("funcname", "dEeqdT");
    model.component("comp1").material("mat4").propertyGroup("def").func("int4")
         .set("table", new String[][]{{"0", "3.0e-4"}, 
         {"0.17", "0"}, 
         {"0.24", "-6e-5"}, 
         {"0.28", "-1.6e-4"}, 
         {"0.5", "-1.6e-4"}, 
         {"0.54", "-9e-5"}, 
         {"0.71", "-9e-5"}, 
         {"0.85", "-1.0e-4"}, 
         {"1.0", "-1.2e-4"}});
    model.component("comp1").material("mat4").propertyGroup("def").func("int4").set("fununit", new String[]{"V/K"});
    model.component("comp1").material("mat4").propertyGroup("def").func("int4").set("argunit", new String[]{""});
    model.component("comp1").material("mat4").propertyGroup("def").set("youngsmodulus", "");
    model.component("comp1").material("mat4").propertyGroup("def").set("poissonsratio", "");
    model.component("comp1").material("mat4").propertyGroup("def").set("youngsmodulus", "E_int(c/csmax)");
    model.component("comp1").material("mat4").propertyGroup("def")
         .setPropertyInfo("youngsmodulus", "Yue Qi et al 2010 J. Electrochem. Soc. 157 A558");
    model.component("comp1").material("mat4").propertyGroup("def").set("poissonsratio", "nu_int(c/csmax)");
    model.component("comp1").material("mat4").propertyGroup("def")
         .setPropertyInfo("poissonsratio", "Yue Qi et al 2010 J. Electrochem. Soc. 157 A558");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("electricconductivity", new String[]{"100[S/m]", "0", "0", "0", "100[S/m]", "0", "0", "0", "100[S/m]"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .setPropertyInfo("electricconductivity", "V. Srinivasan, and J. Newman, \u201cDesign and Optimization of a Natural Graphite/Iron Phosphate Lithium Ion Cell,\u201d J. Electrochem. Soc., vol. 151, p. 1530, 2004.");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("diffusion", new String[]{"1.4523e-13*exp(68025.7/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))[m^2/s]", "0", "0", "0", "1.4523e-13*exp(68025.7/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))[m^2/s]", "0", "0", "0", "1.4523e-13*exp(68025.7/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))[m^2/s]"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .setPropertyInfo("diffusion", "K. Kumaresan, G. Sikha, and R. E. White, \u201cThermal Model for a Li-Ion Cell,\u201d J. Electrochem. Soc., vol. 155, p. A164, 2008.");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1[W/(m*K)]", "0", "0", "0", "1[W/(m*K)]", "0", "0", "0", "1[W/(m*K)]"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .setPropertyInfo("thermalconductivity", "S. Chen, C. Wan, and Y. Wang, J. Power Sources, 140, 111 (2005).");
    model.component("comp1").material("mat4").propertyGroup("def").set("heatcapacity", "750[J/(kg*K)]");
    model.component("comp1").material("mat4").propertyGroup("def")
         .setPropertyInfo("heatcapacity", "SI Chemical Data, John Wiley & Sons, 1994");
    model.component("comp1").material("mat4").propertyGroup("def").set("density", "2300[kg/m^3]");
    model.component("comp1").material("mat4").propertyGroup("def")
         .setPropertyInfo("density", "SI Chemical Data, John Wiley & Sons, 1994");
    model.component("comp1").material("mat4").propertyGroup("def").set("csmax", "31507[mol/m^3]");
    model.component("comp1").material("mat4").propertyGroup("def").descr("csmax", "");
    model.component("comp1").material("mat4").propertyGroup("def").set("T_ref", "318[K]");
    model.component("comp1").material("mat4").propertyGroup("def").descr("T_ref", "");
    model.component("comp1").material("mat4").propertyGroup("def").set("T2", "min(393.15,max(T,223.15))");
    model.component("comp1").material("mat4").propertyGroup("def").descr("T2", "");
    model.component("comp1").material("mat4").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat4").propertyGroup("def").addInput("concentration");
    model.component("comp1").material("mat4").propertyGroup("ElectrodePotential").label("Equilibrium potential");
    model.component("comp1").material("mat4").propertyGroup("ElectrodePotential").identifier("eeq");
    model.component("comp1").material("mat4").propertyGroup("ElectrodePotential")
         .set("Eeq", "def.Eeq(doc)+def.dEeqdT(doc)*(T-298[K])");
    model.component("comp1").material("mat4").propertyGroup("ElectrodePotential")
         .setPropertyInfo("Eeq", "D. P Karthikeyan, G. Sikha, and R. E. White, \u201cThermodynamic model development for lithium intercalation electrodes,\u201d J. Power Sources, vol. 185, p. 1398, 2008.");
    model.component("comp1").material("mat4").propertyGroup("ElectrodePotential").set("dEeqdT", "def.dEeqdT(doc)");
    model.component("comp1").material("mat4").propertyGroup("ElectrodePotential")
         .setPropertyInfo("dEeqdT", "K. E. Thomas, and J. Newman, \u201cHeats of mixing and of entropy in porous insertion electrodes,\u201d J. Power Sources., vol. 119-121, p. 844, 2003.");
    model.component("comp1").material("mat4").propertyGroup("ElectrodePotential").set("cEeqref", "def.csmax");
    model.component("comp1").material("mat4").propertyGroup("ElectrodePotential").set("doc", "c/cEeqref");
    model.component("comp1").material("mat4").propertyGroup("ElectrodePotential")
         .descr("doc", "Degree of conversion (state of lithiation)");
    model.component("comp1").material("mat4").propertyGroup("ElectrodePotential").addInput("concentration");
    model.component("comp1").material("mat4").propertyGroup("ElectrodePotential").addInput("temperature");
    model.component("comp1").material("mat4").propertyGroup("OperationalSOC")
         .label("Operational electrode state of charge");
    model.component("comp1").material("mat4").propertyGroup("OperationalSOC").identifier("opsoc");
    model.component("comp1").material("mat4").propertyGroup("OperationalSOC").set("socmax", "def.Eeq_inv(E_min)");
    model.component("comp1").material("mat4").propertyGroup("OperationalSOC").set("socmin", "def.Eeq_inv(E_max)");
    model.component("comp1").material("mat4").propertyGroup("OperationalSOC").set("E_max", "1[V]");
    model.component("comp1").material("mat4").propertyGroup("OperationalSOC").set("E_min", "0.075[V]");
    model.component("comp1").material("mat4").propertyGroup("ic").label("Intercalation strain");
    model.component("comp1").material("mat4").propertyGroup("ic").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat4").propertyGroup("ic").func("int1").set("funcname", "dVOLdSOL");
    model.component("comp1").material("mat4").propertyGroup("ic").func("int1")
         .set("table", new String[][]{{"0", "0"}, 
         {"0.006802721088435382", "0.12500000000000178"}, 
         {"0.06316812439261421", "1.2736486486486491"}, 
         {"0.11175898931000966", "2.523648648648649"}, 
         {"0.17978620019436342", "3.5709459459459474"}, 
         {"0.2400388726919339", "4.449324324324325"}, 
         {"0.2905733722060252", "5.192567567567568"}, 
         {"0.3566569484936831", "5.66554054054054"}, 
         {"0.4188532555879494", "5.969594594594595"}, 
         {"0.48104956268221566", "6.10472972972973"}, 
         {"0.5432458697764819", "6.173648648648647"}, 
         {"0.58600583090379", "6.306081081081081"}, 
         {"0.6112730806608356", "7.726351351351352"}, 
         {"0.6443148688046647", "8.570945945945946"}, 
         {"0.694849368318756", "9.449324324324323"}, 
         {"0.7414965986394557", "10.29391891891892"}, 
         {"0.7764820213799805", "10.902027027027025"}, 
         {"0.8231292517006802", "11.543918918918918"}, 
         {"0.8542274052478133", "12.152027027027026"}, 
         {"0.8833819241982507", "12.827702702702702"}, 
         {"0.9183673469387755", "12.996621621621621"}, 
         {"0.9494655004859086", "13.16554054054054"}});
    model.component("comp1").material("mat4").propertyGroup("ic").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat4").propertyGroup("ic").func("int1").set("fununit", new String[]{"%"});
    model.component("comp1").material("mat4").propertyGroup("ic").func("int1").set("argunit", new String[]{"1"});
    model.component("comp1").material("mat4").propertyGroup("ic").identifier("is");
    model.component("comp1").material("mat4").propertyGroup("ic").set("dvol", "dVOLdSOL(c/def.csmax)");
    model.component("comp1").material("mat4").propertyGroup("ic")
         .setPropertyInfo("dvol", "S. Schweidler, L. de Biasi, A. Schiele, P. Hartmann, T. Brezesinski and J. Janek, \"Volume Changes of Graphite Anodes Revisited: A Combined Operando X-Ray Diffraction and In Situ Pressure Analysis Study\", J. Phys. Chem. C, 2018, 122, 8829\u20138835");
    model.component("comp1").material("mat4").propertyGroup("ic").addInput("concentration");
    model.component("comp1").material("mat4").propertyGroup("EquilibriumConcentration")
         .label("Equilibrium concentration");
    model.component("comp1").material("mat4").propertyGroup("EquilibriumConcentration")
         .set("csEq", "def.csmax*def.Eeq_inv(V)");
    model.component("comp1").material("mat4").propertyGroup("EquilibriumConcentration")
         .addInput("electricpotential");
    model.component("comp1").material("mat4").propertyGroup("EquilibriumPotentialWithDOCInput")
         .label("Equilibrium potential (using degree of conversion as model input)");
    model.component("comp1").material("mat4").propertyGroup("EquilibriumPotentialWithDOCInput")
         .set("Eeq", "def.Eeq(doc)+def.dEeqdT(doc)*(T-298[K])");
    model.component("comp1").material("mat4").propertyGroup("EquilibriumPotentialWithDOCInput")
         .set("dEeqdT", "def.dEeqdT(doc)");
    model.component("comp1").material("mat4").propertyGroup("EquilibriumPotentialWithDOCInput")
         .addInput("degreeofconversion");
    model.component("comp1").material("mat4").propertyGroup("EquilibriumPotentialWithDOCInput")
         .addInput("temperature");
    model.component("comp1").material("mat4").propertyGroup("EquilibriumDegreeOfConversion")
         .label("Equilibrium degree of conversion");
    model.component("comp1").material("mat4").propertyGroup("EquilibriumDegreeOfConversion")
         .set("docEq", "def.Eeq_inv(V)");
    model.component("comp1").material("mat4").propertyGroup("EquilibriumDegreeOfConversion")
         .addInput("electricpotential");
    model.component("comp1").material().create("mat5", "Common");
    model.component("comp1").material("mat5").propertyGroup("def").func().create("int1", "Interpolation");
    model.component("comp1").material("mat5").propertyGroup()
         .create("ElectrolyteConductivity", "ElectrolyteConductivity", "Electrolyte conductivity");
    model.component("comp1").material("mat5").propertyGroup("ElectrolyteConductivity").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat5").propertyGroup()
         .create("SpeciesProperties", "SpeciesProperties", "Species properties");
    model.component("comp1").material("mat5").propertyGroup("SpeciesProperties").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat5").propertyGroup("SpeciesProperties").func()
         .create("int2", "Interpolation");
    model.component("comp1").material("mat5").propertyGroup()
         .create("ElectrolyteSaltConcentration", "ElectrolyteSaltConcentration", "Electrolyte salt concentration");
    model.component("comp1").material("mat5").label("LiPF6 in 3:7 EC:EMC (Liquid, Li-ion Battery)");
    model.component("comp1").material("mat5").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat5").propertyGroup("def").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat5").propertyGroup("def").func("int1").set("funcname", "DL_int1");
    model.component("comp1").material("mat5").propertyGroup("def").func("int1")
         .set("table", new String[][]{{"200", "3.9e-10/(1-200*59e-6)"}, 
         {"500", "4.12e-10/(1-500*59e-6)"}, 
         {"800", "4e-10/(1-800*59e-6)"}, 
         {"1000", "3.8e-10/(1-1000*59e-6)"}, 
         {"1200", "3.50e-10/(1-1200*59e-6)"}, 
         {"1600", "2.68e-10/(1-1600*59e-6)"}, 
         {"2000", "1.9e-10/(1-2000*59e-6)"}});
    model.component("comp1").material("mat5").propertyGroup("def").func("int1").set("interp", "piecewisecubic");
    model.component("comp1").material("mat5").propertyGroup("def").func("int1")
         .set("fununit", new String[]{"m^2/s"});
    model.component("comp1").material("mat5").propertyGroup("def").func("int1").set("argunit", new String[]{""});
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("diffusion", new String[]{"DL_int1(c/1[mol/m^3])*exp(16500/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))", "0", "0", "0", "DL_int1(c/1[mol/m^3])*exp(16500/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))", "0", "0", "0", "DL_int1(c/1[mol/m^3])*exp(16500/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))"});
    model.component("comp1").material("mat5").propertyGroup("def")
         .setPropertyInfo("diffusion", "T. G. Zavalis, M. Behm, and G. Lindbergh, \"Investigations of Short-Circuit Scenarios in a Lithium-Ion Battery Cell,\" J. Electrochem. Soc., vol. 159, p. A848, 2012.");
    model.component("comp1").material("mat5").propertyGroup("def").set("T_ref", "298[K]");
    model.component("comp1").material("mat5").propertyGroup("def").descr("T_ref", "");
    model.component("comp1").material("mat5").propertyGroup("def").set("T2", "min(393.15,max(T,223.15))");
    model.component("comp1").material("mat5").propertyGroup("def").descr("T2", "");
    model.component("comp1").material("mat5").propertyGroup("def").addInput("concentration");
    model.component("comp1").material("mat5").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat5").propertyGroup("ElectrolyteConductivity")
         .label("Electrolyte conductivity");
    model.component("comp1").material("mat5").propertyGroup("ElectrolyteConductivity").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat5").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("funcname", "sigmal_int1");
    model.component("comp1").material("mat5").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("table", new String[][]{{"0", "1e-6"}, 
         {"200", "0.455"}, 
         {"500", "0.783"}, 
         {"800", "0.935"}, 
         {"1000", "0.95"}, 
         {"1200", "0.927"}, 
         {"1600", "0.78"}, 
         {"2000", "0.60"}, 
         {"2200", "0.515"}});
    model.component("comp1").material("mat5").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("interp", "piecewisecubic");
    model.component("comp1").material("mat5").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("fununit", new String[]{"S/m"});
    model.component("comp1").material("mat5").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("argunit", new String[]{""});
    model.component("comp1").material("mat5").propertyGroup("ElectrolyteConductivity")
         .set("sigmal", new String[]{"sigmal_int1(c/1[mol/m^3])*exp(4000/8.314*(1/(T_ref2/1[K])-1/(T3/1[K])))", "0", "0", "0", "sigmal_int1(c/1[mol/m^3])*exp(4000/8.314*(1/(T_ref2/1[K])-1/(T3/1[K])))", "0", "0", "0", "sigmal_int1(c/1[mol/m^3])*exp(4000/8.314*(1/(T_ref2/1[K])-1/(T3/1[K])))"});
    model.component("comp1").material("mat5").propertyGroup("ElectrolyteConductivity")
         .setPropertyInfo("sigmal", "T. G. Zavalis, M. Behm, and G. Lindbergh, \"Investigations of Short-Circuit Scenarios in a Lithium-Ion Battery Cell,\" J. Electrochem. Soc., vol. 159, p. A848, 2012.");
    model.component("comp1").material("mat5").propertyGroup("ElectrolyteConductivity").set("T_ref2", "298[K]");
    model.component("comp1").material("mat5").propertyGroup("ElectrolyteConductivity").descr("T_ref2", "");
    model.component("comp1").material("mat5").propertyGroup("ElectrolyteConductivity")
         .set("T3", "min(393.15,max(T,223.15))");
    model.component("comp1").material("mat5").propertyGroup("ElectrolyteConductivity").descr("T3", "");
    model.component("comp1").material("mat5").propertyGroup("ElectrolyteConductivity").addInput("concentration");
    model.component("comp1").material("mat5").propertyGroup("ElectrolyteConductivity").addInput("temperature");
    model.component("comp1").material("mat5").propertyGroup("SpeciesProperties").label("Species properties");
    model.component("comp1").material("mat5").propertyGroup("SpeciesProperties").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat5").propertyGroup("SpeciesProperties").func("int1")
         .set("funcname", "transpNm_int1");
    model.component("comp1").material("mat5").propertyGroup("SpeciesProperties").func("int1")
         .set("table", new String[][]{{"200", "0.37"}, 
         {"500", "0.322"}, 
         {"800", "0.27"}, 
         {"1000", "0.251"}, 
         {"1200", "0.248"}, 
         {"1600", "0.236"}, 
         {"2000", "0.11"}});
    model.component("comp1").material("mat5").propertyGroup("SpeciesProperties").func("int1")
         .set("interp", "piecewisecubic");
    model.component("comp1").material("mat5").propertyGroup("SpeciesProperties").func("int1")
         .set("fununit", new String[]{""});
    model.component("comp1").material("mat5").propertyGroup("SpeciesProperties").func("int1")
         .set("argunit", new String[]{""});
    model.component("comp1").material("mat5").propertyGroup("SpeciesProperties").func("int2")
         .label("Interpolation 2");
    model.component("comp1").material("mat5").propertyGroup("SpeciesProperties").func("int2")
         .set("funcname", "actdep_int1");
    model.component("comp1").material("mat5").propertyGroup("SpeciesProperties").func("int2")
         .set("table", new String[][]{{"200", "0"}, 
         {"500", "0.29"}, 
         {"800", "0.695"}, 
         {"1000", "1"}, 
         {"1200", "1.32"}, 
         {"1600", "2.07"}, 
         {"2000", "2.50"}});
    model.component("comp1").material("mat5").propertyGroup("SpeciesProperties").func("int2")
         .set("interp", "piecewisecubic");
    model.component("comp1").material("mat5").propertyGroup("SpeciesProperties").func("int2")
         .set("fununit", new String[]{""});
    model.component("comp1").material("mat5").propertyGroup("SpeciesProperties").func("int2")
         .set("argunit", new String[]{""});
    model.component("comp1").material("mat5").propertyGroup("SpeciesProperties")
         .set("transpNum", "transpNm_int1(c/1[mol/m^3])");
    model.component("comp1").material("mat5").propertyGroup("SpeciesProperties")
         .setPropertyInfo("transpNum", "A. Nyman, M. Behm, and G. Lindbergh, \u201cElectrochemical characterisation and modelling of the mass transport phenomena in LiPF6-EC-EMC,\u201d Electrochim. Acta, vol. 53, p. 6356, 2008.");
    model.component("comp1").material("mat5").propertyGroup("SpeciesProperties")
         .set("fcl", "actdep_int1(c/1[mol/m^3])*exp(-1000/8.314*(1/(T_ref3/1[K])-1/(T4/1[K])))");
    model.component("comp1").material("mat5").propertyGroup("SpeciesProperties")
         .setPropertyInfo("fcl", "T. G. Zavalis, M. Behm, and G. Lindbergh, \"Investigations of Short-Circuit Scenarios in a Lithium-Ion Battery Cell,\" J. Electrochem. Soc., vol. 159, p. A848, 2012.");
    model.component("comp1").material("mat5").propertyGroup("SpeciesProperties")
         .set("T4", "min(393.15,max(T,223.15))");
    model.component("comp1").material("mat5").propertyGroup("SpeciesProperties").descr("T4", "");
    model.component("comp1").material("mat5").propertyGroup("SpeciesProperties").set("T_ref3", "298[K]");
    model.component("comp1").material("mat5").propertyGroup("SpeciesProperties").descr("T_ref3", "");
    model.component("comp1").material("mat5").propertyGroup("SpeciesProperties").addInput("concentration");
    model.component("comp1").material("mat5").propertyGroup("SpeciesProperties").addInput("temperature");
    model.component("comp1").material("mat5").propertyGroup("ElectrolyteSaltConcentration")
         .label("Electrolyte salt concentration");
    model.component("comp1").material("mat5").propertyGroup("ElectrolyteSaltConcentration").identifier("cElsalt");
    model.component("comp1").material("mat5").propertyGroup("ElectrolyteSaltConcentration")
         .set("cElsalt", "1200[mol/m^3]");
    model.component("comp1").material().create("mat6", "Common");
    model.component("comp1").material("mat6").propertyGroup()
         .create("ElectrodePotential", "ElectrodePotential", "Equilibrium potential");
    model.component("comp1").material("mat6").label("Lithium Metal, Li (Negative, Li-ion Battery)");
    model.component("comp1").material("mat6").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat6").propertyGroup("def").set("youngsmodulus", "");
    model.component("comp1").material("mat6").propertyGroup("def").set("poissonsratio", "");
    model.component("comp1").material("mat6").propertyGroup("def").set("density", "");
    model.component("comp1").material("mat6").propertyGroup("def").set("thermalconductivity", "");
    model.component("comp1").material("mat6").propertyGroup("def").set("electricconductivity", "");
    model.component("comp1").material("mat6").propertyGroup("def").set("heatcapacity", "");
    model.component("comp1").material("mat6").propertyGroup("def").set("youngsmodulus", "2[GPa]");
    model.component("comp1").material("mat6").propertyGroup("def")
         .setPropertyInfo("youngsmodulus", "Yue Qi et al 2010 J. Electrochem. Soc. 157 A558");
    model.component("comp1").material("mat6").propertyGroup("def").set("poissonsratio", "0.34");
    model.component("comp1").material("mat6").propertyGroup("def")
         .setPropertyInfo("poissonsratio", "Yue Qi et al 2010 J. Electrochem. Soc. 157 A558");
    model.component("comp1").material("mat6").propertyGroup("def").set("density", "0.534[g/cm^3]");
    model.component("comp1").material("mat6").propertyGroup("def")
         .setPropertyInfo("density", "https://en.wikipedia.org/wiki/Lithium");
    model.component("comp1").material("mat6").propertyGroup("def")
         .set("thermalconductivity", new String[]{"84.8[W/(m*K)]", "0", "0", "0", "84.8[W/(m*K)]", "0", "0", "0", "84.8[W/(m*K)]"});
    model.component("comp1").material("mat6").propertyGroup("def")
         .setPropertyInfo("thermalconductivity", "https://en.wikipedia.org/wiki/Lithium");
    model.component("comp1").material("mat6").propertyGroup("def")
         .set("electricconductivity", new String[]{"1/(92.8[n\u03a9*m])", "0", "0", "0", "1/(92.8[n\u03a9*m])", "0", "0", "0", "1/(92.8[n\u03a9*m])"});
    model.component("comp1").material("mat6").propertyGroup("def")
         .setPropertyInfo("electricconductivity", "https://en.wikipedia.org/wiki/Lithium");
    model.component("comp1").material("mat6").propertyGroup("def").set("heatcapacity", "3.58[kJ/kg/K]");
    model.component("comp1").material("mat6").propertyGroup("def")
         .setPropertyInfo("heatcapacity", "https://en.wikipedia.org/wiki/Lithium");
    model.component("comp1").material("mat6").propertyGroup("ElectrodePotential").label("Equilibrium potential");
    model.component("comp1").material("mat6").propertyGroup("ElectrodePotential").identifier("eeq");
    model.component("comp1").material("mat6").propertyGroup("ElectrodePotential").set("Eeq", "0[V]");
    model.component("comp1").material("mat6").propertyGroup("ElectrodePotential").set("dEeqdT", "0[V/K]");
    model.component("comp1").material("mat6").propertyGroup("ElectrodePotential").set("cEeqref", "0[M]");
    model.component("comp1").material("mat6").propertyGroup("ElectrodePotential").addInput("concentration");
    model.component("comp1").material("mat6").propertyGroup("ElectrodePotential").addInput("temperature");
    model.component("comp1").material("mat1").selection().named("sel5");
    model.component("comp1").material("mat2").selection().named("sel1");
    model.component("comp1").material("mat3").selection().named("sel4");
    model.component("comp1").material("mat4").selection().named("sel2");
    model.component("comp1").material("mat5").selection().named("sel3");
    model.component("comp1").material("mat6").selection().named("sel6");

    model.component("comp1").physics("liion").feature("sep1").set("epsl", "epsl_sep");
    model.component("comp1").physics("liion").create("pce1", "PorousElectrode", 2);
    model.component("comp1").physics("liion").feature("pce1")
         .label("\u591a\u5b54\u7535\u6781 1\uff08\u8d1f\u6781\uff09");
    model.component("comp1").physics("liion").feature("pce1").selection().named("sel2");
    model.component("comp1").physics("liion").feature("pce1").set("ElectrolyteMaterial", "mat5");
    model.component("comp1").physics("liion").feature("pce1")
         .set("sigma", new String[]{"sigmas_neg", "0", "0", "0", "sigmas_neg", "0", "0", "0", "sigmas_neg"});
    model.component("comp1").physics("liion").feature("pce1").set("epss", "epss_neg");
    model.component("comp1").physics("liion").feature("pce1").set("epsl", "epsl_neg");
    model.component("comp1").physics("liion").feature("pce1").feature("pin1").set("ParticleMaterial", "mat4");
    model.component("comp1").physics("liion").feature("pce1").feature("pin1").set("rp", "rp_neg");
    model.component("comp1").physics("liion").feature("pce1").feature("pin1").set("HeatMix", true);
    model.component("comp1").physics("liion").feature("pce1").feature("per1").set("MaterialOption", "mat4");
    model.component("comp1").physics("liion").feature("pce1").feature("per1").set("i0_ref", "i0ref_neg");
    model.component("comp1").physics("liion").feature().duplicate("pce2", "pce1");
    model.component("comp1").physics("liion").feature("pce2")
         .label("\u591a\u5b54\u7535\u6781 2\uff08\u6b63\u6781\uff09");
    model.component("comp1").physics("liion").feature("pce2").selection().named("sel4");
    model.component("comp1").physics("liion").feature("pce2")
         .set("sigma", new String[]{"sigmas_pos", "0", "0", "0", "sigmas_pos", "0", "0", "0", "sigmas_pos"});
    model.component("comp1").physics("liion").feature("pce2").set("epss", "epss_pos");
    model.component("comp1").physics("liion").feature("pce2").set("epsl", "epsl_pos");
    model.component("comp1").physics("liion").feature("pce2").feature("pin1").set("ParticleMaterial", "mat3");
    model.component("comp1").physics("liion").feature("pce2").feature("pin1").set("rp", "rp_pos");
    model.component("comp1").physics("liion").feature("pce2").feature("per1").set("MaterialOption", "mat3");
    model.component("comp1").physics("liion").feature("pce2").feature("per1").set("i0_ref", "i0ref_pos");
    model.component("comp1").physics("liion").feature().duplicate("pce3", "pce1");
    model.component("comp1").physics("liion").feature("pce3")
         .label("\u591a\u5b54\u7535\u6781 1\uff08\u8d1f\u6781\uff0c\u7535\u5316\u5b66\u60f0\u6027\uff09");
    model.component("comp1").physics("liion").feature("pce3").selection().set(3);
    model.component("comp1").physics("liion").feature("pce3")
         .set("IntercalationOption", "NonIntercalatingParticles");
    model.component("comp1").physics("liion").feature("pce3").feature("per1").set("Eeq_mat", "userdef");
    model.component("comp1").physics("liion").feature("pce3").feature("per1").set("Eeq", "0.1[V]");
    model.component("comp1").physics("liion").feature("pce3").feature("per1").set("ilocmat_mat", "userdef");
    model.component("comp1").physics("liion").feature("pce3").feature("per1").set("dEeqdT_mat", "userdef");
    model.component("comp1").physics("liion").feature().duplicate("pce4", "pce2");
    model.component("comp1").physics("liion").feature("pce4")
         .label("\u591a\u5b54\u7535\u6781 2\uff08\u6b63\u6781\uff0c\u7535\u5316\u5b66\u60f0\u6027\uff09");
    model.component("comp1").physics("liion").feature("pce4").selection().set(5);
    model.component("comp1").physics("liion").feature("pce4")
         .set("IntercalationOption", "NonIntercalatingParticles");
    model.component("comp1").physics("liion").feature("pce4").feature("per1").set("Eeq_mat", "userdef");
    model.component("comp1").physics("liion").feature("pce4").feature("per1").set("Eeq", "0.1[V]+E_cell");
    model.component("comp1").physics("liion").feature("pce4").feature("per1").set("ilocmat_mat", "userdef");
    model.component("comp1").physics("liion").feature("pce4").feature("per1").set("dEeqdT_mat", "userdef");
    model.component("comp1").physics("liion").create("cc1", "CurrentConductor", 2);
    model.component("comp1").physics("liion").feature("cc1").selection().named("uni1");
    model.component("comp1").physics("liion").feature("cc1")
         .label("\u7535\u6d41\u5bfc\u4f53 1\uff08\u96c6\u6d41\u4f53\u548c\u91d1\u5c5e\u4e1d\uff09");
    model.component("comp1").physics("liion").create("egnd1", "ElectricGround", 1);
    model.component("comp1").physics("liion").feature("egnd1").selection().named("sel7");
    model.component("comp1").physics("liion").create("pot1", "ElectricPotential", 1);
    model.component("comp1").physics("liion").feature("pot1").selection().named("sel8");
    model.component("comp1").physics("liion").feature("pot1").set("phisbnd", "E_cell");
    model.component("comp1").physics("liion").prop("CellSettings").set("CellSOCandInitialChargeInventory", true);
    model.component("comp1").physics("liion").feature("socicd1").set("InitialChargeType", "CellVoltage");
    model.component("comp1").physics("liion").feature("socicd1").set("Ecell_init", "E_cell");
    model.component("comp1").physics("liion").feature("socicd1").feature("neges1").selection().named("sel2");
    model.component("comp1").physics("liion").feature("socicd1").feature("poses1").selection().named("sel4");
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 1);
    model.component("comp1").physics("ht").feature("temp1").selection().named("uni2");
    model.component("comp1").physics("ht").feature("temp1").set("T0", "T0");
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T0");

    model.component("comp1").multiphysics().create("ech1", "ElectrochemicalHeating", 2);

    model.component("comp1").material("mat3").propertyGroup("def").set("density", new String[]{"rho_pos"});
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", new String[]{"Cp_pos"});
    model.component("comp1").material("mat5").propertyGroup("def").set("thermalconductivity", new String[]{"k_sep"});
    model.component("comp1").material("mat5").propertyGroup("def").set("density", new String[]{"rho_sep"});
    model.component("comp1").material("mat5").propertyGroup("def").set("heatcapacity", new String[]{"Cp_sep"});

    model.component("comp1").func().create("step1", "Step");
    model.component("comp1").func("step1").set("location", 0.5);
    model.component("comp1").func("step1").set("smooth", 1);

    model.component("comp1").material("mat6").propertyGroup("def")
         .set("electricconductivity", new String[]{"max(step1(t/t_ramp),1e-8)/(92.8[n\u03a9*m])"});

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(1, 2, 3, 4, 5, 6);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("dis1").selection().set(35, 36);

    return model;
  }

  public static Model run3(Model model) {
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("dis1").set("numelem", 10);
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").set("smoothcontrol", false);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(20, 22, 24, 26, 28, 29);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemcount", 20);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemratio", 10);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("reverse", true);
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").probe().create("dom1", "Domain");
    model.component("comp1").probe("dom1").set("intsurface", true);
    model.component("comp1").probe("dom1").set("intvolume", true);
    model.component("comp1").probe("dom1").label("\u6700\u9ad8\u6e29\u5ea6\u63a2\u9488");
    model.component("comp1").probe("dom1").set("type", "maximum");
    model.component("comp1").probe("dom1").set("expr", "T");

    model.component("comp1").cpl().create("maxop1", "Maximum");
    model.component("comp1").cpl("maxop1").selection().all();

    model.study("std1").feature("time").set("tlist", "0 10^range(-3,0.5,-1)");
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "R_in", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "R_in", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("plistarr", "1 5", 0);
    model.study("std1").feature("param").setIndex("punit", "um", 0);
    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol3");
    model.sol("sol3").study("std1");
    model.sol("sol3").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol3");

    model.component("comp1").probe("dom1").genResult("none");

    model.batch("p1").run("compute");

    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").label("\u6e29\u5ea6\uff08\u6f14\u53d8\u56fe\uff09");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "T");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u6cbf\u9694\u819c\u7684\u6e29\u5ea6");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").setIndex("looplevelinput", "last", 0);
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg3").feature("lngr1").set("linewidth", "preference");
    model.result("pg3").feature("lngr1").selection().set(9, 16, 18, 26);
    model.result("pg3").feature("lngr1").set("expr", "T");
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").set("xdataexpr", "r");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").run();
    model.result("pg4").label("\u5c40\u90e8\u8377\u7535\u72b6\u6001");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "liion.socloc_surface");
    model.result("pg4").feature("surf1")
         .set("descr", "\u5c40\u90e8\u7535\u6781\u6750\u6599\u8377\u7535\u72b6\u6001\uff0c\u9897\u7c92\u8868\u9762");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u6700\u9ad8\u6e29\u5ea6 vs. \u65f6\u95f4");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").set("legendpos", "lowerright");
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").setIndex("expr", "comp1.maxop1(T)", 0);
    model.result("pg5").feature("glob1").setIndex("unit", "degC", 0);
    model.result("pg5").feature("glob1").setIndex("descr", "\u7535\u6c60\u6700\u9ad8\u6e29\u5ea6", 0);
    model.result("pg5").feature("glob1").set("autodescr", false);
    model.result("pg5").run();

    model.title("\u9502\u79bb\u5b50\u7535\u6c60\u7684\u5185\u90e8\u77ed\u8def");

    model
         .description("\u5728\u7535\u6c60\u5185\u90e8\u77ed\u8def\u671f\u95f4\uff0c\u4e24\u4e2a\u7535\u6781\u6750\u6599\u5185\u90e8\u8fdb\u884c\u7535\u5b50\u4e92\u8fde\uff0c\u4ece\u800c\u4ea7\u751f\u8f83\u9ad8\u7684\u5c40\u90e8\u7535\u6d41\u5bc6\u5ea6\u3002\u9502\u79bb\u5b50\u7535\u6c60\u53d1\u751f\u5185\u90e8\u77ed\u8def\u53ef\u80fd\u662f\u9502\u679d\u6676\u7684\u751f\u957f\u6216\u538b\u7f29\u51b2\u51fb\u7b49\u5f15\u8d77\u7684\u3002\u5185\u90e8\u77ed\u8def\u671f\u95f4\u53d1\u751f\u7684\u9ad8\u5c40\u90e8\u7535\u6d41\u5bc6\u5ea6\u5c06\u5bfc\u81f4\u5c40\u90e8\u6e29\u5ea6\u5347\u9ad8\uff0c\u8fdb\u800c\u53ef\u80fd\u5bfc\u81f4\u7535\u6c60\u70ed\u5931\u63a7\u3002\n\n\u672c\u4f8b\u6a21\u62df\u7531\u679d\u6676\u751f\u957f\u5f15\u8d77\u7684\u5185\u90e8\u77ed\u8def\u5bfc\u81f4\u7684\u6e29\u5ea6\u5347\u9ad8\u3002\u8be5\u6a21\u578b\u5c06\u7535\u6c60\u5355\u5143\u6a21\u578b\u4e2d\u7684\u7535\u5316\u5b66\u70ed\u6e90\uff08\u4e3b\u8981\u6e90\u4e8e\u7535\u6781\u76f8\u4e2d\u7684\u7126\u8033\u70ed\uff09\u4e0e\u4f20\u70ed\u8fdb\u884c\u8026\u5408\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();

    model.label("internal_short_circuit.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
