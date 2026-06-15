/*
 * gaas_pin_photodiode.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:51 by COMSOL 6.3.0.290. */
public class gaas_pin_photodiode {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Semiconductor_Module\\Photonic_Devices_and_Sensors");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("semi", "Semiconductor", "geom1");
    model.component("comp1").physics("semi").create("ot1", "OpticalTransitions");
    model.component("comp1").physics("semi").feature("ot1").selection().all();
    model.component("comp1").physics().create("ewfd", "ElectromagneticWavesFrequencyDomain", "geom1");

    model.component("comp1").multiphysics().create("semc1", "SemiconductorElectromagneticWavesCoupling", 2);
    model.component("comp1").multiphysics("semc1").set("Semiconductor_physics", "semi");
    model.component("comp1").multiphysics("semc1").set("ElectromagneticWaves_physics", "ewfd");

    model.study().create("std1");
    model.study("std1").create("fstat", "FrequencyStationary");
    model.study("std1").feature("fstat").set("ftplistmethod", "manual");
    model.study("std1").feature("fstat").set("freq", "3.6E14");
    model.study("std1").feature("fstat").set("solnum", "auto");
    model.study("std1").feature("fstat").set("notsolnum", "auto");
    model.study("std1").feature("fstat").set("outputmap", new String[]{});
    model.study("std1").feature("fstat").set("ngenAUX", "1");
    model.study("std1").feature("fstat").set("goalngenAUX", "1");
    model.study("std1").feature("fstat").set("ngenAUX", "1");
    model.study("std1").feature("fstat").set("goalngenAUX", "1");
    model.study("std1").feature("fstat").setSolveFor("/physics/semi", true);
    model.study("std1").feature("fstat").setSolveFor("/physics/ewfd", true);
    model.study("std1").feature("fstat").setSolveFor("/multiphysics/semc1", true);

