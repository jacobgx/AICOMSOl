/*
 * li_battery_thermal_2d_axi.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:31 by COMSOL 6.3.0.290. */
public class li_battery_thermal_2d_axi {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Battery_Design_Module\\Thermal_Management");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("lb", "LumpedBattery", "geom1");
    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/lb", true);
    model.study("std1").feature("time").setSolveFor("/physics/ht", true);

    model.component("comp1").geom("geom1").insertFile("li_battery_thermal_2d_axi_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("sel3");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("L_neg", "55e-6[m]", "\u8d1f\u6781\u957f\u5ea6");
    model.param().set("L_sep", "30e-6[m]", "\u9694\u819c\u957f\u5ea6");
    model.param().set("L_pos", "55e-6[m]", "\u6b63\u6781\u957f\u5ea6");
    model.param().set("L_neg_cc", "7[um]", "\u8d1f\u6781\u96c6\u6d41\u4f53\u539a\u5ea6");
    model.param().set("L_pos_cc", "10[um]", "\u6b63\u6781\u96c6\u6d41\u4f53\u539a\u5ea6");
    model.param().set("L_batt", "L_neg+L_neg_cc+L_sep+L_pos+L_pos_cc", "\u7535\u6c60\u539a\u5ea6");
    model.param().set("kT_pos", "1.58[W/(m*K)]", "\u6b63\u6781\u5bfc\u70ed\u7cfb\u6570");
    model.param().set("kT_neg", "1.04[W/(m*K)]", "\u8d1f\u6781\u5bfc\u70ed\u7cfb\u6570");
    model.param().set("kT_pos_cc", "170[W/(m*K)]", "\u6b63\u6781\u96c6\u6d41\u4f53\u5bfc\u70ed\u7cfb\u6570");
    model.param().set("kT_neg_cc", "398[W/(m*K)]", "\u8d1f\u6781\u96c6\u6d41\u4f53\u5bfc\u70ed\u7cfb\u6570");
    model.param().set("kT_sep", "0.344[W/(m*K)]", "\u9694\u819c\u5bfc\u70ed\u7cfb\u6570");
    model.param().set("rho_pos", "2328.5[kg/m^3]", "\u6b63\u6781\u5bc6\u5ea6");
    model.param().set("rho_neg", "1347.33[kg/m^3]", "\u8d1f\u6781\u5bc6\u5ea6");
    model.param().set("rho_pos_cc", "2770[kg/m^3]", "\u6b63\u6781\u96c6\u6d41\u4f53\u5bc6\u5ea6");
    model.param().set("rho_neg_cc", "8933[kg/m^3]", "\u8d1f\u6781\u96c6\u6d41\u4f53\u5bc6\u5ea6");
    model.param().set("rho_sep", "1008.98[kg/m^3]", "\u9694\u819c\u5bc6\u5ea6");
    model.param().set("Cp_pos", "1269.21[J/(kg*K)]", "\u6b63\u6781\u70ed\u5bb9");
    model.param().set("Cp_neg", "1437.4[J/(kg*K)]", "\u8d1f\u6781\u70ed\u5bb9");
    model.param().set("Cp_pos_cc", "875[J/(kg*K)]", "\u6b63\u6781\u96c6\u6d41\u4f53\u70ed\u5bb9");
    model.param().set("Cp_neg_cc", "385[J/(kg*K)]", "\u8d1f\u6781\u96c6\u6d41\u4f53\u70ed\u5bb9");
    model.param().set("Cp_sep", "1978.16[J/(kg*K)]", "\u9694\u819c\u70ed\u5bb9");
    model.param()
         .set("kT_batt_ang", "(kT_pos*L_pos+kT_neg*L_neg+kT_pos_cc*L_pos_cc+kT_neg_cc*L_neg_cc+kT_sep*L_sep)/L_batt", "\u7535\u6c60\u5bfc\u70ed\u7cfb\u6570\uff0c\u89d2\u5ea6");
    model.param()
         .set("kT_batt_r", "L_batt/(L_pos/kT_pos+L_neg/kT_neg+L_pos_cc/kT_pos_cc+L_neg_cc/kT_neg_cc+L_sep/kT_sep)", "\u7535\u6c60\u5bfc\u70ed\u7cfb\u6570\uff0c\u5f84\u5411");
    model.param()
         .set("rho_batt", "(rho_pos*L_pos+rho_neg*L_neg+rho_pos_cc*L_pos_cc+rho_neg_cc*L_neg_cc+rho_sep*L_sep)/L_batt", "\u7535\u6c60\u5bc6\u5ea6");
    model.param()
         .set("Cp_batt", "(Cp_pos*L_pos*rho_pos+Cp_neg*L_neg*rho_neg+Cp_pos_cc*L_pos_cc*rho_pos_cc+Cp_neg_cc*L_neg_cc*rho_neg_cc+Cp_sep*L_sep*rho_sep)/(L_batt*rho_batt)", "\u7535\u6c60\u70ed\u5bb9");
    model.param().set("cycle_time", "600[s]", "\u5faa\u73af\u65f6\u95f4");
    model.param().set("T_inlet", "298.15[K]", "\u5165\u53e3\u6e29\u5ea6");
    model.param().set("T_init", "T_inlet", "\u521d\u59cb\u6e29\u5ea6");
    model.param().set("Q_batt", "1.258[A*h]", "\u7535\u6c60\u5bb9\u91cf");
    model.param().set("eta_ohmic1C", "4.5[mV]", "1C \u6b27\u59c6\u8fc7\u7535\u4f4d");
    model.param().set("J0", "0.85", "\u65e0\u91cf\u7eb2\u7535\u8377\u4ea4\u6362\u7535\u6d41");
    model.param().set("tau", "1000[s]", "\u6269\u6563\u65f6\u95f4\u5e38\u6570");
    model.param().set("SOC_init", "0.2", "\u521d\u59cb\u8377\u7535\u72b6\u6001");

    model.func().create("wv1", "Wave");
    model.func("wv1").set("type", "square");
    model.func("wv1").set("period", "cycle_time");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("C_rate", "7.5*(wv1(t))*(t<1500)");
    model.component("comp1").variable("var1")
         .descr("C_rate", "\u5916\u52a0\u7535\u6d41\u7684\u500d\u7387\u500d\u6570");

    model.component("comp1").probe().create("dom1", "Domain");
    model.component("comp1").probe("dom1").set("intsurface", true);
    model.component("comp1").probe("dom1").set("intvolume", true);
    model.component("comp1").probe("dom1").label("\u5e73\u5747\u6e29\u5ea6");
    model.component("comp1").probe("dom1").set("probename", "MeanT");
    model.component("comp1").probe("dom1").selection().named("geom1_sel2");
    model.component("comp1").probe("dom1").set("expr", "T-T_init");
    model.component("comp1").probe("dom1").set("window", "window1");
    model.component("comp1").probe("dom1").set("windowtitle", "\u63a2\u9488\u56fe\u201c1\u201d");
    model.component("comp1").probe().duplicate("dom2", "dom1");
    model.component("comp1").probe("dom2").label("\u6700\u9ad8\u6e29\u5ea6");
    model.component("comp1").probe("dom2").set("probename", "maxT");
    model.component("comp1").probe("dom2").set("type", "maximum");
    model.component("comp1").probe().duplicate("dom3", "dom2");
    model.component("comp1").probe("dom3").label("\u6700\u4f4e\u6e29\u5ea6");
    model.component("comp1").probe("dom3").set("probename", "minT");
    model.component("comp1").probe("dom3").set("type", "minimum");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").label("Steel AISI 4340");
    model.component("comp1").material("mat1").set("family", "steel");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "205[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.28");
    model.component("comp1").material("mat1").selection().named("geom1_sel1");

