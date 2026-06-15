/*
 * heterojunction_tunneling.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:58 by COMSOL 6.3.0.290. */
public class heterojunction_tunneling {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Semiconductor_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("semi", "Semiconductor", "geom1");
    model.component("comp1").physics().create("cc", "CurvilinearCoordinates", "geom1");
    model.component("comp1").physics().create("cc2", "CurvilinearCoordinates", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/semi", true);
    model.study("std1").feature("stat").setSolveFor("/physics/cc", true);
    model.study("std1").feature("stat").setSolveFor("/physics/cc2", true);

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("T0", "300[K]", "\u53c2\u8003\u6e29\u5ea6");
    model.param().set("T", "T0", "\u6676\u683c\u6e29\u5ea6");
    model.param().set("d1", "0.25[um]", "\u5c42 1 \u539a\u5ea6");
    model.param().set("d3", "d1", "\u5c42 3 \u539a\u5ea6");
    model.param().set("d2", "0.078[um]", "\u5c42 2 \u539a\u5ea6");
    model.param().set("N_D1", "1.5e17[cm^-3]", "\u5c42 1 \u7684 n \u578b\u63ba\u6742\u6d53\u5ea6");
    model.param().set("N_D2", "5e15[cm^-3]", "\u5c42 2 \u7684 n \u578b\u63ba\u6742\u6d53\u5ea6");
    model.param().set("N_D3", "8e17[cm^-3]", "\u5c42 3 \u7684 n \u578b\u63ba\u6742\u6d53\u5ea6");
    model.param().set("N_A", "0[cm^-3]", "P \u578b\u63ba\u6742\u6d53\u5ea6");
    model.param().set("Al_frac0", "0.3", "\u5f02\u8d28\u7ed3\u5904 Al \u7684\u6469\u5c14\u5206\u6570");
    model.param().set("Va", "0[V]", "\u5916\u52a0\u7535\u538b");
    model.param().set("thickness", "1[m]", "\u9762\u5916\u539a\u5ea6");
    model.param().set("Vth", "k_B_const*T/e_const", "\u70ed\u7535\u538b");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"d1+d2+d3", "d2"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"-d1", "0"});
    model.component("comp1").geom("geom1").feature("r1").set("layerbottom", false);
    model.component("comp1").geom("geom1").feature("r1").set("layerleft", true);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "d1", 0);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "d2", 1);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").label("\u57df 1 \u7684\u53d8\u91cf");
    model.component("comp1").variable("var1").selection().geom("geom1", 2);
    model.component("comp1").variable("var1").selection().set(1);
    model.component("comp1").variable("var1").set("N_D", "N_D1");
    model.component("comp1").variable("var1").descr("N_D", "n \u578b\u63ba\u6742");
    model.component("comp1").variable("var1").set("Al_frac", "0");
    model.component("comp1").variable("var1").descr("Al_frac", "Al \u6469\u5c14\u5206\u6570");
    model.component("comp1").variable().duplicate("var2", "var1");
    model.component("comp1").variable("var2").label("\u57df 2 \u7684\u53d8\u91cf");
    model.component("comp1").variable("var2").selection().set(2);
    model.component("comp1").variable("var2").set("N_D", "N_D2");
    model.component("comp1").variable("var2").set("Al_frac", "Al_frac0*(1-x/d2)");
    model.component("comp1").variable().duplicate("var3", "var1");
    model.component("comp1").variable("var3").label("\u57df 3 \u7684\u53d8\u91cf");
    model.component("comp1").variable("var3").selection().set(3);
    model.component("comp1").variable("var3").set("N_D", "N_D3");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("Al(x)Ga(1-x)As\uff08Yang \u7b49\u4eba\uff0c1993\uff09");
    model.component("comp1").material("mat1").propertyGroup("def").set("x", new String[]{"Al_frac"});
    model.component("comp1").material("mat1").propertyGroup("def").descr("x", "Al \u6469\u5c14\u5206\u6570");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("me", new String[]{"(0.067+0.083*def.x)*me_const"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .descr("me", "\u7535\u5b50 DOS \u6709\u6548\u8d28\u91cf");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("mh", new String[]{"(0.48+0.31*def.x)*me_const"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .descr("mh", "\u7a7a\u7a74 DOS \u6709\u6548\u8d28\u91cf");
    model.component("comp1").material("mat1").propertyGroup()
         .create("SemicondMaterial", "SemicondMaterial", "Semiconductor_material");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial")
         .set("mun", new String[]{"7200[cm^2/V/s]/(1+5.51e-17[cm^3]*(N_D+N_A))^0.233*(T0/T)^2.3*(1-0.127*def.x/0.1)"});
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial")
         .set("mup", new String[]{"380[cm^2/V/s]/(1+3.17e-17[cm^3]*(N_D+N_A))^0.266*(T0/T)^2.7*(1-0.067*def.x/0.1)"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"13.1-3*def.x"});
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial")
         .set("Eg0", new String[]{"1.519[V]+1.247[V]*def.x-5.405e-4[V/K]*T^2/(T+204[K])"});
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial")
         .set("chi0", new String[]{"4.07[V]-0.6*1.247[V]*def.x"});
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial")
         .set("Nc", new String[]{"2*((def.me*k_B_const*T)/(2*pi*hbar_const^2))^1.5"});
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial")
         .set("Nv", new String[]{"2*((def.mh*k_B_const*T)/(2*pi*hbar_const^2))^1.5"});

    model.component("comp1").physics("cc").selection().set(2);
    model.component("comp1").physics("cc").create("diff1", "DiffusionMethod", 2);
    model.component("comp1").physics("cc").feature("diff1").create("inl1", "Inlet", 1);
    model.component("comp1").physics("cc").feature("diff1").feature("inl1").selection().set(6);
    model.component("comp1").physics("cc").feature("diff1").create("out1", "Outlet", 1);
    model.component("comp1").physics("cc").feature("diff1").feature("out1").selection().set(5);
    model.component("comp1").physics("cc2").selection().set(2);
    model.component("comp1").physics("cc2").create("diff1", "DiffusionMethod", 2);
    model.component("comp1").physics("cc2").feature("diff1").create("inl1", "Inlet", 1);
    model.component("comp1").physics("cc2").feature("diff1").feature("inl1").selection().set(7);
    model.component("comp1").physics("cc2").feature("diff1").create("out1", "Outlet", 1);
    model.component("comp1").physics("cc2").feature("diff1").feature("out1").selection().set(4);

    model.component("comp1").variable().create("var4");
    model.component("comp1").variable("var4").label("\u96a7\u7a7f\u53d8\u91cf");
    model.component("comp1").variable("var4").selection().geom("geom1", 2);
    model.component("comp1").variable("var4").selection().set(2);
    model.component("comp1").variable("var4").set("pbcc", "cc.diff1.U");
    model.component("comp1").variable("var4")
         .descr("pbcc", "\u6cbf\u52bf\u5792\u8fb9\u7f18\u7684\u5f52\u4e00\u5316\u5f27\u957f");
    model.component("comp1").variable("var4").set("flcc", "cc2.diff1.U");
    model.component("comp1").variable("var4")
         .descr("flcc", "\u6cbf\u573a\u7ebf\u7684\u5f52\u4e00\u5316\u5f27\u957f");

    model.component("comp1").physics("semi").prop("d").set("d", "thickness");
    model.component("comp1").physics("semi").prop("ShapeProperty").set("Formulation", "FEM2Ef");
    model.component("comp1").physics("semi").prop("LatticeProperties").set("T0", "T0");
    model.component("comp1").physics("semi").feature("smm1").set("minput_temperature", "T");
    model.component("comp1").physics("semi").feature("smm1").set("Ionization", "incomplete");
    model.component("comp1").physics("semi").feature("smm1")
         .set("deltaEd", "0.005[V]+0.03[V]*material.def.x/Al_frac0");
    model.component("comp1").physics("semi").feature("smm1").set("deltaEa", "0.026[V]");
    model.component("comp1").physics("semi").feature("cont1").set("HeteroModelSelection", 2);
    model.component("comp1").physics("semi").feature().duplicate("cont2", "cont1");
    model.component("comp1").physics("semi").feature("cont2").selection().set(4);
    model.component("comp1").physics("semi").feature("cont2").set("extraElectronCurrent", "WKBTunneling");
    model.component("comp1").physics("semi").feature("cont2").create("wkbe1", "WKBTunnelingModelElectrons", 1);
    model.component("comp1").physics("semi").feature("cont2").feature("wkbe1").selection("pbdm").set(2);
    model.component("comp1").physics("semi").feature("cont2").feature("wkbe1").selection("obnd").set(7);
    model.component("comp1").physics("semi").feature("cont2").feature("wkbe1").set("m", "material.def.me");
    model.component("comp1").physics("semi").feature("cont2").feature("wkbe1").set("flc", "flcc");
    model.component("comp1").physics("semi").feature("cont2").feature("wkbe1").set("bndc", "pbcc");
    model.component("comp1").physics("semi").create("adm1", "AnalyticDopingModel", 2);
    model.component("comp1").physics("semi").feature("adm1").selection().all();
    model.component("comp1").physics("semi").feature("adm1").set("impurityType", "donor");
    model.component("comp1").physics("semi").feature("adm1").set("NDc", "N_D");
    model.component("comp1").physics("semi").create("tar1", "TrapAssistedRecombination", 2);
    model.component("comp1").physics("semi").feature("tar1").selection().all();

    model.component("comp1").material("mat1").propertyGroup()
         .create("SRH", "SRH", "Shockley-Read-Hall_recombination");
    model.component("comp1").material("mat1").propertyGroup("SRH").set("taun", new String[]{"1[ns]"});
    model.component("comp1").material("mat1").propertyGroup("SRH").set("taup", new String[]{"1[ns]"});

    model.component("comp1").physics("semi").create("mc1", "MetalContact", 1);
    model.component("comp1").physics("semi").feature("mc1").selection().set(1);
    model.component("comp1").physics("semi").feature("mc1").set("V0", "Va");
    model.component("comp1").physics("semi").create("mc2", "MetalContact", 1);
    model.component("comp1").physics("semi").feature("mc2").selection().set(10);

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size1").selection().set(4);

    model.study("std1").label("\u7814\u7a76 1\uff1a\u65e0\u96a7\u7a7f");
    model.study("std1").feature("stat").setSolveFor("/physics/cc", false);
    model.study("std1").feature("stat").setSolveFor("/physics/cc2", false);
    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").set("disabledphysics", new String[]{"semi/cont2"});
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "T0", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "K", 0);
    model.study("std1").feature("stat").setIndex("pname", "T0", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "K", 0);
    model.study("std1").feature("stat").setIndex("pname", "Va", 0);
    model.study("std1").feature("stat")
         .setIndex("plistarr", "range(-1.2,0.1,-0.01) -0.05 range(0.025,0.025,0.25)", 0);
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
    model.result("pg4").run();
    model.result("pg4").feature().remove("surf2");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("300 K \u65f6\u7684 I-V \u66f2\u7ebf");
    model.result("pg5").set("titletype", "manual");
    model.result("pg5").set("title", "300K \u65f6\u7684 I-V \u66f2\u7ebf");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "\u7535\u6d41\u5bc6\u5ea6 (10<sup>4</sup>A/cm<sup>2</sup>)");
    model.result("pg5").set("axislimits", true);
    model.result("pg5").set("xmin", -1.2);
    model.result("pg5").set("xmax", 0.4);
    model.result("pg5").set("ymax", 2);
    model.result("pg5").set("legendpos", "upperleft");
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").setIndex("expr", "semi.I0_1/d2/thickness/1e4[A/cm^2]", 0);
    model.result("pg5").feature("glob1").setIndex("descr", "\u65e0\u96a7\u7a7f", 0);
    model.result("pg5").run();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/semi", false);
    model.study("std2").feature("stat").setSolveFor("/physics/cc", true);
    model.study("std2").feature("stat").setSolveFor("/physics/cc2", true);
    model.study("std2").label("\u7814\u7a76 2\uff1a\u66f2\u7ebf\u5750\u6807");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").label("\u77e2\u91cf\u573a (cc)");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").feature().create("str1", "Streamline");
    model.result("pg6").feature("str1").set("showsolutionparams", "on");
    model.result("pg6").feature("str1").set("posmethod", "uniform");
    model.result("pg6").feature("str1").set("color", "red");
    model.result("pg6").feature("str1").set("smooth", "internal");
    model.result("pg6").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("str1").set("showsolutionparams", "on");
    model.result("pg6").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("str1").set("showsolutionparams", "on");
    model.result("pg6").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("str1").set("showsolutionparams", "on");
    model.result("pg6").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("str1").set("showsolutionparams", "on");
    model.result("pg6").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("str1").set("data", "parent");
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").label("\u77e2\u91cf\u573a (cc2)");
    model.result("pg7").set("data", "dset2");
    model.result("pg7").feature().create("str1", "Streamline");
    model.result("pg7").feature("str1").set("showsolutionparams", "on");
    model.result("pg7").feature("str1").set("expr", new String[]{"cc2.vX", "cc2.vY"});
    model.result("pg7").feature("str1").set("posmethod", "uniform");
    model.result("pg7").feature("str1").set("color", "red");
    model.result("pg7").feature("str1").set("smooth", "internal");
    model.result("pg7").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg7").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg7").feature("str1").set("showsolutionparams", "on");
    model.result("pg7").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg7").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg7").feature("str1").set("showsolutionparams", "on");
    model.result("pg7").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg7").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg7").feature("str1").set("showsolutionparams", "on");
    model.result("pg7").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg7").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg7").feature("str1").set("showsolutionparams", "on");
    model.result("pg7").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg7").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg7").feature("str1").set("data", "parent");
    model.result("pg6").run();
    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").run();
    model.result("pg8").label("\u66f2\u7ebf\u5750\u6807");
    model.result("pg8").set("data", "dset2");
    model.result("pg8").create("con1", "Contour");
    model.result("pg8").feature("con1").set("expr", "pbcc");
    model.result("pg8").feature("con1").set("number", 5);
    model.result("pg8").feature().duplicate("con2", "con1");
    model.result("pg8").run();
    model.result("pg8").feature("con2").set("expr", "flcc");
    model.result("pg8").feature("con2").set("number", 8);
    model.result("pg8").run();
    model.result("pg8").feature("con1").set("colortable", "GrayScale");
    model.result("pg8").run();

    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").setSolveFor("/physics/semi", true);
    model.study("std3").feature("stat").setSolveFor("/physics/cc", false);
    model.study("std3").feature("stat").setSolveFor("/physics/cc2", false);
    model.study("std3").label("\u7814\u7a76 3\uff1a\u96a7\u7a7f");
    model.study("std3").feature("stat").set("useinitsol", true);
    model.study("std3").feature("stat").set("initmethod", "sol");
    model.study("std3").feature("stat").set("initstudy", "std1");
    model.study("std3").feature("stat").set("solnum", 1);
    model.study("std3").feature("stat").set("usesol", true);
    model.study("std3").feature("stat").set("notsolmethod", "sol");
    model.study("std3").feature("stat").set("notstudy", "std2");
    model.study("std3").feature("stat").set("useparam", true);
    model.study("std3").feature("stat").setIndex("pname", "T0", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "", 0);
    model.study("std3").feature("stat").setIndex("punit", "K", 0);
    model.study("std3").feature("stat").setIndex("pname", "T0", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "", 0);
    model.study("std3").feature("stat").setIndex("punit", "K", 0);
    model.study("std3").feature("stat").setIndex("pname", "Va", 0);
    model.study("std3").feature("stat")
         .setIndex("plistarr", "range(-1.2,0.1,-0.01) -0.05 range(0.025,0.025,0.25)", 0);
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().create("pg9", "PlotGroup2D");
    model.result("pg9").label("\u7535\u5b50\u6d53\u5ea6 (semi) 1");
    model.result("pg9").set("data", "dset3");
    model.result("pg9").setIndex("looplevel", 23, 0);
    model.result("pg9").set("showlegendsmaxmin", true);
    model.result("pg9").feature().create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("showsolutionparams", "on");
    model.result("pg9").feature("surf1").set("expr", "semi.N");
    model.result("pg9").feature("surf1").set("unit", "1/cm^3");
    model.result("pg9").feature("surf1").set("colortable", "Prism");
    model.result("pg9").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg9").feature("surf1").set("resolution", "norefine");
    model.result("pg9").feature("surf1").set("smooth", "internal");
    model.result("pg9").feature("surf1").set("showsolutionparams", "on");
    model.result("pg9").feature("surf1").set("data", "parent");
    model.result().create("pg10", "PlotGroup2D");
    model.result("pg10").label("\u7a7a\u7a74\u6d53\u5ea6 (semi) 1");
    model.result("pg10").set("data", "dset3");
    model.result("pg10").setIndex("looplevel", 23, 0);
    model.result("pg10").set("showlegendsmaxmin", true);
    model.result("pg10").feature().create("surf1", "Surface");
    model.result("pg10").feature("surf1").set("showsolutionparams", "on");
    model.result("pg10").feature("surf1").set("expr", "semi.P");
    model.result("pg10").feature("surf1").set("unit", "1/cm^3");
    model.result("pg10").feature("surf1").set("colortable", "Prism");
    model.result("pg10").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg10").feature("surf1").set("resolution", "norefine");
    model.result("pg10").feature("surf1").set("smooth", "internal");
    model.result("pg10").feature("surf1").set("showsolutionparams", "on");
    model.result("pg10").feature("surf1").set("data", "parent");
    model.result().create("pg11", "PlotGroup2D");
    model.result("pg11").label("\u7535\u52bf (semi) 1");
    model.result("pg11").set("data", "dset3");
    model.result("pg11").setIndex("looplevel", 23, 0);
    model.result("pg11").feature().create("surf1", "Surface");
    model.result("pg11").feature("surf1").set("showsolutionparams", "on");
    model.result("pg11").feature("surf1").set("expr", "V");
    model.result("pg11").feature("surf1").set("resolution", "norefine");
    model.result("pg11").feature("surf1").set("smooth", "internal");
    model.result("pg11").feature("surf1").set("showsolutionparams", "on");
    model.result("pg11").feature("surf1").set("data", "parent");
    model.result().create("pg12", "PlotGroup2D");
    model.result("pg12").set("data", "dset3");
    model.result("pg12").create("surf2", "Surface");
    model.result("pg12").create("surf1", "Surface");
    model.result("pg12").feature("surf2").set("expr", "semi.Nnetdop");
    model.result("pg12").feature("surf2").set("unit", "1/cm^3");
    model.result("pg12").feature("surf2").set("coloring", "gradient");
    model.result("pg12").feature("surf2").set("colorscalemode", "logarithmic");
    model.result("pg12").feature("surf2").set("topcolor", "red");
    model.result("pg12").feature("surf2").set("bottomcolor", "custom");
    model.result("pg12").feature("surf2").set("custombottomcolor", new double[]{1, 0.8, 0.8});
    model.result("pg12").feature("surf2").set("smooth", "internal");
    model.result("pg12").feature("surf2").set("showsolutionparams", "on");
    model.result("pg12").feature("surf2").set("data", "parent");
    model.result("pg12").feature("surf2").set("titletype", "none");
    model.result("pg12").feature("surf2").feature().create("filt1", "Filter");
    model.result("pg12").feature("surf2").feature("filt1").set("expr", "semi.Na-semi.Nd > 1[1/cm^3]");
    model.result("pg12").feature("surf2").feature("filt1").set("useder", true);
    model.result("pg12").feature("surf1").set("expr", "semi.Nnetdop");
    model.result("pg12").feature("surf1").set("unit", "1/cm^3");
    model.result("pg12").feature("surf1").set("coloring", "gradient");
    model.result("pg12").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg12").feature("surf1").set("topcolor", "blue");
    model.result("pg12").feature("surf1").set("bottomcolor", "custom");
    model.result("pg12").feature("surf1").set("custombottomcolor", new double[]{0.8, 0.8, 1});
    model.result("pg12").feature("surf1").set("smooth", "internal");
    model.result("pg12").feature("surf1").set("showsolutionparams", "on");
    model.result("pg12").feature("surf1").set("data", "parent");
    model.result("pg12").feature("surf1").set("titletype", "none");
    model.result("pg12").feature("surf1").feature().create("filt1", "Filter");
    model.result("pg12").feature("surf1").feature("filt1").set("expr", "semi.Nd-semi.Na > 1[1/cm^3]");
    model.result("pg12").feature("surf1").feature("filt1").set("useder", true);
    model.result("pg12").set("titletype", "manual");
    model.result("pg12")
         .set("title", "\u51c0\u63ba\u6742\u5242\u6d53\u5ea6 \\vert N<sub>d</sub> - N<sub>a</sub>\\vert: P \u578b (\u7ea2\u8272), N \u578b (\u84dd\u8272)");
    model.result("pg12").set("showlegendsmaxmin", true);
    model.result("pg12").set("showlegendsunit", true);
    model.result("pg12").set("legendpos", "alternating");
    model.result("pg12").feature("surf2").label("P \u578b");
    model.result("pg12").feature("surf1").label("N \u578b");
    model.result("pg12").label("\u51c0\u63ba\u6742\u5242\u6d53\u5ea6 (semi) 1");
    model.result("pg9").run();
    model.result("pg12").run();
    model.result().remove("pg12");
    model.result("pg5").run();
    model.result("pg5").feature().duplicate("glob2", "glob1");
    model.result("pg5").run();
    model.result("pg5").feature("glob2").set("data", "dset3");
    model.result("pg5").feature("glob2").setIndex("descr", "\u96a7\u7a7f", 0);
    model.result("pg5").run();
    model.result().create("pg12", "PlotGroup1D");
    model.result("pg12").run();
    model.result("pg12").label("\u5bfc\u5e26\u8f6e\u5ed3");
    model.result("pg12").set("data", "dset3");
    model.result("pg12").setIndex("looplevelinput", "manual", 0);
    model.result("pg12").setIndex("looplevel", new int[]{8, 19}, 0);
    model.result("pg12").create("lngr1", "LineGraph");
    model.result("pg12").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg12").feature("lngr1").set("linewidth", "preference");
    model.result("pg12").feature("lngr1").selection().set(2, 5, 8);
    model.result("pg12").feature("lngr1").set("expr", "semi.Ec");
    model.result("pg12").feature("lngr1").set("smooth", "everywhere");
    model.result("pg12").feature("lngr1").set("legend", true);
    model.result("pg12").feature("lngr1").set("autodescr", true);
    model.result("pg12").feature().duplicate("lngr2", "lngr1");
    model.result("pg12").run();
    model.result("pg12").feature("lngr2").set("expr", "semi.Efn");
    model.result("pg12").feature("lngr2").set("linestyle", "dashed");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg12").feature("lngr2").set("linecolor", "cyclereset");
    model.result("pg12").run();
    model.result("pg12").set("axislimits", true);
    model.result("pg12").set("xmin", 0.1);
    model.result("pg12").set("xmax", 0.4);
    model.result("pg12").set("legendpos", "middleleft");
    model.result("pg12").run();

    model.study().create("std4");
    model.study("std4").label("\u7814\u7a76 4\uff1a\u65e0\u96a7\u7a7f\uff0c\u8f83\u4f4e\u7684 Ts");
    model.study("std4").feature().copy("stat", "std1/stat");
    model.study("std4").feature("stat").setIndex("plistarr", "-0.05 range(-0.1,-0.1,-1.2)", 0);
    model.study("std4").feature("stat").set("useinitsol", true);
    model.study("std4").feature("stat").set("initmethod", "sol");
    model.study("std4").feature("stat").set("initstudy", "std1");
    model.study("std4").feature("stat").set("solnum", 13);
    model.study("std4").create("param", "Parametric");
    model.study("std4").feature("param").setIndex("pname", "T0", 0);
    model.study("std4").feature("param").setIndex("plistarr", "", 0);
    model.study("std4").feature("param").setIndex("punit", "K", 0);
    model.study("std4").feature("param").setIndex("pname", "T0", 0);
    model.study("std4").feature("param").setIndex("plistarr", "", 0);
    model.study("std4").feature("param").setIndex("punit", "K", 0);
    model.study("std4").feature("param").setIndex("pname", "T", 0);
    model.study("std4").feature("param").setIndex("plistarr", "200 180", 0);
    model.study("std4").feature("param").setIndex("pname", "T0", 1);
    model.study("std4").feature("param").setIndex("plistarr", "", 1);
    model.study("std4").feature("param").setIndex("punit", "K", 1);
    model.study("std4").feature("param").setIndex("pname", "T0", 1);
    model.study("std4").feature("param").setIndex("plistarr", "", 1);
    model.study("std4").feature("param").setIndex("punit", "K", 1);
    model.study("std4").feature("param").setIndex("pname", "Al_frac0", 1);
    model.study("std4").feature("param").setIndex("plistarr", "0.27 0.28", 1);
    model.study("std4").createAutoSequences("all");

    model.sol().create("sol5");
    model.sol("sol5").study("std4");
    model.sol("sol5").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol5");
    model.batch("p1").run("compute");

    model.result().create("pg13", "PlotGroup2D");
    model.result("pg13").label("\u7535\u5b50\u6d53\u5ea6 (semi) 2");
    model.result("pg13").set("data", "dset5");
    model.result("pg13").setIndex("looplevel", 13, 0);
    model.result("pg13").setIndex("looplevel", 2, 1);
    model.result("pg13").set("showlegendsmaxmin", true);
    model.result("pg13").feature().create("surf1", "Surface");
    model.result("pg13").feature("surf1").set("showsolutionparams", "on");
    model.result("pg13").feature("surf1").set("expr", "semi.N");
    model.result("pg13").feature("surf1").set("unit", "1/cm^3");
    model.result("pg13").feature("surf1").set("colortable", "Prism");
    model.result("pg13").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg13").feature("surf1").set("resolution", "norefine");
    model.result("pg13").feature("surf1").set("smooth", "internal");
    model.result("pg13").feature("surf1").set("showsolutionparams", "on");
    model.result("pg13").feature("surf1").set("data", "parent");
    model.result().create("pg14", "PlotGroup2D");
    model.result("pg14").label("\u7a7a\u7a74\u6d53\u5ea6 (semi) 2");
    model.result("pg14").set("data", "dset5");
    model.result("pg14").setIndex("looplevel", 13, 0);
    model.result("pg14").setIndex("looplevel", 2, 1);
    model.result("pg14").set("showlegendsmaxmin", true);
    model.result("pg14").feature().create("surf1", "Surface");
    model.result("pg14").feature("surf1").set("showsolutionparams", "on");
    model.result("pg14").feature("surf1").set("expr", "semi.P");
    model.result("pg14").feature("surf1").set("unit", "1/cm^3");
    model.result("pg14").feature("surf1").set("colortable", "Prism");
    model.result("pg14").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg14").feature("surf1").set("resolution", "norefine");
    model.result("pg14").feature("surf1").set("smooth", "internal");
    model.result("pg14").feature("surf1").set("showsolutionparams", "on");
    model.result("pg14").feature("surf1").set("data", "parent");
    model.result().create("pg15", "PlotGroup2D");
    model.result("pg15").label("\u7535\u52bf (semi) 2");
    model.result("pg15").set("data", "dset5");
    model.result("pg15").setIndex("looplevel", 13, 0);
    model.result("pg15").setIndex("looplevel", 2, 1);
    model.result("pg15").feature().create("surf1", "Surface");
    model.result("pg15").feature("surf1").set("showsolutionparams", "on");
    model.result("pg15").feature("surf1").set("expr", "V");
    model.result("pg15").feature("surf1").set("resolution", "norefine");
    model.result("pg15").feature("surf1").set("smooth", "internal");
    model.result("pg15").feature("surf1").set("showsolutionparams", "on");
    model.result("pg15").feature("surf1").set("data", "parent");
    model.result().create("pg16", "PlotGroup2D");
    model.result("pg16").set("data", "dset5");
    model.result("pg16").create("surf2", "Surface");
    model.result("pg16").create("surf1", "Surface");
    model.result("pg16").feature("surf2").set("expr", "semi.Nnetdop");
    model.result("pg16").feature("surf2").set("unit", "1/cm^3");
    model.result("pg16").feature("surf2").set("coloring", "gradient");
    model.result("pg16").feature("surf2").set("colorscalemode", "logarithmic");
    model.result("pg16").feature("surf2").set("topcolor", "red");
    model.result("pg16").feature("surf2").set("bottomcolor", "custom");
    model.result("pg16").feature("surf2").set("custombottomcolor", new double[]{1, 0.8, 0.8});
    model.result("pg16").feature("surf2").set("smooth", "internal");
    model.result("pg16").feature("surf2").set("showsolutionparams", "on");
    model.result("pg16").feature("surf2").set("data", "parent");
    model.result("pg16").feature("surf2").set("titletype", "none");
    model.result("pg16").feature("surf2").feature().create("filt1", "Filter");
    model.result("pg16").feature("surf2").feature("filt1").set("expr", "semi.Na-semi.Nd > 1[1/cm^3]");
    model.result("pg16").feature("surf2").feature("filt1").set("useder", true);
    model.result("pg16").feature("surf1").set("expr", "semi.Nnetdop");
    model.result("pg16").feature("surf1").set("unit", "1/cm^3");
    model.result("pg16").feature("surf1").set("coloring", "gradient");
    model.result("pg16").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg16").feature("surf1").set("topcolor", "blue");
    model.result("pg16").feature("surf1").set("bottomcolor", "custom");
    model.result("pg16").feature("surf1").set("custombottomcolor", new double[]{0.8, 0.8, 1});
    model.result("pg16").feature("surf1").set("smooth", "internal");
    model.result("pg16").feature("surf1").set("showsolutionparams", "on");
    model.result("pg16").feature("surf1").set("data", "parent");
    model.result("pg16").feature("surf1").set("titletype", "none");
    model.result("pg16").feature("surf1").feature().create("filt1", "Filter");
    model.result("pg16").feature("surf1").feature("filt1").set("expr", "semi.Nd-semi.Na > 1[1/cm^3]");
    model.result("pg16").feature("surf1").feature("filt1").set("useder", true);
    model.result("pg16").set("titletype", "manual");
    model.result("pg16")
         .set("title", "\u51c0\u63ba\u6742\u5242\u6d53\u5ea6 \\vert N<sub>d</sub> - N<sub>a</sub>\\vert: P \u578b (\u7ea2\u8272), N \u578b (\u84dd\u8272)");
    model.result("pg16").set("showlegendsmaxmin", true);
    model.result("pg16").set("showlegendsunit", true);
    model.result("pg16").set("legendpos", "alternating");
    model.result("pg16").feature("surf2").label("P \u578b");
    model.result("pg16").feature("surf1").label("N \u578b");
    model.result("pg16").label("\u51c0\u63ba\u6742\u5242\u6d53\u5ea6 (semi) 1");
    model.result("pg13").run();
    model.result("pg16").run();
    model.result().remove("pg16");
    model.result().create("pg16", "PlotGroup1D");
    model.result("pg16").run();
    model.result("pg16").label("\u4e0d\u540c Ts \u4e0b\u7684 I-V \u66f2\u7ebf");
    model.result("pg16").create("glob1", "Global");
    model.result("pg16").feature("glob1").set("markerpos", "datapoints");
    model.result("pg16").feature("glob1").set("linewidth", "preference");
    model.result("pg16").feature("glob1").setIndex("expr", "-semi.I0_1/d2/thickness", 0);
    model.result("pg16").feature("glob1").setIndex("unit", "A/cm^2", 0);
    model.result("pg16").feature("glob1").setIndex("descr", "T=300 K\uff0cAl_frac0=0.30\uff0c\u65e0\u96a7\u7a7f", 0);
    model.result("pg16").feature("glob1").set("xdata", "expr");
    model.result("pg16").feature("glob1").set("xdataexpr", "-Va");
    model.result("pg16").feature("glob1").set("linestyle", "dotted");
    model.result("pg16").run();
    model.result("pg16").setIndex("looplevelinput", "manual", 0);
    model.result("pg16").setIndex("looplevel", new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13}, 0);
    model.result("pg16").set("titletype", "manual");
    model.result("pg16").set("title", "300 K\u3001200 K \u548c 180 K \u4e0b\u7684 I-V \u66f2\u7ebf");
    model.result("pg16").set("ylabelactive", true);
    model.result("pg16").set("ylabel", "\u7535\u6d41\u5bc6\u5ea6 (A/cm<sup>2</sup>)");
    model.result("pg16").run();
    model.result("pg16").feature().duplicate("glob2", "glob1");
    model.result("pg16").run();
    model.result("pg16").feature("glob2").set("data", "dset5");
    model.result("pg16").feature("glob2").setIndex("descr", "\u65e0\u96a7\u7a7f", 0);
    model.result("pg16").set("ylog", true);
    model.result("pg16").run();

    model.study().create("std5");
    model.study("std5").label("\u7814\u7a76 5\uff1a\u96a7\u7a7f\uff0c\u8f83\u4f4e\u7684 Ts");
    model.study("std5").feature().copy("param", "std4/param");
    model.study("std5").feature().copy("stat", "std4/stat");
    model.study("std5").feature("stat").set("useadvanceddisable", false);
    model.study("std5").feature("stat").set("initstudy", "std4");
    model.study("std5").feature("stat").set("initsol", "sol5");
    model.study("std5").feature("stat").set("initsoluse", "sol6");
    model.study("std5").feature("stat").set("solnum", "first");
    model.study("std5").feature("stat").set("usesol", true);
    model.study("std5").feature("stat").set("notsolmethod", "sol");
    model.study("std5").feature("stat").set("notstudy", "std2");
    model.study("std5").createAutoSequences("all");

    model.sol().create("sol9");
    model.sol("sol9").study("std5");
    model.sol("sol9").label("\u53c2\u6570\u5316\u89e3 2");

    model.batch("p2").feature("so1").set("psol", "sol9");
    model.batch("p2").run("compute");

    model.result().create("pg17", "PlotGroup2D");
    model.result("pg17").label("\u7535\u5b50\u6d53\u5ea6 (semi) 3");
    model.result("pg17").set("data", "dset7");
    model.result("pg17").setIndex("looplevel", 13, 0);
    model.result("pg17").setIndex("looplevel", 2, 1);
    model.result("pg17").set("showlegendsmaxmin", true);
    model.result("pg17").feature().create("surf1", "Surface");
    model.result("pg17").feature("surf1").set("showsolutionparams", "on");
    model.result("pg17").feature("surf1").set("expr", "semi.N");
    model.result("pg17").feature("surf1").set("unit", "1/cm^3");
    model.result("pg17").feature("surf1").set("colortable", "Prism");
    model.result("pg17").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg17").feature("surf1").set("resolution", "norefine");
    model.result("pg17").feature("surf1").set("smooth", "internal");
    model.result("pg17").feature("surf1").set("showsolutionparams", "on");
    model.result("pg17").feature("surf1").set("data", "parent");
    model.result().create("pg18", "PlotGroup2D");
    model.result("pg18").label("\u7a7a\u7a74\u6d53\u5ea6 (semi) 3");
    model.result("pg18").set("data", "dset7");
    model.result("pg18").setIndex("looplevel", 13, 0);
    model.result("pg18").setIndex("looplevel", 2, 1);
    model.result("pg18").set("showlegendsmaxmin", true);
    model.result("pg18").feature().create("surf1", "Surface");
    model.result("pg18").feature("surf1").set("showsolutionparams", "on");
    model.result("pg18").feature("surf1").set("expr", "semi.P");
    model.result("pg18").feature("surf1").set("unit", "1/cm^3");
    model.result("pg18").feature("surf1").set("colortable", "Prism");
    model.result("pg18").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg18").feature("surf1").set("resolution", "norefine");
    model.result("pg18").feature("surf1").set("smooth", "internal");
    model.result("pg18").feature("surf1").set("showsolutionparams", "on");
    model.result("pg18").feature("surf1").set("data", "parent");
    model.result().create("pg19", "PlotGroup2D");
    model.result("pg19").label("\u7535\u52bf (semi) 3");
    model.result("pg19").set("data", "dset7");
    model.result("pg19").setIndex("looplevel", 13, 0);
    model.result("pg19").setIndex("looplevel", 2, 1);
    model.result("pg19").feature().create("surf1", "Surface");
    model.result("pg19").feature("surf1").set("showsolutionparams", "on");
    model.result("pg19").feature("surf1").set("expr", "V");
    model.result("pg19").feature("surf1").set("resolution", "norefine");
    model.result("pg19").feature("surf1").set("smooth", "internal");
    model.result("pg19").feature("surf1").set("showsolutionparams", "on");
    model.result("pg19").feature("surf1").set("data", "parent");
    model.result().create("pg20", "PlotGroup2D");
    model.result("pg20").set("data", "dset7");
    model.result("pg20").create("surf2", "Surface");
    model.result("pg20").create("surf1", "Surface");
    model.result("pg20").feature("surf2").set("expr", "semi.Nnetdop");
    model.result("pg20").feature("surf2").set("unit", "1/cm^3");
    model.result("pg20").feature("surf2").set("coloring", "gradient");
    model.result("pg20").feature("surf2").set("colorscalemode", "logarithmic");
    model.result("pg20").feature("surf2").set("topcolor", "red");
    model.result("pg20").feature("surf2").set("bottomcolor", "custom");
    model.result("pg20").feature("surf2").set("custombottomcolor", new double[]{1, 0.8, 0.8});
    model.result("pg20").feature("surf2").set("smooth", "internal");
    model.result("pg20").feature("surf2").set("showsolutionparams", "on");
    model.result("pg20").feature("surf2").set("data", "parent");
    model.result("pg20").feature("surf2").set("titletype", "none");
    model.result("pg20").feature("surf2").feature().create("filt1", "Filter");
    model.result("pg20").feature("surf2").feature("filt1").set("expr", "semi.Na-semi.Nd > 1[1/cm^3]");
    model.result("pg20").feature("surf2").feature("filt1").set("useder", true);
    model.result("pg20").feature("surf1").set("expr", "semi.Nnetdop");
    model.result("pg20").feature("surf1").set("unit", "1/cm^3");
    model.result("pg20").feature("surf1").set("coloring", "gradient");
    model.result("pg20").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg20").feature("surf1").set("topcolor", "blue");
    model.result("pg20").feature("surf1").set("bottomcolor", "custom");
    model.result("pg20").feature("surf1").set("custombottomcolor", new double[]{0.8, 0.8, 1});
    model.result("pg20").feature("surf1").set("smooth", "internal");
    model.result("pg20").feature("surf1").set("showsolutionparams", "on");
    model.result("pg20").feature("surf1").set("data", "parent");
    model.result("pg20").feature("surf1").set("titletype", "none");
    model.result("pg20").feature("surf1").feature().create("filt1", "Filter");
    model.result("pg20").feature("surf1").feature("filt1").set("expr", "semi.Nd-semi.Na > 1[1/cm^3]");
    model.result("pg20").feature("surf1").feature("filt1").set("useder", true);
    model.result("pg20").set("titletype", "manual");
    model.result("pg20")
         .set("title", "\u51c0\u63ba\u6742\u5242\u6d53\u5ea6 \\vert N<sub>d</sub> - N<sub>a</sub>\\vert: P \u578b (\u7ea2\u8272), N \u578b (\u84dd\u8272)");
    model.result("pg20").set("showlegendsmaxmin", true);
    model.result("pg20").set("showlegendsunit", true);
    model.result("pg20").set("legendpos", "alternating");
    model.result("pg20").feature("surf2").label("P \u578b");
    model.result("pg20").feature("surf1").label("N \u578b");
    model.result("pg20").label("\u51c0\u63ba\u6742\u5242\u6d53\u5ea6 (semi) 1");
    model.result("pg17").run();
    model.result("pg20").run();
    model.result().remove("pg20");
    model.result("pg16").run();
    model.result("pg16").feature().duplicate("glob3", "glob1");
    model.result("pg16").run();
    model.result("pg16").feature("glob3").set("data", "dset3");
    model.result("pg16").feature("glob3").setIndex("looplevelinput", "manual", 0);
    model.result("pg16").feature("glob3")
         .setIndex("looplevel", new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13}, 0);
    model.result("pg16").feature("glob3").setIndex("descr", "T=300 K\uff0cAl_frac0=0.30\uff0c\u96a7\u7a7f", 0);
    model.result("pg16").feature("glob3").set("linestyle", "solid");
    model.result("pg16").feature("glob3").set("linecolor", "cyclereset");
    model.result("pg16").feature().duplicate("glob4", "glob3");
    model.result("pg16").run();
    model.result("pg16").feature("glob4").set("data", "dset7");
    model.result("pg16").feature("glob4").setIndex("descr", "\u96a7\u7a7f", 0);
    model.result("pg16").feature("glob4").set("linecolor", "cycle");
    model.result("pg16").run();
    model.result("pg16").set("axislimits", true);
    model.result("pg16").set("xmin", 0);
    model.result("pg16").set("ymin", 0.1);
    model.result("pg16").set("ymax", "1e4");
    model.result("pg16").set("legendpos", "upperleft");
    model.result("pg16").run();

    model.title("\u5f02\u8d28\u7ed3\u96a7\u7a7f");

    model
         .description("\u672c\u57fa\u51c6\u6a21\u578b\u6a21\u62df\u5206\u7ea7\u5f02\u8d28\u7ed3\uff0c\u5176\u4e2d\u4f7f\u7528\u70ed\u7535\u5b50\u53d1\u5c04\u516c\u5f0f\u6c42\u89e3\u5f02\u8d28\u7ed3\u4e0a\u7684\u7535\u8377\u8f6c\u79fb\uff0c\u672c\u4f8b\u901a\u8fc7\u4f7f\u7528 WKB \u8fd1\u4f3c\uff0c\u663e\u793a\u4e86\u8de8\u8d8a\u52bf\u5792\u7684\u91cf\u5b50\u96a7\u7a7f\u6548\u5e94\u5bf9\u7535\u6d41\u5bc6\u5ea6\u7684\u989d\u5916\u8d21\u732e\u3002\u5c3d\u7ba1\u6a21\u62df\u7684\u7cfb\u7edf\u672c\u8d28\u4e0a\u662f\u4e00\u7ef4\u6a21\u578b\uff0c\u4f46\u5176\u4e2d\u8fd8\u521b\u5efa\u4e86\u4e8c\u7ef4\u6a21\u578b\u6765\u6f14\u793a\u8f83\u5e38\u89c4\u6784\u578b\u7684\u4eff\u771f\u8fc7\u7a0b\uff1b\u4e0d\u4ec5\u5982\u6b64\uff0c\u8fd8\u6f14\u793a\u4e86\u5982\u4f55\u8bbe\u7f6e\u7528\u6237\u5b9a\u4e49\u7684\u4e09\u5143\u6750\u6599\u5c5e\u6027\u3002\u8ba1\u7b97\u5f97\u5230\u7684 I-V \u66f2\u7ebf\u53ca\u5176\u6e29\u5ea6\u4f9d\u5b58\u6027\uff0c\u4ee5\u53ca\u80fd\u5e26\u56fe\u5747\u4e0e\u6587\u732e\u7ed3\u679c\u9ad8\u5ea6\u543b\u5408\u3002");

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

    model.label("heterojunction_tunneling.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
