/*
 * lumped_li_battery_parameter_estimation.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:24 by COMSOL 6.3.0.290. */
public class lumped_li_battery_parameter_estimation {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Battery_Design_Module\\Lithium-Ion_Batteries,_Performance");

    model.component().create("comp1", true);

    model.component("comp1").physics().create("lb", "LumpedBattery");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/lb", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("eta_IR_1C", "10[mV]", "1C \u6b27\u59c6\u8fc7\u7535\u4f4d\uff0c\u62df\u5408\u53c2\u6570");
    model.param()
         .set("invJ0", "1", "\u9006\u65e0\u91cf\u7eb2\u7535\u8377\u4ea4\u6362\u7535\u6d41\uff0c\u62df\u5408\u53c2\u6570");
    model.param().set("tau", "1000[s]", "\u6269\u6563\u65f6\u95f4\u5e38\u6570\uff0c\u62df\u5408\u53c2\u6570");
    model.param().set("J0", "1/invJ0", "\u65e0\u91cf\u7eb2\u7535\u8377\u4ea4\u6362\u7535\u6d41");
    model.param().set("Q_cell0", "12[A*h]", "\u7535\u6c60\u5bb9\u91cf");
    model.param().set("SOC_0", "0.3797", "\u521d\u59cb\u8377\u7535\u72b6\u6001");
    model.param().set("T", "298.15[K]", "\u6e29\u5ea6");

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("I_cell_exp", "I_cell_exp(t)[A]", "\u5b9e\u9a8c\u7535\u6c60\u7535\u6d41");
    model.component("comp1").variable("var1")
         .set("E_cell_exp", "E_cell_exp(t)[V]", "\u5b9e\u9a8c\u7535\u6c60\u7535\u538b");

    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").label("\u8d1f\u8f7d\u5faa\u73af\u6570\u636e");
    model.result().table("tbl1").importData("lumped_li_battery_parameter_estimation_E_I_vs_t_data.txt");

    model.component("comp1").func().create("int1", "Interpolation");
    model.component("comp1").func("int1").label("\u63d2\u503c - E \u548c I vs. t");
    model.component("comp1").func("int1").set("source", "resultTable");
    model.component("comp1").func("int1").setIndex("argunit", "s", 0);
    model.component("comp1").func("int1").setEntry("funcnames", "col2", "E_cell_exp");
    model.component("comp1").func("int1").setEntry("columnType", "col3", "value");
    model.component("comp1").func("int1").setEntry("funcnames", "col3", "I_cell_exp");

