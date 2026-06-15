/*
 * mesfet.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:55 by COMSOL 6.3.0.290. */
public class mesfet {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Semiconductor_Module\\Transistors");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("semi", "Semiconductor", "geom1");

    model.param().set("L", "1[um]");
    model.param().descr("L", "\u6805\u6781\u957f\u5ea6");
    model.param().set("Wd", "4[um]");
    model.param().descr("Wd", "\u5668\u4ef6\u5bbd\u5ea6");
    model.param().set("Hd", "0.5[um]");
    model.param().descr("Hd", "\u5668\u4ef6\u9ad8\u5ea6");
    model.param().set("Ws", "1[um]");
    model.param().descr("Ws", "\u6e90\u6781\u5bbd\u5ea6");
    model.param().set("Wdd", "1[um]");
    model.param().descr("Wdd", "\u6f0f\u6781\u5bbd\u5ea6");
    model.param().set("Vg", "0[V]");
    model.param().descr("Vg", "\u6805\u538b");
    model.param().set("Vd", "0[V]");
    model.param().descr("Vd", "\u6f0f\u6781\u7535\u538b");
    model.param().set("Vs", "0[V]");
    model.param().descr("Vs", "\u6e90\u6781\u7535\u538b");
    model.param().set("Nd", "1e16[1/cm^3]");
    model.param().descr("Nd", "\u63ba\u6742\u6d53\u5ea6");

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"Wd", "Hd"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"-Wd/2", "0"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "-Wd/2+Ws/2 -L/2 L/2 Wd/2-Ws/2", 0);
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "Hd Hd Hd Hd", 1);
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

    model.component("comp1").physics("semi").create("mc1", "MetalContact", 1);
    model.component("comp1").physics("semi").feature("mc1").set("ContactType", "Schottky");
    model.component("comp1").physics("semi").feature("mc1").selection().set(5);
    model.component("comp1").physics("semi").feature("mc1").set("V0", "-Vg");
    model.component("comp1").physics("semi").create("mc2", "MetalContact", 1);
    model.component("comp1").physics("semi").feature("mc2").selection().set(3);
    model.component("comp1").physics("semi").feature("mc2").set("V0", "Vs");
    model.component("comp1").physics("semi").create("mc3", "MetalContact", 1);
    model.component("comp1").physics("semi").feature("mc3").selection().set(7);
    model.component("comp1").physics("semi").feature("mc3").set("V0", "Vd");
    model.component("comp1").physics("semi").create("adm1", "AnalyticDopingModel", 2);
    model.component("comp1").physics("semi").feature("adm1").selection().set(1);
    model.component("comp1").physics("semi").feature("adm1").set("impurityType", "donor");
    model.component("comp1").physics("semi").feature("adm1").set("NDc", "Nd");
    model.component("comp1").physics("semi").create("tar1", "TrapAssistedRecombination", 2);
    model.component("comp1").physics("semi").feature("tar1").selection().set(1);
    model.component("comp1").physics("semi").feature("tar1").set("taun_mat", "userdef");
    model.component("comp1").physics("semi").feature("tar1").set("taup_mat", "userdef");
    model.component("comp1").physics().copy("semi2", "semi");
    model.component("comp1").physics("semi2").prop("ModelProperties").set("Compute", "MajorityPsi");

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size2").set("hauto", 3);
    model.component("comp1").mesh("mesh1").run();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/semi", true);
    model.study("std1").feature("stat").setSolveFor("/physics/semi2", true);
    model.study("std1").feature("stat").setSolveFor("/physics/semi2", false);
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "L", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "L", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "Vg", 0);
    model.study("std1").feature("stat").setIndex("pname", "L", 1);
    model.study("std1").feature("stat").setIndex("plistarr", "", 1);
    model.study("std1").feature("stat").setIndex("punit", "m", 1);
    model.study("std1").feature("stat").setIndex("pname", "L", 1);
    model.study("std1").feature("stat").setIndex("plistarr", "", 1);
    model.study("std1").feature("stat").setIndex("punit", "m", 1);
    model.study("std1").feature("stat").setIndex("plistarr", "0 1 2", 0);
    model.study("std1").feature("stat").setIndex("pname", "Vd", 1);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0,1,10)", 1);
    model.study("std1").feature("stat").set("sweeptype", "filled");
    model.study("std1").feature("stat").set("preusesol", "auto");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u7535\u5b50\u6d53\u5ea6 (semi)");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("expr", "semi.N");
    model.result("pg1").feature("surf1").set("unit", "1/cm^3");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg1").feature("surf1").set("resolution", "norefine");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u7a7a\u7a74\u6d53\u5ea6 (semi)");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("expr", "semi.P");
    model.result("pg2").feature("surf1").set("unit", "1/cm^3");
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg2").feature("surf1").set("resolution", "norefine");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u7535\u52bf (semi)");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("expr", "V");
    model.result("pg3").feature("surf1").set("resolution", "norefine");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").create("surf2", "Surface");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf2").set("expr", "semi.Nnetdop");
    model.result("pg4").feature("surf2").set("unit", "1/cm^3");
    model.result("pg4").feature("surf2").set("coloring", "gradient");
    model.result("pg4").feature("surf2").set("colorscalemode", "logarithmic");
    model.result("pg4").feature("surf2").set("topcolor", "red");
    model.result("pg4").feature("surf2").set("bottomcolor", "custom");
    model.result("pg4").feature("surf2").set("custombottomcolor", new double[]{1, 0.8, 0.8});
    model.result("pg4").feature("surf2").set("smooth", "internal");
    model.result("pg4").feature("surf2").set("showsolutionparams", "on");
    model.result("pg4").feature("surf2").set("data", "parent");
    model.result("pg4").feature("surf2").set("titletype", "none");
    model.result("pg4").feature("surf2").feature().create("filt1", "Filter");
    model.result("pg4").feature("surf2").feature("filt1").set("expr", "semi.Na-semi.Nd > 1[1/cm^3]");
    model.result("pg4").feature("surf2").feature("filt1").set("useder", true);
    model.result("pg4").feature("surf1").set("expr", "semi.Nnetdop");
    model.result("pg4").feature("surf1").set("unit", "1/cm^3");
    model.result("pg4").feature("surf1").set("coloring", "gradient");
    model.result("pg4").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg4").feature("surf1").set("topcolor", "blue");
    model.result("pg4").feature("surf1").set("bottomcolor", "custom");
    model.result("pg4").feature("surf1").set("custombottomcolor", new double[]{0.8, 0.8, 1});
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg4").feature("surf1").set("titletype", "none");
    model.result("pg4").feature("surf1").feature().create("filt1", "Filter");
    model.result("pg4").feature("surf1").feature("filt1").set("expr", "semi.Nd-semi.Na > 1[1/cm^3]");
    model.result("pg4").feature("surf1").feature("filt1").set("useder", true);
    model.result("pg4").set("titletype", "manual");
    model.result("pg4")
         .set("title", "\u51c0\u63ba\u6742\u5242\u6d53\u5ea6 \\vert N<sub>d</sub> - N<sub>a</sub>\\vert: P \u578b (\u7ea2\u8272), N \u578b (\u84dd\u8272)");
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").set("legendpos", "alternating");
    model.result("pg4").feature("surf2").label("P \u578b");
    model.result("pg4").feature("surf1").label("N \u578b");
    model.result("pg4").label("\u51c0\u63ba\u6742\u5242\u6d53\u5ea6 (semi)");
    model.result("pg1").run();
    model.result("pg4").run();
    model.result().remove("pg4");
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u6f0f\u6781\u7535\u6d41 vs. \u6f0f\u6781\u7535\u538b");
    model.result("pg4").set("legendpos", "upperleft");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").set("expr", new String[]{"semi.I0_3"});
    model.result("pg4").feature("glob1").set("descr", new String[]{"\u7ec8\u7aef\u7535\u6d41"});
    model.result("pg4").feature("glob1").set("unit", new String[]{"A"});
    model.result("pg4").feature("glob1").setIndex("descr", "\u7535\u5b50\u548c\u7a7a\u7a74", 0);
    model.result("pg4").run();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/semi", true);
    model.study("std2").feature("stat").setSolveFor("/physics/semi2", true);
    model.study("std2").feature("stat").setSolveFor("/physics/semi", false);
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").setIndex("pname", "L", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "m", 0);
    model.study("std2").feature("stat").setIndex("pname", "L", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "m", 0);
    model.study("std2").feature("stat").setIndex("pname", "Vg", 0);
    model.study("std2").feature("stat").setIndex("pname", "L", 1);
    model.study("std2").feature("stat").setIndex("plistarr", "", 1);
    model.study("std2").feature("stat").setIndex("punit", "m", 1);
    model.study("std2").feature("stat").setIndex("pname", "L", 1);
    model.study("std2").feature("stat").setIndex("plistarr", "", 1);
    model.study("std2").feature("stat").setIndex("punit", "m", 1);
    model.study("std2").feature("stat").setIndex("plistarr", "0 1 2", 0);
    model.study("std2").feature("stat").setIndex("pname", "Vd", 1);
    model.study("std2").feature("stat").setIndex("plistarr", "range(0,1,10)", 1);
    model.study("std2").feature("stat").set("sweeptype", "filled");
    model.study("std2").feature("stat").set("preusesol", "auto");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").label("\u7535\u5b50\u6d53\u5ea6 (semi2)");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").setIndex("looplevel", 11, 0);
    model.result("pg5").setIndex("looplevel", 3, 1);
    model.result("pg5").set("showlegendsmaxmin", true);
    model.result("pg5").feature().create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("expr", "semi2.N");
    model.result("pg5").feature("surf1").set("unit", "1/cm^3");
    model.result("pg5").feature("surf1").set("colortable", "Prism");
    model.result("pg5").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg5").feature("surf1").set("resolution", "norefine");
    model.result("pg5").feature("surf1").set("smooth", "internal");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("data", "parent");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").label("\u7a7a\u7a74\u6d53\u5ea6 (semi2)");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").setIndex("looplevel", 11, 0);
    model.result("pg6").setIndex("looplevel", 3, 1);
    model.result("pg6").set("showlegendsmaxmin", true);
    model.result("pg6").feature().create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("expr", "semi2.P");
    model.result("pg6").feature("surf1").set("unit", "1/cm^3");
    model.result("pg6").feature("surf1").set("colortable", "Prism");
    model.result("pg6").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg6").feature("surf1").set("resolution", "norefine");
    model.result("pg6").feature("surf1").set("smooth", "internal");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("data", "parent");
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").label("\u7535\u52bf (semi2)");
    model.result("pg7").set("data", "dset2");
    model.result("pg7").setIndex("looplevel", 11, 0);
    model.result("pg7").setIndex("looplevel", 3, 1);
    model.result("pg7").feature().create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("showsolutionparams", "on");
    model.result("pg7").feature("surf1").set("expr", "V2");
    model.result("pg7").feature("surf1").set("resolution", "norefine");
    model.result("pg7").feature("surf1").set("smooth", "internal");
    model.result("pg7").feature("surf1").set("showsolutionparams", "on");
    model.result("pg7").feature("surf1").set("data", "parent");
    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").set("data", "dset2");
    model.result("pg8").create("surf2", "Surface");
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf2").set("expr", "semi2.Nnetdop");
    model.result("pg8").feature("surf2").set("unit", "1/cm^3");
    model.result("pg8").feature("surf2").set("coloring", "gradient");
    model.result("pg8").feature("surf2").set("colorscalemode", "logarithmic");
    model.result("pg8").feature("surf2").set("topcolor", "red");
    model.result("pg8").feature("surf2").set("bottomcolor", "custom");
    model.result("pg8").feature("surf2").set("custombottomcolor", new double[]{1, 0.8, 0.8});
    model.result("pg8").feature("surf2").set("smooth", "internal");
    model.result("pg8").feature("surf2").set("showsolutionparams", "on");
    model.result("pg8").feature("surf2").set("data", "parent");
    model.result("pg8").feature("surf2").set("titletype", "none");
    model.result("pg8").feature("surf2").feature().create("filt1", "Filter");
    model.result("pg8").feature("surf2").feature("filt1").set("expr", "semi2.Na-semi2.Nd > 1[1/cm^3]");
    model.result("pg8").feature("surf2").feature("filt1").set("useder", true);
    model.result("pg8").feature("surf1").set("expr", "semi2.Nnetdop");
    model.result("pg8").feature("surf1").set("unit", "1/cm^3");
    model.result("pg8").feature("surf1").set("coloring", "gradient");
    model.result("pg8").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg8").feature("surf1").set("topcolor", "blue");
    model.result("pg8").feature("surf1").set("bottomcolor", "custom");
    model.result("pg8").feature("surf1").set("custombottomcolor", new double[]{0.8, 0.8, 1});
    model.result("pg8").feature("surf1").set("smooth", "internal");
    model.result("pg8").feature("surf1").set("showsolutionparams", "on");
    model.result("pg8").feature("surf1").set("data", "parent");
    model.result("pg8").feature("surf1").set("titletype", "none");
    model.result("pg8").feature("surf1").feature().create("filt1", "Filter");
    model.result("pg8").feature("surf1").feature("filt1").set("expr", "semi2.Nd-semi2.Na > 1[1/cm^3]");
    model.result("pg8").feature("surf1").feature("filt1").set("useder", true);
    model.result("pg8").set("titletype", "manual");
    model.result("pg8")
         .set("title", "\u51c0\u63ba\u6742\u5242\u6d53\u5ea6 \\vert N<sub>d</sub> - N<sub>a</sub>\\vert: P \u578b (\u7ea2\u8272), N \u578b (\u84dd\u8272)");
    model.result("pg8").set("showlegendsmaxmin", true);
    model.result("pg8").set("showlegendsunit", true);
    model.result("pg8").set("legendpos", "alternating");
    model.result("pg8").feature("surf2").label("P \u578b");
    model.result("pg8").feature("surf1").label("N \u578b");
    model.result("pg8").label("\u51c0\u63ba\u6742\u5242\u6d53\u5ea6 (semi2)");
    model.result("pg5").run();
    model.result("pg8").run();
    model.result().remove("pg8");
    model.result("pg4").run();
    model.result("pg4").feature().duplicate("glob2", "glob1");
    model.result("pg4").run();
    model.result("pg4").feature("glob2").set("data", "dset2");
    model.result("pg4").feature("glob2").set("expr", new String[]{"semi2.I0_3"});
    model.result("pg4").feature("glob2").set("descr", new String[]{"\u7ec8\u7aef\u7535\u6d41"});
    model.result("pg4").feature("glob2").set("unit", new String[]{"A"});
    model.result("pg4").feature("glob2").setIndex("descr", "\u4ec5\u591a\u6570\u8f7d\u6d41\u5b50", 0);
    model.result("pg4").feature("glob2").set("linemarker", "asterisk");
    model.result("pg4").feature("glob2").set("markerpos", "interp");
    model.result("pg4").feature("glob2").set("linecolor", "cyclereset");
    model.result("pg4").run();
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "\u6f0f\u6781\u7535\u538b (V)");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u7ec8\u7aef\u7535\u6d41 (A)");
    model.result("pg4").run();

    model.title("MESFET \u7684\u76f4\u6d41\u7279\u6027");

    model
         .description("\u672c\u4f8b\u4f7f\u7528\u4ec5\u5305\u542b\u591a\u6570\u8f7d\u6d41\u5b50\u7684\u516c\u5f0f\u6765\u6bd4\u8f83 MESFET \u7684\u7535\u6d41-\u7535\u538b\u7279\u6027\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("mesfet.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
