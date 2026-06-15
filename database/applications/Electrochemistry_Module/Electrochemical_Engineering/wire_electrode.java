/*
 * wire_electrode.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:54 by COMSOL 6.3.0.290. */
public class wire_electrode {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Electrochemistry_Module\\Electrochemical_Engineering");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("cd", "PrimaryCurrentDistribution", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/cd", true);

    model.component("comp1").geom("geom1").insertFile("wire_electrode_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("sel3");

    model.param().set("Ecell", "1.3[V]");
    model.param().descr("Ecell", "\u7535\u6c60\u7535\u538b");
    model.param().set("Eeq_c", "0[V]");
    model.param().descr("Eeq_c", "\u9634\u6781\u5e73\u8861\u7535\u4f4d");
    model.param().set("Eeq_a", "1.2[V]");
    model.param().descr("Eeq_a", "\u9633\u6781\u5e73\u8861\u7535\u4f4d");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("rho", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("cs", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an3", "Analytic");
    model.component("comp1").material("mat1").label("Water, liquid");
    model.component("comp1").material("mat1").set("family", "water");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"273.15", "413.15", "1.3799566804-0.021224019151*T^1+1.3604562827E-4*T^2-4.6454090319E-7*T^3+8.9042735735E-10*T^4-9.0790692686E-13*T^5+3.8457331488E-16*T^6"}, {"413.15", "553.75", "0.00401235783-2.10746715E-5*T^1+3.85772275E-8*T^2-2.39730284E-11*T^3"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"273.15", "553.75", "12010.1471-80.4072879*T^1+0.309866854*T^2-5.38186884E-4*T^3+3.62536437E-7*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").label("Piecewise 3");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("smooth", "contd1");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("pieces", new String[][]{{"273.15", "293.15", "0.000063092789034*T^3-0.060367639882855*T^2+18.9229382407066*T-950.704055329848"}, {"293.15", "373.15", "0.000010335053319*T^3-0.013395065634452*T^2+4.969288832655160*T+432.257114008512"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").label("Piecewise 4");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"273.15", "553.75", "-0.869083936+0.00894880345*T^1-1.58366345E-5*T^2+7.97543259E-9*T^3"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").label("Interpolation");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("table", new String[][]{{"273", "1403"}, 
         {"278", "1427"}, 
         {"283", "1447"}, 
         {"293", "1481"}, 
         {"303", "1507"}, 
         {"313", "1526"}, 
         {"323", "1541"}, 
         {"333", "1552"}, 
         {"343", "1555"}, 
         {"353", "1555"}, 
         {"363", "1550"}, 
         {"373", "1543"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("interp", "piecewisecubic");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("fununit", new String[]{"m/s"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("expr", "-1/rho(T)*d(rho(T),T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").label("Analytic 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("funcname", "gamma_w");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("expr", "1+(T/Cp(T))*(alpha_p(T)*cs(T))^2");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("fununit", "1");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").label("Analytic 3");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("funcname", "muB");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("expr", "2.79*eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3")
         .set("plotargs", new String[][]{{"T", "273.15", "553.75"}});
    model.component("comp1").material("mat1").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(T)", "0", "0", "0", "alpha_p(T)", "0", "0", "0", "alpha_p(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("ratioofspecificheat", "gamma_w(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "rho(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ElectrolyteConductivity", "ElectrolyteConductivity", "Electrolyte_conductivity");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity")
         .set("sigmal", new String[]{"10[S/m]"});
    model.component("comp1").material("mat1").label("\u7535\u89e3\u8d28");

    model.component("comp1").physics("cd").create("es1", "ElectrodeSurface", 2);
    model.component("comp1").physics("cd").feature("es1").selection().set(2, 5);

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").label("\u9634\u6781");
    model.component("comp1").selection("sel1").set(2, 5);

    model.component("comp1").physics("cd").feature("es1").selection().named("sel1");
    model.component("comp1").physics("cd").feature("es1").feature("er1").set("Eeq", "Eeq_c");
    model.component("comp1").physics("cd").create("es2", "ElectrodeSurface", 2);
    model.component("comp1").physics("cd").feature("es2").selection()
         .set(6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51);

    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").label("\u9633\u6781");
    model.component("comp1").selection("sel2")
         .set(6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51);

    model.component("comp1").physics("cd").feature("es2").selection().named("sel2");
    model.component("comp1").physics("cd").feature("es2").set("phisext0", "Ecell");
    model.component("comp1").physics("cd").feature("es2").feature("er1").set("Eeq", "Eeq_a");
    model.component("comp1").physics("cd").feature("init1").set("phil", "(Ecell-Eeq_a-Eeq_c)/2");

    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(3);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().named("geom1_boxsel1");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 6);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blstretch", 1.3);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("inittype", "blhmin");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blhmin", "2e-5[m]");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp1", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").selection().named("sel1");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").set("blnlayers", 2);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").set("blstretch", 1.3);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").set("blhminfact", 5);
    model.component("comp1").mesh("mesh1").feature("size").set("table", "cfd");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").set("opname", "anode_int");
    model.component("comp1").cpl("intop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop1").selection().named("geom1_boxsel1");
    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").set("opname", "anode_avg");
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("aveop1").selection().named("geom1_boxsel1");

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "Ecell", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "V", 0);
    model.study("std1").feature("stat").setIndex("pname", "Ecell", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "V", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(1.25,0.05,1.8)", 0);
    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();
    model.sol("sol1").copySolution("sol2");
    model.sol("sol2").label("\u4e00\u6b21\u7535\u6d41\u5206\u5e03");

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").label("\u6781\u5316\u56fe");
    model.result("pg1").set("data", "none");
    model.result("pg1").set("titletype", "label");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "\u7535\u6c60\u7535\u538b (V)");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u603b\u7535\u6d41 (A)");
    model.result("pg1").set("legendpos", "upperleft");
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("markerpos", "datapoints");
    model.result("pg1").feature("glob1").set("linewidth", "preference");
    model.result("pg1").feature("glob1").set("data", "dset2");
    model.result("pg1").feature("glob1").setIndex("expr", "abs(anode_int(cd.nIl))", 0);
    model.result("pg1").feature("glob1").set("legendmethod", "manual");
    model.result("pg1").feature("glob1").setIndex("legends", "\u4e00\u6b21\u7535\u6d41\u5206\u5e03", 0);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").label("\u7535\u89e3\u8d28\u7535\u4f4d");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevel", 5, 0);
    model.result("pg2").set("edges", false);
    model.result("pg2").create("iso1", "Isosurface");
    model.result("pg2").feature("iso1").set("colortable", "Bryophyta");
    model.result("pg2").run();

    model.component("comp1").view("view1").set("ssao", true);

    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").set("entitydim", 2);
    model.component("comp1").selection("uni1").set("input", new String[]{"sel1", "sel2"});
    model.component("comp1").selection("uni1").label("\u9633\u6781\u548c\u9634\u6781");

    model.component("comp1").view("view1").hideEntities().create("hide1");
    model.component("comp1").view("view1").hideEntities("hide1").geom(2);
    model.component("comp1").view("view1").hideEntities("hide1").add(4);
    model.component("comp1").view("view1").hideEntities("hide1").add(2);

    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").label("\u65e0\u91cf\u7eb2\u7535\u6d41\u5bc6\u5ea6\u5206\u5e03");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 5, 0);
    model.result("pg3").selection().geom("geom1", 2);
    model.result("pg3").selection().named("uni1");
    model.result("pg3").set("titletype", "label");
    model.result("pg3").set("edges", false);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "(comp1.cd.nIl)/anode_avg(comp1.cd.nIl)");
    model.result("pg3").feature("surf1").set("rangecoloractive", true);
    model.result("pg3").feature("surf1").set("rangecolormin", -2);
    model.result("pg3").feature("surf1").set("rangecolormax", 2);
    model.result("pg3").feature("surf1").set("colortable", "Ctenophora");
    model.result("pg3").run();

    model.component("comp1").physics("cd").prop("CurrentDistributionType")
         .set("CurrentDistributionType", "Secondary");

    model.param().set("i0_c", "100[A/m^2]");
    model.param().descr("i0_c", "\u9634\u6781\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("i0_a", "100[A/m^2]");
    model.param().descr("i0_a", "\u9633\u6781\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("be_c", "0.5");
    model.param().descr("be_c", "\u9634\u6781\u5bf9\u79f0\u56e0\u5b50");
    model.param().set("be_a", "0.5");
    model.param().descr("be_a", "\u9633\u6781\u5bf9\u79f0\u56e0\u5b50");
    model.param().set("T", "298[K]");
    model.param().descr("T", "\u6e29\u5ea6");

    model.component("comp1").physics("cd").feature("es1").feature("er1").set("ElectrodeKinetics", "ButlerVolmer");
    model.component("comp1").physics("cd").feature("es1").feature("er1").set("i0", "i0_c");
    model.component("comp1").physics("cd").feature("es1").feature("er1").set("alphaa", "be_c");
    model.component("comp1").physics("cd").feature("es1").feature("er1").set("alphac", "1-be_c");
    model.component("comp1").physics("cd").feature("es2").feature("er1").set("ElectrodeKinetics", "ButlerVolmer");
    model.component("comp1").physics("cd").feature("es2").feature("er1").set("i0", "i0_a");
    model.component("comp1").physics("cd").feature("es2").feature("er1").set("alphaa", "be_a");
    model.component("comp1").physics("cd").feature("es2").feature("er1").set("alphac", "1-be_a");

    model.common("cminpt").set("modified", new String[][]{{"temperature", "T"}});

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg2").run();

    model.sol("sol1").label("\u4e8c\u6b21\u7535\u6d41\u5206\u5e03");

    model.result("pg1").run();
    model.result("pg1").feature().duplicate("glob2", "glob1");
    model.result("pg1").run();
    model.result("pg1").feature("glob2").set("data", "dset1");
    model.result("pg1").feature("glob2").setIndex("legends", "\u4e8c\u6b21\u7535\u6d41\u5206\u5e03", 0);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 9, 0);
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 9, 0);
    model.result("pg3").run();

    model.param().set("D", "1e-9[m^2/s]");
    model.param().descr("D", "\u6269\u6563\u7cfb\u6570");
    model.param().set("c_in", "1e3[mol/m^3]");
    model.param().descr("c_in", "\u5165\u53e3\u6d53\u5ea6");
    model.param().set("u_in", "5[mm/s]");
    model.param().descr("u_in", "\u5165\u53e3\u6d41\u901f");

    model.component("comp1").physics().create("tds", "DilutedSpecies", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/tds", true);

    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);

    model.component("comp1").physics("tds").prop("TransportMechanism").set("Migration", true);
    model.component("comp1").physics("tds").feature("sp1").setIndex("z", -1, 0);
    model.component("comp1").physics("tds").feature("cdm1")
         .set("D_c", new String[]{"D", "0", "0", "0", "D", "0", "0", "0", "D"});
    model.component("comp1").physics("tds").create("in1", "Inflow", 2);
    model.component("comp1").physics("tds").feature("in1").selection().set(1);

    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").geom(2);
    model.component("comp1").selection("sel3").label("\u5165\u53e3");
    model.component("comp1").selection("sel3").set(1);

    model.component("comp1").physics("tds").feature("in1").selection().named("sel3");
    model.component("comp1").physics("tds").feature("in1").setIndex("c0", "c_in", 0);
    model.component("comp1").physics("tds").create("out1", "Outflow", 2);
    model.component("comp1").physics("tds").feature("out1").selection().set(52);

    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").geom(2);
    model.component("comp1").selection("sel4").label("\u51fa\u53e3");
    model.component("comp1").selection("sel4").set(52);

    model.component("comp1").physics("tds").feature("out1").selection().named("sel4");
    model.component("comp1").physics("tds").feature("init1").setIndex("initc", "c_in", 0);
    model.component("comp1").physics("tds").create("eeic1", "ElectrodeElectrolyteInterfaceCoupling", 2);
    model.component("comp1").physics("tds").feature("eeic1").selection().named("geom1_boxsel1");
    model.component("comp1").physics("tds").feature("eeic1").feature("rc1")
         .set("iloc_src", "root.comp1.cd.es2.er1.iloc");
    model.component("comp1").physics("tds").feature("eeic1").feature("rc1").setIndex("Vib", 1, 0);
    model.component("comp1").physics("cd").feature("es2").feature("er1").set("Eeq_mat", "NernstEquation");
    model.component("comp1").physics("cd").feature("es2").feature("er1").set("Eeq_ref", "Eeq_a");
    model.component("comp1").physics("cd").feature("es2").feature("er1").set("CRNernst", "c/c_in");
    model.component("comp1").physics("cd").feature("es2").feature("er1").set("i0Type", "FromNernstEquation");
    model.component("comp1").physics("cd").feature("es2").feature("er1").set("i0_ref", "i0_a");
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl1").selection().named("sel3");
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf").feature("inl1").set("Uavfdf", "u_in");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("spf").feature("out1").selection().named("geom1_sel3");
    model.component("comp1").physics("spf").feature("out1").set("NormalFlow", true);
    model.component("comp1").physics("spf").create("wallbc2", "WallBC", 2);
    model.component("comp1").physics("spf").feature("wallbc2").selection().set(3, 4);
    model.component("comp1").physics("spf").feature("wallbc2").set("BoundaryCondition", "Slip");

    model.component("comp1").multiphysics().create("rfd1", "ReactingFlowDS", 3);
    model.component("comp1").multiphysics().create("pc1", "PotentialCoupling", 3);
    model.component("comp1").multiphysics("pc1").selection().set(1);

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/cd", true);
    model.study("std2").feature("stat").setSolveFor("/physics/tds", true);
    model.study("std2").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/rfd1", true);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/pc1", true);
    model.study("std2").feature("stat").setSolveFor("/physics/cd", false);
    model.study("std2").feature("stat").setSolveFor("/physics/tds", false);
    model.study("std2").create("stat2", "Stationary");
    model.study("std2").feature("stat2").setSolveFor("/physics/spf", false);
    model.study("std2").feature("stat2").set("useparam", true);
    model.study("std2").feature("stat2").setIndex("pname", "Ecell", 0);
    model.study("std2").feature("stat2").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat2").setIndex("punit", "V", 0);
    model.study("std2").feature("stat2").setIndex("pname", "Ecell", 0);
    model.study("std2").feature("stat2").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat2").setIndex("punit", "V", 0);
    model.study("std2").feature("stat2").setIndex("plistarr", "range(1.25,0.05,1.8)", 0);
    model.study("std2").showAutoSequences("all");

    model.sol("sol3").feature("s2").create("fc1", "FullyCoupled");
    model.sol("sol3").feature("s2").feature("fc1").set("linsolver", "d1");

    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.study("std2").label("\u4e09\u6b21\u7535\u6d41\u5206\u5e03");

    model.result("pg1").run();
    model.result("pg1").feature().duplicate("glob3", "glob2");
    model.result("pg1").run();
    model.result("pg1").feature("glob3").set("data", "dset3");
    model.result("pg1").feature("glob3").setIndex("legends", "\u4e09\u6b21\u7535\u6d41\u5206\u5e03", 0);
    model.result("pg1").run();
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").label("\u901f\u5ea6");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").create("slc1", "Slice");
    model.result("pg4").feature("slc1").set("expr", "spf.U");
    model.result("pg4").feature("slc1").set("descr", "\u901f\u5ea6\u5927\u5c0f");
    model.result("pg4").feature("slc1").set("quickxnumber", 7);
    model.result("pg4").feature("slc1").set("colortable", "Metasepia");
    model.result("pg4").feature("slc1").set("colortabletrans", "reverse");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").run();
    model.result("pg5").label("\u6d53\u5ea6");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").set("titletype", "label");
    model.result("pg5").set("edges", false);
    model.result("pg5").set("showlegendsunit", true);
    model.result("pg5").create("arwv1", "ArrowVolume");
    model.result("pg5").feature("arwv1").set("expr", new String[]{"u", "v", "w"});
    model.result("pg5").feature("arwv1").set("descr", "\u901f\u5ea6\u573a");
    model.result("pg5").feature("arwv1").set("xnumber", 10);
    model.result("pg5").feature("arwv1").set("ynumber", 10);
    model.result("pg5").feature("arwv1").set("znumber", 1);
    model.result("pg5").feature("arwv1").set("color", "white");
    model.result("pg5").feature().duplicate("arwv2", "arwv1");
    model.result("pg5").run();
    model.result("pg5").feature("arwv2").set("titletype", "none");
    model.result("pg5").feature("arwv2").set("ynumber", 1);
    model.result("pg5").feature("arwv2").set("znumber", 10);
    model.result("pg5").feature("arwv2").set("inheritplot", "arwv1");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").create("mslc1", "Multislice");
    model.result("pg5").feature("mslc1").set("expr", "c");
    model.result("pg5").feature("mslc1").set("descr", "\u6469\u5c14\u6d53\u5ea6\uff0cc");
    model.result("pg5").feature("mslc1").set("xnumber", "0");
    model.result("pg5").feature("mslc1").set("colortable", "Pelagic");
    model.result("pg5").feature("mslc1").create("vis1", "VisualEffects");
    model.result("pg5").run();
    model.result("pg5").feature("mslc1").feature("vis1").set("lighting", false);
    model.result("pg5").run();
    model.result("pg5").feature("mslc1").create("tran1", "Transparency");
    model.result("pg5").run();
    model.result("pg5").feature("mslc1").feature("tran1").set("transparency", 0.2);
    model.result("pg5").run();
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").label("\u9633\u6781");
    model.result("pg5").feature("surf1").set("expr", "c");
    model.result("pg5").feature("surf1").set("descr", "\u6469\u5c14\u6d53\u5ea6\uff0cc");
    model.result("pg5").feature("surf1").set("inheritplot", "mslc1");
    model.result("pg5").feature("surf1").create("sel1", "Selection");
    model.result("pg5").feature("surf1").feature("sel1").selection().named("sel2");
    model.result("pg5").create("surf2", "Surface");
    model.result("pg5").feature("surf2").label("\u5730\u677f");
    model.result("pg5").feature("surf2").set("expr", "1");
    model.result("pg5").feature("surf2").create("mtrl1", "MaterialAppearance");
    model.result("pg5").run();
    model.result("pg5").feature("surf2").feature("mtrl1").set("appearance", "custom");
    model.result("pg5").run();
    model.result("pg5").feature("surf2").create("sel1", "Selection");
    model.result("pg5").feature("surf2").feature("sel1").selection().set(3);
    model.result("pg5").create("surf3", "Surface");
    model.result("pg5").feature("surf3").label("\u9634\u6781");
    model.result("pg5").feature("surf3").set("expr", "c");
    model.result("pg5").feature("surf3").set("descr", "\u6469\u5c14\u6d53\u5ea6\uff0cc");
    model.result("pg5").feature("surf3").set("inheritplot", "mslc1");
    model.result("pg5").feature("surf3").create("sel1", "Selection");
    model.result("pg5").feature("surf3").feature("sel1").selection().set(5);
    model.result("pg5").run();
    model.result("pg2").run();
    model.result("pg2").set("data", "dset3");
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").set("data", "dset3");
    model.result("pg3").run();

    model.study("std1").feature("stat").setSolveFor("/physics/tds", false);
    model.study("std1").feature("stat").setSolveFor("/physics/spf", false);

    model.result("pg5").run();

    model.title("\u7ebf\u7535\u6781");

    model
         .description("\u63a7\u5236\u7535\u89e3\u6db2\u548c\u7535\u6781\u7684\u7535\u6d41\u5bc6\u5ea6\u5206\u5e03\u662f\u7535\u5316\u5b66\u6c60\u8bbe\u8ba1\u7684\u91cd\u70b9\u3002\u672c\u4f8b\u6a21\u62df\u7ebf\u7f51\u683c\u7535\u6781\u5355\u5143\u7535\u6c60\u4e2d\u7684\u4e00\u6b21\u3001\u4e8c\u6b21\u548c\u4e09\u6b21\u7535\u6d41\u5206\u5e03\u3002");

    return model;
  }

  public static Model run2(Model model) {

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("wire_electrode.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
