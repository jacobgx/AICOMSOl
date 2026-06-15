/*
 * gan_double_heterostructure_led.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:52 by COMSOL 6.3.0.290. */
public class gan_double_heterostructure_led {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Semiconductor_Module\\Photonic_Devices_and_Sensors");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("semi", "Semiconductor", "geom1");

    model.study().create("std1");
    model.study("std1").create("semie", "SemiconductorEquilibrium");
    model.study("std1").feature("semie").set("ftplistmethod", "manual");
    model.study("std1").feature("semie").set("solnum", "auto");
    model.study("std1").feature("semie").set("notsolnum", "auto");
    model.study("std1").feature("semie").set("outputmap", new String[]{});
    model.study("std1").feature("semie").set("ngenAUX", "1");
    model.study("std1").feature("semie").set("goalngenAUX", "1");
    model.study("std1").feature("semie").set("ngenAUX", "1");
    model.study("std1").feature("semie").set("goalngenAUX", "1");
    model.study("std1").feature("semie").setSolveFor("/physics/semi", true);

    model.param().set("V_n", "0[V]");
    model.param().descr("V_n", "n \u578b\u63a5\u89e6\u7535\u538b");
    model.param().set("V_p", "0[V]");
    model.param().descr("V_p", "p \u578b\u63a5\u89e6\u7535\u538b");
    model.param().set("InGaN_bg", "((h_const*c_const)/450[nm])/(1.6e-19[C])");
    model.param().descr("InGaN_bg", "InGaN \u5c42\u7684\u5e26\u9699\u80fd");
    model.param().set("I_p", "1e-6[A]");
    model.param().descr("I_p", "p \u578b\u63a5\u89e6\u7535\u6d41");
    model.param().set("A_cross", "200[um]*200[um]");
    model.param().descr("A_cross", "\u6a2a\u622a\u9762\u79ef");

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", "0.15[um]", 1);
    model.component("comp1").geom("geom1").run("i1");
    model.component("comp1").geom("geom1").create("i2", "Interval");
    model.component("comp1").geom("geom1").feature("i2").setIndex("coord", "0.15[um]", 0);
    model.component("comp1").geom("geom1").feature("i2").setIndex("coord", "0.2[um]", 1);
    model.component("comp1").geom("geom1").run("i2");
    model.component("comp1").geom("geom1").create("i3", "Interval");
    model.component("comp1").geom("geom1").feature("i3").setIndex("coord", "0.2[um]", 0);
    model.component("comp1").geom("geom1").feature("i3").setIndex("coord", "0.35[um]", 1);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("SemicondMaterial", "SemicondMaterial", "Semiconductor material");
    model.component("comp1").material("mat1").label("GaN - Gallium Nitride [solid,Zinc Blende]");
    model.component("comp1").material("mat1").set("phase", "solid");
    model.component("comp1").material("mat1").set("orientation", "Zinc Blende");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"9.7", "0", "0", "0", "9.7", "0", "0", "0", "9.7"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"130[W/(m*K)]", "0", "0", "0", "130[W/(m*K)]", "0", "0", "0", "130[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "6070[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "490[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").label("Semiconductor material");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").set("Eg0", "3.2[V]");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").set("chi0", "4.1[V]");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial")
         .set("Nv", "(T/1[K])^(3/2)*8e15[1/cm^3]");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial")
         .set("Nc", "(T/1[K])^(3/2)*2.3e14[1/cm^3]");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").set("mun", "1000[cm^2/(V*s)]");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").set("mup", "350[cm^2/(V*s)]");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").addInput("temperature");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("SemicondMaterial", "SemicondMaterial", "Semiconductor material");
    model.component("comp1").material("mat2").label("GaN - Gallium Nitride [solid,Zinc Blende] 1");
    model.component("comp1").material("mat2").set("phase", "solid");
    model.component("comp1").material("mat2").set("orientation", "Zinc Blende");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"9.7", "0", "0", "0", "9.7", "0", "0", "0", "9.7"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"130[W/(m*K)]", "0", "0", "0", "130[W/(m*K)]", "0", "0", "0", "130[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "6070[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "490[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("SemicondMaterial").label("Semiconductor material");
    model.component("comp1").material("mat2").propertyGroup("SemicondMaterial").set("Eg0", "3.2[V]");
    model.component("comp1").material("mat2").propertyGroup("SemicondMaterial").set("chi0", "4.1[V]");
    model.component("comp1").material("mat2").propertyGroup("SemicondMaterial")
         .set("Nv", "(T/1[K])^(3/2)*8e15[1/cm^3]");
    model.component("comp1").material("mat2").propertyGroup("SemicondMaterial")
         .set("Nc", "(T/1[K])^(3/2)*2.3e14[1/cm^3]");
    model.component("comp1").material("mat2").propertyGroup("SemicondMaterial").set("mun", "1000[cm^2/(V*s)]");
    model.component("comp1").material("mat2").propertyGroup("SemicondMaterial").set("mup", "350[cm^2/(V*s)]");
    model.component("comp1").material("mat2").propertyGroup("SemicondMaterial").addInput("temperature");
    model.component("comp1").material("mat1").label("Al_0.15_Ga_0.85_N");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").set("Eg0", new String[]{"3.7[V]"});
    model.component("comp1").material("mat2").label("In_0.06_Ga_0.94_N");
    model.component("comp1").material("mat2").selection().set(2);
    model.component("comp1").material("mat2").propertyGroup("SemicondMaterial").set("Eg0", new String[]{"InGaN_bg"});
    model.component("comp1").material("mat2").propertyGroup("SemicondMaterial").set("chi0", new String[]{"4.6[V]"});

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().set(2);

    model.component("comp1").physics("semi").prop("d").set("A", "A_cross");
    model.component("comp1").physics("semi").prop("ModelProperties").set("CarrierStatistics", "FermiDirac");
    model.component("comp1").physics("semi").create("adm1", "AnalyticDopingModel", 1);
    model.component("comp1").physics("semi").feature("adm1").label("n \u578b\u63ba\u6742");
    model.component("comp1").physics("semi").feature("adm1").selection().all();
    model.component("comp1").physics("semi").feature("adm1").set("impurityDistribution", "box");
    model.component("comp1").physics("semi").feature("adm1").set("impurityType", "donor");
    model.component("comp1").physics("semi").feature("adm1").set("NDc", "1e18[1/cm^3]");
    model.component("comp1").physics("semi").feature("adm1").set("jwidth", "0.125[um]");
    model.component("comp1").physics("semi").feature("adm1").set("jds1D", "0.025[um]");
    model.component("comp1").physics("semi").create("adm2", "AnalyticDopingModel", 1);
    model.component("comp1").physics("semi").feature("adm2").label("p \u578b\u63ba\u6742");
    model.component("comp1").physics("semi").feature("adm2").selection().all();
    model.component("comp1").physics("semi").feature("adm2").set("impurityDistribution", "box");
    model.component("comp1").physics("semi").feature("adm2").set("NAc", "1e18[1/cm^3]");
    model.component("comp1").physics("semi").feature("adm2").set("rb", new String[]{"0.225[um]", "0", "0"});
    model.component("comp1").physics("semi").feature("adm2").set("jwidth", "0.125[um]");
    model.component("comp1").physics("semi").feature("adm2").set("jds1D", "0.025[um]");
    model.component("comp1").physics("semi").create("mc1", "MetalContact", 0);
    model.component("comp1").physics("semi").feature("mc1").label("n \u578b\u63a5\u89e6");
    model.component("comp1").physics("semi").feature("mc1").selection().set(1);
    model.component("comp1").physics("semi").feature("mc1").set("V0", "V_n");
    model.component("comp1").physics("semi").create("mc2", "MetalContact", 0);
    model.component("comp1").physics("semi").feature("mc2").label("p \u578b\u63a5\u89e6");
    model.component("comp1").physics("semi").feature("mc2").selection().set(4);
    model.component("comp1").physics("semi").feature("mc2").set("V0", "V_p");
    model.component("comp1").physics("semi").create("cont2", "Continuity", 0);
    model.component("comp1").physics("semi").feature("cont2").selection().all();
    model.component("comp1").physics("semi").feature("cont2")
         .label("\u8fde\u7eed/\u5f02\u8d28\u7ed3 - \u70ed\u7535\u5b50\u53d1\u5c04");
    model.component("comp1").physics("semi").feature("cont2").set("HeteroModelSelection", 2);
    model.component("comp1").physics("semi").create("ot1", "OpticalTransitions", 1);
    model.component("comp1").physics("semi").feature("ot1").selection().all();
    model.component("comp1").physics("semi").feature("ot1").set("StimulatedEmission", false);
    model.component("comp1").physics("semi").create("mc3", "MetalContact", 0);
    model.component("comp1").physics("semi").feature("mc3").label("p \u578b\u63a5\u89e6 - \u7535\u6d41");
    model.component("comp1").physics("semi").feature("mc3").selection().set(4);
    model.component("comp1").physics("semi").feature("mc3").set("TerminalType", "Current");
    model.component("comp1").physics("semi").feature("mc3").set("I0", "I_p");
    model.component("comp1").physics("semi").create("aur1", "AURecombination", 1);
    model.component("comp1").physics("semi").feature("aur1").selection().all();
    model.component("comp1").physics("semi").create("tar1", "TrapAssistedRecombination", 1);
    model.component("comp1").physics("semi").feature("tar1").selection().all();

    model.component("comp1").material("mat1").propertyGroup().create("Auger", "Auger", "Auger_recombination");
    model.component("comp1").material("mat1").propertyGroup("Auger").set("Cn", new String[]{"1.7e-30[cm^6/s]"});
    model.component("comp1").material("mat1").propertyGroup("Auger").set("Cp", new String[]{"1.7e-30[cm^6/s]"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("SRH", "SRH", "Shockley-Read-Hall_recombination");
    model.component("comp1").material("mat1").propertyGroup("SRH").set("taun", new String[]{"1e-8"});
    model.component("comp1").material("mat1").propertyGroup("SRH").set("taup", new String[]{"1e-8"});
    model.component("comp1").material("mat2").propertyGroup().create("Auger", "Auger", "Auger_recombination");
    model.component("comp1").material("mat2").propertyGroup("Auger").set("Cn", new String[]{"1.7e-30[cm^6/s]"});
    model.component("comp1").material("mat2").propertyGroup("Auger").set("Cp", new String[]{"1.7e-30[cm^6/s]"});
    model.component("comp1").material("mat2").propertyGroup()
         .create("SRH", "SRH", "Shockley-Read-Hall_recombination");
    model.component("comp1").material("mat2").propertyGroup("SRH").set("taun", new String[]{"1e-8"});
    model.component("comp1").material("mat2").propertyGroup("SRH").set("taup", new String[]{"1e-8"});

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().geom("geom1");
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").selection().set(1, 3);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("elemcount", 100);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("elemratio", 6);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("symmetric", true);
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis2").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis2").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis2").set("elemcount", 30);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis2").set("elemratio", 2);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis2").set("symmetric", true);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 1\uff1a\u521d\u6b65\u7814\u7a76");
    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/semi", true);
    model.study("std2").label("\u7814\u7a76 2\uff1a\u504f\u538b");
    model.study("std2").feature("stat").set("useadvanceddisable", true);
    model.study("std2").feature("stat").set("disabledphysics", new String[]{"semi/mc3"});
    model.study("std2").feature("stat").set("useinitsol", true);
    model.study("std2").feature("stat").set("initmethod", "sol");
    model.study("std2").feature("stat").set("initstudy", "std1");
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").setIndex("pname", "V_n", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "V", 0);
    model.study("std2").feature("stat").setIndex("pname", "V_n", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "V", 0);
    model.study("std2").feature("stat").setIndex("pname", "V_p", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "range(0,0.1,3) range(3.025,0.025,3.3)", 0);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").label("\u80fd\u7ea7 (semi)");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "Energy Diagram");
    model.result("pg1").set("ylabel", "Energy (eV)");
    model.result("pg1").feature().create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").label("\u5bfc\u5e26\u80fd\u7ea7");
    model.result("pg1").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr1").set("expr", "semi.Ec_e");
    model.result("pg1").feature("lngr1").set("unit", "eV");
    model.result("pg1").feature("lngr1").set("linecolor", "blue");
    model.result("pg1").feature("lngr1").set("legend", true);
    model.result("pg1").feature("lngr1").set("legendmethod", "manual");
    model.result("pg1").feature("lngr1").set("legends", new String[]{"Ec"});
    model.result("pg1").feature("lngr1").set("resolution", "norefine");
    model.result("pg1").feature("lngr1").set("smooth", "everywhere");
    model.result("pg1").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr1").set("data", "parent");
    model.result("pg1").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr1").selection().set(1, 2, 3);
    model.result("pg1").feature().create("lngr2", "LineGraph");
    model.result("pg1").feature("lngr2").label("\u7535\u5b50\u51c6\u8d39\u7c73\u80fd\u7ea7");
    model.result("pg1").feature("lngr2").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr2").set("expr", "semi.Efn_e");
    model.result("pg1").feature("lngr2").set("unit", "eV");
    model.result("pg1").feature("lngr2").set("linestyle", "dashed");
    model.result("pg1").feature("lngr2").set("linecolor", "black");
    model.result("pg1").feature("lngr2").set("legend", true);
    model.result("pg1").feature("lngr2").set("legendmethod", "manual");
    model.result("pg1").feature("lngr2").set("legends", new String[]{"Efn"});
    model.result("pg1").feature("lngr2").set("resolution", "norefine");
    model.result("pg1").feature("lngr2").set("smooth", "everywhere");
    model.result("pg1").feature("lngr2").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr2").set("data", "parent");
    model.result("pg1").feature("lngr2").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr2").selection().set(1, 2, 3);
    model.result("pg1").feature().create("lngr3", "LineGraph");
    model.result("pg1").feature("lngr3").label("\u7a7a\u7a74\u51c6\u8d39\u7c73\u80fd\u7ea7");
    model.result("pg1").feature("lngr3").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr3").set("expr", "semi.Efp_e");
    model.result("pg1").feature("lngr3").set("unit", "eV");
    model.result("pg1").feature("lngr3").set("linestyle", "dotted");
    model.result("pg1").feature("lngr3").set("linecolor", "black");
    model.result("pg1").feature("lngr3").set("legend", true);
    model.result("pg1").feature("lngr3").set("legendmethod", "manual");
    model.result("pg1").feature("lngr3").set("legends", new String[]{"Efp"});
    model.result("pg1").feature("lngr3").set("resolution", "norefine");
    model.result("pg1").feature("lngr3").set("smooth", "everywhere");
    model.result("pg1").feature("lngr3").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr3").set("data", "parent");
    model.result("pg1").feature("lngr3").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr3").selection().set(1, 2, 3);
    model.result("pg1").feature().create("lngr4", "LineGraph");
    model.result("pg1").feature("lngr4").label("\u4ef7\u5e26\u80fd\u7ea7");
    model.result("pg1").feature("lngr4").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr4").set("expr", "semi.Ev_e");
    model.result("pg1").feature("lngr4").set("unit", "eV");
    model.result("pg1").feature("lngr4").set("linecolor", "green");
    model.result("pg1").feature("lngr4").set("legend", true);
    model.result("pg1").feature("lngr4").set("legendmethod", "manual");
    model.result("pg1").feature("lngr4").set("legends", new String[]{"Ev"});
    model.result("pg1").feature("lngr4").set("resolution", "norefine");
    model.result("pg1").feature("lngr4").set("smooth", "everywhere");
    model.result("pg1").feature("lngr4").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr4").set("data", "parent");
    model.result("pg1").feature("lngr4").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr4").selection().set(1, 2, 3);
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").label("\u8f7d\u6d41\u5b50\u6d53\u5ea6 (semi)");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result("pg2").set("ylabel", "Carrier concentration (1/cm^3)");
    model.result("pg2").set("ylog", true);
    model.result("pg2").feature().create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").label("\u7535\u5b50\u6d53\u5ea6");
    model.result("pg2").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg2").feature("lngr1").set("expr", "semi.N");
    model.result("pg2").feature("lngr1").set("unit", "1/cm^3");
    model.result("pg2").feature("lngr1").set("legend", true);
    model.result("pg2").feature("lngr1").set("legendmethod", "manual");
    model.result("pg2").feature("lngr1").set("legends", new String[]{"electrons"});
    model.result("pg2").feature("lngr1").set("resolution", "norefine");
    model.result("pg2").feature("lngr1").set("smooth", "everywhere");
    model.result("pg2").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg2").feature("lngr1").set("data", "parent");
    model.result("pg2").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg2").feature("lngr1").selection().set(1, 2, 3);
    model.result("pg2").feature().create("lngr2", "LineGraph");
    model.result("pg2").feature("lngr2").label("\u7a7a\u7a74\u6d53\u5ea6");
    model.result("pg2").feature("lngr2").set("showsolutionparams", "on");
    model.result("pg2").feature("lngr2").set("expr", "semi.P");
    model.result("pg2").feature("lngr2").set("unit", "1/cm^3");
    model.result("pg2").feature("lngr2").set("legend", true);
    model.result("pg2").feature("lngr2").set("legendmethod", "manual");
    model.result("pg2").feature("lngr2").set("legends", new String[]{"holes"});
    model.result("pg2").feature("lngr2").set("resolution", "norefine");
    model.result("pg2").feature("lngr2").set("smooth", "everywhere");
    model.result("pg2").feature("lngr2").set("showsolutionparams", "on");
    model.result("pg2").feature("lngr2").set("data", "parent");
    model.result("pg2").feature("lngr2").selection().geom("geom1", 1);
    model.result("pg2").feature("lngr2").selection().set(1, 2, 3);
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").label("\u7535\u52bf (semi)");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result("pg3").set("ylabel", "Electric Potential (V)");
    model.result("pg3").feature().create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg3").feature("lngr1").set("expr", "V");
    model.result("pg3").feature("lngr1").set("resolution", "norefine");
    model.result("pg3").feature("lngr1").set("smooth", "everywhere");
    model.result("pg3").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg3").feature("lngr1").set("data", "parent");
    model.result("pg3").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg3").feature("lngr1").selection().set(1, 2, 3);
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").create("lngr2", "LineGraph");
    model.result("pg4").feature("lngr1").selection().all();
    model.result("pg4").feature("lngr1").set("xdataexpr", "X");
    model.result("pg4").feature("lngr1").set("expr", "semi.Nnetdop");
    model.result("pg4").feature("lngr1").set("unit", "1/cm^3");
    model.result("pg4").feature("lngr1").set("linecolor", "red");
    model.result("pg4").feature("lngr1").set("legend", true);
    model.result("pg4").feature("lngr1").set("legendmethod", "manual");
    model.result("pg4").feature("lngr1").set("legends", new String[]{"P \u578b (\u7ea2\u8272)"});
    model.result("pg4").feature("lngr1").feature().create("filt1", "LineGraphFilter");
    model.result("pg4").feature("lngr1").feature("filt1").set("expr", "semi.Na-semi.Nd > 1[1/cm^3]");
    model.result("pg4").feature("lngr2").selection().all();
    model.result("pg4").feature("lngr2").set("xdataexpr", "X");
    model.result("pg4").feature("lngr2").set("expr", "semi.Nnetdop");
    model.result("pg4").feature("lngr2").set("unit", "1/cm^3");
    model.result("pg4").feature("lngr2").set("linecolor", "blue");
    model.result("pg4").feature("lngr2").set("legend", true);
    model.result("pg4").feature("lngr2").set("legendmethod", "manual");
    model.result("pg4").feature("lngr2").set("legends", new String[]{"N \u578b (\u84dd\u8272)"});
    model.result("pg4").feature("lngr2").feature().create("filt1", "LineGraphFilter");
    model.result("pg4").feature("lngr2").feature("filt1").set("expr", "semi.Nd-semi.Na > 1[1/cm^3]");
    model.result("pg4").set("titletype", "none");
    model.result("pg4").set("legendpos", "uppermiddle");
    model.result("pg4")
         .set("ylabel", "\u51c0\u63ba\u6742\u5242\u6d53\u5ea6 \\vert N<sub>d</sub> - N<sub>a</sub>\\vert, 1/cm<sup>3</sup>");
    model.result("pg4").set("ylog", true);
    model.result("pg4").feature("lngr1").label("P \u578b");
    model.result("pg4").feature("lngr2").label("N \u578b");
    model.result("pg4").label("\u51c0\u63ba\u6742\u5242\u6d53\u5ea6 (semi)");
    model.result("pg1").run();
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "x \u5750\u6807 (um)");
    model.result("pg1").setIndex("looplevelinput", "manual", 0);
    model.result("pg1").setIndex("looplevel", new int[]{1}, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", new int[]{43}, 0);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "x \u5750\u6807 (um)");
    model.result("pg2").setIndex("looplevelinput", "manual", 0);
    model.result("pg2").setIndex("looplevel", new int[]{1}, 0);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", new int[]{43}, 0);
    model.result("pg2").run();
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevelinput", "first", 0);
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u7535\u6d41 vs. \u7535\u538b");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").set("titletype", "manual");
    model.result("pg5").set("title", "\u7535\u6d41-\u7535\u538b\u66f2\u7ebf");
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "\u7535\u538b (V)");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "\u7535\u6d41 (mA)");
    model.result("pg5").set("legendpos", "upperleft");
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").setIndex("expr", "abs(semi.I0_1)", 0);
    model.result("pg5").feature("glob1").setIndex("unit", "mA", 0);
    model.result("pg5").feature("glob1").setIndex("descr", "\u504f\u538b", 0);
    model.result("pg5").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("data", "dset2");
    model.result().numerical("gev1").setIndex("expr", "semi.I0_2", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();

    model.component("comp1").physics("semi").feature("mc3").set("V0_init", "2.2[V]");

    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").setSolveFor("/physics/semi", true);
    model.study("std3").label("\u7814\u7a76 3\uff1a\u504f\u7f6e\u7535\u6d41");
    model.study("std3").setGenPlots(false);
    model.study("std3").feature("stat").set("useinitsol", true);
    model.study("std3").feature("stat").set("initmethod", "sol");
    model.study("std3").feature("stat").set("initstudy", "std2");
    model.study("std3").feature("stat").set("solnum", 23);
    model.study("std3").feature("stat").set("useparam", true);
    model.study("std3").feature("stat").setIndex("pname", "V_n", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "", 0);
    model.study("std3").feature("stat").setIndex("punit", "V", 0);
    model.study("std3").feature("stat").setIndex("pname", "V_n", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "", 0);
    model.study("std3").feature("stat").setIndex("punit", "V", 0);
    model.study("std3").feature("stat").setIndex("pname", "I_p", 0);
    model.study("std3").feature("stat")
         .setIndex("plistarr", "1e-6 1e-5 1e-4 range(5e-4,2.5e-4,10e-3) range(15e-3,5e-3,100e-3) range(125e-3,25e-3,700e-3)", 0);
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result("pg5").run();
    model.result("pg5").feature().duplicate("glob2", "glob1");
    model.result("pg5").run();
    model.result("pg5").feature("glob2").set("data", "dset3");
    model.result("pg5").feature("glob2").setIndex("expr", "abs(semi.I0_3)", 0);
    model.result("pg5").feature("glob2").setIndex("descr", "\u504f\u7f6e\u7535\u6d41", 0);
    model.result("pg5").feature("glob2").set("xdata", "expr");
    model.result("pg5").feature("glob2").set("xdataexpr", "semi.V0_3");
    model.result("pg5").feature("glob2").set("linestyle", "dashed");
    model.result("pg5").feature("glob2").set("linemarker", "cycle");
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u53d1\u5c04\u7387");
    model.result("pg6").set("data", "dset3");
    model.result("pg6").set("titletype", "manual");
    model.result("pg6").set("title", "\u53d1\u5c04\u7387 (1/(m^3*s))");
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("xlabel", "x \u5750\u6807 (um)");
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg6").feature("lngr1").set("linewidth", "preference");
    model.result("pg6").feature("lngr1").selection().all();
    model.result("pg6").feature("lngr1").set("expr", "semi.ot1.R_spon");
    model.result("pg6").run();
    model.result().dataset().create("dset4", "Solution");
    model.result().dataset("dset4").set("solution", "sol3");
    model.result().dataset("dset4").set("comp", "semi_ot1_xdim");
    model.result().dataset("dset4").label("\u504f\u7f6e\u7535\u6d41\u7814\u7a76 - \u9891\u57df");
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u53d1\u5c04\u5149\u8c31");
    model.result("pg7").set("data", "dset4");
    model.result("pg7").set("titletype", "manual");
    model.result("pg7").set("title", "InGaN \u5c42\u7684\u53d1\u5c04\u5149\u8c31");
    model.result("pg7").set("xlabelactive", true);
    model.result("pg7").set("xlabel", "\u80fd\u91cf (eV)");
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7")
         .set("ylabel", "\u5355\u4f4d\u4f53\u79ef\u548c\u80fd\u91cf\u7684\u53d1\u5c04\u529f\u7387 (1/(m^3*s))");
    model.result("pg7").create("lngr1", "LineGraph");
    model.result("pg7").feature("lngr1").set("markerpos", "datapoints");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg7").feature("lngr1").set("linewidth", "preference");
    model.result("pg7").feature("lngr1").selection().all();
    model.result("pg7").feature("lngr1").set("expr", "comp1.atxd1(0.1525e-6,semi.ot1.dP_dE)");
    model.result("pg7").feature("lngr1").set("xdata", "expr");
    model.result("pg7").feature("lngr1")
         .set("xdataexpr", "hbar_const*comp1.atxd1(0.1525e-6,semi.ot1.omega)/e_const");
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").label("InGaN \u5c42\u7684\u603b\u53d1\u5c04\u7387 vs. \u7535\u6d41");
    model.result("pg8").set("data", "dset3");
    model.result("pg8").set("xlabelactive", true);
    model.result("pg8").set("xlabel", "\u7535\u6d41 (mA)");
    model.result("pg8").set("ylabelactive", true);
    model.result("pg8").set("ylabel", "InGaN \u5c42\u7684\u603b\u53d1\u5c04\u7387 (1/s)");
    model.result("pg8").set("titletype", "manual");
    model.result("pg8").set("title", "InGaN \u5c42\u7684\u603b\u53d1\u5c04\u7387");
    model.result("pg8").create("glob1", "Global");
    model.result("pg8").feature("glob1").set("markerpos", "datapoints");
    model.result("pg8").feature("glob1").set("linewidth", "preference");
    model.result("pg8").feature("glob1").setIndex("expr", "intop1(semi.ot1.R_spon)*A_cross", 0);
    model.result("pg8").feature("glob1").setIndex("unit", "", 0);
    model.result("pg8").feature("glob1").set("xdata", "expr");
    model.result("pg8").feature("glob1").set("xdataexpr", "I_p");
    model.result("pg8").feature("glob1").set("xdataunit", "mA");
    model.result("pg8").feature("glob1").set("legend", false);
    model.result("pg8").run();
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").run();
    model.result("pg9").label("\u6548\u7387");
    model.result("pg9").set("data", "dset3");
    model.result("pg9").set("xlabelactive", true);
    model.result("pg9").set("xlabel", "\u7535\u6d41\u5bc6\u5ea6 (A/cm^2)");
    model.result("pg9").set("ylabelactive", true);
    model.result("pg9").set("ylabel", "\u5185\u91cf\u5b50\u6548\u7387");
    model.result("pg9").set("titletype", "manual");
    model.result("pg9").set("title", "\u6548\u7387\u4e0e\u7535\u6d41\u5bc6\u5ea6\u7684\u5173\u7cfb");
    model.result("pg9").create("glob1", "Global");
    model.result("pg9").feature("glob1").set("markerpos", "datapoints");
    model.result("pg9").feature("glob1").set("linewidth", "preference");
    model.result("pg9").feature("glob1").setIndex("expr", "intop1(semi.ot1.R_spon)*A_cross/(semi.I0_3/e_const)", 0);
    model.result("pg9").feature("glob1").set("xdata", "expr");
    model.result("pg9").feature("glob1").set("xdataexpr", "semi.I0_3/(A_cross*10000)");
    model.result("pg9").feature("glob1").set("legend", false);
    model.result("pg9").run();

    model.title("InGaN/AlGaN \u53cc\u5f02\u8d28\u7ed3 LED");

    model
         .description("\u672c\u6a21\u578b\u6a21\u62df GaN \u57fa\u53d1\u5149\u4e8c\u6781\u7ba1\uff0c\u5e76\u8ba1\u7b97\u53d1\u5c04\u5f3a\u5ea6\u3001\u5149\u8c31\u548c\u91cf\u5b50\u6548\u7387\u968f\u9a71\u52a8\u7535\u6d41\u7684\u53d8\u5316\u60c5\u51b5\u3002\u672c\u4f8b\u4e3a\u5e26\u9699\u7684\u76f4\u63a5\u8f90\u5c04\u590d\u5408\u4ee5\u53ca\u975e\u8f90\u5c04\u4fc4\u6b47\u590d\u5408\u548c\u9677\u9631\u8f85\u52a9\u590d\u5408\u8fc7\u7a0b\u5efa\u6a21\u3002\u4eff\u771f\u7ed3\u679c\u8868\u660e\uff0c\u53d1\u5c04\u5f3a\u5ea6\u968f\u7740\u7535\u6d41\u7684\u589e\u52a0\u800c\u5448\u6b21\u7ebf\u6027\u589e\u52a0\uff0c\u8fd9\u662f LED \u5668\u4ef6\u7684\u5171\u540c\u7279\u6027\uff0c\u79f0\u4e3a\u201cLED \u5149\u6548\u4e0b\u964d\u201d\u3002\u8bf7\u6ce8\u610f\uff0c\u8be5\u6a21\u578b\u4e0d\u5305\u542b\u8584\u6709\u6e90\u533a\u5185\u7684\u91cf\u5b50\u9650\u57df\u6548\u5e94\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("gan_double_heterostructure_led.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