    model.param().set("w_dom", "5[um]");
    model.param().descr("w_dom", "\u5bbd\u5ea6");
    model.param().set("h_dom", "1[um]");
    model.param().descr("h_dom", "\u539a\u5ea6");
    model.param().set("V_n", "2[V]");
    model.param().descr("V_n", "n \u578b\u63a5\u89e6\u7535\u538b");
    model.param().set("V_p", "0[V]");
    model.param().descr("V_p", "p \u578b\u63a5\u89e6\u7535\u538b");
    model.param().set("hbar0", "h_const/(2*pi)");
    model.param().descr("hbar0", "\u65e0\u5f27\u5ea6\u5355\u4f4d\u7684 hbar");
    model.param().set("lda0", "870[nm]");
    model.param().descr("lda0", "\u5165\u5c04\u6ce2\u957f");
    model.param().set("f0", "c_const/lda0");
    model.param().descr("f0", "\u5165\u5c04\u9891\u7387");
    model.param().set("omega0", "2*pi*1[rad]*f0");
    model.param().descr("omega0", "\u5165\u5c04\u89d2\u9891\u7387");
    model.param().set("E_ph", "f0*h_const");
    model.param().descr("E_ph", "\u5165\u5c04\u5149\u5b50\u80fd\u91cf");
    model.param().set("n0", "3.5");
    model.param().descr("n0", "\u7837\u5316\u9553\u6298\u5c04\u7387\uff08\u5b9e\u90e8\uff09");
    model.param().set("tau", "2[ns]");
    model.param().descr("tau", "\u81ea\u53d1\u5bff\u547d");
    model.param().set("d0", "1[um]");
    model.param()
         .descr("d0", "\u201c\u534a\u5bfc\u4f53\u201d\u63a5\u53e3\u7684\u9762\u5916\u539a\u5ea6\uff08\u201c\u6ce2\u52a8\u5149\u5b66\u201d\u63a5\u53e3\u4e2d\u56fa\u5b9a\u4e3a 1 m\uff09");
    model.param().set("Pin", "10[uW]");
    model.param().descr("Pin", "\u9762\u5916\u539a\u5ea6 d0 = 1 um \u65f6\u7684\u5165\u5c04\u529f\u7387");

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("semi").prop("d").set("d", "d0");
    model.component("comp1").physics("semi").prop("ModelProperties").set("CarrierStatistics", "FermiDirac");

    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"w_dom", "h_dom"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("semi").create("adm1", "AnalyticDopingModel", 2);
    model.component("comp1").physics("semi").feature("adm1").label("\u6052\u5b9a\u7684 p \u578b\u63ba\u6742");
    model.component("comp1").physics("semi").feature("adm1").selection().set(1);
    model.component("comp1").physics("semi").feature("adm1").set("NAc", "1e14[1/cm^3]");
    model.component("comp1").physics("semi").create("adm2", "AnalyticDopingModel", 2);
    model.component("comp1").physics("semi").feature("adm2").label("p \u578b\u63ba\u6742");
    model.component("comp1").physics("semi").feature("adm2").selection().set(1);
    model.component("comp1").physics("semi").feature("adm2").set("impurityDistribution", "box");
    model.component("comp1").physics("semi").feature("adm2").set("NAc", "1e18[1/cm^3]");
    model.component("comp1").physics("semi").feature("adm2")
         .set("rb", new String[]{"0[um]", "h_dom-0.1*h_dom", "0"});
    model.component("comp1").physics("semi").feature("adm2").set("jwidth", "w_dom");
    model.component("comp1").physics("semi").feature("adm2").set("jdepth", "0.1*h_dom");
    model.component("comp1").physics("semi").feature("adm2").set("jds", "0.15*h_dom");
    model.component("comp1").physics("semi").feature("adm2").set("Nb_src", "root.comp1.semi.adm1.NAc");
    model.component("comp1").physics("semi").create("gdm1", "GeometricDopingModel", 2);
    model.component("comp1").physics("semi").feature("gdm1").label("p+ \u578b\u63ba\u6742");
    model.component("comp1").physics("semi").feature("gdm1").selection().set(1);
    model.component("comp1").physics("semi").feature("gdm1").set("NAgen", "1e20[1/cm^3]");
    model.component("comp1").physics("semi").feature("gdm1").set("jd_gen", "0.1*h_dom");
    model.component("comp1").physics("semi").feature("gdm1").set("Nb_gen_src", "root.comp1.semi.adm1.NAc");
    model.component("comp1").physics("semi").feature("gdm1").feature("gdmbs1").selection().set(3);
    model.component("comp1").physics("semi").create("adm3", "AnalyticDopingModel", 2);
    model.component("comp1").physics("semi").feature("adm3").label("n \u578b\u63ba\u6742");
    model.component("comp1").physics("semi").feature("adm3").selection().set(1);
    model.component("comp1").physics("semi").feature("adm3").set("impurityDistribution", "box");
    model.component("comp1").physics("semi").feature("adm3").set("impurityType", "donor");
    model.component("comp1").physics("semi").feature("adm3").set("NDc", "1e18[1/cm^3]");
    model.component("comp1").physics("semi").feature("adm3").set("jwidth", "w_dom");
    model.component("comp1").physics("semi").feature("adm3").set("jdepth", "0.1*h_dom");
    model.component("comp1").physics("semi").feature("adm3").set("jds", "0.15*h_dom");
    model.component("comp1").physics("semi").feature("adm3").set("Nb_src", "root.comp1.semi.adm1.NAc");
    model.component("comp1").physics("semi").create("gdm2", "GeometricDopingModel", 2);
    model.component("comp1").physics("semi").feature("gdm2").label("n+ \u578b\u63ba\u6742");
    model.component("comp1").physics("semi").feature("gdm2").selection().set(1);
    model.component("comp1").physics("semi").feature("gdm2").set("impurityType", "donor");
    model.component("comp1").physics("semi").feature("gdm2").set("NDgen", "1e20[1/cm^3]");
    model.component("comp1").physics("semi").feature("gdm2").set("jd_gen", "0.1*h_dom");
    model.component("comp1").physics("semi").feature("gdm2").set("Nb_gen_src", "root.comp1.semi.adm1.NAc");
    model.component("comp1").physics("semi").feature("gdm2").feature("gdmbs1").selection().set(2);
    model.component("comp1").physics("semi").create("mc1", "MetalContact", 1);
    model.component("comp1").physics("semi").feature("mc1").label("p \u578b\u63a5\u89e6");
    model.component("comp1").physics("semi").feature("mc1").selection().set(3);
    model.component("comp1").physics("semi").feature("mc1").set("V0", "V_p");
    model.component("comp1").physics("semi").create("mc2", "MetalContact", 1);
    model.component("comp1").physics("semi").feature("mc2").label("n \u578b\u63a5\u89e6");
    model.component("comp1").physics("semi").feature("mc2").selection().set(2);
    model.component("comp1").physics("semi").feature("mc2").set("V0", "V_n");

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
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex1", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex1")
         .set("n", new String[][]{{"sqrt(0.5*(real(root.comp1.mat1.def.epsilonr_iso)+sqrt(real(root.comp1.mat1.def.epsilonr_iso)^2+(-imag(root.comp1.mat1.def.epsilonr_iso))^2)))"}});
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex1")
         .set("ki", new String[][]{{"-0.5*imag(root.comp1.mat1.def.epsilonr_iso)/sqrt(0.5*(real(root.comp1.mat1.def.epsilonr_iso)+sqrt(real(root.comp1.mat1.def.epsilonr_iso)^2+(-imag(root.comp1.mat1.def.epsilonr_iso))^2)))"}});
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex1").set("n", new String[]{"n0"});
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex1").set("ki", new String[]{"0"});

    model.component("comp1").physics("semi").feature("ot1").set("tau_spon", "tau");
    model.component("comp1").physics("ewfd").prop("components").set("components", "outofplane");
    model.component("comp1").physics("ewfd").create("port1", "Port", 1);
    model.component("comp1").physics("ewfd").feature("port1").selection().set(3);
    model.component("comp1").physics("ewfd").feature("port1").set("Pin", "Pin/d0");
    model.component("comp1").physics("ewfd").create("port2", "Port", 1);
    model.component("comp1").physics("ewfd").feature("port2").selection().set(2);
    model.component("comp1").physics("ewfd").create("pc1", "PeriodicCondition", 1);
    model.component("comp1").physics("ewfd").feature("pc1").selection().set(1, 4);
    model.component("comp1").physics("ewfd").feature("init1").set("E", new int[]{0, 0, 1});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(3);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 1);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 500);
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().all();
    model.component("comp1").cpl("intop1").set("intorder", 1);

    model.study("std1").label("\u7814\u7a76 1\uff1a\u6ce2\u957f\u626b\u63cf");
    model.study("std1").setGenPlots(false);
    model.study("std1").feature("fstat").set("freq", "f0");
    model.study("std1").feature("fstat").set("useparam", true);
    model.study("std1").feature("fstat").setIndex("pname", "w_dom", 0);
    model.study("std1").feature("fstat").setIndex("plistarr", "", 0);
    model.study("std1").feature("fstat").setIndex("punit", "m", 0);
    model.study("std1").feature("fstat").setIndex("pname", "w_dom", 0);
    model.study("std1").feature("fstat").setIndex("plistarr", "", 0);
    model.study("std1").feature("fstat").setIndex("punit", "m", 0);
    model.study("std1").feature("fstat").setIndex("pname", "lda0", 0);
    model.study("std1").feature("fstat").setIndex("plistarr", "range(875[nm],-10[nm],475[nm])", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").label("\u63ba\u6742\u5206\u5e03");
    model.result("pg1").setIndex("looplevelinput", "first", 0);
    model.result("pg1").create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg1").feature("lngr1").set("linewidth", "preference");
    model.result("pg1").feature("lngr1").selection().set(1);
    model.result("pg1").feature("lngr1").set("expr", "semi.Nd-semi.Na");
    model.result("pg1").feature("lngr1").set("unit", "1/cm^3");
    model.result("pg1").feature("lngr1").set("xdata", "reversedarc");
    model.result("pg1").run();
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "\u6df1\u5ea6 (um)");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u80fd\u7ea7\u56fe");
    model.result("pg2").setIndex("looplevelinput", "last", 0);
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg2").feature("lngr1").set("linewidth", "preference");
    model.result("pg2").feature("lngr1").selection().set(1);
    model.result("pg2").feature("lngr1").set("expr", "semi.Ec_e");
    model.result("pg2").feature("lngr1").set("unit", "eV");
    model.result("pg2").feature("lngr1").set("xdata", "reversedarc");
    model.result("pg2").feature("lngr1").set("legendmethod", "manual");
    model.result("pg2").feature("lngr1").setIndex("legends", "\u5bfc\u5e26", 0);
    model.result("pg2").feature("lngr1").set("legend", true);
    model.result("pg2").feature("lngr1").label("\u5bfc\u5e26");
    model.result("pg2").feature().duplicate("lngr2", "lngr1");
    model.result("pg2").run();
    model.result("pg2").feature("lngr2").label("\u7535\u5b50\u51c6\u8d39\u7c73\u80fd");
    model.result("pg2").feature("lngr2").set("expr", "semi.Efn_e");
    model.result("pg2").feature("lngr2").setIndex("legends", "\u7535\u5b50\u8d39\u7c73\u80fd\u7ea7", 0);
    model.result("pg2").feature("lngr2").set("linestyle", "dashed");
    model.result("pg2").feature("lngr2").set("linecolor", "blue");
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("lngr3", "lngr1");
    model.result("pg2").run();
    model.result("pg2").feature("lngr3").label("\u4ef7\u5e26");
    model.result("pg2").feature("lngr3").set("expr", "semi.Ev_e");
    model.result("pg2").feature("lngr3").set("linecolor", "black");
    model.result("pg2").feature("lngr3").setIndex("legends", "\u4ef7\u5e26", 0);
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("lngr4", "lngr2");
    model.result("pg2").run();
    model.result("pg2").feature("lngr4").set("expr", "semi.Efp_e");
    model.result("pg2").feature("lngr4").set("linecolor", "black");
    model.result("pg2").feature("lngr4").setIndex("legends", "\u7a7a\u7a74\u8d39\u7c73\u80fd\u7ea7", 0);
    model.result("pg2").run();
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "\u6df1\u5ea6 (um)");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u80fd\u91cf (eV)");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u80fd\u7ea7\u56fe");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u7535\u6d41\u968f\u6ce2\u957f\u7684\u53d8\u5316");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").setIndex("expr", "abs(semi.I0_1)/Pin", 0);
    model.result("pg3").feature("glob1").setIndex("unit", "A/W", 0);
    model.result("pg3").feature("glob1").setIndex("descr", "\u7535\u6d41/\u5149\u529f\u7387", 0);
    model.result("pg3").feature("glob1").set("xdataparamunit", "nm");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u7535\u573a");
    model.result("pg4").setIndex("looplevelinput", "manual", 0);
    model.result("pg4").setIndex("looplevel", new int[]{16}, 0);
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr1").set("linewidth", "preference");
    model.result("pg4").feature("lngr1").selection().set(1);
    model.result("pg4").feature("lngr1").set("xdata", "reversedarc");
    model.result("pg4").run();
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "\u6df1\u5ea6 (um)");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u7535\u573a\u5f3a\u5ea6 (V/m)");
    model.result("pg4").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").label("\u8ba1\u7b97\u7ec4 1 - \u68c0\u67e5\u7c92\u5b50\u5b88\u6052");
    model.result().evaluationGroup("eg1").setIndex("looplevelinput", "manual", 0);
    model.result().evaluationGroup("eg1").setIndex("looplevel", new int[]{16}, 0);
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("expr", "(1-abs(ewfd.S11)^2-abs(ewfd.S21)^2)*Pin/h_const/freq", 0);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "\u5438\u6536\u7387\uff08\u7aef\u53e3\uff09", 0);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("expr", "intop1(semi.ot1.G_stim-semi.ot1.R_spon)*d0", 1);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "\u5438\u6536\u7387\uff08\u5149\u8dc3\u8fc1\uff09", 1);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "semi.I0_2/e_const", 2);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "\u5e26\u7535\u7c92\u5b50\u5230\u8fbe\u7387", 2);
    model.result().evaluationGroup("eg1").run();
    model.result().dataset().create("dset2", "Solution");
    model.result().dataset("dset2").label("\u7814\u7a76 1\uff1a\u989d\u5916\u7ef4\u5ea6");
    model.result().dataset("dset2").set("comp", "semi_ot1_xdim");
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u81ea\u53d1\u53d1\u5c04");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").setIndex("looplevelinput", "manual", 0);
    model.result("pg5").setIndex("looplevel", new int[]{16}, 0);
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg5").feature("lngr1").set("linewidth", "preference");
    model.result("pg5").feature("lngr1").selection().all();
    model.result("pg5").feature("lngr1").set("expr", "comp1.atxd2(0,0.5e-6,semi.ot1.dP_dE)");
    model.result("pg5").feature("lngr1").set("descractive", true);
    model.result("pg5").feature("lngr1")
         .set("descr", "\u5355\u4f4d\u4f53\u79ef\u548c\u5355\u4f4d\u80fd\u91cf\u7684\u603b\u53d1\u5c04\u529f\u7387");
    model.result("pg5").feature("lngr1").set("xdata", "expr");
    model.result("pg5").feature("lngr1").set("xdataexpr", "hbar_const*comp1.atxd2(0,0.5e-6,semi.ot1.omega)");
    model.result("pg5").feature("lngr1").set("xdataunit", "eV");
    model.result("pg5").feature("lngr1").set("xdatadescractive", true);
    model.result("pg5").feature("lngr1").set("xdatadescr", "\u5149\u5b50\u80fd\u91cf");
    model.result("pg5").run();
    model.result("pg5").set("titletype", "manual");
    model.result("pg5").set("title", "\u81ea\u53d1\u53d1\u5c04");
    model.result("pg5").run();

    model.title("\u7837\u5316\u9553 PIN \u5149\u7535\u4e8c\u6781\u7ba1");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u201c\u534a\u5bfc\u4f53\u5149\u7535\u5b50\u5b66\u201d\u63a5\u53e3\u6765\u5bf9\u7b80\u5355\u7684\u7837\u5316\u9553 PIN \u4e8c\u6781\u7ba1\u7ed3\u6784\u5efa\u6a21\uff0c\u5176\u4e2d\u8003\u8651\u4e86\u534a\u5bfc\u4f53\u4e2d\u7684\u6fc0\u53d1\u548c\u81ea\u53d1\u53d1\u5c04\uff0c\u901a\u8fc7\u81ea\u6d3d\u7684\u5f62\u5f0f\u5f15\u5165\u4e86\u76f8\u5e94\u7684\u5149\u5438\u6536\u548c\u590d\u6570\u6298\u5c04\u7387\u7684\u53d8\u5316\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("gaas_pin_photodiode.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