    model.component("comp1").physics("lb").selection().named("geom1_sel2");
    model.component("comp1").physics("lb").prop("BatterySettings").set("AppliedCurrentType", "CrateMultiple");
    model.component("comp1").physics("lb").prop("BatterySettings").set("Crate", "C_rate");
    model.component("comp1").physics("lb").prop("BatterySettings").set("Q_cell0", "Q_batt");
    model.component("comp1").physics("lb").prop("BatterySettings").set("SOC_cell0", "SOC_init");
    model.component("comp1").physics("lb").feature("cep1").set("SOC_Eocv", new int[]{});
    model.component("comp1").physics("lb").feature("cep1").set("Eocv", new int[]{});
    model.component("comp1").physics("lb").feature("cep1")
         .set("SOC_Eocv", new double[]{0, 0.01, 0.02, 0.030000000000000002, 0.04, 0.05, 0.060000000000000005, 0.07, 0.08, 0.09000000000000001, 0.1, 0.11, 0.12000000000000001, 0.13, 0.14, 0.15, 0.16, 0.17, 0.18000000000000002, 0.19, 0.2, 0.21000000000000002, 0.22, 0.23, 0.24000000000000002, 0.25, 0.26, 0.27, 0.28, 0.29000000000000004, 0.3, 0.31, 0.32, 0.33, 0.34, 0.35000000000000003, 0.36000000000000004, 0.37, 0.38, 0.39, 0.4, 0.41000000000000003, 0.42000000000000004, 0.43, 0.44, 0.45, 0.46, 0.47000000000000003, 0.48000000000000004, 0.49000000000000005, 0.5, 0.51, 0.52, 0.53, 0.54, 0.55, 0.56, 0.5700000000000001, 0.5800000000000001, 0.5900000000000001, 0.6, 0.61, 0.62, 0.63, 0.64, 0.65, 0.66, 0.67, 0.68, 0.6900000000000001, 0.7000000000000001, 0.7100000000000001, 0.7200000000000001, 0.73, 0.74, 0.75, 0.76, 0.77, 0.78, 0.79, 0.8, 0.81, 0.8200000000000001, 0.8300000000000001, 0.8400000000000001, 0.8500000000000001, 0.86, 0.87, 0.88, 0.89, 0.9, 0.91, 0.92, 0.93, 0.9400000000000001, 0.9500000000000001, 0.9600000000000001, 0.9700000000000001, 0.9800000000000001, 0.9900000000000001, 1});
    model.component("comp1").physics("lb").feature("cep1")
         .set("Eocv", new double[]{1.3104190837885064, 2.2611069048882873, 2.8334213300309643, 3.1844362423678003, 3.393016295869783, 3.510875145406253, 3.5869463584273378, 3.6264514929037994, 3.6557565420023983, 3.6850615911009976, 3.6994551120045407, 3.7113345164250635, 3.7232139208455868, 3.7350933252661096, 3.746972729686633, 3.7588394760123167, 3.7669805471052284, 3.7751216181981406, 3.7832626892910524, 3.7914037603839645, 3.7995448314768763, 3.8076859025697884, 3.8158269736627, 3.8239680447556124, 3.832109115848524, 3.8402384951548894, 3.844926614135922, 3.8496147331169555, 3.8543028520979887, 3.8589909710790216, 3.863679090060055, 3.8683672090410877, 3.873055328022121, 3.877743447003154, 3.882431565984187, 3.8871182387192977, 3.891379235814907, 3.8956402329105164, 3.900108284697505, 3.9048921849677303, 3.910004858946812, 3.9155565350495363, 3.9213824207219656, 3.927676048105941, 3.9340438371441717, 3.94051108662678, 3.9470234377800457, 3.9533348394394214, 3.9592735197022777, 3.9650124251831564, 3.970366661250179, 3.975134457606617, 3.979408469784772, 3.9832924969139087, 3.986400783870235, 3.989209391104789, 3.9920179983393425, 3.994805300205646, 3.9971563232603926, 3.9995073463151387, 4.001858369369885, 4.004209392424632, 4.006560415479378, 4.008911438534124, 4.011262461588871, 4.014107842011973, 4.017168121841405, 4.020228401670838, 4.02328868150027, 4.0263489613297025, 4.029409241159136, 4.032469520988568, 4.03470071596249, 4.035374828848886, 4.036048941735283, 4.036723054621679, 4.037397167508075, 4.038071280394472, 4.038745393280867, 4.039419506167263, 4.039965701342635, 4.040511627548762, 4.041057553754889, 4.041603479961017, 4.042149406167145, 4.042695332373272, 4.0432412585794, 4.04546697590993, 4.048603777764223, 4.0517405796185155, 4.054877381472807, 4.0580141833271, 4.061150985181393, 4.064287787035685, 4.069434668295399, 4.079298736570241, 4.091601063364331, 4.110991916734893, 4.13395738036637, 4.167078533171239, 4.204442586248173});
    model.component("comp1").physics("lb").feature("cep1").set("SOC_dEocvdT", new int[]{});
    model.component("comp1").physics("lb").feature("cep1").set("dEocvdT", new int[]{});
    model.component("comp1").physics("lb").feature("cep1")
         .set("SOC_dEocvdT", new double[]{0, 0.24, 0.34, 0.39, 0.7, 0.76, 1});
    model.component("comp1").physics("lb").feature("cep1")
         .set("dEocvdT", new String[]{"3e-4", "0", "-6e-5", "-1.6e-4", "-1.6e-4", "-9e-5", "-9e-5"});
    model.component("comp1").physics("lb").feature("vl1").set("eta_ir1C", "eta_ohmic1C");
    model.component("comp1").physics("lb").feature("vl1").set("J0", "J0");
    model.component("comp1").physics("lb").feature("vl1").set("IncludeConcentrationOverpotential", true);
    model.component("comp1").physics("lb").feature("vl1").set("tau", "tau");
    model.component("comp1").physics("ht").create("solid2", "SolidHeatTransferModel", 2);
    model.component("comp1").physics("ht").feature("solid2").selection().named("geom1_sel2");
    model.component("comp1").physics("ht").feature("solid2").set("k_mat", "userdef");
    model.component("comp1").physics("ht").feature("solid2")
         .set("k", new String[]{"kT_batt_r", "0", "0", "0", "kT_batt_r", "0", "0", "0", "kT_batt_ang"});
    model.component("comp1").physics("ht").feature("solid2").set("rho_mat", "userdef");
    model.component("comp1").physics("ht").feature("solid2").set("rho", "rho_batt");
    model.component("comp1").physics("ht").feature("solid2").set("Cp_mat", "userdef");
    model.component("comp1").physics("ht").feature("solid2").set("Cp", "Cp_batt");
    model.component("comp1").physics("ht").create("solid3", "SolidHeatTransferModel", 2);
    model.component("comp1").physics("ht").feature("solid3").selection().named("geom1_sel3");
    model.component("comp1").physics("ht").feature("solid3").set("k_mat", "userdef");
    model.component("comp1").physics("ht").feature("solid3")
         .set("k", new String[]{"kT_sep", "0", "0", "0", "kT_sep", "0", "0", "0", "kT_sep"});
    model.component("comp1").physics("ht").feature("solid3").set("rho_mat", "userdef");
    model.component("comp1").physics("ht").feature("solid3").set("rho", "rho_sep");
    model.component("comp1").physics("ht").feature("solid3").set("Cp_mat", "userdef");
    model.component("comp1").physics("ht").feature("solid3").set("Cp", "Cp_sep");
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 1);
    model.component("comp1").physics("ht").feature("hf1").selection().set(2, 7, 12);
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("h", 20);
    model.component("comp1").physics("ht").feature("hf1").set("Text", "T_init");
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T_init");

    model.component("comp1").multiphysics().create("ech1", "ElectrochemicalHeating", 2);

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").set("smoothcontrol", false);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "0 2100");
    model.study("std1").feature("time").set("usertol", true);
    model.study("std1").feature("time").set("rtol", "1e-3");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("tout", "tsteps");

    model.study("std1").createAutoSequences("all");

    model.component("comp1").probe("dom1").genResult("none");
    model.component("comp1").probe("dom2").genResult("none");
    model.component("comp1").probe("dom3").genResult("none");

    model.sol("sol1").runAll();

    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").label("\u7535\u6c60\u7535\u4f4d\u548c\u8d1f\u8f7d (lb)");
    model.result("pg2").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result("pg2").set("ylabel", "\u7535\u6c60\u7535\u4f4d (V)");
    model.result("pg2").set("showsecylabel", "on");
    model.result("pg2").set("twoyaxes", true);
    model.result("pg2").set("legendpos", "lowerleft");
    model.result("pg2").feature().create("glob1", "Global");
    model.result("pg2").feature("glob1").set("showsolutionparams", "on");
    model.result("pg2").feature("glob1").set("expr", new String[]{"lb.E_cell"});
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg2").feature("glob1").set("showunitcombo", "off");
    model.result("pg2").feature("glob1").set("showsolutionparams", "on");
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg2").feature("glob1").set("showunitcombo", "off");
    model.result("pg2").feature("glob1").set("showsolutionparams", "on");
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg2").feature("glob1").set("showunitcombo", "off");
    model.result("pg2").feature("glob1").set("showsolutionparams", "on");
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg2").feature("glob1").set("showunitcombo", "off");
    model.result("pg2").feature("glob1").set("showsolutionparams", "on");
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg2").feature("glob1").set("showunitcombo", "off");
    model.result("pg2").feature("glob1").set("data", "parent");
    model.result("pg2").feature().create("glob2", "Global");
    model.result("pg2").feature("glob2").set("showsolutionparams", "on");
    model.result("pg2").feature("glob2").set("expr", new String[]{"lb.Eocv_cell"});
    model.result("pg2").feature("glob2").set("xdatasolnumtype", "inner");
    model.result("pg2").feature("glob2").set("showunitcombo", "off");
    model.result("pg2").feature("glob2").set("showsolutionparams", "on");
    model.result("pg2").feature("glob2").set("xdatasolnumtype", "inner");
    model.result("pg2").feature("glob2").set("showunitcombo", "off");
    model.result("pg2").feature("glob2").set("showsolutionparams", "on");
    model.result("pg2").feature("glob2").set("xdatasolnumtype", "inner");
    model.result("pg2").feature("glob2").set("showunitcombo", "off");
    model.result("pg2").feature("glob2").set("showsolutionparams", "on");
    model.result("pg2").feature("glob2").set("xdatasolnumtype", "inner");
    model.result("pg2").feature("glob2").set("showunitcombo", "off");
    model.result("pg2").feature("glob2").set("showsolutionparams", "on");
    model.result("pg2").feature("glob2").set("xdatasolnumtype", "inner");
    model.result("pg2").feature("glob2").set("showunitcombo", "off");
    model.result("pg2").feature("glob2").set("data", "parent");
    model.result("pg2").feature().create("glob3", "Global");
    model.result("pg2").feature("glob3").set("showsolutionparams", "on");
    model.result("pg2").feature("glob3").set("plotonsecyaxis", true);
    model.result("pg2").feature("glob3").set("expr", new String[]{"lb.I_cell"});
    model.result("pg2").feature("glob3").set("xdatasolnumtype", "inner");
    model.result("pg2").feature("glob3").set("showunitcombo", "off");
    model.result("pg2").feature("glob3").set("showsolutionparams", "on");
    model.result("pg2").feature("glob3").set("xdatasolnumtype", "inner");
    model.result("pg2").feature("glob3").set("showunitcombo", "off");
    model.result("pg2").feature("glob3").set("showsolutionparams", "on");
    model.result("pg2").feature("glob3").set("xdatasolnumtype", "inner");
    model.result("pg2").feature("glob3").set("showunitcombo", "off");
    model.result("pg2").feature("glob3").set("showsolutionparams", "on");
    model.result("pg2").feature("glob3").set("xdatasolnumtype", "inner");
    model.result("pg2").feature("glob3").set("showunitcombo", "off");
    model.result("pg2").feature("glob3").set("showsolutionparams", "on");
    model.result("pg2").feature("glob3").set("xdatasolnumtype", "inner");
    model.result("pg2").feature("glob3").set("showunitcombo", "off");
    model.result("pg2").feature("glob3").set("data", "parent");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").label("\u7535\u6c60\u8377\u7535\u72b6\u6001 (lb)");
    model.result("pg3").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result("pg3").set("showsecylabel", "on");
    model.result("pg3").set("twoyaxes", true);
    model.result("pg3").set("legendpos", "lowerleft");
    model.result("pg3").feature().create("glob1", "Global");
    model.result("pg3").feature("glob1").set("showsolutionparams", "on");
    model.result("pg3").feature("glob1").set("expr", new String[]{"lb.SOC_cell"});
    model.result("pg3").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg3").feature("glob1").set("showunitcombo", "off");
    model.result("pg3").feature("glob1").set("showsolutionparams", "on");
    model.result("pg3").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg3").feature("glob1").set("showunitcombo", "off");
    model.result("pg3").feature("glob1").set("showsolutionparams", "on");
    model.result("pg3").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg3").feature("glob1").set("showunitcombo", "off");
    model.result("pg3").feature("glob1").set("showsolutionparams", "on");
    model.result("pg3").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg3").feature("glob1").set("showunitcombo", "off");
    model.result("pg3").feature("glob1").set("showsolutionparams", "on");
    model.result("pg3").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg3").feature("glob1").set("showunitcombo", "off");
    model.result("pg3").feature("glob1").set("data", "parent");
    model.result("pg3").feature().create("glob2", "Global");
    model.result("pg3").feature("glob2").set("showsolutionparams", "on");
    model.result("pg3").feature("glob2").set("plotonsecyaxis", true);
    model.result("pg3").feature("glob2").set("expr", new String[]{"lb.I_cell"});
    model.result("pg3").feature("glob2").set("xdatasolnumtype", "inner");
    model.result("pg3").feature("glob2").set("showunitcombo", "off");
    model.result("pg3").feature("glob2").set("showsolutionparams", "on");
    model.result("pg3").feature("glob2").set("xdatasolnumtype", "inner");
    model.result("pg3").feature("glob2").set("showunitcombo", "off");
    model.result("pg3").feature("glob2").set("showsolutionparams", "on");
    model.result("pg3").feature("glob2").set("xdatasolnumtype", "inner");
    model.result("pg3").feature("glob2").set("showunitcombo", "off");
    model.result("pg3").feature("glob2").set("showsolutionparams", "on");
    model.result("pg3").feature("glob2").set("xdatasolnumtype", "inner");
    model.result("pg3").feature("glob2").set("showunitcombo", "off");
    model.result("pg3").feature("glob2").set("showsolutionparams", "on");
    model.result("pg3").feature("glob2").set("xdatasolnumtype", "inner");
    model.result("pg3").feature("glob2").set("showunitcombo", "off");
    model.result("pg3").feature("glob2").set("data", "parent");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u6e29\u5ea6 (ht)");
    model.result("pg4").set("dataisaxisym", "off");
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("solutionparams", "parent");
    model.result("pg4").feature("surf1").set("expr", "T");
    model.result("pg4").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg2").run();
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("data", "dset1");
    model.result().dataset("rev1")
         .set("defaultPlotIDs", new String[]{"ht/HT_PhysicsInterfaces/icom8/pdef1/pcond2/pcond4/pcond2/pg2|ht", "ht/HT_PhysicsInterfaces/icom8/pdef1/pcond2/pcond4/pcond2/pg3|ht", "ht/HT_PhysicsInterfaces/icom8/pdef1/pcond2/pcond4/pcond2/pg4|ht"});
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u6e29\u5ea6 (ht) 1");
    model.result("pg5").feature().create("vol1", "Volume");
    model.result("pg5").feature("vol1").set("solutionparams", "parent");
    model.result("pg5").feature("vol1").set("expr", "T");
    model.result("pg5").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg5").feature("vol1").set("smooth", "internal");
    model.result("pg5").feature("vol1").set("showsolutionparams", "on");
    model.result("pg5").feature("vol1").set("data", "parent");
    model.result("pg5").label("\u6e29\u5ea6 (ht) 1");
    model.result("pg5").run();
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("glob2").active(false);
    model.result("pg2").run();
    model.result("pg2").set("titletype", "label");
    model.result("pg2").set("legendpos", "lowerright");
    model.result("pg2").run();
    model.result("pg5").run();
    model.result("pg5").set("looplevel", new String[]{"interp"});
    model.result("pg5").set("interp", new int[]{1500});
    model.result("pg5").run();
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").label("\u6e29\u5ea6 vs. \u65f6\u95f4");
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "\u6e29\u5ea6\u53d8\u5316");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\\DELTA T (K)");
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").feature("tblp1").set("legendmethod", "manual");
    model.result("pg1").feature("tblp1").setIndex("legends", "\u5e73\u5747", 0);
    model.result("pg1").feature("tblp1").setIndex("legends", "\u6700\u9ad8", 1);
    model.result("pg1").feature("tblp1").setIndex("legends", "\u6700\u4f4e", 2);
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();

    model.title("\u5706\u67f1\u5f62\u9502\u79bb\u5b50\u7535\u6c60\u70ed\u5efa\u6a21 - \u4e8c\u7ef4");

    model
         .description("\u672c\u4f8b\u4f7f\u7528\u4e8c\u7ef4\u8f74\u5bf9\u79f0\u51e0\u4f55\u6765\u6a21\u62df\u5706\u67f1\u7535\u6c60\u7684\u70ed\u91cf\u5206\u5e03\u3002\u70ed\u6a21\u578b\u4e0e\u96c6\u603b\u7535\u6c60\u6a21\u578b\u8fdb\u884c\u8026\u5408\uff0c\u540e\u8005\u7528\u4e8e\u5728\u6d3b\u6027\u7535\u6c60\u6750\u6599\u4e2d\u751f\u6210\u70ed\u6e90\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("li_battery_thermal_2d_axi.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