    model.component("comp1").physics("lb").prop("BatterySettings").set("I_app", "I_cell_exp");
    model.component("comp1").physics("lb").prop("BatterySettings").set("Q_cell0", "Q_cell0");
    model.component("comp1").physics("lb").prop("BatterySettings").set("SOC_cell0", "SOC_0");
    model.component("comp1").physics("lb").feature("cep1").set("SOC_Eocv", new int[]{});
    model.component("comp1").physics("lb").feature("cep1").set("Eocv", new int[]{});
    model.component("comp1").physics("lb").feature("cep1")
         .set("SOC_Eocv", new double[]{0, 0.01, 0.02, 0.030000000000000002, 0.04, 0.05, 0.060000000000000005, 0.07, 0.08, 0.09000000000000001, 0.1, 0.11, 0.12000000000000001, 0.13, 0.14, 0.15, 0.16, 0.17, 0.18000000000000002, 0.19, 0.2, 0.21000000000000002, 0.22, 0.23, 0.24000000000000002, 0.25, 0.26, 0.27, 0.28, 0.29000000000000004, 0.3, 0.31, 0.32, 0.33, 0.34, 0.35000000000000003, 0.36000000000000004, 0.37, 0.38, 0.39, 0.4, 0.41000000000000003, 0.42000000000000004, 0.43, 0.44, 0.45, 0.46, 0.47000000000000003, 0.48000000000000004, 0.49000000000000005, 0.5, 0.51, 0.52, 0.53, 0.54, 0.55, 0.56, 0.5700000000000001, 0.5800000000000001, 0.5900000000000001, 0.6, 0.61, 0.62, 0.63, 0.64, 0.65, 0.66, 0.67, 0.68, 0.6900000000000001, 0.7000000000000001, 0.7100000000000001, 0.7200000000000001, 0.73, 0.74, 0.75, 0.76, 0.77, 0.78, 0.79, 0.8, 0.81, 0.8200000000000001, 0.8300000000000001, 0.8400000000000001, 0.8500000000000001, 0.86, 0.87, 0.88, 0.89, 0.9, 0.91, 0.92, 0.93, 0.9400000000000001, 0.9500000000000001, 0.9600000000000001, 0.9700000000000001, 0.9800000000000001, 0.9900000000000001, 1});
    model.component("comp1").physics("lb").feature("cep1")
         .set("Eocv", new double[]{1.3104190837885064, 2.2611069048882873, 2.8334213300309643, 3.1844362423678003, 3.393016295869783, 3.510875145406253, 3.5869463584273378, 3.6264514929037994, 3.6557565420023983, 3.6850615911009976, 3.6994551120045407, 3.7113345164250635, 3.7232139208455868, 3.7350933252661096, 3.746972729686633, 3.7588394760123167, 3.7669805471052284, 3.7751216181981406, 3.7832626892910524, 3.7914037603839645, 3.7995448314768763, 3.8076859025697884, 3.8158269736627, 3.8239680447556124, 3.832109115848524, 3.8402384951548894, 3.844926614135922, 3.8496147331169555, 3.8543028520979887, 3.8589909710790216, 3.863679090060055, 3.8683672090410877, 3.873055328022121, 3.877743447003154, 3.882431565984187, 3.8871182387192977, 3.891379235814907, 3.8956402329105164, 3.900108284697505, 3.9048921849677303, 3.910004858946812, 3.9155565350495363, 3.9213824207219656, 3.927676048105941, 3.9340438371441717, 3.94051108662678, 3.9470234377800457, 3.9533348394394214, 3.9592735197022777, 3.9650124251831564, 3.970366661250179, 3.975134457606617, 3.979408469784772, 3.9832924969139087, 3.986400783870235, 3.989209391104789, 3.9920179983393425, 3.994805300205646, 3.9971563232603926, 3.9995073463151387, 4.001858369369885, 4.004209392424632, 4.006560415479378, 4.008911438534124, 4.011262461588871, 4.014107842011973, 4.017168121841405, 4.020228401670838, 4.02328868150027, 4.0263489613297025, 4.029409241159136, 4.032469520988568, 4.03470071596249, 4.035374828848886, 4.036048941735283, 4.036723054621679, 4.037397167508075, 4.038071280394472, 4.038745393280867, 4.039419506167263, 4.039965701342635, 4.040511627548762, 4.041057553754889, 4.041603479961017, 4.042149406167145, 4.042695332373272, 4.0432412585794, 4.04546697590993, 4.048603777764223, 4.0517405796185155, 4.054877381472807, 4.0580141833271, 4.061150985181393, 4.064287787035685, 4.069434668295399, 4.079298736570241, 4.091601063364331, 4.110991916734893, 4.13395738036637, 4.167078533171239, 4.204442586248173});
    model.component("comp1").physics("lb").feature("cep1").set("Tref", "T");
    model.component("comp1").physics("lb").feature("vl1").set("IncludeConcentrationOverpotential", true);

