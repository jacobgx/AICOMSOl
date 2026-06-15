/*
 * gaas_pn_junction_infrared_led_diode.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:51 by COMSOL 6.3.0.290. */
public class gaas_pn_junction_infrared_led_diode {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Semiconductor_Module\\Photonic_Devices_and_Sensors");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("semi", "Semiconductor", "geom1");

    model.study().create("std1");
    model.study("std1").create("semii", "SemiconductorInitialization");
    model.study("std1").feature("semii").set("ftplistmethod", "manual");
    model.study("std1").feature("semii").set("solnum", "auto");
    model.study("std1").feature("semii").set("notsolnum", "auto");
    model.study("std1").feature("semii").set("outputmap", new String[]{});
    model.study("std1").feature("semii").set("errestandadap", "adaption");
    model.study("std1").feature("semii").set("goalfunctype", "gfman");
    model.study("std1").feature("semii").set("gfunc", "1");
    model.study("std1").feature("semii").set("ngenAUX", "1");
    model.study("std1").feature("semii").set("goalngenAUX", "1");
    model.study("std1").feature("semii").set("ngenAUX", "1");
    model.study("std1").feature("semii").set("goalngenAUX", "1");
    model.study("std1").feature("semii").setSolveFor("/physics/semi", true);

    model.param().set("V_n", "0[V]");
    model.param().descr("V_n", "n \u578b\u7535\u538b");
    model.param().set("V_p", "1.5[V]");
    model.param().descr("V_p", "p \u578b\u7535\u538b");
    model.param().set("w_dom", "25[um]");
    model.param().descr("w_dom", "\u57df\u5bbd\u5ea6");
    model.param().set("h_dom", "10[um]");
    model.param().descr("h_dom", "\u57df\u9ad8\u5ea6");
    model.param().set("w_con_n", "w_dom/5");
    model.param().descr("w_con_n", "n \u578b\u63a5\u89e6\u5bbd\u5ea6");
    model.param().set("h_con_n", "h_dom/5");
    model.param().descr("h_con_n", "n \u578b\u63a5\u89e6\u9ad8\u5ea6");
    model.param().set("w_con_p", "w_dom/10");
    model.param().descr("w_con_p", "p \u578b\u63a5\u89e6\u5bbd\u5ea6");
    model.param().set("h_p", "h_dom/4");
    model.param().descr("h_p", "\u8868\u9762 p \u578b\u5c42\u9ad8\u5ea6");
    model.param().set("r_fill", "0.25[um]");
    model.param().descr("r_fill", "\u5706\u89d2\u534a\u5f84");

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"w_dom", "h_dom"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"w_con_n", "h_con_n"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"w_dom", "0"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("r1", "r2");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "h_dom", 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "w_con_p", 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "h_dom", 1, 1);
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("fil1").selection("point").set("uni1", 4);
    model.component("comp1").geom("geom1").feature("fil1").set("radius", "r_fill");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("SemicondMaterial", "SemicondMaterial", "Semiconductor material");
    model.component("comp1").material("mat1").propertyGroup()
         .create("JainRoulstonModel", "JainRoulstonModel", "Jain\u2013Roulston model");
    model.component("comp1").material("mat1").label("GaAs - Gallium Arsenide");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "330[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "5500[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"46[W/(m*K)]", "0", "0", "0", "46[W/(m*K)]", "0", "0", "0", "46[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"12.9", "0", "0", "0", "12.9", "0", "0", "0", "12.9"});
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").label("Semiconductor material");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").set("Eg0", "1.424[V]");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").set("chi0", "4.07[V]");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial")
         .set("Nv", "(T/1[K])^(3/2)*1.83e15[1/cm^3]");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial")
         .set("Nc", "(8.63e13*(T/1[K])^(3/2)*(1-1.93e-4*(T/1[K])-4.19e-8*(T/1[K])^2+21*exp(-0.29[V]*e_const/(k_B_const*T))+44*exp(-0.48[V]*e_const/(k_B_const*T))))[1/cm^3]");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").set("mun", "8500[cm^2/(V*s)]");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").set("mup", "400[cm^2/(V*s)]");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("JainRoulstonModel").label("Jain\u2013Roulston model");
    model.component("comp1").material("mat1").propertyGroup("JainRoulstonModel").set("An_jr", "16.5e-9[V]");
    model.component("comp1").material("mat1").propertyGroup("JainRoulstonModel").set("Bn_jr", "2.39e-7[V]");
    model.component("comp1").material("mat1").propertyGroup("JainRoulstonModel").set("Cn_jr", "91.4e-12[V]");
    model.component("comp1").material("mat1").propertyGroup("JainRoulstonModel").set("Ap_jr", "9.83e-9[V]");
    model.component("comp1").material("mat1").propertyGroup("JainRoulstonModel").set("Bp_jr", "3.9e-7[V]");
    model.component("comp1").material("mat1").propertyGroup("JainRoulstonModel").set("Cp_jr", "3.9e-12[V]");
    model.component("comp1").material("mat1").propertyGroup("JainRoulstonModel").set("Nref_jr", "1[1/cm^3]");
    model.component("comp1").material("mat1").propertyGroup("JainRoulstonModel").set("alpha_jr", "0.5");

    model.component("comp1").physics("semi").prop("ModelProperties").set("CarrierStatistics", "FermiDirac");
    model.component("comp1").physics("semi").create("adm1", "AnalyticDopingModel", 2);
    model.component("comp1").physics("semi").feature("adm1").label("p \u578b\u63ba\u6742");
    model.component("comp1").physics("semi").feature("adm1").selection().all();
    model.component("comp1").physics("semi").feature("adm1").set("impurityDistribution", "box");
    model.component("comp1").physics("semi").feature("adm1").set("NAc", "1e18[1/cm^3]");
    model.component("comp1").physics("semi").feature("adm1").set("rb", new String[]{"0[um]", "0", "h_dom-h_p"});
    model.component("comp1").physics("semi").feature("adm1").set("jwidth", "w_dom");
    model.component("comp1").physics("semi").feature("adm1").set("jheight", "h_p");
    model.component("comp1").physics("semi").feature("adm1").set("JunctionOrLength", "decay_length");
    model.component("comp1").physics("semi").feature("adm1").set("ls", "2[um]");
    model.component("comp1").physics("semi").create("adm2", "AnalyticDopingModel", 2);
    model.component("comp1").physics("semi").feature("adm2").label("n \u578b\u63ba\u6742");
    model.component("comp1").physics("semi").feature("adm2").selection().all();
    model.component("comp1").physics("semi").feature("adm2").set("impurityDistribution", "box");
    model.component("comp1").physics("semi").feature("adm2").set("impurityType", "donor");
    model.component("comp1").physics("semi").feature("adm2").set("NDc", "1e18[1/cm^3]");
    model.component("comp1").physics("semi").feature("adm2").set("jwidth", "w_dom+w_con_n");
    model.component("comp1").physics("semi").feature("adm2").set("jheight", "h_dom-h_p");
    model.component("comp1").physics("semi").feature("adm2").set("JunctionOrLength", "decay_length");
    model.component("comp1").physics("semi").feature("adm2").set("ls", "2[um]");
    model.component("comp1").physics("semi").create("mc1", "MetalContact", 1);
    model.component("comp1").physics("semi").feature("mc1").label("p \u578b\u63a5\u89e6");
    model.component("comp1").physics("semi").feature("mc1").selection().set(3);
    model.component("comp1").physics("semi").feature("mc1").set("V0", "V_p");
    model.component("comp1").physics("semi").create("mc2", "MetalContact", 1);
    model.component("comp1").physics("semi").feature("mc2").label("n \u578b\u63a5\u89e6");
    model.component("comp1").physics("semi").feature("mc2").selection().set(7);
    model.component("comp1").physics("semi").feature("mc2").set("V0", "V_n");
    model.component("comp1").physics("semi").create("ot1", "OpticalTransitions", 2);
    model.component("comp1").physics("semi").feature("ot1").selection().set(1);
    model.component("comp1").physics("semi").feature("ot1").set("StimulatedEmission", false);
    model.component("comp1").physics("semi").create("aur1", "AURecombination", 2);
    model.component("comp1").physics("semi").feature("aur1").selection().set(1);

    model.component("comp1").material("mat1").propertyGroup().create("Auger", "Auger", "Auger_recombination");
    model.component("comp1").material("mat1").propertyGroup("Auger").set("Cn", new String[]{"1e-31[cm^6/s]"});
    model.component("comp1").material("mat1").propertyGroup("Auger").set("Cp", new String[]{"1e-31[cm^6/s]"});

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().set(1);

    model.study("std1").label("\u7814\u7a76 1\uff1a\u7f51\u683c\u7ec6\u5316");
    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.component("comp1").mesh("mesh1").run();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").run();
    model.result("pg1").label("\u63ba\u6742");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "semi.Nd-semi.Na");
    model.result("pg1").run();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/semi", true);
    model.study("std2").setGenPlots(false);
    model.study("std2").label("\u7814\u7a76 2\uff1a\u7535\u538b\u626b\u63cf");
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").setIndex("pname", "V_n", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "V", 0);
    model.study("std2").feature("stat").setIndex("pname", "V_n", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "V", 0);
    model.study("std2").feature("stat").setIndex("pname", "V_p", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "log(range(1,0.25,3.5)) log(range(3.6,0.1,4.5))", 0);
    model.study("std2").showAutoSequences("all");

    model.sol("sol5").feature("s1").create("se1", "Segregated");
    model.sol("sol5").feature("s1").feature("se1").feature("ssDef").set("subdtech", "auto");
    model.sol("sol5").feature("s1").feature("se1").create("ll1", "LowerLimit");
    model.sol("sol5").feature("s1").feature("se1").feature("ll1").set("lowerlimit", "comp1.Ne 1 comp1.Ph 1");

    model.study("std2").createAutoSequences("all");

    model.sol("sol5").runAll();

    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u7535\u6d41 vs. \u7535\u538b");
    model.result("pg2").set("data", "dset3");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "\u7535\u538b (V)");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u7535\u6d41 (mA)");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u7535\u6d41\u968f\u7535\u538b\u53d8\u5316");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").setIndex("expr", "semi.I0_1", 0);
    model.result("pg2").feature("glob1").setIndex("unit", "mA", 0);
    model.result("pg2").feature("glob1").set("legend", false);
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").run();
    model.result("pg3").label("\u53d1\u5c04\u7387");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "\u53d1\u5c04\u7387");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "semi.ot1.R_spon");
    model.result("pg3").feature("surf1").set("unit", "1/(cm^3*s)");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 11, 0);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u53d1\u5c04\u7387 vs. \u7535\u6d41");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").set("titletype", "manual");
    model.result("pg4").set("title", "\u603b\u53d1\u5c04\u7387\u4e0e\u7535\u6d41\u7684\u5173\u7cfb");
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "\u7535\u6d41 (mA)");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u603b\u53d1\u5c04\u7387 (1/s)");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").setIndex("expr", "intop1(semi.ot1.R_spon)", 0);
    model.result("pg4").feature("glob1").setIndex("descr", "", 0);
    model.result("pg4").feature("glob1").set("xdata", "expr");
    model.result("pg4").feature("glob1").set("xdataexpr", "semi.I0_1");
    model.result("pg4").feature("glob1").set("xdataunit", "mA");
    model.result("pg4").feature("glob1").set("legend", false);
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u5185\u91cf\u5b50\u6548\u7387");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").set("titletype", "manual");
    model.result("pg5").set("title", "\u5185\u91cf\u5b50\u6548\u7387\u4e0e\u7535\u6d41\u7684\u5173\u7cfb");
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "\u7535\u6d41 (mA)");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "\u5185\u91cf\u5b50\u6548\u7387");
    model.result("pg5").setIndex("looplevelinput", "manualindices", 0);
    model.result("pg5").setIndex("looplevelindices", "range(5,1,21)", 0);
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").setIndex("expr", "intop1(semi.ot1.R_spon)/(semi.I0_1/e_const)", 0);
    model.result("pg5").feature("glob1").set("xdata", "expr");
    model.result("pg5").feature("glob1").set("xdataexpr", "semi.I0_1");
    model.result("pg5").feature("glob1").set("xdataunit", "mA");
    model.result("pg5").feature("glob1").set("legend", false);
    model.result("pg5").run();
    model.result("pg5").set("axislimits", true);
    model.result("pg5").set("ymin", 0);
    model.result("pg5").run();
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "dset3");
    model.result().dataset("rev1").set("startangle", 270);
    model.result().dataset("rev1").set("revangle", 270);
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").run();
    model.result("pg6").label("\u4e09\u7ef4\u53d1\u5c04\u7387");
    model.result("pg6").set("titletype", "manual");
    model.result("pg6").set("title", "\u53d1\u5c04\u7387");
    model.result("pg6").create("vol1", "Volume");
    model.result("pg6").feature("vol1").set("expr", "semi.ot1.R_spon");
    model.result("pg6").run();

    model.title("\u7837\u5316\u9553 P-N \u7ed3\u7ea2\u5916\u53d1\u5149\u4e8c\u6781\u7ba1");

    model
         .description("\u672c\u4f8b\u6a21\u62df\u5728\u7535\u78c1\u6ce2\u8c31\u7684\u7ea2\u5916\u90e8\u5206\u53d1\u5149\u7684 LED\u3002\u8be5\u5668\u4ef6\u7ed3\u6784\u7531\u5355\u4e2a p-n \u7ed3\u7ec4\u6210\uff0c\u8fd9\u4e2a p-n \u7ed3\u7531\u4e00\u4e2a n \u578b\u6676\u7247\u9876\u9762\u9644\u8fd1\u7684 p \u578b\u63ba\u6742\u5c42\u5f62\u6210\u3002\u8fd9\u79cd\u5668\u4ef6\u7684\u51e0\u4f55\u7ed3\u6784\u5f88\u7b80\u5355\uff0c\u4e14\u751f\u4ea7\u6210\u672c\u4f4e\uff0c\u56e0\u6b64\uff0c\u7c7b\u4f3c\u7684 LED \u88ab\u5e7f\u6cdb\u7528\u4e8e\u7535\u89c6\u9065\u63a7\u5668\u7684\u7ea2\u5916\u53d1\u5c04\u5668\u7b49\u8bb8\u591a\u5bb6\u7528\u7535\u5668\u4e2d\u3002\u672c\u4f8b\u4f7f\u7528\u201c\u5149\u8dc3\u8fc1\u201d\u7279\u5f81\u8ba1\u7b97\u8be5\u5668\u4ef6\u7684\u7535\u81f4\u53d1\u5149\uff0c\u5176\u4e2d\u8ba1\u7b97\u4e86\u7535\u5b50\u5c5e\u6027\uff0c\u5e76\u5bf9\u5149\u7684\u4ea7\u751f\u6548\u7387\u8fdb\u884c\u8bc4\u4f30\u3002\u6b64\u5916\uff0c\u901a\u8fc7\u5c06\u8f90\u5c04\u590d\u5408\u7684\u7a7a\u95f4\u5206\u5e03\u8fdb\u884c\u53ef\u89c6\u5316\uff0c\u8fd8\u53ef\u4ee5\u63d0\u51fa\u8bbe\u8ba1\u5efa\u8bae\uff0c\u4ece\u800c\u6700\u5927\u9650\u5ea6\u5730\u63d0\u9ad8\u8f93\u51fa\u5149\u7684\u603b\u6548\u7387\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();

    model.label("gaas_pn_junction_infrared_led_diode.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