    model.study("std1").label("\u7814\u7a76 1 - \u8d1f\u8f7d\u66f2\u7ebf\u4eff\u771f");
    model.study("std1").feature("time").set("tlist", "range(0,1,300)");
    model.study("std1").feature("time").set("usertol", true);
    model.study("std1").feature("time").set("rtol", 0.001);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").label("\u7535\u6c60\u7535\u4f4d\u548c\u8d1f\u8f7d (lb)");
    model.result("pg1").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result("pg1").set("ylabel", "\u7535\u6c60\u7535\u4f4d (V)");
    model.result("pg1").set("showsecylabel", "on");
    model.result("pg1").set("twoyaxes", true);
    model.result("pg1").set("legendpos", "lowerleft");
    model.result("pg1").feature().create("glob1", "Global");
    model.result("pg1").feature("glob1").set("showsolutionparams", "on");
    model.result("pg1").feature("glob1").set("expr", new String[]{"lb.E_cell"});
    model.result("pg1").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg1").feature("glob1").set("showunitcombo", "off");
    model.result("pg1").feature("glob1").set("showsolutionparams", "on");
    model.result("pg1").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg1").feature("glob1").set("showunitcombo", "off");
    model.result("pg1").feature("glob1").set("showsolutionparams", "on");
    model.result("pg1").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg1").feature("glob1").set("showunitcombo", "off");
    model.result("pg1").feature("glob1").set("showsolutionparams", "on");
    model.result("pg1").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg1").feature("glob1").set("showunitcombo", "off");
    model.result("pg1").feature("glob1").set("showsolutionparams", "on");
    model.result("pg1").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg1").feature("glob1").set("showunitcombo", "off");
    model.result("pg1").feature("glob1").set("data", "parent");
    model.result("pg1").feature().create("glob2", "Global");
    model.result("pg1").feature("glob2").set("showsolutionparams", "on");
    model.result("pg1").feature("glob2").set("expr", new String[]{"lb.Eocv_cell"});
    model.result("pg1").feature("glob2").set("xdatasolnumtype", "inner");
    model.result("pg1").feature("glob2").set("showunitcombo", "off");
    model.result("pg1").feature("glob2").set("showsolutionparams", "on");
    model.result("pg1").feature("glob2").set("xdatasolnumtype", "inner");
    model.result("pg1").feature("glob2").set("showunitcombo", "off");
    model.result("pg1").feature("glob2").set("showsolutionparams", "on");
    model.result("pg1").feature("glob2").set("xdatasolnumtype", "inner");
    model.result("pg1").feature("glob2").set("showunitcombo", "off");
    model.result("pg1").feature("glob2").set("showsolutionparams", "on");
    model.result("pg1").feature("glob2").set("xdatasolnumtype", "inner");
    model.result("pg1").feature("glob2").set("showunitcombo", "off");
    model.result("pg1").feature("glob2").set("showsolutionparams", "on");
    model.result("pg1").feature("glob2").set("xdatasolnumtype", "inner");
    model.result("pg1").feature("glob2").set("showunitcombo", "off");
    model.result("pg1").feature("glob2").set("data", "parent");
    model.result("pg1").feature().create("glob3", "Global");
    model.result("pg1").feature("glob3").set("showsolutionparams", "on");
    model.result("pg1").feature("glob3").set("plotonsecyaxis", true);
    model.result("pg1").feature("glob3").set("expr", new String[]{"lb.I_cell"});
    model.result("pg1").feature("glob3").set("xdatasolnumtype", "inner");
    model.result("pg1").feature("glob3").set("showunitcombo", "off");
    model.result("pg1").feature("glob3").set("showsolutionparams", "on");
    model.result("pg1").feature("glob3").set("xdatasolnumtype", "inner");
    model.result("pg1").feature("glob3").set("showunitcombo", "off");
    model.result("pg1").feature("glob3").set("showsolutionparams", "on");
    model.result("pg1").feature("glob3").set("xdatasolnumtype", "inner");
    model.result("pg1").feature("glob3").set("showunitcombo", "off");
    model.result("pg1").feature("glob3").set("showsolutionparams", "on");
    model.result("pg1").feature("glob3").set("xdatasolnumtype", "inner");
    model.result("pg1").feature("glob3").set("showunitcombo", "off");
    model.result("pg1").feature("glob3").set("showsolutionparams", "on");
    model.result("pg1").feature("glob3").set("xdatasolnumtype", "inner");
    model.result("pg1").feature("glob3").set("showunitcombo", "off");
    model.result("pg1").feature("glob3").set("data", "parent");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").label("\u7535\u6c60\u8377\u7535\u72b6\u6001 (lb)");
    model.result("pg2").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result("pg2").set("showsecylabel", "on");
    model.result("pg2").set("twoyaxes", true);
    model.result("pg2").set("legendpos", "lowerleft");
    model.result("pg2").feature().create("glob1", "Global");
    model.result("pg2").feature("glob1").set("showsolutionparams", "on");
    model.result("pg2").feature("glob1").set("expr", new String[]{"lb.SOC_cell"});
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
    model.result("pg2").feature("glob2").set("plotonsecyaxis", true);
    model.result("pg2").feature("glob2").set("expr", new String[]{"lb.I_cell"});
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
    model.result("pg1").run();
    model.result("pg1").label("\u7535\u6c60\u7535\u538b");
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("twoyaxes", false);
    model.result("pg1").set("legendpos", "lowerright");
    model.result("pg1").run();
    model.result("pg1").feature("glob1").setIndex("descr", "\u6a21\u62df\u7684\u7535\u6c60\u7535\u538b", 0);
    model.result("pg1").run();
    model.result("pg1").feature("glob3").set("expr", new String[]{"E_cell_exp"});
    model.result("pg1").feature("glob3").set("descr", new String[]{"\u5b9e\u9a8c\u7535\u6c60\u7535\u538b"});
    model.result("pg1").feature("glob3").set("unit", new String[]{"V"});
    model.result("pg1").run();
    model.result("pg1").set("axislimits", true);
    model.result("pg1").set("xmax", 305);
    model.result("pg1").set("ymin", 3.35);
    model.result("pg1").set("ymax", 4.2);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u7535\u538b\u635f\u8017\u548c\u8d1f\u8f7d");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").set("expr", new String[]{"lb.eta_ir"});
    model.result("pg3").feature("glob1").set("descr", new String[]{"\u6b27\u59c6\u8fc7\u7535\u4f4d"});
    model.result("pg3").feature("glob1").set("unit", new String[]{"V"});
    model.result("pg3").feature("glob1").set("expr", new String[]{"lb.eta_ir", "lb.eta_act"});
    model.result("pg3").feature("glob1")
         .set("descr", new String[]{"\u6b27\u59c6\u8fc7\u7535\u4f4d", "\u6d3b\u5316\u8fc7\u7535\u4f4d"});
    model.result("pg3").feature("glob1").set("expr", new String[]{"lb.eta_ir", "lb.eta_act", "lb.eta_conc"});
    model.result("pg3").feature("glob1")
         .set("descr", new String[]{"\u6b27\u59c6\u8fc7\u7535\u4f4d", "\u6d3b\u5316\u8fc7\u7535\u4f4d", "\u6d53\u5ea6\u8fc7\u7535\u4f4d"});
    model.result("pg3").feature("glob1").set("xdata", "expr");
    model.result("pg3").feature("glob1").set("xdataexpr", "t");
    model.result("pg3").run();
    model.result("pg3").create("glob2", "Global");
    model.result("pg3").feature("glob2").set("markerpos", "datapoints");
    model.result("pg3").feature("glob2").set("linewidth", "preference");
    model.result("pg3").feature("glob2").set("expr", new String[]{"lb.I_cell"});
    model.result("pg3").feature("glob2").set("descr", new String[]{"\u7535\u6c60\u7535\u6d41"});
    model.result("pg3").feature("glob2").set("unit", new String[]{"A"});
    model.result("pg3").feature("glob2").set("xdata", "expr");
    model.result("pg3").feature("glob2").set("xdataexpr", "t");
    model.result("pg3").feature("glob2").set("linestyle", "dotted");
    model.result("pg3").feature("glob2").set("linecolor", "black");
    model.result("pg3").run();
    model.result("pg3").set("titletype", "none");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "\u8fc7\u7535\u538b (V)");
    model.result("pg3").set("twoyaxes", true);
    model.result("pg3").setIndex("plotonsecyaxis", true, 1, 1);
    model.result("pg3").set("axislimits", true);
    model.result("pg3").set("xmax", 305);
    model.result("pg3").set("ymin", -0.4);
    model.result("pg3").set("ymax", 0.2);
    model.result("pg3").set("yminsec", -350);
    model.result("pg3").set("ymaxsec", 200);
    model.result("pg3").set("legendpos", "lowerleft");
    model.result("pg3").run();

    model.component("comp1").physics("lb").feature("vl1").set("minput_temperature", "T");
    model.component("comp1").physics("lb").feature("vl1").set("eta_ir1C", "eta_IR_1C");
    model.component("comp1").physics("lb").feature("vl1").set("J0", "J0");
    model.component("comp1").physics("lb").feature("vl1").set("tau", "tau");

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/lb", true);
    model.study("std2").label("\u7814\u7a76 2 - \u53c2\u6570\u4f30\u8ba1");
    model.study("std2").setGenPlots(false);
    model.study("std2").create("lsqo", "LSQOptimization");
    model.study("std2").feature("lsqo").set("source", "resultTable");
    model.study("std2").feature("lsqo").setEntry("columnType", "col3", "none");
    model.study("std2").feature("lsqo").setEntry("modelExpression", "col2", "comp1.lb.E_cell");
    model.study("std2").feature("lsqo").setEntry("unit", "col2", "V");
    model.study("std2").feature("lsqo").setIndex("pname", "eta_IR_1C", 0);
    model.study("std2").feature("lsqo").setIndex("initval", "10[mV]", 0);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 0);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 0);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 0);
    model.study("std2").feature("lsqo").setIndex("pname", "eta_IR_1C", 0);
    model.study("std2").feature("lsqo").setIndex("initval", "10[mV]", 0);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 0);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 0);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 0);
    model.study("std2").feature("lsqo").setIndex("pname", "invJ0", 1);
    model.study("std2").feature("lsqo").setIndex("initval", 1, 1);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 1);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 1);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 1);
    model.study("std2").feature("lsqo").setIndex("pname", "invJ0", 1);
    model.study("std2").feature("lsqo").setIndex("initval", 1, 1);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 1);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 1);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 1);
    model.study("std2").feature("lsqo").setIndex("pname", "tau", 2);
    model.study("std2").feature("lsqo").setIndex("initval", "1000[s]", 2);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 2);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 2);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 2);
    model.study("std2").feature("lsqo").setIndex("pname", "tau", 2);
    model.study("std2").feature("lsqo").setIndex("initval", "1000[s]", 2);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 2);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 2);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 2);
    model.study("std2").feature("lsqo").setIndex("scale", 0.01, 0);
    model.study("std2").feature("lsqo").setIndex("scale", 1000, 2);
    model.study("std2").feature("lsqo").set("lsqdatamethod", "lsq");

    model.component("comp1").probe().create("var1", "GlobalVariable");
    model.component("comp1").probe("var1").set("expr", "eta_IR_1C");
    model.component("comp1").probe().create("var2", "GlobalVariable");
    model.component("comp1").probe("var2").set("expr", "invJ0");
    model.component("comp1").probe().create("var3", "GlobalVariable");
    model.component("comp1").probe("var3").set("expr", "tau");

    model.study("std2").createAutoSequences("all");

    model.component("comp1").probe("var1").genResult("none");
    model.component("comp1").probe("var2").genResult("none");
    model.component("comp1").probe("var3").genResult("none");

    model.sol("sol2").runAll();

    model.study("std2").feature("lsqo").set("probewindow", "");

    model.result("pg1").run();
    model.result("pg1").set("data", "dset2");
    model.result("pg1").run();
    model.result("pg3").run();
    model.result("pg3").set("data", "dset2");
    model.result("pg3").run();

    model.study("std2").feature("lsqo").set("plot", true);

    model.result().table().create("tbl4", "Table");
    model.result().table("tbl4").label("\u6ee1\u8d1f\u8f7d\u5faa\u73af\u6570\u636e");
    model.result().table("tbl4").importData("lumped_li_battery_parameter_estimation_E_I_vs_t_fulldata.txt");

    model.component("comp1").func().create("int2", "Interpolation");
    model.component("comp1").func("int2").label("\u63d2\u503c - E \u548c I vs. t\uff08\u6ee1\u8d1f\u8f7d\uff09");
    model.component("comp1").func("int2").set("source", "resultTable");
    model.component("comp1").func("int2").set("resultTable", "tbl4");
    model.component("comp1").func("int2").setIndex("argunit", "s", 0);
    model.component("comp1").func("int2").setEntry("funcnames", "col2", "E_cell_exp_full");
    model.component("comp1").func("int2").setEntry("columnType", "col3", "value");
    model.component("comp1").func("int2").setEntry("funcnames", "col3", "I_cell_exp_full");

    model.component("comp1").variable().duplicate("var2", "var1");
    model.component("comp1").variable("var2").set("I_cell_exp", "I_cell_exp_full(t)[A]");
    model.component("comp1").variable("var2")
         .descr("I_cell_exp", "\u5b9e\u9a8c\u7535\u6c60\u7535\u6d41 - \u6ee1\u8d1f\u8f7d\u5faa\u73af");
    model.component("comp1").variable("var2").set("E_cell_exp", "E_cell_exp_full(t)[V]");
    model.component("comp1").variable("var2")
         .descr("E_cell_exp", "\u5b9e\u9a8c\u7535\u6c60\u7535\u538b - \u6ee1\u8d1f\u8f7d\u5faa\u73af");

    model.study().create("std3");
    model.study("std3").create("time", "Transient");
    model.study("std3").feature("time").setSolveFor("/physics/lb", true);
    model.study("std3").label("\u7814\u7a76 3 - \u6ee1\u8d1f\u8f7d\u66f2\u7ebf\u4f30\u8ba1");
    model.study("std3").feature("time").set("tlist", "range(0,1,600)");
    model.study("std3").feature("time").set("usertol", true);
    model.study("std3").feature("time").set("rtol", 0.001);
    model.study("std3").feature("time").set("useadvanceddisable", true);
    model.study("std3").feature("time").set("disabledvariables", new String[]{"var1"});
    model.study("std3").feature("time").set("useinitsol", true);
    model.study("std3").feature("time").set("usesol", true);
    model.study("std3").feature("time").set("notsolmethod", "sol");
    model.study("std3").feature("time").set("notstudy", "std2");
    model.study("std3").setGenPlots(false);
    model.study("std1").feature("time").set("useadvanceddisable", true);
    model.study("std1").feature("time").set("disabledvariables", new String[]{"var2"});
    model.study("std2").feature("time").set("useadvanceddisable", true);
    model.study("std2").feature("time").set("disabledvariables", new String[]{"var2"});
    model.study("std3").createAutoSequences("all");

    model.component("comp1").probe("var1").genResult("none");
    model.component("comp1").probe("var2").genResult("none");
    model.component("comp1").probe("var3").genResult("none");

    model.sol("sol3").runAll();

    model.result("pg1").run();
    model.result().duplicate("pg5", "pg1");
    model.result("pg5").run();
    model.result("pg5").label("\u7535\u6c60\u7535\u538b\uff1a\u6ee1\u8d1f\u8f7d\u5faa\u73af\u4f30\u8ba1");
    model.result("pg5").set("data", "dset4");
    model.result("pg5").set("xmax", 610);
    model.result("pg5").set("legendpos", "lowerleft");
    model.result("pg5").run();
    model.result("pg5").feature("glob1").setIndex("descr", "\u4f30\u8ba1\u7684\u7535\u6c60\u7535\u538b", 0);
    model.result("pg5").run();
    model.result().numerical().create("gev4", "EvalGlobal");
    model.result().numerical("gev4").label("\u5168\u5c40\u8ba1\u7b97\uff1a\u6807\u51c6\u504f\u5dee (Study1)");
    model.result().numerical("gev4").setIndex("expr", "lb.E_cell-E_cell_exp", 0);
    model.result().numerical("gev4").set("dataseries", "stddev");
    model.result().table().create("tbl5", "Table");
    model.result().table("tbl5").comments("\u5168\u5c40\u8ba1\u7b97\uff1a\u6807\u51c6\u504f\u5dee (Study1)");
    model.result().numerical("gev4").set("table", "tbl5");
    model.result().numerical("gev4").setResult();
    model.result().numerical().duplicate("gev5", "gev4");
    model.result().numerical("gev5").label("\u5168\u5c40\u8ba1\u7b97\uff1a\u6807\u51c6\u504f\u5dee (Study2)");
    model.result().numerical("gev5").set("data", "dset2");
    model.result().numerical("gev5").set("table", "tbl5");
    model.result().numerical("gev5").appendResult();
    model.result().numerical().duplicate("gev6", "gev5");
    model.result().numerical("gev6").label("\u5168\u5c40\u8ba1\u7b97\uff1a\u6807\u51c6\u504f\u5dee (Study3)");
    model.result().numerical("gev6").set("data", "dset4");
    model.result().numerical("gev6").setIndex("looplevelinput", "manualindices", 0);
    model.result().numerical("gev6").setIndex("looplevelindices", "range(302,1,601)", 0);
    model.result().numerical("gev6").set("table", "tbl5");
    model.result().numerical("gev6").appendResult();

    model.title("\u77ac\u6001\u96c6\u603b\u7535\u6c60\u6a21\u578b\u7684\u53c2\u6570\u4f30\u8ba1");

    model
         .description("\u672c\u6559\u7a0b\u4f7f\u7528\u4e00\u79cd\u79f0\u4e3a\u201c\u9ed1\u5323\u5b50\u201d\u65b9\u6cd5\u7684\u6280\u672f\uff0c\u6839\u636e\u4e00\u5c0f\u7ec4\u96c6\u603b\u53c2\u6570\u6765\u5b9a\u4e49\u7535\u6c60\u6a21\u578b\uff0c\u5e76\u5047\u8bbe\u4e0d\u4e86\u89e3\u7535\u6c60\u7535\u6781\u7684\u5185\u90e8\u7ed3\u6784\u6216\u8bbe\u8ba1\uff0c\u4e5f\u4e0d\u4e86\u89e3\u6750\u6599\u7684\u9009\u62e9\u3002\n\n\u6a21\u578b\u8f93\u5165\u5305\u62ec\u7535\u6c60\u5bb9\u91cf\u3001\u521d\u59cb\u8377\u7535\u72b6\u6001 (SOC)\uff0c\u4ee5\u53ca\u5f00\u8def\u7535\u538b vs. SOC \u66f2\u7ebf\uff0c\u8fd9\u4e9b\u6570\u636e\u5c06\u4e0e\u8d1f\u8f7d\u5faa\u73af\u5b9e\u9a8c\u6570\u636e\u7ed3\u5408\u4f7f\u7528\u3002\n\n\u672c\u4f8b\u4f7f\u7528\u201c\u53c2\u6570\u4f30\u8ba1\u201d\u7814\u7a76\u6b65\u9aa4\u6765\u786e\u5b9a\u96c6\u603b\u53c2\u6570\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("lumped_li_battery_parameter_estimation.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
